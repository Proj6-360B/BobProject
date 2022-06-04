package ViewMain;

import Project.ProjectManager;
import ViewMain.Components.*;
import Profile.*;
import javax.swing.*;
import java.awt.*;

/**
 * Main View that holds the menu bar and tabbed pane.
 * @author David Huynh
 * @author Damien Cruz
 */
public class ViewMain extends JFrame {
    //Managers
    /**
     * ProfileManager from main
     */
    private final ProfileManager myProfileManager;
    /**
     * ProjectManager from main
     */
    private final ProjectManager myProjectManager;

    //Size
    /**
     * Size
     */
    private static final Dimension DIMENSION = new Dimension(960, 800);
    //GUI Components
    private JMenuBar myMenuBar;
    private JTabbedPane myTabbedPane;

    /**
     * Constructor.
     * @author David Huynh
     * @author Damien Cruz
     * @param theProfileManager myProfileManager
     * @param theProjectManager myProjectManager
     */
    public ViewMain(ProfileManager theProfileManager, ProjectManager theProjectManager) {
        //Fields Initialize
        myProfileManager = theProfileManager;
        myProjectManager = theProjectManager;

        //Quit if no Profile selected
        if (myProfileManager.getSelectedProfile() == null) System.exit(0);

        //Initialize
        initializeFrame();
        initializeFrameComponents();
    }

    /**
     * Frame properties
     * @author David Huynh
     * @author Damien Cruz
     */
    private void initializeFrame() {
        //Frame Properties
        setTitle("Project6 MainGUIFrame");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(DIMENSION);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);
    }

    /**
     * Frame components and add.
     * @author David Huynh
     * @author Damien Cruz
     */
    private void initializeFrameComponents() {
        //MenuBar
        myMenuBar = new CompMenuBar(myProfileManager);
        setJMenuBar(myMenuBar);

        //TabbedPane
        myTabbedPane = new CompTabbedPane(myProjectManager, myProfileManager);
        add(myTabbedPane);
    }
}
