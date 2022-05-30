package ViewMain.Components.Tabs;

import Project.ProjectManager;
import Project.Project;
import Project.Status;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Locale;

/**
 * https://www.tutorialspoint.com/how-can-we-detect-the-double-click-events-of-a-jtable-row-in-java#:~:text=We%20can%20detect%20the%20double,click%20events%20of%20a%20JTable. <br>
 * https://github.com/hrehfeld/QuakeInjector
 */
public class TableProjectsPanel extends JPanel {
    private ProjectManager myProjectManager;
    private JTable myTable;
    private JScrollPane myScrollPane;
    private final String[] columnNames = {"Status", "Type", "Name", "Date", "Description"};
//    Object[][] data = { //TODO ProjectManger pass its List, and this will parse it to table.
//            {Status.ACTIVE, "Repair", "Garage Lights", "2021/10/11", "Eat the booty like groceries"},
//    };

    public TableProjectsPanel(ProjectManager thePM) {
        myProjectManager = thePM;
//        setLayout(new GridBagLayout());
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        myTable = new JTable(parseProjectList(myProjectManager.getProjectList()), columnNames) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) { //TODO column 1 is tied to Name, cant reorder header
                return false;
            }
        };
        myTable.getTableHeader().setReorderingAllowed(false);
        myTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {     // to detect double click events
                    JTable target = (JTable)me.getSource(); //TODO column 1 is tied to Name, cant reorder header
                    JOptionPane.showMessageDialog(null, myTable.getValueAt(target.getSelectedRow(), 1)); //TODO Display Project from this event. Search via name (because it gives you String)?
                }
            }
        });
        formatTableColumnsWidth();
        myTable.setPreferredScrollableViewportSize(new Dimension(500, 500));
        myTable.setColumnSelectionAllowed(false);
        myTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        myTable.setFillsViewportHeight(true);
        myTable.setShowGrid(true);
        myTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(myTable.getModel());
        myTable.setRowSorter(rowSorter);

        myScrollPane = new JScrollPane(myTable);
        add(myScrollPane);
    }

    public void updateProjectList(Object[][] data) {
        //TODO
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

    private void formatTableColumnsWidth() {
        for (int i = 0; i < columnNames.length; i++) {
            TableColumn column = myTable.getColumnModel().getColumn(i);
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
                    column.setMinWidth(75);
                    column.setMaxWidth(75);
                    column.setResizable(false);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new TableProjectsPanel(new ProjectManager()));
        frame.setVisible(true);
    }

    public void setNameSearch(String theName) {
        ((TableRowSorter) myTable.getRowSorter()).setRowFilter(RowFilter.regexFilter(theName)); //TODO rn, it searches every column. Limit or advance search?
    }
}
