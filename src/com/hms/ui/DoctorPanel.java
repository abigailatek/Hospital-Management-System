package com.hms.ui;

import com.hms.models.Doctor;
import com.hms.services.DoctorService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DoctorPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    public DoctorPanel() {

        setLayout(new BorderLayout());

        JLabel title =
                new JLabel("Doctor Management");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28));

        add(title, BorderLayout.NORTH);

        model =
                new DefaultTableModel();

        model.setColumnIdentifiers(
                new Object[]{
                        "ID",
                        "User ID",
                        "First Name",
                        "Last Name",
                        "Specialization",
                        "Phone",
                        "Email"
                });

        table =
                new JTable(model);

        add(
                new JScrollPane(table),
                BorderLayout.CENTER);

        loadDoctors();
    }

    private void loadDoctors() {

        DoctorService service =
                new DoctorService();

        List<Doctor> doctors =
                service.getAllDoctors();

        model.setRowCount(0);

        for (Doctor doctor : doctors) {

            model.addRow(
                    new Object[]{
                            doctor.getDoctorID(),
                            doctor.getUserID(),
                            doctor.getFirstName(),
                            doctor.getLastName(),
                            doctor.getSpecialization(),
                            doctor.getPhone(),
                            doctor.getEmail()
                    });
        }
    }
}