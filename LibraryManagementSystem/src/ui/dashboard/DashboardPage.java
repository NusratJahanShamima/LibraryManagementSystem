package ui.dashboard;

import ui.books.BookManagementPage;

import javax.swing.*;
import java.awt.*;

public class DashboardPage extends JFrame {

    private Image backgroundImage;

    public DashboardPage() {
        setTitle("ICT Seminar Library - Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Load background using ImageIcon (smooth scaling)
        backgroundImage = new ImageIcon("src/assets/img/book.jpg").getImage();

        // Custom JPanel with background image
        JPanel backgroundPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // Top Title
        JLabel titleLabel = new JLabel("WELCOME TO ICT SEMINAR LIBRARY", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(40, 10, 40, 10));
        backgroundPanel.add(titleLabel, BorderLayout.NORTH);

        // Center Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 20, 20));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        String[] buttons = {
                "Book Management",
                "Student/User Management",
                "Issue / Return Management",
                "Reports",
                "Logout"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Serif", Font.BOLD, 33));
            setBackground(Color.BLACK);
            btn.setForeground(Color.BLACK);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            buttonPanel.add(btn);

            // Add action listener for Book Management button
            if (text.equals("Book Management")) {
                btn.addActionListener(e -> {
                    new BookManagementPage();  // Open BookManagementPage
                    dispose();                // Close Dashboard
                });
            }

            if (text.equals("Logout")) {
                btn.addActionListener(e -> {
                    dispose();
                    new ui.login.LoginPage();
                });
            }

            if (text.equals("Student/User Management")) {
                btn.addActionListener(e -> {
                    new ui.students.StudentManagementPage();  // এই লাইনেই তোমার Student/User Management page launch হবে
                    dispose();  // Dashboard বন্ধ হয়ে যাবে
                });
            }


            if (text.equals("Issue / Return Management")) {
                btn.addActionListener(e -> {
                    new ui.issue.IssueReturnManagementPage();  // Issue/Return page launch
                    dispose();  // Dashboard বন্ধ হবে
                });
            }



            // TODO: Add other buttons' action listeners later (Student/User Management, Logout, etc.)
        }

        // Center wrapper for alignment
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(buttonPanel);

        backgroundPanel.add(centerPanel, BorderLayout.CENTER);
        setContentPane(backgroundPanel);
        setVisible(true);
    }
}
