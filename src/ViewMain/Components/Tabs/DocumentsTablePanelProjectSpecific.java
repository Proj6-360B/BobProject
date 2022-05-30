package ViewMain.Components.Tabs;

import InstaDialogue.InstaDialogue;
import Project.AttachedFile;
import Project.Project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class DocumentsTablePanelProjectSpecific extends AbstractTablePanel {
    private final static String[] columnNames = {"Type", "Name"};
    private Project selectedProject;

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

    public void search(String theString) {
        search(theString, 1);
    }

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
