package com.hms.ui;

import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DoctorProfileScreen extends JPanel {

    public DoctorProfileScreen() {
        setLayout(new BorderLayout());
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("DOCTOR PROFILE");
        title.setFont(Theme.TITLE);
        title.setForeground(Theme.PRIMARY_GREEN);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                new EmptyBorder(25, 30, 25, 30)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addRow(form, gbc, 0, "Doctor ID:", new JTextField(20));
        addRow(form, gbc, 1, "Full Name:", new JTextField(20));
        addRow(form, gbc, 2, "Specialization:", new JTextField(20));
        addRow(form, gbc, 3, "Phone:", new JTextField(20));
        addRow(form, gbc, 4, "Email:", new JTextField(20));

        JButton update = new JButton("Update Profile");
        update.setBackground(Theme.PRIMARY_GREEN);
        update.setForeground(Color.WHITE);
        update.setFocusPainted(false);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        form.add(update, gbc);

        add(title, BorderLayout.NORTH);
        add(form, BorderLayout.CENTER);
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        panel.add(field, gbc);
    }
}