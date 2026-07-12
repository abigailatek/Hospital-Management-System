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

    private DefaultTableModel model;

    public PrescriptionPanel() {

        setLayout(new BorderLayout(15,15));
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(20,25,20,25));

        JLabel title =
                new JLabel("PRESCRIPTIONS");

        title.setFont(Theme.TITLE);
        title.setForeground(Theme.PRIMARY_GREEN);

        JPanel form =
                new JPanel(new GridBagLayout());

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
                new Insets(10,10,10,10);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        JTextField txtRecordId =
                new JTextField(18);

        JTextField txtMedicine =
                new JTextField(18);

        JTextField txtDosage =
                new JTextField(18);

        JTextField txtDuration =
                new JTextField(18);

        JTextField txtNotes =
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

        JButton add =
                new JButton(
                        "Add Prescription");

        JButton delete =
                new JButton(
                        "Delete");

        add.setBackground(
                Theme.PRIMARY_GREEN);

        add.setForeground(
                Color.WHITE);

        delete.setBackground(
                Theme.PRIMARY_GREEN);

        delete.setForeground(
                Color.WHITE);

        JPanel buttons =
                new JPanel();

        buttons.setBackground(
                Color.WHITE);

        buttons.add(add);
        buttons.add(delete);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;

        form.add(buttons, gbc);

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

        JTable table =
                new JTable(model);

        table.setRowHeight(28);

        add(title, BorderLayout.NORTH);
        add(form, BorderLayout.CENTER);
        add(new JScrollPane(table),
                BorderLayout.SOUTH);

        loadPrescriptions();

        PrescriptionService service =
                new PrescriptionService();

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

                    loadPrescriptions();

                    txtRecordId.setText("");
                    txtMedicine.setText("");
                    txtDosage.setText("");
                    txtDuration.setText("");
                    txtNotes.setText("");
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage());
            }
        });

        delete.addActionListener(e -> {

            int row =
                    table.getSelectedRow();

            if (row < 0) {
                return;
            }

            int id =
                    Integer.parseInt(
                            model.getValueAt(
                                    row,
                                    0).toString());

            service.deletePrescription(id);

            loadPrescriptions();
        });
    }

    private void loadPrescriptions() {

        model.setRowCount(0);

        PrescriptionService service =
                new PrescriptionService();

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