package ViewLogin;

import Profile.ProfileManager;

import javax.swing.*;

public class ViewLogin extends JFrame {
    //ProfileManager
    private ProfileManager myProfileManager;

    //Constructor
    public ViewLogin(ProfileManager theProfileManager) {
        //ProfileManager
        myProfileManager = theProfileManager;

        //Initialize
        initializeFrame();
        setVisible(true);
    }

    private void initializeFrame() {
        //TODO Make the Frame
    }
}
