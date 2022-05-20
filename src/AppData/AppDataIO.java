package AppData;
import java.io.File;
import java.io.IOException;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

/**
 * Export/Import a zip file this program appdata folder.
 * @author David Huynh
 */
public class AppDataIO {
    /**
     * Path where the appdata folder is stored.
     */
    private static String PATH_APPDATA = "./appdata/";
    /**
     * Name of the appdata zip file.
     */
    private static String FILENAME_APPDATA = "appdata.zip";

    /**
     * Test import and export.
     * @param args
     */
    public static void main(String[] args) {
        importAllFromZip("./");
        exportAllToZip("./");
    }

    /**
     * Saves a zip of appdata folder to the given path.
     * @param theExportPath The path to save appdata.zip at.
     */
    public static void exportAllToZip(String theExportPath) {
        try {
            new ZipFile(theExportPath + FILENAME_APPDATA).addFolder(new File(PATH_APPDATA));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write all info from given Zip file to appdata folder.
     * @param theZipPath The path of appdata.zip
     */
    public static void importAllFromZip(String theZipPath) {
        try {
            new ZipFile(theZipPath + FILENAME_APPDATA).extractAll("./");
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }
}

