import Profile.ProfileManager;
import ViewMain.ViewMain;

public class Main {
    public static void main(String[] args){
        //Managers
        ProfileManager myProfileManager = new ProfileManager();
        //TODO Project Manager (and Project Object/Entries)

        //Login
        //TODO A Frame or something to make & select the user. A user needs to be selected before moving on
        myProfileManager.selectProfileByUsername("Bob");

        //Main Scratch.View
        ViewMain gui = new ViewMain(myProfileManager);

        //Exit
        myProfileManager.writeProfiles(); //TODO This still activates when GUI is still on.
    }

}