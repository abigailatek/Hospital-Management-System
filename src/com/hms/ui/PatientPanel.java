package com.hms.ui;

import com.hms.models.Patient;
import com.hms.services.PatientService;
import com.hms.ui.components.CrudToolbar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PatientPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;

    private CrudToolbar toolbar;

    public PatientPanel() {

        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 250));

        // ==========================
        // Title
        // ==========================

        JLabel title = new JLabel("Patient Management");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        add(title, BorderLayout.NORTH);

        // ==========================
        // Toolbar
        // ==========================

        toolbar = new CrudToolbar();

        add(toolbar, BorderLayout.SOUTH);

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
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        // ==========================
        // Events
        // ==========================

        toolbar.btnRefresh.addActionListener(e -> loadPatients());

        toolbar.btnSearch.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Search functionality coming next."
                )
        );

        toolbar.btnAdd.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Add Patient coming next."
                )
        );

        toolbar.btnEdit.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Edit Patient coming next."
                )
        );

        toolbar.btnDelete.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Delete Patient coming next."
                )
        );

        loadPatients();
    }

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