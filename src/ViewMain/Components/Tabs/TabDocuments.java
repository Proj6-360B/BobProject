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

        JLabel searchLabel = new JLabel("Search: ", SwingConstants.TRAILING);

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

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {
            myProjectManager.readProjects();
            table.updateTable();
        });

        searchPanel.add(searchLabel);
        searchPanel.add(searchText);
        searchPanel.add(refreshButton);

        //Add
        add(searchPanel);
        add(table);
    }
}
