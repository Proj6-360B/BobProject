import Profile.Profile;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

public class ProfilesTest {

    @Test
    public void setsProperly() throws NoSuchFieldException, IllegalAccessException {
        var name = new Profile();
        name.setUsername("bob");
        Field field = name.getClass().getDeclaredField("myUsername");
        field.setAccessible(true);
        assertEquals("Fields don't match", field.get(name), "bob");
    }

    @Test
    public void setsPasswordProperly() throws NoSuchFieldException, IllegalAccessException {
        var pass = new Profile();
        pass.setePassword("bob");
        Field field = pass.getClass().getDeclaredField("ePassword");
        field.setAccessible(true);
        assertEquals("Fields don't match", field.get(pass), "bob");
    }

    @Test
    public void getsProperly() throws NoSuchFieldException, IllegalAccessException {
        var name = new Profile();
        Field field = name.getClass().getDeclaredField("myUsername");
        field.setAccessible(true);
        field.set(name, "Bob_name");

        String result = name.getUsername();
        assertEquals("field not retrieved properly", result, "Bob_name");
    }

    @Test
    public void testPrivilege() {
        var priv = new Profile();
        assertEquals("GUEST", priv.getPrivilege().toString());
    }

    @Test
    public void testEmail() throws NoSuchFieldException, IllegalAccessException {
        var email = new Profile();
        Field field = email.getClass().getDeclaredField("myEmail");
        field.setAccessible(true);
        field.set(email, "bob@gmail.com");

        String result = email.getEmail();
        assertEquals("field not retrieved properly", result, "bob@gmail.com");
    }
}
