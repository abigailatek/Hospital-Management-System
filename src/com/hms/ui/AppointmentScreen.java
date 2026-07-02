package com.hms.ui;

import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AppointmentScreen extends JPanel {

    public AppointmentScreen() {
        setLayout(new BorderLayout());
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("APPOINTMENTS", SwingConstants.CENTER);
        title.setFont(Theme.TITLE);
        title.setForeground(Theme.PRIMARY_GREEN);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(215, 215, 215)),
                new EmptyBorder(20, 25, 20, 25)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField patientName = new JTextField(18);
        JTextField doctorName = new JTextField(18);
        JTextField date = new JTextField(18);
        JTextField time = new JTextField(18);
        JComboBox<String> status = new JComboBox<>(new String[]{
                "Scheduled", "Completed", "Cancelled"
        });

        addRow(form, gbc, 0, "Patient Name:", patientName, "Doctor Name:", doctorName);
        addRow(form, gbc, 1, "Date:", date, "Time:", time);
        addRow(form, gbc, 2, "Status:", status, "", new JLabel(""));

        JButton add = button("Add Appointment");
        JButton clear = button("Clear");
        JButton cancel = button("Cancel");

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        buttons.setBackground(Color.WHITE);
        buttons.add(add);
        buttons.add(clear);
        buttons.add(cancel);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        form.add(buttons, gbc);

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Patient", "Doctor", "Date", "Time", "Status"}, 0
        );

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        add.addActionListener(e -> {
            model.addRow(new Object[]{
                    patientName.getText(),
                    doctorName.getText(),
                    date.getText(),
                    time.getText(),
                    status.getSelectedItem()
            });
        });

        clear.addActionListener(e -> {
            patientName.setText("");
            doctorName.setText("");
            date.setText("");
            time.setText("");
            status.setSelectedIndex(0);
        });

        JPanel center = new JPanel(new BorderLayout(15, 15));
        center.setBackground(Theme.BACKGROUND);
        center.add(form, BorderLayout.NORTH);
        center.add(scrollPane, BorderLayout.CENTER);

        add(title, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
    }

    private void addRow(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String label1,
            JComponent field1,
            String label2,
            JComponent field2
    ) {
        gbc.gridy = row;

        gbc.gridx = 0;
        panel.add(label(label1), gbc);

        gbc.gridx = 1;
        panel.add(field1, gbc);

        gbc.gridx = 2;
        panel.add(label(label2), gbc);

        gbc.gridx = 3;
        panel.add(field2, gbc);
    }

    private JLabel label(String text) {
        JLabel label = new JLabel(text);
        label.setFont(Theme.NORMAL);
        label.setForeground(Theme.TEXT);
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
