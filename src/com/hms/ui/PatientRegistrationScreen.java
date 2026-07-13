package com.hms.ui;

import com.hms.utils.Theme;
import com.hms.models.Patient;
import com.hms.services.PatientService;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PatientRegistrationScreen extends JPanel {

    public PatientRegistrationScreen() {
        setLayout(new BorderLayout());
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("PATIENT REGISTRATION", SwingConstants.CENTER);
        title.setFont(Theme.TITLE);
        title.setForeground(Theme.PRIMARY_GREEN);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(215, 215, 215)),
                new EmptyBorder(25, 30, 25, 30)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField firstName = new JTextField(18);
        JTextField lastName = new JTextField(18);
        JTextField dob = new JTextField(18);
        JTextField phone = new JTextField(18);
        JTextField email = new JTextField(18);
        JTextField emergencyContact =new JTextField(18);
        JRadioButton male = new JRadioButton("M");
        JRadioButton female = new JRadioButton("F");
        JRadioButton other = new JRadioButton("O");

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        genderGroup.add(other);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setBackground(Color.WHITE);
        genderPanel.add(male);
        genderPanel.add(female);
        genderPanel.add(other);

        JTextArea address = new JTextArea(4, 40);
        address.setLineWrap(true);
        address.setWrapStyleWord(true);

        addRow(form, gbc, 0, "First Name:", firstName, "Last Name:", lastName);
        addRow(form, gbc, 1, "DOB:", dob, "Gender:", genderPanel);
        addRow(form, gbc, 2, "Phone:", phone,"Email:", email);
        addRow(form, gbc, 3,"Emergency Contact:",emergencyContact,"", new JLabel());

        gbc.gridx = 0;
        gbc.gridy = 4;
        form.add(label("Address:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        form.add(new JScrollPane(address), gbc);
        gbc.gridwidth = 1;

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        buttons.setBackground(Color.WHITE);

        JButton save = button("Save");
        JButton clear = button("Clear");
        JButton cancel = button("Cancel");

        buttons.add(save);
        buttons.add(clear);
        buttons.add(cancel);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        form.add(buttons, gbc);

        add(title, BorderLayout.NORTH);
        save.addActionListener(e -> {

    try {

        Patient patient = new Patient();

        patient.setFirstName(firstName.getText());
        patient.setLastName(lastName.getText());
        patient.setDateOfBirth(
                LocalDate.parse(
                        dob.getText()));

        String gender = "";

        if (male.isSelected()) {
            gender = "Male";
        }
        else if (female.isSelected()) {
            gender = "Female";
        }
        else {
            gender = "Other";
        }

        patient.setGender(gender);
        patient.setPhone(phone.getText());
        patient.setEmail(email.getText());
        patient.setAddress(address.getText());

        PatientService service =
                new PatientService();

        if (service.addPatient(patient)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Patient registered successfully.");

            firstName.setText("");
            lastName.setText("");
            dob.setText("");
            phone.setText("");
            email.setText("");
            address.setText("");
            genderGroup.clearSelection();
        }

    } catch (Exception ex) {

        JOptionPane.showMessageDialog(
                this,
                ex.getMessage());
    }
});

cancel.addActionListener(e -> {
    genderGroup.clearSelection();
    firstName.setText("");
    lastName.setText("");
    dob.setText("");
    phone.setText("");
    email.setText("");
    address.setText("");
});
        add(form, BorderLayout.CENTER);

        clear.addActionListener(e -> {
            firstName.setText("");
            lastName.setText("");
            dob.setText("");
            phone.setText("");
            email.setText("");
            address.setText("");
            genderGroup.clearSelection();
        });
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
