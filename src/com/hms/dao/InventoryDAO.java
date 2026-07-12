package com.hms.dao;
import com.hms.database.DatabaseConnection;
import com.hms.models.Inventory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class InventoryDAO {

    // CREATE
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
                VALUES (?,?,?,?,?)
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

    // READ ALL
    public List<Inventory> getAllItems() {

        List<Inventory> list = new ArrayList<>();

        String sql =
                "SELECT * FROM Inventory ORDER BY ItemID";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                list.add(mapResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // DELETE
    public boolean deleteItem(int itemId) {

        String sql =
                "DELETE FROM Inventory WHERE ItemID=?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, itemId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // SEARCH
    public List<Inventory> searchItems(String keyword) {

        List<Inventory> list = new ArrayList<>();

        String sql =
                "SELECT * FROM Inventory WHERE MedicineName LIKE ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    private Inventory mapResultSet(ResultSet rs)
            throws SQLException {

        Inventory item = new Inventory();

        item.setItemId(
                rs.getInt("ItemID"));

        item.setMedicineName(
                rs.getString("MedicineName"));

        item.setQuantity(
                rs.getInt("Quantity"));

        item.setUnitPrice(
                rs.getBigDecimal("UnitPrice"));

        Date date =
                rs.getDate("ExpiryDate");

        if (date != null) {
            item.setExpiryDate(
                    date.toLocalDate());
        }

        item.setSupplier(
                rs.getString("Supplier"));

        return item;
    }

    public boolean addInventoryItem(Inventory item) {
        String sql = """
                INSERT INTO Inventory
                (
                    MedicineName,
                    Quantity,
                    UnitPrice,
                    ExpiryDate,
                    Supplier
                )
                VALUES (?,?,?,?,?)
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