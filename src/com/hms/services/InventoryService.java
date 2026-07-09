package com.hms.services;

import com.hms.dao.InventoryDAO;
import com.hms.models.Inventory;

public class InventoryService {

    private final InventoryDAO dao = new InventoryDAO();

    public boolean addInventoryItem(Inventory item) {

        if (item.getMedicineName() == null || item.getMedicineName().isBlank()) {
            throw new IllegalArgumentException("Medicine name cannot be empty.");
        }

        if (item.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }

        return dao.addItem(item);
    }
}