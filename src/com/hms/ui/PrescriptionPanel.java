package com.hms.ui;

import com.hms.models.Prescription;
import com.hms.services.PrescriptionService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PrescriptionPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private JTextField txtSearch;

    public PrescriptionPanel() {

        setLayout(new BorderLayout());

        JLabel title =
                new JLabel("Prescription Management");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28));

        add(title, BorderLayout.NORTH);

        //--------------------------
        // Toolbar
        //--------------------------

        JPanel toolbar = new JPanel();

        JButton btnAdd =
                new JButton("Add");

        JButton btnDelete =
                new JButton("Delete");

        JButton btnRefresh =
                new JButton("Refresh");

        txtSearch =
                new JTextField(15);

        JButton btnSearch =
                new JButton("Search Record ID");

        toolbar.add(btnAdd);
        toolbar.add(btnDelete);
        toolbar.add(btnRefresh);
        toolbar.add(txtSearch);
        toolbar.add(btnSearch);

        add(toolbar,
                BorderLayout.SOUTH);

        //--------------------------
        // Table
        //--------------------------

        model =
                new DefaultTableModel();

        model.setColumnIdentifiers(
                new Object[]{
                        "Prescription ID",
                        "Record ID",
                        "Medicine",
                        "Dosage",
                        "Duration",
                        "Notes"
                });

        table =
                new JTable(model);

        table.setRowHeight(25);

        add(
                new JScrollPane(table),
                BorderLayout.CENTER);

        //--------------------------
        // Events
        //--------------------------

        btnRefresh.addActionListener(
                e -> loadPrescriptions());

        btnAdd.addActionListener(
                e -> addPrescription());

        btnDelete.addActionListener(
                e -> deletePrescription());

        btnSearch.addActionListener(
                e -> searchPrescriptions());

        loadPrescriptions();
    }

    //-----------------------------------

    private void loadPrescriptions() {

        PrescriptionService service =
                new PrescriptionService();

        List<Prescription> list =
                service.getAllPrescriptions();

        model.setRowCount(0);

        for (Prescription p : list) {

            model.addRow(
                    new Object[]{
                            p.getPrescriptionId(),
                            p.getRecordId(),
                            p.getMedicineName(),
                            p.getDosage(),
                            p.getDuration(),
                            p.getNotes()
                    });
        }
    }

    //-----------------------------------

    private void addPrescription() {

        try {

            Prescription p =
                    new Prescription();

            p.setRecordId(
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Record ID")));

            p.setMedicineName(
                    JOptionPane.showInputDialog(
                            this,
                            "Medicine Name"));

            p.setDosage(
                    JOptionPane.showInputDialog(
                            this,
                            "Dosage"));

            p.setDuration(
                    JOptionPane.showInputDialog(
                            this,
                            "Duration"));

            p.setNotes(
                    JOptionPane.showInputDialog(
                            this,
                            "Notes"));

            PrescriptionService service =
                    new PrescriptionService();

            if (service.addPrescription(p)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Prescription added.");

                loadPrescriptions();
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid input.");
        }
    }

    //-----------------------------------

    private void deletePrescription() {

        int row =
                table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a prescription.");

            return;
        }

        int id =
                (Integer)
                        model.getValueAt(
                                row,
                                0);

        PrescriptionService service =
                new PrescriptionService();

        if (service.deletePrescription(id)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Prescription deleted.");

            loadPrescriptions();
        }
    }

    //-----------------------------------

    private void searchPrescriptions() {

        try {

            int recordId =
                    Integer.parseInt(
                            txtSearch.getText());

            PrescriptionService service =
                    new PrescriptionService();

            List<Prescription> list =
                    service.searchPrescriptions(
                            recordId);

            model.setRowCount(0);

            for (Prescription p : list) {

                model.addRow(
                        new Object[]{
                                p.getPrescriptionId(),
                                p.getRecordId(),
                                p.getMedicineName(),
                                p.getDosage(),
                                p.getDuration(),
                                p.getNotes()
                        });
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid Record ID.");
        }
    }
}