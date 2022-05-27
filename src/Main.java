import Profile.ProfileManager;
import ViewLogin.ViewLogin;
import ViewMain.ViewMain;

public class Main {
    //Managers
    private static final ProfileManager myProfileManager = new ProfileManager();
//    private static final ProfileManager myProjectManger = new ProfileManager();

    public static void main(String[] args){
        //Main View
//        ViewMain gui = new ViewMain(myProfileManager); //TODO This still activates when Login is still happening.

        //Login
        ViewLogin login = new ViewLogin(myProfileManager);

        //Exit
        myProfileManager.writeProfiles(); //TODO This still activates when GUI is still on.
    }

}