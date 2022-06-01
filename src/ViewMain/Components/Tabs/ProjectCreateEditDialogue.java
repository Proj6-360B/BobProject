package ViewMain.Components.Tabs;

import InstaDialogue.InstaDialogue;
import Profile.Privilege;
import Project.Date;
import Project.Project;
import Project.ProjectManager;
import Project.Status;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;

public class ProjectCreateEditDialogue extends JDialog implements ActionListener {
    private ProjectManager myProjectManager;
    private Project selectedProject;
    private boolean isCreateNew;
    private Privilege currentPrivilege;

    public ProjectCreateEditDialogue(ProjectManager theProjectManager) {
        super(null, "Create New Project", ModalityType.APPLICATION_MODAL);
        myProjectManager = theProjectManager;
        isCreateNew = true;
        addProject();
    }

    public ProjectCreateEditDialogue(ProjectManager theProjectManager, Project theSelectedProject, Privilege thePrivilege) {
        super(null, "Edit/View Project", ModalityType.APPLICATION_MODAL);
        myProjectManager = theProjectManager;
        selectedProject = theSelectedProject;
        isCreateNew = false;
        currentPrivilege = thePrivilege;
        addProject();
    }

    // Components of the Form
    private final String font = "Arial";
    private Container c;
    private JLabel title;
    private JLabel name;
    private JTextField nameText;
    private JLabel typeLabel;
    private JTextField typeText;
    private JLabel projectStatusLabel;
    private JLabel projectDescription;
    private JRadioButton activeButton;//active
    private JRadioButton futureButton;//future
    private JRadioButton completeButton;//completeButton
    private JLabel projectDateLabel;
    private JTextField projectDateYearText;
    private JTextField projectDateMonthText;
    private JTextField projectDateDayText;
    private ButtonGroup statusGroup;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton deleteButton;
//    private JLabel res;
    private JTextArea descTArea;
    private TabDocumentsEditableProjectSpecific filesPanel;

    public void addProject() {
        if (isCreateNew) {
            setTitle("Create New Project");
            setBounds(300, 90, 700, 650);
        } else {
            setTitle("Edit Project");
            setBounds(300, 90, 1100, 650);
        }
        setLocationRelativeTo(null);
//        setAlwaysOnTop(true);//so the user cannot bypass login by clicking off of it
        setResizable(false);//so it doesn't look ugly with a resize
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Cancels

        c = getContentPane();
        c.setLayout(null);

        if (isCreateNew) {
            title = new JLabel("Create New Project");
            title.setFont(new Font(font, Font.PLAIN, 30));
            title.setSize(300, 30);
            title.setLocation(250, 30);
        } else {
            title = new JLabel(selectedProject.getProjectName());
            title.setFont(new Font(font, Font.PLAIN, 30));
            title.setSize(700, 30);
            title.setLocation(50, 30);
        }
        c.add(title);

        name = new JLabel("Project Name");
        name.setFont(new Font(font, Font.PLAIN, 20));
        name.setSize(200, 20);
        name.setLocation(50, 100);
        name.setToolTipText("Enter the name you wish to this remember by.");
        c.add(name);

        nameText = new JTextField();
        nameText.setFont(new Font(font, Font.PLAIN, 15));
        nameText.setSize(190, 20);
        nameText.setLocation(250, 100);
        nameText.setToolTipText("Enter the name you wish to this remember by.");
        c.add(nameText);

        typeLabel = new JLabel("Project Type");
        typeLabel.setFont(new Font(font, Font.PLAIN, 20));
        typeLabel.setSize(200, 20);
        typeLabel.setLocation(50, 150);
        typeLabel.setToolTipText("i.e. Repair, Installation, Assembly, etc.");
        c.add(typeLabel);

        typeText = new JTextField();
        typeText.setFont(new Font(font, Font.PLAIN, 15));
        typeText.setSize(190, 20);
        typeText.setLocation(250, 150);
        typeText.setToolTipText("i.e. Repair, Installation, Assembly, etc.");
        c.add(typeText);

        projectDescription = new JLabel("Description"); //Description Label
        projectDescription.setFont(new Font(font, Font.PLAIN, 30));
        projectDescription.setSize(150, 30);
        projectDescription.setLocation(50, 300);
        projectDescription.setToolTipText("Enter a description or reminders for the project.");
        c.add(projectDescription);

        descTArea = new JTextArea(); //Description Text
        descTArea.setSize(350, 200);
        descTArea.setLocation(250, 300);
        descTArea.setLineWrap(true);
        descTArea.setWrapStyleWord(true);
        descTArea.setToolTipText("Enter a description or reminders for the project.");
        c.add(descTArea);

        projectStatusLabel = new JLabel("Project Status");
        projectStatusLabel.setFont(new Font(font, Font.PLAIN, 20));
        projectStatusLabel.setSize(200, 20);
        projectStatusLabel.setLocation(50, 200);
        c.add(projectStatusLabel);

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

        projectDateLabel = new JLabel("Project Date");
        projectDateLabel.setFont(new Font(font, Font.PLAIN, 20));
        projectDateLabel.setSize(200, 20);
        projectDateLabel.setLocation(50, 250);
        projectDateLabel.setToolTipText("Enter a date for the project. (YYYY/MM/DD)");
        c.add(projectDateLabel);

        projectDateYearText = new JTextField();
        projectDateYearText.setFont(new Font(font, Font.PLAIN, 15));
        projectDateYearText.setSize(40, 20);
        projectDateYearText.setLocation(250, 250);
        projectDateYearText.setToolTipText("YYYY");
        c.add(projectDateYearText);

        JLabel tempSlashLabel = new JLabel("/");
        tempSlashLabel.setFont(new Font(font, Font.PLAIN, 15));
        tempSlashLabel.setSize(5, 20);
        tempSlashLabel.setLocation(290, 250);
        c.add(tempSlashLabel);

        projectDateMonthText = new JTextField();
        projectDateMonthText.setFont(new Font(font, Font.PLAIN, 15));
        projectDateMonthText.setSize(25, 20);
        projectDateMonthText.setLocation(295, 250);
        projectDateMonthText.setToolTipText("MM");
        c.add(projectDateMonthText);

        tempSlashLabel = new JLabel("/");
        tempSlashLabel.setFont(new Font(font, Font.PLAIN, 15));
        tempSlashLabel.setSize(5, 20);
        tempSlashLabel.setLocation(295 + 25, 250);
        c.add(tempSlashLabel);

        projectDateDayText = new JTextField();
        projectDateDayText.setFont(new Font(font, Font.PLAIN, 15));
        projectDateDayText.setSize(25, 20);
        projectDateDayText.setLocation(325, 250);
        projectDateDayText.setToolTipText("DD");
        c.add(projectDateDayText);

        statusGroup = new ButtonGroup();
        statusGroup.add(activeButton);
        statusGroup.add(futureButton);
        statusGroup.add(completeButton);

        if (isCreateNew) {
            setToCurrentDate();
        } else {
            setTextFromProject();
        }

        saveButton = new JButton("Save");
        saveButton.setFont(new Font(font, Font.PLAIN, 15));
        saveButton.setSize(100, 20);
        saveButton.setLocation(265, 550);
        saveButton.addActionListener(this);
        c.add(saveButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font(font, Font.PLAIN, 15));
        cancelButton.setSize(100, 20);
        cancelButton.setLocation(425, 550);
        cancelButton.addActionListener(this);
        c.add(cancelButton);

        deleteButton = new JButton("Delete");//to prevent null pointer, won't be visible to user
        if (!isCreateNew) {
            //deleteButton = new JButton("Delete");
            deleteButton.setFont(new Font(font, Font.PLAIN, 15));
            deleteButton.setSize(100, 20);
            deleteButton.setLocation(100, 550);
            deleteButton.addActionListener(this);
            c.add(deleteButton);
        }

        if (!isCreateNew) { //Attached File Explorer
            filesPanel = new TabDocumentsEditableProjectSpecific(selectedProject, currentPrivilege);
            filesPanel.setSize(400, 550);
            filesPanel.setLocation(640, 20);
            add(filesPanel);
        }

        //Privilege Handling
        if (currentPrivilege != Privilege.ADMIN) {
            saveButton.setEnabled(false);
            deleteButton.setEnabled(false);
            cancelButton.setText("Close");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            if (nameText.getText().isEmpty()) {
                InstaDialogue.showErrorMessage("Invalid Project Name.");
                return;
            }
            if (typeText.getText().isEmpty()) {
                InstaDialogue.showErrorMessage("Invalid Project Type.");
                return;
            }
            if (projectDateYearText.getText().isEmpty() || projectDateMonthText.getText().isEmpty() || projectDateDayText.getText().isEmpty()) {
                InstaDialogue.showErrorMessage("Invalid Project Date.");
                return;
            }
            Status selectedStatus;
            if (futureButton.isSelected()) {
                selectedStatus = Status.FUTURE;
            } else if (activeButton.isSelected()) {
                selectedStatus = Status.ACTIVE;
            } else {
                selectedStatus = Status.COMPLETE;
            }

            if (isCreateNew) {
                try { //try create. if fail, send them back bruv
                    myProjectManager.addNewProject(nameText.getText(), descTArea.getText(), typeText.getText(), selectedStatus, new Date(projectDateYearText.getText(), projectDateMonthText.getText(), projectDateDayText.getText()));
                    myProjectManager.writeProjects();
                } catch (Exception ex) {
                    InstaDialogue.showErrorMessage("Something went wrong while saving:\n" + ex.getMessage());
                    return;
                }
            } else {
                selectedProject.setProjectType(typeText.getText());
                selectedProject.setProjectDate(new Date(projectDateYearText.getText(), projectDateMonthText.getText(), projectDateDayText.getText()));
                selectedProject.setProjectDescription(descTArea.getText());
                selectedProject.setProjectStatus(selectedStatus);
                try {
                    selectedProject.renameProject(nameText.getText());
                } catch (IOException ex) {
                    InstaDialogue.showErrorMessage("Something went wrong while saving:\n" + ex.getMessage());
                    return;
                }
            }
            //Success!
            dispose();
        } else if (e.getSource() == cancelButton) {
            dispose();
        } else if (e.getSource() == deleteButton) {
            if (InstaDialogue.showYesNoConfirmation("This will permanently delete the project!") == 1) return; //cancels
            myProjectManager.deleteProject(selectedProject);
            dispose();
        }
    }

    private void setToCurrentDate() {
        String tempDate = LocalDate.now().toString();
        projectDateYearText.setText(tempDate.substring(0, 4));
        projectDateMonthText.setText(tempDate.substring(5, 7));
        projectDateDayText.setText(tempDate.substring(8, 10));
    }

    private void setTextFromProject() {
        nameText.setText(selectedProject.getProjectName());
        typeText.setText(selectedProject.getProjectType());
        String tempDate = selectedProject.getProjectDate().toString();
        projectDateYearText.setText(tempDate.substring(0, 4));
        projectDateMonthText.setText(tempDate.substring(5, 7));
        projectDateDayText.setText(tempDate.substring(8, 10));
        descTArea.setText(selectedProject.getProjectDescription());
        switch (selectedProject.getProjectStatus()) {
            case ACTIVE -> statusGroup.setSelected(activeButton.getModel(), true);
            case FUTURE -> statusGroup.setSelected(futureButton.getModel(), true);
            case COMPLETE -> statusGroup.setSelected(completeButton.getModel(), true);
        }
    }
}
