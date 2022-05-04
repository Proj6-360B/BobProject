package Main;

import Profile.Privilege;
import Profile.ProfileManager;

public class Main {
    public static void main(String[] args){
        ProfileManager pm = new ProfileManager();
        GUIMain gui = new GUIMain(pm);
    }

}