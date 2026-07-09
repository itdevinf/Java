/* For exif reader */
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class classMain {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("classMain");
		System.out.println("1/ Créer un token github");
		System.out.println("1.1/ Se connecter à github>Profil->Setup");
		System.out.println("1.2/ Colonne de gauche->Personnal access tokens->Tokens(classic)");
		System.out.println("1.3/ Colonne de droite->Generate new token(classic)");
		System.out.println("1.4/ Note:TestToken");
		System.out.println("1.5/ Expiration:No expiration");
		System.out.println("1.6/ Sélect scopes:repo");
		System.out.println("1.7/ Generate token");
		System.out.println("1.7/ Copier le token généré");

		System.out.println("2/ Créer un dépot local");
		System.out.println("2.1/ Sélectionner le projet myJavaGitProjects");
		System.out.println("2.2/ Menu window->Perspective->Open Perspective->Other...->Git->Open");
		System.out.println("2.3/ Stage, Commit with message, Commit and Push");

		System.out.println("eclipse-workspace/myJavaGitProjects");
		
		readAllJpg("F:/Vacances Laurent et Brigitte USA/USA/");
	}

	
    public static void readAllJpg(String path) throws Exception {
        File dossier = new File(path);

        File[] fichiers = dossier.listFiles();

        if (fichiers != null) {
            Arrays.sort(fichiers, (a, b) -> a.getName().compareToIgnoreCase(b.getName()));
            for (File f : fichiers) {
                if (f.isFile()) {
                	String fileName = f.getName();
                	if ((!fileName.startsWith(".")) && (fileName.endsWith(".jpeg") || fileName.endsWith(".JPG"))) {
                		readJpg(path + "/" + fileName);
                	}
                    System.out.println("fileName : " + fileName);
                }
            }
        }
    }
	
    public static void readJpg(String fileName) throws Exception {
        File file = new File(fileName);
        byte[] jpeg = readAll(file);

        parseJpeg(jpeg);
    }


    static byte[] readAll(File f) throws IOException {
        try (FileInputStream fis = new FileInputStream(f)) {
            return fis.readAllBytes();
        }
    }

    static void parseJpeg(byte[] jpeg) throws Exception {        int pos = 2; // skip SOI (FFD8)
        while (pos < jpeg.length) {
            if ((jpeg[pos] & 0xFF) != 0xFF) break;

            int marker = jpeg[pos + 1] & 0xFF;
            int size = ((jpeg[pos + 2] & 0xFF) << 8) | (jpeg[pos + 3] & 0xFF);

            System.out.println("Marker: 0x" + Integer.toHexString(marker) + " Size=" + size);

            switch (marker) {

                case 0xE0: // APP0 (JFIF)
                    parseAPP0(jpeg, pos + 4, size - 2);
                    break;

                case 0xE1: // APP1 (EXIF)
                    parseAPP1(jpeg, pos + 4, size - 2);
                    break;

                default:
                    // autres segments ignorés pour l'instant
                    break;
            }

            pos += size + 2;
        }
    }

    // -----------------------------
    // APP0 (JFIF)
    // -----------------------------
    static void parseAPP0(byte[] jpeg, int offset, int length) throws Exception {
        String id = new String(jpeg, offset, 5, "ASCII");

        System.out.println("=== APP0 (JFIF) ===");

        if (!id.equals("JFIF\0")) {
            System.out.println("APP0 trouvé mais pas JFIF");
            return;
        }

        int versionMajor = jpeg[offset + 5] & 0xFF;
        int versionMinor = jpeg[offset + 6] & 0xFF;

        int units = jpeg[offset + 7] & 0xFF;

        int xDensity = ((jpeg[offset + 8] & 0xFF) << 8) | (jpeg[offset + 9] & 0xFF);
        int yDensity = ((jpeg[offset + 10] & 0xFF) << 8) | (jpeg[offset + 11] & 0xFF);

        int thumbWidth = jpeg[offset + 12] & 0xFF;
        int thumbHeight = jpeg[offset + 13] & 0xFF;

        System.out.println("Version : " + versionMajor + "." + versionMinor);
        System.out.println("Units : " + units);
        System.out.println("X Density : " + xDensity);
        System.out.println("Y Density : " + yDensity);
        System.out.println("Thumbnail : " + thumbWidth + "x" + thumbHeight);
    }

    // -----------------------------
    // APP1 (EXIF)
    // -----------------------------
    static void parseAPP1(byte[] jpeg, int offset, int length) throws Exception {
        System.out.println("=== APP1 (EXIF) ===");

        String header = new String(jpeg, offset, 6, "ASCII");
        if (!header.equals("Exif\0\0")) {
            System.out.println("APP1 trouvé mais pas EXIF");
            return;
        }

        int tiffStart = offset + 6;

        boolean littleEndian = jpeg[tiffStart] == 'I';
        ByteOrder order = littleEndian ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN;

        ByteBuffer buf = ByteBuffer.wrap(jpeg).order(order);

        int ifd0Offset = buf.getInt(tiffStart + 4);
        int ifd0 = tiffStart + ifd0Offset;

        // Lire IFD0
        int gpsOffset = parseIFD0ForGPS(jpeg, ifd0, tiffStart, order);

        if (gpsOffset != -1) {
            parseGPSIFD(jpeg, gpsOffset, tiffStart, order);
        }        

        
        Map<Integer, String> tags = parseIFD(jpeg, tiffStart + ifd0Offset, tiffStart, order);

        for (Map.Entry<Integer, String> e : tags.entrySet()) {
            System.out.println("Tag 0x" + Integer.toHexString(e.getKey()) + " = " + e.getValue());
        }
    }

    // -----------------------------
    // IFD parser (EXIF)
    // -----------------------------
    static Map<Integer, String> parseIFD(byte[] data, int ifdOffset, int base, ByteOrder order) {
        Map<Integer, String> tags = new HashMap<>();

        ByteBuffer buf = ByteBuffer.wrap(data).order(order);
        int count = buf.getShort(ifdOffset) & 0xFFFF;

        int entry = ifdOffset + 2;

        for (int i = 0; i < count; i++) {
            int tag = buf.getShort(entry) & 0xFFFF;
            int countValues = buf.getInt(entry + 4);
            int valueOffset = buf.getInt(entry + 8);

            switch (tag) {
                case 0x0112: // Orientation
                    tags.put(tag, "Orientation = " + valueOffset);
                    break;

                case 0x0132: // DateTime
                	String dateTime = readAscii(data, base + valueOffset, countValues);
                	String timestamp = convertExifDate(dateTime);
                    tags.put(tag, timestamp);
                    break;

                case 0x010F: // Make
                case 0x0110: // Model
                    tags.put(tag, readAscii(data, base + valueOffset, countValues));
                    break;

                default:
                    // tu peux ajouter d'autres tags ici
                    break;
            }

            entry += 12;
        }

        return tags;
    }

    static String readAscii(byte[] data, int offset, int length) {
        try {
            return new String(data, offset, length, "ASCII").trim();
        } catch (Exception e) {
            return "";
        }
    }
    
    // -----------------------------
    // IFD0 : trouver le pointeur GPS (tag 0x8825)
    // -----------------------------
    static int parseIFD0ForGPS(byte[] data, int ifdOffset, int base, ByteOrder order) {
        ByteBuffer buf = ByteBuffer.wrap(data).order(order);
        int count = buf.getShort(ifdOffset) & 0xFFFF;

        int entry = ifdOffset + 2;

        for (int i = 0; i < count; i++) {
            int tag = buf.getShort(entry) & 0xFFFF;

            if (tag == 0x8825) { // GPSInfoIFDPointer
                int gpsOffset = buf.getInt(entry + 8);
                return base + gpsOffset;
            }

            entry += 12;
        }

        return -1;
    }

    // -----------------------------
    // IFD GPS
    // -----------------------------
    static void parseGPSIFD(byte[] data, int ifdOffset, int base, ByteOrder order) {
        ByteBuffer buf = ByteBuffer.wrap(data).order(order);
        int count = buf.getShort(ifdOffset) & 0xFFFF;

        int entry = ifdOffset + 2;

        String latRef = null;
        String lonRef = null;
        double[] lat = null;
        double[] lon = null;

        for (int i = 0; i < count; i++) {
            int tag = buf.getShort(entry) & 0xFFFF;
            int type = buf.getShort(entry + 2) & 0xFFFF;
            int countValues = buf.getInt(entry + 4);
            int valueOffset = buf.getInt(entry + 8);

            int realOffset = (type == 2 && countValues > 4)
                    ? base + valueOffset
                    : entry + 8;

            switch (tag) {
                case 0x0001: // LatitudeRef
                    latRef = new String(data, realOffset, countValues).trim();
                    break;

                case 0x0002: // Latitude
                    lat = readRationalTriplet(data, base + valueOffset, order);
                    break;

                case 0x0003: // LongitudeRef
                    lonRef = new String(data, realOffset, countValues).trim();
                    break;

                case 0x0004: // Longitude
                    lon = readRationalTriplet(data, base + valueOffset, order);
                    break;
            }

            entry += 12;
        }

        if (lat != null && lon != null) {
            double latitude = convertToDegrees(lat);
            double longitude = convertToDegrees(lon);

            if ("S".equals(latRef)) latitude = -latitude;
            if ("W".equals(lonRef)) longitude = -longitude;

            System.out.println("GPS Latitude : " + latitude);
            System.out.println("GPS Longitude : " + longitude);
        }
    }

    // -----------------------------
    // Lecture de 3 rationnels (degrés, minutes, secondes)
    // -----------------------------
    static double[] readRationalTriplet(byte[] data, int offset, ByteOrder order) {
        ByteBuffer buf = ByteBuffer.wrap(data).order(order);
        double[] result = new double[3];

        for (int i = 0; i < 3; i++) {
            long num = buf.getInt(offset + i * 8) & 0xFFFFFFFFL;
            long den = buf.getInt(offset + i * 8 + 4) & 0xFFFFFFFFL;
            result[i] = (double) num / (double) den;
        }

        return result;
    }

    static double convertToDegrees(double[] dms) {
        return dms[0] + dms[1] / 60.0 + dms[2] / 3600.0;
    }
    
    static String convertExifDate(String exifDate) {
        if (exifDate == null || exifDate.length() < 19) return "";

        // exifDate = "YYYY:MM:DD HH:MM:SS"
        String yyyy = exifDate.substring(0, 4);
        String mm   = exifDate.substring(5, 7);
        String dd   = exifDate.substring(8, 10);
        String hh   = exifDate.substring(11, 13);
        String min  = exifDate.substring(14, 16);
        String ss   = exifDate.substring(17, 19);

        return yyyy + mm + dd + hh + min + ss;
    }
}

