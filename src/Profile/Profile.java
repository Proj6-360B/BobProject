package Profile;

public class Profile {
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
}

