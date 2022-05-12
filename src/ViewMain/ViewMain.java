package ViewMain;

import ViewMain.Components.*;
import Profile.*;
import javax.swing.*;
import java.awt.*;

public class ViewMain extends JFrame {
    //Managers
    private final ProfileManager myProfileManager;
    //TODO private ProjectManager myProjectManager;

    //Size
    private static final Dimension DIMENSION = new Dimension(960, 800);
    //GUI Components
    private JMenuBar myMenuBar;
    private JTabbedPane myTabbedPane;

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
        setTitle("Project6 MainGUIFrame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(DIMENSION);
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
