package ViewMain.Components.Tabs;

import Project.Status;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * https://www.tutorialspoint.com/how-can-we-detect-the-double-click-events-of-a-jtable-row-in-java#:~:text=We%20can%20detect%20the%20double,click%20events%20of%20a%20JTable. <br>
 * https://github.com/hrehfeld/QuakeInjector
 */
public class ScrollTableProjects extends JPanel {
    JTable myTable;
    JScrollPane myScrollPane;
    String[] columnNames = {"Status", "Name", "Date", "Reminders"};
    Object[][] data = { //TODO ProjectManger pass its List, and this will parse it to table.
            {Status.ACTIVE, "Garage Lights", "2021/10/11", "-Eat the booty like groceries"},
            {Status.ACTIVE, "Bruh Project", "2021/10/12", "-Eat the booty like groceries"},
            {Status.COMPLETE, "Aids", "2020/10/12", "-Eat the booty like groceries"}
    };

    public ScrollTableProjects() {
//        setLayout(new GridBagLayout()); //TODO BoxLayout wont let column width work
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        myTable = new JTable(data, columnNames) {
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
//        add(myScrollPane, new GridBagConstraints() {
//            {
//                anchor = CENTER;
//                fill = BOTH;
//                gridx = 0;
//                gridy = 1;
//                gridwidth = 1;
//                gridheight = 1;
//                weightx = 1;
//                weighty = 1;
//            }
//        });
    }

    private void formatTableColumnsWidth() { //TODO not working because BoxLayout
        for (int i = 0; i < columnNames.length; i++) {
            TableColumn column = myTable.getColumnModel().getColumn(i);
            switch (i) {
                case 0: //Status
                    column.setMinWidth(70);
                    column.setMaxWidth(70);
                    column.setResizable(false);
                    break;
//                case 1: //Name
////                    column.setMinWidth(10);
////                    column.setPreferredWidth(50);
//                    break;
                case 2: //Date
                    column.setMinWidth(75);
                    column.setMaxWidth(75);
                    column.setResizable(false);
                    break;
//                case 3: //Reminders
////                    column.setMinWidth(10);
////                    column.setPreferredWidth(100);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new ScrollTableProjects());
        frame.setVisible(true);
    }
}
