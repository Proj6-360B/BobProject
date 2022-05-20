package ViewMain.Components;

import Profile.ProfileManager;

import javax.swing.*;
import java.awt.*;

public class CompAboutFrame extends JDialog {
    //Size
    private static final Dimension DIMENSION = new Dimension(370, 250);
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
        myAboutText.setFont(new Font(myProfileManager.getSelectedProfile().getFont().toString(), Font.PLAIN,15));
        add(myAboutText);
        System.out.println(myAboutText.getFont()); //DEBUG

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
