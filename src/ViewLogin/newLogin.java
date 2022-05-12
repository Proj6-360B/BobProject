package ViewLogin;

import Profile.Privilege;
import Profile.ProfileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//credit = "https://www.geeksforgeeks.org/java-swing-simple-user-registration-form/"
class newLogin
        extends JDialog
        implements ActionListener {

    private ProfileManager myProjectManager;

    // Components of the Form
    private final String font = "Arial";
    private Container c;
    private JLabel title;
    private JLabel name;
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
    public newLogin(ProfileManager thePM)
    {
        myProjectManager = thePM;

        setTitle("Create New Profile");
        setBounds(300, 90, 450, 350);
        setAlwaysOnTop(true);//so the user cannot bypass login by clicking off of it
        setResizable(false);//so it doesn't look ugly with a resize
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//so you can't avoid login

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Create New Profile");
        title.setFont(new Font(font, Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(100, 30);
        c.add(title);

        name = new JLabel("Name");
        name.setFont(new Font(font, Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(50, 100);
        c.add(name);

        tname = new JTextField();
        tname.setFont(new Font(font, Font.PLAIN, 15));
        tname.setSize(190, 20);
        tname.setLocation(150, 100);
        c.add(tname);

        email = new JLabel("E-Mail");
        email.setFont(new Font(font, Font.PLAIN, 20));
        email.setSize(100, 20);
        email.setLocation(50, 150);
        c.add(email);

        emailText = new JTextField();
        emailText.setFont(new Font(font, Font.PLAIN, 15));
        emailText.setSize(190, 20);
        emailText.setLocation(150, 150);
        c.add(emailText);

        privilegeLabel = new JLabel("Privilege Level");
        privilegeLabel.setFont(new Font(font, Font.PLAIN, 20));
        privilegeLabel.setSize(100, 20);
        privilegeLabel.setLocation(50, 200);
        c.add(privilegeLabel);

        adminButton = new JRadioButton("Admin");
        adminButton.setFont(new Font(font, Font.PLAIN, 15));
        adminButton.setSelected(true);
        adminButton.setSize(75, 20);
        adminButton.setLocation(150, 200);
        c.add(adminButton);

        normalButton = new JRadioButton("Normal");
        normalButton.setFont(new Font(font, Font.PLAIN, 15));
        normalButton.setSelected(false);
        normalButton.setSize(80, 20);
        normalButton.setLocation(225, 200);
        c.add(normalButton);

        guestButton = new JRadioButton("Guest");
        guestButton.setFont(new Font(font, Font.PLAIN, 15));
        guestButton.setSelected(false);
        guestButton.setSize(80, 20);
        guestButton.setLocation(300, 200);
        c.add(guestButton);

        privilegeGroup = new ButtonGroup();
        privilegeGroup.add(adminButton);
        privilegeGroup.add(normalButton);
        privilegeGroup.add(guestButton);

        submitButton = new JButton("Submit");
        submitButton.setFont(new Font(font, Font.PLAIN, 15));
        submitButton.setSize(100, 20);
        submitButton.setLocation(100, 250);
        submitButton.addActionListener(this);
        c.add(submitButton);

        resetButton = new JButton("Cancel");
        resetButton.setFont(new Font(font, Font.PLAIN, 15));
        resetButton.setSize(100, 20);
        resetButton.setLocation(220, 250);
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
            myProjectManager.addNewProfile(tname.getText(), emailText.getText(), p);
            myProjectManager.writeProfiles();
            new ViewLogin(myProjectManager);//reopen login screen
        } else if (e.getSource() == resetButton) {
            new ViewLogin(myProjectManager);//reopen login screen
            dispose();
        }
    }
}