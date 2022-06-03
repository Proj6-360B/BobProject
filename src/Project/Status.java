package Project;

/**
 * Status of the Project.
 * @author Abdulmen
 */
public enum Status {
    ACTIVE("ACTIVE"),FUTURE("FUTURE"),COMPLETE("COMPLETE");

    private String theStatusString;

    Status(String theStatus) {
    }
}
