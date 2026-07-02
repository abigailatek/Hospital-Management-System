package com.hms.ui;

import javax.swing.*;
import java.awt.*;

public class AdminDashboardScreen extends JPanel {

    public AdminDashboardScreen() {

        setLayout(new BorderLayout());

        JLabel label = new JLabel(
                "Admin Dashboard",
                SwingConstants.CENTER);

        label.setFont(new Font("Segoe UI",Font.BOLD,30));

        add(label,BorderLayout.CENTER);

    }

}