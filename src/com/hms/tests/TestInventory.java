package com.hms.tests;

import com.hms.models.Inventory;
import com.hms.services.InventoryService;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestInventory {

    public static void main(String[] args) {

        Inventory item = new Inventory();

        item.setMedicineName("Paracetamol 500mg");
        item.setQuantity(500);
        item.setUnitPrice(new BigDecimal("1200.00"));
        item.setExpiryDate(LocalDate.of(2028, 12, 31));
        item.setSupplier("LifeCare Medical Suppliers Ltd");

        InventoryService service = new InventoryService();

        if (service.addInventoryItem(item)) {
            System.out.println("Inventory item added successfully!");
        } else {
            System.out.println("Failed to add inventory item.");
        }
    }
}