package com.hms.ui;

import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AttendancePanel extends JPanel {

    public AttendancePanel() {

        setLayout(new BorderLayout(15,15));
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(20,25,20,25));

        JLabel title =
                new JLabel("STAFF ATTENDANCE");

        title.setFont(Theme.TITLE);
        title.setForeground(Theme.PRIMARY_GREEN);

        JPanel form =
                new JPanel(new FlowLayout(FlowLayout.LEFT));

        form.setBackground(Color.WHITE);

        JTextField txtStaffId =
                new JTextField(10);

        JTextField txtName =
                new JTextField(15);

        JComboBox<String> cmbStatus =
                new JComboBox<>(new String[]{
                        "Present",
                        "Absent",
                        "Leave"
                });

        JButton btnAdd =
                new JButton("Mark Attendance");

        btnAdd.setBackground(
                Theme.PRIMARY_GREEN);

        btnAdd.setForeground(
                Color.WHITE);

        form.add(new JLabel("Staff ID"));
        form.add(txtStaffId);

        form.add(new JLabel("Name"));
        form.add(txtName);

        form.add(new JLabel("Status"));
        form.add(cmbStatus);

        form.add(btnAdd);

        DefaultTableModel model =
                new DefaultTableModel(
                        new Object[]{
                                "Staff ID",
                                "Name",
                                "Status"
                        },
                        0);

        JTable table =
                new JTable(model);

        table.setRowHeight(28);

        btnAdd.addActionListener(e -> {

            model.addRow(
                    new Object[]{
                            txtStaffId.getText(),
                            txtName.getText(),
                            cmbStatus.getSelectedItem()
                    });

            txtStaffId.setText("");
            txtName.setText("");
            cmbStatus.setSelectedIndex(0);
        });

        add(title, BorderLayout.NORTH);
        add(form, BorderLayout.CENTER);
        add(new JScrollPane(table),
                BorderLayout.SOUTH);
    }
}