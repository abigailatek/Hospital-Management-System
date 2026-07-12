package com.hms.ui;

import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SettingsScreen extends JPanel {

    public SettingsScreen() {

        setLayout(new BorderLayout(15, 15));
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(20, 25, 20, 25));

        //------------------------------------
        // Title
        //------------------------------------

        JLabel title =
                new JLabel("SYSTEM SETTINGS");

        title.setFont(Theme.TITLE);
        title.setForeground(Theme.PRIMARY_GREEN);

        //------------------------------------
        // Main Form Panel
        //------------------------------------

        JPanel formPanel =
                new JPanel(
                        new GridBagLayout());

        formPanel.setBackground(Color.WHITE);

        formPanel.setBorder(
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
                new Insets(
                        10,
                        10,
                        10,
                        10);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        //------------------------------------
        // Fields
        //------------------------------------

        JTextField txtUsername =
                field();

        txtUsername.setText(
                "Administrator");

        JTextField txtEmail =
                field();

        txtEmail.setText(
                "admin@lifecare.com");

        JPasswordField txtPassword =
                new JPasswordField(18);

        JPasswordField txtConfirm =
                new JPasswordField(18);

        JCheckBox chkNotifications =
                new JCheckBox(
                        "Enable Notifications");

        JCheckBox chkRemember =
                new JCheckBox(
                        "Remember Username");

        JCheckBox chkDarkMode =
                new JCheckBox(
                        "Dark Mode");

        chkNotifications.setBackground(
                Color.WHITE);

        chkRemember.setBackground(
                Color.WHITE);

        chkDarkMode.setBackground(
                Color.WHITE);

        //------------------------------------
        // Rows
        //------------------------------------

        addRow(
                formPanel,
                gbc,
                0,
                "Username:",
                txtUsername,
                "Email:",
                txtEmail);

        addRow(
                formPanel,
                gbc,
                1,
                "New Password:",
                txtPassword,
                "Confirm Password:",
                txtConfirm);

        //------------------------------------
        // Preferences
        //------------------------------------

        JPanel preferencePanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT));

        preferencePanel.setBackground(
                Color.WHITE);

        preferencePanel.add(
                chkNotifications);

        preferencePanel.add(
                chkRemember);

        preferencePanel.add(
                chkDarkMode);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;

        formPanel.add(
                preferencePanel,
                gbc);

        //------------------------------------
        // Buttons
        //------------------------------------

        JPanel buttons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                5));

        buttons.setBackground(
                Color.WHITE);

        JButton btnSave =
                button(
                        "Save");

        JButton btnReset =
                button(
                        "Reset");

        JButton btnBackup =
                button(
                        "Backup");

        buttons.add(btnSave);
        buttons.add(btnReset);
        buttons.add(btnBackup);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;

        formPanel.add(
                buttons,
                gbc);

        //------------------------------------
        // System Information
        //------------------------------------

        JPanel infoPanel =
                new JPanel(
                        new GridLayout(
                                4,
                                1,
                                5,
                                5));

        infoPanel.setBackground(
                Color.WHITE);

        infoPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(215,215,215)),
                        new EmptyBorder(
                                20,
                                25,
                                20,
                                25)));

        infoPanel.add(
                new JLabel(
                        "Hospital Management System"));

        infoPanel.add(
                new JLabel(
                        "Version : 1.0"));

        JLabel status =
                new JLabel(
                        "Database Status : Connected");

        status.setForeground(
                new Color(
                        0,
                        140,
                        0));

        infoPanel.add(status);

        infoPanel.add(
                new JLabel(
                        "Developed by The Byte Group"));

        //------------------------------------
        // Events
        //------------------------------------

        btnSave.addActionListener(e -> {

            String p1 =
                    new String(
                            txtPassword.getPassword());

            String p2 =
                    new String(
                            txtConfirm.getPassword());

            if (!p1.isBlank()
                    && !p1.equals(p2)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Passwords do not match.");

                return;
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Settings Saved.");
        });

        btnReset.addActionListener(e -> {

            txtUsername.setText(
                    "Administrator");

            txtEmail.setText(
                    "admin@lifecare.com");

            txtPassword.setText("");
            txtConfirm.setText("");

            chkNotifications.setSelected(false);
            chkRemember.setSelected(false);
            chkDarkMode.setSelected(false);
        });

        btnBackup.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Database Backup Complete."));

        //------------------------------------
        // Layout
        //------------------------------------

        JPanel center =
                new JPanel(
                        new BorderLayout(
                                15,
                                15));

        center.setBackground(
                Theme.BACKGROUND);

        center.add(
                formPanel,
                BorderLayout.NORTH);

        center.add(
                infoPanel,
                BorderLayout.CENTER);

        add(
                title,
                BorderLayout.NORTH);

        add(
                center,
                BorderLayout.CENTER);
    }

    //------------------------------------
    // Helper Methods
    //------------------------------------

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
        panel.add(label(label1), gbc);

        gbc.gridx = 1;
        panel.add(field1, gbc);

        gbc.gridx = 2;
        panel.add(label(label2), gbc);

        gbc.gridx = 3;
        panel.add(field2, gbc);
    }

    private JTextField field() {

        JTextField field =
                new JTextField(18);

        field.setFont(
                Theme.NORMAL);

        return field;
    }

    private JLabel label(
            String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                Theme.NORMAL);

        label.setForeground(
                Theme.TEXT);

        return label;
    }

    private JButton button(
            String text) {

        JButton btn =
                new JButton(text);

        btn.setFont(
                Theme.NORMAL);

        btn.setBackground(
                Theme.PRIMARY_GREEN);

        btn.setForeground(
                Color.WHITE);

        btn.setFocusPainted(false);

        return btn;
    }
}