package com.hms.dao;

import com.hms.database.DatabaseConnection;
import com.hms.models.Billing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillingDAO {

    // CREATE
    public boolean addBill(Billing bill) {

        String sql = """
                INSERT INTO Bills
                (
                    PatientID,
                    BillDate,
                    TotalAmount,
                    PaymentStatus
                )
                VALUES (?,?,?,?)
                """;

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, bill.getPatientId());
            ps.setDate(2, Date.valueOf(bill.getBillDate()));
            ps.setBigDecimal(3, bill.getTotalAmount());
            ps.setString(4, bill.getPaymentStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // READ ALL
    public List<Billing> getAllBills() {

        List<Billing> list = new ArrayList<>();

        String sql =
                "SELECT * FROM Bills ORDER BY BillID";

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
    public boolean deleteBill(int billId) {

        String sql =
                "DELETE FROM Bills WHERE BillID=?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, billId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // SEARCH BY PATIENT ID
    public List<Billing> searchBills(int patientId) {

        List<Billing> list = new ArrayList<>();

        String sql =
                "SELECT * FROM Bills WHERE PatientID=?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, patientId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // HELPER METHOD
    private Billing mapResultSet(ResultSet rs)
            throws SQLException {

        Billing bill = new Billing();

        bill.setBillId(
                rs.getInt("BillID"));

        bill.setPatientId(
                rs.getInt("PatientID"));

        Date date =
                rs.getDate("BillDate");

        if (date != null) {
            bill.setBillDate(
                    date.toLocalDate());
        }

        bill.setTotalAmount(
                rs.getBigDecimal("TotalAmount"));

        bill.setPaymentStatus(
                rs.getString("PaymentStatus"));

        return bill;
    }
}