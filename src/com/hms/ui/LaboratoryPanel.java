package com.hms.ui;

import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LaboratoryPanel extends JPanel {

    public LaboratoryPanel() {

        setLayout(new BorderLayout(15,15));
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(20,25,20,25));

        JLabel title = new JLabel("LABORATORY");
        title.setFont(Theme.TITLE);
        title.setForeground(Theme.PRIMARY_GREEN);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(215,215,215)),
                        new EmptyBorder(20,25,20,25)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtPatientId = new JTextField(18);
        JTextField txtTestName = new JTextField(18);
        JTextField txtResult = new JTextField(18);
        JTextField txtDate = new JTextField(18);

        addRow(formPanel, gbc, 0,
                "Patient ID:", txtPatientId,
                "Test Name:", txtTestName);

        addRow(formPanel, gbc, 1,
                "Result:", txtResult,
                "Test Date:", txtDate);

        JButton add =
                new JButton("Add Test");

        add.setBackground(Theme.PRIMARY_GREEN);
        add.setForeground(Color.WHITE);

        JPanel buttons = new JPanel();
        buttons.setBackground(Color.WHITE);
        buttons.add(add);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        formPanel.add(buttons, gbc);

        DefaultTableModel model =
                new DefaultTableModel(
                        new Object[]{
                                "Patient ID",
                                "Test Name",
                                "Result",
                                "Test Date"
                        }, 0);

        JTable table = new JTable(model);
        table.setRowHeight(28);

        add.addActionListener(e -> {

            model.addRow(
                    new Object[]{
                            txtPatientId.getText(),
                            txtTestName.getText(),
                            txtResult.getText(),
                            txtDate.getText()
                    });

            txtPatientId.setText("");
            txtTestName.setText("");
            txtResult.setText("");
            txtDate.setText("");
        });

        JPanel center =
                new JPanel(new BorderLayout(15,15));

        center.setBackground(Theme.BACKGROUND);
        center.add(formPanel, BorderLayout.NORTH);
        center.add(new JScrollPane(table),
                BorderLayout.CENTER);

        add(title, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
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