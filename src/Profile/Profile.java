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
     * The preferred font.
     */
    private String myFont;
    /**
     * the encrypted password
     */
    private String ePassword;

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
        setFont("Dialogue");
    }

    /**
     * Profile object.
     * @param theUsername The Username
     * @param theEmail The Email Address
     * @param thePrivilege The Privilege Enum
     * @param theFont The Preferred Font.
     */
    public Profile(String theUsername, String theEmail, Privilege thePrivilege, String theFont) {
        setUsername(theUsername);
        setEmail(theEmail);
        setPrivilege(thePrivilege);
        setFont(theFont);
    }
    /**
     * @author Damien Cruz
     * Profile object.
     * @param theUsername The Username
     * @param theEmail The Email Address
     * @param thePrivilege The Privilege Enum
     * @param theFont The Preferred Font.
     * @param ePassword the password that should be encrypted BEFORE being sent to profile
     */
    public Profile(String theUsername, String theEmail, Privilege thePrivilege,String theFont, String ePassword) {
        setUsername(theUsername);
        setEmail(theEmail);
        setPrivilege(thePrivilege);
        setFont(theFont);
        setePassword(ePassword);
    }



    /**
     * Gets the username.
     * @return the username
     */
    public String getUsername() {
        return myUsername;
    }

    /**
     * Sets the username
     * @param myUsername the username to set
     */
    public void setUsername(String myUsername) {
        this.myUsername = myUsername;
    }

    /**
     * Get the privilege enum.
     * @return the privilege enum
     */
    public Privilege getPrivilege() {
        return myPrivilege;
    }

    /**
     * Set the privilege enum.
     * @param myPrivilege the privilege enum to set
     */
    public void setPrivilege(Privilege myPrivilege) {
        this.myPrivilege = myPrivilege;
    }

    /**
     * Get the email address.
     * @return the email address
     */
    public String getEmail() {
        return myEmail;
    }

    /**
     * Set the email address.
     * @param myEmail the email address to set.
     */
    public void setEmail(String myEmail) {
        this.myEmail = myEmail;
    }

    /**
     * Get the preferred font. (null returns "Dialogue").
     * @return myFont.
     */
    public String getFont() {
        if (myFont == null) return "Dialogue";
        return myFont;
    }

    /**
     * Set the preferred font.
     * @param theFont The font to set.
     */
    public void setFont(String theFont) {
        myFont = theFont;
    }


    public String getePassword() {
        return ePassword;
    }

    public void setePassword(String ePassword) {
        this.ePassword = ePassword;
    }

    /**
     * toString(). <br>
     * [myUsername] ([thePrivilege])
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

