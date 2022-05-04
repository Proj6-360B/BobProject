package Main;

import Main.GUIComponents.CompTabbedPane;
import Profile.*;
import javax.swing.*;
import java.awt.*;

public class GUIMain extends JFrame {
    //Managers
    private ProfileManager myProfileManager;
    //GUI Components
    private JTabbedPane myTabbedPane;

    public GUIMain(ProfileManager thePM) {
        myProfileManager = thePM;
        myProfileManager.selectProfileByUsername("Bob"); //TODO Replace with GUI
        initializeFrame();

    }

    private void initializeFrame() {
        setTitle("MainGUIFrame");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(new Dimension(960, 800));
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        initializeFrameComponents();

        setVisible(true);
    }

    private void initializeFrameComponents() {
        //TabbedPane
        myTabbedPane = new CompTabbedPane();
        add(myTabbedPane);
    }
}
