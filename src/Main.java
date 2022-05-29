import FileChooserHelper.FileChooserHelper;
import Profile.ProfileManager;
import ViewLogin.ViewLogin;
import ViewMain.ViewMain;

import java.io.File;

public class Main {
    //Managers
    private static final ProfileManager myProfileManager = new ProfileManager();
//    private static final ProfileManager myProjectManger = new ProfileManager();

    public static void main(String[] args){
        try {
            initFolderSetup();
        } catch (Exception e) {
            FileChooserHelper.showErrorMessage("Save data folder can't be written/read in " + System.getProperty("java.class.path") + ".");
        }
        //Main View
//        ViewMain gui = new ViewMain(myProfileManager); //TODO This still activates when Login is still happening.

        //Login
        ViewLogin login = new ViewLogin(myProfileManager);

        //Exit
        myProfileManager.writeProfiles(); //TODO This still activates when GUI is still on.
    }

    private static void initFolderSetup() {
        //appdata
        File dir[] = {new File("./appdata"), new File("./appdata/profiles"), new File("./appdata/projects")};
        for (File f: dir) {
            if (!f.exists()) {
                f.mkdir();
            }
        }
    }

}