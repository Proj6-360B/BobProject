package ViewMain.Components.Tabs;

import ViewMain.Components.NewProject;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabActiveProject extends JPanel implements ActionListener {
    private JTable myProjectTable;
    private JPanel projectsPanel;

    public TabActiveProject() {
        projectsPanel = new JPanel();
        BoxLayout boxLayout =new BoxLayout(projectsPanel, BoxLayout.Y_AXIS);
        projectsPanel.setLayout(boxLayout);
        add(projectsPanel);
        JButton addButton = new JButton("Add new Project");
        addButton.setSize(250,25);
        projectsPanel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewProject newProject = new NewProject();
                newProject.addProject();
                addProjectButton(newProject.getName());



            }
        });






        //search bar would go here

        // name date and reminder labels would go here
        initializeProjectTable();
        populateWithProjectButtons();

        //add new button would go here
    }

    public void addProjectButton(String name){
        JButton addProjectButton = new JButton(name);
        addProjectButton.setSize(250,25);
        projectsPanel.add(addProjectButton);


    }

    /**
     * dynamically populates the gui with buttons representing Projects
     * needs to take in stored Projects somehow
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

    @Override
    public void actionPerformed(ActionEvent e) {

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
