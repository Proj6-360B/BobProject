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
        add(myAboutButton);

        //Settings Button
        mySettingsButton = new JButton("Settings");
        mySettingsButton.setBackground(Color.white);
        mySettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == myAboutButton){
                    JFrame temp = new CompAboutFrame(myProfileManager);
                }else if (e.getSource() == mySettingsButton){
                    //todo open settings frame
                    JFrame temp2 = new CompSettingsFrame(myProfileManager);
                }
                //TODO Settings Frame or whatever to export/import settings
            }
        });
        add(mySettingsButton);
    }
}
