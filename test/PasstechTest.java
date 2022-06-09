import Authentication.Passtech;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PasstechTest {

    @Test
    public void encryptNumbers(){
        String expectedValue = "202cb962ac59075b964b07152d234b70";
        String password = "123";

        String expectedValue1 = "250cf8b51c773f3f8dc8b4be867a9a02";
        String password1 = "456";

        String expectedValue2 = "698d51a19d8a121ce581499d7b701668";
        String password2 = "111";

        var passtech = new Passtech();
        String actualValue = passtech.encrypt(password);
        String actualValue1 = passtech.encrypt(password1);
        String actualValue2 = passtech.encrypt(password2);

        assertEquals(expectedValue, actualValue);
        assertEquals(expectedValue1, actualValue1);
        assertEquals(expectedValue2, actualValue2);
    }
    @Test
    public void encryptStrings(){
        String expectedValue = "5f4dcc3b5aa765d61d8327deb882cf99";
        String password = "password";

        String expectedValue1 = "18b0a538f4e1a658b7c2d7ad80af9785";
        String password1 = "verylongpasswordthatisabout20characters";

        String expectedValue2 = "0cc175b9c0f1b6a831c399e269772661";
        String password2 = "a";

        var passtech = new Passtech();
        String actualValue = passtech.encrypt(password);
        String actualValue1 = passtech.encrypt(password1);
        String actualValue2 = passtech.encrypt(password2);

        assertEquals(expectedValue, actualValue);
        assertEquals(expectedValue1, actualValue1);
        assertEquals(expectedValue2, actualValue2);
    }

    @Test
    public void encryptStringsWithNumbers(){
        String expectedValue = "482c811da5d5b4bc6d497ffa98491e38";
        String password = "password123";

        String expectedValue1 = "22c14f311a60486b36f79f3bc962be66";
        String password1 = "a1b2c3d4";

        String expectedValue2 = "088ddb0b8cdd8e5fd7d710d1ef0e6dad";
        String password2 = "111aaaabbb22223d";

        var passtech = new Passtech();
        String actualValue = passtech.encrypt(password);
        String actualValue1 = passtech.encrypt(password1);
        String actualValue2 = passtech.encrypt(password2);

        assertEquals(expectedValue, actualValue);
        assertEquals(expectedValue1, actualValue1);
        assertEquals(expectedValue2, actualValue2);
    }


}
