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

        JLabel heading = new JLabel("ADMIN DASHBOARD");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 24));
        heading.setForeground(Theme.PRIMARY_GREEN);

        wrapper.add(heading, BorderLayout.NORTH);

        JPanel tiles = new JPanel(new GridLayout(3, 3, 20, 20));
        tiles.setBackground(Theme.BACKGROUND);

        tiles.add(tile("assets/icons/dashboard.png", "Dashboard", "22"));
        tiles.add(tile("assets/icons/doctor.png", "Doctors", "7"));
        tiles.add(tile("assets/icons/appointment.png", "Appointments", "3"));

        tiles.add(tile("assets/icons/patient.png", "Patients", "5"));
        tiles.add(tile("assets/icons/diagnosis.png", "Diagnosis", "3"));
        tiles.add(tile("assets/icons/prescription.png", "Prescription", "6"));

        tiles.add(tile("assets/icons/billing.png", "Medical Store", "6"));
        tiles.add(tile("assets/icons/billing.png", "Accounts", "0"));
        tiles.add(tile("assets/icons/report.png", "Reports", "2"));

        wrapper.add(tiles, BorderLayout.CENTER);

        JPanel right = new JPanel(new GridLayout(2, 1, 15, 15));
        right.setPreferredSize(new Dimension(300, 0));
        right.setBackground(Theme.BACKGROUND);

        right.add(sidePanel("Quick Links", new String[]{
                "New Appointment",
                "New Patient",
                "Add Prescription",
                "View Report",
                "Billing"
        }));

        right.add(sidePanel("Notes", new String[]{
                "Support Center",
                "How to use it",
                "Help Line",
                "Customer Feedback"
        }));

        add(wrapper, BorderLayout.CENTER);
        add(right, BorderLayout.EAST);
    }

    private JPanel tile(String iconPath, String title, String count) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel iconLabel = new JLabel("", SwingConstants.CENTER);

        ImageIcon icon = new ImageIcon(iconPath);
        Image img = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        iconLabel.setIcon(new ImageIcon(img));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(new Color(70, 70, 70));

        JLabel countLabel = new JLabel(count, SwingConstants.CENTER);
        countLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        countLabel.setOpaque(true);
        countLabel.setBackground(new Color(235, 235, 235));
        countLabel.setPreferredSize(new Dimension(35, 25));

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBackground(Color.WHITE);
        bottom.add(titleLabel, BorderLayout.CENTER);
        bottom.add(countLabel, BorderLayout.EAST);

        card.add(iconLabel, BorderLayout.CENTER);
        card.add(bottom, BorderLayout.SOUTH);

        return card;
    }

    private JPanel sidePanel(String title, String[] items) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                new EmptyBorder(15, 18, 15, 18)
        ));

        JLabel header = new JLabel(title);
        header.setFont(new Font("Segoe UI", Font.BOLD, 18));
        header.setForeground(Theme.PRIMARY_GREEN);
        header.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(header);
        panel.add(Box.createVerticalStrut(15));

        for (String item : items) {
            JLabel label = new JLabel("• " + item);
            label.setFont(Theme.NORMAL);
            label.setForeground(Theme.TEXT);
            label.setBorder(new EmptyBorder(8, 0, 8, 0));
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(label);
        }

        return panel;
    }
}