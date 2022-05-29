package Project;

import Profile.Profile;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.sound.sampled.Line;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class ProjectManager {
    public static String PROJECT_PATH = "./appdata/projects/projects.txt";
    private LinkedList<Projects> myProjects;
    private Projects mySelectedProjects;

    public ProjectManager() {
        myProjects = readProjects();
    }


    public Projects getProjectsByName(String theProjectName) throws IllegalArgumentException {
        if (myProjects == null || myProjects.size() == 0) {
            throw new IllegalArgumentException("There are no Projects to select from.");
        }
        Iterator it = myProjects.iterator();
        while (it.hasNext()) {
            Projects temp = (Projects)it.next();
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

    public Projects getSelectedProject() {
        return mySelectedProjects;
    }


    public void setMySelectedProjects(Projects theProjects) {
        if (myProjects == null || myProjects.size() == 0) {
            throw new IllegalArgumentException("There are no Projects to select from.");
        }
        Iterator it = myProjects.iterator();
        while (it.hasNext()) {
            Projects temp = (Projects) it.next();
            if (temp == theProjects) {
                System.out.println("Setting Selected user as: " + temp.getProjectName());
                mySelectedProjects = temp;
                return;
            }
        }
        throw new IllegalArgumentException("There is no Project with the name" + theProjects.getProjectName() + '.');
    }

    public void addNewProject(String theUsername, String theEmail, Status theStatus) throws IllegalArgumentException {
        if (myProjects != null || myProjects.size() != 0) {
            Iterator it = myProjects.iterator();
            while (it.hasNext()) {
                if (((Profile)it.next()).getUsername().equals(theUsername)) {
                    throw new IllegalArgumentException("Project name already exists.");
                }
            }
        }
        myProjects.add(new Projects(theUsername, theEmail, theStatus, new Date(2022, 1, 1))); //TODO Let user pick Date
    }

    public void addNewProject(String theUsername, String theEmail, Status theStatus, LinkedList<File> theAttachedFiles) throws IllegalArgumentException {
        //TODO
    }

    public LinkedList<Projects> getProjectList() {
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
            System.out.println("Writing Projects to " + PROJECT_PATH + ':'); //DEBUG Out
            FileWriter fw = new FileWriter(PROJECT_PATH);
            for (Projects p: myProjects) {
                JSONObject json = new JSONObject();
                json.put("ProjectName", p.getProjectName());
                json.put("ProjectStatus", p.getProjectStatus());
                json.put("ProjectType", p.getProjectType());
                //TODO Save Date
                json.put("AttachedFiles", p.getAttachedFiles()); //TODO Test

                System.out.println("\t" + json.toJSONString());
                fw.write(json.toJSONString() + "\n");
            }
            fw.flush();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LinkedList<Projects> readProjects() {
        System.out.println("Reading Projects from " + PROJECT_PATH + ':'); //DEBUG Out
        LinkedList<Projects> temp = new LinkedList<Projects>();
        try {
            Scanner scanner = new Scanner(new File(PROJECT_PATH));
            while (scanner.hasNextLine()) {
                JSONObject projects = (JSONObject) new JSONParser().parse(scanner.nextLine());
                System.out.println("\t" + projects.toJSONString()); //DEBUG Out
                temp.add(new Projects(
                        (String)projects.get("theProjectName"),
                        (String)projects.get("theProjectType"),
                        Status.valueOf((String)projects.get("theStatus")),
                        new Date(2022, 1, 1), //TODO Load Date
                        (LinkedList<File>)projects.get("AttachedFiles") //TODO Test
                ));
            }
            scanner.close();
        } catch (Exception e) {
            return temp;
        }
        return temp;
    }
}
