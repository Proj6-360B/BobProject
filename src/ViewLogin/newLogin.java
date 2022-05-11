package ViewLogin;

import Profile.Privilege;
import Profile.ProfileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//credit = "https://www.geeksforgeeks.org/java-swing-simple-user-registration-form/"
class newLogin
        extends JFrame
        implements ActionListener {

    private ProfileManager myProjectManager;

    // Components of the Form
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
    private JLabel dob;
    private JComboBox date;
    private JComboBox month;
    private JComboBox year;
    private JButton submitButton;
    private JButton resetButton;
    private JLabel res;
    private JTextArea resadd;

    private String dates[]
            = { "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31" };
    private String months[]
            = { "Jan", "feb", "Mar", "Apr",
            "May", "Jun", "July", "Aug",
            "Sup", "Oct", "Nov", "Dec" };
    private String years[]
            = { "1995", "1996", "1997", "1998",
            "1999", "2000", "2001", "2002",
            "2003", "2004", "2005", "2006",
            "2007", "2008", "2009", "2010",
            "2011", "2012", "2013", "2014",
            "2015", "2016", "2017", "2018",
            "2019" };

    // constructor, to initialize the components
    // with default values.
    public newLogin(ProfileManager thePM)
    {
        myProjectManager = thePM;

        setTitle("Create New Profile");
        setBounds(300, 90, 450, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Create New Profile");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(100, 30);
        c.add(title);

        name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(100, 100);
        c.add(name);

        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(190, 20);
        tname.setLocation(200, 100);
        c.add(tname);

        email = new JLabel("E-Mail");
        email.setFont(new Font("Arial", Font.PLAIN, 20));
        email.setSize(100, 20);
        email.setLocation(100, 150);
        c.add(email);

        emailText = new JTextField();
        emailText.setFont(new Font("Arial", Font.PLAIN, 15));
        emailText.setSize(150, 20);
        emailText.setLocation(200, 150);
        c.add(emailText);

        privilegeLabel = new JLabel("Privilege Level");
        privilegeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        privilegeLabel.setSize(100, 20);
        privilegeLabel.setLocation(100, 200);
        c.add(privilegeLabel);

        adminButton = new JRadioButton("Admin");
        adminButton.setFont(new Font("Arial", Font.PLAIN, 15));
        adminButton.setSelected(true);
        adminButton.setSize(75, 20);
        adminButton.setLocation(200, 200);
        c.add(adminButton);

        normalButton = new JRadioButton("Normal");
        normalButton.setFont(new Font("Arial", Font.PLAIN, 15));
        normalButton.setSelected(false);
        normalButton.setSize(80, 20);
        normalButton.setLocation(275, 200);
        c.add(normalButton);

        guestButton = new JRadioButton("Guest");
        guestButton.setFont(new Font("Arial", Font.PLAIN, 15));
        guestButton.setSelected(false);
        guestButton.setSize(80, 20);
        guestButton.setLocation(350, 200);
        c.add(guestButton);

        privilegeGroup = new ButtonGroup();
        privilegeGroup.add(adminButton);
        privilegeGroup.add(normalButton);
        privilegeGroup.add(guestButton);

        dob = new JLabel("DOB");
        dob.setFont(new Font("Arial", Font.PLAIN, 20));
        dob.setSize(100, 20);
        dob.setLocation(100, 250);
        c.add(dob);

        date = new JComboBox(dates);
        date.setFont(new Font("Arial", Font.PLAIN, 15));
        date.setSize(50, 20);
        date.setLocation(200, 250);
        c.add(date);

        month = new JComboBox(months);
        month.setFont(new Font("Arial", Font.PLAIN, 15));
        month.setSize(60, 20);
        month.setLocation(250, 250);
        c.add(month);

        year = new JComboBox(years);
        year.setFont(new Font("Arial", Font.PLAIN, 15));
        year.setSize(60, 20);
        year.setLocation(320, 250);
        c.add(year);


        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 15));
        submitButton.setSize(100, 20);
        submitButton.setLocation(150, 300);
        submitButton.addActionListener(this);
        c.add(submitButton);

        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.PLAIN, 15));
        resetButton.setSize(100, 20);
        resetButton.setLocation(270, 300);
        resetButton.addActionListener(this);
        c.add(resetButton);



        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
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
            new ViewLogin(myProjectManager);
            dispose();
        } else if (e.getSource() == resetButton) {
            ViewLogin log = new ViewLogin(myProjectManager);
            dispose();
        }
    }
}