package InstaDialogue;

import javax.swing.*;

/**
 * Helper to create JDialog windows for things like errors.
 * @author David Huynh
 */
public class InstaDialogue {
    /**
     * File Chooser's starting path.
     */
    public static String PATH_FILECHOOSER_START = System.getProperty("user.home") + "/Downloads/";

    /**
     * Show dialog box for error message.
     * @author David Huynh
     * @param theMessage Message to convey.
     * @return true, to use for timing.
     */
    public static boolean showErrorMessage(String theMessage) { //TODO remove redundant
        JOptionPane.showMessageDialog(null, "Please Retry:\n" + theMessage);
        return true;
    }

    /**
     * Show dialog box to get 1 String input.
     * @param theMessage Message to convey.
     * @return User input String.
     */
    public static String showInputDialog(String theMessage) { //TODO remove redundant
        return JOptionPane.showInputDialog(null, theMessage);
    }

    /**
     * Show yes or no confirmation box.
     * @author David Huynh
     * @param theMessage Message to convey.
     * @return 0: Yes/True
     */
    public static int showYesNoConfirmation(String theMessage) {
        return JOptionPane.showConfirmDialog(null, theMessage, "Confirm?", JOptionPane.YES_NO_OPTION);
    }
}
