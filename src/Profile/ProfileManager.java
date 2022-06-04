package Profile;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * ProfileManager <br>
 * Stores a list of Profiles (read from profiles.txt on creation). <br>
 * Stores an Active/Selected Profile (mySelectedProfile). <br>
 * Able to create, remove, and search for Profiles. <br>
 * Call writeProfiles() before System Exit to save Profiles. <br><br>
 *
 * https://mkyong.com/java/json-simple-example-read-and-write-json/
 *
 * @author David Huynh
 * @author Damien Cruz
 */
public class ProfileManager { //TODO Remove repeating code
    /**
     * Profile folder path.
     */
    public final static String PROFILE_PATH = "appdata/profiles/profiles.txt";
    /**
     * List of Profiles.
     */
    private ArrayList<Profile> myProfiles;
    /**
     * Current currently selected / logged on Profile
     */
    private Profile mySelectedProfile;

    /**
     * Constructor. Read profiles.txt on creation.
     * @author David Huynh
     */
    public ProfileManager() {
        myProfiles = readProfiles();
    }

    ////////////////////
    //   Set & Get    //
    ////////////////////

    /**
     * Get Profile by Username String.
     * @author David Huynh
     * @param theUsername Username String to search for.
     * @return Profile with same username.
     * @throws IllegalArgumentException No Profile found.
     */
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
        throw new IllegalArgumentException("There is no Profile with the name " + theUsername + '.');
    }

    /**
     * Delete Profile by Username String.
     * @author David Huynh
     * @param theUsername Username String to search for.
     */
    public void deleteProfileByUsername(String theUsername) {
        System.out.println("Deleting Profile: " + theUsername);
        myProfiles.remove(getProfileByUsername(theUsername));
    }

    /**
     * Get currently selected / logged on Profile
     * @author David Huynh
     * @return currently selected / logged on Profile
     */
    public Profile getSelectedProfile() {
        return mySelectedProfile;
    }

    /**
     * Add a new Profile
     * @author David Huynh
     * @author Damien Cruz
     * @param theUsername Username.
     * @param theEmail Email.
     * @param thePrivilege Privilege enum.
     * @param password The password that should be encrypted BEFORE being sent to profile.
     * @throws IllegalArgumentException Already taken username.
     */
    public void addNewProfile(String theUsername, String theEmail, Privilege thePrivilege, String password) throws IllegalArgumentException {
        if (myProfiles != null || myProfiles.size() != 0) {
            Iterator it = myProfiles.iterator();
            while (it.hasNext()) {
                if (((Profile)it.next()).getUsername().equals(theUsername)) {
                    throw new IllegalArgumentException("Username already exists.");
                }
            }
        }
        myProfiles.add(new Profile(theUsername, theEmail, thePrivilege, password));
        writeProfiles();
    }

    /**
     * Get list of all Profiles.
     * @author David Huynh
     * @return list of all Profiles.
     */
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

    /**
     * Set a new selected / logged on Profile by username String.
     * @author David Huynh
     * @param theUsername the username to search for & set.
     * @throws IllegalArgumentException No Profile matching.
     */
    public void setSelectedProfile(String theUsername) throws IllegalArgumentException {
        if (myProfiles == null || myProfiles.size() == 0) {
            throw new IllegalArgumentException("There are no Profiles to select from.");
        }
        Iterator it = myProfiles.iterator();
        while (it.hasNext()) {
            Profile temp = (Profile)it.next();
            if (temp.getUsername().equals(theUsername)) {
                System.out.println("Setting Selected user as: " + temp.getUsername()); //DEBUG
                mySelectedProfile = temp;
                return;
            }
        }
        throw new IllegalArgumentException("There is no Profile with the name " + theUsername + '.');
    }

    /**
     * Set selected / logged on Profile.
     * @author David Huynh
     * @param theProfile to set.
     * @throws IllegalArgumentException No Profile matching.
     */
    public void setSelectedProfile(Profile theProfile) throws IllegalArgumentException {
        if (myProfiles == null || myProfiles.size() == 0) {
            throw new IllegalArgumentException("There are no Profiles to select from.");
        }
        Iterator it = myProfiles.iterator();
        while (it.hasNext()) {
            Profile temp = (Profile)it.next();
            if (temp == theProfile) {
                System.out.println("Setting Selected user as: " + temp.getUsername()); //DEBUG
                mySelectedProfile = temp;
                return;
            }
        }
        throw new IllegalArgumentException("There is no Profile with the name" + theProfile.getUsername() + '.');
    }

    /**
     * Set selected Profile to null, logout.
     * @author David Huynh
     */
    public void logout() {
        mySelectedProfile = null;
    }

    /**
     * Log in as a GUEST Profile.
     * @author David Huynh
     */
    public void loginAsGuest() {
        mySelectedProfile = new Profile();
    }

    ////////////////////
    //    In & Out    //
    ////////////////////

    /**
     * Read all Profile from JSON.
     * @author David Huynh
     * @return List of all Profiles saved.
     */
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
                        (String)profile.get("Email"),
                        Privilege.valueOf((String)profile.get("Privilege")),
                        (String) profile.get("password")
                ));
            }
            scanner.close();
        } catch (Exception e) {
            return temp;
        }
        return temp;
    }

    /**
     * Write all Profiles to JSON.
     * @author David Huynh
     */
    public void writeProfiles() {
        try {
            System.out.println("Writing Profiles to " + PROFILE_PATH + ':'); //DEBUG Out
            FileWriter fw = new FileWriter(PROFILE_PATH);
            for (Profile p: myProfiles) {
                JSONObject json = new JSONObject();
                json.put("Username", p.getUsername());
                json.put("Privilege", p.getPrivilege().getPrivilegeString());
                json.put("Email", p.getEmail());
                json.put("password", p.getePassword());

                System.out.println("\t" + json.toJSONString());
                fw.write(json.toJSONString() + "\n");
            }
            fw.flush();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
