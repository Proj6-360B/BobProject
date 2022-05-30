package ViewMain.Components;

import Project.ProjectManager;
import ViewMain.Components.Tabs.*;

import javax.swing.*;

public class CompTabbedPane extends JTabbedPane {
    //Tabs
    private JPanel myProjectsTab;
    private JPanel myDocumentsTab;

    public CompTabbedPane(ProjectManager theProjectManager) {
        //Project Tab
        myProjectsTab = new TabProject(theProjectManager);
        add("Active Project", myProjectsTab);
        //Archived Project Tab


    }
}
