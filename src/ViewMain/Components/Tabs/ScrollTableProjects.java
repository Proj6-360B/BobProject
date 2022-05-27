package ViewMain.Components.Tabs;

import Project.Status;

import javax.swing.*;
import javax.swing.table.TableColumn;

public class ScrollTableProjects extends JPanel {
    JTable myTable;
    JScrollPane myScrollPane;
    String[] columnNames = {"Status", "Name", "Date", "Reminders"};
    Object[][] data = {
            {Status.ACTIVE, "Garage Lights", "10/20/21", "-Eat the booty like groceries"}
    };

    public ScrollTableProjects() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        myTable = new JTable(data, columnNames);
        formatTableColumnsWidth();
//        myTable.setFillsViewportHeight(true);

        myScrollPane = new JScrollPane(myTable);

        add(myScrollPane);
    }

    private void formatTableColumnsWidth() { //TODO not working
        for (int i = 0; i < columnNames.length; i++) {
            TableColumn column = myTable.getColumnModel().getColumn(i);
            switch (i) { //Status
                case 0:
                    column.setMinWidth(1);
                    column.setPreferredWidth(1);
                    continue;
                case 2:
                    column.setMinWidth(1);
                    column.setPreferredWidth(1);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new ScrollTableProjects());
        frame.setVisible(true);
    }
}
