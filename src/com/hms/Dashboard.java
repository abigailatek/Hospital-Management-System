package com.hms;

import com.hms.ui.AdminDashboardScreen;
import com.hms.utils.Theme;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    private JPanel contentPanel;

    public Dashboard() {

        setTitle("Hospital Management System");
        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ================= MAIN PANEL =================

        JPanel mainPanel = new JPanel(new BorderLayout());

        // ================= TOP BAR =================

        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setPreferredSize(new Dimension(1400,60));
        topBar.setBackground(Theme.PRIMARY_GREEN);

        JLabel logo = new JLabel(" 🏥 HMS");
        logo.setFont(new Font("Segoe UI",Font.BOLD,22));
        logo.setForeground(Color.WHITE);

        JLabel user = new JLabel("Administrator   ");
        user.setForeground(Color.WHITE);
        user.setFont(Theme.NORMAL);

        topBar.add(logo,BorderLayout.WEST);
        topBar.add(user,BorderLayout.EAST);

        // ================= SIDEBAR =================

        JPanel sidebar = new JPanel();
        sidebar.setBackground(Color.WHITE);
        sidebar.setPreferredSize(new Dimension(220,800));
        sidebar.setLayout(new GridLayout(12,1));

        sidebar.add(createButton("🏠 Dashboard"));
        sidebar.add(createButton("👤 Patients"));
        sidebar.add(createButton("🩺 Doctors"));
        sidebar.add(createButton("📅 Appointments"));
        sidebar.add(createButton("🧾 Diagnosis"));
        sidebar.add(createButton("💊 Prescription"));
        sidebar.add(createButton("💰 Billing"));
        sidebar.add(createButton("🏥 Medical Store"));
        sidebar.add(createButton("📊 Reports"));
        sidebar.add(createButton("👥 HRM"));
        sidebar.add(createButton("⚙ Administration"));
        sidebar.add(createButton("⚙ Settings"));

        // ================= CONTENT =================

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Theme.BACKGROUND);

        contentPanel.add(new AdminDashboardScreen());

        // ================= ADD PANELS =================

        mainPanel.add(topBar,BorderLayout.NORTH);
        mainPanel.add(sidebar,BorderLayout.WEST);
        mainPanel.add(contentPanel,BorderLayout.CENTER);

        add(mainPanel);
    }

    private JButton createButton(String text){

        JButton button = new JButton(text);

        button.setFont(Theme.NORMAL);
        button.setHorizontalAlignment(SwingConstants.LEFT);

        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);

        button.setBorder(BorderFactory.createEmptyBorder(
                10,20,10,10));

        return button;

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new Dashboard().setVisible(true);

        });

    }

}