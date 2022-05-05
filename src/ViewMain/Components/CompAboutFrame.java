package ViewMain.Components;

import Profile.ProfileManager;

import javax.swing.*;
import java.awt.*;

public class CompAboutFrame extends JFrame {
    //Size
    private static final Dimension DIMENSION = new Dimension(450, 130);
    //Components
    private final JTextArea myAboutText;
    private final ProfileManager myProfileManager;
    private final VersionHandler myVersionHandler;

    public CompAboutFrame(ProfileManager theProfileManager) {
        //ProfileManager
        myProfileManager = theProfileManager;
        //Version Handler
        myVersionHandler = new VersionHandler();

        //About Text Area
        myAboutText = initializeAboutText();
        myAboutText.setEditable(false);
        add(myAboutText);

        //Initialize
        initializeFrame();
        setVisible(true);
    }

    private void initializeFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(DIMENSION);
        setLocationRelativeTo(null);
    }

    private JTextArea initializeAboutText() {
        StringBuffer sb = new StringBuffer();
        sb.append("Current User: ");
        sb.append(myProfileManager.getSelectedProfile().getUsername());
        sb.append(" (");
        sb.append(myProfileManager.getSelectedProfile().getEmail());
        sb.append(")\n");
        sb.append("   Privilege: ");
        sb.append(myProfileManager.getSelectedProfile().getPrivilege());
        sb.append("\n\nProject6 Team: Damien Cruz, Abdulmuen Fethi, David Huynh, Andrew Nguyen\n");
        sb.append("   Version: ");
        sb.append(myVersionHandler.VERSION);

        return new JTextArea(sb.toString());
    }
}
