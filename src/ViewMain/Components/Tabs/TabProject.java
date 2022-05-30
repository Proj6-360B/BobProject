package ViewMain.Components.Tabs;

import Project.ProjectManager;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabProject extends JPanel {
    private ProjectManager myProjectManager;
    //TODO components to fields if other classes need to access.

    public TabProject(ProjectManager theProjectManager) {
        myProjectManager = theProjectManager;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Scroll Pane & Table
        ProjectsTablePanel tableProj = new ProjectsTablePanel(myProjectManager);

        //Create New, Separator, Search
        JPanel searchPanel = new JPanel();
        searchPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20)); //Clamp vertical size
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.LINE_AXIS));

        JButton createNew = new JButton("Create New Project");
        createNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewProject(myProjectManager);
                tableProj.updateTable();
            }
        });

        JLabel searchLabel = new JLabel("      Search: ", SwingConstants.TRAILING);

        JTextField searchText = new JTextField();
        searchText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                ((ProjectsTablePanel) tableProj).setNameSearch("(?i)" + searchText.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                ((ProjectsTablePanel) tableProj).setNameSearch("(?i)" + searchText.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        searchPanel.add(createNew);
        searchPanel.add(searchLabel);
        searchPanel.add(searchText);

        //Add
        add(searchPanel);
        add(tableProj);
    }
}
