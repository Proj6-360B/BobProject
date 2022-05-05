package ViewLogin;

import Profile.Profile;
import Profile.ProfileManager;
import ViewLogin.Components.ProfileComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewLogin extends JFrame implements ActionListener {
    //ProfileManager
    private ProfileManager myProfileManager;

    //Size
    private static final Dimension DIMENSION = new Dimension(220, 110);
    //Components
    ProfileComboBox myProfileComboBox;
    JButton myCreateNewButton;
    JButton myLoginButton;

    //Constructor
    public ViewLogin(ProfileManager theProfileManager) {
        //ProfileManager
        myProfileManager = theProfileManager;

        //Initialize
        initializeFrame();
        initializeComponents();
        setVisible(true);
    }

    private void initializeComponents() {
        //ProfileComboBox
        myProfileComboBox = new ProfileComboBox(myProfileManager.getProfileList());
        add(myProfileComboBox);

        //Create New Profile Button
        myCreateNewButton = new JButton("Create New");
        myCreateNewButton.addActionListener(this);
        add(myCreateNewButton);

        //Login Button
        myLoginButton = new JButton("Login");
        myLoginButton.addActionListener(this);
        add(myLoginButton);
    }

    private void initializeFrame() {
        setTitle("Login");
        setSize(DIMENSION);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myCreateNewButton) {
            System.out.println("Create New Event"); //DEBUG
            //TODO start frame to create new Profile
            //TODO update ComboBox with new list
        } else if (e.getSource() == myLoginButton) {
            System.out.println("Login Event"); //DEBUG
            myProfileManager.setSelectedProfile(((Profile)myProfileComboBox.getSelectedItem()));
            dispose();
        }
    }
}
