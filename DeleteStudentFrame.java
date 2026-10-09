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

public class DeleteStudentFrame extends JFrame implements ActionListener {

    JLabel idLabel;
    JTextField idText;

    JButton deleteButton;
    JButton clearButton;

    public DeleteStudentFrame() {

        setTitle("Delete Student");
        setSize(400, 250);

        setLayout(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Student ID Label
        idLabel = new JLabel("Student ID:");
        idLabel.setBounds(50, 50, 100, 30);
        add(idLabel);

        // Student ID TextField
        idText = new JTextField();
        idText.setBounds(160, 50, 180, 30);
        add(idText);

        // Delete Button
        deleteButton = new JButton("Delete");
        deleteButton.setBounds(90, 120, 100, 35);
        add(deleteButton);

        // Clear Button
        clearButton = new JButton("Clear");
        clearButton.setBounds(210, 120, 100, 35);
        add(clearButton);

        // Action Listeners
        deleteButton.addActionListener(this);
        clearButton.addActionListener(this);

        // Center Frame
        setLocationRelativeTo(null);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == deleteButton) {

            String id = idText.getText();

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this student?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {

                String sql = "DELETE FROM students WHERE id=?";

                try {

                    Connection con = DBConnection.getConnection();

                    PreparedStatement ps =
                            con.prepareStatement(sql);

                    ps.setInt(1, Integer.parseInt(id));

                    int result = ps.executeUpdate();

                    if (result > 0) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Student Deleted Successfully"
                        );

                        idText.setText("");

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
                            "Error while deleting student"
                    );
                }
            }
        }

        if (e.getSource() == clearButton) {

            idText.setText("");
        }
    }

    public static void main(String[] args) {

        new DeleteStudentFrame();
    }
}