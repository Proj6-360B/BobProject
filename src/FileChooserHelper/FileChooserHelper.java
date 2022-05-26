package FileChooserHelper;

import javax.swing.*;

public class FileChooserHelper {
    /**
     * File Chooser's starting path.
     */
    public static String PATH_FILECHOOSER_START = System.getProperty("user.home") + "/Downloads/";

    public static void showErrorMessageInOptionPane(String theMessage) {
        JFrame tempFrame = new JFrame();
        tempFrame.setVisible(false);
        if (showErrorMessageInOptionPane(tempFrame, theMessage)) {
            tempFrame.dispose();
        }
    }

    public static boolean showErrorMessageInOptionPane(JFrame tempFrame, String theMessage) { //TODO ugly, replace with lambda
        JOptionPane.showMessageDialog(tempFrame, "Please Retry:\n" + theMessage);
        return true;
    }
}
