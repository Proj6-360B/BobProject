package Profile;

/**
 * Privilege Enums. <br>
 * ADMIN, NORMAL, GUEST
 * @author David Huynh
 */
public enum Privilege {
    /**
     * Admin. Full ownership
     */
    ADMIN("ADMIN"),
    /**
     * Normal. TODO
     */
//    NORMAL("NORMAL"),
    /**
     * Guest. No editing.
     */
    GUEST("GUEST");

    /**
     * Get the string equivalent of the Enum.
     */
    private String myPrivilegeString;

    /**
     * The TODO
     * @param thePrivilegeInt
     */
    Privilege (String thePrivilegeInt) {
        myPrivilegeString = thePrivilegeInt;
    }

    /**
     * TODO
     * @return
     */
    public String getPrivilegeInt() {
        return myPrivilegeString;
    }
}