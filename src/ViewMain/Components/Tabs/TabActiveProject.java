package ViewMain.Components.Tabs;

import Project.ProjectManager;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TabActiveProject extends JPanel {
    private ProjectManager myProjectManager;
    //TODO components to fields if other classes need to access.

    public TabActiveProject(ProjectManager theProjectManager) {
        myProjectManager = theProjectManager;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Scroll Pane & Table
        JPanel tableProj = new TableProjectsPanel(myProjectManager);

        //Create New, Separator, Search
        JPanel searchPanel = new JPanel();
        searchPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20)); //Clamp vertical size
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.LINE_AXIS));

        JButton createNew = new JButton("Create New Project");
        createNew.addActionListener(e -> new NewProject(myProjectManager));
        searchPanel.add(createNew);

        JLabel searchLabel = new JLabel("      Search Name: ", SwingConstants.TRAILING);
        searchPanel.add(searchLabel);

        JTextField searchText = new JTextField();
        searchText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                ((TableProjectsPanel) tableProj).setNameSearch("(?i)" + searchText.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                ((TableProjectsPanel) tableProj).setNameSearch("(?i)" + searchText.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        searchPanel.add(searchText);

        //Add
        add(searchPanel);
        add(tableProj);
    }
}
