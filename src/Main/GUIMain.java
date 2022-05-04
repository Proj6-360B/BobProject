package Main;

import Main.GUIComponents.*;
import Profile.*;
import javax.swing.*;
import java.awt.*;

public class GUIMain extends JFrame {
    //Managers
    private ProfileManager myProfileManager;
    //GUI Components
    private JMenuBar myMenuBar;
    private JTabbedPane myTabbedPane;
    //TODO About Tab/Frame

    public GUIMain(ProfileManager thePM) {
        myProfileManager = thePM;
        myProfileManager.selectProfileByUsername("Bob"); //TODO Replace with GUI
        initializeFrame();

    }

    private void initializeFrame() {
        //Frame Properties
        setTitle("MainGUIFrame");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(new Dimension(960, 800));
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        //Components
        initializeFrameComponents();

        setVisible(true);
    }

    private void initializeFrameComponents() {
        //MenuBar
        myMenuBar = new CompMenuBar();
        setJMenuBar(myMenuBar);

        //TabbedPane
        myTabbedPane = new CompTabbedPane();
        add(myTabbedPane);
    }
}
