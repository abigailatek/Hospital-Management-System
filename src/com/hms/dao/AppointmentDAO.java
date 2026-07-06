package com.hms.dao;

import com.hms.database.DatabaseConnection;
import com.hms.models.Appointment;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

public class AppointmentDAO {

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
                VALUES
                (?,?,?,?,?,?)
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

            return false;
        }
    }
}