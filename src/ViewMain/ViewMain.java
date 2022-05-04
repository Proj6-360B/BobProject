package ViewMain;

import ViewMain.GUIComponents.*;
import Profile.*;
import javax.swing.*;
import java.awt.*;

public class ViewMain extends JFrame {
    //Managers
    private ProfileManager myProfileManager;
    //TODO private ProjectManager myProjectManager;
    //GUI Components
    private JMenuBar myMenuBar;
    private JTabbedPane myTabbedPane;
    //TODO About Tab/Frame

    public ViewMain(ProfileManager thePM) {
        //Fields Initialize
        myProfileManager = thePM;

        //Initialize
        initializeFrame();
        initializeFrameComponents();
        setVisible(true);
    }

    private void initializeFrame() {
        //Frame Properties
        setTitle("MainGUIFrame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(960, 800));
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);
    }

    private void initializeFrameComponents() {
        //MenuBar
        myMenuBar = new CompMenuBar(myProfileManager);
        setJMenuBar(myMenuBar);

        //TabbedPane
        myTabbedPane = new CompTabbedPane();
        add(myTabbedPane);
    }
}
