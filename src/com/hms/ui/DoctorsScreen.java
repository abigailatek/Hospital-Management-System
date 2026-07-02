package com.hms.ui;

import com.hms.models.Doctor;
import com.hms.services.DoctorService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DoctorsScreen extends JPanel {

    JTextField nameField, specField;
    DefaultTableModel model;

    public DoctorsScreen() {

        setLayout(new BorderLayout());

        // FORM
        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));

        nameField = new JTextField();
        specField = new JTextField();

        JButton addBtn = new JButton("Add Doctor");
        JButton deleteBtn = new JButton("Delete Doctor");

        form.add(new JLabel("Name"));
        form.add(nameField);

        form.add(new JLabel("Specialization"));
        form.add(specField);

        form.add(addBtn);
        form.add(deleteBtn);

        // TABLE
        model = new DefaultTableModel(new String[]{"Name", "Specialization"}, 0);
        JTable table = new JTable(model);

        refreshTable();

        // ACTIONS
        addBtn.addActionListener(e -> {
            Doctor d = new Doctor(nameField.getText(), specField.getText());
            DoctorService.addDoctor(d);
            refreshTable();
        });

        deleteBtn.addActionListener(e -> {
            DoctorService.deleteDoctor(nameField.getText());
            refreshTable();
        });

        add(form, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void refreshTable() {
        model.setRowCount(0);

        for (Doctor d : DoctorService.getDoctors()) {
            model.addRow(new Object[]{
                    d.getName(),
                    d.getSpecialization()
            });
        }
    }
}