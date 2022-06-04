package Project;

import AppData.AppDataIO;
import AppData.SerializeIO;
import InstaDialogue.InstaDialogue;
import Profile.Profile;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Manages an array of Projects. Handles reading in Serialized Project files to make arrays.
 * @see Project
 * @author David Huynh
 * @author Abdulmuen Fethi
 */
public class ProjectManager {
    /**
     * Path to Project Folder.
     */
    public static String PROJECT_PATH = "appdata/projects";
    /**
     * List of Projects.
     */
    private LinkedList<Project> myProjects;
//    private Project mySelectedProject;

    /**
     * Constructor. Immediately reads Projects folder on construct.
     */
    public ProjectManager() {
        myProjects = readProjects();
    }

    /**
     * Get a Project by name.
     * @author Abdulmuen Fethi
     * @param theProjectName Project to search for.
     * @return Project with the same name.
     * @throws IllegalArgumentException No Project matching
     */
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
        throw new IllegalArgumentException("There is no Project with the name " + theProjectName + '.');
    }

    /**
     * Delete Project by name.
     * @author Abdulmuen Fethi
     * @author David Huynh
     * @param theProjectName Project name to search for.
     */
    public void deleteProject(String theProjectName) {
        deleteProject(getProject(theProjectName));
    }

    /**
     * Delete Project by reference.
     * @author David Huynh
     * @param theProject Project to search for.
     */
    public void deleteProject(Project theProject) {
        System.out.println("Deleting Project: " + theProject);
        theProject.delete();
        myProjects.remove(theProject);
        System.out.println(" Successfully deleted.");
    }

    /**
     * Add a new Project to the list.
     * @see Project
     * @author Abdulmuen Fethi
     * @author David Huynh
     * @param theName Name
     * @param theDescription Description
     * @param theType Type
     * @param theStatus Status enum
     * @param theDate Date object
     * @throws IllegalArgumentException Already taken name
     */
    public void addNewProject(String theName, String theDescription, String theType, Status theStatus, Date theDate) throws IllegalArgumentException {
        if (myProjects != null || myProjects.size() != 0) {
            Iterator it = myProjects.iterator();
            while (it.hasNext()) {
                if (((Project) it.next()).getProjectName().equals(theName)) {
                    throw new IllegalArgumentException("Project name already exists."); //TODO avoid by renaming "- Copy" of something
                }
            }
        }
        myProjects.add(new Project(theName, theDescription, theType, theStatus, theDate)); //with empty AttachedFiles list. (have user edit list when viewing Project)
    }

    /**
     * Get the Project list.
     * @author Abdulmuen Fethi
     * @return Project list.
     */
    public LinkedList<Project> getProjectList() {
        return myProjects;
    }

    /**
     * Get list of Project's names
     * @author Abdulmuen Fethi
     * @return Project name list.
     */
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

    /**
     * Tells each Project to save themselves.
     * @author Abdulmuen Fethi
     * @author David Huynh
     */
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

    /**
     * Read all Projects w/ their AttachedFiles and put them into the list.
     * @author Abdulmuen Fethi
     * @author David Huynh
     */
    public LinkedList<Project> readProjects() {
        System.out.println("Reading Project from " + PROJECT_PATH + ':'); //DEBUG Out
        LinkedList<Project> temp = new LinkedList<Project>();

        try {
            File projectsDir = new File(PROJECT_PATH); //Dir
            for (File project: projectsDir.listFiles()) { //Each individual Project folder
                System.out.println("\tProject: " + project.getName());
                LinkedList<AttachedFile> afList = new LinkedList<AttachedFile>();
                boolean foundProject = false;
                for (File serF: project.listFiles()) { //Project & Attached Files serialized in each folder
                    System.out.println("\t\tSerialized: " + serF.getName());
                    if (serF.getAbsolutePath().endsWith(".fser")) { //Attached File
                        afList.add((AttachedFile) SerializeIO.deserializeObjectFromHere(serF.getAbsolutePath()));
                        System.out.println("\t\t\t-AttachedFile Read");
                    } else if (serF.getAbsolutePath().endsWith(".pser")) { //Project
                        temp.add((Project) SerializeIO.deserializeObjectFromHere(serF.getAbsolutePath()));
                        foundProject = true;
                        System.out.println("\t\t\t-Project Read");
                    }
                }
                if (foundProject) {
                    temp.getLast().setAttachedFilesList(afList);
                } else {
                    System.out.println("Detected broken Project folder, cleaning...");
                    project.delete();
                    cleanUpLooseProjects(); //Check for other
                }
            }
        } catch (Exception e) {
            System.out.println("Error: Unable to read Projects folder!");
            e.printStackTrace();
        }

        return temp;
    }

    /**
     * Cleans up Project folders that do not have a .pser file. (Already does it automatically)
     * @author David Huynh
     */
    public void cleanUpLooseProjects() {
        File directoryProject = new File(PROJECT_PATH);
        for (File projectFolder: directoryProject.listFiles()) {
            boolean foundProject = false;
            for (File projectFile: projectFolder.listFiles()) {
                if (projectFile.getName().endsWith(".pser")) {
                    foundProject = true;
                    break;
                }
            }
            if (!foundProject) new AppDataIO().deleteR(projectFolder);
        }
        System.out.println("Cleaned up appdata/projects/");
    }

    //Demo of features (test)
//    public static void main(String[] args) {
//        ProjectManager myProjectManager = new ProjectManager();
//        //New Proj
////        myProjectManager.addNewProject("Test Proj",
////                                       "A project entry to test IO wow.",
////                                       "Repair",
////                                       Status.ACTIVE,
////                                       new Date(2022, 1, 1));
//        //Add files 3
//        for (Project p: myProjectManager.getProjectList()) {
//            p.addAttachedFileByFileChooser();
//            p.addAttachedFileByFileChooser();
//            p.addAttachedFileByFileChooser();
//        }
//        //Open each file
//        for (Project p: myProjectManager.getProjectList()) {
//            for (AttachedFile af: p.getAttachedFilesList()) {
//                try {
//                    Desktop.getDesktop().open(af.getFile());
//                } catch (Exception e) {
//                    InstaDialogue.showErrorMessage("Couldn't open the file.\n" + e.getMessage());
//                }
//            }
//        }
//        //Save
//        myProjectManager.writeProjects();
//    }
}
