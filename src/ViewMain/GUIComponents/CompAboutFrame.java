package ViewMain.GUIComponents;

import Profile.ProfileManager;

import javax.swing.*;
import java.awt.*;

public class CompAboutFrame extends JFrame {
    private JTextArea myAboutText;
    private ProfileManager myProfileManager;
    //TODO Version Number Handler Class thing

    public CompAboutFrame(ProfileManager theProfileManager) {
        //ProfileManager
        myProfileManager = theProfileManager;

        //About Text Area
        myAboutText = initializeAboutText();
        add(myAboutText);

        //Initialize
        initializeFrame();
        setVisible(true);
    }

    private void initializeFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(new Dimension(450, 130));
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
        //TODO sb.append() the version number

        return new JTextArea(sb.toString());
    }
}
