package com.hms.ui;

import com.hms.models.User;
import com.hms.utils.UserSession;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel sidebar;
    private JPanel contentPanel;
    private CardLayout cardLayout;

    private JButton btnDashboard;
    private JButton btnPatients;
    private JButton btnDoctors;
    private JButton btnAppointments;
    private JButton btnMedicalRecords;
    private JButton btnPrescriptions;
    private JButton btnLaboratory;
    private JButton btnInventory;
    private JButton btnBilling;
    private JButton btnPayments;
    private JButton btnStaff;
    private JButton btnAttendance;
    private JButton btnReports;
    private JButton btnSettings;
    private JButton btnLogout;
    private JButton btnNotifications;
    public MainFrame() {
        initialize();
        applyRolePermissions();
    }

    private void initialize() {

        setTitle("LifeCare Hospital Management System");
        setSize(1400, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //-----------------------------------
        // Sidebar
        //-----------------------------------

        sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(250, 800));
        sidebar.setBackground(new Color(16, 120, 110));
        sidebar.setLayout(new GridLayout(15, 1));

        btnDashboard = createButton("Dashboard");
        btnPatients = createButton("Patients");
        btnDoctors = createButton("Doctors");
        btnAppointments = createButton("Appointments");
        btnMedicalRecords = createButton("Medical Records");
        btnPrescriptions = createButton("Prescriptions");
        btnLaboratory = createButton("Laboratory");
        btnInventory = createButton("Inventory");
        btnBilling = createButton("Billing");
        btnPayments = createButton("Payments");
        btnStaff = createButton("Staff");
        btnAttendance = createButton("Attendance");
        btnReports = createButton("Reports");
        btnNotifications =createButton( "Notifications");
        btnSettings = createButton("Settings");
        btnLogout = createButton("Logout");

        //-----------------------------------
        // Content Panel
        //-----------------------------------

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        //-----------------------------------
        // Dashboard
        //-----------------------------------

        AdminDashboardScreen dashboardPanel =
                new AdminDashboardScreen(
                        () -> showPanel("Patients"),
                        () -> showPanel("Doctors"),
                        () -> showPanel("Appointments"),
                        () -> showPanel("Medical Records"),
                        () -> showPanel("Prescriptions"),
                        () -> showPanel("Inventory"),
                        () -> showPanel("Billing"),
                        () -> showPanel("Reports")
                );

        //-----------------------------------
        // Add Panels
        //-----------------------------------

        contentPanel.add(dashboardPanel, "Dashboard");
        contentPanel.add(new PatientRegistrationScreen(), "Patients");
        contentPanel.add(new DoctorsScreen(), "Doctors");
        contentPanel.add(new AppointmentScreen(), "Appointments");
        contentPanel.add(new MedicalRecordsPanel(), "Medical Records");
        contentPanel.add(new PrescriptionPanel(), "Prescriptions");
        contentPanel.add(new LaboratoryPanel(), "Laboratory");
        contentPanel.add(new MedicalStoreScreen(), "Inventory");
        contentPanel.add(new BillingScreen(), "Billing");
        contentPanel.add(new PaymentPanel(), "Payments");
        contentPanel.add(new AdministrationScreen(), "Staff");
        contentPanel.add(new AttendancePanel(), "Attendance");
        contentPanel.add(new ReportsPanel(), "Reports");
        contentPanel.add(new SettingsPanel(), "Settings");
         contentPanel.add(new NotificationPanel(),  "Notifications");
        //-----------------------------------
        // Add to Frame
        //-----------------------------------

        add(sidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        //-----------------------------------
        // Start on Dashboard
        //-----------------------------------

        cardLayout.show(contentPanel, "Dashboard");

        setVisible(true);
    }

    //-----------------------------------
    // Change Screen
    //-----------------------------------

    private void showPanel(String panelName) {
        cardLayout.show(contentPanel, panelName);
    }

    //-----------------------------------
    // Sidebar Buttons
    //-----------------------------------

    private JButton createButton(String text) {

        JButton button = new JButton(text);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        18));

        button.setBackground(
                new Color(
                        16,
                        120,
                        110));

        button.setForeground(
                Color.WHITE);

        button.setFocusPainted(false);

        button.addActionListener(e -> {

            if (text.equals("Logout")) {

                UserSession.logout();
                dispose();
                new LoginScreen();
                return;
            }

            showPanel(text);
        });

        sidebar.add(button);

        return button;
    }

    //-----------------------------------
    // Role Permissions
    //-----------------------------------

    private void applyRolePermissions() {

        User user =
                UserSession.getCurrentUser();

        if (user == null) {
            return;
        }

        int role =
                user.getRoleID();

        // Doctor
        if (role == 2) {

            btnBilling.setVisible(false);
            btnPayments.setVisible(false);
            btnInventory.setVisible(false);
            btnStaff.setVisible(false);
            btnAttendance.setVisible(false);
        }

        // Pharmacist
        if (role == 4) {

            btnPatients.setVisible(false);
            btnDoctors.setVisible(false);
            btnAppointments.setVisible(false);
            btnBilling.setVisible(false);
        }

        // Accountant
        if (role == 5) {

            btnLaboratory.setVisible(false);
            btnPrescriptions.setVisible(false);
            btnInventory.setVisible(false);
        }
    }

    //-----------------------------------
    // Main
    //-----------------------------------

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                MainFrame::new);
    }
}