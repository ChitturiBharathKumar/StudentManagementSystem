package studentmanagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DashboardFrame extends JFrame implements ActionListener {

    JLabel titleLabel;

    JButton addButton;
    JButton viewButton;
    JButton updateButton;
    JButton deleteButton;
    JButton searchButton;
    JButton logoutButton;

    public DashboardFrame() {

        setTitle("Student Management System");
        setSize(500, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Title
        titleLabel = new JLabel("Student Management System");
        titleLabel.setBounds(145, 30, 220, 30);
        add(titleLabel);

        // Add Student Button
        addButton = new JButton("Add Student");
        addButton.setBounds(100, 90, 130, 40);
        add(addButton);

        // View Student Button
        viewButton = new JButton("View Students");
        viewButton.setBounds(270, 90, 130, 40);
        add(viewButton);

        // Update Student Button
        updateButton = new JButton("Update Student");
        updateButton.setBounds(100, 150, 130, 40);
        add(updateButton);

        // Delete Student Button
        deleteButton = new JButton("Delete Student");
        deleteButton.setBounds(270, 150, 130, 40);
        add(deleteButton);

        // Search Student Button
        searchButton = new JButton("Search Student");
        searchButton.setBounds(100, 210, 130, 40);
        add(searchButton);

        // Logout Button
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(270, 210, 130, 40);
        add(logoutButton);

        // Action Listeners
        addButton.addActionListener(this);
        viewButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        searchButton.addActionListener(this);
        logoutButton.addActionListener(this);

        // Center the frame
        setLocationRelativeTo(null);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addButton) {

            new AddStudentFrame();

        } else if (e.getSource() == viewButton) {

            new ViewStudentFrame();

        } else if (e.getSource() == updateButton) {

            new UpdateStudentFrame();

        } else if (e.getSource() == deleteButton) {

            new DeleteStudentFrame();

        } else if (e.getSource() == searchButton) {

            new SearchStudentFrame();

        } else if (e.getSource() == logoutButton) {

            dispose();

            new LoginFrame();
        }
    }

    public static void main(String[] args) {

        new DashboardFrame();
    }
}