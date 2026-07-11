package com.hms.ui;

import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ReportsPanel extends JPanel {

    public ReportsPanel() {

        setLayout(new BorderLayout(15, 15));
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Reports");
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(Theme.PRIMARY_GREEN);

        add(title, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(4, 2, 20, 20));
        center.setBackground(Theme.BACKGROUND);

        JButton patientBtn = createButton("Patient Report");
        JButton doctorBtn = createButton("Doctor Report");
        JButton appointmentBtn = createButton("Appointment Report");
        JButton billingBtn = createButton("Billing Report");
        JButton inventoryBtn = createButton("Inventory Report");
        JButton paymentBtn = createButton("Payment Report");
        JButton staffBtn = createButton("Staff Report");
        JButton labBtn = createButton("Laboratory Report");

        center.add(patientBtn);
        center.add(doctorBtn);
        center.add(appointmentBtn);
        center.add(billingBtn);
        center.add(inventoryBtn);
        center.add(paymentBtn);
        center.add(staffBtn);
        center.add(labBtn);

        add(center, BorderLayout.CENTER);

        patientBtn.addActionListener(
                e -> openReport("Patient Report"));

        doctorBtn.addActionListener(
                e -> openReport("Doctor Report"));

        appointmentBtn.addActionListener(
                e -> openReport("Appointment Report"));

        billingBtn.addActionListener(
                e -> openReport("Billing Report"));

        inventoryBtn.addActionListener(
                e -> openReport("Inventory Report"));

        paymentBtn.addActionListener(
                e -> openReport("Payment Report"));

        staffBtn.addActionListener(
                e -> openReport("Staff Report"));

        labBtn.addActionListener(
                e -> openReport("Laboratory Report"));
    }

    private JButton createButton(String text) {

        JButton button = new JButton(text);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18));

        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);

        return button;
    }

    private void openReport(String title) {

        JFrame frame = new JFrame(title);

        frame.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE);

        frame.setSize(1200, 700);
        frame.setLocationRelativeTo(null);

        frame.add(new ReportViewerPanel(title));

        frame.setVisible(true);
    }
}