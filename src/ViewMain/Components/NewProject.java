package ViewMain.Components;

import ViewMain.Components.Tabs.TabActiveProject;
import projectManager.ProjectManager;
import projectManager.Status;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewProject extends TabActiveProject implements ActionListener {


        private ProjectManager myProjectManager = new ProjectManager();

        // Components of the Form
        private final String font = "Arial";
        private Container c;
        private JLabel title;
        private JLabel name;
        private JTextField tname;
        private JLabel email;
        private JTextField emailText;
        private JLabel projectStatusLabel;
        private JLabel projectDescription;
        private JRadioButton activeButton;//active
        private JRadioButton futureButton;//future
        private JRadioButton completeButton;//completeButton
        private ButtonGroup statusGroup;
        private JButton saveButton;
        private JButton resetButton;
        private JLabel res;
        private JDialog dialog;
        private JTextArea tArea;



        public void addProject() {

            dialog = new JDialog();

            dialog.setTitle("Create New Project");
            dialog.setBounds(300, 90, 750, 650);
            dialog.setAlwaysOnTop(true);//so the user cannot bypass login by clicking off of it
            dialog.setResizable(false);//so it doesn't look ugly with a resize
            dialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//so you can't avoid login

            c = dialog.getContentPane();
            c.setLayout(null);

            title = new JLabel("Create New Project");
            title.setFont(new Font(font, Font.PLAIN, 30));
            title.setSize(300, 30);
            title.setLocation(250, 30);
            c.add(title);

            name = new JLabel("Project Name");
            name.setFont(new Font(font, Font.PLAIN, 20));
            name.setSize(200, 20);
            name.setLocation(50, 100);
            c.add(name);

            tname = new JTextField();
            tname.setFont(new Font(font, Font.PLAIN, 15));
            tname.setSize(190, 20);
            tname.setLocation(250, 100);
            c.add(tname);

            email = new JLabel("Project type");
            email.setFont(new Font(font, Font.PLAIN, 20));
            email.setSize(200, 20);
            email.setLocation(50, 150);
            c.add(email);

            emailText = new JTextField();
            emailText.setFont(new Font(font, Font.PLAIN, 15));
            emailText.setSize(190, 20);
            emailText.setLocation(250, 150);
            c.add(emailText);

            projectStatusLabel = new JLabel("Project Status");
            projectStatusLabel.setFont(new Font(font, Font.PLAIN, 20));
            projectStatusLabel.setSize(200, 20);
            projectStatusLabel.setLocation(50, 200);
            c.add(projectStatusLabel);

            projectDescription = new JLabel("Project Description");
            projectDescription.setFont(new Font(font, Font.PLAIN, 30));
            projectDescription.setSize(300, 30);
            projectDescription.setLocation(300, 250);
            c.add(projectDescription);

            activeButton = new JRadioButton("Active");
            activeButton.setFont(new Font(font, Font.PLAIN, 15));
            activeButton.setSelected(true);
            activeButton.setSize(75, 20);
            activeButton.setLocation(250, 200);
            c.add(activeButton);

            futureButton = new JRadioButton("Future");
            futureButton.setFont(new Font(font, Font.PLAIN, 15));
            futureButton.setSelected(false);
            futureButton.setSize(80, 20);
            futureButton.setLocation(350, 200);
            c.add(futureButton);

            completeButton = new JRadioButton("Complete");
            completeButton.setFont(new Font(font, Font.PLAIN, 15));
            completeButton.setSelected(false);
            completeButton.setSize(120, 20);
            completeButton.setLocation(450, 200);
            c.add(completeButton);

            tArea = new JTextArea();
            tArea.setSize(350,200);
            tArea.setLocation(250,300);
            c.add(tArea);

            statusGroup = new ButtonGroup();
            statusGroup.add(activeButton);
            statusGroup.add(futureButton);
            statusGroup.add(completeButton);

            saveButton = new JButton("Save");
            saveButton.setFont(new Font(font, Font.PLAIN, 15));
            saveButton.setSize(100, 20);
            saveButton.setLocation(250, 550);
            saveButton.addActionListener(this);
            c.add(saveButton);

            resetButton = new JButton("Cancel");
            resetButton.setFont(new Font(font, Font.PLAIN, 15));
            resetButton.setSize(100, 20);
            resetButton.setLocation(450, 550);
            resetButton.addActionListener(this);
            c.add(resetButton);


            res = new JLabel("");
            res.setFont(new Font(font, Font.PLAIN, 20));
            res.setSize(500, 25);
            res.setLocation(100, 350);
            c.add(res);

            dialog.setVisible(true);
        }


        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == saveButton) {
                //todo add inout verification so it wont take empty strings
                Status s;
                if (futureButton.isSelected()) {
                    s = Status.FUTURE;
                } else if (activeButton.isSelected()) {
                    s = Status.ACTIVE;
                } else {
                    s = Status.COMPLETE;
                }


                myProjectManager.addNewProject(tname.getText(), emailText.getText(), s);
                myProjectManager.writeProjects();
                addProjectButton(tname.getText());

                //new NewProject(myProjectManager);//reopen login screen
                dialog.dispose();
            } else if (e.getSource() == resetButton) {
                //new NewProject(myProjectManager);//reopen login screen
                dialog.dispose();
            }
        }
    }
