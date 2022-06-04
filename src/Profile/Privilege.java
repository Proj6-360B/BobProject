package Profile;

/**
 * Privilege Enums. <br>
 * ADMIN or GUEST
 * @author David Huynh
 */
public enum Privilege {
    /**
     * Admin. Full ownership
     */
    ADMIN("ADMIN"),
    /**
     * Guest. No editing.
     */
    GUEST("GUEST");

    /**
     * Get the string equivalent of the Enum.
     * @author David Huynh
     */
    private String myPrivilegeString;

    /**
     * Enum Constructor from String.
     * @author David Huynh
     * @param thePrivilege The Privilege as a String.
     */
    Privilege (String thePrivilege) {
        myPrivilegeString = thePrivilege;
    }

    /**
     * Get Enum's String representation.
     * @author David Huynh
     * @return Enum's String representation.
     */
    public String getPrivilegeString() {
        return myPrivilegeString;
    }
}