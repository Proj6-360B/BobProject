package Project;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.LinkedList;

import static AppData.AppDataIO.FILENAME_APPDATA;
import static FileChooserHelper.FileChooserHelper.PATH_FILECHOOSER_START;
import static FileChooserHelper.FileChooserHelper.showErrorMessageInOptionPane;

public class Projects {
    private String projectName;
    private String projectType;
    private Status projectStatus;
    private LinkedList<File> attachedFiles;

    public Projects(String theProjectName, String theProjectType, Status theStatus) {
        setProjectName(theProjectName);
        setProjectType(theProjectType);
        setProjectStatus(theStatus);
        setAttachedFiles(new LinkedList<File>());
    }

    public Projects(String theProjectName, String theProjectType, Status theStatus, LinkedList<File> theAttachedFiles) {
        setProjectName(theProjectName);
        setProjectType(theProjectType);
        setProjectStatus(theStatus);
        setAttachedFiles(theAttachedFiles);
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public Status getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Status projectStatus) {
        this.projectStatus = projectStatus;
    }

    public LinkedList<File> getAttachedFiles() {
        return attachedFiles;
    }

    public void setAttachedFiles(LinkedList<File> attachedFiles) {
        this.attachedFiles = attachedFiles;
    }

    /**
     * Add File and return reference to this File list.
     * @param theFile File to add.
     * @return Reference to this attachedFile list.
     */
    public LinkedList<File> addFile(File theFile) throws IllegalArgumentException {
        if (theFile == null || !theFile.exists()) {
            throw new IllegalArgumentException(theFile + " is not a valid file.");
        }
        attachedFiles.add(theFile);
        return attachedFiles;
    }

    /**
     * Prompts user, add the File, and return reference to this File list.
     * @return Reference to this attachedFile list.
     */
    public LinkedList<File> addFileByFileChooser() {
        JFileChooser fc = new JFileChooser(PATH_FILECHOOSER_START);
        fc.setDialogTitle("Select any file to attach...");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int fcReturn = fc.showOpenDialog(null);
        while (fcReturn != JFileChooser.APPROVE_OPTION) { //Try again
            if (fcReturn == JFileChooser.CANCEL_OPTION) return attachedFiles; //Cancels
            fcReturn = fc.showOpenDialog(null);
        }

        //After user chose file
        try {
            return addFile(fc.getSelectedFile());
        } catch (Exception e) {
            showErrorMessageInOptionPane(e.getMessage());
        }
        return attachedFiles;
    }

    /**
     * Delete File from this attachedFile list.
     * @param theFile File find and delete.
     * @return If delete was successful.
     */
    public boolean deleteFile(File theFile) {
        if (theFile == null || !theFile.exists()) {
            throw new IllegalArgumentException(theFile + " is not a valid file.");
        }
        return attachedFiles.remove(theFile);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(projectName);
        sb.append(" (");
        sb.append(projectType);
        sb.append(')');
        return sb.toString();
    }
}
