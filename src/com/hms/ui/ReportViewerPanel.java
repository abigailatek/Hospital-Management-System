package com.hms.ui;

import com.hms.services.ReportService;
import com.hms.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReportViewerPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    public ReportViewerPanel(String reportTitle) {

        setLayout(new BorderLayout(15, 15));
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel(reportTitle);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Theme.PRIMARY_GREEN);

        add(title, BorderLayout.NORTH);

        JPanel toolbar =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT));

        JButton refreshBtn =
                new JButton("Refresh");

        JButton printBtn =
                new JButton("Print");

        JButton pdfBtn =
                new JButton("Export PDF");

        JButton excelBtn =
                new JButton("Export Excel");

        JTextField searchField =
                new JTextField(20);

        toolbar.add(refreshBtn);
        toolbar.add(printBtn);
        toolbar.add(pdfBtn);
        toolbar.add(excelBtn);
        toolbar.add(new JLabel("Search"));
        toolbar.add(searchField);

        add(toolbar, BorderLayout.SOUTH);

        model = new DefaultTableModel();

        table = new JTable(model);
        table.setRowHeight(28);

        JScrollPane scroll =
                new JScrollPane(table);

        add(scroll, BorderLayout.CENTER);

        ReportService service =
                new ReportService();

        loadReport(reportTitle, service);

        refreshBtn.addActionListener(
                e -> loadReport(
                        reportTitle,
                        service));

        printBtn.addActionListener(
                e ->
                        JOptionPane.showMessageDialog(
                                this,
                                "Print feature coming soon."
                        ));

        pdfBtn.addActionListener(
                e ->
                        JOptionPane.showMessageDialog(
                                this,
                                "PDF Export coming soon."
                        ));

        excelBtn.addActionListener(
                e ->
                        JOptionPane.showMessageDialog(
                                this,
                                "Excel Export coming soon."
                        ));
    }

    private void loadReport(
            String reportTitle,
            ReportService service) {

        switch (reportTitle) {

            case "Patient Report":
                table.setModel(
                        service.getPatientReport());
                break;

            case "Doctor Report":
                table.setModel(
                        service.getDoctorReport());
                break;

            case "Appointment Report":
                table.setModel(
                        service.getAppointmentReport());
                break;

            case "Billing Report":
                table.setModel(
                        service.getBillingReport());
                break;

            case "Payment Report":
                table.setModel(
                        service.getPaymentReport());
                break;

            case "Inventory Report":
                table.setModel(
                        service.getInventoryReport());
                break;

            case "Staff Report":
                table.setModel(
                        service.getStaffReport());
                break;

            case "Laboratory Report":
                table.setModel(
                        service.getLabReport());
                break;

            default:
                table.setModel(
                        new DefaultTableModel());
        }
    }
}