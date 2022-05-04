package ViewMain.GUIComponents;

import ViewMain.GUIComponents.Tabs.*;

import javax.swing.*;

public class CompTabbedPane extends JTabbedPane {
    //Tabs
    private JPanel myActiveProjectsTab;
    private JPanel myArchivedProjectsTab;

    public CompTabbedPane() {
        //Active Projects Tab
        myActiveProjectsTab = new TabActiveProject();
        add("Active Projects", myActiveProjectsTab);
        //Archived Projects Tab
        myArchivedProjectsTab = new TabArchivedProject();
        add("Archived Projects", myArchivedProjectsTab);


    }
}
