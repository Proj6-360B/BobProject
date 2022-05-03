package Profile;

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