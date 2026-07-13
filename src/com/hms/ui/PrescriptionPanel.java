package com.hms.ui;

import com.hms.models.Prescription;
import com.hms.services.PrescriptionService;
import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PrescriptionPanel extends JPanel {

    private JTextField txtRecordId;
    private JTextField txtMedicine;
    private JTextField txtDosage;
    private JTextField txtDuration;
    private JTextField txtNotes;

    private JTable table;
    private DefaultTableModel model;

    private PrescriptionService service;

    public PrescriptionPanel() {

        service = new PrescriptionService();

        setLayout(new BorderLayout(15, 15));
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(20, 25, 20, 25));

        //-------------------------------------
        // TITLE
        //-------------------------------------

        JLabel title =
                new JLabel("PRESCRIPTIONS");

        title.setFont(Theme.TITLE);
        title.setForeground(Theme.PRIMARY_GREEN);

        //-------------------------------------
        // FORM PANEL
        //-------------------------------------

        JPanel form =
                new JPanel(new GridBagLayout());

        form.setBackground(Color.WHITE);

        form.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(215, 215, 215)),
                        new EmptyBorder(
                                20,
                                25,
                                20,
                                25)));

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(10, 10, 10, 10);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        txtRecordId =
                new JTextField(18);

        txtMedicine =
                new JTextField(18);

        txtDosage =
                new JTextField(18);

        txtDuration =
                new JTextField(18);

        txtNotes =
                new JTextField(18);

        addRow(
                form,
                gbc,
                0,
                "Record ID:",
                txtRecordId,
                "Medicine:",
                txtMedicine);

        addRow(
                form,
                gbc,
                1,
                "Dosage:",
                txtDosage,
                "Duration:",
                txtDuration);

        addRow(
                form,
                gbc,
                2,
                "Notes:",
                txtNotes,
                "",
                new JLabel());

        //-------------------------------------
        // BUTTONS
        //-------------------------------------

        JButton add =
                createButton(
                        "Add Prescription");

        JButton delete =
                createButton(
                        "Delete");

        JButton search =
                createButton(
                        "Search");

        JButton refresh =
                createButton(
                        "Refresh");

        JPanel buttons =
                new JPanel();

        buttons.setBackground(Color.WHITE);

        buttons.add(add);
        buttons.add(delete);
        buttons.add(search);
        buttons.add(refresh);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;

        form.add(buttons, gbc);

        //-------------------------------------
        // TABLE
        //-------------------------------------

        model =
                new DefaultTableModel(
                        new Object[]{
                                "ID",
                                "Record ID",
                                "Medicine",
                                "Dosage",
                                "Duration",
                                "Notes"
                        },
                        0);

        table =
                new JTable(model);

        table.setRowHeight(28);

        JScrollPane scrollPane =
                new JScrollPane(table);

        scrollPane.setPreferredSize(
                new Dimension(1000, 350));

        //-------------------------------------
        // ADD COMPONENTS
        //-------------------------------------

        add(title, BorderLayout.NORTH);
        add(form, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        //-------------------------------------
        // LOAD DATA
        //-------------------------------------

        loadPrescriptions();

        //-------------------------------------
        // ADD EVENT
        //-------------------------------------

        add.addActionListener(e -> {

            try {

                Prescription p =
                        new Prescription();

                p.setRecordId(
                        Integer.parseInt(
                                txtRecordId.getText()));

                p.setMedicineName(
                        txtMedicine.getText());

                p.setDosage(
                        txtDosage.getText());

                p.setDuration(
                        txtDuration.getText());

                p.setNotes(
                        txtNotes.getText());

                if (service.addPrescription(p)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Prescription Added");

                    clearFields();
                    loadPrescriptions();
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage());
            }
        });

        //-------------------------------------
        // DELETE EVENT
        //-------------------------------------

        delete.addActionListener(e -> {

            int row =
                    table.getSelectedRow();

            if (row < 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Select a prescription.");
                return;
            }

            int option =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Delete this prescription?",
                            "Confirm",
                            JOptionPane.YES_NO_OPTION);

            if (option != JOptionPane.YES_OPTION) {
                return;
            }

            int id =
                    Integer.parseInt(
                            model.getValueAt(
                                    row,
                                    0)
                                    .toString());

            service.deletePrescription(id);

            loadPrescriptions();
        });

        //-------------------------------------
        // SEARCH EVENT
        //-------------------------------------

        search.addActionListener(e -> {

            try {

                int recordId =
                        Integer.parseInt(
                                txtRecordId.getText());

                model.setRowCount(0);

                List<Prescription> list =
                        service.searchPrescriptions(
                                recordId);

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

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage());
            }
        });

        //-------------------------------------
        // REFRESH EVENT
        //-------------------------------------

        refresh.addActionListener(e -> {
            clearFields();
            loadPrescriptions();
        });
    }

    //--------------------------------------------------

    private void loadPrescriptions() {

        model.setRowCount(0);

        List<Prescription> list =
                service.getAllPrescriptions();

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

    //--------------------------------------------------

    private void clearFields() {

        txtRecordId.setText("");
        txtMedicine.setText("");
        txtDosage.setText("");
        txtDuration.setText("");
        txtNotes.setText("");
    }

    //--------------------------------------------------

    private JButton createButton(String text) {

        JButton btn =
                new JButton(text);

        btn.setBackground(
                Theme.PRIMARY_GREEN);

        btn.setForeground(
                Color.WHITE);

        btn.setFocusPainted(false);

        return btn;
    }

    //--------------------------------------------------

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
        panel.add(new JLabel(label1), gbc);

        gbc.gridx = 1;
        panel.add(field1, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel(label2), gbc);

        gbc.gridx = 3;
        panel.add(field2, gbc);
    }
}