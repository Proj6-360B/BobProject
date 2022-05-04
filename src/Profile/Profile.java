package Profile;

public class Profile {
    private String myUsername;
    private Privilege myPrivilege;
    private String myEmail;

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

    public void setEmail(String myUsername) {
        this.myEmail = myEmail;
    }
}

