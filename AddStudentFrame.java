package studentmanagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddStudentFrame extends JFrame implements ActionListener {

    JLabel nameLabel;
    JLabel emailLabel;
    JLabel phoneLabel;
    JLabel courseLabel;
    JLabel addressLabel;

    JTextField nameText;
    JTextField emailText;
    JTextField phoneText;
    JTextField courseText;
    JTextField addressText;

    JButton saveButton;
    JButton clearButton;

    public AddStudentFrame() {

        setTitle("Add Student");
        setSize(450, 400);
        setLayout(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Name
        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(60, 40, 100, 30);
        add(nameLabel);

        nameText = new JTextField();
        nameText.setBounds(170, 40, 200, 30);
        add(nameText);

        // Email
        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(60, 90, 100, 30);
        add(emailLabel);

        emailText = new JTextField();
        emailText.setBounds(170, 90, 200, 30);
        add(emailText);

        // Phone
        phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(60, 140, 100, 30);
        add(phoneLabel);

        phoneText = new JTextField();
        phoneText.setBounds(170, 140, 200, 30);
        add(phoneText);

        // Course
        courseLabel = new JLabel("Course:");
        courseLabel.setBounds(60, 190, 100, 30);
        add(courseLabel);

        courseText = new JTextField();
        courseText.setBounds(170, 190, 200, 30);
        add(courseText);

        // Address
        addressLabel = new JLabel("Address:");
        addressLabel.setBounds(60, 240, 100, 30);
        add(addressLabel);

        addressText = new JTextField();
        addressText.setBounds(170, 240, 200, 30);
        add(addressText);

        // Save Button
        saveButton = new JButton("Save");
        saveButton.setBounds(120, 300, 100, 35);
        add(saveButton);

        // Clear Button
        clearButton = new JButton("Clear");
        clearButton.setBounds(240, 300, 100, 35);
        add(clearButton);

        // Action Listeners
        saveButton.addActionListener(this);
        clearButton.addActionListener(this);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == saveButton) {

            String name = nameText.getText();
            String email = emailText.getText();
            String phone = phoneText.getText();
            String course = courseText.getText();
            String address = addressText.getText();

            String sql = "INSERT INTO students "
                    + "(name, email, phone, course, address) "
                    + "VALUES (?, ?, ?, ?, ?)";

            try {

                Connection con = DBConnection.getConnection();

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, phone);
                ps.setString(4, course);
                ps.setString(5, address);

                int result = ps.executeUpdate();

                if (result > 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Student Added Successfully"
                    );

                    nameText.setText("");
                    emailText.setText("");
                    phoneText.setText("");
                    courseText.setText("");
                    addressText.setText("");
                }

                ps.close();
                con.close();

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        this,
                        "Error while adding student"
                );
            }
        }

        if (e.getSource() == clearButton) {

            nameText.setText("");
            emailText.setText("");
            phoneText.setText("");
            courseText.setText("");
            addressText.setText("");
        }
    }

    public static void main(String[] args) {

        new AddStudentFrame();
    }
}