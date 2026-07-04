package com.hms;

import com.hms.ui.*;
import com.hms.utils.Theme;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    private JPanel contentPanel;
    private JButton activeButton;
    private Timer sessionTimer;

    public Dashboard() {
        setTitle("Admin Dashboard - Hospital Management System");
        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                confirmClose();
            }
        });

        JPanel main = new JPanel(new BorderLayout());

        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(Theme.PRIMARY_GREEN);
        topBar.setPreferredSize(new Dimension(1400, 65));

        JLabel title = new JLabel("  Hospital Management System - Admin");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);

        JPanel topRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 15));
        topRight.setOpaque(false);

        JLabel user = new JLabel("Admin");
        user.setFont(Theme.NORMAL);
        user.setForeground(Color.WHITE);

        JButton logoutTopBtn = new JButton("Logout");
        logoutTopBtn.setFont(Theme.NORMAL);
        logoutTopBtn.setFocusPainted(false);
        logoutTopBtn.setBackground(Color.WHITE);
        logoutTopBtn.setForeground(Theme.PRIMARY_GREEN);
        logoutTopBtn.addActionListener(e -> confirmLogout());

        topRight.add(user);
        topRight.add(logoutTopBtn);

        topBar.add(title, BorderLayout.WEST);
        topBar.add(topRight, BorderLayout.EAST);

        JPanel sidebar = new JPanel(new GridLayout(12, 1, 5, 5));
        sidebar.setPreferredSize(new Dimension(230, 800));
        sidebar.setBackground(Color.WHITE);
        sidebar.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        JButton dashboardBtn = sidebarButton("Dashboard");
        JButton patientsBtn = sidebarButton("Patients");
        JButton doctorsBtn = sidebarButton("Doctors");
        JButton appointmentsBtn = sidebarButton("Appointments");
        JButton diagnosisBtn = sidebarButton("Diagnosis");
        JButton prescriptionBtn = sidebarButton("Prescription");
        JButton billingBtn = sidebarButton("Billing");
        JButton medicalStoreBtn = sidebarButton("Medical Store");
        JButton reportsBtn = sidebarButton("Reports");
        JButton hrmBtn = sidebarButton("HRM");
        JButton adminBtn = sidebarButton("Administration");
        JButton settingsBtn = sidebarButton("Settings");

        sidebar.add(dashboardBtn);
        sidebar.add(patientsBtn);
        sidebar.add(doctorsBtn);
        sidebar.add(appointmentsBtn);
        sidebar.add(diagnosisBtn);
        sidebar.add(prescriptionBtn);
        sidebar.add(billingBtn);
        sidebar.add(medicalStoreBtn);
        sidebar.add(reportsBtn);
        sidebar.add(hrmBtn);
        sidebar.add(adminBtn);
        sidebar.add(settingsBtn);

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Theme.BACKGROUND);

        dashboardBtn.addActionListener(e -> {
            setActive(dashboardBtn);
            showAdminDashboard();
        });

        patientsBtn.addActionListener(e -> {
            setActive(patientsBtn);
            showScreen(new PatientRegistrationScreen());
        });

        doctorsBtn.addActionListener(e -> {
            setActive(doctorsBtn);
            showScreen(new DoctorManagePatientScreen());
        });

        appointmentsBtn.addActionListener(e -> {
            setActive(appointmentsBtn);
            showScreen(new AppointmentScreen());
        });

        diagnosisBtn.addActionListener(e -> {
            setActive(diagnosisBtn);
            showScreen(new DoctorsEMRScreen());
        });

        prescriptionBtn.addActionListener(e -> {
            setActive(prescriptionBtn);
            showScreen(new PrescriptionScreen());
        });

        billingBtn.addActionListener(e -> {
            setActive(billingBtn);
            showScreen(new BillingScreen());
        });

        medicalStoreBtn.addActionListener(e -> {
            setActive(medicalStoreBtn);
            showScreen(new MedicalStoreScreen());
        });

        reportsBtn.addActionListener(e -> {
            setActive(reportsBtn);
            showScreen(new ReportsScreen());
        });

        hrmBtn.addActionListener(e -> {
            setActive(hrmBtn);
            showScreen(new HRMScreen());
        });

        adminBtn.addActionListener(e -> {
            setActive(adminBtn);
            showScreen(new AdministrationScreen());
        });

        settingsBtn.addActionListener(e -> {
            setActive(settingsBtn);
            showScreen(new SettingsScreen());
        });

        main.add(topBar, BorderLayout.NORTH);
        main.add(sidebar, BorderLayout.WEST);
        main.add(contentPanel, BorderLayout.CENTER);

        add(main);

        setActive(dashboardBtn);
        showAdminDashboard();
        startSessionTimeout();
    }

    private void showAdminDashboard() {
        showScreen(new AdminDashboardScreen(
                () -> showScreen(new PatientRegistrationScreen()),
                () -> showScreen(new DoctorManagePatientScreen()),
                () -> showScreen(new AppointmentScreen()),
                () -> showScreen(new DoctorsEMRScreen()),
                () -> showScreen(new PrescriptionScreen()),
                () -> showScreen(new MedicalStoreScreen()),
                () -> showScreen(new BillingScreen()),
                () -> showScreen(new ReportsScreen())
        ));
    }

    private void showScreen(JPanel screen) {
        resetSessionTimer();
        contentPanel.removeAll();
        contentPanel.add(screen, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private JButton sidebarButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBackground(Color.WHITE);
        btn.setForeground(Theme.TEXT);
        btn.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 10));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                if (btn != activeButton) btn.setBackground(Theme.LIGHT_GREEN);
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                if (btn != activeButton) btn.setBackground(Color.WHITE);
            }
        });

        return btn;
    }

    private void setActive(JButton btn) {
        if (activeButton != null) {
            activeButton.setBackground(Color.WHITE);
            activeButton.setForeground(Theme.TEXT);
        }

        activeButton = btn;
        activeButton.setBackground(Theme.PRIMARY_GREEN);
        activeButton.setForeground(Color.WHITE);
    }

    private void confirmLogout() {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to logout?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) dispose();
    }

    private void confirmClose() {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to close the system?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) dispose();
    }

    private void startSessionTimeout() {
        int timeout = 10 * 60 * 1000;

        sessionTimer = new Timer(timeout, e -> {
            JOptionPane.showMessageDialog(
                    this,
                    "Session expired. Please log in again.",
                    "Session Timeout",
                    JOptionPane.WARNING_MESSAGE
            );
            dispose();
        });

        sessionTimer.setRepeats(false);
        sessionTimer.start();
    }

    private void resetSessionTimer() {
        if (sessionTimer != null) sessionTimer.restart();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Dashboard().setVisible(true));
    }
}