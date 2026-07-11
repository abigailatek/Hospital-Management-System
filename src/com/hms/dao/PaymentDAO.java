package com.hms.dao;

import com.hms.database.DatabaseConnection;
import com.hms.models.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    // CREATE
    public boolean addPayment(Payment payment) {

        String sql = """
                INSERT INTO Payments
                (
                    BillID,
                    Amount,
                    PaymentMethod,
                    PaymentDate
                )
                VALUES (?,?,?,?)
                """;

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, payment.getBillId());
            ps.setDouble(2, payment.getAmount());
            ps.setString(3, payment.getPaymentMethod());
            ps.setDate(4, Date.valueOf(payment.getPaymentDate()));

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // READ ALL
    public List<Payment> getAllPayments() {

        List<Payment> list = new ArrayList<>();

        String sql =
                "SELECT * FROM Payments ORDER BY PaymentID";

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
    public boolean deletePayment(int paymentId) {

        String sql =
                "DELETE FROM Payments WHERE PaymentID=?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, paymentId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // SEARCH
    public List<Payment> searchPayments(int billId) {

        List<Payment> list = new ArrayList<>();

        String sql =
                "SELECT * FROM Payments WHERE BillID=?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, billId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    private Payment mapResultSet(ResultSet rs)
            throws SQLException {

        Payment payment = new Payment();

        payment.setPaymentId(
                rs.getInt("PaymentID"));

        payment.setBillId(
                rs.getInt("BillID"));

        payment.setAmount(
                rs.getDouble("Amount"));

        payment.setPaymentMethod(
                rs.getString("PaymentMethod"));

        Date date =
                rs.getDate("PaymentDate");

        if (date != null) {
            payment.setPaymentDate(
                    date.toLocalDate());
        }

        return payment;
    }
}