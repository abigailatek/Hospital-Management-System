package com.hms.ui;

import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DoctorHomeScreen extends JPanel {

    public DoctorHomeScreen(
            Runnable openPatients,
            Runnable openAppointments,
            Runnable openEMR,
            Runnable openPrescription,
            Runnable openReports,
            Runnable openProfile
    ) {
        setLayout(new BorderLayout(20, 20));
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("Doctor Dashboard");
        title.setFont(Theme.TITLE);
        title.setForeground(Theme.PRIMARY_GREEN);

        JPanel center = new JPanel(new BorderLayout(20, 20));
        center.setBackground(Theme.BACKGROUND);

        JPanel cards = new JPanel(new GridLayout(2, 2, 20, 20));
        cards.setBackground(Theme.BACKGROUND);

        cards.add(card("👥", "My Patients", openPatients));
        cards.add(card("📅", "Appointments", openAppointments));
        cards.add(card("📝", "Patient EMR", openEMR));
        cards.add(card("💊", "Prescriptions", openPrescription));

        JPanel rightPanel = new JPanel(new GridLayout(2, 1, 15, 15));
        rightPanel.setPreferredSize(new Dimension(320, 0));
        rightPanel.setBackground(Theme.BACKGROUND);

        rightPanel.add(sidePanel("★ Quick Actions", new String[]{
                "➜ Open Patient Record",
                "➜ View Appointments",
                "➜ New Diagnosis",
                "➜ Write Prescription",
                "➜ View Reports"
        }, new Runnable[]{
                openPatients,
                openAppointments,
                openEMR,
                openPrescription,
                openReports
        }));

        rightPanel.add(notesPanel());

        center.add(cards, BorderLayout.CENTER);
        center.add(rightPanel, BorderLayout.EAST);

        add(title, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
    }

    private JPanel card(String icon, String text, Runnable action) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                new EmptyBorder(25, 25, 25, 25)
        ));

        JLabel iconLabel = new JLabel(icon, SwingConstants.CENTER);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));

        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label.setForeground(Theme.TEXT);

        panel.add(iconLabel, BorderLayout.CENTER);
        panel.add(label, BorderLayout.SOUTH);

        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(Theme.LIGHT_GREEN);
            }

            public void mouseExited(MouseEvent e) {
                panel.setBackground(Color.WHITE);
            }

            public void mouseClicked(MouseEvent e) {
                action.run();
            }
        });

        return panel;
    }

    private JPanel sidePanel(String title, String[] items, Runnable[] actions) {
        JPanel panel = basePanel(title);

        for (int i = 0; i < items.length; i++) {
            panel.add(clickableLabel(items[i], actions[i]));
        }

        return panel;
    }

    private JPanel notesPanel() {
        JPanel panel = basePanel("✎ Notes");

        panel.add(noteLabel("➜ Review patient history",
                "Always review the patient's previous medical history before diagnosis."));

        panel.add(noteLabel("➜ Update diagnosis clearly",
                "Enter diagnosis and treatment details clearly in the EMR screen."));

        panel.add(noteLabel("➜ Confirm prescription dosage",
                "Check medicine dosage before issuing a prescription."));

        panel.add(noteLabel("➜ Request labs when required",
                "Use lab requests where diagnosis requires further confirmation."));

        return panel;
    }

    private JPanel basePanel(String title) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                new EmptyBorder(15, 18, 15, 18)
        ));

        JLabel header = new JLabel(title);
        header.setFont(new Font("Segoe UI", Font.BOLD, 18));
        header.setForeground(Theme.PRIMARY_GREEN);

        panel.add(header);
        panel.add(Box.createVerticalStrut(15));

        return panel;
    }

    private JLabel clickableLabel(String text, Runnable action) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        label.setForeground(Theme.TEXT);
        label.setBorder(new EmptyBorder(8, 0, 8, 0));
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));

        label.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                label.setForeground(Theme.PRIMARY_GREEN);
            }

            public void mouseExited(MouseEvent e) {
                label.setForeground(Theme.TEXT);
            }

            public void mouseClicked(MouseEvent e) {
                action.run();
            }
        });

        return label;
    }

    private JLabel noteLabel(String text, String message) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        label.setForeground(Theme.TEXT);
        label.setBorder(new EmptyBorder(8, 0, 8, 0));
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));

        label.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                label.setForeground(Theme.PRIMARY_GREEN);
            }

            public void mouseExited(MouseEvent e) {
                label.setForeground(Theme.TEXT);
            }

            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(
                        null,
                        message,
                        text,
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        return label;
    }
}