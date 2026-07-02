package com.hms.ui;

import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MedicalStoreScreen extends JPanel {

    public MedicalStoreScreen() {
        setLayout(new BorderLayout(15, 15));
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("MEDICAL STORE");
        title.setFont(Theme.TITLE);
        title.setForeground(Theme.PRIMARY_GREEN);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(215, 215, 215)),
                new EmptyBorder(20, 25, 20, 25)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField medicineId = field();
        JTextField medicineName = field();
        JTextField category = field();
        JTextField quantity = field();
        JTextField supplier = field();
        JTextField expiryDate = field();

        addRow(formPanel, gbc, 0, "Medicine ID:", medicineId, "Medicine Name:", medicineName);
        addRow(formPanel, gbc, 1, "Category:", category, "Quantity:", quantity);
        addRow(formPanel, gbc, 2, "Supplier:", supplier, "Expiry Date:", expiryDate);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        buttons.setBackground(Color.WHITE);

        JButton add = button("Add Medicine");
        JButton update = button("Update");
        JButton clear = button("Clear");
        JButton delete = button("Delete");

        buttons.add(add);
        buttons.add(update);
        buttons.add(clear);
        buttons.add(delete);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        formPanel.add(buttons, gbc);

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Medicine ID", "Medicine Name", "Category", "Quantity", "Supplier", "Expiry Date"}, 0
        );

        JTable table = new JTable(model);
        table.setRowHeight(28);

        add.addActionListener(e -> model.addRow(new Object[]{
                medicineId.getText(),
                medicineName.getText(),
                category.getText(),
                quantity.getText(),
                supplier.getText(),
                expiryDate.getText()
        }));

        clear.addActionListener(e -> {
            medicineId.setText("");
            medicineName.setText("");
            category.setText("");
            quantity.setText("");
            supplier.setText("");
            expiryDate.setText("");
        });

        delete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                model.removeRow(row);
            }
        });

        JPanel center = new JPanel(new BorderLayout(15, 15));
        center.setBackground(Theme.BACKGROUND);
        center.add(formPanel, BorderLayout.NORTH);
        center.add(new JScrollPane(table), BorderLayout.CENTER);

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
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        panel.add(label(label1), gbc);

        gbc.gridx = 1;
        panel.add(field1, gbc);

        gbc.gridx = 2;
        panel.add(label(label2), gbc);

        gbc.gridx = 3;
        panel.add(field2, gbc);
    }

    private JTextField field() {
        JTextField field = new JTextField(18);
        field.setFont(Theme.NORMAL);
        return field;
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