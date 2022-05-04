package Profile;

/**
 * Privilege Enums. <br>
 * ADMIN, NORMAL, GUEST
 */
public enum Privilege {
    ADMIN("ADMIN"),
    NORMAL("NORMAL"),
    GUEST("GUEST");

    private String myPrivilegeString;

    Privilege (String thePrivilegeInt) {
        myPrivilegeString = thePrivilegeInt;
    }

    public String getPrivilegeInt() {
        return myPrivilegeString;
    }
}