package com.hms.ui;

import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DoctorManagePatientScreen extends JPanel {

    public DoctorManagePatientScreen() {
        setLayout(new BorderLayout(15, 15));
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("DOCTOR - MANAGE PATIENTS");
        title.setFont(Theme.TITLE);
        title.setForeground(Theme.PRIMARY_GREEN);
        add(title, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new BorderLayout(10, 10));
        searchPanel.setBackground(Theme.BACKGROUND);

        JTextField searchField = new JTextField();
        JButton searchBtn = button("Search");

        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchBtn, BorderLayout.EAST);

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Patient ID", "Name", "Gender", "Age", "Status"}, 0
        );

        JTable table = new JTable(model);
        table.setRowHeight(28);

        JScrollPane tablePane = new JScrollPane(table);

        JPanel details = new JPanel(new GridLayout(6, 2, 10, 10));
        details.setBackground(Color.WHITE);
        details.setBorder(BorderFactory.createTitledBorder("Patient Details"));

        details.add(label("Patient ID"));
        details.add(new JTextField());

        details.add(label("Patient Name"));
        details.add(new JTextField());

        details.add(label("Age"));
        details.add(new JTextField());

        details.add(label("Gender"));
        details.add(new JTextField());

        details.add(label("Diagnosis"));
        details.add(new JTextField());

        details.add(label("Treatment"));
        details.add(new JTextField());

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));
        buttons.setBackground(Theme.BACKGROUND);

        buttons.add(button("Update"));
        buttons.add(button("Prescription"));
        buttons.add(button("Discharge"));

        JPanel center = new JPanel(new BorderLayout(15, 15));
        center.setBackground(Theme.BACKGROUND);
        center.add(searchPanel, BorderLayout.NORTH);
        center.add(tablePane, BorderLayout.CENTER);
        center.add(details, BorderLayout.SOUTH);

        add(center, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
    }

    private JLabel label(String text) {
        JLabel label = new JLabel(text);
        label.setFont(Theme.NORMAL);
        return label;
    }

    private JButton button(String text) {
        JButton btn = new JButton(text);
        btn.setFont(Theme.NORMAL);
        btn.setBackground(Theme.PRIMARY_GREEN);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        return btn;
    }
}
