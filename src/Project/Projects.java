package Project;

import java.io.File;
import java.util.LinkedList;

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
     * @return Reference to this File list.
     */
    public LinkedList<File> addFile(File theFile) {
        if (theFile == null || !theFile.exists()) {
            throw new IllegalArgumentException(theFile + " is not a valid file.");
        }
        attachedFiles.add(theFile);
        return attachedFiles;
    }

    /**
     * Delete File from this list.
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
