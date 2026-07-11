package com.hms.services;

import com.hms.dao.InventoryDAO;
import com.hms.models.Inventory;

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

    public List<Inventory> getAllItems() {
        return dao.getAllItems();
    }

    public boolean deleteItem(int id) {
        return dao.deleteItem(id);
    }

    public List<Inventory> searchItems(String keyword) {
        return dao.searchItems(keyword);
    }
}