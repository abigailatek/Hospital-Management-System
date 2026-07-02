package com.hms.ui;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class AppointmentsScreen extends JPanel {

    private JTextField patientField;
    private JTextField doctorField;
    private JTextField dateField;
    private JTextField timeField;

    private DefaultTableModel model;

    public AppointmentsScreen() {

        setLayout(new BorderLayout());

        // ================= TITLE =================
        JLabel title = new JLabel("APPOINTMENTS", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        add(title, BorderLayout.NORTH);

        // ================= FORM =================
        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));

        patientField = new JTextField();
        doctorField = new JTextField();
        dateField = new JTextField();
        timeField = new JTextField();

        JButton addBtn = new JButton("Add");
        JButton clearBtn = new JButton("Clear");
        JButton deleteBtn = new JButton("Delete");

        form.add(new JLabel("Patient Name"));
        form.add(patientField);

        form.add(new JLabel("Doctor Name"));
        form.add(doctorField);

        form.add(new JLabel("Date"));
        form.add(dateField);

        form.add(new JLabel("Time"));
        form.add(timeField);

        form.add(addBtn);
        form.add(clearBtn);

        // ================= TABLE =================
        model = new DefaultTableModel(
                new String[]{"Patient", "Doctor", "Date", "Time"}, 0
        );

        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        // ================= BUTTON ACTIONS (UI ONLY) =================

        addBtn.addActionListener(e -> {
            model.addRow(new Object[]{
                    patientField.getText(),
                    doctorField.getText(),
                    dateField.getText(),
                    timeField.getText()
            });
        });

        clearBtn.addActionListener(e -> {
            patientField.setText("");
            doctorField.setText("");
            dateField.setText("");
            timeField.setText("");
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) model.removeRow(row);
        });

        // ================= LAYOUT =================
        add(form, BorderLayout.WEST);
        add(scroll, BorderLayout.CENTER);
    }
}
