package com.hms.ui;

import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ReportsPanel extends JPanel {

    public ReportsPanel() {

        setLayout(new BorderLayout(15,15));
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(20,20,20,20));

        JLabel title = new JLabel("Reports");
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(Theme.PRIMARY_GREEN);

        add(title, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(3,2,20,20));
        center.setBackground(Theme.BACKGROUND);

       JFrame frame = new JFrame("Patient Report");

       frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

       frame.setSize(1000,600);

       frame.setLocationRelativeTo(null);

       frame.add(new ReportViewerPanel("Patient Report"));

       frame.setVisible(true);
        center.add(createButton("Doctor Report"));

        center.add(createButton("Appointment Report"));
        center.add(createButton("Billing Report"));

        center.add(createButton("Inventory Report"));
        center.add(createButton("Payment Report"));

        add(center, BorderLayout.CENTER);
    }

  private JButton createButton(String text) {

    JButton button = new JButton(text);

    button.setFont(new Font("Segoe UI", Font.BOLD, 18));
    button.setBackground(Color.WHITE);
    button.setFocusPainted(false);

    button.addActionListener(e -> {

        JOptionPane.showMessageDialog(
                this,
                text + " will be implemented in the next step.",
                "LifeCare Hospital",
                JOptionPane.INFORMATION_MESSAGE
        );

    });

    return button;
}

}