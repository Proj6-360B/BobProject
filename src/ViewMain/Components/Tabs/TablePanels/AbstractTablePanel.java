package ViewMain.Components.Tabs.TablePanels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;

/**
 * Panel that has a table for tabs.
 * @author David Huynh
 */
public abstract class AbstractTablePanel extends JPanel {
    /**
     * Table
     */
    private JTable myTable;
    /**
     * ScrollPane that holds table
     */
    private JScrollPane myScrollPane;

    /**
     * Constructor (Call initPanel() right after in implementation!)
     */
    public AbstractTablePanel() {
        //Nothing due to how JTables create models. Call initPanel() right after!
    }

    /**
     * Create a JTable in a ScrollPane from given rows/entries, column header names, mouse adapter for action events.
     * @author David Huynh
     * @param theRows rows/entries
     * @param theColumnNames column header names
     * @param theMouseAdapter mouse adapter for action events
     */
    public void initPanel(Object[][] theRows, String[] theColumnNames, MouseAdapter theMouseAdapter) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        myTable = new JTable(new DefaultTableModel(theRows, theColumnNames)) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) { //TODO column 1 is tied to Name, cant reorder header
                return false;
            }
        };
        myTable.getTableHeader().setReorderingAllowed(false); //TODO column 1 is tied to Name, cant reorder header
        myTable.addMouseListener(theMouseAdapter);
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

    /**
     * Search rows. If theColumnToFilter < 0, searches all columns.
     * @author David Huynh
     * @param theString String to search
     * @param theColumnToFilter If theColumnToFilter < 0, searches all columns.
     */
    public void search(String theString, int theColumnToFilter) {
        if (theColumnToFilter < 0) {
            ((TableRowSorter) myTable.getRowSorter()).setRowFilter(RowFilter.regexFilter(theString));
        } else {
            ((TableRowSorter) myTable.getRowSorter()).setRowFilter(RowFilter.regexFilter(theString, theColumnToFilter));
        }
    }

    /**
     * Formats the table column widths.
     * @author David Huynh
     */
    public abstract void formatTableColumnsWidth();

    /**
     * Update table by rereading list for rows and updates entries
     * @author David Huynh
     */
    public abstract void updateTable(); //https://stackoverflow.com/questions/3549206/how-to-add-row-in-jtable

    /**
     * Get the table.
     * @author David Huynh
     * @return the table
     */
    public JTable getMyTable() {
        return myTable;
    }

//    public void setMyTable(JTable myTable) {
//        this.myTable = myTable;
//    }
//
//    public JScrollPane getMyScrollPane() {
//        return myScrollPane;
//    }
//
//    public void setMyScrollPane(JScrollPane myScrollPane) {
//        this.myScrollPane = myScrollPane;
//    }
}
