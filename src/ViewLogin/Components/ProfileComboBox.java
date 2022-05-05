package ViewLogin.Components;

import Profile.Profile;
import Profile.ProfileManager;
import javax.swing.*;
import java.util.List;
import java.util.Vector;

public class ProfileComboBox extends JComboBox {

    public ProfileComboBox(List<Profile> theProfileList) {
        super(theProfileList.toArray());
    }
}
