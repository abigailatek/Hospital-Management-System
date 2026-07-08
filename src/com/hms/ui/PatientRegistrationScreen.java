package com.hms.ui;

import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PatientRegistrationScreen extends JPanel {

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField dobField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextArea addressArea;

    private JRadioButton maleBtn;
    private JRadioButton femaleBtn;
    private JRadioButton otherBtn;
    private ButtonGroup genderGroup;

    private DefaultTableModel tableModel;

    public PatientRegistrationScreen() {
        setLayout(new BorderLayout(15, 15));
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("PATIENT REGISTRATION");
        title.setFont(Theme.TITLE);
        title.setForeground(Theme.PRIMARY_GREEN);

        JPanel formPanel = createFormPanel();
        JTable patientTable = createPatientTable();

        JPanel center = new JPanel(new BorderLayout(15, 15));
        center.setBackground(Theme.BACKGROUND);
        center.add(formPanel, BorderLayout.NORTH);
        center.add(new JScrollPane(patientTable), BorderLayout.CENTER);

        add(title, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                new EmptyBorder(20, 25, 20, 25)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        firstNameField = field();
        lastNameField = field();
        dobField = field();
        phoneField = field();
        emailField = field();

        maleBtn = new JRadioButton("Male");
        femaleBtn = new JRadioButton("Female");
        otherBtn = new JRadioButton("Other");

        maleBtn.setBackground(Color.WHITE);
        femaleBtn.setBackground(Color.WHITE);
        otherBtn.setBackground(Color.WHITE);

        genderGroup = new ButtonGroup();
        genderGroup.add(maleBtn);
        genderGroup.add(femaleBtn);
        genderGroup.add(otherBtn);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        genderPanel.setBackground(Color.WHITE);
        genderPanel.add(maleBtn);
        genderPanel.add(femaleBtn);
        genderPanel.add(otherBtn);

        addressArea = new JTextArea(3, 40);
        addressArea.setFont(Theme.NORMAL);
        addressArea.setLineWrap(true);
        addressArea.setWrapStyleWord(true);

        addRow(formPanel, gbc, 0, "First Name:", firstNameField, "Last Name:", lastNameField);
        addRow(formPanel, gbc, 1, "DOB:", dobField, "Gender:", genderPanel);
        addRow(formPanel, gbc, 2, "Phone:", phoneField, "Email:", emailField);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        formPanel.add(label("Address:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        formPanel.add(new JScrollPane(addressArea), gbc);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        buttons.setBackground(Color.WHITE);

        JButton saveBtn = button("Save");
        JButton clearBtn = button("Clear");
        JButton cancelBtn = button("Cancel");

        saveBtn.addActionListener(e -> savePatient());
        clearBtn.addActionListener(e -> clearForm());
        cancelBtn.addActionListener(e -> clearForm());

        buttons.add(saveBtn);
        buttons.add(clearBtn);
        buttons.add(cancelBtn);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(buttons, gbc);

        return formPanel;
    }

    private JTable createPatientTable() {
        tableModel = new DefaultTableModel(
                new String[]{
                        "First Name",
                        "Last Name",
                        "DOB",
                        "Gender",
                        "Phone",
                        "Email",
                        "Address"
                },
                0
        );

        JTable table = new JTable(tableModel);
        table.setRowHeight(28);
        table.setFont(Theme.NORMAL);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        return table;
    }

    private void savePatient() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String dob = dobField.getText().trim();
        String gender = getSelectedGender();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();
        String address = addressArea.getText().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter First Name, Last Name, and Phone.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        tableModel.addRow(new Object[]{
                firstName,
                lastName,
                dob,
                gender,
                phone,
                email,
                address
        });

        JOptionPane.showMessageDialog(
                this,
                "Patient details saved successfully.",
                "Saved",
                JOptionPane.INFORMATION_MESSAGE
        );

        clearForm();
    }

    private String getSelectedGender() {
        if (maleBtn.isSelected()) return "Male";
        if (femaleBtn.isSelected()) return "Female";
        if (otherBtn.isSelected()) return "Other";
        return "";
    }

    private void clearForm() {
        firstNameField.setText("");
        lastNameField.setText("");
        dobField.setText("");
        phoneField.setText("");
        emailField.setText("");
        addressArea.setText("");
        genderGroup.clearSelection();
    }

    private void addRow(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String label1,
            JComponent field1,
            String label2,
            JComponent field2
    ) {
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

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
