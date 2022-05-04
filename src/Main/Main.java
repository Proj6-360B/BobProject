package Main;

import Profile.Privilege;
import Profile.ProfileManager;

public class Main {
    public static void main(String[] args){
        //Managers
        ProfileManager pm = new ProfileManager();
        //TODO Project Manager (and Project Object/Entries)

        //Login
        //TODO A Frame or something to make & select the user. A user needs to be selected before moving on

        //Main View
        GUIMain gui = new GUIMain(pm);
    }

}