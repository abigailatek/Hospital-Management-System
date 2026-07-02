package com.hms.ui;

import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminDashboardScreen extends JPanel {

    public AdminDashboardScreen() {
        setLayout(new BorderLayout());
        setBackground(Theme.BACKGROUND);

        JPanel main = new JPanel(new BorderLayout(15, 15));
        main.setBackground(Theme.BACKGROUND);
        main.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel heading = new JLabel("ADMIN | DASHBOARD");
        heading.setFont(Theme.HEADING);
        heading.setForeground(Theme.PRIMARY_GREEN);

        main.add(heading, BorderLayout.NORTH);

        JPanel tilesGrid = new JPanel(new GridLayout(3, 3, 20, 20));
        tilesGrid.setBackground(Theme.BACKGROUND);

        tilesGrid.add(createTile("📊", "Dashboard", "22"));
        tilesGrid.add(createTile("🩺", "Doctors", "7"));
        tilesGrid.add(createTile("📅", "Appointments", "3"));

        tilesGrid.add(createTile("👤", "Patients", "5"));
        tilesGrid.add(createTile("🧾", "Diagnosis", "3"));
        tilesGrid.add(createTile("💊", "Prescription", "6"));

        tilesGrid.add(createTile("🏥", "Medical Store", "6"));
        tilesGrid.add(createTile("💰", "Accounts", "0"));
        tilesGrid.add(createTile("📈", "Reports", "2"));

        main.add(tilesGrid, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        rightPanel.setPreferredSize(new Dimension(270, 0));
        rightPanel.setBackground(Theme.BACKGROUND);

        rightPanel.add(createQuickLinksPanel());
        rightPanel.add(createNotesPanel());

        add(main, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }

    private JPanel createTile(String icon, String title, String count) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel iconLabel = new JLabel(icon, SwingConstants.CENTER);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 42));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        titleLabel.setForeground(new Color(80, 80, 80));

        JLabel countLabel = new JLabel(count, SwingConstants.RIGHT);
        countLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        countLabel.setForeground(Color.DARK_GRAY);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBackground(Color.WHITE);
        bottom.add(titleLabel, BorderLayout.CENTER);
        bottom.add(countLabel, BorderLayout.EAST);

        card.add(iconLabel, BorderLayout.CENTER);
        card.add(bottom, BorderLayout.SOUTH);

        return card;
    }

    private JPanel createQuickLinksPanel() {
        JPanel panel = createSidePanel("⭐ Quick Links");

        panel.add(linkLabel("📅  New Appointment"));
        panel.add(linkLabel("👤  New Patient"));
        panel.add(linkLabel("💊  Add Prescription"));
        panel.add(linkLabel("📊  View Report"));
        panel.add(linkLabel("💰  Billing"));

        return panel;
    }

    private JPanel createNotesPanel() {
        JPanel panel = createSidePanel("📝 Notes");

        panel.add(linkLabel("☎  Support Center"));
        panel.add(linkLabel("❔  How to use it"));
        panel.add(linkLabel("📞  Help Line"));
        panel.add(linkLabel("💬  Customer Feedback"));

        return panel;
    }

    private JPanel createSidePanel(String title) {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBackground(Color.WHITE);
        wrapper.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                new EmptyBorder(12, 15, 12, 15)
        ));

        JLabel label = new JLabel(title);
        label.setFont(Theme.HEADING);
        label.setForeground(Theme.PRIMARY_GREEN);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        wrapper.add(label);
        wrapper.add(Box.createVerticalStrut(18));

        return wrapper;
    }

    private JLabel linkLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(Theme.NORMAL);
        label.setForeground(Theme.TEXT);
        label.setBorder(new EmptyBorder(10, 0, 10, 0));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }
}