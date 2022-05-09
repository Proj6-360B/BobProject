import ViewMain.Components.VersionHandler;
import org.junit.Test;

import static org.junit.Assert.*;

public class VersionHandlerTest {
    @Test
    public void versionTest() {
        assertEquals("Iteration1 (v0.1)", new VersionHandler().VERSION);
    }
}
