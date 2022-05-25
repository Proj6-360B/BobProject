package ViewLogin;

import Authintication.Passtech;
import Profile.Privilege;
import Profile.ProfileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * @author Damien Cruz
 * 5/12/22
 */
class NewLogin
        extends JDialog
        implements ActionListener {

    private ProfileManager myProjectManager;

    // Components of the Form
    private final String font = "Arial";
    private Container c;
    private JLabel title;
    private JLabel name;
    private JLabel pass1,pass2;
    private JPasswordField pass1Text;
    private JPasswordField pass2Text;
    private JTextField tname;
    private JLabel email;
    private JTextField emailText;
    private JLabel privilegeLabel;
    private JRadioButton adminButton;//admin
    private JRadioButton normalButton;//normal
    private JRadioButton guestButton;//guest
    private ButtonGroup privilegeGroup;
    private JButton submitButton;
    private JButton resetButton;
    private JLabel res;


    // constructor, to initialize the components
    // with default values.

    /**
     * @Damien Cruz
     * constructor, to initialize the components with default values.
     * @param thePM
     */
    public NewLogin(ProfileManager thePM)
    {
        myProjectManager = thePM;

        setTitle("Create New Profile");
        setBounds(300, 90, 450, 450);
        setAlwaysOnTop(true);//so the user cannot bypass login by clicking off of it
        setResizable(false);//so it doesn't look ugly with a resize
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//so you can't avoid login

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Create New Profile");
        title.setFont(new Font(font, Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(100, 20);
        c.add(title);

        name = new JLabel("Name");
        name.setFont(new Font(font, Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(50, 80);
        c.add(name);

        tname = new JTextField();
        tname.setFont(new Font(font, Font.PLAIN, 15));
        tname.setSize(190, 20);
        tname.setLocation(150, 80);
        c.add(tname);

        email = new JLabel("E-Mail");
        email.setFont(new Font(font, Font.PLAIN, 20));
        email.setSize(100, 20);
        email.setLocation(50, 130);
        c.add(email);

        emailText = new JTextField();
        emailText.setFont(new Font(font, Font.PLAIN, 15));
        emailText.setSize(190, 20);
        emailText.setLocation(150, 130);
        c.add(emailText);

        //put password stuff here
        pass1 = new JLabel("Password");
        pass1.setFont(new Font(font, Font.PLAIN, 20));
        pass1.setSize(100,20);
        pass1.setLocation(50,180);
        c.add(pass1);

        pass1Text = new JPasswordField();
        pass1Text.setFont(new Font(font, Font.PLAIN, 15));
        pass1Text.setSize(190, 20);
        pass1Text.setLocation(150, 180);
        c.add(pass1Text);

        pass2 = new JLabel("Confirm Password");
        pass2.setFont(new Font(font, Font.PLAIN, 20));
        pass2.setSize(100,20);
        pass2.setLocation(50,230);
        c.add(pass2);

        pass2Text = new JPasswordField();
        pass2Text.setFont(new Font(font, Font.PLAIN, 15));
        pass2Text.setSize(190, 20);
        pass2Text.setLocation(150, 230);
        c.add(pass2Text);

        privilegeLabel = new JLabel("Privilege Level");
        privilegeLabel.setFont(new Font(font, Font.PLAIN, 20));
        privilegeLabel.setSize(100, 20);
        privilegeLabel.setLocation(50, 275);
        c.add(privilegeLabel);

        adminButton = new JRadioButton("Admin");
        adminButton.setFont(new Font(font, Font.PLAIN, 15));
        adminButton.setSelected(true);
        adminButton.setSize(75, 20);
        adminButton.setLocation(150, 275);
        c.add(adminButton);

        normalButton = new JRadioButton("Normal");
        normalButton.setFont(new Font(font, Font.PLAIN, 15));
        normalButton.setSelected(false);
        normalButton.setSize(80, 20);
        normalButton.setLocation(225, 275);
        c.add(normalButton);

        guestButton = new JRadioButton("Guest");
        guestButton.setFont(new Font(font, Font.PLAIN, 15));
        guestButton.setSelected(false);
        guestButton.setSize(80, 20);
        guestButton.setLocation(300, 275);
        c.add(guestButton);

        privilegeGroup = new ButtonGroup();
        privilegeGroup.add(adminButton);
        privilegeGroup.add(normalButton);
        privilegeGroup.add(guestButton);

        submitButton = new JButton("Submit");
        submitButton.setFont(new Font(font, Font.PLAIN, 15));
        submitButton.setSize(100, 20);
        submitButton.setLocation(100, 325);
        submitButton.addActionListener(this);
        c.add(submitButton);

        resetButton = new JButton("Cancel");
        resetButton.setFont(new Font(font, Font.PLAIN, 15));
        resetButton.setSize(100, 20);
        resetButton.setLocation(220, 325);
        resetButton.addActionListener(this);
        c.add(resetButton);



        res = new JLabel("");
        res.setFont(new Font(font, Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(100, 350);
        c.add(res);

        setVisible(true);
    }

    // method actionPerformed()
    // to get the action performed
    // by the user and act accordingly

    /**
     * @author Damien Cruz
     * 5/10/22
     * @param e
     */
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == submitButton) {
            Privilege p;
            if(normalButton.isSelected()){
                p = Privilege.NORMAL;
            }else if(adminButton.isSelected()){
                p = Privilege.ADMIN;
            }else{
                p = Privilege.GUEST;
            }
            if(tname.getText().length() == 0){
                JOptionPane.showMessageDialog(c,
                        "Please enter your Name",
                        "Unable To Create New Profile",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(emailText.getText().length() == 0 || ! emailText.getText().contains("@")){
                JOptionPane.showMessageDialog(c,
                        "Please Enter a valid E-Mail Address",
                        "Unable To Create New Profile",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            String pass1String = new String(pass1Text.getPassword());
            String pass2String = new String(pass2Text.getPassword());
            if(pass1Text.getPassword().length == 0){
                JOptionPane.showMessageDialog(c,
                        "You Must Enter a Password to create a new profile",
                        "Unable To Create New Profile",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            else if(!pass1String.equals(pass2String)){
                JOptionPane.showMessageDialog(c,
                        "Passwords Do Not Match",
                        "Unable To Create New Profile",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String passToStore = Passtech.encrypt(pass1String);
            //the passToStore variable has an encrypted version of the password;
            myProjectManager.addNewProfile(tname.getText(), emailText.getText(), p, passToStore);
            new ViewLogin(myProjectManager);//reopen login screen
            dispose();
        } else if (e.getSource() == resetButton) {
            new ViewLogin(myProjectManager);//reopen login screen
            dispose();
        }
    }
}