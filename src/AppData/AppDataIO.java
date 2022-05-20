package AppData;
import java.io.File;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Export/Import a zip file this program appdata folder. <br>
 * Best is construct, then import/export, and let garbage collect. <br>
 * https://github.com/srikanth-lingala/zip4j
 * @author David Huynh
 */
public class AppDataIO {
    /**
     * Path where the appdata folder is stored.
     */
    private static String PATH_APPDATA = "./appdata/";
    /**
     * File name to export as.
     */
    private static String FILENAME_APPDATA = "appdata.zip";
    /**
     * File Chooser's starting path.
     */
    private static String PATH_FILECHOOSER_START = System.getProperty("user.home") + "/Downloads/";

    /**
     * Call to init, then import/export and let garbage collect.
     */
    public AppDataIO() {
        //
    }

    /**
     * Saves a zip of appdata folder to the given path.
     * @param theExportPath The path to save appdata.zip at.
     */
    public void exportAllToZip(String theExportPath) throws ZipException {
        new ZipFile(theExportPath + '\\' + FILENAME_APPDATA).addFolder(new File(PATH_APPDATA));
        System.out.println("Written to " + theExportPath + '\\' + FILENAME_APPDATA);
    }

    /**
     * Write all info from given Zip file to appdata folder.
     * @param theZipPath The path of appdata.zip
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
            showErrorMessageInOptionPane(e.getMessage());
        }

    }

    /**
     * Prompt user to choose where, then saves a zip of appdata folder to the given path.
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
            showErrorMessageInOptionPane(e.getMessage());
        }
    }

    private void showErrorMessageInOptionPane(String theMessage) {
        JFrame tempFrame = new JFrame();
        tempFrame.setVisible(false);
        if (showErrorMessageInOptionPane(tempFrame, theMessage)) {
            tempFrame.dispose();
        }
    }

    private boolean showErrorMessageInOptionPane(JFrame tempFrame, String theMessage) { //TODO ugly, replace with lambda
        JOptionPane.showMessageDialog(tempFrame, "Please Retry:\n" + theMessage);
        return true;
    }

//    public static void main(String[] args) { //DEBUG
//        var ad = new AppDataIO();
////        ad.importAllFromZip("./appdata.zip");
////        ad.exportAllToZip("./appdata.zip");
//        ad.exportAllToZipByFileChooser();
//        ad.importAllFromZipByFileChooser();
//    }
}

