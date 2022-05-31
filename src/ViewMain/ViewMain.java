package ViewMain;

import Project.ProjectManager;
import ViewLogin.ViewLogin;
import ViewMain.Components.*;
import Profile.*;
import javax.swing.*;
import java.awt.*;

public class ViewMain extends JDialog {
    //Managers
    private final ProfileManager myProfileManager;
    private final ProjectManager myProjectManager;

    //Size
    private static final Dimension DIMENSION = new Dimension(960, 800);
    //GUI Components
    private JMenuBar myMenuBar;
    private JTabbedPane myTabbedPane;

    public ViewMain(ProfileManager theProfileManager, ProjectManager theProjectManager) {
        //Fields Initialize
        myProfileManager = theProfileManager;
        myProjectManager = theProjectManager;

        //Initialize
        initializeFrame();
        initializeFrameComponents();
    }

    private void initializeFrame() {
        //Frame Properties
        setTitle("Project6 MainGUIFrame");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(DIMENSION);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);
    }

    private void initializeFrameComponents() {
        //MenuBar
        myMenuBar = new CompMenuBar(myProfileManager);
        setJMenuBar(myMenuBar);

        //TabbedPane
        myTabbedPane = new CompTabbedPane(myProjectManager);
        add(myTabbedPane);
    }
}
