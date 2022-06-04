package ViewMain.Components.Tabs.TablePanels;

import Profile.ProfileManager;
import Project.ProjectManager;
import Project.Project;
import ViewMain.Components.Tabs.ProjectCreateEditDialogue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

/**
 * Panel that holds table to display all Projects.
 * https://www.tutorialspoint.com/how-can-we-detect-the-double-click-events-of-a-jtable-row-in-java#:~:text=We%20can%20detect%20the%20double,click%20events%20of%20a%20JTable. <br>
 * https://github.com/hrehfeld/QuakeInjector
 * @author David Huynh
 */
public class ProjectsTablePanel extends AbstractTablePanel {
    /**
     * ProjectManager from main
     */
    private ProjectManager myProjectManager;
    /**
     * ProfileManager from main
     */
    private ProfileManager myProfileManager;
    /**
     * Column header names.
     */
    public final static String[] COLUMN_NAMES = {"Status", "Type", "Name", "Date", "Description"};

    /**
     * Constructor.
     * @author David Huynh
     * @param theProjectManager ProjectManager from main
     * @param theProfileManager ProfileManager from main
     */
    public ProjectsTablePanel(ProjectManager theProjectManager, ProfileManager theProfileManager) {
        super();
        myProjectManager = theProjectManager;
        myProfileManager = theProfileManager;
        initPanel(parseProjectList(myProjectManager.getProjectList()), COLUMN_NAMES, new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    try {
                        JTable target = (JTable)me.getSource();
                        String projectName = (String) getMyTable().getValueAt(target.getSelectedRow(), 2);
                        JDialog frame = new ProjectCreateEditDialogue(myProjectManager, myProjectManager.getProject(projectName), myProfileManager.getSelectedProfile().getPrivilege());
                        frame.setVisible(true);
                        updateTable();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Double Click Failed. (Probably because it didn't select anything)");
                    } //other catches for opening Project
                }
            }
        });
    }

    /**
     * Helper to parse Projects and their AttachedFiles to table entries.
     * @author David Huynh
     * @param theProjectList Project list from ProjectManager
     * @return Row entries
     */
    private Object[][] parseProjectList(LinkedList<Project> theProjectList) { //"Status", "Type", "Name", "Date", "Description"
        Object result[][] = new Object[theProjectList.size()][COLUMN_NAMES.length];
        int i = 0;
        for (Project p: theProjectList) {
            for (int j = 0; j < COLUMN_NAMES.length; j++) {
                switch (j) {
                    case 0: //Status
                        result[i][j] = p.getProjectStatus();
                        break;
                    case 1: //Type
                        result[i][j] = p.getProjectType();
                        break;
                    case 2: //Name
                        result[i][j] = p.getProjectName();
                        break;
                    case 3: //Date
                        result[i][j] = p.getProjectDate();
                        break;
                    case 4: //Description
                        result[i][j] = p.getProjectDescription();
                        break;
                }
            }
            i++;
        }
        return result;
    }

    /**
     * Default search. Targets name column.
     * @author David Huynh
     * @param theName to search for
     */
    public void search(String theName) {
        search(theName, 2); //Defaults to name
    }

    /**
     * Format width of header columns.
     * @author David Huynh
     */
    @Override
    public void formatTableColumnsWidth() {
        for (int i = 0; i < COLUMN_NAMES.length; i++) {
            TableColumn column = getMyTable().getColumnModel().getColumn(i);
            switch (i) {
                case 0: //Status
                    column.setMinWidth(70);
                    column.setMaxWidth(70);
                    column.setResizable(false);
                    break;
                case 1: //Type
                    column.setMinWidth(70);
                    column.setMaxWidth(70);
                    column.setResizable(false);
                    break;
                case 3: //Date
                    column.setMinWidth(65);
                    column.setMaxWidth(65);
                    column.setResizable(false);
                    break;
            }
        }
    }

    /**
     * Call this to update the table entries.
     * @author David Huynh
     */
    @Override
    public void updateTable() { //https://stackoverflow.com/questions/3549206/how-to-add-row-in-jtable
        System.out.println("Updating Table...");
        DefaultTableModel model = (DefaultTableModel) getMyTable().getModel();
        for (int i = 0; i < getMyTable().getRowCount();) {
            model.removeRow(i);
        }
        for (Project p: myProjectManager.getProjectList()) {
            model.addRow(new Object[]{p.getProjectStatus(), p.getProjectType(), p.getProjectName(), p.getProjectDate(), p.getProjectDescription()});
        }
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame();
//        frame.add(new ProjectsTablePanel(new ProjectManager()));
//        frame.setVisible(true);
//    }
}
