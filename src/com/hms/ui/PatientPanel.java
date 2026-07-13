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

       
      toolbar.btnRefresh.addActionListener(e ->
        loadPatients());

toolbar.btnSearch.addActionListener(e ->
        searchPatient());

toolbar.btnDelete.addActionListener(e ->
        deletePatient());

toolbar.btnAdd.addActionListener(e ->
        JOptionPane.showMessageDialog(
                this,
                "Use the Patient Registration screen to add new patients."));

     toolbar.btnEdit.addActionListener(e -> {

    int row = table.getSelectedRow();

    if (row == -1) {

        JOptionPane.showMessageDialog(
                this,
                "Select a patient first.");

        return;
    }

    int patientId =
            Integer.parseInt(
                    tableModel.getValueAt(
                            row,
                            0).toString());

    Patient patient =
            new Patient();

    patient.setPatientID(patientId);

    patient.setFirstName(
            JOptionPane.showInputDialog(
                    this,
                    "First Name",
                    tableModel.getValueAt(
                            row,
                            1)));

    patient.setLastName(
            JOptionPane.showInputDialog(
                    this,
                    "Last Name",
                    tableModel.getValueAt(
                            row,
                            2)));

    patient.setGender(
            JOptionPane.showInputDialog(
                    this,
                    "Gender",
                    tableModel.getValueAt(
                            row,
                            3)));

    patient.setPhone(
            JOptionPane.showInputDialog(
                    this,
                    "Phone",
                    tableModel.getValueAt(
                            row,
                            4)));

    patient.setEmail(
            JOptionPane.showInputDialog(
                    this,
                    "Email",
                    tableModel.getValueAt(
                            row,
                            5)));

    patient.setAddress(
            JOptionPane.showInputDialog(
                    this,
                    "Address",
                    tableModel.getValueAt(
                            row,
                            6)));

    patient.setEmergencyContact(
            JOptionPane.showInputDialog(
                    this,
                    "Emergency Contact",
                    tableModel.getValueAt(
                            row,
                            7)));

    PatientService service =
            new PatientService();

    if (service.updatePatient(patient)) {

        JOptionPane.showMessageDialog(
                this,
                "Patient updated successfully.");

        loadPatients();
    }
});
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
    private void searchPatient() {

    String input =
            JOptionPane.showInputDialog(
                    this,
                    "Enter Patient ID");

    if (input == null ||
            input.isBlank()) {
        return;
    }

    try {

        int patientId =
                Integer.parseInt(input);

        PatientService service =
                new PatientService();

        Patient patient =
                service.getPatientById(patientId);

        tableModel.setRowCount(0);

        if (patient != null) {

            tableModel.addRow(
                    new Object[]{
                            patient.getPatientID(),
                            patient.getFirstName(),
                            patient.getLastName(),
                            patient.getGender(),
                            patient.getPhone(),
                            patient.getEmail(),
                            patient.getAddress(),
                            patient.getEmergencyContact()
                    });

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Patient not found.");
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Invalid Patient ID.");
    }
}
    private void deletePatient() {

    int row =
            table.getSelectedRow();

    if (row < 0) {

        JOptionPane.showMessageDialog(
                this,
                "Select a patient first.");

        return;
    }

    int patientId =
            Integer.parseInt(
                    tableModel.getValueAt(
                            row,
                            0).toString());

    int choice =
            JOptionPane.showConfirmDialog(
                    this,
                    "Delete this patient?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION);

    if (choice != JOptionPane.YES_OPTION) {
        return;
    }

    PatientService service =
            new PatientService();

    if (service.deletePatient(patientId)) {

        JOptionPane.showMessageDialog(
                this,
                "Patient deleted.");

        loadPatients();
    }
}
}