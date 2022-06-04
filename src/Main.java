import InstaDialogue.InstaDialogue;
import Profile.ProfileManager;
import Project.ProjectManager;
import ViewLogin.ViewLogin;
import ViewMain.ViewMain;

import java.io.File;

/**
 * Driver
 * @author Abdulmuen Fethi
 * @author Damien Cruz
 * @author David Huynh
 */
public class Main {
    //Managers
    /**
     * ProfileManager
     */
    private static final ProfileManager myProfileManager = new ProfileManager();
    /**
     * ProjectManager
     */
    private static final ProjectManager myProjectManager = new ProjectManager();

    /**
     * Entry point for program. Shows ViewLogin, then ViewMain.
     * @author Abdulmuen Fethi
     * @author Damien Cruz
     * @author David Huynh
     * @param args nothing
     */
    public static void main(String[] args){
        //Folder Creation //TODO move this to different class?
        try {
            initFolderSetup();
        } catch (Exception e) {
            InstaDialogue.showErrorMessage("Save data folder can't be written/read in " + System.getProperty("java.class.path") + ".");
        }

        //Login
        ViewLogin login = new ViewLogin(myProfileManager);
        login.setVisible(true);

        //View
        ViewMain viewMain = new ViewMain(myProfileManager, myProjectManager);
        viewMain.setVisible(true);
    }

    /**
     * Create appdata folders.
     * @author David Huynh
     */
    private static void initFolderSetup() {
        //appdata
        File dir[] = {new File("./appdata"), new File("./appdata/profiles"), new File("./appdata/projects")}; //TODO Some crap breaks with "./", only use if necessary
        for (File f: dir) {
            if (!f.exists()) {
                f.mkdir();
            }
        }
    }

}