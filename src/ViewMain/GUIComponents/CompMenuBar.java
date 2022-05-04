package ViewMain.GUIComponents;

import Profile.ProfileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompMenuBar extends JMenuBar {
    //Menus
    private JButton myAboutButton; //TODO JMenuItem breaks button size & JButton is ugly
    private ProfileManager myProfileManager;

    public CompMenuBar(ProfileManager theProfileManager) {
        //ProfileManager
        myProfileManager = theProfileManager;

        //About
        myAboutButton = new JButton("About");
        myAboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame temp = new CompAboutFrame(myProfileManager);
            }
        });
        add(myAboutButton);

        //Properties
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    }
}
