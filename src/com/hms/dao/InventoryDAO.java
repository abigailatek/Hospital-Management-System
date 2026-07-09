package com.hms.dao;

import com.hms.database.DatabaseConnection;
import com.hms.models.Inventory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryDAO {

    public boolean addItem(Inventory item) {

        String sql = """
            INSERT INTO Inventory
            (
                MedicineName,
                Quantity,
                UnitPrice,
                ExpiryDate,
                Supplier
            )
            VALUES
            (?,?,?,?,?)
            """;

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, item.getMedicineName());
            ps.setInt(2, item.getQuantity());
            ps.setBigDecimal(3, item.getUnitPrice());
            ps.setDate(4, Date.valueOf(item.getExpiryDate()));
            ps.setString(5, item.getSupplier());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}