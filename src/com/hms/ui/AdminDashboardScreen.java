package com.hms.ui;

import com.hms.services.DashboardService;
import com.hms.ui.components.StatisticCard;
import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminDashboardScreen extends JPanel {

    private JLabel clockLabel;

    public AdminDashboardScreen(
            Runnable openPatients,
            Runnable openDoctors,
            Runnable openAppointments,
            Runnable openDiagnosis,
            Runnable openPrescription,
            Runnable openMedicalStore,
            Runnable openBilling,
            Runnable openReports
    ) {

        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(245, 247, 250));

        DashboardService dashboard =
                new DashboardService();

        //------------------------------------
        // Main Panel
        //------------------------------------

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(20, 20));

        mainPanel.setBackground(
                Theme.BACKGROUND);

        mainPanel.setBorder(
                new EmptyBorder(
                        20,
                        20,
                        20,
                        20));

        mainPanel.add(
                createHeader(),
                BorderLayout.NORTH);

        //------------------------------------
        // Dashboard Cards
        //------------------------------------

        JPanel cards =
                new JPanel(
                        new GridLayout(
                                3,
                                3,
                                25,
                                25));

        cards.setBackground(
                new Color(
                        245,
                        247,
                        250));

        cards.add(new StatisticCard(
                "👤",
                "Patients",
                dashboard.getPatientCount()));

        cards.add(new StatisticCard(
                "🩺",
                "Doctors",
                dashboard.getDoctorCount()));

        cards.add(new StatisticCard(
                "📅",
                "Appointments",
                dashboard.getAppointmentCount()));

        cards.add(new StatisticCard(
                "💰",
                "Bills",
                dashboard.getBillCount()));

        cards.add(new StatisticCard(
                "💳",
                "Payments",
                dashboard.getPaymentCount()));

        cards.add(new StatisticCard(
                "🧪",
                "Lab Tests",
                dashboard.getLabTestCount()));

        cards.add(new StatisticCard(
                "📦",
                "Inventory",
                dashboard.getInventoryCount()));

        cards.add(new StatisticCard(
                "👨‍⚕️",
                "Staff",
                dashboard.getStaffCount()));

        cards.add(new StatisticCard(
                "🏥",
                "LifeCare",
                dashboard.getPatientCount()));

        mainPanel.add(
                cards,
                BorderLayout.CENTER);

        //------------------------------------
        // Right Panel
        //------------------------------------

        JPanel rightPanel =
                new JPanel();

        rightPanel.setLayout(
                new BoxLayout(
                        rightPanel,
                        BoxLayout.Y_AXIS));

        rightPanel.setBackground(
                Theme.BACKGROUND);

        rightPanel.setPreferredSize(
                new Dimension(
                        320,
                        0));

        rightPanel.add(
                createQuickLinks(
                        openPatients,
                        openDoctors,
                        openAppointments,
                        openPrescription,
                        openBilling));

        rightPanel.add(
                Box.createVerticalStrut(20));

        rightPanel.add(
                createNotes());

        rightPanel.add(
                Box.createVerticalStrut(20));

        rightPanel.add(
                createRecentActivity());

        add(
                mainPanel,
                BorderLayout.CENTER);

        add(
                rightPanel,
                BorderLayout.EAST);

        startClock();
    }

    //---------------------------------------------------
    // HEADER
    //---------------------------------------------------

    private JPanel createHeader() {

        JPanel panel =
                new JPanel(
                        new BorderLayout());

        panel.setBackground(
                Theme.BACKGROUND);

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
                Theme.PRIMARY_GREEN);

        JLabel greeting =
                new JLabel(
                        "👋 Welcome back, Administrator");

        greeting.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        18));

        greeting.setForeground(
                Color.GRAY);

        left.add(title);
        left.add(Box.createVerticalStrut(5));
        left.add(greeting);

        JPanel right =
                new JPanel();

        right.setOpaque(false);

        right.setLayout(
                new BoxLayout(
                        right,
                        BoxLayout.Y_AXIS));

        JLabel date =
                new JLabel(
                        java.time.LocalDate.now()
                                .toString());

        date.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16));

        date.setForeground(
                Color.GRAY);

        clockLabel =
                new JLabel();

        clockLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16));

        clockLabel.setForeground(
                Theme.PRIMARY_GREEN);

        right.add(date);
        right.add(clockLabel);

        panel.add(
                left,
                BorderLayout.WEST);

        panel.add(
                right,
                BorderLayout.EAST);

        return panel;
    }

    //---------------------------------------------------
    // CLOCK
    //---------------------------------------------------

    private void startClock() {

        Timer timer =
                new Timer(
                        1000,
                        e -> clockLabel.setText(
                                java.time.LocalTime.now()
                                        .withNano(0)
                                        .toString()));

        timer.start();
    }

    //---------------------------------------------------
    // QUICK LINKS
    //---------------------------------------------------

    private JPanel createQuickLinks(
            Runnable openPatients,
            Runnable openDoctors,
            Runnable openAppointments,
            Runnable openPrescription,
            Runnable openBilling) {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS));

        panel.setBackground(
                Color.WHITE);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        220,
                                        220)),
                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20)));

        JLabel title =
                new JLabel(
                        "⚡ Quick Actions");

        title.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.BOLD,
                        18));

        title.setForeground(
                Theme.PRIMARY_GREEN);

        panel.add(title);
        panel.add(Box.createVerticalStrut(20));

        panel.add(createQuickButton(
                "👤 Patients",
                openPatients));

        panel.add(Box.createVerticalStrut(12));

        panel.add(createQuickButton(
                "🩺 Doctors",
                openDoctors));

        panel.add(Box.createVerticalStrut(12));

        panel.add(createQuickButton(
                "📅 Appointments",
                openAppointments));

        panel.add(Box.createVerticalStrut(12));

        panel.add(createQuickButton(
                "💊 Prescription",
                openPrescription));

        panel.add(Box.createVerticalStrut(12));

        panel.add(createQuickButton(
                "💰 Billing",
                openBilling));

        return panel;
    }

    //---------------------------------------------------
    // QUICK BUTTON
    //---------------------------------------------------

    private JButton createQuickButton(
            String text,
            Runnable action) {

        JButton button =
                new JButton(text);

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45));

        button.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.BOLD,
                        14));

        button.setBackground(
                new Color(
                        245,
                        250,
                        248));

        button.setForeground(
                Theme.PRIMARY_GREEN);

        button.setFocusPainted(false);

        button.setHorizontalAlignment(
                SwingConstants.LEFT);

        button.addActionListener(
                e -> action.run());

        return button;
    }

    //---------------------------------------------------
    // NOTES
    //---------------------------------------------------

    private JPanel createNotes() {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS));

        panel.setBackground(
                Color.WHITE);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        220,
                                        220)),
                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20)));

        panel.add(
                new JLabel(
                        "📌 LifeCare Notes"));

        panel.add(Box.createVerticalStrut(20));
        panel.add(new JLabel("👋 Welcome Administrator"));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("📊 Monitor hospital activities"));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("📑 Review reports"));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("📅 Manage appointments"));

        return panel;
    }

    //---------------------------------------------------
    // RECENT ACTIVITY
    //---------------------------------------------------

    private JPanel createRecentActivity() {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS));

        panel.setBackground(
                Color.WHITE);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        220,
                                        220)),
                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20)));

        panel.add(
                new JLabel(
                        "📈 Recent Activity"));

        panel.add(Box.createVerticalStrut(20));
        panel.add(new JLabel(" New patient registered"));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel(" Appointment scheduled"));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel(" Payment received"));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel(" Lab result uploaded"));

        return panel;
    }
}