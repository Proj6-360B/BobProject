package ViewMain.Components.Tabs;

import Project.ProjectManager;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabProjects extends JPanel {
    private ProjectManager myProjectManager;

    public TabProjects(ProjectManager theProjectManager) {
        myProjectManager = theProjectManager;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Scroll Pane & Table
        ProjectsTablePanel table = new ProjectsTablePanel(myProjectManager);

        //Create New, Separator, Search
        JPanel searchPanel = new JPanel();
        searchPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20)); //Clamp vertical size
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.LINE_AXIS));

        JButton createNew = new JButton("Create New Project");
        createNew.addActionListener(e -> {
            new NewProject(myProjectManager);
            table.updateTable();
        });

        JLabel searchLabel = new JLabel("      Search: ", SwingConstants.TRAILING);

        JTextField searchText = new JTextField();
        searchText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                table.search("(?i)" + searchText.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                table.search("(?i)" + searchText.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //Nothing
            }
        });

        searchPanel.add(createNew);
        searchPanel.add(searchLabel);
        searchPanel.add(searchText);

        //Add
        add(searchPanel);
        add(table);
    }
}
