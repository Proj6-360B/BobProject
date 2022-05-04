package Main.GUIComponents;

import Main.GUIComponents.Tabs.*;

import javax.swing.*;

public class CompTabbedPane extends JTabbedPane {
    //Tabs
    private JPanel myActiveProjectsTab;
    private JPanel myArchivedProjectsTab;

    public CompTabbedPane() {
        //Create Fields
        myActiveProjectsTab = new TabActiveProject();
        myArchivedProjectsTab = new TabArchivedProject();

        //Add to this
        add("Active Projects", myActiveProjectsTab);
        add("Archived Projects", myArchivedProjectsTab);
    }
}
