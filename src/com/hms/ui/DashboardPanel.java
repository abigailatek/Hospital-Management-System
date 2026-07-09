package com.hms.ui;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    public DashboardPanel() {

        setLayout(new BorderLayout());
        setBackground(new Color(245,247,250));

        //-------------------------
        // Header
        //-------------------------

        JLabel title = new JLabel("Dashboard");

        title.setFont(new Font("Segoe UI", Font.BOLD, 28));

        title.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        add(title, BorderLayout.NORTH);

        //-------------------------
        // Cards
        //-------------------------

        JPanel cards = new JPanel(new GridLayout(1,4,20,20));

        cards.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        cards.setBackground(new Color(245,247,250));

        cards.add(createCard("Patients","0"));
        cards.add(createCard("Doctors","0"));
        cards.add(createCard("Appointments","0"));
        cards.add(createCard("Revenue","UGX 0"));

        add(cards,BorderLayout.CENTER);

    }

    private JPanel createCard(String title,String value){

        JPanel panel = new JPanel();

        panel.setLayout(new BorderLayout());

        panel.setBackground(Color.WHITE);

        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220,220,220)),
                BorderFactory.createEmptyBorder(20,20,20,20)));

        JLabel lblTitle=new JLabel(title);

        lblTitle.setFont(new Font("Segoe UI",Font.PLAIN,18));

        JLabel lblValue=new JLabel(value);

        lblValue.setFont(new Font("Segoe UI",Font.BOLD,32));

        lblValue.setForeground(new Color(16,120,110));

        panel.add(lblTitle,BorderLayout.NORTH);

        panel.add(lblValue,BorderLayout.CENTER);

        return panel;

    }

}