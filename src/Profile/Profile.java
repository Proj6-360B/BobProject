package Profile;

/**
 * Profile. Stores a username, email, the level of privilege, and preferred font.
 * @author David Huynh
 */
public class Profile {
    /**
     * The username.
     */
    private String myUsername;
    /**
     * The Email address.
     */
    private String myEmail;
    /**
     * The privilege level.
     */
    private Privilege myPrivilege;
    /**
     * the encrypted password
     */
    private String ePassword;

    /**
     * Profile object.
     * @author David Huynh
     * @param theUsername The Username
     * @param theEmail The Email Address
     * @param thePrivilege The Privilege Enum
     */
    public Profile(String theUsername, String theEmail, Privilege thePrivilege) {
        setUsername(theUsername);
        setEmail(theEmail);
        setPrivilege(thePrivilege);
    }

    /**
     * Profile object w/ Password.
     * @author Damien Cruz
     * @param theUsername The Username
     * @param theEmail The Email Address
     * @param thePrivilege The Privilege Enum
     * @param ePassword the password that should be encrypted BEFORE being sent to profile
     */
    public Profile(String theUsername, String theEmail, Privilege thePrivilege, String ePassword) {
        setUsername(theUsername);
        setEmail(theEmail);
        setPrivilege(thePrivilege);
        setePassword(ePassword);
    }

    /**
     * Create a GUEST Profile.
     * @author David Huynh
     */
    public Profile() {
        setUsername("GUEST");
        setEmail("GUEST");
        setPrivilege(Privilege.GUEST);
    }

    /**
     * Gets the username.
     * @author David Huynh
     * @return the username
     */
    public String getUsername() {
        return myUsername;
    }

    /**
     * Sets the username.
     * @author David Huynh
     * @param myUsername the username to set
     */
    public void setUsername(String myUsername) {
        this.myUsername = myUsername;
    }

    /**
     * Get the privilege enum.
     * @author David Huynh
     * @return the privilege enum
     */
    public Privilege getPrivilege() {
        return myPrivilege;
    }

    /**
     * Set the privilege enum.
     * @author David Huynh
     * @param myPrivilege the privilege enum to set
     */
    public void setPrivilege(Privilege myPrivilege) {
        this.myPrivilege = myPrivilege;
    }

    /**
     * Get the email address.
     * @author David Huynh
     * @return the email address
     */
    public String getEmail() {
        return myEmail;
    }

    /**
     * Set the email address.
     * @author David Huynh
     * @param myEmail the email address to set.
     */
    public void setEmail(String myEmail) {
        this.myEmail = myEmail;
    }

    /**
     * Set the Encrypted Password.
     * @author Damien Cruz
     * @param ePassword the Encrypted Password to set.
     */
    public void setePassword(String ePassword) {
        this.ePassword = ePassword;
    }

    /**
     * Get the Encrypted Password.
     * @author Damien Cruz
     * @return the Encrypted Password.
     */
    public String getePassword() {
        return ePassword;
    }

    /**
     * toString(). <br>
     * [myUsername] ([thePrivilege])
     * @author David Huynh
     * @return String representation.
     */
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

