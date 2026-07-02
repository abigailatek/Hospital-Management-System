package com.hms.ui;

import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DoctorsEMRScreen extends JPanel {

    public DoctorsEMRScreen() {
        setLayout(new BorderLayout());
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("PATIENT EMR", SwingConstants.CENTER);
        title.setFont(Theme.TITLE);
        title.setForeground(Theme.PRIMARY_GREEN);

        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(Theme.NORMAL);

        tabs.addTab("Patient Info", patientInfoPanel());
        tabs.addTab("History", historyPanel());
        tabs.addTab("Diagnosis", diagnosisPanel());
        tabs.addTab("Labs", labsPanel());

        add(title, BorderLayout.NORTH);
        add(tabs, BorderLayout.CENTER);
    }

    private JPanel patientInfoPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 15, 15));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(30, 40, 30, 40));

        panel.add(label("Patient Name:"));
        panel.add(new JTextField());

        panel.add(label("Patient ID:"));
        panel.add(new JTextField());

        panel.add(label("Age:"));
        panel.add(new JTextField());

        panel.add(label("Gender:"));
        panel.add(new JTextField());

        panel.add(label("Phone:"));
        panel.add(new JTextField());

        return panel;
    }

    private JPanel historyPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(30, 40, 30, 40));

        JTextArea history = new JTextArea();
        history.setFont(Theme.NORMAL);
        history.setLineWrap(true);
        history.setWrapStyleWord(true);

        panel.add(new JScrollPane(history), BorderLayout.CENTER);

        return panel;
    }

    private JPanel diagnosisPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(30, 40, 30, 40));

        JPanel form = new JPanel(new GridLayout(2, 2, 15, 15));
        form.setBackground(Color.WHITE);

        JTextArea diagnosis = new JTextArea();
        JTextArea treatment = new JTextArea();

        diagnosis.setLineWrap(true);
        treatment.setLineWrap(true);

        form.add(label("Diagnosis:"));
        form.add(new JScrollPane(diagnosis));

        form.add(label("Treatment:"));
        form.add(new JScrollPane(treatment));

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttons.setBackground(Color.WHITE);

        buttons.add(button("Save"));
        buttons.add(button("Prescribe"));
        buttons.add(button("Request Lab"));

        panel.add(form, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel labsPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(30, 40, 30, 40));

        JPanel form = new JPanel(new GridLayout(3, 2, 15, 15));
        form.setBackground(Color.WHITE);

        form.add(label("Requested Test:"));
        form.add(new JTextField());

        form.add(label("Status:"));
        form.add(new JTextField());

        form.add(label("Results:"));
        form.add(new JScrollPane(new JTextArea()));

        panel.add(form, BorderLayout.NORTH);

        return panel;
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
