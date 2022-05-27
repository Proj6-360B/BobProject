package ViewMain.Components;

import Project.ProjectManager;
import ViewMain.Components.Tabs.*;

import javax.swing.*;

public class CompTabbedPane extends JTabbedPane {
    //Tabs
    private JPanel myActiveProjectsTab;
    private JPanel myArchivedProjectsTab;

    public CompTabbedPane(ProjectManager theProjectManager) {
        //Active Projects Tab
        myActiveProjectsTab = new TabActiveProject(theProjectManager);
        add("Active Projects", myActiveProjectsTab);
        //Archived Projects Tab
        myArchivedProjectsTab = new TabArchivedProject();
        add("Archived Projects", myArchivedProjectsTab);


    }
}
