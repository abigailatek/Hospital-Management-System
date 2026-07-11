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
        btnSettings = createButton("Settings");
        btnLogout = createButton("Logout");

        cardLayout = new CardLayout();

        contentPanel = new JPanel(cardLayout);

        contentPanel.add(new DashboardPanel(), "Dashboard");
        contentPanel.add(new DoctorPanel(), "Doctors");
        contentPanel.add(new AppointmentPanel(),"Appointments");
        contentPanel.add(new JPanel(), "Medical Records");
        contentPanel.add(new PrescriptionPanel(),"Prescriptions");
        contentPanel.add(new JPanel(), "Laboratory");
        contentPanel.add(new InventoryPanel(),"Inventory");
        contentPanel.add( new BillingPanel(),"Billing");
        contentPanel.add(new PaymentPanel(), "Payments");
        contentPanel.add(new JPanel(), "Staff");
        contentPanel.add(new JPanel(), "Attendance");
        contentPanel.add(new ReportsPanel(), "Reports");
        contentPanel.add(new JPanel(), "Settings");
        add(sidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
        setVisible(true);
    }
    private JButton createButton(String text) {
        JButton button = new JButton(text);

        button.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        button.setBackground(new Color(16, 120, 110));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);

        button.addActionListener(e -> {
if (text.equals("Logout")) {
    UserSession.logout();
    dispose();
    new LoginScreen();
    return;
}
            cardLayout.show(contentPanel, text);
        });

        sidebar.add(button);

        return button;
    }

    private void applyRolePermissions() {

        User user = UserSession.getCurrentUser();

        if (user == null) {
            return;
        }

        int role = user.getRoleID();

        if (role == 2) { // Doctor
            btnBilling.setVisible(false);
            btnPayments.setVisible(false);
            btnInventory.setVisible(false);
            btnStaff.setVisible(false);
            btnAttendance.setVisible(false);
        }

        if (role == 4) { // Pharmacist
            btnPatients.setVisible(false);
            btnDoctors.setVisible(false);
            btnAppointments.setVisible(false);
            btnBilling.setVisible(false);
        }

        if (role == 5) { // Accountant
            btnLaboratory.setVisible(false);
            btnPrescriptions.setVisible(false);
            btnInventory.setVisible(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}