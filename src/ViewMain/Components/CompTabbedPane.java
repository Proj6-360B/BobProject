package ViewMain.Components;

import Project.ProjectManager;
import ViewMain.Components.Tabs.*;

import javax.swing.*;

public class CompTabbedPane extends JTabbedPane {
    //Tabs
    private JPanel myActiveProjectsTab;
    private JPanel myArchivedProjectsTab;

    public CompTabbedPane(ProjectManager theProjectManager) {
        //Active Project Tab
        myActiveProjectsTab = new TabActiveProject(theProjectManager);
        add("Active Project", myActiveProjectsTab);
        //Archived Project Tab
        myArchivedProjectsTab = new TabArchivedProject();
        add("Archived Project", myArchivedProjectsTab);


    }
}
