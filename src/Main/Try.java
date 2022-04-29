package Main;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Try {
    Frame a = new Frame();
}


class Frame implements ActionListener, MenuListener, AncestorListener {
    private JFrame frame;
    private JPanel panel, decCalc, binaryCalc, hexCalc, bigNumCalc, convertor, exitPanel;
    private JLabel label;
    private JButton decButton;
    private JButton bigNumButton;
    private JButton binaryButton;
    private JButton hexButton;
    private JButton convertorButton;
    private JButton exitButton;
    private JTextField textField, bigTextField1, bigTextField2,convertorTextField,
            decTextField1, decTextField2,binaryTextField1, binaryTextField2, hexTextField1, hexTextField2;
    private JMenuBar menuBar;
    private JMenu calc;
    private JMenu conv;
    private JMenu help;
    private JMenu about;
    private JMenuItem start;
    private JMenuItem exit;
    private JMenuItem binaryConverter;
    private JMenuItem hexConverter;
    private JMenuItem info;
    private JMenuItem instructions;
    private JTabbedPane tabbedPane;
    private JTextArea decAnswerBox, bigAnswerBox, binaryAnswerBox,hexAnswerBox,convertorAnswerBox;
    private JComboBox<String> decCombo;
    private JComboBox<String> comboBig;
    private JComboBox<String> comboBinary;
    private JComboBox<String> comboHex;
    private JComboBox<String> convertorCombo;

    //this frame method makes up the main window that holds the three menus on the menu bar.
    public Frame() {
        outerWindow();
    }



    private void outerWindow() {
        outerFrame();
        calc = new JMenu("Program");
        help = new JMenu("Help");
        about = new JMenu("About");
        instructions = new JMenuItem("Instructions");
        KeyStroke H = KeyStroke.getKeyStroke(KeyEvent.VK_H,KeyEvent.CTRL_DOWN_MASK);
        instructions.setAccelerator(H);
        instructions.addActionListener(this);
        info = new JMenuItem("Info");
        KeyStroke I = KeyStroke.getKeyStroke(KeyEvent.VK_I,KeyEvent.CTRL_DOWN_MASK);
        info.setAccelerator(I);
        info.addActionListener(this);
        menuBar.add(calc);

        menuBar.add(help);
        help.add(instructions);
        menuBar.add(about);
        about.add(info);
        start = new JMenuItem("Start");
        KeyStroke S = KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK);
        start.setAccelerator(S);
        exit = new JMenuItem("Exit");
        KeyStroke E = KeyStroke.getKeyStroke(KeyEvent.VK_E,KeyEvent.CTRL_DOWN_MASK);
        exit.setAccelerator(E);
        binaryConverter = new JMenuItem("Active Projects");
        hexConverter = new JMenuItem("Documents");

        hexConverter.addActionListener(this);
        binaryConverter.addActionListener(this);

        calc.add(start);
        calc.add(exit);
        help.addMenuListener(this);
        exit.addActionListener(this);
        about.addActionListener(this);
        start.addActionListener(this);

        label = new JLabel();
        textField = new JTextField(30);

        frame.add(menuBar, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    //this method creates a decimal input window with two text fields and a combo box for choosing operator
    private JPanel decPanelTemplate() {

        JPanel decPanelTemplate = new JPanel();

        JLabel label1 = new JLabel("Name");
        decTextField1 = new JTextField(30);
        decCombo = new JComboBox<>(new String[]{"..", "..", "..", ".."});
        decCombo.addAncestorListener(this);
        JLabel label2 = new JLabel("...");
        decTextField2 = new JTextField(30);

        decTextField1.addActionListener(this);
        decTextField1.addAncestorListener(this);
        decTextField2.addActionListener(this);
        decTextField2.addAncestorListener(this);


//        decPanelTemplate.add(label1);
//        decPanelTemplate.add(decTextField1);
//        decPanelTemplate.add(decCombo);
//        decPanelTemplate.add(label2);
//        decPanelTemplate.add(decTextField2);

        decAnswerBox = new JTextArea(10, 50);
        decAnswerBox.addAncestorListener(this);

        decPanelTemplate.add(decButton);

        decPanelTemplate.setVisible(true);
        return decPanelTemplate;
    }

    //this method creates a big number input window with two text fields and a combo box for choosing operator
    private JPanel bigPanelTemplate() {

        JPanel bigPanelTemplate = new JPanel();

        JLabel label1 = new JLabel("...");
        bigTextField1 = new JTextField(35);
        bigNumCalc.add(new JLabel("..."), BorderLayout.NORTH);
        comboBig = new JComboBox<>(new String[]{"Add", "Add", "Add", "Add", "Add", "Add", "Add"});
        comboBig.addAncestorListener(this);
        JLabel label2 = new JLabel("Add File");
        bigTextField2 = new JTextField(35);

        bigTextField1.addActionListener(this);
        bigTextField1.addAncestorListener(this);
        bigTextField2.addActionListener(this);
        bigTextField2.addAncestorListener(this);


//        bigPanelTemplate.add(label1);
//        bigPanelTemplate.add(bigTextField1);
//        bigPanelTemplate.add(comboBig);
//        bigPanelTemplate.add(label2);
//        bigPanelTemplate.add(bigTextField2);

        bigAnswerBox = new JTextArea(10, 50);
        bigAnswerBox.addAncestorListener(this);


        bigPanelTemplate.add(bigNumButton);


        bigPanelTemplate.setVisible(true);
        return bigPanelTemplate;
    }

    //this method creates a Binary input window with two text fields and a combo box for choosing operator
    private JPanel binaryPanelTemplate() {

        JPanel binaryNanelTemplate = new JPanel();

        JLabel label1 = new JLabel("Add File");
        binaryTextField1 = new JTextField(30);
        binaryCalc.add(new JLabel("Add File"), BorderLayout.NORTH);
        comboBinary = new JComboBox<>(new String[]{"Add File", "Add File", "Add File", "Add File"});
        comboBinary.addAncestorListener(this);
        JLabel label2 = new JLabel("Add File");
        binaryTextField2 = new JTextField(30);

        binaryTextField1.addActionListener(this);
        binaryTextField1.addAncestorListener(this);
        binaryTextField2.addActionListener(this);
        binaryTextField2.addAncestorListener(this);


//        binaryNanelTemplate.add(label1);
//        binaryNanelTemplate.add(binaryTextField1);
//        binaryNanelTemplate.add(comboBinary);
//        binaryNanelTemplate.add(label2);
//        binaryNanelTemplate.add(binaryTextField2);

        binaryAnswerBox = new JTextArea(10, 50);
        binaryAnswerBox.addAncestorListener(this);

        binaryNanelTemplate.add(binaryButton);

        binaryNanelTemplate.setVisible(true);
        return binaryNanelTemplate;
    }

    //this method creates a Hexadecimal input window with two text fields and a combo box for choosing operator
    private JPanel hexPanelTemplate() {

        JPanel hexPanelTemplate = new JPanel();

        JLabel label1 = new JLabel("Add File");
        hexTextField1 = new JTextField(30);
        hexCalc.add(new JLabel("Add File"), BorderLayout.NORTH);
        comboHex = new JComboBox<>(new String[]{"Add File", "Add File", "Add File", "Add File"});
        comboHex.addAncestorListener(this);
        JLabel label2 = new JLabel("Add File");
        hexTextField2 = new JTextField(30);

        hexTextField1.addActionListener(this);
        hexTextField1.addAncestorListener(this);
        hexTextField2.addActionListener(this);
        hexTextField2.addAncestorListener(this);


//        hexPanelTemplate.add(label1);
//        hexPanelTemplate.add(hexTextField1);
//        hexPanelTemplate.add(comboHex);
//        hexPanelTemplate.add(label2);
//        hexPanelTemplate.add(hexTextField2);

        hexAnswerBox = new JTextArea(10, 50);
        hexAnswerBox.addAncestorListener(this);

        hexPanelTemplate.add(hexButton);

        hexPanelTemplate.setVisible(true);
        return hexPanelTemplate;
    }

    //this method creates a convertor input window with two text fields and a combo box for choosing operator
    private JPanel convertorPanelTemplate() {

        JPanel convertorPanelTemplate = new JPanel();

        JLabel label1 = new JLabel("Add File");
        convertorTextField = new JTextField(30);
        convertorCombo = new JComboBox<>(new String[]{"Active Projects", "Documents", "Location", "Appliances"});
        convertorCombo.addAncestorListener(this);

        convertorTextField.addActionListener(this);
        convertorTextField.addAncestorListener(this);


        convertorPanelTemplate.add(label1);
        convertorPanelTemplate.add(convertorTextField);
        convertorPanelTemplate.add(convertorCombo);


        convertorAnswerBox = new JTextArea(10, 50);
        convertorAnswerBox.addAncestorListener(this);

        convertorPanelTemplate.add(convertorButton);

        convertorPanelTemplate.setVisible(true);
        return convertorPanelTemplate;
    }

    //this creates the window that opens when we click start,
    // it holds the tabs for choosing the calculator type.
    // it adds the above windows to each tab
    private void innerWindow() {
        outerFrame();
        tabbedPane = new JTabbedPane();
        frame.add(tabbedPane);
        decButton = new JButton("Add file");
        bigNumButton = new JButton("Add File");
        binaryButton = new JButton("Add File");
        hexButton = new JButton("Add+");
        convertorButton = new JButton("Add+");
        exitButton = new JButton("Exit");

        //set big number calculator panel
        decCalc = new JPanel();
        decButton.addAncestorListener(this);
        decButton.addActionListener(this);
        decCalc.add(new JLabel("Add +"), BorderLayout.NORTH);
        decCalc.add(decPanelTemplate(), BorderLayout.NORTH);
        decCalc.add(decButton, BorderLayout.CENTER);
        decCalc.add(decAnswerBox, BorderLayout.SOUTH);
        decCalc.addAncestorListener(this);


        //set big number calculator panel
        bigNumCalc = new JPanel();
        bigNumButton.addActionListener(this);
        bigNumButton.addAncestorListener(this);
        bigNumCalc.add(bigPanelTemplate(), BorderLayout.NORTH);
        bigNumCalc.add(bigNumButton, BorderLayout.CENTER);
        bigNumCalc.add(bigAnswerBox, BorderLayout.SOUTH);
        bigNumCalc.addAncestorListener(this);


        //set binary number calculator panel
        binaryCalc = new JPanel();
        binaryButton.addAncestorListener(this);
        binaryButton.addActionListener(this);
        binaryCalc.add(binaryPanelTemplate(), BorderLayout.NORTH);
        binaryCalc.add(binaryButton, BorderLayout.CENTER);
        binaryCalc.add(binaryAnswerBox, BorderLayout.SOUTH);
        binaryCalc.addAncestorListener(this);


        //set hex number calculator panel
        hexCalc = new JPanel();
        hexButton.addAncestorListener(this);
        hexButton.addActionListener(this);
        hexCalc.add(hexPanelTemplate(), BorderLayout.NORTH);
        hexCalc.add(hexButton, BorderLayout.CENTER);
        hexCalc.add(hexAnswerBox, BorderLayout.SOUTH);
        hexCalc.addAncestorListener(this);


//set convertor panel
        convertor = new JPanel();
        convertorButton.addAncestorListener(this);
        convertorButton.addActionListener(this);
        convertor.add(convertorPanelTemplate(), BorderLayout.NORTH);
        convertor.add(convertorButton, BorderLayout.CENTER);
        convertor.add(convertorAnswerBox, BorderLayout.SOUTH);
        convertor.addAncestorListener(this);


        //set exit panel
        exitPanel = new JPanel();
        exitButton.addActionListener(this);
        exitPanel.add(exitButton, BorderLayout.CENTER);
        exitPanel.addAncestorListener(this);

        //adds each panel to the tabs
        tabbedPane.add("Active Projects", decCalc);
        tabbedPane.add("Documents", bigNumCalc);
        tabbedPane.add("Location", binaryCalc);
        tabbedPane.add("Appliances", hexCalc);
        tabbedPane.add("Archives", convertor);
        tabbedPane.add("Exit", exitPanel);

        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // this creates the main frame where the tabs will be the basis for the GUI
    // it is reused for the tabs window
    private void outerFrame() {

        frame = new JFrame();
        frame.setTitle("Add +");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(960, 800));
        frame.setLayout(new BorderLayout(10, 10));
        frame.setLocationRelativeTo(null);

        menuBar = new JMenuBar();
        panel = new JPanel();

        frame.setVisible(true);
    }

    // overrides the action performed method
    // sets action events and assign a action performed for each element with action listeners
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == exit) {
            System.exit(0);
        } else if (e.getSource() == start) {
            innerWindow();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        } else if(e.getSource()==instructions){
            JPanel panel1 = new JPanel();
            JFrame frab = new JFrame();
            frab.add(panel1);
            frab.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frab.setSize(new Dimension(400, 400));
            frab.setLocationRelativeTo(null);
            panel1.add(new JTextArea("How To use this this app" +
                    "\n-\n-\n-\n-\n-"));
            panel1.setVisible(true);
            frab.setVisible(true);
        }else if(e.getSource()==info){
            JPanel panel1 = new JPanel();
            panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
            JFrame frab = new JFrame();
            frab.add(panel1);
            frab.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frab.setSize(new Dimension(400, 110));
            frab.setLocationRelativeTo(null);
            JTextArea aboutUS = new JTextArea(
                    "Current User: " + "TODO" +  "\n\nProject6 Team:\n Damien Cruz, Abdulmuen Fethi, David Huynh, Andrew Nguyen\n"); //TODO display user's name
            aboutUS.setEditable(false);
//            aboutUS.setSize(300,300);
            panel1.add(aboutUS);
            panel1.setVisible(true);
            frab.setVisible(true);
        }
    }
    // overrides the menu Selected method
    // activates when menu with no item is selected
    @Override
    public void menuSelected(MenuEvent e) {


    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
    //creates ancestor event for the tabbed panes with ancestor Listeners
    @Override
    public void ancestorAdded(AncestorEvent event) {

    }

    @Override
    public void ancestorRemoved(AncestorEvent event) {

    }

    @Override
    public void ancestorMoved(AncestorEvent event) {

    }

    public JMenu getConv() {
        return conv;
    }

    public void setConv(JMenu conv) {
        this.conv = conv;
    }
}
