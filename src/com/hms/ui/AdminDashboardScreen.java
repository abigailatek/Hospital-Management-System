package com.hms.ui;

import com.hms.services.DashboardService;
import com.hms.ui.components.StatisticCard;
import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminDashboardScreen extends JPanel {

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

        setLayout(new BorderLayout(20,20));
        setBackground(Theme.BACKGROUND);

        DashboardService dashboard = new DashboardService();

        JPanel mainPanel = new JPanel(new BorderLayout(20,20));
        mainPanel.setBackground(Theme.BACKGROUND);
        mainPanel.setBorder(new EmptyBorder(20,20,20,20));

        JLabel title = new JLabel("LifeCare Hospital Dashboard");
        title.setFont(new Font("Segoe UI",Font.BOLD,30));
        title.setForeground(Theme.PRIMARY_GREEN);

        mainPanel.add(title,BorderLayout.NORTH);

        JPanel cards = new JPanel(new GridLayout(3,3,20,20));
        cards.setBackground(Theme.BACKGROUND);

        cards.add(new StatisticCard("👤","Patients",dashboard.getPatientCount()));
        cards.add(new StatisticCard("🩺","Doctors",dashboard.getDoctorCount()));
        cards.add(new StatisticCard("📅","Appointments",dashboard.getAppointmentCount()));
        cards.add(new StatisticCard("💰","Bills",dashboard.getBillCount()));
        cards.add(new StatisticCard("💳","Payments",dashboard.getPaymentCount()));
        cards.add(new StatisticCard("🧪","Lab Tests",dashboard.getLabTestCount()));
        cards.add(new StatisticCard("📦","Inventory",dashboard.getInventoryCount()));
        cards.add(new StatisticCard("👨‍⚕️","Staff",dashboard.getStaffCount()));
        cards.add(new StatisticCard("🏥","LifeCare",dashboard.getPatientCount()));

        mainPanel.add(cards,BorderLayout.CENTER);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
        rightPanel.setBackground(Theme.BACKGROUND);
        rightPanel.setPreferredSize(new Dimension(280,0));

        rightPanel.add(createQuickLinks(
                openPatients,
                openDoctors,
                openAppointments,
                openPrescription,
                openBilling
        ));

        rightPanel.add(Box.createVerticalStrut(20));

        rightPanel.add(createNotes());

        add(mainPanel,BorderLayout.CENTER);
        add(rightPanel,BorderLayout.EAST);

    }


    private JPanel createQuickLinks(
            Runnable openPatients,
            Runnable openDoctors,
            Runnable openAppointments,
            Runnable openPrescription,
            Runnable openBilling
    ){

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Quick Links"));

        panel.add(createButton("Patients",openPatients));
        panel.add(createButton("Doctors",openDoctors));
        panel.add(createButton("Appointments",openAppointments));
        panel.add(createButton("Prescription",openPrescription));
        panel.add(createButton("Billing",openBilling));

        return panel;
    }

    private JPanel createNotes(){

        JPanel panel=new JPanel();

        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        panel.setBackground(Color.WHITE);

        panel.setBorder(BorderFactory.createTitledBorder("LifeCare Notes"));

        panel.add(new JLabel("✔ Welcome Administrator"));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("✔ Monitor daily hospital activities"));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("✔ View reports from the Reports module"));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("✔ Manage patients and appointments"));

        return panel;
    }

    private JButton createButton(String text,Runnable action){

        JButton button=new JButton(text);

        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.setMaximumSize(new Dimension(220,40));

        button.addActionListener(e->action.run());

        return button;
    }

}