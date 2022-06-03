package ViewMain.Components.Tabs;

import Profile.Privilege;
import Profile.ProfileManager;
import Project.ProjectManager;
import ViewMain.Components.Tabs.TablePanels.ProjectsTablePanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabProjects extends JPanel implements ActionListener {
    private ProjectManager myProjectManager;
    private ProfileManager myProfileManager;
    private JButton createNewButton;
    JComboBox searchFilterComboBox;
    private JButton refreshButton;
    private ProjectsTablePanel table;


    public TabProjects(ProjectManager theProjectManager, ProfileManager theProfileManager) {
        myProjectManager = theProjectManager;
        myProfileManager = theProfileManager;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Scroll Pane & Table
        table = new ProjectsTablePanel(myProjectManager, myProfileManager);

        //Create New, Separator, Search
        JPanel searchPanel = new JPanel();
        searchPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20)); //Clamp vertical size
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.LINE_AXIS));

        createNewButton = new JButton("Create New Project");
        createNewButton.addActionListener(this);

        searchFilterComboBox = new JComboBox();
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

        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(this);

        searchPanel.add(createNewButton);
        searchPanel.add(new JLabel("      Search: ", SwingConstants.TRAILING));
        searchPanel.add(searchText);
        searchPanel.add(searchFilterComboBox);
        searchPanel.add(new JLabel("      ", SwingConstants.TRAILING));
        searchPanel.add(refreshButton);

        //Add
        add(searchPanel);
        add(table);

        if(myProfileManager.getSelectedProfile().getPrivilege() == Privilege.GUEST){
            createNewButton.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createNewButton) {
            ProjectCreateEditDialogue frame = new ProjectCreateEditDialogue(myProjectManager, myProfileManager.getSelectedProfile().getPrivilege());
            frame.setVisible(true);
            table.updateTable();
        }
        else if (e.getSource() == refreshButton) {
            myProjectManager.readProjects();
            table.updateTable();
            createNewButton.setEnabled(myProfileManager.getSelectedProfile().getPrivilege() == Privilege.ADMIN);
        }
    }
}
