package com.hms;

import com.hms.ui.*;
import com.hms.ui.LoginScreen;
import com.hms.utils.Theme;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    private final JPanel contentPanel;
    private JButton activeButton;

    public Dashboard() {
        setTitle("Hospital Management System");
        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel main = new JPanel(new BorderLayout());

        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(Theme.PRIMARY_GREEN);
        topBar.setPreferredSize(new Dimension(1400, 65));

        JLabel title = new JLabel("  🏥 Hospital Management System");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);

        JLabel user = new JLabel("👤 Admin   |   🚪 Logout   ");
        user.setFont(Theme.NORMAL);
        user.setForeground(Color.WHITE);

        topBar.add(title, BorderLayout.WEST);
        topBar.add(user, BorderLayout.EAST);

        JPanel sidebar = new JPanel(new GridLayout(12, 1, 5, 5));
        sidebar.setPreferredSize(new Dimension(230, 800));
        sidebar.setBackground(Color.WHITE);
        sidebar.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        JButton dashboardBtn = menuButton("🏥 Dashboard");
        JButton patientsBtn = menuButton("👤  Patients");
        JButton doctorsBtn = menuButton("🩺  Doctors");
        JButton appointmentsBtn = menuButton("📅  Appointments");
        JButton diagnosisBtn = menuButton("📝  Diagnosis");
        JButton prescriptionBtn = menuButton("💊  Prescription");
        JButton billingBtn = menuButton("💰  Billing");
        JButton medicalStoreBtn = menuButton("🏥  Medical Store");
        JButton reportsBtn = menuButton("📊  Reports");
        JButton hrmBtn = menuButton("👥  HRM");
        JButton adminBtn = menuButton("🛠️  Administration");
        JButton settingsBtn = menuButton("⚙️  Settings");

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
            showScreen(new AdminDashboardScreen());
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


        settingsBtn.addActionListener(e -> {
            setActive(settingsBtn);
            showScreen(new SettingsScreen());
        });

        main.add(topBar, BorderLayout.NORTH);
        main.add(sidebar, BorderLayout.WEST);
        main.add(contentPanel, BorderLayout.CENTER);

        add(main);

        setActive(dashboardBtn);
        showScreen(new AdminDashboardScreen());
    }

    private void showScreen(JPanel screen) {
        contentPanel.removeAll();
        contentPanel.add(screen, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showPlaceholder(String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Theme.BACKGROUND);

        JLabel label = new JLabel(title + " SCREEN", SwingConstants.CENTER);
        label.setFont(Theme.TITLE);
        label.setForeground(Theme.PRIMARY_GREEN);

        panel.add(label, BorderLayout.CENTER);
        showScreen(panel);
    }

    private JButton menuButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setFocusPainted(false);
        btn.setBackground(Color.WHITE);
        btn.setForeground(Theme.TEXT);
        btn.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 10));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                if (btn != activeButton) {
                    btn.setBackground(Theme.LIGHT_GREEN);
                }
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                if (btn != activeButton) {
                    btn.setBackground(Color.WHITE);
                }
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Dashboard().setVisible(true));
    }
}