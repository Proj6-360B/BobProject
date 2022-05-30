package InstaDialogue;

import javax.swing.*;

public class InstaDialogue {
    /**
     * File Chooser's starting path.
     */
    public static String PATH_FILECHOOSER_START = System.getProperty("user.home") + "/Downloads/";

    public static boolean showErrorMessage(String theMessage) { //TODO remove redundant
        JOptionPane.showMessageDialog(null, "Please Retry:\n" + theMessage);
        return true;
    }

    public static String showInputDialog(String theMessage) { //TODO remove redundant
        return JOptionPane.showInputDialog(null, theMessage);
    }

    private static String showInputDialog(JFrame tempFrame, String theMessage) {
        return JOptionPane.showInputDialog(tempFrame, theMessage);
    }

    public static int showYesNoConfirmation(String theMessage) {
        return JOptionPane.showConfirmDialog(null, theMessage, "Confirm?", JOptionPane.YES_NO_OPTION);
    }
}
