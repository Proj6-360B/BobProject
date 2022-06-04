package AppData;
import java.io.File;

import InstaDialogue.InstaDialogue;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import static InstaDialogue.InstaDialogue.PATH_FILECHOOSER_START;

/**
 * Export/Import a zip file this program appdata folder. <br>
 * Best is construct, then import/export, and let garbage collect. <br>
 * https://github.com/srikanth-lingala/zip4j
 * @author David Huynh
 * @author Damien Cruz
 */
public class AppDataIO {
    /**
     * Path where the appdata folder is stored.
     */
    private static final String PATH_APPDATA = "appdata";
    /**
     * File name to export as.
     */
    private static final String FILENAME_APPDATA = "appdata.zip";

    /**
     * Call to init, then import/export and let garbage collect.
     */
    public AppDataIO() {
        //
    }

    /**
     * Saves a zip of appdata folder to the given path.
     * @param theExportPath The path to save appdata.zip at.
     * @author David Huynh
     */
    public void exportAllToZip(String theExportPath) throws ZipException {
        new ZipFile(theExportPath + '\\' + FILENAME_APPDATA).addFolder(new File(PATH_APPDATA));
        System.out.println("Written to " + theExportPath + '\\' + FILENAME_APPDATA);
    }

    /**
     * Write all info from given Zip file to appdata folder.
     * @param theZipPath The path of appdata.zip
     * @author David Huynh
     */
    public void importAllFromZip(String theZipPath) throws ZipException, IllegalArgumentException {
        ZipFile zip = new ZipFile(theZipPath);
        if (zip.getFileHeader("appdata/") == null) { //Check legit / for appdata folder
            throw new IllegalArgumentException(theZipPath + " is not a appdata save file.");
        }
        zip.extractAll("./");
        System.out.println("Importing from " + theZipPath);
    }

    private String parseExtension() {
        return "";
    }

    /**
     * Prompt user to choose where, then write all info from given Zip file to appdata folder.
     * @author David Huynh
     */
    public void importAllFromZipByFileChooser() {
        JFileChooser fc = new JFileChooser(PATH_FILECHOOSER_START);
        fc.setDialogTitle("Import " + FILENAME_APPDATA + " file...");
        fc.setFileFilter(new FileNameExtensionFilter(".zip file containing appdata", "zip"));

        int fcReturn = fc.showOpenDialog(null);
        while (fcReturn != JFileChooser.APPROVE_OPTION) { //Try again
            if (fcReturn == JFileChooser.CANCEL_OPTION) return; //Cancels
            fcReturn = fc.showOpenDialog(null);
        }

        //After user chose file
        try {
            importAllFromZip(fc.getSelectedFile().getAbsolutePath());
        } catch (Exception e) {
            InstaDialogue.showErrorMessage(e.getMessage());
        }
    }

    /**
     * Prompt user to choose where, then saves a zip of appdata folder to the given path.
     * @author David Huynh
     */
    public void exportAllToZipByFileChooser() {
        JFileChooser fc = new JFileChooser(PATH_FILECHOOSER_START);
        fc.setDialogTitle("Export " + FILENAME_APPDATA + " to folder...");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int fcReturn = fc.showSaveDialog(null);
        while (fcReturn != JFileChooser.APPROVE_OPTION) { //Miss clicked or something, Try again
            if (fcReturn == JFileChooser.CANCEL_OPTION) return; //Cancels
            fcReturn = fc.showOpenDialog(null);
        }

        //After user chose file
        try {
            exportAllToZip(fc.getSelectedFile().getAbsolutePath());
        } catch (Exception e) {
            InstaDialogue.showErrorMessage(e.getMessage());
        }
    }

    /**
     * Delete recursively starting from given directory.'
     * @author David Huynh
     * @param directoryToBeDeleted starting from given directory
     */
    public void deleteR(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents == null) return;
        for (File file : allContents) {
            if (file.isDirectory()) deleteR(file);
            file.delete();
        }
        directoryToBeDeleted.delete();
    }

//    public static void main(String[] args) { //DEBUG
//        var ad = new AppDataIO();
////        ad.importAllFromZip("appdata.zip");
////        ad.exportAllToZip("appdata.zip");
//        ad.exportAllToZipByFileChooser();
//        ad.importAllFromZipByFileChooser();
//    }
}

