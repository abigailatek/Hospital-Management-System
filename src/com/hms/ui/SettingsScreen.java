package com.hms.ui;

import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SettingsScreen extends JPanel {

    public SettingsScreen() {

        setLayout(new BorderLayout());
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(20,25,20,25));

        JLabel title = new JLabel("SYSTEM SETTINGS");
        title.setFont(Theme.TITLE);
        title.setForeground(Theme.PRIMARY_GREEN);

        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220,220,220)),
                new EmptyBorder(25,25,25,25)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12,12,12,12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField hospitalName = new JTextField(20);
        JTextField hospitalEmail = new JTextField(20);
        JTextField hospitalPhone = new JTextField(20);
        JTextField address = new JTextField(20);

        JComboBox<String> theme = new JComboBox<>(
                new String[]{
                        "Green Theme",
                        "Blue Theme",
                        "Dark Theme"
                });

        gbc.gridx=0;
        gbc.gridy=0;
        card.add(new JLabel("Hospital Name"),gbc);

        gbc.gridx=1;
        card.add(hospitalName,gbc);

        gbc.gridx=0;
        gbc.gridy=1;
        card.add(new JLabel("Email"),gbc);

        gbc.gridx=1;
        card.add(hospitalEmail,gbc);

        gbc.gridx=0;
        gbc.gridy=2;
        card.add(new JLabel("Phone"),gbc);

        gbc.gridx=1;
        card.add(hospitalPhone,gbc);

        gbc.gridx=0;
        gbc.gridy=3;
        card.add(new JLabel("Address"),gbc);

        gbc.gridx=1;
        card.add(address,gbc);

        gbc.gridx=0;
        gbc.gridy=4;
        card.add(new JLabel("Theme"),gbc);

        gbc.gridx=1;
        card.add(theme,gbc);

        JPanel buttons = new JPanel();

        JButton save = new JButton("Save");
        JButton reset = new JButton("Reset");

        save.setBackground(Theme.PRIMARY_GREEN);
        save.setForeground(Color.WHITE);

        reset.setBackground(Color.GRAY);
        reset.setForeground(Color.WHITE);

        buttons.add(save);
        buttons.add(reset);

        gbc.gridx=0;
        gbc.gridy=5;
        gbc.gridwidth=2;

        card.add(buttons,gbc);

        add(title,BorderLayout.NORTH);
        add(card,BorderLayout.CENTER);

    }

}

