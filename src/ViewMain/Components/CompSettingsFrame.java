package ViewMain.Components;

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
    private JButton submitButton, cancelButton, defaultButton;
    private JLabel fontLabel;
    JComboBox fontComboBox;
    private Container c;
    String defaultFont = "Arial";
    String profileFont;

    public CompSettingsFrame(ProfileManager theProfileManager) {
        myProfileManager = theProfileManager;
        profileFont = myProfileManager.getSelectedProfile().getFont();
        initializeFrame();
        //todo setup the look/controls of settings frame



        fontLabel = new JLabel("Font");
        fontLabel.setFont(new Font(profileFont, Font.PLAIN, 20));
        fontLabel.setSize(100, 20);
        fontLabel.setLocation(50, 50);
        c.add(fontLabel);

        fontComboBox = new JComboBox<String>(fonts);
        fontComboBox.setSelectedItem(profileFont); //TODO It's not showing currently saved.
        fontComboBox.setSize(190, 20);
        fontComboBox.setLocation(130, 50);
        c.add(fontComboBox);

        /*
        ideas
        -text font and color
        -the deafault way things are sorted
        -
         */


        //buttons
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
        }
    }
}
