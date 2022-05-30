package ViewMain.Components.Tabs;

import Project.ProjectManager;
import Project.Project;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.util.LinkedList;

/**
 * https://www.tutorialspoint.com/how-can-we-detect-the-double-click-events-of-a-jtable-row-in-java#:~:text=We%20can%20detect%20the%20double,click%20events%20of%20a%20JTable. <br>
 * https://github.com/hrehfeld/QuakeInjector
 */
public class TableProjectsPanel extends AbstractTableTab {
    private ProjectManager myProjectManager;
    private final static String[] columnNames = {"Status", "Type", "Name", "Date", "Description"};

    public TableProjectsPanel(ProjectManager thePM) {
        super();
        myProjectManager = thePM;
        initPanel(parseProjectList(myProjectManager.getProjectList()), columnNames);
    }

    private Object[][] parseProjectList(LinkedList<Project> theProjectList) { //"Status", "Type", "Name", "Date", "Description"
        Object result[][] = new Object[theProjectList.size()][columnNames.length];
        int i = 0;
        for (Project p: theProjectList) {
            for (int j = 0; j < columnNames.length; j++) {
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

    public void formatTableColumnsWidth() {
        for (int i = 0; i < columnNames.length; i++) {
            TableColumn column = getMyTable().getColumnModel().getColumn(i);
            switch (i) {
                case 0: //Status
                    column.setMinWidth(70);
                    column.setMaxWidth(70);
                    column.setResizable(false);
                    break;
                case 1: //Type
                    column.setMinWidth(50);
                    column.setMaxWidth(50);
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

    public void setNameSearch(String theName) {
        search(theName, 2); //TODO rn, it searches every column. Limit or advance search?
    }

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
//        frame.add(new TableProjectsPanel(new ProjectManager()));
//        frame.setVisible(true);
//    }
}
