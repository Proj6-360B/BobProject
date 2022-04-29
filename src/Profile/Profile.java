package Profile;

public class Profile {
    private String myUsername;
    private Privilege myPrivilege;

    public Profile(String theUsername, Privilege thePrivilege) {
        setUsername(theUsername);
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
}

