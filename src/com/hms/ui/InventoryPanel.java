package com.hms.ui;

import com.hms.models.Inventory;
import com.hms.services.InventoryService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class InventoryPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private JTextField txtSearch;

    public InventoryPanel() {

        setLayout(new BorderLayout());

        JLabel title =
                new JLabel("Medical Store / Inventory");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28));

        add(title, BorderLayout.NORTH);

        JPanel toolbar = new JPanel();

        JButton btnAdd =
                new JButton("Add");

        JButton btnDelete =
                new JButton("Delete");

        JButton btnRefresh =
                new JButton("Refresh");

        txtSearch =
                new JTextField(15);

        JButton btnSearch =
                new JButton("Search");

        toolbar.add(btnAdd);
        toolbar.add(btnDelete);
        toolbar.add(btnRefresh);
        toolbar.add(txtSearch);
        toolbar.add(btnSearch);

        add(toolbar,
                BorderLayout.SOUTH);

        model =
                new DefaultTableModel();

        model.setColumnIdentifiers(
                new Object[]{
                        "Item ID",
                        "Medicine",
                        "Quantity",
                        "Unit Price",
                        "Expiry Date",
                        "Supplier"
                });

        table =
                new JTable(model);

        add(
                new JScrollPane(table),
                BorderLayout.CENTER);

        btnRefresh.addActionListener(
                e -> loadInventory());

        btnAdd.addActionListener(
                e -> addItem());

        btnDelete.addActionListener(
                e -> deleteItem());

        btnSearch.addActionListener(
                e -> searchItems());

        loadInventory();
    }

    private void loadInventory() {

        InventoryService service =
                new InventoryService();

        List<Inventory> items =
                service.getAllItems();

        model.setRowCount(0);

        for (Inventory i : items) {

            model.addRow(
                    new Object[]{
                            i.getItemId(),
                            i.getMedicineName(),
                            i.getQuantity(),
                            i.getUnitPrice(),
                            i.getExpiryDate(),
                            i.getSupplier()
                    });
        }
    }

    private void addItem() {

        try {

            Inventory item =
                    new Inventory();

            item.setMedicineName(
                    JOptionPane.showInputDialog(
                            this,
                            "Medicine Name"));

            item.setQuantity(
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Quantity")));

            item.setUnitPrice(
                    new BigDecimal(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Unit Price")));

            item.setExpiryDate(
                    LocalDate.parse(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Expiry Date (yyyy-mm-dd)")));

            item.setSupplier(
                    JOptionPane.showInputDialog(
                            this,
                            "Supplier"));

            InventoryService service =
                    new InventoryService();

            if (service.addInventoryItem(item)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Item added successfully.");

                loadInventory();
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid input.");
        }
    }

    private void deleteItem() {

        int row =
                table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select an item.");

            return;
        }

        int itemId =
                (Integer)
                        model.getValueAt(
                                row,
                                0);

        InventoryService service =
                new InventoryService();

        if (service.deleteItem(itemId)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Item deleted.");

            loadInventory();
        }
    }

    private void searchItems() {

        String keyword =
                txtSearch.getText();

        InventoryService service =
                new InventoryService();

        List<Inventory> items =
                service.searchItems(keyword);

        model.setRowCount(0);

        for (Inventory i : items) {

            model.addRow(
                    new Object[]{
                            i.getItemId(),
                            i.getMedicineName(),
                            i.getQuantity(),
                            i.getUnitPrice(),
                            i.getExpiryDate(),
                            i.getSupplier()
                    });
        }
    }
}