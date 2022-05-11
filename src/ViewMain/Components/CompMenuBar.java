package ViewMain.Components;

import Profile.ProfileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompMenuBar extends JMenuBar {
    //Menus
    private JButton myAboutButton; //TODO JMenuItem breaks button size & JButton is ugly
    private JButton mySettingsButton;
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
        myAboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame temp = new CompAboutFrame(myProfileManager);
            }
        });
        add(myAboutButton);

        //Settings Button
        mySettingsButton = new JButton("Settings");
        mySettingsButton.setBackground(Color.white);
        mySettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO Settings Frame or whatever to export/import settings
            }
        });
        add(mySettingsButton);
    }
}
