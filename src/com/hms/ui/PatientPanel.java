package com.hms.ui;

import com.hms.models.Patient;
import com.hms.services.PatientService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PatientPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;

    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnRefresh;

    private JTextField txtSearch;
    private JButton btnSearch;

    public PatientPanel() {

        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 250));

        // ==========================
        // Title
        // ==========================

        JLabel title = new JLabel("Patient Management");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        // ==========================
        // Search Panel
        // ==========================

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setBackground(new Color(245, 247, 250));

        txtSearch = new JTextField(20);
        btnSearch = new JButton("Search");

        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        // ==========================
        // Top Panel
        // ==========================

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(245, 247, 250));

        topPanel.add(title, BorderLayout.WEST);
        topPanel.add(searchPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // ==========================
        // Buttons
        // ==========================

        btnAdd = new JButton("Add Patient");
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
        btnRefresh = new JButton("Refresh");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.WHITE);

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnRefresh);

        // ==========================
        // Table
        // ==========================

        tableModel = new DefaultTableModel();

        tableModel.setColumnIdentifiers(new Object[]{
                "ID",
                "First Name",
                "Last Name",
                "Gender",
                "Phone",
                "Email",
                "Address",
                "Emergency Contact"
        });

        table = new JTable(tableModel);

        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JScrollPane scrollPane = new JScrollPane(table);

        // ==========================
        // Center Panel
        // ==========================

        JPanel centerPanel = new JPanel(new BorderLayout());

        centerPanel.add(buttonPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // ==========================
        // Events
        // ==========================

        btnRefresh.addActionListener(e -> loadPatients());

        // Search button (to be implemented later)
        btnSearch.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Search functionality coming next.")
        );

        // Load data when panel opens
        loadPatients();
    }

    /**
     * Loads all patients from the database into the JTable.
     */
    private void loadPatients() {

        PatientService service = new PatientService();

        List<Patient> patients = service.getAllPatients();

        tableModel.setRowCount(0);

        for (Patient patient : patients) {

            tableModel.addRow(new Object[]{
                    patient.getPatientID(),
                    patient.getFirstName(),
                    patient.getLastName(),
                    patient.getGender(),
                    patient.getPhone(),
                    patient.getEmail(),
                    patient.getAddress(),
                    patient.getEmergencyContact()
            });
        }
    }
}