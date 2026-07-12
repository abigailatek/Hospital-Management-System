package com.hms.ui;

import com.hms.models.MedicalRecord;
import com.hms.services.MedicalRecordService;
import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

public class MedicalRecordsPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    public MedicalRecordsPanel() {

        setLayout(new BorderLayout(15, 15));
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("MEDICAL RECORDS");
        title.setFont(Theme.TITLE);
        title.setForeground(Theme.PRIMARY_GREEN);

        //------------------------------------
        // Form Panel
        //------------------------------------

        JPanel form = new JPanel(new GridBagLayout());
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

        JTextField txtPatientId =
                new JTextField(18);

        JTextField txtDoctorId =
                new JTextField(18);

        JTextField txtDiagnosis =
                new JTextField(18);

        JTextField txtTreatment =
                new JTextField(18);

        JTextField txtAllergies =
                new JTextField(18);

        JTextField txtChronic =
                new JTextField(18);

        JTextField txtDate =
                new JTextField(
                        LocalDate.now().toString(),
                        18);

        addRow(
                form,
                gbc,
                0,
                "Patient ID:",
                txtPatientId,
                "Doctor ID:",
                txtDoctorId);

        addRow(
                form,
                gbc,
                1,
                "Diagnosis:",
                txtDiagnosis,
                "Treatment:",
                txtTreatment);

        addRow(
                form,
                gbc,
                2,
                "Allergies:",
                txtAllergies,
                "Chronic Conditions:",
                txtChronic);

        addRow(
                form,
                gbc,
                3,
                "Record Date:",
                txtDate,
                "",
                new JLabel());

        //------------------------------------
        // Buttons
        //------------------------------------

        JButton save =
                new JButton("Save Record");

        save.setBackground(
                Theme.PRIMARY_GREEN);

        save.setForeground(
                Color.WHITE);

        JPanel buttons =
                new JPanel();

        buttons.setBackground(
                Color.WHITE);

        buttons.add(save);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;

        form.add(buttons, gbc);

        //------------------------------------
        // Table
        //------------------------------------

        model =
                new DefaultTableModel(
                        new Object[]{
                                "Record ID",
                                "Patient ID",
                                "Doctor ID",
                                "Diagnosis",
                                "Treatment",
                                "Date"
                        },
                        0);

        table =
                new JTable(model);

        table.setRowHeight(28);

        JScrollPane scroll =
                new JScrollPane(table);

        //------------------------------------
        // Layout
        //------------------------------------

        add(title, BorderLayout.NORTH);

        JPanel center =
                new JPanel(
                        new BorderLayout(15, 15));

        center.setBackground(
                Theme.BACKGROUND);

        center.add(form, BorderLayout.NORTH);
        center.add(scroll, BorderLayout.CENTER);

        add(center, BorderLayout.CENTER);

        //------------------------------------
        // Save Event
        //------------------------------------

        save.addActionListener(e -> {

            try {

                MedicalRecord record =
                        new MedicalRecord();

                record.setPatientId(
                        Integer.parseInt(
                                txtPatientId.getText()));

                record.setDoctorId(
                        Integer.parseInt(
                                txtDoctorId.getText()));

                record.setDiagnosis(
                        txtDiagnosis.getText());

                record.setTreatment(
                        txtTreatment.getText());

                record.setAllergies(
                        txtAllergies.getText());

                record.setChronicConditions(
                        txtChronic.getText());

                record.setRecordDate(
                        LocalDate.parse(
                                txtDate.getText()));

                MedicalRecordService service =
                        new MedicalRecordService();

                if (service.addMedicalRecord(
                        record)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Medical Record Saved.");

                    model.addRow(
                            new Object[]{
                                    "",
                                    record.getPatientId(),
                                    record.getDoctorId(),
                                    record.getDiagnosis(),
                                    record.getTreatment(),
                                    record.getRecordDate()
                            });

                    txtPatientId.setText("");
                    txtDoctorId.setText("");
                    txtDiagnosis.setText("");
                    txtTreatment.setText("");
                    txtAllergies.setText("");
                    txtChronic.setText("");
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage());
            }
        });
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