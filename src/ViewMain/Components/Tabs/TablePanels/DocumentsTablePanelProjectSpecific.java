package ViewMain.Components.Tabs.TablePanels;

import InstaDialogue.InstaDialogue;
import Project.AttachedFile;
import Project.Project;
import ViewMain.Components.Tabs.TablePanels.AbstractTablePanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

/**
 * Panel that holds table to display AttachedFiles of a specific Project for editing/viewing.
 * @author David Huynh
 */
public class DocumentsTablePanelProjectSpecific extends AbstractTablePanel {
    /**
     * Project to view/edit AttachedFile.
     */
    private Project selectedProject;
    /**
     * Column header names.
     */
    private final static String[] columnNames = {"Type", "Name"};

    /**
     * Constructor
     * @author David Huynh
     * @param theSelectedProject Project to view/edit AttachedFile.
     */
    public DocumentsTablePanelProjectSpecific(Project theSelectedProject) {
        super();
        selectedProject = theSelectedProject;
        initPanel(parseFiles(selectedProject.getAttachedFilesList()), columnNames, new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    try {
                        JTable target = (JTable) me.getSource(); //TODO column 1 is tied, cant reorder header
                        Desktop.getDesktop().open(selectedProject.getAttachedFile((String) getMyTable().getValueAt(target.getSelectedRow(), 1)).getFile());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Double Click Failed. (Probably because it didn't select anything)");
                    } catch (Exception e) {
                        InstaDialogue.showErrorMessage("Something went wrong when opening the file:\n" + e.getMessage());
                    }
                }
            }
        });
    }

    /**
     * Default search. Targets file name.
     * @author David Huynh
     * @param theString to search for.
     */
    public void search(String theString) {
        search(theString, -1); //Defaults to all
    }

    /**
     * Helper to parse AttachedFiles to table entries.
     * @author David Huynh
     * @param theAttachedFiles AttachedFile list from selected Project
     * @return Row entries
     */
    private Object[][] parseFiles(LinkedList<AttachedFile> theAttachedFiles) {
        Object result[][] = new Object[theAttachedFiles.size()][columnNames.length];
        int i = 0;
        for (AttachedFile af : theAttachedFiles) {
            for (int j = 0; j < columnNames.length; j++) {
                switch (j) {
                    case 0: //Project
                        result[i][j] = af.getType();
                        break;
                    case 1: //Type
                        result[i][j] = af.getName();
                }
            }
            i++;
        }
        return result;
    }

    /**
     * Format width of header columns.
     * @author David Huynh
     */
    @Override
    public void formatTableColumnsWidth() {
//        for (int i = 0; i < columnNames.length; i++) {
//            TableColumn column = getMyTable().getColumnModel().getColumn(i);
//            switch (i) {
//                case 0: //Type
//                    column.setMinWidth(50);
//                    column.setMaxWidth(50);
//                    column.setResizable(false);
//                    break;
//            }
//        }
    }

    /**
     * Call this to update the table entries.
     * @author David Huynh
     */
    @Override
    public void updateTable() {
        System.out.println("Updating Table...");
        DefaultTableModel model = (DefaultTableModel) getMyTable().getModel();
        for (int i = 0; i < getMyTable().getRowCount();) {
            model.removeRow(i);
        }
        for (AttachedFile af: selectedProject.getAttachedFilesList()) {
            model.addRow(new Object[]{af.getType(), af.getName()});
        }
    }
}
