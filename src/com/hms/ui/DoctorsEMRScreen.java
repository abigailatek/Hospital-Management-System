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

        tabs.addTab("Patient Info", createPatientInfoPanel());
        tabs.addTab("History", createHistoryPanel());
        tabs.addTab("Diagnosis", createDiagnosisPanel());
        tabs.addTab("Labs", createLabsPanel());

        add(title, BorderLayout.NORTH);
        add(tabs, BorderLayout.CENTER);
    }

    private JPanel createPatientInfoPanel() {
        JPanel panel = basePanel();

        panel.add(label("Patient Name: John Doe"));
        panel.add(label("Age: 35"));
        panel.add(label("Gender: Male"));
        panel.add(label("Phone: 07XXXXXXXX"));
        panel.add(label("Patient ID: P-001"));

        return panel;
    }

    private JPanel createHistoryPanel() {
        JPanel panel = basePanel();

        JTextArea history = new JTextArea(
                "Previous Visit: Fever and headache\n" +
                "Medication: Paracetamol\n" +
                "Allergies: None\n" +
                "Last Visit Date: 10/07/2026"
        );
        history.setFont(Theme.NORMAL);
        history.setLineWrap(true);
        history.setWrapStyleWord(true);

        panel.add(new JScrollPane(history));

        return panel;
    }

    private JPanel createDiagnosisPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(25, 30, 25, 30));

        JPanel form = new JPanel(new GridLayout(2, 2, 15, 15));
        form.setBackground(Color.WHITE);

        JTextArea diagnosis = new JTextArea(4, 30);
        JTextArea treatment = new JTextArea(4, 30);

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

    private JPanel createLabsPanel() {
        JPanel panel = basePanel();

        panel.add(label("Requested Labs: Blood Test, Malaria Test"));
        panel.add(label("Lab Status: Pending"));
        panel.add(label("Results: Not yet available"));

        return panel;
    }

    private JPanel basePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(25, 30, 25, 30));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    private JLabel label(String text) {
        JLabel label = new JLabel(text);
        label.setFont(Theme.NORMAL);
        label.setForeground(Theme.TEXT);
        label.setBorder(new EmptyBorder(8, 0, 8, 0));
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
