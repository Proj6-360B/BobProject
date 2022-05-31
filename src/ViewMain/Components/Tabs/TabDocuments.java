package ViewMain.Components.Tabs;

import Project.ProjectManager;
import ViewMain.Components.Tabs.TablePanels.DocumentsTablePanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class TabDocuments extends JPanel {
    private ProjectManager myProjectManager;

    public TabDocuments(ProjectManager theProjectManager) {
        myProjectManager = theProjectManager;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Scroll Pane & Table
        DocumentsTablePanel table = new DocumentsTablePanel(myProjectManager);

        //Search
        JPanel searchPanel = new JPanel();
        searchPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20)); //Clamp vertical size
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.LINE_AXIS));

        JComboBox searchFilterComboBox = new JComboBox();
        searchFilterComboBox.addItem("No Filter");
        for (String filter: table.COLUMN_NAMES) {
            searchFilterComboBox.addItem(filter);
        }

        JTextField searchText = new JTextField();
        searchText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    table.search("(?i)" + searchText.getText(), searchFilterComboBox.getSelectedIndex() - 1);
                } catch (Exception e1) {
                    //Suppress
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    table.search("(?i)" + searchText.getText(), searchFilterComboBox.getSelectedIndex() - 1);
                } catch (Exception e1) {
                    //Suppress
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //Nothing
            }
        });

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {
            myProjectManager.readProjects();
            table.updateTable();
        });

        searchPanel.add(new JLabel("Search: ", SwingConstants.TRAILING));
        searchPanel.add(searchText);
        searchPanel.add(searchFilterComboBox);
        searchPanel.add(new JLabel("      ", SwingConstants.TRAILING));
        searchPanel.add(refreshButton);

        //Add
        add(searchPanel);
        add(table);
    }
}
