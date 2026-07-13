package com.hms.ui;

import com.hms.models.Appointment;
import com.hms.services.AppointmentService;
import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AppointmentScreen extends JPanel {

    private JTextField patientId;
    private JTextField doctorId;
    private JTextField date;
    private JTextField time;
    private JTextField reason;
    private JComboBox<String> status;

    private JTable table;
    private DefaultTableModel model;

    private AppointmentService service;

    public AppointmentScreen() {

        service = new AppointmentService();

        setLayout(new BorderLayout());
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(20, 25, 20, 25));

        JLabel title =
                new JLabel(
                        "APPOINTMENTS",
                        SwingConstants.CENTER);

        title.setFont(Theme.TITLE);
        title.setForeground(Theme.PRIMARY_GREEN);

        JPanel form =
                new JPanel(
                        new GridBagLayout());

        form.setBackground(Color.WHITE);
        form.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(215,215,215)),
                        new EmptyBorder(
                                20,
                                25,
                                20,
                                25)));

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(8,10,8,10);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        patientId =
                new JTextField(18);

        doctorId =
                new JTextField(18);

        date =
                new JTextField(18);

        time =
                new JTextField(18);
        reason =
                 new JTextField(18);
        status =
                new JComboBox<>(
                        new String[]{
                                "Scheduled",
                                "Completed",
                                "Cancelled"
                        });

        addRow(
                form,
                gbc,
                0,
                "Patient ID:",
                patientId,
                "Doctor ID:",
                doctorId);

        addRow(
                form,
                gbc,
                1,
                "Date (yyyy-mm-dd):",
                date,
                "Time (HH:mm:ss):",
                time);

        addRow(
                form,
                gbc,
                2,
                "Reason:",
                reason,
                "Status:",
                status);

        JButton add =
                button("Add Appointment");

        JButton clear =
                button("Clear");

        JButton delete =
                button("Delete");

         JButton update =
        button("Update");

        JButton search =
        button("Search");

        JButton refresh =
        button("Refresh");

        JPanel buttons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                5));

        buttons.setBackground(Color.WHITE);

        buttons.add(add);
        buttons.add(update);
        buttons.add(search);
        buttons.add(refresh);
        buttons.add(clear);
        buttons.add(delete); 

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;

        form.add(buttons, gbc);

        model =
        new DefaultTableModel(
                new String[]{
                        "Appointment ID",
                        "Patient ID",
                        "Doctor ID",
                        "Date",
                        "Time",
                        "Reason",
                        "Status"
                },
                0);
        table =
                new JTable(model);

        table.setRowHeight(28);

        JScrollPane scrollPane =
                new JScrollPane(table);

        JPanel center =
                new JPanel(
                        new BorderLayout(
                                15,
                                15));

        center.setBackground(
                Theme.BACKGROUND);

        center.add(
                form,
                BorderLayout.NORTH);

        center.add(
                scrollPane,
                BorderLayout.CENTER);

        add(title, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);

        //-----------------------------------
        // Events
        //-----------------------------------

        add.addActionListener(
                e -> addAppointment());

        clear.addActionListener(
                e -> clearFields());

        delete.addActionListener(
                e -> deleteAppointment());
         update.addActionListener(
        e -> updateAppointment());

        search.addActionListener(
        e -> searchAppointments());

         refresh.addActionListener(
        e -> loadAppointments());

        loadAppointments();
        table.getSelectionModel()
        .addListSelectionListener(e -> {

            int row = table.getSelectedRow();

            if (row < 0) {
                return;
            }

            patientId.setText(
                    model.getValueAt(row,1).toString());

            doctorId.setText(
                    model.getValueAt(row,2).toString());

            date.setText(
                    model.getValueAt(row,3).toString());

            time.setText(
                    model.getValueAt(row,4).toString());
             reason.setText(
        model.getValueAt(row,5).toString());

             status.setSelectedItem(
        model.getValueAt(row,6).toString());
        });
    }

    //------------------------------------------------

    private void loadAppointments() {

        model.setRowCount(0);

        List<Appointment> appointments =
                service.getAllAppointments();

        for (Appointment a : appointments) {

         model.addRow(
        new Object[]{
                a.getAppointmentId(),
                a.getPatientId(),
                a.getDoctorId(),
                a.getAppointmentDate(),
                a.getAppointmentTime(),
                a.getReason(),
                a.getStatus()
        });
        }
    }

    //------------------------------------------------

    private void addAppointment() {

        try {

            Appointment appointment =
                    new Appointment();

            appointment.setPatientId(
                    Integer.parseInt(
                            patientId.getText()));

            appointment.setDoctorId(
                    Integer.parseInt(
                            doctorId.getText()));

            appointment.setAppointmentDate(
                    LocalDate.parse(
                            date.getText()));

            appointment.setAppointmentTime(
                    LocalTime.parse(
                            time.getText()));

            appointment.setStatus(
                    status.getSelectedItem()
                            .toString());

            appointment.setReason(
                         reason.getText());

            if (service.addAppointment(
                    appointment)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Appointment created.");

                loadAppointments();

                clearFields();
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage());
        }
    }

    //------------------------------------------------

    private void deleteAppointment() {

        int row =
                table.getSelectedRow();

        if (row < 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select appointment.");

            return;
        }

        int id =
                Integer.parseInt(
                        model.getValueAt(
                                row,
                                0)
                                .toString());

        if (service.deleteAppointment(
                id)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment deleted.");

            loadAppointments();
        }
    }

    //------------------------------------------------

       private void updateAppointment() {

    int row = table.getSelectedRow();

    if (row < 0) {

        JOptionPane.showMessageDialog(
                this,
                "Select an appointment.");

        return;
    }

    try {

        Appointment appointment =
                new Appointment();

        appointment.setAppointmentId(
                Integer.parseInt(
                        model.getValueAt(
                                row,
                                0).toString()));

        appointment.setPatientId(
                Integer.parseInt(
                        patientId.getText()));

        appointment.setDoctorId(
                Integer.parseInt(
                        doctorId.getText()));

        appointment.setAppointmentDate(
                LocalDate.parse(
                        date.getText()));

        appointment.setAppointmentTime(
                LocalTime.parse(
                        time.getText()));

        appointment.setStatus(
                status.getSelectedItem()
                        .toString());

        appointment.setReason(
                reason.getText());

        if (service.updateAppointment(
                appointment)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment updated successfully.");
            loadAppointments();
            clearFields();
        }

    } catch (Exception ex) {

        JOptionPane.showMessageDialog(
                this,
                ex.getMessage());
    }
}


private void searchAppointments() {

    try {

        int doctor =
                Integer.parseInt(
                        JOptionPane.showInputDialog(
                                this,
                                "Enter Doctor ID"));

        List<Appointment> appointments =
                service.searchAppointments(
                        doctor);

        model.setRowCount(0);

        for (Appointment a : appointments) {

         model.addRow(
        new Object[]{
                a.getAppointmentId(),
                a.getPatientId(),
                a.getDoctorId(),
                a.getAppointmentDate(),
                a.getAppointmentTime(),
                a.getReason(),
                a.getStatus()
        });
        }

    } catch (Exception ex) {

        JOptionPane.showMessageDialog(
                this,
                "Invalid Doctor ID.");
    }
}
 private void clearFields() {
    patientId.setText("");
    doctorId.setText("");
    date.setText("");
    time.setText("");
    reason.setText("");
    status.setSelectedIndex(0);
 }
    //------------------------------------------------
    private void addRow(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String label1,
            JComponent field1,
            String label2,
            JComponent field2) {

        gbc.gridy = row;

        gbc.gridx = 0;
        panel.add(label(label1), gbc);

        gbc.gridx = 1;
        panel.add(field1, gbc);

        gbc.gridx = 2;
        panel.add(label(label2), gbc);

        gbc.gridx = 3;
        panel.add(field2, gbc);
    }

    private JLabel label(String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                Theme.NORMAL);

        label.setForeground(
                Theme.TEXT);

        return label;
    }

    private JButton button(String text) {

        JButton btn =
                new JButton(text);

        btn.setFont(
                Theme.NORMAL);

        btn.setBackground(
                Theme.PRIMARY_GREEN);

        btn.setForeground(
                Color.WHITE);

        btn.setFocusPainted(false);

        return btn;
    }

}