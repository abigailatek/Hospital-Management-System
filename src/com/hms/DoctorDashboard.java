package com.hms;

import com.hms.ui.*;
import com.hms.utils.Theme;

import javax.swing.*;
import java.awt.*;

public class DoctorDashboard extends JFrame {

    private JPanel contentPanel;
    private JButton activeButton;
    private Timer sessionTimer;

    public DoctorDashboard() {
        setTitle("Doctor Dashboard - Hospital Management System");
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

        JLabel title = new JLabel("  Hospital Management System - Doctor");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);

        JLabel user = new JLabel("Doctor   |   Logout   ");
        user.setFont(Theme.NORMAL);
        user.setForeground(Color.WHITE);

        topBar.add(title, BorderLayout.WEST);
        topBar.add(user, BorderLayout.EAST);

        JPanel sidebar = new JPanel(new GridLayout(8, 1, 5, 5));
        sidebar.setPreferredSize(new Dimension(230, 800));
        sidebar.setBackground(Color.WHITE);
        sidebar.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        JButton dashboardBtn = sidebarButton("Dashboard");
        JButton patientsBtn = sidebarButton("My Patients");
        JButton appointmentsBtn = sidebarButton("Appointments");
        JButton emrBtn = sidebarButton("Patient EMR");
        JButton prescriptionBtn = sidebarButton("Prescription");
        JButton reportsBtn = sidebarButton("Reports");
        JButton profileBtn = sidebarButton("Profile");
        JButton logoutBtn = sidebarButton("Logout");

        sidebar.add(dashboardBtn);
        sidebar.add(patientsBtn);
        sidebar.add(appointmentsBtn);
        sidebar.add(emrBtn);
        sidebar.add(prescriptionBtn);
        sidebar.add(reportsBtn);
        sidebar.add(profileBtn);
        sidebar.add(logoutBtn);

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Theme.BACKGROUND);

        dashboardBtn.addActionListener(e -> {
            setActive(dashboardBtn);
            showScreen(new DoctorHomeScreen());
        });

        patientsBtn.addActionListener(e -> {
            setActive(patientsBtn);
            showScreen(new DoctorManagePatientScreen());
        });

        appointmentsBtn.addActionListener(e -> {
            setActive(appointmentsBtn);
            showScreen(new AppointmentScreen());
        });

        emrBtn.addActionListener(e -> {
            setActive(emrBtn);
            showScreen(new DoctorsEMRScreen());
        });

        prescriptionBtn.addActionListener(e -> {
            setActive(prescriptionBtn);
            showScreen(new PrescriptionScreen());
        });

        reportsBtn.addActionListener(e -> {
            setActive(reportsBtn);
            showScreen(new ReportsScreen());
        });

        profileBtn.addActionListener(e -> {
            setActive(profileBtn);
            showScreen(new DoctorProfileScreen());
        });

        logoutBtn.addActionListener(e -> confirmLogout());

        main.add(topBar, BorderLayout.NORTH);
        main.add(sidebar, BorderLayout.WEST);
        main.add(contentPanel, BorderLayout.CENTER);

        add(main);

        setActive(dashboardBtn);
        showScreen(new DoctorHomeScreen());
        startSessionTimeout();
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

        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
        }
    }

    private void confirmClose() {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to close the system?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
        }
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
        if (sessionTimer != null) {
            sessionTimer.restart();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DoctorDashboard().setVisible(true));
    }
}