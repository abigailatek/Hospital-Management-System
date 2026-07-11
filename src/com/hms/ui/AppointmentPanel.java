package com.hms.ui;

import com.hms.models.Appointment;
import com.hms.services.AppointmentService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.List;

public class AppointmentPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnRefresh;
    private JButton btnSearch;

    private JTextField txtSearch;

    public AppointmentPanel() {

        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 250));

        JLabel title = new JLabel("Appointment Management");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20));

        JPanel searchPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT));

        txtSearch = new JTextField(20);
        btnSearch = new JButton("Search");

        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        JPanel topPanel =
                new JPanel(
                        new BorderLayout());

        topPanel.add(title, BorderLayout.WEST);
        topPanel.add(searchPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        btnAdd = new JButton("Add");
        btnDelete = new JButton("Delete");
        btnRefresh = new JButton("Refresh");

        JPanel toolbar =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT));

        toolbar.add(btnAdd);
        toolbar.add(btnDelete);
        toolbar.add(btnRefresh);

        model = new DefaultTableModel();

        model.setColumnIdentifiers(
                new Object[]{
                        "Appointment ID",
                        "Patient ID",
                        "Doctor ID",
                        "Date",
                        "Time",
                        "Status",
                        "Reason"
                });

        table = new JTable(model);

        table.setRowHeight(25);

        JScrollPane scrollPane =
                new JScrollPane(table);

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout());

        centerPanel.add(
                toolbar,
                BorderLayout.NORTH);

        centerPanel.add(
                scrollPane,
                BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        btnRefresh.addActionListener(
                e -> loadAppointments());

        btnAdd.addActionListener(
                e -> addAppointment());

        btnDelete.addActionListener(
                e -> deleteAppointment());

        btnSearch.addActionListener(
                e -> searchAppointments());

        txtSearch.addActionListener(
                e -> searchAppointments());

        loadAppointments();
    }

    //------------------------------------------------

    private void loadAppointments() {

        AppointmentService service =
                new AppointmentService();

        List<Appointment> appointments =
                service.getAllAppointments();

        model.setRowCount(0);

        for (Appointment a : appointments) {

            model.addRow(
                    new Object[]{
                            a.getAppointmentId(),
                            a.getPatientId(),
                            a.getDoctorId(),
                            a.getAppointmentDate(),
                            a.getAppointmentTime(),
                            a.getStatus(),
                            a.getReason()
                    });
        }
    }

    //------------------------------------------------

    private void addAppointment() {

        try {

            Appointment a =
                    new Appointment();

            a.setPatientId(
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Patient ID")));

            a.setDoctorId(
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Doctor ID")));

           a.setAppointmentDate(
        LocalDate.parse( JOptionPane.showInputDialog(
                        this,"Date (yyyy-mm-dd)")));


            a.setAppointmentTime(
                    LocalTime.parse(JOptionPane.showInputDialog(
                                    this,"Time (HH:mm:ss)")));

            a.setStatus(
                    JOptionPane.showInputDialog(
                            this,
                            "Status"));

            a.setReason(
                    JOptionPane.showInputDialog(
                            this,
                            "Reason"));

            AppointmentService service =
                    new AppointmentService();

            if (service.addAppointment(a)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Appointment added.");

                loadAppointments();
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid input.");
        }
    }

    //------------------------------------------------

    private void deleteAppointment() {

        int row =
                table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select an appointment.");

            return;
        }

        int appointmentId =
                (Integer)
                        model.getValueAt(
                                row,
                                0);

        AppointmentService service =
                new AppointmentService();

        if (service.deleteAppointment(
                appointmentId)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment deleted.");

            loadAppointments();
        }
    }

    //------------------------------------------------

   private void searchAppointments() {

    try {

        int doctorId =
                Integer.parseInt(
                        txtSearch.getText().trim());

        AppointmentService service =
                new AppointmentService();

        List<Appointment> appointments =
                service.searchAppointments(
                        doctorId);

        model.setRowCount(0);

        for (Appointment a : appointments) {

            model.addRow(new Object[]{
                    a.getAppointmentId(),
                    a.getPatientId(),
                    a.getDoctorId(),
                    a.getAppointmentDate(),
                    a.getAppointmentTime(),
                    a.getStatus(),
                    a.getReason()
            });
        }

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter a valid Doctor ID.");
    }
}
}