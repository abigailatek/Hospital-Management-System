package com.hms.ui;

import com.hms.utils.Theme;
import com.hms.models.Inventory;
import com.hms.services.InventoryService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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
        JTextField unitPrice = field();
        JTextField txtSearch = new JTextField(20);
        JButton btnSearch = button("Search");
        JButton btnRefresh =button("Refresh");

        addRow(formPanel, gbc, 0, "Medicine ID:", medicineId, "Medicine Name:", medicineName);
        addRow(formPanel, gbc, 1, "Category:", category, "Quantity:", quantity);
        addRow(formPanel, gbc, 2, "Supplier:", supplier, "Expiry Date:", expiryDate);
        addRow(formPanel, gbc, 3, "Unit Price:", unitPrice,"", new JLabel());
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5)); buttons.setBackground(Color.WHITE);

        JButton add = button("Add Medicine");
        JButton update = button("Update");
        JButton clear = button("Clear");
        JButton delete = button("Delete");

        buttons.add(add);
        buttons.add(update);
        buttons.add(clear);
        buttons.add(delete);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        formPanel.add(buttons, gbc);
        JPanel searchPanel =
        new JPanel( new FlowLayout( FlowLayout.RIGHT));

       searchPanel.setBackground( Color.WHITE);

       searchPanel.add( new JLabel("Search:"));

      searchPanel.add(txtSearch);
      searchPanel.add(btnSearch);
      searchPanel.add(btnRefresh);

      gbc.gridx = 0;
      gbc.gridy = 5;
      gbc.gridwidth = 4;

     formPanel.add(searchPanel, gbc); 

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Medicine ID", "Medicine Name", "Category", "Quantity", "Supplier", "Expiry Date"}, 0
        );

        JTable table = new JTable(model);
        table.setRowHeight(28);
          loadItems(model);
       InventoryService service =
        new InventoryService();

add.addActionListener(e -> {

    try {

        Inventory item =
                new Inventory();

        item.setMedicineName(
                medicineName.getText());

        item.setQuantity(
                Integer.parseInt(
                        quantity.getText()));

        item.setUnitPrice(new BigDecimal( unitPrice.getText()));

        item.setExpiryDate(
                LocalDate.parse(
                        expiryDate.getText()));

        item.setSupplier(
                supplier.getText());

        if (service.addInventoryItem(item)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Medicine added.");

            loadItems(model);

            medicineId.setText("");
            medicineName.setText("");
            category.setText("");
            quantity.setText("");
            supplier.setText("");
            expiryDate.setText("");
        }

    } catch (Exception ex) {

        JOptionPane.showMessageDialog(
                this,
                ex.getMessage());
    }
});

        clear.addActionListener(e -> {
            medicineId.setText("");
            medicineName.setText("");
            category.setText("");
            quantity.setText("");
            supplier.setText("");
            expiryDate.setText("");
        });
        delete.addActionListener(e -> {

    int row =
            table.getSelectedRow();

    if (row < 0) {
        return;
    }

    int id =
            Integer.parseInt(
                    model.getValueAt(
                            row,
                            0).toString());

    service.deleteItem(id);

    loadItems(model);
});
           btnSearch.addActionListener(e -> {

    model.setRowCount(0);

    List<Inventory> items =
            service.searchItems(
                    txtSearch.getText());

    for (Inventory i : items) {

        model.addRow(
                new Object[]{
                        i.getItemId(),
                        i.getMedicineName(),
                        "",
                        i.getQuantity(),
                        i.getSupplier(),
                        i.getExpiryDate()
                });
    }
});    
btnRefresh.addActionListener(e -> {

    txtSearch.setText("");

    loadItems(model);
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
    private void loadItems(
        DefaultTableModel model) {

    model.setRowCount(0);

    InventoryService service =
            new InventoryService();

    List<Inventory> items =
            service.getAllItems();

    for (Inventory i : items) {

    
model.addRow(
        new Object[]{
                i.getItemId(),
                i.getMedicineName(),
                i.getQuantity(),
                i.getUnitPrice(),
                i.getSupplier(),
                i.getExpiryDate()
        });
    }
}
}