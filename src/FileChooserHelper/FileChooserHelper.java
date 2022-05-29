package FileChooserHelper;

import javax.swing.*;

public class FileChooserHelper {
    /**
     * File Chooser's starting path.
     */
    public static String PATH_FILECHOOSER_START = System.getProperty("user.home") + "/Downloads/";

    public static void showErrorMessage(String theMessage) {
        JFrame tempFrame = new JFrame();
        tempFrame.setVisible(false);
        if (showErrorMessage(tempFrame, theMessage)) {
            tempFrame.dispose();
        }
    }

    public static boolean showErrorMessage(JFrame tempFrame, String theMessage) {
        JOptionPane.showMessageDialog(tempFrame, "Please Retry:\n" + theMessage);
        return true;
    }
}
