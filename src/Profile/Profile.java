package Profile;

import java.io.*;
import java.util.ArrayList;

public class Profile implements java.io.Serializable {
    private String myUsername;
    private String myEmail;
    private Privilege myPrivilege;

    /**
     * Profile object.
     * @param theUsername The Username
     * @param theEmail The Email Address
     * @param thePrivilege The Privilege Enum
     */
    public Profile(String theUsername, String theEmail, Privilege thePrivilege) {
        setUsername(theUsername);
        setEmail(theEmail);
        setPrivilege(thePrivilege);
    }

    public String getUsername() {
        return myUsername;
    }

    public void setUsername(String myUsername) {
        this.myUsername = myUsername;
    }

    public Privilege getPrivilege() {
        return myPrivilege;
    }

    public void setPrivilege(Privilege myPrivilege) {
        this.myPrivilege = myPrivilege;
    }

    public String getEmail() {
        return myEmail;
    }

    public void setEmail(String myEmail) {
        this.myEmail = myEmail;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(myUsername);
        sb.append(" (");
        sb.append(myEmail);
        sb.append(')');
        return sb.toString();
    }

    //DEBUG TEST
    public static void main(String[] args) {
        Profile p1 = new Profile("Bob", "bobk41@gmail.com", Privilege.ADMIN);
        Profile p2 = new Profile("Billy", "billybob123@yahoo.com", Privilege.NORMAL);
        ArrayList<Profile> array = new ArrayList<Profile>();
        array.add(p1);
        array.add(p2);

        try {
            for (Profile p: array) {
                FileOutputStream outFile = new FileOutputStream("appdata/profiles/" + p.getUsername() + ".ser");
                ObjectOutputStream outObj = new ObjectOutputStream(outFile);
                outObj.writeObject(p);

                outObj.close();
                outFile.close();
            }
            System.out.println("Profile saved.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            for (File f: new File("appdata/profiles/").listFiles()) {
                if (!f.getName().endsWith(".ser")) continue;

                FileInputStream inFile = new FileInputStream(f);
                ObjectInputStream inObj = new ObjectInputStream(inFile);
                Profile temp = (Profile) inObj.readObject();
                System.out.println(temp);

                inFile.close();
                inObj.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

