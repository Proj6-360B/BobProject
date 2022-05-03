package Profile;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * https://mkyong.com/java/json-simple-example-read-and-write-json/
 */
public class ProfileManager {
    public static String PROFILE_PATH = "./appdata/profiles/profiles.txt";
    private ArrayList<Profile> myProfiles;
    private Profile mySelectedProfile;
//    private boolean isLoaded;

    public ProfileManager() {
        myProfiles = readProfiles();
    }

    public Profile getProfileByUsername(String theUsername) throws IllegalArgumentException {
        if (myProfiles == null || myProfiles.size() == 0) {
            throw new IllegalArgumentException("There are no Profiles to select from.");
        }
        Iterator it = myProfiles.iterator();
        while (it.hasNext()) {
            Profile temp = (Profile)it.next();
            if (temp.getUsername().equals(theUsername)) {
                return temp;
            }
        }
        throw new IllegalArgumentException("There is no Profile with the name" + theUsername + '.');
    }

    public Profile getSelectedProfile() {
        return mySelectedProfile;
    }

    public void addNewProfile(String theUsername, Privilege thePrivilege) throws IllegalArgumentException {
        if (myProfiles != null || myProfiles.size() != 0) {
            Iterator it = myProfiles.iterator();
            while (it.hasNext()) {
                if (((Profile)it.next()).getUsername().equals(theUsername)) {
                    throw new IllegalArgumentException("Username already exists.");
                }
            }
        }
        myProfiles.add(new Profile(theUsername, thePrivilege));
    }

    public ArrayList<Profile> getProfileList() {
        return myProfiles;
    }

    public ArrayList<String> getProfileUsernameList() {
        ArrayList<String> result = new ArrayList<>(myProfiles.size());
        if (myProfiles != null || myProfiles.size() != 0) {
            Iterator it = myProfiles.iterator();
            while (it.hasNext()) {
                result.add(((Profile)it.next()).getUsername());
            }
        }
        return result;
    }

    public void selectProfileByUsername(String theUsername) {
        if (myProfiles == null || myProfiles.size() == 0) {
            throw new IllegalArgumentException("There are no Profiles to select from.");
        }
        Iterator it = myProfiles.iterator();
        while (it.hasNext()) {
            Profile temp = (Profile)it.next();
            if (temp.getUsername().equals(theUsername)) {
                mySelectedProfile = temp;
                return;
            }
        }
        throw new IllegalArgumentException("There is no Profile with the name" + theUsername + '.');
    }


    public ArrayList<Profile> readProfiles() {
        System.out.println("Reading Profiles from " + PROFILE_PATH + ':'); //DEBUG Out
        ArrayList<Profile> temp = new ArrayList<Profile>();
        try {
            Scanner scanner = new Scanner(new File(PROFILE_PATH));
            while (scanner.hasNextLine()) {
                JSONObject profile = (JSONObject) new JSONParser().parse(scanner.nextLine());
                System.out.println("\t" + profile.toJSONString()); //DEBUG Out
                temp.add(new Profile(
                        (String)profile.get("Username"),
                        Privilege.valueOf((String)profile.get("Privilege"))
                ));
            }
            scanner.close();
        } catch (Exception e) {
            return temp;
        }
        return temp;
    }

    public void writeProfiles() {
        try {
            FileWriter fw = new FileWriter(PROFILE_PATH);
            for (Profile p: myProfiles) {
                JSONObject json = new JSONObject();
                json.put("Username", p.getUsername());
                json.put("Privilege", p.getPrivilege().getPrivilegeInt());

                fw.write(json.toJSONString() + "\n");
            }
            fw.flush();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
