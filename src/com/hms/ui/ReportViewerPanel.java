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

        //--------------------------------
        // Title
        //--------------------------------

        JLabel title = new JLabel(reportTitle);

        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Theme.PRIMARY_GREEN);

        add(title, BorderLayout.NORTH);

        //--------------------------------
        // Toolbar
        //--------------------------------

        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton refreshBtn = new JButton("Refresh");
        JButton printBtn = new JButton("Print");
        JButton pdfBtn = new JButton("Export PDF");
        JButton excelBtn = new JButton("Export Excel");

        JTextField searchField = new JTextField(20);

        toolbar.add(refreshBtn);
        toolbar.add(printBtn);
        toolbar.add(pdfBtn);
        toolbar.add(excelBtn);

        toolbar.add(new JLabel("Search"));
        toolbar.add(searchField);

        add(toolbar, BorderLayout.SOUTH);

        //--------------------------------
        // Table
        //--------------------------------

        model = new DefaultTableModel();

        table = new JTable(model);

        table.setRowHeight(28);

        JScrollPane scroll = new JScrollPane(table);

        add(scroll, BorderLayout.CENTER);

        //--------------------------------
        // Load Reports
        //--------------------------------

        ReportService service = new ReportService();

        if (reportTitle.equals("Patient Report")) {
            table.setModel(service.getPatientReport());
        }

        //--------------------------------
        // Temporary Events
        //--------------------------------

        refreshBtn.addActionListener(e -> {

            if (reportTitle.equals("Patient Report")) {
                table.setModel(service.getPatientReport());
            }

        });

        printBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Print feature coming soon."
                )
        );

        pdfBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "PDF Export coming soon."
                )
        );

        excelBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Excel Export coming soon."
                )
        );
    }
}