package ViewMain.Components.Tabs;

import ViewMain.Components.NewProject;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabActiveProject extends JPanel {
    //TODO components to fields if other classes need to access.

    public TabActiveProject() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Create New, Separator, Search
        JPanel searchPanel = new JPanel();
        searchPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20)); //Clamp vertical size
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.LINE_AXIS));

        JButton createNew = new JButton("Create New Project");
        createNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
        searchPanel.add(createNew);

        JLabel searchLabel = new JLabel("      Search Name: ", SwingConstants.TRAILING);
        searchPanel.add(searchLabel);

        JTextField searchText = new JTextField();
        searchPanel.add(searchText);

        add(searchPanel);

        //Scroll Pane & Table
        add(new ScrollTableProjects());
    }
}
