package Main.GUIComponents;

import javax.swing.*;
import java.awt.*;

public class CompMenuBar extends JMenuBar {
    //Menus
    private JMenu myAboutMenu;

    public CompMenuBar() {
        //About
        myAboutMenu = new JMenu("About");
        //TODO About Frame/Popup
        add(myAboutMenu);

        //Properties
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    }
}
