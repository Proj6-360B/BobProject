package ViewMain.Components;

import Profile.ProfileManager;
import ViewLogin.ViewLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MenuBar for ViewMain.
 * @author David Huynh
 * @author Damien Cruz
 * @author Andrew Nguyen
 */
public class CompMenuBar extends JMenuBar implements ActionListener {
    /**
     * ProfileManager from main
     */
    private ProfileManager myProfileManager;
    //Components
    private JButton myAboutButton;
    private JButton mySettingsButton;
    private JButton myLogoutButton;

    /**
     * Constructor.
     * @author David Huynh
     * @author Damien Cruz
     * @author Andrew Nguyen
     * @param theProfileManager ProfileManager from main
     */
    public CompMenuBar(ProfileManager theProfileManager) {
        //ProfileManager
        myProfileManager = theProfileManager;

        //Properties
        setBackground(Color.white);
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        //About Button
        myAboutButton = new JButton("About");
        myAboutButton.setBackground(Color.white);
        myAboutButton.addActionListener(this);
        add(myAboutButton);

        //Settings Button
        mySettingsButton = new JButton("Settings");
        mySettingsButton.setBackground(Color.white);
        mySettingsButton.addActionListener(this);
        add(mySettingsButton);

        myLogoutButton = new JButton("Logout");
        myLogoutButton.setBackground(Color.white);
        myLogoutButton.addActionListener(this);
        add(myLogoutButton);

    }

    /**
     * ActionListener for all buttons.
     * @author David Huynh
     * @author Damien Cruz
     * @author Andrew Nguyen
     * handles all button pushes for this frame
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myAboutButton) {
            //open about window
             new CompAboutFrame(myProfileManager);
        } else if (e.getSource() == mySettingsButton) {
            //open settings menu
             new CompSettingsFrame(myProfileManager);
        } else if (e.getSource() == myLogoutButton) {

            myProfileManager.logout();
            ViewLogin login = new ViewLogin(myProfileManager);
            login.setVisible(true);

          //  System.exit(0); // will try to make it redirect instead

        }

    }
}
