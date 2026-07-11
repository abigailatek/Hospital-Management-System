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

    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnRefresh;
    private JButton btnSearch;

    private JTextField txtSearch;

    public DoctorPanel() {

        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 250));

        //--------------------------------
        // Title
        //--------------------------------

        JLabel title = new JLabel("Doctor Management");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20));

        //--------------------------------
        // Search Panel
        //--------------------------------

        JPanel searchPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT));

        txtSearch = new JTextField(20);
        btnSearch = new JButton("Search");

        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        //--------------------------------
        // Top Panel
        //--------------------------------

        JPanel topPanel =
                new JPanel(
                        new BorderLayout());

        topPanel.add(title, BorderLayout.WEST);
        topPanel.add(searchPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        //--------------------------------
        // Toolbar
        //--------------------------------

        btnAdd = new JButton("Add");
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
        btnRefresh = new JButton("Refresh");

        JPanel toolbar =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT));

        toolbar.add(btnAdd);
        toolbar.add(btnEdit);
        toolbar.add(btnDelete);
        toolbar.add(btnRefresh);

        //--------------------------------
        // Table
        //--------------------------------

        model = new DefaultTableModel();

        model.setColumnIdentifiers(
                new Object[]{
                        "Doctor ID",
                        "User ID",
                        "First Name",
                        "Last Name",
                        "Specialization",
                        "Phone",
                        "Email"
                });

        table = new JTable(model);

        table.setRowHeight(25);
        table.getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                14));

        JScrollPane scrollPane =
                new JScrollPane(table);

        //--------------------------------
        // Center Panel
        //--------------------------------

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

        //--------------------------------
        // Events
        //--------------------------------

        btnRefresh.addActionListener(
                e -> loadDoctors());

        btnAdd.addActionListener(
                e -> addDoctor());

        btnEdit.addActionListener(
                e -> editDoctor());

        btnDelete.addActionListener(
                e -> deleteDoctor());

        btnSearch.addActionListener(
                e -> searchDoctors());

        txtSearch.addActionListener(
                e -> searchDoctors());

        //--------------------------------

        loadDoctors();
    }

    //--------------------------------------------------
    // Load Doctors
    //--------------------------------------------------

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

    //--------------------------------------------------
    // Add Doctor
    //--------------------------------------------------

    private void addDoctor() {

        try {

            Doctor doctor =
                    new Doctor();

            doctor.setUserID(
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    this,
                                    "User ID")));

            doctor.setFirstName(
                    JOptionPane.showInputDialog(
                            this,
                            "First Name"));

            doctor.setLastName(
                    JOptionPane.showInputDialog(
                            this,
                            "Last Name"));

            doctor.setSpecialization(
                    JOptionPane.showInputDialog(
                            this,
                            "Specialization"));

            doctor.setPhone(
                    JOptionPane.showInputDialog(
                            this,
                            "Phone"));

            doctor.setEmail(
                    JOptionPane.showInputDialog(
                            this,
                            "Email"));

            DoctorService service =
                    new DoctorService();

            if (service.createDoctor(
                    doctor)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Doctor added successfully.");

                loadDoctors();
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid input.");
        }
    }

    //--------------------------------------------------
    // Edit Doctor
    //--------------------------------------------------

    private void editDoctor() {

        int row =
                table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a doctor.");

            return;
        }

        try {

            Doctor doctor =
                    new Doctor();

            doctor.setDoctorID(
                    (Integer)
                            model.getValueAt(
                                    row,
                                    0));

            doctor.setUserID(
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    this,
                                    "User ID",
                                    model.getValueAt(
                                            row,
                                            1))));

            doctor.setFirstName(
                    JOptionPane.showInputDialog(
                            this,
                            "First Name",
                            model.getValueAt(
                                    row,
                                    2)));

            doctor.setLastName(
                    JOptionPane.showInputDialog(
                            this,
                            "Last Name",
                            model.getValueAt(
                                    row,
                                    3)));

            doctor.setSpecialization(
                    JOptionPane.showInputDialog(
                            this,
                            "Specialization",
                            model.getValueAt(
                                    row,
                                    4)));

            doctor.setPhone(
                    JOptionPane.showInputDialog(
                            this,
                            "Phone",
                            model.getValueAt(
                                    row,
                                    5)));

            doctor.setEmail(
                    JOptionPane.showInputDialog(
                            this,
                            "Email",
                            model.getValueAt(
                                    row,
                                    6)));

            DoctorService service =
                    new DoctorService();

            if (service.updateDoctor(
                    doctor)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Doctor updated successfully.");

                loadDoctors();
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid input.");
        }
    }

    //--------------------------------------------------
    // Delete Doctor
    //--------------------------------------------------

    private void deleteDoctor() {

        int row =
                table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a doctor.");

            return;
        }

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete this doctor?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION);

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        int doctorID =
                (Integer)
                        model.getValueAt(
                                row,
                                0);

        DoctorService service =
                new DoctorService();

        if (service.deleteDoctor(
                doctorID)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Doctor deleted.");

            loadDoctors();
        }
    }

    //--------------------------------------------------
    // Search Doctors
    //--------------------------------------------------

    private void searchDoctors() {

        String keyword =
                txtSearch.getText()
                        .trim();

        DoctorService service =
                new DoctorService();

        List<Doctor> doctors =
                service.searchDoctors(
                        keyword);

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