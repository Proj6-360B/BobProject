package Project;

import javax.swing.*;
import java.io.File;
import java.util.LinkedList;

import static FileChooserHelper.FileChooserHelper.PATH_FILECHOOSER_START;
import static FileChooserHelper.FileChooserHelper.showErrorMessageInOptionPane;

public class Projects {
    private String projectName;
    private String projectType;
    private Status projectStatus;
//    private File myWarranty;
    private Date date;
    private LinkedList<AttachedFile> attachedFilesList;

    public Projects(String theProjectName, String theProjectType, Status theStatus, Date theDate, LinkedList<AttachedFile> theAttachedFiles) {
        setProjectName(theProjectName);
        setProjectType(theProjectType);
        setProjectStatus(theStatus);
        setDate(theDate);
        setAttachedFilesList(theAttachedFiles);
    }

    public Projects(String theProjectName, String theProjectType, Status theStatus, Date theDate) {
        new Projects(theProjectName, theProjectType, theStatus, theDate, new LinkedList<AttachedFile>());
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LinkedList<AttachedFile> getAttachedFilesList() {
        return attachedFilesList;
    }

    public void setAttachedFilesList(LinkedList<AttachedFile> attachedFilesList) {
        this.attachedFilesList = attachedFilesList;
    }

    /**
     * Add File and return reference to this File list.
     * @param theFile File to add.
     * @return Reference to this attachedFile list.
     */
    public void addFile(File theFile, String theString) throws IllegalArgumentException {
        attachedFilesList.add(new AttachedFile(theFile, theString));
    }

    /**
     * Prompts user, add the File, and return reference to this File list.
     * @return Reference to this attachedFile list.
     */
    public void addFileByFileChooser() {
        JFileChooser fc = new JFileChooser(PATH_FILECHOOSER_START);
        fc.setDialogTitle("Select any file to attach...");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int fcReturn = fc.showOpenDialog(null);
        while (fcReturn != JFileChooser.APPROVE_OPTION) { //Try again
            if (fcReturn == JFileChooser.CANCEL_OPTION) return;
            fcReturn = fc.showOpenDialog(null);
        }
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
        return attachedFilesList.remove(theFile);
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
