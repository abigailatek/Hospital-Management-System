package com.hms.ui;

import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class SettingsPanel extends JPanel {

    private JCheckBox chkNotifications;
    private JCheckBox chkRemember;
    private JCheckBox chkDarkMode;

    private JTextField txtUsername;
    private JTextField txtEmail;

    private JPasswordField txtPassword;
    private JPasswordField txtConfirm;

    public SettingsPanel() {

        setLayout(new BorderLayout(20, 20));
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(25, 25, 25, 25));

        //-----------------------------------
        // HEADER
        //-----------------------------------

        JLabel title =
                new JLabel("⚙ System Settings");

        title.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.BOLD,
                        30));

        title.setForeground(
                Theme.PRIMARY_GREEN);

        add(title, BorderLayout.NORTH);

        //-----------------------------------
        // MAIN CONTAINER
        //-----------------------------------

        JPanel container =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                20,
                                20));

        container.setBackground(
                Theme.BACKGROUND);

        container.add(createProfileCard());
        container.add(createPreferencesCard());
        container.add(createSecurityCard());
        container.add(createDatabaseCard());

        add(container, BorderLayout.CENTER);

        //-----------------------------------
        // FOOTER
        //-----------------------------------

        JLabel footer =
                new JLabel(
                        "LifeCare Hospital Management System v1.0 | Developed by The Byte Group",
                        SwingConstants.CENTER);

        footer.setForeground(Color.GRAY);
        footer.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13));

        add(footer, BorderLayout.SOUTH);
    }

    //===================================================
    // PROFILE CARD
    //===================================================

    private Component createDatabaseCard() {
        
        JPanel card =
                createCard("💾 Database Settings");

        JTextField txtHost =
                new JTextField("localhost");

        JTextField txtPort =
                new JTextField("3306");

        JTextField txtDB =
                new JTextField("lifecare_db");

        card.add(label("Host"));
        card.add(txtHost);

        card.add(Box.createVerticalStrut(15));

        card.add(label("Port"));
        card.add(txtPort);

        card.add(Box.createVerticalStrut(15));

        card.add(label("Database Name"));
        card.add(txtDB);

        card.add(Box.createVerticalStrut(20));

        JButton save =
                greenButton("Save Database Settings");

        save.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Database Settings Updated Successfully."));

        card.add(save);

        return card;
    }

    private JPanel createProfileCard() {

        JPanel card =
                createCard("👤 Administrator");

        txtUsername =
                new JTextField("Administrator");

        txtEmail =
                new JTextField("admin@lifecare.com");

        card.add(label("Username"));
        card.add(txtUsername);

        card.add(Box.createVerticalStrut(15));

        card.add(label("Email"));
        card.add(txtEmail);

        card.add(Box.createVerticalStrut(20));

        JButton save =
                greenButton("Save Profile");

        save.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Profile Updated Successfully."));

        card.add(save);

        return card;
    }

    //===================================================
    // PREFERENCES CARD
    //===================================================

    private JPanel createCard(String title) {

    JPanel panel = new JPanel();
    panel.setLayout(
            new BoxLayout(
                    panel,
                    BoxLayout.Y_AXIS));

    panel.setBackground(Color.WHITE);

    panel.setBorder(
            BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(
                            new Color(220,220,220)),
                    BorderFactory.createEmptyBorder(20,20, 20, 20)));

    JLabel lblTitle =
            new JLabel(title);

    lblTitle.setFont(
            new Font(
                    "Segoe UI Emoji",
                    Font.BOLD,
                    18));

    lblTitle.setForeground(
            Theme.PRIMARY_GREEN);

    panel.add(lblTitle);
    panel.add(Box.createVerticalStrut(20));

    return panel;
}
    private JPanel createPreferencesCard() {

        JPanel card =
                createCard("⚙ System Preferences");

        chkNotifications =
                new JCheckBox(
                        "Enable Notifications");

        chkRemember =
                new JCheckBox(
                        "Remember Username");

        chkDarkMode =
                new JCheckBox(
                        "Dark Mode");

        styleCheckBox(chkNotifications);
        styleCheckBox(chkRemember);
        styleCheckBox(chkDarkMode);

        card.add(chkNotifications);
        card.add(Box.createVerticalStrut(10));

        card.add(chkRemember);
        card.add(Box.createVerticalStrut(10));

        card.add(chkDarkMode);

        chkDarkMode.addActionListener(e -> {

            if (chkDarkMode.isSelected()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Dark Mode Enabled");

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Dark Mode Disabled");
            }
        });

        return card;
    }

    //===================================================
    // SECURITY CARD
    //===================================================

    private void styleCheckBox(
        JCheckBox box) {

    box.setBackground(
            Color.WHITE);

    box.setFont(
            new Font(
                    "Segoe UI Emoji",
                    Font.PLAIN,
                    14));
}

    private JPanel createSecurityCard() {

        JPanel card =
                createCard("🔒 Security");

        txtPassword =
                new JPasswordField();

        txtConfirm =
                new JPasswordField();

        card.add(label("New Password"));
        card.add(txtPassword);

        card.add(Box.createVerticalStrut(15));

        card.add(label("Confirm Password"));
        card.add(txtConfirm);

        card.add(Box.createVerticalStrut(20));

        JButton change =
                greenButton("Change Password");

        change.addActionListener(e -> {

            String p1 =
                    new String(
                            txtPassword.getPassword());

            String p2 =
                    new String(
                            txtConfirm.getPassword());

            if (p1.isBlank()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Password cannot be empty.");

                return;
            }

            if (!p1.equals(p2)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Passwords do not match.");

                return;
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Password Updated Successfully.");

            txtPassword.setText("");
            txtConfirm.setText("");
        });

        card.add(change);

        return card;
    }

    private Component label(String string) {
       
        JLabel label =
                new JLabel(string);

        label.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        14));

        return label;
    }
    private JButton greenButton(String string) {

        JButton button =
                new JButton(string);

        button.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        16));

        button.setBackground(
                Theme.PRIMARY_GREEN);

        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);

        return button;
    }
}
   