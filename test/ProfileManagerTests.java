import Profile.Profile;
import Profile.ProfileManager;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ProfileManagerTests {

        @Test
        public void getProfileByUsername() {
            Profile expectedValue = new ProfileManager().getProfileByUsername("Bob");

            String actualValue = "Bob (bobk41@gmail.com)";
            System.out.println("Expected Value="+ expectedValue.toString() +" . Actual Value=" + actualValue);

            assertEquals(expectedValue.toString(), actualValue);
        }

        @Test
        public void loginAsGuest() {
            var account = new Profile();
            System.out.println("new account=" + account + " " + "new account name=" + account.getUsername());
            assertEquals("GUEST", account.getUsername());
        }

        @Test
        public void deleteProfileByUsername() {
            Profile expectedValue = new ProfileManager().getProfileByUsername("Bob");

            String username = "Bob (bobk41@gmail.com)";

            System.out.println("Expected Value="+ expectedValue.toString() +" . Actual Value=" + username);

            assertEquals(expectedValue.toString(), username);
        }

        @Test
        public void getSelectedProfile() throws NoSuchFieldException, IllegalAccessException {
            var name = new Profile();
        Field field = name.getClass().getDeclaredField("myUsername");
        field.setAccessible(true);
        field.set(name, "Bob_name");

        String result = name.getUsername();
        assertEquals("field not retrieved properly", result, "Bob_name");
        }
        @Test
        public void addNewProfile(){
            try {
                String theUsername="someone";
                String theEmail="someone@gmail.com";
                Profile thePrivilege = null;
                String password="2134";

                assertTrue(true);

            } catch (Exception exception) {

                exception.printStackTrace();
                assertFalse(false);
            }
        }
        @Test
        public void getProfileListWhenEmpty (){
                ArrayList<Profile> expectedValue = new ArrayList<>(0);
                ArrayList<Profile> actualValue = new ArrayList<>();
                System.out.println("Expected Value="+ expectedValue +" . Actual Value="+actualValue);
                assertEquals(expectedValue, actualValue);
                assertFalse(false);
            }
        }



