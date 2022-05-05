import Profile.ProfileManager;
import ViewLogin.ViewLogin;
import ViewMain.ViewMain;

public class Main {
    //Managers
    private static final ProfileManager myProfileManager = new ProfileManager();
    //TODO Project Manager (and Project Object/Entries)

    public static void main(String[] args){
        //Main View
        ViewMain gui = new ViewMain(myProfileManager); //TODO This still activates when Login is still happening.

        //Login
        ViewLogin login = new ViewLogin(myProfileManager);
        //myProfileManager.selectProfileByUsername("Bob"); //DEBUG

        //Exit
        myProfileManager.writeProfiles(); //TODO This still activates when GUI is still on.
    }

}