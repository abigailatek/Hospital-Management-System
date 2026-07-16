package com.hms.ui;

import com.hms.models.LabTest;
import com.hms.services.LabTestService;
import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class LaboratoryPanel extends JPanel {

    private DefaultTableModel model;
    private JTable table;
    private LabTestService service;

    public LaboratoryPanel() {

        service = new LabTestService();

        setLayout(new BorderLayout(15, 15));
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("LABORATORY");
        title.setFont(Theme.TITLE);
        title.setForeground(Theme.PRIMARY_GREEN);

        //---------------------------------------------------
        // Form Panel
        //---------------------------------------------------

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(215, 215, 215)),
                        new EmptyBorder(20, 25, 20, 25)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtPatientId = new JTextField(18);
        JTextField txtDoctorId = new JTextField(18);
        JTextField txtTestName = new JTextField(18);
        JTextField txtResult = new JTextField(18);
        JTextField txtDate = new JTextField(18);

        JComboBox<String> cmbStatus =
                new JComboBox<>(
                        new String[]{
                                "Pending",
                                "Completed"
                        });

        addRow(
                formPanel,
                gbc,
                0,
                "Patient ID:",
                txtPatientId,
                "Doctor ID:",
                txtDoctorId);

        addRow(
                formPanel,
                gbc,
                1,
                "Test Name:",
                txtTestName,
                "Status:",
                cmbStatus);

        addRow(
                formPanel,
                gbc,
                2,
                "Result:",
                txtResult,
                "Test Date:",
                txtDate);

        //---------------------------------------------------
        // Buttons
        //---------------------------------------------------

        JButton add = new JButton("Add Test");
        JButton delete = new JButton("Delete");
        JButton refresh = new JButton("Refresh");

        add.setBackground(Theme.PRIMARY_GREEN);
        delete.setBackground(Theme.PRIMARY_GREEN);
        refresh.setBackground(Theme.PRIMARY_GREEN);

        add.setForeground(Color.WHITE);
        delete.setForeground(Color.WHITE);
        refresh.setForeground(Color.WHITE);

        JPanel buttons = new JPanel();
        buttons.setBackground(Color.WHITE);

        buttons.add(add);
        buttons.add(delete);
        buttons.add(refresh);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        formPanel.add(buttons, gbc);

        //---------------------------------------------------
        // Table
        //---------------------------------------------------

        model =
                new DefaultTableModel(
                        new Object[]{
                                "ID",
                                "Patient ID",
                                "Doctor ID",
                                "Test Name",
                                "Result",
                                "Test Date",
                                "Status"
                        },
                        0);

        table = new JTable(model);
        table.setRowHeight(28);

        loadTests();

        //---------------------------------------------------
        // Events
        //---------------------------------------------------

        add.addActionListener(e -> {

            try {

                LabTest test =
                        new LabTest();

                test.setPatientId(
                        Integer.parseInt(
                                txtPatientId.getText()));

                test.setDoctorId(
                        Integer.parseInt(
                                txtDoctorId.getText()));

                test.setTestName(
                        txtTestName.getText());

                test.setResult(
                        txtResult.getText());

                test.setTestDate(
                        LocalDate.parse(
                                txtDate.getText()));

                test.setStatus(
                        cmbStatus.getSelectedItem()
                                .toString());

                if (service.createLabTest(test)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Lab Test Added Successfully.");

                    loadTests();

                    txtPatientId.setText("");
                    txtDoctorId.setText("");
                    txtTestName.setText("");
                    txtResult.setText("");
                    txtDate.setText("");
                    cmbStatus.setSelectedIndex(0);
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage());
            }
        });

        delete.addActionListener(e -> {

            int row = table.getSelectedRow();

            if (row < 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Select a test first.");
                return;
            }

            int id =
                    Integer.parseInt(
                            model.getValueAt(
                                    row,
                                    0)
                                    .toString());

            if (service.deleteLabTest(id)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Lab Test Deleted.");

                loadTests();
            }
        });

        refresh.addActionListener(e -> loadTests());

        //---------------------------------------------------
        // Layout
        //---------------------------------------------------

        JPanel center =
                new JPanel(
                        new BorderLayout(15, 15));

        center.setBackground(
                Theme.BACKGROUND);

        center.add(
                formPanel,
                BorderLayout.NORTH);

        center.add(
                new JScrollPane(table),
                BorderLayout.CENTER);

        add(title, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
    }

    //---------------------------------------------------
    // Load Tests
    //---------------------------------------------------

    private void loadTests() {

        model.setRowCount(0);

        List<LabTest> tests =
                service.getAllLabTests();

        for (LabTest t : tests) {

            model.addRow(
                    new Object[]{
                            t.getTestId(),
                            t.getPatientId(),
                            t.getDoctorId(),
                            t.getTestName(),
                            t.getResult(),
                            t.getTestDate(),
                            t.getStatus()
                    });
        }
    }

    //---------------------------------------------------
    // Helper Method
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
        panel.add(new JLabel(label1), gbc);

        gbc.gridx = 1;
        panel.add(field1, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel(label2), gbc);

        gbc.gridx = 3;
        panel.add(field2, gbc);
    }
}