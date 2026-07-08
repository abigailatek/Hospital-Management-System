package com.hms.dao;

import com.hms.database.DatabaseConnection;
import com.hms.models.Billing;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BillingDAO {

    public boolean addBill(Billing bill) {

        String sql = """
            INSERT INTO Bills
            (
                PatientID,
                BillDate,
                TotalAmount,
                PaymentStatus
            )
            VALUES
            (?,?,?,?)
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

}