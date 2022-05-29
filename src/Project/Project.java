package Project;

import AppData.SerializeIO;
import FileChooserHelper.FileChooserHelper;

import javax.swing.*;
import java.io.*;
import java.util.LinkedList;

import static FileChooserHelper.FileChooserHelper.PATH_FILECHOOSER_START;
import static FileChooserHelper.FileChooserHelper.showErrorMessage;

public class Project implements Serializable {
    private static String PATH = "./appdata/projects";
    private String projectName;
    private String projectDescription;
    private String projectType;
    private Status projectStatus;
//    private File myWarranty;
    private Date date;
    private LinkedList<AttachedFile> attachedFilesList;

    /**
     * Constructor for Project. //TODO @params
     */
    public Project(String theProjectName, String theProjectDescription, String theProjectType, Status theStatus, Date theDate, LinkedList<AttachedFile> theAttachedFiles) {
        setProjectName(theProjectName);
        setProjectDescription(theProjectDescription);
        setProjectType(theProjectType);
        setProjectStatus(theStatus);
        setDate(theDate);
        setAttachedFilesList(theAttachedFiles);
    }

    /**
     * Constructor for Project without files. //TODO @params
     */
    public Project(String theProjectName, String theProjectDescription, String theProjectType, Status theStatus, Date theDate) {
        new Project(theProjectName, theProjectDescription, theProjectType, theStatus, theDate, new LinkedList<AttachedFile>());
    }

    public String getProjectName() {
        return projectName;
    }

    private void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
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

    public void renameProject(String theName) throws IOException {

    }

    /**
     * Add File and return reference to this File list.
     * @param theFile File to add.
     * @return Reference to this attachedFile list.
     */
    public void addFile(File theFile, String theString) throws IllegalArgumentException {
        try {
            attachedFilesList.add(new AttachedFile(PATH + '/' + getFormattedName(), theFile, theString));
        } catch (IOException e) {
            FileChooserHelper.showErrorMessage("Couldn't save file.\n" + e.getMessage());
        }
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

    public void writeProject() throws IOException {
        //mkdir
        File projectDir = new File(PATH + '/' + getFormattedName());
        if (!projectDir.exists()) projectDir.mkdir();
        //itself serialized
        SerializeIO.serializeObjectToHere(this, projectDir.getAbsolutePath() + '/' + getFormattedName() + ".pser");
        //attachedFiles
        for (AttachedFile af: attachedFilesList) {
            af.writeFile(projectDir.getAbsolutePath());
        }
    }

    /**
     * Returns folder & serialized file name of this project.
     * @return folder & serialized file name of this project.
     */
    private String getFormattedName() {
        return projectName.trim().replaceAll(" ", "_").replaceAll(".", "");
    }

    /**
     * Delete files (have Manager call this before remove from list) <br>
     * https://www.baeldung.com/java-delete-directory
     */
    public void delete() {
        File directoryToBeDeleted = new File(PATH + '/' + getFormattedName());
        if (!directoryToBeDeleted.exists()) return; //not exist already
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                file.delete();
            }
        }
        directoryToBeDeleted.delete();
    }
}
