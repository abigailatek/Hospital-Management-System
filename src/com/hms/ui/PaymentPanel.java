package com.hms.ui;

import com.hms.models.Payment;
import com.hms.services.PaymentService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class PaymentPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private JTextField txtSearch;

    public PaymentPanel() {

        setLayout(new BorderLayout());

        JLabel title =
                new JLabel("Payments");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28));

        add(title, BorderLayout.NORTH);

        JPanel toolbar =
        new JPanel(
                new FlowLayout( FlowLayout.LEFT,10,10));

       toolbar.setBackground(new Color(245,247,250));

        JButton btnAdd =
                new JButton("Add");

        JButton btnDelete =
                new JButton("Delete");

        JButton btnRefresh =
                new JButton("Refresh");
        
        txtSearch =
                new JTextField(15);
         txtSearch.setFont(
        new Font( "Segoe UI", Font.PLAIN, 14));
        JButton btnSearch =
                new JButton("Search Bill ID");
        styleButton(btnAdd);
        styleButton(btnDelete);
        styleButton(btnRefresh);
        styleButton(btnSearch);
        toolbar.add(btnAdd);
        toolbar.add(btnDelete);
        toolbar.add(btnRefresh);
        toolbar.add(txtSearch);
        toolbar.add(btnSearch);

        add(toolbar,BorderLayout.SOUTH);
        model =new DefaultTableModel();
        model.setColumnIdentifiers(
                new Object[]{
                        "Payment ID",
                        "Bill ID",
                        "Amount",
                        "Method",
                        "Date"
                });

        table =
                new JTable(model);

        add(  new JScrollPane(table),
                BorderLayout.CENTER);

        btnRefresh.addActionListener(
                e -> loadPayments());

        btnAdd.addActionListener(
                e -> addPayment());

        btnDelete.addActionListener(
                e -> deletePayment());

        btnSearch.addActionListener(
                e -> searchPayments());

        loadPayments();
    }

    private void loadPayments() {

        PaymentService service =
                new PaymentService();

        List<Payment> payments =
                service.getAllPayments();

        model.setRowCount(0);

        for (Payment p : payments) {

            model.addRow(
                    new Object[]{
                            p.getPaymentId(),
                            p.getBillId(),
                            p.getAmount(),
                            p.getPaymentMethod(),
                            p.getPaymentDate()
                    });
        }
    }

    private void addPayment() {

        try {

            Payment payment =
                    new Payment();

            payment.setBillId(
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Bill ID")));

            payment.setAmount(
                    Double.parseDouble(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Amount")));

            payment.setPaymentMethod(
                    JOptionPane.showInputDialog(
                            this,
                            "Payment Method"));

            payment.setPaymentDate(
                    LocalDate.parse(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Payment Date (yyyy-mm-dd)")));

            PaymentService service =
                    new PaymentService();

            if (service.makePayment(payment)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Payment saved.");

                loadPayments();
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid input.");
        }
    }

    private void deletePayment() {

        int row =
                table.getSelectedRow();

        if (row == -1) return;

        int paymentId =
                (Integer)
                        model.getValueAt(
                                row,
                                0);

        PaymentService service =
                new PaymentService();

        if (service.deletePayment(
                paymentId)) {

            loadPayments();
        }
    }

    private void searchPayments() {

        try {

            int billId =
                    Integer.parseInt(
                            txtSearch.getText());

            PaymentService service =
                    new PaymentService();

            List<Payment> payments =
                    service.searchPayments(
                            billId);

            model.setRowCount(0);

            for (Payment p : payments) {

                model.addRow(
                        new Object[]{
                                p.getPaymentId(),
                                p.getBillId(),
                                p.getAmount(),
                                p.getPaymentMethod(),
                                p.getPaymentDate()
                        });
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid Bill ID.");
        }
    }
    private void styleButton(JButton button) {

    button.setBackground(
            new Color(16,120,110));

    button.setForeground(
            Color.WHITE);

    button.setFocusPainted(false);

    button.setFont(
            new Font(
                    "Segoe UI",
                    Font.BOLD,
                    13));

    button.setCursor(
            new Cursor(
                    Cursor.HAND_CURSOR));
}
}