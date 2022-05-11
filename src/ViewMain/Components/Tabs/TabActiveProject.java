package ViewMain.Components.Tabs;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public class TabActiveProject extends JPanel {
    private JTable myProjectTable;
    public TabActiveProject() {

        //search bar would go here

        // name date and reminder labels would go here
        initializeProjectTable();
        populateWithProjectButtons();

        //add new button would go here
    }

    /**
     * dynamically populates the gui with buttons representing projects
     * needs to take in stored projects somehow
     */
    private void populateWithProjectButtons() {
        //todo
    }

    //TODO Table, how the heck do u use this? https://docs.oracle.com/javase/tutorial/uiswing/components/table.html
    private void initializeProjectTable() {
        myProjectTable = new JTable();
        TableColumn nameColumn = new TableColumn();
        nameColumn.setHeaderValue("Name");
        myProjectTable.addColumn(nameColumn);
        add(myProjectTable);
    }
    //TODO Table, how the heck do u use this?
    class ActiveTableModel extends AbstractTableModel {

        @Override
        public int getRowCount() {
            return 0;
        }

        @Override
        public int getColumnCount() {
            return 0;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return null;
        }
    }


}
