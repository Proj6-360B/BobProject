package ViewMain.Components;

import Profile.ProfileManager;

import javax.swing.*;
import java.awt.*;

/**
 * About frame to show credits, and the current logged-in user.
 * @author David Huynh
 * @author Damien Cruz
 */
public class CompAboutFrame extends JDialog {
    /**
     * ProfileManager from main
     */
    private final ProfileManager myProfileManager;
    /**
     * VersionHandler
     */
    private final VersionHandler myVersionHandler;
    /**
     * Size
     */
    private static final Dimension DIMENSION = new Dimension(370, 250);
    //Components
    private final JTextArea myAboutText;

    /**
     * Constructor
     * @author David Huynh
     * @author Damien Cruz
     * @param theProfileManager ProfileManager from main
     */
    public CompAboutFrame(ProfileManager theProfileManager) {
        //ProfileManager
        myProfileManager = theProfileManager;
        //Version Handler
        myVersionHandler = new VersionHandler();

        //About Text Area
        myAboutText = initializeAboutText();
        myAboutText.setEditable(false);
        add(myAboutText);
        System.out.println(myAboutText.getFont()); //DEBUG

        //Initialize
        initializeFrame();
        setVisible(true);
    }

    /**
     * Frame properties
     * @author David Huynh
     */
    private void initializeFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(DIMENSION);
        setLocationRelativeTo(null);
    }

    /**
     * About text
     * @author David Huynh
     * @return myAboutText
     */
    private JTextArea initializeAboutText() {
        StringBuffer sb = new StringBuffer(); //TODO Read from txt instead of ugly appending.
        sb.append("This app is registered to: ");
        sb.append(myProfileManager.getSelectedProfile().getUsername());
        sb.append(" (");
        sb.append(myProfileManager.getSelectedProfile().getEmail());
        sb.append(")\n");
        sb.append("   Privilege: ");
        sb.append(myProfileManager.getSelectedProfile().getPrivilege());
        sb.append("\n\nThis app provided by the Project6 Team:\n   Damien Cruz\n   Abdulmuen Fethi (Abdi)\n   David Huynh\n   Andrew Nguyen\n\n");
        sb.append("Version: ");
        sb.append(myVersionHandler.VERSION);

        return new JTextArea(sb.toString());
    }
}
