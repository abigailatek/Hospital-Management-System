 package com.hms.dao;

import com.hms.database.DatabaseConnection;
import com.hms.models.Patient;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
public class PatientDAO {
    public boolean addPatient(Patient patient) {

    String sql = """
        INSERT INTO Patients
        (
            FirstName,
            LastName,
            Gender,
            DateOfBirth,
            Phone,
            Email,
            Address,
            EmergencyContact
        )
        VALUES
        (?,?,?,?,?,?,?,?)
        """;

    try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
    ) {

        ps.setString(1, patient.getFirstName());
        ps.setString(2, patient.getLastName());
        ps.setString(3, patient.getGender());
        ps.setDate(4, Date.valueOf(patient.getDateOfBirth()));
        ps.setString(5, patient.getPhone());
        ps.setString(6, patient.getEmail());
        ps.setString(7, patient.getAddress());
        ps.setString(8, patient.getEmergencyContact());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {

        e.printStackTrace();

        return false;
    }
}
}
