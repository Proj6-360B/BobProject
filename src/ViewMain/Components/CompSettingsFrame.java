package ViewMain.Components;

import AppData.AppDataIO;
import Authintication.Passtech;
import Profile.Profile;
import Profile.ProfileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Damien Cruz
 */
public class CompSettingsFrame extends JFrame implements ActionListener {
    private final ProfileManager myProfileManager;
    private final String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    private JButton submitButton, cancelButton, defaultButton, exportButton, importButton, newPassButton;
    private JLabel fontLabel, emailLabel, oldPass, newPass;
    private JPasswordField oldPassField, newPassField;
    JComboBox fontComboBox;
    JTextField emailText;
    private Container c;
    String defaultFont = "Arial";
    String profileFont;



    public CompSettingsFrame(ProfileManager theProfileManager) {
        myProfileManager = theProfileManager;
        profileFont = myProfileManager.getSelectedProfile().getFont();
        initializeFrame();
        //todo setup the look/controls of settings frame


/*
        fontLabel = new JLabel("Font");
        fontLabel.setFont(new Font(profileFont, Font.PLAIN, 20));
        fontLabel.setSize(100, 20);
        fontLabel.setLocation(35, 50);
        c.add(fontLabel);

        fontComboBox = new JComboBox<String>(fonts);
        fontComboBox.setSelectedItem(profileFont); //TODO It's not showing currently saved.
        fontComboBox.setSize(190, 20);
        fontComboBox.setLocation(130, 50);
        c.add(fontComboBox);
        *
 */


        emailLabel = new JLabel("Update Email");
        emailLabel.setFont(new Font(profileFont,Font.PLAIN,20));
        emailLabel.setSize(140,20);
        emailLabel.setLocation(30, 100);
        c.add(emailLabel);

        emailText = new JTextField();
        emailText.setFont(new Font(profileFont, Font.PLAIN, 15));
        emailText.setSize(190, 20);
        emailText.setLocation(170, 100);
        c.add(emailText);

        oldPass = new JLabel("Old Password");
        oldPass.setFont(new Font(profileFont, Font.PLAIN, 20));
        oldPass.setLocation(30, 130);
        oldPass.setSize(140,20);
        c.add(oldPass);

        oldPassField = new JPasswordField();
        oldPassField.setFont(new Font(profileFont, Font.PLAIN, 15));
        oldPassField.setSize(190, 20);
        oldPassField.setLocation(170, 130);
        c.add(oldPassField);

        newPass = new JLabel("New Password");
        newPass.setFont(new Font(profileFont, Font.PLAIN, 20));
        newPass.setLocation(30, 160);
        newPass.setSize(140,20);
        c.add(newPass);

        newPassField = new JPasswordField();
        newPassField.setFont(new Font(profileFont, Font.PLAIN, 15));
        newPassField.setSize(190, 20);
        newPassField.setLocation(170, 160);
        c.add(newPassField);






        /*
        ideas
        -text font and color
        -the deafault way things are sorted
        -
         */


        //buttons
        importButton = new JButton("Import");
        importButton.setFont(new Font(profileFont, Font.PLAIN, 15));
        importButton.setSize(100,20);
        importButton.setLocation(50, 280);
        importButton.addActionListener(this);
        c.add(importButton);

        exportButton = new JButton("Export");
        exportButton.setFont(new Font(profileFont, Font.PLAIN, 15));
        exportButton.setSize(100,20);
        exportButton.setLocation(170, 280);
        exportButton.addActionListener(this);
        c.add(exportButton);

        newPassButton = new JButton("Change Pass");
        newPassButton.setFont(new Font(profileFont, Font.PLAIN, 15));
        newPassButton.setSize(100,20);
        newPassButton.setLocation(290, 280);
        newPassButton.addActionListener(this);
        c.add(newPassButton);



        submitButton = new JButton("Apply");
        submitButton.setFont(new Font(profileFont, Font.PLAIN, 15));
        submitButton.setSize(100, 20);
        submitButton.setLocation(50, 250);
        submitButton.addActionListener(this);
        c.add(submitButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font(profileFont, Font.PLAIN, 15));
        cancelButton.setSize(100, 20);
        cancelButton.setLocation(170, 250);
        cancelButton.addActionListener(this);
        c.add(cancelButton);

        defaultButton = new JButton("Reset");
        defaultButton.setFont(new Font(profileFont, Font.PLAIN, 15));
        defaultButton.setSize(100, 20);
        defaultButton.setLocation(290, 250);
        defaultButton.addActionListener(this);
        c.add(defaultButton);
        setVisible(true);
    }
    private void initializeFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(myProfileManager.getSelectedProfile().getUsername() +"`s" + " Settings"); //custom to user
        c = getContentPane();
        c.setLayout(null);
        setBounds(300, 90, 450, 350);
        setLocationRelativeTo(null);
    }
    private void confirmSettings(){
        //saving setting selection to the current profile in profile manager
        if(!emailText.getText().equals("")){
            myProfileManager.getSelectedProfile().setEmail(emailText.getText());
        }
        myProfileManager.getSelectedProfile().setFont(fontComboBox.getSelectedItem().toString());
        myProfileManager.writeProfiles();
        dispose();
    }
    private void defaultSettings(){
        //setup to set the default values to the current profile
        myProfileManager.getSelectedProfile().setFont(defaultFont);
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitButton){
            confirmSettings();
        }else if(e.getSource() == cancelButton){
            dispose();
        }else if(e.getSource() ==  defaultButton){
            defaultSettings();
        }else if (e.getSource() == importButton){
            new AppDataIO().importAllFromZipByFileChooser();
            //unsure if anything else needs to be done to make it work
        }else if(e.getSource() == exportButton){
            new AppDataIO().exportAllToZipByFileChooser();
        } else if(e.getSource() == newPassButton){
            String oldPassString = new String(oldPassField.getPassword());
            String newPassString = new String(newPassField.getPassword());

            if(Passtech.encrypt(oldPassString).equals(myProfileManager.getSelectedProfile().getePassword())) {
                System.out.println("passwords match");
                myProfileManager.getSelectedProfile().setePassword(Passtech.encrypt(newPassString));
                myProfileManager.writeProfiles();
                JOptionPane.showMessageDialog(c,
                        "Password Has been updated",
                        "Sucsess",
                        JOptionPane.INFORMATION_MESSAGE);
            }else{
                System.out.println("Old passWord Did Not Match");
                JOptionPane.showMessageDialog(c,
                        "Current Password Does Not Match ",
                        "Unable To Change Password",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
