package com.hms.ui;

import com.hms.services.DashboardService;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

   public DashboardPanel() {
        DashboardService

    service = new DashboardService();

    setLayout(new BorderLayout(20,20));
    setBackground(new Color(245,247,250));

    //----------------------------------
    // Header
    //----------------------------------

    JPanel header =
            new JPanel(new BorderLayout());

    header.setOpaque(false);

    JPanel left =
            new JPanel();

    left.setOpaque(false);
    left.setLayout(
            new BoxLayout(
                    left,
                    BoxLayout.Y_AXIS));

    JLabel title =
            new JLabel(
                    "🏥 LifeCare Hospital Dashboard");

    title.setFont(
            new Font(
                    "Segoe UI Emoji",
                    Font.BOLD,
                    32));

    title.setForeground(
            new Color(16,120,110));

    JLabel greeting =
            new JLabel(
                    "👋 Welcome back, Administrator");

    greeting.setFont(
            new Font(
                    "Segoe UI Emoji",
                    Font.PLAIN,
                    18));

    greeting.setForeground(Color.GRAY);

    left.add(title);
    left.add(Box.createVerticalStrut(5));
    left.add(greeting);

    JLabel date =
            new JLabel(
                    java.time.LocalDate.now().toString());

    date.setFont(
            new Font(
                    "Segoe UI",
                    Font.PLAIN,
                    16));

    date.setForeground(Color.GRAY);

    header.add(left, BorderLayout.WEST);
    header.add(date, BorderLayout.EAST);

    add(header, BorderLayout.NORTH);

    //----------------------------------
    // Cards
    //----------------------------------

    JPanel cards =
            new JPanel(
                    new GridLayout(
                            2,
                            4,
                            25,
                            25));

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
            "👤 Patients",
            String.valueOf(
                    service.getPatientCount())));

    cards.add(createCard(
            "🩺 Doctors",
            String.valueOf(
                    service.getDoctorCount())));

    cards.add(createCard(
            "📅 Appointments",
            String.valueOf(
                    service.getAppointmentCount())));

    cards.add(createCard(
            "💰 Bills",
            String.valueOf(
                    service.getBillCount())));

    cards.add(createCard(
            "🧪 Lab Tests",
            String.valueOf(
                    service.getLabTestCount())));

    cards.add(createCard(
            "👨‍⚕️ Staff",
            String.valueOf(
                    service.getStaffCount())));

    cards.add(createCard(
            "📦 Inventory",
            String.valueOf(
                    service.getInventoryCount())));

    cards.add(createCard(
            "💳 Payments",
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
                                    225,
                                    225,
                                    225)),
                    BorderFactory.createEmptyBorder(
                            20,
                            20,
                            20,
                            20)));

    panel.setPreferredSize(
            new Dimension(
                    250,
                    180));

    JLabel lblTitle =
            new JLabel(title);

    lblTitle.setFont(
            new Font(
                    "Segoe UI Emoji",
                    Font.BOLD,
                    18));

    lblTitle.setForeground(
            new Color(
                    16,
                    120,
                    110));

    JLabel lblValue =
            new JLabel(
                    value,
                    SwingConstants.CENTER);

    lblValue.setFont(
            new Font(
                    "Segoe UI",
                    Font.BOLD,
                    48));

    lblValue.setForeground(
            new Color(
                    16,
                    120,
                    110));

    JLabel subtitle =
            new JLabel(
                    "Total Records",
                    SwingConstants.CENTER);

    subtitle.setForeground(
            Color.GRAY);

    subtitle.setFont(
            new Font(
                    "Segoe UI",
                    Font.PLAIN,
                    14));

    JPanel center =
            new JPanel();

    center.setOpaque(false);

    center.setLayout(
            new BoxLayout(
                    center,
                    BoxLayout.Y_AXIS));

    lblValue.setAlignmentX(
            Component.CENTER_ALIGNMENT);

    subtitle.setAlignmentX(
            Component.CENTER_ALIGNMENT);

    center.add(Box.createVerticalGlue());
    center.add(lblValue);
    center.add(Box.createVerticalStrut(5));
    center.add(subtitle);
    center.add(Box.createVerticalGlue());

    panel.add(
            lblTitle,
            BorderLayout.NORTH);

    panel.add(
            center,
            BorderLayout.CENTER);

    return panel;
}
}