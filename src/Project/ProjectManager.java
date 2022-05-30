package Project;

import AppData.SerializeIO;
import InstaDialogue.InstaDialogue;
import Profile.Profile;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class ProjectManager {
    public static String PROJECT_PATH = "appdata/projects";
    private LinkedList<Project> myProjects;
//    private Project mySelectedProject;

    public ProjectManager() {
        myProjects = readProjects();
    }


    public Project getProject(String theProjectName) throws IllegalArgumentException {
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

    public void deleteProject(String theProjectName) {
        deleteProject(getProject(theProjectName));
    }

    public void deleteProject(Project theProject) {
        System.out.print("Deleting Project: " + theProject);
        myProjects.remove(theProject);
        System.out.println(" Done.");
    }

//    public Project getSelectedProject() {
//        return mySelectedProject;
//    }
//
//
//    public void setMySelectedProjects(Project theProject) {
//        if (myProjects == null || myProjects.size() == 0) {
//            throw new IllegalArgumentException("There are no Project to select from.");
//        }
//        Iterator it = myProjects.iterator();
//        while (it.hasNext()) {
//            Project temp = (Project) it.next();
//            if (temp == theProject) {
//                System.out.println("Setting Selected user as: " + temp.getProjectName());
//                mySelectedProject = temp;
//                return;
//            }
//        }
//        throw new IllegalArgumentException("There is no Project with the name" + theProject.getProjectName() + '.');
//    }

    public void addNewProject(String theName, String theDescription, String theType, Status theStatus, Date theDate) throws IllegalArgumentException {
        if (myProjects != null || myProjects.size() != 0) {
            Iterator it = myProjects.iterator();
            while (it.hasNext()) {
                if (((Project) it.next()).getProjectName().equals(theName)) {
                    throw new IllegalArgumentException("Project name already exists.");
                }
            }
        }
        myProjects.add(new Project(theName, theDescription, theType, theStatus, theDate)); //with empty AttachedFiles list. (have user edit list when viewing Project)
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
            for (Project p: myProjects) {
                p.writeProject();
            }
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
                System.out.println("\tProject: " + project.getName());
                LinkedList<AttachedFile> afList = new LinkedList<AttachedFile>();
                for (File serF: project.listFiles()) { //Project & Attached Files serialized in each folder
                    System.out.println("\t\tSerialized: " + serF.getName());
                    if (serF.getAbsolutePath().endsWith(".fser")) { //Attached File
                        afList.add((AttachedFile) SerializeIO.deserializeObjectFromHere(serF.getAbsolutePath()));
                        System.out.println("\t\t\t-AttachedFile Read");
                    } else if (serF.getAbsolutePath().endsWith(".pser")) { //Project
                        temp.add((Project) SerializeIO.deserializeObjectFromHere(serF.getAbsolutePath()));
                        System.out.println("\t\t\t-Project Read");
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

    public static void main(String[] args) {
        ProjectManager myProjectManager = new ProjectManager();
        //New Proj
//        myProjectManager.addNewProject("Test Proj",
//                                       "A project entry to test IO wow.",
//                                       "Repair",
//                                       Status.ACTIVE,
//                                       new Date(2022, 1, 1));
        //Add files 3
//        for (Project p: myProjectManager.getProjectList()) {
//            p.addAttachedFileByFileChooser();
//            p.addAttachedFileByFileChooser();
//            p.addAttachedFileByFileChooser();
//        }
        //Open each file
        for (Project p: myProjectManager.getProjectList()) {
            for (AttachedFile af: p.getAttachedFilesList()) {
                try {
                    Desktop.getDesktop().open(af.getFile());
                } catch (Exception e) {
                    InstaDialogue.showErrorMessage("Couldn't open the file.\n" + e.getMessage());
                }
            }
        }
        //Save
        myProjectManager.writeProjects();
    }
}
