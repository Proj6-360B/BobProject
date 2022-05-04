package Scratch.View;

import javax.swing.*;
import java.awt.*;

public class mainGui extends JFrame {
    private static final int SIZE_WIDTH = 900;//default starting width
    private static final int SIZE_HEIGHT = 500;//default starting height
    final JMenuItem exit = new JMenuItem("exit");//menu item for exiting

    public mainGui() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);//so we can close the window with the windows exit button
        setSize(SIZE_WIDTH, SIZE_HEIGHT);//setting the default size
        var menuBar = new JMenuBar();//this is where menu items will live
        setJMenuBar(menuBar);
        JPanel mainPanel = new JPanel();// main container for everything to sit in
        this.add(mainPanel);
        mainPanel.setBackground(Color.white);//whatever  background color we want


        menuBar.add(exit);//so you can exit the window from the menu bar




        exit.addActionListener(
                e -> System.exit(0)
        );


    }
}
