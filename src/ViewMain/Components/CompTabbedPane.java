package ViewMain.Components;

import Profile.ProfileManager;
import Project.*;
import ViewMain.Components.Tabs.*;

import javax.swing.*;
import java.util.LinkedList;

public class CompTabbedPane extends JTabbedPane {
    //Tabs
    private JPanel myProjectsTab;
    private JPanel myDocumentsTab;

    public CompTabbedPane(ProjectManager theProjectManager, ProfileManager theProfileManager) {
        //Project Tab
        myProjectsTab = new TabProjects(theProjectManager, theProfileManager);
        add("Project", myProjectsTab);
        //Documents
        myDocumentsTab = new TabDocuments(theProjectManager);
        add("Documents", myDocumentsTab);
    }
}
