package ViewMain.Components.Tabs;

import ViewMain.Components.NewProject;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabActiveProject extends JPanel implements ActionListener {
    private JTable myProjectTable;
    private JPanel projectsPanel;

    public TabActiveProject() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        //Search & Create New
//        JPanel searchPanel = new JPanel();
//        JButton createNew = new JButton("Create New");
//        searchPanel.add(createNew);
//        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.LINE_AXIS));
//        JLabel searchText = new JLabel("Search Name: ", SwingConstants.TRAILING);
//        searchPanel.add(searchText);

        //Scroll Pane & Table
        add(new ScrollTableProjects());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    //TODO Table, how the heck do u use this?
    //https://docs.oracle.com/javase/tutorial/uiswing/components/table.html#data
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
