package com.hms.ui;

import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminDashboardScreen extends JPanel {

    public AdminDashboardScreen() {
        setLayout(new BorderLayout(20, 20));
        setBackground(Theme.BACKGROUND);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(Theme.BACKGROUND);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Dashboard Overview");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Theme.PRIMARY_GREEN);

        mainPanel.add(title, BorderLayout.NORTH);

        JPanel tiles = new JPanel(new GridLayout(3, 3, 20, 20));
        tiles.setBackground(Theme.BACKGROUND);

        tiles.add(createTile("🏠", "Dashboard"));
        tiles.add(createTile("👨‍⚕️", "Doctors"));
        tiles.add(createTile("📅", "Appointments"));

        tiles.add(createTile("👤", "Patients"));
        tiles.add(createTile("🩺", "Diagnosis"));
        tiles.add(createTile("💊", "Prescription"));

        tiles.add(createTile("🏥", "Medical Store"));
        tiles.add(createTile("💰", "Accounts"));
        tiles.add(createTile("📊", "Reports"));

        mainPanel.add(tiles, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new GridLayout(2, 1, 15, 15));
        rightPanel.setPreferredSize(new Dimension(280, 0));
        rightPanel.setBackground(Theme.BACKGROUND);

        rightPanel.add(createSidePanel("⚡ Quick Links", new String[]{
                "📅  New Appointment",
                "👤  New Patient",
                "💊  Add Prescription",
                "📊  View Report",
                "💰  Billing"
        }));

        rightPanel.add(createSidePanel("📝 Notes", new String[]{
                "📞  Support Center",
                "❓  How to use it",
                "☎️  Help Line",
                "💬  Customer Feedback"
        }));

        add(mainPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }

    private JPanel createTile(String emoji, String title) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);

        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel iconLabel = new JLabel(emoji, SwingConstants.CENTER);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(new Color(70, 70, 70));

        card.add(iconLabel, BorderLayout.CENTER);
        card.add(titleLabel, BorderLayout.SOUTH);

        return card;
    }

    private JPanel createSidePanel(String heading, String[] items) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                new EmptyBorder(15, 18, 15, 18)
        ));

        JLabel title = new JLabel(heading);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(Theme.PRIMARY_GREEN);

        panel.add(title);
        panel.add(Box.createVerticalStrut(18));

        for (String item : items) {
            JLabel label = new JLabel(item);
            label.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            label.setBorder(new EmptyBorder(8, 0, 8, 0));
            panel.add(label);
        }

        return panel;
    }
}