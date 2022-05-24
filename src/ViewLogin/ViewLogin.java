package ViewLogin;

import Profile.Profile;
import Profile.ProfileManager;
import ViewLogin.Components.ProfileComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewLogin extends JDialog implements ActionListener {
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
        //todo #1 add Jpassword field

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


        setAlwaysOnTop(true);//so the user cannot bypass login by clicking off of it
        setResizable(false);//so it doesn't look ugly with a resize
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//so you can't avoid login
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myCreateNewButton) {
            System.out.println("Create New Event"); //DEBUG
            NewLogin l = new NewLogin(myProfileManager);
            //get info from newlogin screen
            //add info to profile combobox
            //set new profile as the selected profile
            dispose();
        } else if (e.getSource() == myLoginButton) {
            System.out.println("Login Event"); //DEBUG
            //todo #2 encrypt String from password field and compare to stored encrypted password
            myProfileManager.setSelectedProfile(((Profile)myProfileComboBox.getSelectedItem()));
            dispose();
        }
    }

}
