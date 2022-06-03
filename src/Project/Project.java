package Project;

import AppData.AppDataIO;
import AppData.SerializeIO;
import InstaDialogue.InstaDialogue;

import javax.swing.*;
import java.io.*;
import java.util.*;

import static InstaDialogue.InstaDialogue.PATH_FILECHOOSER_START;

/**
 * The Project entities. Stores name, descrption, date, type, status, and a list of AttachedFiles <br>
 * Send this "PATH + '/' + getFormattedName()" to AttachedFile because it doesn't know where it is saved in.
 * @see AttachedFile
 * @author David Huynh
 */
public class Project implements Serializable {
    /**
     * serialVersionUID so it doesnt auto generate and it farts during deserialize.
     */
    private static final long serialVersionUID = 91021L;

    private static String PROJECT_PATH = "appdata/projects";
    /**
     * Name of the project.
     */
    private String projectName;
    /**
     * Long description text for project, like a notepad.
     */
    private String projectDescription;
    /**
     * ie Repair, Installation,
     */
    private String projectType;
    /**
     * Enum of Status.
     */
    private Status projectStatus;
    /**
     * Project's Date
     */
    private Date projectDate;
    /**
     * List of Attached Files.
     */
    private LinkedList<AttachedFile> attachedFilesList;

    /**
     * Constructor for Project. //TODO @params
     */
    public Project(String theProjectName, String theProjectDescription, String theProjectType, Status theStatus, Date theDate, LinkedList<AttachedFile> theAttachedFiles) {
        setProjectName(theProjectName);
        setProjectDescription(theProjectDescription);
        setProjectType(theProjectType);
        setProjectStatus(theStatus);
        setProjectDate(theDate);
        setAttachedFilesList(theAttachedFiles);
    }

    /**
     * Constructor for Project without files. //TODO @params
     */
    public Project(String theProjectName, String theProjectDescription, String theProjectType, Status theStatus, Date theDate) {
        setProjectName(theProjectName);
        setProjectDescription(theProjectDescription);
        setProjectType(theProjectType);
        setProjectStatus(theStatus);
        setProjectDate(theDate);
        setAttachedFilesList(new LinkedList<AttachedFile>());
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

    public Date getProjectDate() {
        return projectDate;
    }

    public void setProjectDate(Date projectDate) {
        this.projectDate = projectDate;
    }

    public LinkedList<AttachedFile> getAttachedFilesList() {
        return attachedFilesList;
    }

    public void setAttachedFilesList(LinkedList<AttachedFile> attachedFilesList) {
        this.attachedFilesList = attachedFilesList;
    }

    public void renameProject(String theName) throws IOException {
        String oldFName = getFormattedName();
        String newFName = getFormattedName(theName);
        //Rename Serialize
        File oldDir = new File(PROJECT_PATH + '/' + oldFName + "/" + oldFName + ".pser");
        File newDir = new File(PROJECT_PATH + '/' + oldFName + "/" + newFName + ".pser");
        oldDir.renameTo(newDir);

        //Rename folder
        oldDir = new File(PROJECT_PATH + '/' + oldFName);
        newDir = new File(PROJECT_PATH + '/' + newFName);
        oldDir.renameTo(newDir);

        //Set
        setProjectName(theName);
        writeProject();
    }

    /**
     * Add File and return reference to this File list.
     * @param theFile File to add.
     * @param theName The name of the file attached to be displayed.
     * @return Reference to this attachedFile list.
     */
    private void addAttachedFile(File theFile, String theName) throws IllegalArgumentException {
        try {
            attachedFilesList.add(new AttachedFile(PROJECT_PATH + '\\' + getFormattedName(), theFile, theName));
        } catch (IOException e) {
            InstaDialogue.showErrorMessage("Couldn't save file.\n" + e.getMessage());
        }
    }

    /**
     * Prompts user, add the File, and return reference to this File list.
     * @return Reference to this attachedFile list.
     */
    public boolean addAttachedFileByFileChooser() {
        //Get file
        JFileChooser fc = new JFileChooser(PATH_FILECHOOSER_START);
        fc.setDialogTitle("Select any file to attach...");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int fcReturn = fc.showOpenDialog(null);
        while (fcReturn != JFileChooser.APPROVE_OPTION) { //Try again
            if (fcReturn == JFileChooser.CANCEL_OPTION) return true;
            fcReturn = fc.showOpenDialog(null);
        }

        addAttachedFile(fc.getSelectedFile(), InstaDialogue.showInputDialog("Enter the File's Type (ie Manual, Receipt, etc.)"));
        return false;
    }

    //https://stackoverflow.com/questions/6555040/multiple-input-in-joptionpane-showinputdialog
    public boolean editAttachedFileByOptionPane(AttachedFile theAF) throws IOException {
        JPanel panel = new JPanel();

        JTextField nameText = new JTextField();
        nameText.setText(theAF.getName());
        nameText.setToolTipText("The File name.");
        JTextField typeText = new JTextField();
        typeText.setText(theAF.getType());
        typeText.setToolTipText("The File type. (ie Manual, Receipt, etc.");

        panel.add(new JLabel("Name:"));
        panel.add(nameText);
        panel.add(Box.createHorizontalStrut(20));
        panel.add(new JLabel("Type:"));
        panel.add(typeText);

        if (JOptionPane.showConfirmDialog(null, panel, "Edit " + theAF.getName(), JOptionPane.OK_CANCEL_OPTION) == 0) {
            theAF.setType(typeText.getText());
            theAF.rename(PROJECT_PATH + '\\' + getFormattedName(), nameText.getText());
        }

        cleanUpLooseAttachedFiles();
        return false;
    }

    public boolean editAttachedFileByOptionPane(String theAF) throws IOException {
        return editAttachedFileByOptionPane(getAttachedFile(theAF));
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
        File projectDir = new File(PROJECT_PATH + '\\' + getFormattedName());
        System.out.println(projectDir.getAbsolutePath()); //DEBUG out
        if (!projectDir.exists()) projectDir.mkdir();
        //itself serialized
        SerializeIO.serializeObjectToHere(this, projectDir.getAbsolutePath() + '\\' + getFormattedName() + ".pser");
        //attachedFiles
        for (AttachedFile af: attachedFilesList) {
            af.writeFile(projectDir.getAbsolutePath());
        }
        cleanUpLooseAttachedFiles();
    }

    /**
     * Returns folder & serialized file name of this project.
     * @return folder & serialized file name of this project.
     */
    private String getFormattedName() {
        return getFormattedName(projectName);
    }

    public static String getFormattedName(String theName) {
        return theName.trim().replaceAll(" ", "_");
    }

    /**
     * Delete files (have Manager call this before remove from list) <br>
     * https://www.baeldung.com/java-delete-directory
     */
    public void delete() {
        File directoryToBeDeleted = new File(PROJECT_PATH + '\\' + getFormattedName());
        if (!directoryToBeDeleted.exists()) return; //not exist already
        new AppDataIO().deleteR(directoryToBeDeleted);
        directoryToBeDeleted.delete();
    }

    public AttachedFile getAttachedFile(String theName) throws IllegalArgumentException {
        if (attachedFilesList == null || attachedFilesList.isEmpty()) throw new IllegalArgumentException("There are no Attached Files");
        Iterator it = attachedFilesList.iterator();
        while (it.hasNext()) {
            AttachedFile temp = (AttachedFile) it.next();
            if (temp.getName().equals(theName)) return temp; //found
        }
        throw new IllegalArgumentException("There is no Attached File with the name" + theName + '.');
    }

    public void deleteAttachedFile(String theName) { //TODO test
        deleteAttachedFile(getAttachedFile(theName));
    }

    public void deleteAttachedFile(AttachedFile theAF) { //TODO test
        try {
            theAF.delete(PROJECT_PATH + '/' + getFormattedName());
            attachedFilesList.remove(theAF);
        } catch (IOException e) {
            InstaDialogue.showErrorMessage("Couldn't delete the attached file.\n" + e.getMessage());
        }
    }

    public void cleanUpLooseAttachedFiles() {
        File projectFolder = new File(PROJECT_PATH + '/' + getFormattedName());
        //get count
        int countFser = 0;
        for (File projectFiles: projectFolder.listFiles()) {
            if (projectFiles.getName().endsWith(".fser")) {
                countFser++;
            }
        }
        File projectFilesFolder = new File(PROJECT_PATH + '/' + getFormattedName() + "/files");

        //if mismatch
        if (countFser != projectFilesFolder.listFiles().length) {
            for (File clonedFile: projectFilesFolder.listFiles()) {
                try {
                    getAttachedFile(clonedFile.getName());
                } catch (IllegalArgumentException e) {
                    clonedFile.delete();
                }
            }
        }
    }
}
