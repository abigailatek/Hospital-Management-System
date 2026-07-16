package com.hms.services;

import com.hms.dao.InventoryDAO;
import com.hms.models.Inventory;

import java.time.LocalDate;
import java.util.List;

public class InventoryService {

    private final InventoryDAO dao =
            new InventoryDAO();

    public boolean addItem(Inventory item) {

        if (item.getMedicineName() == null ||
                item.getMedicineName().isBlank()) {
            throw new IllegalArgumentException(
                    "Medicine name cannot be empty.");
        }

        if (item.getQuantity() < 0) {
            throw new IllegalArgumentException(
                    "Quantity cannot be negative.");
        }

        return dao.addItem(item);
    }

    public boolean addInventoryItem(Inventory item) {
        return addItem(item);
    }

    public List<Inventory> getAllItems() {
        return dao.getAllItems();
    }

    public boolean deleteItem(int id) {
        return dao.deleteItem(id);
    }

    public List<Inventory> searchItems(String keyword) {
        return dao.searchItems(keyword);
    }

    public boolean updateItem(Inventory item) {
        return dao.updateItem(item);
    }

    public List<Inventory> getLowStockItems() {
        return dao.getAllItems()
                .stream()
                .filter(i -> i.getQuantity() <= 5)
                .toList();
    }

    public List<Inventory> getExpiringItems() {
        return dao.getAllItems()
                .stream()
                .filter(i ->
                        i.getExpiryDate() != null &&
                        i.getExpiryDate().isBefore(
                                LocalDate.now().plusDays(30)))
                .toList();
    }
}