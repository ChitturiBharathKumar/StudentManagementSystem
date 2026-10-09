package studentmanagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame implements ActionListener {

    JLabel userLabel, passLabel;
    JTextField userText;
    JPasswordField passText;
    JButton loginButton, clearButton;

    public LoginFrame() {

        setTitle("Student Management System - Login");
        setSize(450, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Username Label
        userLabel = new JLabel("Username:");
        userLabel.setBounds(70, 60, 100, 30);
        add(userLabel);

        // Username TextField
        userText = new JTextField();
        userText.setBounds(180, 60, 200, 30);
        add(userText);

        // Password Label
        passLabel = new JLabel("Password:");
        passLabel.setBounds(70, 110, 100, 30);
        add(passLabel);

        // Password Field
        passText = new JPasswordField();
        passText.setBounds(180, 110, 200, 30);
        add(passText);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setBounds(120, 170, 100, 35);
        add(loginButton);

        // Clear Button
        clearButton = new JButton("Clear");
        clearButton.setBounds(240, 170, 100, 35);
        add(clearButton);

        // Add ActionListener
        loginButton.addActionListener(this);
        clearButton.addActionListener(this);

        // Center the Frame
        setLocationRelativeTo(null);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginButton) {

            String username = userText.getText();
            String password = new String(passText.getPassword());

            String sql = "SELECT * FROM users WHERE username=? AND password=?";

            try {

                Connection con = DBConnection.getConnection();

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, username);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Login Successful"
                    );

                    dispose();

                    new DashboardFrame();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Invalid Username or Password"
                    );
                }

                rs.close();
                ps.close();
                con.close();

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        this,
                        "Database Error"
                );
            }
        }

        if (e.getSource() == clearButton) {

            userText.setText("");
            passText.setText("");
        }
    }

    public static void main(String[] args) {

        new LoginFrame();
    }
}
