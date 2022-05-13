package ViewMain.Components;

import Profile.ProfileManager;

import javax.swing.*;
import java.awt.*;

/**
 * @author Damien Cruz
 */
public class CompSettingsFrame extends JFrame {
    private static final Dimension DIMENSION = new Dimension(350, 200) ;
    private final ProfileManager myProfileManager;
    public CompSettingsFrame(ProfileManager theProfileManager) {
        myProfileManager = theProfileManager;
        initializeFrame();
        //todo setup the look/controls of settings frame




        setVisible(true);
    }
    private void initializeFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(DIMENSION);
        setLocationRelativeTo(null);
    }
    private void exportSettings(){
        //figure out how to export the settings
    }

}
