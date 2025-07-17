package ui.books;

import db.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewBooksPanel extends JPanel {

    private JTable booksTable;
    private DefaultTableModel tableModel;

    public ViewBooksPanel() {
        setLayout(new BorderLayout());
        setOpaque(false);

        JLabel headingLabel = new JLabel("View All Books", SwingConstants.CENTER);
        headingLabel.setFont(new Font("Serif", Font.BOLD, 32));
        headingLabel.setForeground(Color.WHITE);
        add(headingLabel, BorderLayout.NORTH);

        // Table columns
        String[] columns = {"Book Title", "Author", "Subject", "Quantity"};

        tableModel = new DefaultTableModel(columns, 0) {
            // Make table cells non-editable
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        booksTable = new JTable(tableModel);
        booksTable.setFont(new Font("SansSerif", Font.PLAIN, 16));
        booksTable.setRowHeight(24);

        // Table header styling
        booksTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 18));
        booksTable.getTableHeader().setBackground(Color.DARK_GRAY);
        booksTable.getTableHeader().setForeground(Color.WHITE);

        // Table background & grid colors
        booksTable.setBackground(new Color(30, 30, 30));
        booksTable.setForeground(Color.WHITE);
        booksTable.setGridColor(Color.GRAY);

        JScrollPane scrollPane = new JScrollPane(booksTable);
        add(scrollPane, BorderLayout.CENTER);

        loadBooks();
    }

    private void loadBooks() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books");

            tableModel.setRowCount(0); // clear existing rows

            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                String subject = rs.getString("subject");
                int quantity = rs.getInt("quantity");

                Object[] row = {title, author, subject, quantity};
                tableModel.addRow(row);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading books data", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
