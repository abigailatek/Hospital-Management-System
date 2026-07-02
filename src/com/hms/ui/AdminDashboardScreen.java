package com.hms.ui;

import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminDashboardScreen extends JPanel {

    public AdminDashboardScreen() {
        setLayout(new BorderLayout());
        setBackground(Theme.BACKGROUND);

        JPanel wrapper = new JPanel(new BorderLayout(18, 18));
        wrapper.setBackground(Theme.BACKGROUND);
        wrapper.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel heading = new JLabel("Dashboard Overview");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 24));
        heading.setForeground(Theme.PRIMARY_GREEN);

        wrapper.add(heading, BorderLayout.NORTH);

        JPanel tiles = new JPanel(new GridLayout(3, 3, 22, 22));
        tiles.setBackground(Theme.BACKGROUND);

        tiles.add(tile("assets/icons/dashboard.png", "Dashboard", "22"));
        tiles.add(tile("assets/icons/doctor.png", "Doctors", "7"));
        tiles.add(tile("assets/icons/appointment.png", "Appointments", "3"));
        tiles.add(tile("assets/icons/patient.png", "Patients", "5"));
        tiles.add(tile("assets/icons/diagnosis.png", "Diagnosis", "3"));
        tiles.add(tile("assets/icons/prescription.png", "Prescription", "6"));
        tiles.add(tile("assets/icons/medical_store.png", "Medical Store", "6"));
        tiles.add(tile("assets/icons/billing.png", "Accounts", "0"));
        tiles.add(tile("assets/icons/report.png", "Reports", "2"));

        wrapper.add(tiles, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new GridLayout(2, 1, 15, 15));
        rightPanel.setPreferredSize(new Dimension(310, 0));
        rightPanel.setBackground(Theme.BACKGROUND);

        rightPanel.add(sidePanel("Quick Links", new String[]{
                "New Appointment",
                "New Patient",
                "Add Prescription",
                "View Report",
                "Billing"
        }));

        rightPanel.add(sidePanel("Notes", new String[]{
                "Support Center",
                "How to use it",
                "Help Line",
                "Customer Feedback"
        }));

        add(wrapper, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }

    private JPanel tile(String iconPath, String title, String count) {
        JPanel card = new JPanel(new BorderLayout(5, 5));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(215, 215, 215)),
                new EmptyBorder(18, 18, 14, 18)
        ));

        JLabel countLabel = new JLabel(count, SwingConstants.RIGHT);
        countLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        countLabel.setForeground(Color.WHITE);
        countLabel.setOpaque(true);
        countLabel.setBackground(Theme.PRIMARY_GREEN);
        countLabel.setBorder(new EmptyBorder(3, 8, 3, 8));

        JLabel iconLabel = new JLabel();
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon icon = new ImageIcon(iconPath);
        if (icon.getIconWidth() > 0) {
            Image img = icon.getImage().getScaledInstance(72, 72, Image.SCALE_SMOOTH);
            iconLabel.setIcon(new ImageIcon(img));
        } else {
            iconLabel.setText("□");
            iconLabel.setFont(new Font("Segoe UI", Font.BOLD, 40));
            iconLabel.setForeground(Theme.PRIMARY_GREEN);
        }

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(new Color(65, 65, 65));

        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(Color.WHITE);
        top.add(countLabel, BorderLayout.EAST);

        card.add(top, BorderLayout.NORTH);
        card.add(iconLabel, BorderLayout.CENTER);
        card.add(titleLabel, BorderLayout.SOUTH);

        return card;
    }

    private JPanel sidePanel(String title, String[] items) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(215, 215, 215)),
                new EmptyBorder(18, 18, 18, 18)
        ));

        JLabel header = new JLabel(title);
        header.setFont(new Font("Segoe UI", Font.BOLD, 18));
        header.setForeground(Theme.PRIMARY_GREEN);
        header.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(header);
        panel.add(Box.createVerticalStrut(18));

        for (String item : items) {
            JLabel label = new JLabel("•  " + item);
            label.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            label.setForeground(Theme.TEXT);
            label.setBorder(new EmptyBorder(9, 0, 9, 0));
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(label);
        }

        return panel;
    }
}