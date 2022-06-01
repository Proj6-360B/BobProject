package ViewLogin.Components;

import Profile.Profile;

import javax.swing.*;
import java.util.List;

public class ProfileComboBox extends JComboBox {

    public ProfileComboBox(List<Profile> theProfileList) {
        super(theProfileList.toArray());
    }
}
