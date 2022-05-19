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
    private Container c;
    String font = "Arial";

    public CompSettingsFrame(ProfileManager theProfileManager) {
        myProfileManager = theProfileManager;
        initializeFrame();
        //todo setup the look/controls of settings frame



        fontLabel = new JLabel("Font");
        fontLabel.setFont(new Font(font, Font.PLAIN, 20));
        fontLabel.setSize(100, 20);
        fontLabel.setLocation(50, 50);
        c.add(fontLabel);

        JComboBox fontComboBox = new JComboBox<String>(fonts);
        fontComboBox.setSelectedItem(myProfileManager.getSelectedProfile().getFont()); //TODO It's not showing currently saved.
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
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font(font, Font.PLAIN, 15));
        submitButton.setSize(100, 20);
        submitButton.setLocation(50, 250);
        submitButton.addActionListener(this);
        c.add(submitButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font(font, Font.PLAIN, 15));
        cancelButton.setSize(100, 20);
        cancelButton.setLocation(170, 250);
        cancelButton.addActionListener(this);
        c.add(cancelButton);

        defaultButton = new JButton("Reset");
        defaultButton.setFont(new Font(font, Font.PLAIN, 15));
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
        //todo have some way of saving setting selection to the current profile in profile manager
    }
    private void defaultSettings(){
        //todo setup to set the default values to the current profile
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
            //go back or close window
        }else if(e.getSource() ==  defaultButton){
            defaultSettings();
        }
    }
}
