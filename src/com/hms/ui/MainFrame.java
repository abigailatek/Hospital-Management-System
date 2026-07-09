package com.hms.ui;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {

    private JPanel sidebar;
    private JPanel contentPanel;

    public MainFrame() {
        initialize();
    }

    private void initialize() {

        setTitle("LifeCare Hospital Management System");
        setSize(1400, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //-------------------------
        // Sidebar
        //-------------------------

        sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(250, 800));
        sidebar.setBackground(new Color(16, 120, 110));
        sidebar.setLayout(new GridLayout(15, 1));

        addButton("Dashboard");
        addButton("Patients");
        addButton("Doctors");
        addButton("Appointments");
        addButton("Medical Records");
        addButton("Prescriptions");
        addButton("Laboratory");
        addButton("Inventory");
        addButton("Billing");
        addButton("Payments");
        addButton("Staff");
        addButton("Attendance");
        addButton("Reports");
        addButton("Settings");
        addButton("Logout");

        //-------------------------
        // Content Panel
        //-------------------------

        contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Welcome to LifeCare Hospital");

        title.setFont(new Font("Segoe UI", Font.BOLD, 30));

        contentPanel.add(title);

        add(sidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void addButton(String text) {

        JButton button = new JButton(text);

        button.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        button.setBackground(new Color(16,120,110));

        button.setForeground(Color.WHITE);

        button.setFocusPainted(false);

        button.setBorder(BorderFactory.createEmptyBorder(15,20,15,20));

        sidebar.add(button);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new MainFrame();

        });

    }

}