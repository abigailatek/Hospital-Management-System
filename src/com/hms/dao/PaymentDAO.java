package com.hms.dao;

import com.hms.database.DatabaseConnection;
import com.hms.models.Payment;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentDAO {

    public boolean addPayment(Payment payment) {

        String sql = """
            INSERT INTO Payments
            (
                BillID,
                Amount,
                PaymentMethod,
                PaymentDate
            )
            VALUES
            (?,?,?,?)
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
}