package studentmanagement;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewStudentFrame extends JFrame {

    JTable table;
    DefaultTableModel model;

    public ViewStudentFrame() {

        setTitle("View Students");
        setSize(800, 400);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        // Create table model
        model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Email");
        model.addColumn("Phone");
        model.addColumn("Course");
        model.addColumn("Address");

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        loadStudents();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void loadStudents() {

        String sql = "SELECT * FROM students";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String course = rs.getString("course");
                String address = rs.getString("address");

                model.addRow(new Object[] {
                        id,
                        name,
                        email,
                        phone,
                        course,
                        address
                });
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new ViewStudentFrame();
    }
}