package com.hms.dao;

import com.hms.database.DatabaseConnection;
import com.hms.models.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StaffDAO {

    public boolean addStaff(Staff staff) {

        String sql = """
            INSERT INTO Staff
            (
                UserID,
                FirstName,
                LastName,
                Position,
                Phone,
                Email,
                Salary
            )
            VALUES
            (?,?,?,?,?,?,?)
            """;

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, staff.getUserId());
            ps.setString(2, staff.getFirstName());
            ps.setString(3, staff.getLastName());
            ps.setString(4, staff.getPosition());
            ps.setString(5, staff.getPhone());
            ps.setString(6, staff.getEmail());
            ps.setBigDecimal(7, staff.getSalary());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}