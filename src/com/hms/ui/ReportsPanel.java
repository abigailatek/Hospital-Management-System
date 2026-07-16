package com.hms.ui;
import com.hms.services.ReportService;
import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ReportsPanel extends JPanel {

    public ReportsPanel() {

        setLayout(new BorderLayout(20, 20));
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        //--------------------------------------------------
        // Top Section
        //--------------------------------------------------

        JPanel topPanel =
                new JPanel(
                        new BorderLayout(15, 15));

        topPanel.setBackground(
                Theme.BACKGROUND);

        JLabel title =
                new JLabel("Reports");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30));

        title.setForeground(
                Theme.PRIMARY_GREEN);

        topPanel.add(
                title,
                BorderLayout.NORTH);

        //--------------------------------------------------
        // Statistics Cards
        //--------------------------------------------------

        ReportService service =
                new ReportService();

        JPanel statsPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                5,
                                15,
                                15));

        statsPanel.setBackground(
                Theme.BACKGROUND);

        statsPanel.add(
                createStatCard(
                        "Patients",
                        String.valueOf(
                                service.getPatientCount())));

        statsPanel.add(
                createStatCard(
                        "Doctors",
                        String.valueOf(
                                service.getDoctorCount())));

        statsPanel.add(
                createStatCard(
                        "Appointments",
                        String.valueOf(
                                service.getAppointmentCount())));

        statsPanel.add(
                createStatCard(
                        "Lab Tests",
                        String.valueOf(
                                service.getLabTestCount())));

        statsPanel.add(
                createStatCard(
                        "Revenue",
                        "UGX "
                                + service.getTotalRevenue()));

        topPanel.add(
                statsPanel,
                BorderLayout.CENTER);

        add(
                topPanel,
                BorderLayout.NORTH);

        //--------------------------------------------------
        // Report Buttons
        //--------------------------------------------------

        JPanel center =
                new JPanel(
                        new GridLayout(4, 2, 20, 20));
        center.setBackground(
                Theme.BACKGROUND);

        JButton patientBtn =
                createButton(
                        "Patient Report");

        JButton doctorBtn =
                createButton(
                        "Doctor Report");

        JButton appointmentBtn =
                createButton(
                        "Appointment Report");

        JButton billingBtn =
                createButton(
                        "Billing Report");

        JButton inventoryBtn =
                createButton(
                        "Inventory Report");

        JButton paymentBtn =
                createButton(
                        "Payment Report");

        JButton staffBtn =
                createButton(
                        "Staff Report");

        JButton labBtn =
                createButton(
                        "Laboratory Report");


        center.add(patientBtn);
        center.add(doctorBtn);
        center.add(appointmentBtn);
        center.add(billingBtn);
        center.add(inventoryBtn);
        center.add(paymentBtn);
        center.add(staffBtn);
        center.add(labBtn);
        add(
                center,
                BorderLayout.CENTER);

        //--------------------------------------------------
        // Events
        //--------------------------------------------------

        patientBtn.addActionListener(
                e -> openReport(
                        "Patient Report"));

        doctorBtn.addActionListener(
                e -> openReport(
                        "Doctor Report"));

        appointmentBtn.addActionListener(
                e -> openReport(
                        "Appointment Report"));

        billingBtn.addActionListener(
                e -> openReport(
                        "Billing Report"));

        inventoryBtn.addActionListener(
                e -> openReport(
                        "Inventory Report"));

        paymentBtn.addActionListener(
                e -> openReport(
                        "Payment Report"));

        staffBtn.addActionListener(
                e -> openReport(
                        "Staff Report"));

        labBtn.addActionListener(
                e -> openReport(
                        "Laboratory Report"));
    }

    //--------------------------------------------------
    // Create Report Buttons
    //--------------------------------------------------

    private JButton createButton(
            String text) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18));

        button.setBackground(
                Color.WHITE);

        button.setFocusPainted(
                false);

        return button;
    }

    //--------------------------------------------------
    // Statistics Cards
    //--------------------------------------------------

    private JPanel createStatCard(
            String title,
            String value) {

        JPanel card =
                new JPanel(
                        new BorderLayout());

        card.setBackground(
                Color.WHITE);

        card.setBorder(
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
                        Font.BOLD,
                        16));

        JLabel lblValue =
                new JLabel(
                        value,
                        SwingConstants.CENTER);

        lblValue.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24));

        lblValue.setForeground(
                Theme.PRIMARY_GREEN);

        card.add(
                lblTitle,
                BorderLayout.NORTH);

        card.add(
                lblValue,
                BorderLayout.CENTER);

        return card;
    }

    //--------------------------------------------------
    // Open Report Viewer
    //--------------------------------------------------

    private void openReport(
            String title) {

        JFrame frame =
                new JFrame(title);

        frame.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE);

        frame.setSize(
                1200,
                700);

        frame.setLocationRelativeTo(
                null);

        frame.add(
                new ReportViewerPanel(
                        title));

        frame.setVisible(
                true);
    }
}