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

public class UpdateStudentFrame extends JFrame implements ActionListener {

    JLabel idLabel;
    JLabel nameLabel;
    JLabel emailLabel;
    JLabel phoneLabel;
    JLabel courseLabel;
    JLabel addressLabel;

    JTextField idText;
    JTextField nameText;
    JTextField emailText;
    JTextField phoneText;
    JTextField courseText;
    JTextField addressText;

    JButton updateButton;
    JButton clearButton;

    public UpdateStudentFrame() {

        setTitle("Update Student");
        setSize(450, 450);

        setLayout(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Student ID
        idLabel = new JLabel("Student ID:");
        idLabel.setBounds(60, 30, 100, 30);
        add(idLabel);

        idText = new JTextField();
        idText.setBounds(170, 30, 200, 30);
        add(idText);

        // Name
        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(60, 80, 100, 30);
        add(nameLabel);

        nameText = new JTextField();
        nameText.setBounds(170, 80, 200, 30);
        add(nameText);

        // Email
        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(60, 130, 100, 30);
        add(emailLabel);

        emailText = new JTextField();
        emailText.setBounds(170, 130, 200, 30);
        add(emailText);

        // Phone
        phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(60, 180, 100, 30);
        add(phoneLabel);

        phoneText = new JTextField();
        phoneText.setBounds(170, 180, 200, 30);
        add(phoneText);

        // Course
        courseLabel = new JLabel("Course:");
        courseLabel.setBounds(60, 230, 100, 30);
        add(courseLabel);

        courseText = new JTextField();
        courseText.setBounds(170, 230, 200, 30);
        add(courseText);

        // Address
        addressLabel = new JLabel("Address:");
        addressLabel.setBounds(60, 280, 100, 30);
        add(addressLabel);

        addressText = new JTextField();
        addressText.setBounds(170, 280, 200, 30);
        add(addressText);

        // Update Button
        updateButton = new JButton("Update");
        updateButton.setBounds(120, 340, 100, 35);
        add(updateButton);

        // Clear Button
        clearButton = new JButton("Clear");
        clearButton.setBounds(240, 340, 100, 35);
        add(clearButton);

        // Action Listeners
        updateButton.addActionListener(this);
        clearButton.addActionListener(this);

        // Center Frame
        setLocationRelativeTo(null);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == updateButton) {

            String id = idText.getText();
            String name = nameText.getText();
            String email = emailText.getText();
            String phone = phoneText.getText();
            String course = courseText.getText();
            String address = addressText.getText();

            String sql = "UPDATE students SET name=?, email=?, "
                    + "phone=?, course=?, address=? WHERE id=?";

            try {

                Connection con = DBConnection.getConnection();

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, phone);
                ps.setString(4, course);
                ps.setString(5, address);
                ps.setInt(6, Integer.parseInt(id));

                int result = ps.executeUpdate();

                if (result > 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Student Updated Successfully"
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Student ID Not Found"
                    );
                }

                ps.close();
                con.close();

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        this,
                        "Error while updating student"
                );
            }
        }

        if (e.getSource() == clearButton) {

            idText.setText("");
            nameText.setText("");
            emailText.setText("");
            phoneText.setText("");
            courseText.setText("");
            addressText.setText("");
        }
    }

    public static void main(String[] args) {

        new UpdateStudentFrame();
    }
}