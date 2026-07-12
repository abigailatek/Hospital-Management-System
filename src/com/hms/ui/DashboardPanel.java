package com.hms.ui;

import com.hms.services.DashboardService;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    private DashboardService service;

    public DashboardPanel() {

        service = new DashboardService();

        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 250));

        //-------------------------
        // Header
        //-------------------------

        JLabel title = new JLabel("Dashboard");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20));

        add(title, BorderLayout.NORTH);

        //-------------------------
        // Statistics
        //-------------------------

        JPanel cards =
                new JPanel(
                        new GridLayout(
                                2,
                                4,
                                20,
                                20));

        cards.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20));

        cards.setBackground(
                new Color(
                        245,
                        247,
                        250));

        cards.add(createCard(
                "Patients",
                String.valueOf(
                        service.getPatientCount())));

        cards.add(createCard(
                "Doctors",
                String.valueOf(
                        service.getDoctorCount())));

        cards.add(createCard(
                "Appointments",
                String.valueOf(
                        service.getAppointmentCount())));

        cards.add(createCard(
                "Bills",
                String.valueOf(
                        service.getBillCount())));

        cards.add(createCard(
                "Lab Tests",
                String.valueOf(
                        service.getLabTestCount())));

        cards.add(createCard(
                "Staff",
                String.valueOf(
                        service.getStaffCount())));

        cards.add(createCard(
                "Inventory",
                String.valueOf(
                        service.getInventoryCount())));

        cards.add(createCard(
                "Payments",
                String.valueOf(
                        service.getPaymentCount())));

        add(cards, BorderLayout.CENTER);
    }

    private JPanel createCard(
            String title,
            String value) {

        JPanel panel =
                new JPanel(
                        new BorderLayout());

        panel.setBackground(Color.WHITE);
           
        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        220,
                                        220)),
        
                        BorderFactory.createEmptyBorder(
                                20,
                                20,
                                20,
                                20)));

        JLabel lblTitle =
                new JLabel(title);

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        18));

        JLabel lblValue =
                new JLabel(
                        value,
                        SwingConstants.CENTER);

        lblValue.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        36));

        lblValue.setForeground(
                new Color(
                        16,
                        120,
                        110));

        panel.add(
                lblTitle,
                BorderLayout.NORTH);

        panel.add(
                lblValue,
                BorderLayout.CENTER);

        return panel;
    }
}