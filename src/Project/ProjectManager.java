package Project;

import AppData.SerializeIO;
import Profile.Profile;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class ProjectManager {
    public static String PROJECT_PATH = "./appdata/projects";
    private LinkedList<Project> myProjects;
    private Project mySelectedProject;

    public ProjectManager() {
        myProjects = readProjects();
    }


    public Project getProjectsByName(String theProjectName) throws IllegalArgumentException {
        if (myProjects == null || myProjects.size() == 0) {
            throw new IllegalArgumentException("There are no Project to select from.");
        }
        Iterator it = myProjects.iterator();
        while (it.hasNext()) {
            Project temp = (Project)it.next();
            if (temp.getProjectName().equals(theProjectName)) {
                return temp;
            }
        }
        throw new IllegalArgumentException("There is no Project with the name" + theProjectName + '.');
    }

    public void deleteProjectsByName(String theProjectName) {
        System.out.println("Deleting Profile: " + theProjectName);
        myProjects.remove(getProjectsByName(theProjectName));
    }

    public Project getSelectedProject() {
        return mySelectedProject;
    }


    public void setMySelectedProjects(Project theProject) {
        if (myProjects == null || myProjects.size() == 0) {
            throw new IllegalArgumentException("There are no Project to select from.");
        }
        Iterator it = myProjects.iterator();
        while (it.hasNext()) {
            Project temp = (Project) it.next();
            if (temp == theProject) {
                System.out.println("Setting Selected user as: " + temp.getProjectName());
                mySelectedProject = temp;
                return;
            }
        }
        throw new IllegalArgumentException("There is no Project with the name" + theProject.getProjectName() + '.');
    }

    public void addNewProject(String theUsername, String theDescription, String theEmail, Status theStatus) throws IllegalArgumentException {
        if (myProjects != null || myProjects.size() != 0) {
            Iterator it = myProjects.iterator();
            while (it.hasNext()) {
                if (((Profile)it.next()).getUsername().equals(theUsername)) {
                    throw new IllegalArgumentException("Project name already exists.");
                }
            }
        }
        myProjects.add(new Project(theUsername, theDescription, theEmail, theStatus, new Date(2022, 1, 1))); //TODO Let user pick Date
    }

    public void addNewProject(String theUsername, String theEmail, Status theStatus, LinkedList<File> theAttachedFiles) throws IllegalArgumentException {
        //TODO
    }

    public LinkedList<Project> getProjectList() {
        return myProjects;
    }

    public ArrayList<String> getProjectNameList() {
        ArrayList<String> result = new ArrayList<>(myProjects.size());
        if (myProjects != null || myProjects.size() != 0) {
            Iterator it = myProjects.iterator();
            while (it.hasNext()) {
                result.add(((Profile)it.next()).getUsername());
            }
        }
        return result;
    }

    public void writeProjects() {
        try {
            System.out.println("Writing Project to " + PROJECT_PATH + ':'); //DEBUG Out

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LinkedList<Project> readProjects() {
        System.out.println("Reading Project from " + PROJECT_PATH + ':'); //DEBUG Out
        LinkedList<Project> temp = new LinkedList<Project>();

        try {
            File projectsDir = new File(PROJECT_PATH); //Dir
            for (File project: projectsDir.listFiles()) { //Each individual Project folder
                LinkedList<AttachedFile> afList = new LinkedList<AttachedFile>();
                for (File serF: project.listFiles()) { //Project & Attached Files serialized in each folder
                    if (serF.getAbsolutePath().endsWith(".fser")) { //Attached File
                        afList.add((AttachedFile) SerializeIO.deserializeObjectFromHere(serF.getAbsolutePath()));
                    } else if (serF.getAbsolutePath().endsWith(".pser")) { //Project
                        temp.add((Project) SerializeIO.deserializeObjectFromHere(serF.getAbsolutePath()));
                    }
                }
                temp.getLast().setAttachedFilesList(afList);
            }
        } catch (Exception e) {
            System.out.println("Error: Unable to read Projects folder!");
            e.printStackTrace();
        }

        return temp;
    }
}
