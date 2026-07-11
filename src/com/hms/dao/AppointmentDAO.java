package com.hms.dao;

import com.hms.database.DatabaseConnection;
import com.hms.models.Appointment;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    // CREATE
    public boolean addAppointment(Appointment appointment) {

        String sql = """
            INSERT INTO Appointments
            (
                PatientID,
                DoctorID,
                AppointmentDate,
                AppointmentTime,
                Status,
                Reason
            )
            VALUES (?,?,?,?,?,?)
            """;

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, appointment.getPatientId());
            ps.setInt(2, appointment.getDoctorId());
            ps.setDate(3, Date.valueOf(appointment.getAppointmentDate()));
            ps.setTime(4, Time.valueOf(appointment.getAppointmentTime()));
            ps.setString(5, appointment.getStatus());
            ps.setString(6, appointment.getReason());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // READ BY ID
    public Appointment getAppointmentById(int appointmentId) {

        String sql = "SELECT * FROM Appointments WHERE AppointmentID=?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, appointmentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // READ ALL
    public List<Appointment> getAllAppointments() {

        List<Appointment> list = new ArrayList<>();

        String sql = "SELECT * FROM Appointments ORDER BY AppointmentDate";

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

    // UPDATE
    public boolean updateAppointment(Appointment appointment) {

        String sql = """
            UPDATE Appointments
            SET
                PatientID=?,
                DoctorID=?,
                AppointmentDate=?,
                AppointmentTime=?,
                Status=?,
                Reason=?
            WHERE AppointmentID=?
            """;

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, appointment.getPatientId());
            ps.setInt(2, appointment.getDoctorId());
            ps.setDate(3, Date.valueOf(appointment.getAppointmentDate()));
            ps.setTime(4, Time.valueOf(appointment.getAppointmentTime()));
            ps.setString(5, appointment.getStatus());
            ps.setString(6, appointment.getReason());
            ps.setInt(7, appointment.getAppointmentId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // DELETE
    public boolean deleteAppointment(int appointmentId) {

        String sql = "DELETE FROM Appointments WHERE AppointmentID=?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, appointmentId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // SEARCH
    public List<Appointment> searchAppointments(int doctorId) {

    List<Appointment> list = new ArrayList<>();

    String sql =
            "SELECT * FROM Appointments WHERE DoctorID=?";

    try (
            Connection conn =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql)
    ) {

        ps.setInt(1, doctorId);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            list.add(mapResultSet(rs));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return list;
}

    // Helper
    private Appointment mapResultSet(ResultSet rs) throws SQLException {

        Appointment appointment = new Appointment();

        appointment.setAppointmentId(rs.getInt("AppointmentID"));
        appointment.setPatientId(rs.getInt("PatientID"));
        appointment.setDoctorId(rs.getInt("DoctorID"));
        appointment.setAppointmentDate(rs.getDate("AppointmentDate").toLocalDate());
        appointment.setAppointmentTime(rs.getTime("AppointmentTime").toLocalTime());
        appointment.setStatus(rs.getString("Status"));
        appointment.setReason(rs.getString("Reason"));

        return appointment;
    }public boolean isDoctorAvailable(int doctorId,
                                 LocalDate date,
                                 LocalTime time) {

    String sql = """
        SELECT COUNT(*)
        FROM Appointments
        WHERE DoctorID = ?
        AND AppointmentDate = ?
        AND AppointmentTime = ?
        """;

    try (
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)
    ) {

        ps.setInt(1, doctorId);
        ps.setDate(2, Date.valueOf(date));
        ps.setTime(3, Time.valueOf(time));

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt(1) == 0;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}
}