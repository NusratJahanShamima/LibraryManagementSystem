package ui.books;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class BookManagementPage extends JFrame {

    private JPanel rightPanel;

    private Image backgroundImage;

    public BookManagementPage() {
        setTitle("Book Management");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Load background image
        backgroundImage = new ImageIcon("src/assets/img/bs.jpg").getImage();

        // Custom background panel
        JPanel backgroundPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // Left (button) panel
        JPanel leftPanel = new JPanel(new GridLayout(5, 1, 15, 15));
        leftPanel.setOpaque(false);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));

        String[] buttonLabels = {
                "Add New Book",
                "Update Book Details",
                "Delete Book",
                "View All Books",
                "Search Book by Title"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);

            // Set icon (optional - you can replace with your own icons)
            String iconPath = "src/assets/img/icon.png"; // Replace with actual icon path if available
            if (new File(iconPath).exists()) {
                ImageIcon icon = new ImageIcon(iconPath);
                Image scaledIcon = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(scaledIcon));
            }

            button.setFont(new Font("SansSerif", Font.BOLD, 20));
            button.setBackground(new Color(30, 30, 30));         // Dark background
            button.setForeground(Color.WHITE);                   // White text
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            button.setHorizontalAlignment(SwingConstants.LEFT);  // Icon + Text alignment
            button.setIconTextGap(10);                           // Space between icon and text
            button.setOpaque(true);

            // Hover effect
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(60, 60, 60));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(30, 30, 30));
                }
            });

            button.addActionListener(e -> showPanel(label));
            leftPanel.add(button);
        }


        // Right panel (dynamic content)
        rightPanel = new JPanel(new BorderLayout());
        rightPanel.setOpaque(false);

        // Add panels to the background panel
        backgroundPanel.add(leftPanel, BorderLayout.EAST);
        backgroundPanel.add(rightPanel, BorderLayout.CENTER);

        setContentPane(backgroundPanel);
        setVisible(true);
    }

    private void showPanel(String panelName) {
        rightPanel.removeAll();

        switch (panelName) {
            case "Add New Book":
                rightPanel.add(new AddBookPanel(), BorderLayout.CENTER);
                break;
            case "Update Book Details":
                rightPanel.add(new UpdateBookPanel(), BorderLayout.CENTER);
                break;

            case "Delete Book":
                rightPanel.add(new DeleteBookPanel(), BorderLayout.CENTER);
                break;

            case "View All Books":
                rightPanel.add(new ViewBooksPanel(), BorderLayout.CENTER);
                break;

            case "Search Book by Title":
                rightPanel.add(new SearchBookPanel(), BorderLayout.CENTER);
                break;

            default:
                rightPanel.add(new JLabel("Unknown action", SwingConstants.CENTER), BorderLayout.CENTER);
        }

        rightPanel.revalidate();
        rightPanel.repaint();
    }
}