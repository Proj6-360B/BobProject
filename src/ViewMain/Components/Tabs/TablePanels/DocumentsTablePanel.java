package ViewMain.Components.Tabs.TablePanels;

import InstaDialogue.InstaDialogue;
import Project.Project;
import Project.AttachedFile;
import Project.ProjectManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class DocumentsTablePanel extends AbstractTablePanel {
    private ProjectManager myProjectManager;
    public final static String[] COLUMN_NAMES = {"Project", "Type", "Name"};

    public DocumentsTablePanel(ProjectManager thePM) {
        super();
        myProjectManager = thePM;
        initPanel(parseProjectList(myProjectManager.getProjectList()), COLUMN_NAMES, new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    try {
                        JTable target = (JTable)me.getSource(); //TODO column is tied, cant reorder header
                        Desktop.getDesktop().open(myProjectManager.getProject((String) getMyTable().getValueAt(target.getSelectedRow(), 0)).getAttachedFile((String) getMyTable().getValueAt(target.getSelectedRow(), 2)).getFile());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Double Click Failed. (Probably because it didn't select anything)");
                    } catch (Exception e) {
                        InstaDialogue.showErrorMessage("Something went wrong when opening the file:\n" + e.getMessage());
                    }
                }
            }
        });
    }

    private Object[][] parseProjectList(LinkedList<Project> theProjectList) {
        //get count
        int count = 0;
        for (Project p: theProjectList) {
            count += p.getAttachedFilesList().size();
        }
        //parse
        Object result[][] = new Object[count][COLUMN_NAMES.length];
        int i = 0;
        for (Project p: theProjectList) {
            for (AttachedFile af: p.getAttachedFilesList()) {
                for (int j = 0; j < COLUMN_NAMES.length; j++) {
                    switch (j) {
                        case 0: //Project
                            result[i][j] = p.getProjectName();
                            break;
                        case 1: //Type
                            result[i][j] = af.getType();
                            break;
                        case 2: //Name
                            result[i][j] = af.getName();
                            break;
                    }
                }
                i++;
            }
        }
        return result;
    }

    public void search(String theString) {
        search(theString, 2);
    }

    @Override
    public void formatTableColumnsWidth() {
        for (int i = 0; i < COLUMN_NAMES.length; i++) {
            TableColumn column = getMyTable().getColumnModel().getColumn(i);
            switch (i) {
                case 1: //Type
                    column.setMinWidth(50);
                    column.setMaxWidth(50);
                    column.setResizable(false);
                    break;
            }
        }
    }

    @Override
    public void updateTable() {
        System.out.println("Updating Table...");
        DefaultTableModel model = (DefaultTableModel) getMyTable().getModel();
        for (int i = 0; i < getMyTable().getRowCount();) {
            model.removeRow(i);
        }
        for (Project p: myProjectManager.getProjectList()) {
            for (AttachedFile af: p.getAttachedFilesList()) {
                model.addRow(new Object[]{p.getProjectName(), af.getType(), af.getName()});
            }
        }
    }
}
