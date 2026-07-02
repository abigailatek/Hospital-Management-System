package com.hms.ui;

import com.hms.models.Patient;
import com.hms.services.PatientService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PatientsScreen extends JPanel {

    JTextField nameField, ageField, genderField, searchField;
    DefaultTableModel model;

    public PatientsScreen() {

        setLayout(new BorderLayout());

        // ===== FORM =====
        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));
        nameField = new JTextField();
        ageField = new JTextField();
        genderField = new JTextField();
        searchField = new JTextField();

        JButton addBtn = new JButton("Add Patient");
        JButton deleteBtn = new JButton("Delete Patient");
        JButton searchBtn = new JButton("Search");
        JButton refreshBtn = new JButton("Refresh");

        form.add(new JLabel("Name"));
        form.add(nameField);

        form.add(new JLabel("Age"));
        form.add(ageField);

        form.add(new JLabel("Gender"));
        form.add(genderField);

        form.add(addBtn);
        form.add(deleteBtn);

        form.add(new JLabel("Search"));
        form.add(searchField);

        form.add(searchBtn);
        form.add(refreshBtn);

        // ===== TABLE =====
        model = new DefaultTableModel(new String[]{"Name", "Age", "Gender"}, 0);
        JTable table = new JTable(model);

        refreshTable(PatientService.getPatients());

        table.getSelectionModel().addListSelectionListener(e -> {
    int selectedRow = table.getSelectedRow();

    if (selectedRow >= 0) {
        nameField.setText(model.getValueAt(selectedRow, 0).toString());
        ageField.setText(model.getValueAt(selectedRow, 1).toString());
        genderField.setText(model.getValueAt(selectedRow, 2).toString());
    }
        });

        // ===== ACTIONS =====

        addBtn.addActionListener(e -> {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = genderField.getText();

            Patient p = new Patient(name, age, gender);
            PatientService.addPatient(p);

            refreshTable(PatientService.getPatients());
        });

        deleteBtn.addActionListener(e -> {
            String name = nameField.getText();
            PatientService.deletePatient(name);
            refreshTable(PatientService.getPatients());
        });

        searchBtn.addActionListener(e -> {
            String keyword = searchField.getText();
            refreshTable(PatientService.searchPatient(keyword));
        });

        refreshBtn.addActionListener(e -> {
            refreshTable(PatientService.getPatients());
        });

        add(form, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void refreshTable(java.util.List<Patient> list) {
        model.setRowCount(0);

        for (Patient p : list) {
            model.addRow(new Object[]{
                    p.getName(),
                    p.getAge(),
                    p.getGender()
            });
        }
    }
}
