package ViewLogin;

import Authintication.Passtech;
import Profile.Profile;
import Profile.ProfileManager;
import ViewLogin.Components.ProfileComboBox;
import ViewMain.ViewMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ViewLogin extends JDialog implements ActionListener, KeyListener {

    //ProfileManager
    private ProfileManager myProfileManager;
    Toolkit t = Toolkit.getDefaultToolkit();

    //Size
    private static final Dimension DIMENSION = new Dimension(320, 140);
    //Components
    ProfileComboBox myProfileComboBox;
    JButton myCreateNewButton;
    JButton myLoginButton;
    JButton myGuestButton;
    JPasswordField passf;
    private Container c;

    //Constructor
    public ViewLogin(ProfileManager theProfileManager) {
        super(null, "Login", ModalityType.APPLICATION_MODAL);

        //ProfileManager
        myProfileManager = theProfileManager;

        //Initialize
        setSize(DIMENSION);
        setLocationRelativeTo(null);
        setResizable(false);//so it doesn't look ugly with a resize
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//so you can't avoid login

        //Enter key listen
        addKeyListener(this);

        initializeComponents();
//        setVisible(true);
    }

    private void initializeComponents() {
        c = getContentPane();
        c.setLayout(null);

        //ProfileComboBox
        myProfileComboBox = new ProfileComboBox(myProfileManager.getProfileList());
        myProfileComboBox.setSize(280, 30);
        System.out.println(myProfileComboBox.getSize().getHeight());
        myProfileComboBox.setLocation(15, 10);
        c.add(myProfileComboBox);

        //Password field
        passf = new JPasswordField();
        passf.setSize(280, 20);
        passf.setLocation(15, 45);
        passf.createToolTip();
        passf.setToolTipText("Enter Your Password");
        c.add(passf);

        //Create New Profile Button
        myCreateNewButton = new JButton("Create New");
        myCreateNewButton.setLocation(15, 70);
        myCreateNewButton.setSize(100, 30);
        myCreateNewButton.addActionListener(this);
        c.add(myCreateNewButton);

        //Guest Button
        myGuestButton = new JButton("Guest Login");
        myGuestButton.setLocation(115, 70);
        myGuestButton.setSize(105, 30);
        myGuestButton.addActionListener(this);
        c.add(myGuestButton);

        //Login Button
        myLoginButton = new JButton("Login");
        myLoginButton.setLocation(217, 70);
        myLoginButton.setSize(80, 30);
        myLoginButton.addActionListener(this);
        c.add(myLoginButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myCreateNewButton) {
            System.out.println("Create New Event"); //DEBUG

            NewLogin l = new NewLogin(myProfileManager);
            l.setVisible(true);

            c.remove(myProfileComboBox); //TODO there has to be a better way than remaking it.
            myProfileComboBox = new ProfileComboBox(myProfileManager.getProfileList());
            myProfileComboBox.setSize(180, 30);
            myProfileComboBox.setLocation(15, 10);
            c.add(myProfileComboBox);
            this.repaint();
        } else if (e.getSource() == myLoginButton) {
            loginEvent();
        } else if (e.getSource() == myGuestButton) {
            myProfileManager.loginAsGuest();
            dispose();
        }
    }

    private void loginEvent() {
        System.out.println("Login Event"); //DEBUG
        String passString = new String(passf.getPassword());
        Profile selected = (Profile) myProfileComboBox.getSelectedItem();
        if (Passtech.encrypt(passString).equals(selected.getePassword())) {
            System.out.println("passwords match");
            myProfileManager.setSelectedProfile(selected);
            myProfileManager.writeProfiles();
            dispose();
        } else {
            System.out.println("passwords dont match");
            JOptionPane.showMessageDialog(c,
                    "Password and Selected Profile Do not match",
                    "Unable To Login",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Enter
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            loginEvent();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Nothing
    }
}


