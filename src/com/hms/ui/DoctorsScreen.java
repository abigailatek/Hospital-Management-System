package com.hms.ui;

import com.hms.models.Doctor;
import com.hms.services.DoctorService;
import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DoctorsScreen extends JPanel {

    private JTextField doctorId;
    private JTextField fullName;
    private JTextField specialization;
    private JTextField phone;
    private JTextField email;
    private JTextField availability;

    private JTable table;
    private DefaultTableModel model;

    private DoctorService service;

    public DoctorsScreen() {

        service = new DoctorService();

        setLayout(new BorderLayout(15, 15));
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("DOCTOR MANAGEMENT");
        title.setFont(Theme.TITLE);
        title.setForeground(Theme.PRIMARY_GREEN);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(215, 215, 215)),
                new EmptyBorder(20, 25, 20, 25)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        doctorId = field();
        fullName = field();
        specialization = field();
        phone = field();
        email = field();
        availability = field();

        addRow(formPanel, gbc, 0,
                "Doctor ID:", doctorId,
                "Full Name:", fullName);

        addRow(formPanel, gbc, 1,
                "Specialization:", specialization,
                "Phone:", phone);

        addRow(formPanel, gbc, 2,
                "Email:", email,
                "Availability:", availability);

        JPanel buttons = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER,
                        15,
                        5));

        buttons.setBackground(Color.WHITE);

        JButton add = button("Add Doctor");
        JButton update = button("Update");
        JButton clear = button("Clear");
        JButton delete = button("Delete");

        buttons.add(add);
        buttons.add(update);
        buttons.add(clear);
        buttons.add(delete);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        formPanel.add(buttons, gbc);

        model = new DefaultTableModel(
                new String[]{
                        "Doctor ID",
                        "Full Name",
                        "Specialization",
                        "Phone",
                        "Email",
                        "Availability"
                },
                0
        );

        table = new JTable(model);
        table.setRowHeight(28);

        JScrollPane tablePane =
                new JScrollPane(table);

        JPanel center =
                new JPanel(
                        new BorderLayout(15, 15));

        center.setBackground(Theme.BACKGROUND);
        center.add(formPanel, BorderLayout.NORTH);
        center.add(tablePane, BorderLayout.CENTER);

        add(title, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);

        //-------------------------
        // Events
        //-------------------------

        add.addActionListener(e -> addDoctor());

        update.addActionListener(e -> updateDoctor());

        delete.addActionListener(e -> deleteDoctor());

        clear.addActionListener(e -> clearFields());

        table.getSelectionModel()
                .addListSelectionListener(e -> {

                    int row =
                            table.getSelectedRow();

                    if (row >= 0) {

                        doctorId.setText(
                                model.getValueAt(
                                        row,
                                        0).toString());

                        fullName.setText(
                                model.getValueAt(
                                        row,
                                        1).toString());

                        specialization.setText(
                                model.getValueAt(
                                        row,
                                        2).toString());

                        phone.setText(
                                model.getValueAt(
                                        row,
                                        3).toString());

                        email.setText(
                                model.getValueAt(
                                        row,
                                        4).toString());

                        availability.setText(
                                model.getValueAt(
                                        row,
                                        5).toString());
                    }
                });

        loadDoctors();
    }

    //---------------------------------------------------

    private void loadDoctors() {

        model.setRowCount(0);

        List<Doctor> doctors =
                service.getAllDoctors();

        for (Doctor d : doctors) {

            String name =
                    d.getFirstName() +
                            " " +
                            d.getLastName();

            model.addRow(new Object[]{
                    d.getDoctorID(),
                    name,
                    d.getSpecialization(),
                    d.getPhone(),
                    d.getEmail(),
                    "Available"
            });
        }
    }

    //---------------------------------------------------

    private void addDoctor() {

        try {

            Doctor doctor =
                    new Doctor();

            doctor.setDoctorID(
                    Integer.parseInt(
                            doctorId.getText()));

            String[] names =
                    fullName.getText()
                            .trim()
                            .split(" ", 2);

            doctor.setFirstName(names[0]);

            if (names.length > 1) {
                doctor.setLastName(names[1]);
            } else {
                doctor.setLastName("");
            }

            doctor.setSpecialization(
                    specialization.getText());

            doctor.setPhone(
                    phone.getText());

            doctor.setEmail(
                    email.getText());

            doctor.setUserID(0);

            if (service.addDoctor(doctor)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Doctor added successfully.");

                loadDoctors();
                clearFields();
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage());
        }
    }

    //---------------------------------------------------

    private void updateDoctor() {

        JOptionPane.showMessageDialog(
                this,
                "Update functionality can be added later.");
    }

    //---------------------------------------------------

    private void deleteDoctor() {

        int row =
                table.getSelectedRow();

        if (row < 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a doctor.");

            return;
        }

        int id =
                Integer.parseInt(
                        model.getValueAt(
                                row,
                                0)
                                .toString());

        if (service.deleteDoctor(id)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Doctor deleted.");

            loadDoctors();
            clearFields();
        }
    }

    //---------------------------------------------------

    private void clearFields() {

        doctorId.setText("");
        fullName.setText("");
        specialization.setText("");
        phone.setText("");
        email.setText("");
        availability.setText("");
    }

    //---------------------------------------------------

    private void addRow(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String label1,
            JComponent field1,
            String label2,
            JComponent field2) {

        gbc.gridy = row;
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        panel.add(label(label1), gbc);

        gbc.gridx = 1;
        panel.add(field1, gbc);

        gbc.gridx = 2;
        panel.add(label(label2), gbc);

        gbc.gridx = 3;
        panel.add(field2, gbc);
    }

    private JTextField field() {
        JTextField field = new JTextField(18);
        field.setFont(Theme.NORMAL);
        return field;
    }

    private JLabel label(String text) {
        JLabel label = new JLabel(text);
        label.setFont(Theme.NORMAL);
        label.setForeground(Theme.TEXT);
        return label;
    }

    private JButton button(String text) {
        JButton btn = new JButton(text);
        btn.setFont(Theme.NORMAL);
        btn.setBackground(Theme.PRIMARY_GREEN);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        return btn;
    }
}