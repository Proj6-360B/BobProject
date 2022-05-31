package ViewMain.Components.Tabs;

import InstaDialogue.InstaDialogue;
import Project.Project;
import Project.ProjectManager;
import ViewMain.Components.Tabs.TablePanels.DocumentsTablePanelProjectSpecific;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabDocumentsEditableProjectSpecific extends JPanel implements ActionListener{
    private Project selectedProject;
    private JButton editFileButton;
    private JButton addFileButton;
    private JButton delFileButton;
    private DocumentsTablePanelProjectSpecific table;


    public TabDocumentsEditableProjectSpecific(Project theSelectedProject) {
        selectedProject = theSelectedProject;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Scroll Pane & Table
        table = new DocumentsTablePanelProjectSpecific(theSelectedProject);

        //Search Add/Delete
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

        editFileButton = new JButton("Edit");
        editFileButton.addActionListener(this);

        addFileButton = new JButton("Add");
        addFileButton.addActionListener(this);

        delFileButton = new JButton("Del");
        delFileButton.addActionListener(this);

        searchPanel.add(searchLabel);
        searchPanel.add(searchText);
        searchPanel.add(editFileButton);
        searchPanel.add(addFileButton);
        searchPanel.add(delFileButton);

        //Add
        add(searchPanel);
        add(table);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.add(new TabDocumentsEditableProjectSpecific(new ProjectManager().getProjectList().peek()));
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editFileButton) { //On success, updates table
            try {
                if (!selectedProject.editAttachedFileByOptionPane((String) table.getMyTable().getValueAt(table.getMyTable().getSelectedRow(), 1))) {
                    table.updateTable();
                }
            } catch (Exception e1) {
                InstaDialogue.showErrorMessage("Couldn't edit the File:\n" + e1.getMessage());
            }

        } else if (e.getSource() == addFileButton) {
            if (!selectedProject.addAttachedFileByFileChooser()) {
                table.updateTable();
            }
        } else if (e.getSource() == delFileButton) {
            String afName = (String) table.getMyTable().getValueAt(table.getMyTable().getSelectedRow(), 1);
            if (InstaDialogue.showYesNoConfirmation("Delete " + afName + "?") == 0) {
                selectedProject.deleteAttachedFile(afName);
                table.updateTable();
            }
        }
    }
}
