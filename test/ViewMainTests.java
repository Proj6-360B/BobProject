import Profile.ProfileManager;
import Project.ProjectManager;
import ViewMain.ViewMain;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class ViewMainTests {
    private ViewMain main;
    private Profile.ProfileManager ProfileManager;
    private Project.ProjectManager ProjectManager;

    @BeforeEach
    void setUp() {
        main = new ViewMain(ProfileManager , ProjectManager);
    }

    @Test
    public void testInitiation() {
        assertNull(main);
    }
}
