package com.hms.dao;

import com.hms.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardDAO {

    private int getCount(String tableName) {

        String sql = "SELECT COUNT(*) FROM " + tableName;

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int getPatientCount() {
        return getCount("Patients");
    }

    public int getDoctorCount() {
        return getCount("Doctors");
    }

    public int getAppointmentCount() {
        return getCount("Appointments");
    }

    public int getBillCount() {
        return getCount("Bills");
    }

    public int getLabTestCount() {
        return getCount("LabTests");
    }

    public int getStaffCount() {
        return getCount("Staff");
    }

    public int getInventoryCount() {
        return getCount("Inventory");
    }

    public int getPaymentCount() {
        return getCount("Payments");
    }
}