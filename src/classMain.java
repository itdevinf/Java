/* For exif reader */
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
// MOV
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;


public class classMain {
	final static int LOG_LEVEL_DEBUG		= 1;
	final static int LOG_LEVEL_INFO			= 2;
	final static int LOG_LEVEL_RESULT		= 3;
	final static int LOG_LEVEL_WARNING		= 4;
	final static int LOG_LEVEL_ERROR		= 5;
	final static int LOG_LEVEL_VALUE		= LOG_LEVEL_RESULT;
	
	final static int ACTION_TRACE					= 00;
	final static int ACTION_SIMPLE_RENAME_FILE		= 01;
	final static int ACTION_COMPLEX_RENAME_FILE		= 02;
	final static int ACTION_CHANGE_DIR_FILE			= 03;
	final static int ACTION_REMOVE_EMPTY_DIR		= 04;
	
	final static String LOG_LABEL_DEBUG		= "[DEBUG  ]";
	final static String LOG_LABEL_INFO		= "[INFO   ]";
	final static String LOG_LABEL_WARNING	= "[WARNING]";
	final static String LOG_LABEL_INCONNU	= "[INCONNU]";
	final static String LOG_LABEL_ERROR		= "[ERROR  ]";
	final static String LOG_LABEL_RESULT	= "[RESULT ]";
	
	final static int TAG_LATITUDE_REF	= 0x0001;
	final static int TAG_LATITUDE		= 0x0002;
	final static int TAG_LONGITUDE_REF	= 0x0003;
	final static int TAG_LONGITUDE		= 0x0004;
	final static int TAG_GPS_LATITUDE	= 0xF002; // Added TAG not in norm
	final static int TAG_GPS_LONGITUDE	= 0xF004; // Added TAG not in norm

	final static int TAG_VERSION_MAJOR	= 0xFF01; // Added TAG not in norm
	final static int TAG_VERSION_MINOR	= 0xFF02; // Added TAG not in norm
	final static int TAG_UNITS			= 0xFF03; // Added TAG not in norm
	final static int TAG_X_DENSITY		= 0xFF04; // Added TAG not in norm
	final static int TAG_Y_DENSITY		= 0xFF05; // Added TAG not in norm
	final static int TAG_THUMB_WIDTH	= 0xFF06; // Added TAG not in norm
	final static int TAG_THUMB_HEIGHT	= 0xFF07; // Added TAG not in norm


	final static int SEG_X01	= 0xDB;
	final static int SEG_X02	= 0xC0;
	final static int SEG_X03	= 0xC4;
	final static int SEG_X04	= 0xDA;
	final static int SEG_X05	= 0xDD;
	final static int SEG_X06	= 0xE2;
	final static int SEG_X07	= 0xE4;
	final static int SEG_X08	= 0xE5;
	
	final static int TAG_RIEN			= 0x0000;
	final static int TAG_X0				= 0x0100;
	final static int TAG_X1				= 0x0101;
	final static int TAG_ORIENTATION	= 0x0112;
	final static int TAG_X2				= 0x011A;
	final static int TAG_X3				= 0x011B;
	final static int TAG_X4				= 0x0128;
	final static int TAG_X5				= 0x0131;
	final static int TAG_TIMESTAMP		= 0x0132;
	final static int TAG_MAKE			= 0x010F;
	final static int TAG_MODEL			= 0x0110;
	final static int TAG_X6				= 0x013C;
	final static int TAG_X7				= 0x0213;
	final static int TAG_X8				= 0x8825;
	final static int TAG_X9				= 0x8769;
	final static int TAG_MODIF_TIMESTAMP= 0xF132; // Added TAG not in norm

	static int nbFiles = 0;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		log(LOG_LEVEL_DEBUG, "classMain");
		log(LOG_LEVEL_DEBUG, "1/ Créer un token github");
		log(LOG_LEVEL_DEBUG, "1.1/ Se connecter à github>Profil->Setup");
		log(LOG_LEVEL_DEBUG, "1.2/ Colonne de gauche->Personnal access tokens->Tokens(classic)");
		log(LOG_LEVEL_DEBUG, "1.3/ Colonne de droite->Generate new token(classic)");
		log(LOG_LEVEL_DEBUG, "1.4/ Note:TestToken");
		log(LOG_LEVEL_DEBUG, "1.5/ Expiration:No expiration");
		log(LOG_LEVEL_DEBUG, "1.6/ Sélect scopes:repo");
		log(LOG_LEVEL_DEBUG, "1.7/ Generate token");
		log(LOG_LEVEL_DEBUG, "1.7/ Copier le token généré");

		log(LOG_LEVEL_DEBUG, "2/ Créer un dépot local");
		log(LOG_LEVEL_DEBUG, "2.1/ Sélectionner le projet myJavaGitProjects");
		log(LOG_LEVEL_DEBUG, "2.2/ Menu window->Perspective->Open Perspective->Other...->Git->Open");
		log(LOG_LEVEL_DEBUG, "2.3/ Stage, Commit with message, Commit and Push");

		log(LOG_LEVEL_DEBUG, "eclipse-workspace/myJavaGitProjects");
		
		int prefixReplaceByTag = TAG_TIMESTAMP;
		String actions = "" + ACTION_TRACE + ":";
		String path = "";
		String prefixToBeReplaced = "";
		String prefixReplaceBy = "";
		String who = "";
		
		// Laurent
		nbFiles = 0;
		path = "E:\\D\\Images\\Mes_images\\ImagesTriees\\20aa\\202a\\2026\\T2\\20260529_VoyageAuxUSA\\20260529_0602_NouvelleOrléans";
		prefixToBeReplaced = "";
		prefixReplaceBy = "";
		who = "_l";
		actions = "" + ACTION_COMPLEX_RENAME_FILE + ":;" + ACTION_CHANGE_DIR_FILE + ":..;" + ACTION_REMOVE_EMPTY_DIR + ":Laurent";
//		readAllphotoOrFilm(path,  prefixToBeReplaced, prefixReplaceBy, prefixReplaceByTag, who, ".jpg,.mp4", actions);		
		log(LOG_LABEL_ERROR, "NOT AN ERROR JUST TO SEE End nbFiles : " + nbFiles);
//		System.exit(0);
		
		nbFiles = 0;
		path = "E:\\D\\Images\\Mes_images\\ImagesTriees\\20aa\\202a\\2026\\T2\\20260529_VoyageAuxUSA\\20260529_0602_NouvelleOrléans";
		prefixReplaceByTag = TAG_RIEN;
		who = "_lau";
		actions = "" + ACTION_SIMPLE_RENAME_FILE + ":_l,_lau";
		readAllphotoOrFilm(path,  prefixToBeReplaced, prefixReplaceBy, prefixReplaceByTag, who, ".jpg,.mp4", actions);		

		log(LOG_LABEL_ERROR, "NOT AN ERROR JUST TO SEE End nbFiles : " + nbFiles);
		System.exit(0);
		
		// Brigitte
		nbFiles = 0;
		path = "E:\\D\\Images\\Mes_images\\ImagesTriees\\20aa\\202a\\2026\\T2\\20260529_VoyageAuxUSA\\USA";
		prefixToBeReplaced = "IMG_";
		prefixReplaceByTag = TAG_TIMESTAMP;
		prefixReplaceBy = "";
		who = "_bri";
		actions = "" +  + ACTION_COMPLEX_RENAME_FILE;
		readAllphotoOrFilm(path, prefixToBeReplaced, prefixReplaceBy, prefixReplaceByTag, who, ".jpeg,.JPG,.MOV", actions);
		prefixToBeReplaced = "";
		readAllphotoOrFilm(path,  prefixToBeReplaced, prefixReplaceBy, prefixReplaceByTag, who, ".JPG", actions);

		
		log(LOG_LABEL_ERROR, "NOT AN ERROR JUST TO SEE End nbFiles : " + nbFiles);


	}

	
    public static void readAllphotoOrFilm(String path, String prefixToBeReplaced, String prefixReplaceBy, int prefixReplaceByTag, String who, String extensions, String actions) throws Exception {
    	File dossier = new File(path);

        File[] fichiers = dossier.listFiles();

        if (fichiers != null) {
            Arrays.sort(fichiers, (a, b) -> a.getName().compareToIgnoreCase(b.getName()));
            for (File f : fichiers) {
                if (f.isFile()) {
                	nbFiles++;
                	String fileName = f.getName();
                    manageFiles(path, fileName, prefixToBeReplaced, prefixReplaceBy, prefixReplaceByTag, who, extensions, actions);
                }
                else if (f.isDirectory()) {
                	String dirName = f.getName();
                	log(LOG_LEVEL_DEBUG, "path/fileName : " + path + File.separator + dirName);
            		readAllphotoOrFilm(path + File.separator + dirName, prefixToBeReplaced, prefixReplaceBy, prefixReplaceByTag, who, extensions, actions);
                    manageDir(path + File.separator + dirName, actions);
                }
            }
        }
        else {
        	log(LOG_LEVEL_WARNING, "path : \"" + path + "\" does not exist.");
        }
    }

    private static void manageFiles(String path, String fileName, String prefixToBeReplaced, String prefixReplaceBy, int prefixReplaceByTag, String who, String extensions, String actions) throws Exception {
    	String prefixReplaceByValue = "";
    	Map<Integer, String> tags = null;
    	log(LOG_LEVEL_DEBUG, "path/fileName : " + path + File.separator + fileName);
        StringTokenizer tokenizerExts = new StringTokenizer(extensions, ",");
        String extension = null;
        while (tokenizerExts.hasMoreElements()) {
        	String tokenizerExtension = (String) tokenizerExts.nextElement();
        	log(LOG_LEVEL_DEBUG, "tokenizerExtension : " + tokenizerExtension);
        	if (fileName.endsWith(tokenizerExtension)) {
            	extension = tokenizerExtension;
            	break;
            }
        }
        if ((fileName.startsWith(prefixToBeReplaced)) && (extension != null)) {
    		if ((extension.equals(".jpeg")) || (extension.equals(".JPG")) || (extension.equals(".jpg"))) {
    			tags = readJpg(path, fileName);
    		}
    		else if (extension.equals(".MOV")) {
    			tags = readMov(path, fileName);
    		}
    		else if (extension.equals(".mp4")) {
    			tags = null;
    		}
    		else {
    			log(LOG_LEVEL_WARNING, "Extention non traitée : " + extension);
    		}
    		if ((prefixReplaceBy.equals("")) && (prefixReplaceByTag != TAG_RIEN)) {
    			String prefixReplaceByTagValue = "";
    			if (tags != null) {
    				prefixReplaceByTagValue = tags.get(prefixReplaceByTag);
    			}
    			if (prefixReplaceByTagValue != null) {
    				if (prefixToBeReplaced.equals("")) {
    					prefixReplaceByValue = prefixReplaceByTagValue + who;                					
    				}
    				else {
    					prefixReplaceByValue = prefixReplaceByTagValue + who + "_" + prefixToBeReplaced;
    				}
    			}
    			else {
    				prefixReplaceByValue = "";
    				prefixReplaceByTagValue = "";
    			}
    		}
    	    manageFilesActions(path, fileName, prefixToBeReplaced, prefixReplaceBy, prefixReplaceByTag, prefixReplaceByValue, who, extension, actions);	
    	}
    }
    
    private static void manageFilesActions(String path, String fileName, String prefixToBeReplaced, String prefixReplaceBy, int prefixReplaceByTag, String prefixReplaceByValue, String who, String extension, String actions) throws Exception {
    	boolean continuAction=true;
    	String newPathFileName = "";
    	StringTokenizer tokenizerActs = new StringTokenizer(actions, ";");
        while (tokenizerActs.hasMoreElements() && continuAction) {
        	String tokenizerAction = (String) tokenizerActs.nextElement();
        	log(LOG_LEVEL_DEBUG, "tokenizerAction : " + tokenizerAction);
        	int currentAction = ACTION_TRACE;
        	String currentStrAction = ""+currentAction;
        	String[] currentParams =  null;
        	int paramSepPos = tokenizerAction.indexOf(":");
        	if (paramSepPos < 1) {
            	log(LOG_LEVEL_ERROR, "Syntax error current action : " + tokenizerAction + " should contains \":\" and after its parameters");
            	System.exit(-2);                        		
        	}
        	else {
        		currentStrAction = tokenizerAction.substring(0, paramSepPos);
        	}
        	try {
        		currentAction = Integer.parseInt(currentStrAction);
                StringTokenizer stCurrentParams = new StringTokenizer(tokenizerAction.substring(paramSepPos+1), ",");
                ArrayList<String> list = new ArrayList<>();
                while (stCurrentParams.hasMoreTokens()) {
                    list.add(stCurrentParams.nextToken());
                }
                currentParams = list.toArray(new String[0]);
        	}
        	catch(Exception e) {
            	log(LOG_LEVEL_ERROR, "Syntax error current action : " + tokenizerAction + " is not an action number or not good parameters.");
            	System.exit(-2);
        	}
        	switch(currentAction) {
        		case ACTION_TRACE:
        			break;
        		case ACTION_SIMPLE_RENAME_FILE:
                    if (fileName.contains(who + ".") || fileName.contains(who + "_")) {
                    	log(LOG_LEVEL_WARNING, "Déjà renommé path/fileName : " + path + File.separator + fileName);
                    	continuAction = false;
                    }
                    else {
	                    newPathFileName = GetNewFileSimpleName(path, fileName, currentParams);
	                    if (newPathFileName != null) {
	                    	log(LOG_LEVEL_RESULT, "new path/fileName : " + path + File.separator + newPathFileName + "<-" + fileName);
	                    	renameFile(path + File.separator + fileName, path + File.separator + newPathFileName);
	                    	fileName = newPathFileName;
	                    }
                    }
                    break;
        		case ACTION_COMPLEX_RENAME_FILE:
                    if (fileName.contains(who + ".") || fileName.contains(who + "_")) {
                    	log(LOG_LEVEL_WARNING, "Déjà renommé path/fileName : " + path + File.separator + fileName);
                    	continuAction = false;
                    }
                    else {
	                    newPathFileName = GetNewFileComplexName(path, fileName, prefixToBeReplaced, prefixReplaceByValue, who, extension);
	                    if (newPathFileName != null) {
	                    	log(LOG_LEVEL_RESULT, "new path/fileName : " + path + File.separator + newPathFileName + "<-" + fileName);
	                    	renameFile(path + File.separator + fileName, path + File.separator + newPathFileName);
	                    	fileName = newPathFileName;
	                    }
                    }
                    break;
        		case ACTION_CHANGE_DIR_FILE:
        			String newPath = path + File.separator + currentParams[0];
        	    	File dossier = new File(newPath);
        			if (!dossier.isDirectory()) {
        				log(LOG_LEVEL_ERROR, "Syntaxe error, file action : " + tokenizerAction + " its parameter : " + newPath + " is not a directory, check param : " + tokenizerAction);
        			}
    				moveDir(path + File.separator + fileName, newPath + File.separator + fileName);
        			break;
        		default:
        			// Si action sur DIR ne rien faire
        			if (!(currentAction == ACTION_REMOVE_EMPTY_DIR)) {
        				log(LOG_LEVEL_ERROR, "Syntaxe error, file action : " + tokenizerAction + " is not known");
        				System.exit(-2);
        			}
                	break;
        	}
        } 
    }

	private static void renameFile(String ancienNom, String nouveauNom) {
		File ancien = new File(ancienNom);
		File nouveau = new File(nouveauNom);
		boolean ok = false;
		ok = ancien.renameTo(nouveau);
		if (ok) {
			log(LOG_LEVEL_DEBUG, "Fichier renommé : " + ancien + "->" + nouveau);
		} else {
			log(LOG_LEVEL_DEBUG, "Échec du renommage : " + ancien + "->" + nouveau);
		}
	}

    public static void moveDir(String source, String destination) {
        Path src = Paths.get(source);
        Path dst = Paths.get(destination);
        try {
        	File file = new File(destination);
        	if (file.exists()) {
                log(LOG_LEVEL_ERROR, "Echec le fichier destination existe déjà : \n" + source + " ->\n" + destination);        		
                System.exit(-6);
        	}
            Files.move(src, dst, StandardCopyOption.REPLACE_EXISTING);
            log(LOG_LEVEL_INFO, "Fichier déplacé avec succès : \n" + source + "->\n" + destination);
        } catch (Exception e) {
            log(LOG_LEVEL_ERROR, "Echec du déplacement de fichier : \n" + source + " ->\n" + destination);
            e.printStackTrace();
            System.exit(-8);
        }
    }

    private static void manageDir(String path, String actions) throws Exception {
    	manageDirActions(path, actions);
    }

    private static void manageDirActions(String path, String actions) throws Exception {
    	StringTokenizer tokenizerActs = new StringTokenizer(actions, ";");
        while (tokenizerActs.hasMoreElements()) {
        	String tokenizerAction = (String) tokenizerActs.nextElement();
        	log(LOG_LEVEL_DEBUG, "tokenizerAction : " + tokenizerAction);
        	int currentAction = ACTION_TRACE;
        	String currentStrAction = ""+currentAction;
        	String currentParam =  "";
        	int paramSepPos = tokenizerAction.indexOf(":");
        	if (paramSepPos < 1) {
            	log(LOG_LEVEL_ERROR, "Syntax error current action : " + tokenizerAction + " should contains \":\" and after its parameters");
            	System.exit(-2);                        		
        	}
        	else {
        		currentStrAction = tokenizerAction.substring(0, paramSepPos);
        	}
        	try {
        		currentAction = Integer.parseInt(currentStrAction);
        		currentParam  = tokenizerAction.substring(paramSepPos+1);
        	}
        	catch(Exception e) {
            	log(LOG_LEVEL_ERROR, "Syntax error current action : " + tokenizerAction + " is not an action number.");
            	System.exit(-2);
        	}
        	switch(currentAction) {
        		case ACTION_TRACE:
        			break;
        		case ACTION_REMOVE_EMPTY_DIR:
        			deleteDir(path, currentParam);
        			break;
        		default:
        			if (!((currentAction == ACTION_SIMPLE_RENAME_FILE) || (currentAction == ACTION_COMPLEX_RENAME_FILE) || (currentAction == ACTION_CHANGE_DIR_FILE))) {
        				log(LOG_LEVEL_ERROR, "Syntaxe error, dir action : " + tokenizerAction + " is not known");
        				System.exit(-2);
        				break;
        			}
        	}
        }
    }

    public static void deleteDir(String path, String pathName) throws Exception {
    	if (!path.endsWith(File.separator + pathName)) {
        	log(LOG_LEVEL_WARNING, "Could not delete pathName : " + pathName + " current path is : " + path + ".");
        	return;
    	}
        Path dir = Paths.get(path);

        if (isDirectoryEmpty(dir)) {
            Files.delete(dir);
        	log(LOG_LEVEL_INFO, "Directory : " + path + " deleted");
        } else {
        	log(LOG_LEVEL_ERROR, "Directory : " + path + " is not empty !");
        }
    }

    private static boolean isDirectoryEmpty(Path dir) throws Exception {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            return !stream.iterator().hasNext();
        }
    }
  
    public static Map<Integer, String> readJpg(String path, String fileName) throws Exception {
        File file = new File(path + File.separator + fileName);
        byte[] jpeg = readAll(file);

        Map<Integer, String> tags = parseJpeg(jpeg);
        return tags;
    }


    static byte[] readAll(File f) throws IOException {
        try (FileInputStream fis = new FileInputStream(f)) {
            return fis.readAllBytes();
        }
    }

    static Map<Integer, String> parseJpeg(byte[] jpeg) throws Exception {
    	int pos = 2; // skip SOI (FFD8)
    	Map<Integer, String> tags = new HashMap<>();
    	while (pos < jpeg.length) {
            if ((jpeg[pos] & 0xFF) != 0xFF) break;

            int marker = jpeg[pos + 1] & 0xFF;
            int size = ((jpeg[pos + 2] & 0xFF) << 8) | (jpeg[pos + 3] & 0xFF);

            log(LOG_LEVEL_DEBUG, "Marker: 0x" + Integer.toHexString(marker) + " Size=" + size);

            switch (marker) {

                case 0xE0: // APP0 (JFIF)
                	tags = parseAPP0(tags, jpeg, pos + 4, size - 2);
                    break;

                case 0xE1: // APP1 (EXIF)
                	tags = parseAPP1(tags, jpeg, pos + 4, size - 2);
                    break;

                case SEG_X01:
                case SEG_X02:
                case SEG_X03:
                case SEG_X04:
                case SEG_X05:
                case SEG_X06:
                case SEG_X07:
                case SEG_X08:
                	log(LOG_LEVEL_INFO, "AUTRE SEGMENT to be done : 0x" + Integer.toHexString(marker));
                    break;

                default:
                    // autres segments ignorés pour l'instant
                	log(LOG_LEVEL_ERROR, "AUTRE SEGMENT 0x NON TRAITES Segment : 0x" + Integer.toHexString(marker));
                    break;
            }

            pos += size + 2;
        }
        return tags;
    }

    // -----------------------------
    // APP0 (JFIF)
    // -----------------------------
    static Map<Integer, String> parseAPP0(Map<Integer, String> tags, byte[] jpeg, int offset, int length) throws Exception {
        String id = new String(jpeg, offset, 5, "ASCII");

        log(LOG_LEVEL_DEBUG, "=== APP0 (JFIF) ===");

        if (!id.equals("JFIF\0")) {
        	log(LOG_LEVEL_DEBUG, "APP0 trouvé mais pas JFIF");
            return tags;
        }

        int versionMajor = jpeg[offset + 5] & 0xFF;
        tags.put(TAG_VERSION_MAJOR, "" + versionMajor);
        int versionMinor = jpeg[offset + 6] & 0xFF;
        tags.put(TAG_VERSION_MINOR, "" + versionMinor);

        int units = jpeg[offset + 7] & 0xFF;
        tags.put(TAG_UNITS, "" + units);

        int xDensity = ((jpeg[offset + 8] & 0xFF) << 8) | (jpeg[offset + 9] & 0xFF);
        tags.put(TAG_X_DENSITY, "" + xDensity);
        int yDensity = ((jpeg[offset + 10] & 0xFF) << 8) | (jpeg[offset + 11] & 0xFF);
        tags.put(TAG_Y_DENSITY, "" + yDensity);

        int thumbWidth = jpeg[offset + 12] & 0xFF;
        tags.put(TAG_THUMB_WIDTH, "" + thumbWidth);
        int thumbHeight = jpeg[offset + 13] & 0xFF;
        tags.put(TAG_THUMB_HEIGHT, "" + thumbHeight);

        log(LOG_LEVEL_DEBUG, "Version : " + versionMajor + "." + versionMinor);
        log(LOG_LEVEL_DEBUG, "Units : " + units);
        log(LOG_LEVEL_DEBUG, "X Density : " + xDensity);
        log(LOG_LEVEL_DEBUG, "Y Density : " + yDensity);
        log(LOG_LEVEL_DEBUG, "Thumbnail : " + thumbWidth + "x" + thumbHeight);
        
        return tags;
    }

    // -----------------------------
    // APP1 (EXIF)
    // -----------------------------
    static Map<Integer, String> parseAPP1(Map<Integer, String> tags, byte[] jpeg, int offset, int length) throws Exception {
    	log(LOG_LEVEL_DEBUG, "=== APP1 (EXIF) ===");

        String header = new String(jpeg, offset, 6, "ASCII");
        if (!header.equals("Exif\0\0")) {
        	log(LOG_LEVEL_DEBUG, "APP1 trouvé mais pas EXIF");
            return tags;
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
        	tags = parseGPSIFD(tags, jpeg, gpsOffset, tiffStart, order);
        }        

        
        tags = parseIFD(tags, jpeg, tiffStart + ifd0Offset, tiffStart, order);

        for (Map.Entry<Integer, String> e : tags.entrySet()) {
        	log(LOG_LEVEL_INFO, "Tag 0x" + Integer.toHexString(e.getKey()) + " = " + e.getValue());
        }
        return tags;
    }

    // -----------------------------
    // IFD parser (EXIF)
    // -----------------------------
    static Map<Integer, String> parseIFD(Map<Integer, String> tags, byte[] data, int ifdOffset, int base, ByteOrder order) {
        ByteBuffer buf = ByteBuffer.wrap(data).order(order);
        int count = buf.getShort(ifdOffset) & 0xFFFF;

        int entry = ifdOffset + 2;

        for (int i = 0; i < count; i++) {
            int tag = buf.getShort(entry) & 0xFFFF;
            int countValues = buf.getInt(entry + 4);
            int valueOffset = buf.getInt(entry + 8);

            switch (tag) {
                case TAG_ORIENTATION: // Orientation
                    tags.put(tag, "Orientation = " + valueOffset);
                    break;

                case TAG_TIMESTAMP: // DateTime
                	String dateTime = readAscii(data, base + valueOffset, countValues);
                	String timestamp = convertExifDate(dateTime);
                    tags.put(tag, timestamp);
                    break;

                case TAG_MAKE: // Make
                    tags.put(tag, "Make = " + readAscii(data, base + valueOffset, countValues));
                    break;
                case TAG_MODEL: // Model
                    tags.put(tag, "Model = " + readAscii(data, base + valueOffset, countValues));
                    break;

                case TAG_X0:
                case TAG_X1:
                case TAG_X2:
                case TAG_X3:
                case TAG_X4:
                case TAG_X5:
                case TAG_X6:
                case TAG_X7:
                case TAG_X8:
                case TAG_X9:
                    tags.put(tag, "Inconnu : " + tag);
                    break;
                    
                default:
                    // tu peux ajouter d'autres tags ici
                	log(LOG_LEVEL_ERROR, "AUTRE TAG 0x NON TRAITES Tag : 0x" + Integer.toHexString(tag));
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
    static Map<Integer, String> parseGPSIFD(Map<Integer, String> tags, byte[] data, int ifdOffset, int base, ByteOrder order) {
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
                case TAG_LATITUDE_REF: // LatitudeRef
                    latRef = new String(data, realOffset, countValues).trim();
                    tags.put(TAG_LATITUDE_REF, latRef);
                    break;

                case TAG_LATITUDE: // Latitude
                    lat = readRationalTriplet(data, base + valueOffset, order);
                    tags.put(TAG_LATITUDE, "" + lat);
                    break;

                case TAG_LONGITUDE_REF: // LongitudeRef
                    lonRef = new String(data, realOffset, countValues).trim();
                    tags.put(TAG_LONGITUDE_REF, lonRef);
                    break;

                case TAG_LONGITUDE: // Longitude
                    lon = readRationalTriplet(data, base + valueOffset, order);
                    tags.put(TAG_LONGITUDE, "" + lon);
                   break;
            }

            entry += 12;
        }

        if (lat != null && lon != null) {
            double latitude = convertToDegrees(lat);
            double longitude = convertToDegrees(lon);

            if ("S".equals(latRef)) latitude = -latitude;
            if ("W".equals(lonRef)) longitude = -longitude;

            log(LOG_LEVEL_INFO, "GPS Latitude : " + latitude);
            tags.put(TAG_GPS_LATITUDE, "" + latitude);
            log(LOG_LEVEL_INFO, "GPS Longitude : " + longitude);
            tags.put(TAG_GPS_LONGITUDE, "" + longitude);
        }
        return tags;
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

        return yyyy + mm + dd + "_" + hh + min + ss;
    }
    
    static String GetNewFileSimpleName(String path, String fileName, String[] currentParams) {
    	String newFileName = null;
    	File pathName = new File(path);
    	if (pathName.exists()) {
        	File pathFileName = new File(path + File.separator + fileName);
        	if (pathFileName.exists()) {
    			newFileName = fileName.replace(currentParams[0], currentParams[1]);
        		log(LOG_LEVEL_DEBUG, "new file name : " + newFileName);
        	}
        	else {
        		log(LOG_LEVEL_WARNING, "File name : " + pathFileName + " does not exist");
        	} 
    	}
    	else {
    		log(LOG_LEVEL_WARNING, "Path : " + path + " does not exist");
    	}
    	return newFileName;
    }
    
    static String GetNewFileComplexName(String path, String fileName, String prefixToBeReplaced, String prefixReplaceBy, String who, String extension) {
    	String newFileName = null;
    	File pathName = new File(path);
    	if (pathName.exists()) {
        	File pathFileName = new File(path + File.separator + fileName);
        	if (pathFileName.exists()) {
        		if (fileName.startsWith(prefixToBeReplaced)) {
    				log(LOG_LEVEL_RESULT, "fileName : " + fileName + " extension : " + extension);
    				if (fileName.endsWith(extension)) {
    					if (prefixToBeReplaced.equals("")) {
    						newFileName = fileName;
        					newFileName = newFileName.substring(0, newFileName.length() - extension.length()) + who + extension;
   					}
    					else {
    						newFileName = fileName.replace(prefixToBeReplaced, prefixReplaceBy);
        					newFileName = newFileName.substring(0, newFileName.length() - extension.length()) + extension;
   					}
        				log(LOG_LEVEL_DEBUG, "new file fame : " + newFileName);
        			}
        			else {
        				log(LOG_LEVEL_DEBUG, "file name does not end with extension : " + extension);
        			}
        		}
            	else {
            		log(LOG_LEVEL_DEBUG, "file name does not start with prefix : " + prefixToBeReplaced);
            	}
        	}
        	else {
        		log(LOG_LEVEL_WARNING, "File name : " + pathFileName + " does not exist");
        	} 
    	}
    	else {
    		log(LOG_LEVEL_WARNING, "Path : " + path + " does not exist");
    	}
    	return newFileName;
    }

    
/* MOV */
    public static Map<Integer, String> readMov(String path, String fileName) throws Exception {
        Map<Integer, String> tags = new HashMap<>();
        File file = new File(path + File.separator + fileName);

        try (FileInputStream fis = new FileInputStream(file)) {

            byte[] buffer = new byte[8];

            while (fis.read(buffer) == 8) {
                int atomSize = ByteBuffer.wrap(buffer, 0, 4).getInt();
                String atomType = new String(buffer, 4, 4);

                if ("mvhd".equals(atomType)) {
                	log(LOG_LEVEL_DEBUG, "Atom mvhd found");

                    // Skip version + flags (4 bytes)
                    fis.skip(4);

                    // Read creation time (4 bytes)
                    byte[] creationBytes = new byte[4];
                    fis.read(creationBytes);
                    long creationTime = uint32(creationBytes);

                    // Read modification time (4 bytes)
                    byte[] modificationBytes = new byte[4];
                    fis.read(modificationBytes);
                    long modificationTime = uint32(modificationBytes);

                    log(LOG_LEVEL_DEBUG, "Creation time (raw): " + creationTime);
                    log(LOG_LEVEL_DEBUG, "Modification time (raw): " + modificationTime);

                    log(LOG_LEVEL_DEBUG, "Creation date: " + quickTimeEpochToDate(creationTime));
                    log(LOG_LEVEL_DEBUG, "Modification date: " + quickTimeEpochToDate(modificationTime));

                    return tags;
                }
                else {
                	tags = parseMov(tags, file);
                }

                // Skip to next atom
                fis.skip(atomSize - 8);
            }
        }
        return tags;
    }

    private static String quickTimeEpochToDate(long seconds) {
        // QuickTime epoch starts in 1904
        long unixSeconds = seconds - 2082844800L;
        LocalDateTime dt = LocalDateTime.ofInstant(Instant.ofEpochSecond(unixSeconds), ZoneId.systemDefault());
        
        return String.format("%04d%02d%02d_%02d%02d%02d",
                dt.getYear(),
                dt.getMonthValue(),
                dt.getDayOfMonth(),
                dt.getHour(),
                dt.getMinute(),
                dt.getSecond()
        );
    }
 
	public static Map<Integer, String> parseMov(Map<Integer, String> tags, File file) throws IOException {
	    try (FileInputStream fis = new FileInputStream(file)) {
	
	        while (true) {
	            byte[] header = new byte[8];
	            if (fis.read(header) != 8) break;
	
	            int atomSize = byteToInt(header, 0);
	            String atomType = new String(header, 4, 4);
	
	            if (atomSize < 8) break;
	
	            if ("moov".equals(atomType)) {
	            	log(LOG_LEVEL_DEBUG, "Atome moov trouvé, taille = " + atomSize);
	                tags = parseMoov(tags, fis, atomSize - 8);
	                return tags;
	            }
	
	            // Skip other top-level atoms
	            fis.skip(atomSize - 8);
	        }
	    }
	    return tags;
	}

	private static Map<Integer, String> parseMoov(Map<Integer, String> tags, FileInputStream fis, int moovSize) throws IOException {
	    int bytesRead = 0;
	
	    while (bytesRead < moovSize) {
	        byte[] header = new byte[8];
	        if (fis.read(header) != 8) break;
	
	        int atomSize = byteToInt(header, 0);
	        String atomType = new String(header, 4, 4);
	
	        bytesRead += atomSize;
	
	        if ("mvhd".equals(atomType)) {
	            log(LOG_LEVEL_DEBUG, "Atome mvhd trouvé !");
	            tags = parseMvhd(tags, fis);
	            return tags;
	        }
	
	        // Skip other atoms inside moov
	        fis.skip(atomSize - 8);
	    }
	    return tags;
	}

	private static Map<Integer, String> parseMvhd(Map<Integer, String> tags, FileInputStream fis) throws IOException {
	    // Skip version + flags
	    fis.skip(4);
	
	    byte[] creation = new byte[4];
	    byte[] modification = new byte[4];
	
	    fis.read(creation);
	    fis.read(modification);
	
	    long creationTimeRaw = uint32(creation);
	    long modificationTimeRaw = uint32(modification);
	    String creationTime = quickTimeEpochToDate(creationTimeRaw);
	    String modificationTime = quickTimeEpochToDate(modificationTimeRaw);
	    log(LOG_LEVEL_DEBUG, "Creation date: " + creationTime);
	    tags.put(TAG_TIMESTAMP, "" + creationTime);
	    log(LOG_LEVEL_DEBUG, "Modification date: " + modificationTime);
	    tags.put(TAG_MODIF_TIMESTAMP, "" + modificationTime);
        
	    log(LOG_LEVEL_DEBUG, "Creation time raw = " + creationTimeRaw);
	    log(LOG_LEVEL_DEBUG, "Modification time raw = " + modificationTimeRaw);

	    return tags;
	}
	
	private static int byteToInt(byte[] bytes, int offset) {
	    return ((bytes[offset] & 0xFF) << 24) |
	           ((bytes[offset + 1] & 0xFF) << 16) |
	           ((bytes[offset + 2] & 0xFF) << 8) |
	           (bytes[offset + 3] & 0xFF);
	}
	
	private static long uint32(byte[] bytes) {
	    return byteToInt(bytes, 0) & 0xFFFFFFFFL;
	}
	
	private static void log(int level, String msg) {
		if (level >= LOG_LEVEL_VALUE ) {
			switch(level) {
				case LOG_LEVEL_DEBUG:
					log(LOG_LABEL_DEBUG, msg);
					break;
				case LOG_LEVEL_INFO:
					log(LOG_LABEL_INFO, msg);
					break;
				case LOG_LEVEL_WARNING:
					log(LOG_LABEL_WARNING, msg);
					break;
				case LOG_LEVEL_RESULT:
					log(LOG_LABEL_RESULT, msg);
					break;
				case LOG_LEVEL_ERROR:
					log(LOG_LABEL_ERROR, msg);
					break;
				default:
					log(LOG_LABEL_INCONNU, msg);
					break;
			}
		}
	}

	private static void log(String level, String msg) {
		System.out.println(level + " : " + msg);
	}
}

