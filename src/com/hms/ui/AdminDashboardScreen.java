package com.hms.ui;

import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        setLayout(new BorderLayout(20, 20));
        setBackground(Theme.BACKGROUND);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(Theme.BACKGROUND);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Dashboard Overview");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Theme.PRIMARY_GREEN);

        mainPanel.add(title, BorderLayout.NORTH);

        JPanel tiles = new JPanel(new GridLayout(3, 3, 20, 20));
        tiles.setBackground(Theme.BACKGROUND);

        tiles.add(createTile("🏠", "Dashboard", null));
        tiles.add(createTile("👨‍⚕️", "Doctors", openDoctors));
        tiles.add(createTile("📅", "Appointments", openAppointments));
        tiles.add(createTile("👤", "Patients", openPatients));
        tiles.add(createTile("🩺", "Diagnosis", openDiagnosis));
        tiles.add(createTile("💊", "Prescription", openPrescription));
        tiles.add(createTile("🏥", "Medical Store", openMedicalStore));
        tiles.add(createTile("💰", "Billing", openBilling));
        tiles.add(createTile("📊", "Reports", openReports));

        mainPanel.add(tiles, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new GridLayout(2, 1, 15, 15));
        rightPanel.setPreferredSize(new Dimension(280, 0));
        rightPanel.setBackground(Theme.BACKGROUND);

        rightPanel.add(createQuickLinksPanel(
                openAppointments,
                openPatients,
                openPrescription,
                openReports,
                openBilling
        ));

        rightPanel.add(createNotesPanel());

        add(mainPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }

    private JPanel createTile(String emoji, String title, Runnable action) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel iconLabel = new JLabel(emoji, SwingConstants.CENTER);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(60, 60, 60));

        card.add(iconLabel, BorderLayout.CENTER);
        card.add(titleLabel, BorderLayout.SOUTH);

        if (action != null) {
            card.setCursor(new Cursor(Cursor.HAND_CURSOR));
            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    card.setBackground(new Color(235, 248, 235));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    card.setBackground(Color.WHITE);
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    action.run();
                }
            });
        }

        return card;
    }

    private JPanel createQuickLinksPanel(
            Runnable openAppointments,
            Runnable openPatients,
            Runnable openPrescription,
            Runnable openReports,
            Runnable openBilling
    ) {
        JPanel panel = createSidePanelBase("★ Quick Links");

        panel.add(createLink("➜ New Appointment", openAppointments));
        panel.add(createLink("➜ New Patient", openPatients));
        panel.add(createLink("➜ Add Prescription", openPrescription));
        panel.add(createLink("➜ View Report", openReports));
        panel.add(createLink("➜ Billing", openBilling));

        return panel;
    }

    private JPanel createNotesPanel() {
        JPanel panel = createSidePanelBase("✎ Notes");

        panel.add(createNoteLink(
                "➜ Support Center",
                "For technical support, contact the Hospital IT Department."
        ));

        panel.add(createNoteLink(
                "➜ How to use it",
                "Use the sidebar, dashboard cards, or quick links to move between modules."
        ));

        panel.add(createNoteLink(
                "➜ Help Line",
                "Help Line: +256 XXX XXX XXX\nEmail: support@hospital.com"
        ));

        panel.add(createNoteLink(
                "➜ Customer Feedback",
                "Thank you for using the Hospital Management System.\nYour feedback is appreciated."
        ));

        return panel;
    }

    private JPanel createSidePanelBase(String heading) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                new EmptyBorder(15, 18, 15, 18)
        ));

        JLabel title = new JLabel(heading);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(Theme.PRIMARY_GREEN);

        panel.add(title);
        panel.add(Box.createVerticalStrut(18));

        return panel;
    }

    private JLabel createLink(String text, Runnable action) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        label.setForeground(Theme.TEXT);
        label.setBorder(new EmptyBorder(8, 0, 8, 0));
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setForeground(Theme.PRIMARY_GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setForeground(Theme.TEXT);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                action.run();
            }
        });

        return label;
    }

    private JLabel createNoteLink(String text, String message) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        label.setForeground(Theme.TEXT);
        label.setBorder(new EmptyBorder(8, 0, 8, 0));
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setForeground(Theme.PRIMARY_GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setForeground(Theme.TEXT);
            }

            @Override
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