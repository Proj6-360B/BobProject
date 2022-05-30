package ViewMain.Components.Tabs;

import Project.Project;
import Project.ProjectManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public abstract class AbstractTableTab extends JPanel {
    private JTable myTable;
    private JScrollPane myScrollPane;

    public AbstractTableTab() {
        //Nothing Call initPanel() right after!
    }

    public void initPanel(Object[][] theRows, String[] theColumnNames) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        myTable = new JTable(new DefaultTableModel(theRows, theColumnNames)) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) { //TODO column 1 is tied to Name, cant reorder header
                return false;
            }
        };
        myTable.getTableHeader().setReorderingAllowed(false); //TODO column 1 is tied to Name, cant reorder header
        myTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {     // to detect double click events
                    JTable target = (JTable)me.getSource(); //TODO column 1 is tied to Name, cant reorder header
                    JOptionPane.showMessageDialog(null, myTable.getValueAt(target.getSelectedRow(), 1)); //TODO Display Project from this event.
                }
            }
        });
        myTable.setPreferredScrollableViewportSize(new Dimension(500, 500));
        myTable.setColumnSelectionAllowed(false);
        myTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        myTable.setFillsViewportHeight(true);
        myTable.setShowGrid(true);
        myTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(myTable.getModel());
        myTable.setRowSorter(rowSorter);

        formatTableColumnsWidth();

        myScrollPane = new JScrollPane(myTable);
        add(myScrollPane);
    }

    public void search(String theString, int theRowToFilter) {
        ((TableRowSorter) myTable.getRowSorter()).setRowFilter(RowFilter.regexFilter(theString, theRowToFilter));
    }

    public abstract void formatTableColumnsWidth();

    public abstract void updateTable(); //https://stackoverflow.com/questions/3549206/how-to-add-row-in-jtable

    public JTable getMyTable() {
        return myTable;
    }

    public void setMyTable(JTable myTable) {
        this.myTable = myTable;
    }

    public JScrollPane getMyScrollPane() {
        return myScrollPane;
    }

    public void setMyScrollPane(JScrollPane myScrollPane) {
        this.myScrollPane = myScrollPane;
    }
}
