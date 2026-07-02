package com.hms.ui;

import com.hms.utils.Theme;

import javax.swing.*;
import java.awt.*;

public class HomeScreen extends JPanel {

    public HomeScreen(Runnable openPatients, Runnable openAppointments) {

        setLayout(new BorderLayout());
        setBackground(Theme.WHITE);

        // ================= TOP TITLE BAR =================
        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(Theme.PRIMARY_GREEN);
        top.setPreferredSize(new Dimension(1000, 60));

        JLabel title = new JLabel("  HMS DASHBOARD");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JLabel user = new JLabel("Admin   ");
        user.setForeground(Color.WHITE);

        top.add(title, BorderLayout.WEST);
        top.add(user, BorderLayout.EAST);

        add(top, BorderLayout.NORTH);

        // ================= CENTER CARDS GRID =================
        JPanel grid = new JPanel(new GridLayout(2, 3, 15, 15));
        grid.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        grid.setBackground(Theme.WHITE);

        grid.add(card("Patients", "124", "👤"));
        grid.add(card("Doctors", "18", "🩺"));
        grid.add(card("Appointments", "32", "📅"));
        grid.add(card("Diagnosis", "15", "🧾"));
        grid.add(card("Prescription", "40", "💊"));
        grid.add(card("Billing", "9", "💰"));

        add(grid, BorderLayout.CENTER);

        // ================= QUICK ACTIONS =================
        JPanel bottom = new JPanel(new FlowLayout());
        bottom.setBackground(Theme.WHITE);

        JButton newPatient = new JButton("New Patient");
        JButton newAppointment = new JButton("New Appointment");

        styleButton(newPatient);
        styleButton(newAppointment);

        newPatient.addActionListener(e -> openPatients.run());
        newAppointment.addActionListener(e -> openAppointments.run());

        bottom.add(newPatient);
        bottom.add(newAppointment);

        add(bottom, BorderLayout.SOUTH);
    }

    // ================= CARD UI =================
    private JPanel card(String title, String value, String icon) {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Theme.LIGHT_GREEN);
        panel.setBorder(BorderFactory.createLineBorder(Theme.PRIMARY_GREEN));

        JLabel iconLabel = new JLabel(icon, SwingConstants.CENTER);
        iconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 28));

        JLabel t = new JLabel(title, SwingConstants.CENTER);
        t.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JLabel v = new JLabel(value, SwingConstants.CENTER);
        v.setFont(new Font("Segoe UI", Font.BOLD, 22));
        v.setForeground(Theme.PRIMARY_GREEN);

        panel.add(iconLabel, BorderLayout.NORTH);
        panel.add(t, BorderLayout.CENTER);
        panel.add(v, BorderLayout.SOUTH);

        return panel;
    }

    // ================= BUTTON STYLE =================
    private void styleButton(JButton btn) {
        btn.setBackground(Theme.PRIMARY_GREEN);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
    }
}