package com.hms.dao;

import com.hms.database.DatabaseConnection;
import com.hms.models.Attendance;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AttendanceDAO {

    public boolean addAttendance(Attendance attendance) {

        String sql = """
            INSERT INTO Attendance
            (
                StaffID,
                AttendanceDate,
                Status
            )
            VALUES
            (?,?,?)
            """;

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, attendance.getStaffId());
            ps.setDate(2, Date.valueOf(attendance.getAttendanceDate()));
            ps.setString(3, attendance.getStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}