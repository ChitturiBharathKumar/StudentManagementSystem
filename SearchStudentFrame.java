package studentmanagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SearchStudentFrame extends JFrame implements ActionListener {

    JLabel searchLabel;
    JTextField searchText;

    JButton searchButton;
    JButton clearButton;

    JTable table;
    DefaultTableModel model;

    public SearchStudentFrame() {

        setTitle("Search Student");
        setSize(800, 500);

        setLayout(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Search Label
        searchLabel = new JLabel("Enter Name:");
        searchLabel.setBounds(50, 30, 100, 30);
        add(searchLabel);

        // Search TextField
        searchText = new JTextField();
        searchText.setBounds(150, 30, 250, 30);
        add(searchText);

        // Search Button
        searchButton = new JButton("Search");
        searchButton.setBounds(420, 30, 100, 30);
        add(searchButton);

        // Clear Button
        clearButton = new JButton("Clear");
        clearButton.setBounds(540, 30, 100, 30);
        add(clearButton);

        // Table Model
        model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Email");
        model.addColumn("Phone");
        model.addColumn("Course");
        model.addColumn("Address");

        // Table
        table = new JTable(model);

        // Scroll Pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 90, 720, 330);
        add(scrollPane);

        // Action Listeners
        searchButton.addActionListener(this);
        clearButton.addActionListener(this);

        // Center Frame
        setLocationRelativeTo(null);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == searchButton) {

            searchStudent();
        }

        if (e.getSource() == clearButton) {

            searchText.setText("");

            model.setRowCount(0);
        }
    }

    public void searchStudent() {

        String name = searchText.getText();

        model.setRowCount(0);

        String sql = "SELECT * FROM students WHERE name LIKE ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, "%" + name + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");

                String studentName =
                        rs.getString("name");

                String email =
                        rs.getString("email");

                String phone =
                        rs.getString("phone");

                String course =
                        rs.getString("course");

                String address =
                        rs.getString("address");

                model.addRow(new Object[] {
                        id,
                        studentName,
                        email,
                        phone,
                        course,
                        address
                });
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new SearchStudentFrame();
    }
}