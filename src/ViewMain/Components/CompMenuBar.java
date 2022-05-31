package ViewMain.Components;

import Profile.ProfileManager;
import ViewLogin.ViewLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompMenuBar extends JMenuBar implements ActionListener {
    //Menus
    private JButton myAboutButton; //TODO JMenuItem breaks button size & JButton is ugly
    private JButton mySettingsButton;
    private JButton myLogoutButton;
    private ProfileManager myProfileManager;

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

        //Logout Button
        myLogoutButton = new JButton("Logout");
        myLogoutButton.setBackground(Color.white);
        myLogoutButton.addActionListener(this);
        add(myLogoutButton);


    }

    /**
     * @author Damien Cruz
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
            System.exit(0); // will try to make it redirect instead
        }

    }
}
