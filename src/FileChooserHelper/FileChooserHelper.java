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

    private static boolean showErrorMessage(JFrame tempFrame, String theMessage) {
        JOptionPane.showMessageDialog(tempFrame, "Please Retry:\n" + theMessage);
        return true;
    }

    public static String showInputDialog(String theMessage) {
        JFrame tempFrame = new JFrame();
        tempFrame.setVisible(false);
        String temp = showInputDialog(tempFrame, theMessage);
        if (temp == null || temp != null) {
            tempFrame.dispose();
        }
        return temp;
    }

    private static String showInputDialog(JFrame tempFrame, String theMessage) {
        return JOptionPane.showInputDialog(tempFrame, theMessage);
    }
}
