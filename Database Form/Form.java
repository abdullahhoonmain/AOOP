import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Form extends JFrame {

    private Connection con;
    private JButton insertButton, updateButton, deleteButton;
    private JTextField nameText, marksText, subjectText, rollNoText;
    private JTable dataTable;

    public Form() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaformdb", "root", ""); // Ensure this is correct
            initComponents();
            DisplayData(); // Call DisplayData only after connection is established
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, "Database connection error: ", ex);
            JOptionPane.showMessageDialog(this, "Error connecting to database: " + ex.getMessage());
            System.exit(1);
        }

        this.setVisible(true);
        this.setSize(800, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        insertButton = new JButton("Insert");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");

        nameText = new JTextField();
        marksText = new JTextField();
        subjectText = new JTextField();
        rollNoText = new JTextField();

        dataTable = new JTable(new DefaultTableModel(new Object[]{"Name", "Subject", "RollNo", "Marks"}, 0));

        insertButton.addActionListener(evt -> insertData());
        updateButton.addActionListener(evt -> updateData());
        deleteButton.addActionListener(evt -> deleteData());

        this.setLayout(new GridLayout(9, 7));

        this.add(new JLabel("Name"));
        this.add(nameText);
        this.add(new JLabel("RollNo"));
        this.add(rollNoText);
        this.add(new JLabel("Marks"));
        this.add(marksText);
        this.add(new JLabel("Subject"));
        this.add(subjectText);
        
        

        this.add(insertButton);
        this.add(updateButton);
        this.add(deleteButton);

        this.add(new JScrollPane(dataTable));
    }

    private void insertData() {
        try {
            String name = nameText.getText();
            int marks = Integer.parseInt(marksText.getText());
            String subject = subjectText.getText();
            int rollNo = Integer.parseInt(rollNoText.getText());

            String sql = "INSERT INTO javaformdb (Name, Marks, Subject, rollNo) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, marks);
            ps.setString(3, subject);
            ps.setInt(4, rollNo);

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Record inserted successfully.");
                DisplayData(); // Refresh the table data
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for marks and roll number.");
        } catch (SQLException e) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, "Error inserting data: ", e);
            JOptionPane.showMessageDialog(this, "Error inserting data: " + e.getMessage());
        }
    }

    private void updateData() {
        int selectedRow = dataTable.getSelectedRow();
        if (selectedRow >= 0) {
            int rollNo = (int) dataTable.getValueAt(selectedRow, 2); // Assuming roll number is in the 3rd column

            String name = nameText.getText();
            int marks;
            String subject = subjectText.getText();

            try {
                marks = Integer.parseInt(marksText.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for marks.");
                return;
            }

            String sql = "UPDATE javaformdb SET Name = ?, Marks = ?, Subject = ? WHERE rollNo = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setInt(2, marks);
                ps.setString(3, subject);
                ps.setInt(4, rollNo);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Record updated successfully.");
                    DisplayData(); // Refresh the table data
                } else {
                    JOptionPane.showMessageDialog(this, "No record found with the specified roll number.");
                }
            } catch (SQLException e) {
                Logger.getLogger(Form.class.getName()).log(Level.SEVERE, "Error updating data: ", e);
                JOptionPane.showMessageDialog(this, "Error updating data: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a record to update.");
        }
    }

    private void DisplayData() {
        try {
            String sql = "SELECT * FROM javaformdb";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
            model.setRowCount(0); // Clear existing data

            while (rs.next()) {
                Object[] row = new Object[]{
                        rs.getString("Name"),
                        rs.getString("Subject"),
                        rs.getInt("RollNo"),
                        rs.getInt("Marks")
                };
                model.addRow(row); // Add each row to the table
            }
        } catch (SQLException e) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, "Error displaying data: ", e);
            JOptionPane.showMessageDialog(this, "Error displaying data: " + e.getMessage());
        }
    }

    private void deleteData() {
        int selectedRow = dataTable.getSelectedRow();
        if (selectedRow >= 0) {
            int rollNo = (int) dataTable.getValueAt(selectedRow, 2); // Assuming roll number is in the 3rd column

            String sql = "DELETE FROM javaformdb WHERE rollNo = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, rollNo);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Record deleted successfully.");
                    DisplayData(); // Refresh the table data
                } else {
                    JOptionPane.showMessageDialog(this, "No record found with the specified roll number.");
                }
            } catch (SQLException e) {
                Logger.getLogger(Form.class.getName()).log(Level.SEVERE, "Error deleting data: ", e);
                JOptionPane.showMessageDialog(this, "Error deleting data: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a record to delete.");
        }
    }
}

