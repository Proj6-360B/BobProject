package projectManager;

import Profile.Privilege;

public class Projects {
    private String projectName;
    private String projectType;
    private Status projectStatus;

    public Projects(String theProjectName, String theProjectType, Status theStatus) {
        setProjectName(theProjectName);
        setProjectType(theProjectType);
        setProjectStatus(theStatus);
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
}
