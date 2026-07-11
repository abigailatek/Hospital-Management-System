package com.hms.ui;

import com.hms.models.Billing;
import com.hms.services.BillingService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class BillingPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    private JTextField txtSearch;

    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnRefresh;
    private JButton btnSearch;

    public BillingPanel() {

        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 250));

        //----------------------------
        // Title
        //----------------------------

        JLabel title =
                new JLabel("Billing Management");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28));

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20));

        //----------------------------
        // Search
        //----------------------------

        JPanel searchPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT));

        txtSearch =
                new JTextField(20);

        btnSearch =
                new JButton(
                        "Search Patient ID");

        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        JPanel topPanel =
                new JPanel(
                        new BorderLayout());

        topPanel.add(
                title,
                BorderLayout.WEST);

        topPanel.add(
                searchPanel,
                BorderLayout.EAST);

        add(topPanel,
                BorderLayout.NORTH);

        //----------------------------
        // Toolbar
        //----------------------------

        btnAdd =
                new JButton("Add");

        btnDelete =
                new JButton("Delete");

        btnRefresh =
                new JButton("Refresh");

        JPanel toolbar =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT));

        toolbar.add(btnAdd);
        toolbar.add(btnDelete);
        toolbar.add(btnRefresh);

        //----------------------------
        // Table
        //----------------------------

        model =
                new DefaultTableModel();

        model.setColumnIdentifiers(
                new Object[]{
                        "Bill ID",
                        "Patient ID",
                        "Bill Date",
                        "Amount",
                        "Payment Status"
                });

        table =
                new JTable(model);

        table.setRowHeight(25);

        JScrollPane scroll =
                new JScrollPane(table);

        JPanel center =
                new JPanel(
                        new BorderLayout());

        center.add(
                toolbar,
                BorderLayout.NORTH);

        center.add(
                scroll,
                BorderLayout.CENTER);

        add(center,
                BorderLayout.CENTER);

        //----------------------------
        // Events
        //----------------------------

        btnRefresh.addActionListener(
                e -> loadBills());

        btnAdd.addActionListener(
                e -> addBill());

        btnDelete.addActionListener(
                e -> deleteBill());

        btnSearch.addActionListener(
                e -> searchBills());

        txtSearch.addActionListener(
                e -> searchBills());

        loadBills();
    }

    //------------------------------------------------

    private void loadBills() {

        BillingService service =
                new BillingService();

        List<Billing> bills =
                service.getAllBills();

        model.setRowCount(0);

        for (Billing b : bills) {

            model.addRow(
                    new Object[]{
                            b.getBillId(),
                            b.getPatientId(),
                            b.getBillDate(),
                            b.getTotalAmount(),
                            b.getPaymentStatus()
                    });
        }
    }

    //------------------------------------------------

    private void addBill() {

        try {

            Billing bill =
                    new Billing();

            bill.setPatientId(
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Patient ID")));

            bill.setBillDate(
                    LocalDate.parse(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Bill Date (yyyy-mm-dd)")));

            bill.setTotalAmount(
                    new BigDecimal(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Amount")));

            bill.setPaymentStatus(
                    JOptionPane.showInputDialog(
                            this,
                            "Payment Status"));

            BillingService service =
                    new BillingService();

            if (service.createBill(bill)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Bill added successfully.");

                loadBills();
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid input.");
        }
    }

    //------------------------------------------------

    private void deleteBill() {

        int row =
                table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a bill.");

            return;
        }

        int billId =
                (Integer)
                        model.getValueAt(
                                row,
                                0);

        BillingService service =
                new BillingService();

        if (service.deleteBill(
                billId)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Bill deleted.");

            loadBills();
        }
    }

    //------------------------------------------------

    private void searchBills() {

        try {

            int patientId =
                    Integer.parseInt(
                            txtSearch.getText());

            BillingService service =
                    new BillingService();

            List<Billing> bills =
                    service.searchBills(
                            patientId);

            model.setRowCount(0);

            for (Billing b : bills) {

                model.addRow(
                        new Object[]{
                                b.getBillId(),
                                b.getPatientId(),
                                b.getBillDate(),
                                b.getTotalAmount(),
                                b.getPaymentStatus()
                        });
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid Patient ID.");
        }
    }
}