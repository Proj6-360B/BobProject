import AppData.AppDataIO;
import Profile.ProfileManager;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class AppDataIOTest {
    @Test
    public void versionExportTest_CheckProfiles() throws ZipException, FileNotFoundException {
        var ad = new AppDataIO();
        ad.exportAllToZip("./test/appdata.zip");
        new ZipFile("./test/appdata.zip").extractAll("./test/");

        var exScanner = new Scanner(new File("./test/appdata/profiles/profiles.txt"));
        var oldScanner = new Scanner(new File("./appdata/profiles/profiles.txt"));
        while (exScanner.hasNextLine()) {
            if (!exScanner.nextLine().equals(oldScanner.nextLine())) {
                fail("profiles.txt do not match");
            }
        }
    }

    @Test
    public void versionImportTest_CheckProfiles() throws ZipException {
        var ad = new AppDataIO();
        ad.exportAllToZip("./test/appdata.zip"); //Save current

        //Check
        ad.importAllFromZip("./test/appdataImport.zip");
        var pm = new ProfileManager();
        assertEquals("test", pm.getProfileByUsername("test").getUsername());

        ad.importAllFromZip("./test/appdata.zip"); //Put back current
    }
}
