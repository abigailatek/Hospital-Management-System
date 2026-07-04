package com.hms.ui;

import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DoctorHomeScreen extends JPanel {

    public DoctorHomeScreen() {
        setLayout(new BorderLayout(20, 20));
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("Doctor Dashboard");
        title.setFont(Theme.TITLE);
        title.setForeground(Theme.PRIMARY_GREEN);

        JPanel cards = new JPanel(new GridLayout(2, 2, 20, 20));
        cards.setBackground(Theme.BACKGROUND);

        cards.add(card("My Patients"));
        cards.add(card("Today’s Appointments"));
        cards.add(card("Pending Diagnosis"));
        cards.add(card("Prescriptions"));

        JPanel quick = new JPanel(new GridLayout(4, 1, 8, 8));
        quick.setBackground(Color.WHITE);
        quick.setBorder(BorderFactory.createTitledBorder("Quick Actions"));

        quick.add(new JLabel("➜ Open Patient Record"));
        quick.add(new JLabel("➜ New Diagnosis"));
        quick.add(new JLabel("➜ Write Prescription"));
        quick.add(new JLabel("➜ Request Lab Test"));

        add(title, BorderLayout.NORTH);
        add(cards, BorderLayout.CENTER);
        add(quick, BorderLayout.EAST);
    }

    private JPanel card(String text) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                new EmptyBorder(25, 25, 25, 25)
        ));

        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label.setForeground(Theme.TEXT);

        panel.add(label, BorderLayout.CENTER);
        return panel;
    }
}