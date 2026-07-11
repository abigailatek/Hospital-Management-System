package com.hms.ui;

import com.hms.models.Billing;
import com.hms.services.BillingService;
import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class BillingScreen extends JPanel {

    private JTextField invoiceNo;
    private JTextField patientId;
    private JTextField serviceField;
    private JTextField amount;
    private JComboBox<String> paymentStatus;

    private JTable table;
    private DefaultTableModel model;

    private BillingService billingService;

    public BillingScreen() {

        billingService = new BillingService();

        setLayout(new BorderLayout(15, 15));
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("BILLING MANAGEMENT");
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

        invoiceNo = field();
        patientId = field();
        serviceField = field();
        amount = field();

        paymentStatus = new JComboBox<>(
                new String[]{
                        "Pending",
                        "Paid",
                        "Partially Paid"
                });

        addRow(
                formPanel,
                gbc,
                0,
                "Bill ID:",
                invoiceNo,
                "Patient ID:",
                patientId);

        addRow(
                formPanel,
                gbc,
                1,
                "Service:",
                serviceField,
                "Amount:",
                amount);

        addRow(
                formPanel,
                gbc,
                2,
                "Payment Status:",
                paymentStatus,
                "",
                new JLabel(""));

        JPanel buttons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                5));

        buttons.setBackground(Color.WHITE);

        JButton generate =
                button("Generate Bill");

        JButton clear =
                button("Clear");

        JButton delete =
                button("Delete");

        buttons.add(generate);
        buttons.add(clear);
        buttons.add(delete);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;

        formPanel.add(buttons, gbc);

        model =
                new DefaultTableModel(
                        new String[]{
                                "Bill ID",
                                "Patient ID",
                                "Amount",
                                "Payment Status",
                                "Bill Date"
                        },
                        0);

        table =
                new JTable(model);

        table.setRowHeight(28);

        JPanel center =
                new JPanel(
                        new BorderLayout(
                                15,
                                15));

        center.setBackground(
                Theme.BACKGROUND);

        center.add(
                formPanel,
                BorderLayout.NORTH);

        center.add(
                new JScrollPane(table),
                BorderLayout.CENTER);

        add(title, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);

        //---------------------------------
        // Events
        //---------------------------------

        generate.addActionListener(
                e -> createBill());

        clear.addActionListener(
                e -> clearFields());

        delete.addActionListener(
                e -> deleteBill());

        loadBills();
    }

    //---------------------------------

    private void loadBills() {

        model.setRowCount(0);

        List<Billing> bills =
                billingService.getAllBills();

        for (Billing b : bills) {

            model.addRow(
                    new Object[]{
                            b.getBillId(),
                            b.getPatientId(),
                            b.getTotalAmount(),
                            b.getPaymentStatus(),
                            b.getBillDate()
                    });
        }
    }

    //---------------------------------

    private void createBill() {

        try {

            Billing bill =
                    new Billing();

            bill.setPatientId(
                    Integer.parseInt(
                            patientId.getText()));

            bill.setBillDate(
                    LocalDate.now());

            bill.setTotalAmount(
                    new BigDecimal(
                            amount.getText()));

            bill.setPaymentStatus(
                    paymentStatus
                            .getSelectedItem()
                            .toString());

            if (billingService
                    .createBill(bill)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Bill generated successfully.");

                loadBills();

                clearFields();
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage());
        }
    }

    //---------------------------------

    private void deleteBill() {

        int row =
                table.getSelectedRow();

        if (row < 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a bill.");

            return;
        }

        int billId =
                Integer.parseInt(
                        model.getValueAt(
                                row,
                                0)
                                .toString());

        if (billingService
                .deleteBill(
                        billId)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Bill deleted.");

            loadBills();
        }
    }

    //---------------------------------

    private void clearFields() {

        invoiceNo.setText("");
        patientId.setText("");
        serviceField.setText("");
        amount.setText("");
        paymentStatus.setSelectedIndex(0);
    }

    //---------------------------------

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
        JTextField field =
                new JTextField(18);

        field.setFont(
                Theme.NORMAL);

        return field;
    }

    private JLabel label(String text) {
        JLabel label =
                new JLabel(text);

        label.setFont(
                Theme.NORMAL);

        label.setForeground(
                Theme.TEXT);

        return label;
    }

    private JButton button(String text) {
        JButton btn =
                new JButton(text);

        btn.setFont(
                Theme.NORMAL);

        btn.setBackground(
                Theme.PRIMARY_GREEN);

        btn.setForeground(
                Color.WHITE);

        btn.setFocusPainted(false);

        return btn;
    }
}