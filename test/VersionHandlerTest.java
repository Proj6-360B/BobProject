import ViewMain.Components.VersionHandler;
import org.junit.Test;

import static org.junit.Assert.*;

public class VersionHandlerTest {
    String expectedValue = "Iteration3";
    @Test
    public void versionTest() {
        assertEquals(expectedValue, new VersionHandler().VERSION);
    }
}
