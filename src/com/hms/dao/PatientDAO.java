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
}public Patient getPatientById(int patientId) {
    String sql = "SELECT * FROM Patients WHERE PatientID = ?";
    try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
    ) {
        ps.setInt(1, patientId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            Patient patient = new Patient();

            patient.setPatientID(rs.getInt("PatientID"));
            patient.setFirstName(rs.getString("FirstName"));
            patient.setLastName(rs.getString("LastName"));
            patient.setGender(rs.getString("Gender"));
            Date dob = rs.getDate("DateOfBirth");

if (dob != null) {
    patient.setDateOfBirth(dob.toLocalDate());
}
            patient.setPhone(rs.getString("Phone"));
            patient.setEmail(rs.getString("Email"));
            patient.setAddress(rs.getString("Address"));
            patient.setEmergencyContact(rs.getString("EmergencyContact"));

            return patient;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}public List<Patient> getAllPatients() {

    List<Patient> patients = new ArrayList<>();

    String sql = "SELECT * FROM Patients ORDER BY PatientID";

    try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
    ) {

        while (rs.next()) {

            Patient patient = new Patient();

            patient.setPatientID(rs.getInt("PatientID"));
            patient.setFirstName(rs.getString("FirstName"));
            patient.setLastName(rs.getString("LastName"));
            patient.setGender(rs.getString("Gender"));
            Date dob = rs.getDate("DateOfBirth");

if (dob != null) {
    patient.setDateOfBirth(dob.toLocalDate());
}
            patient.setPhone(rs.getString("Phone"));
            patient.setEmail(rs.getString("Email"));
            patient.setAddress(rs.getString("Address"));
            patient.setEmergencyContact(rs.getString("EmergencyContact"));

            patients.add(patient);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return patients;
}public boolean updatePatient(Patient patient) {

    String sql = """
        UPDATE Patients
        SET
            FirstName = ?,
            LastName = ?,
            Gender = ?,
            DateOfBirth = ?,
            Phone = ?,
            Email = ?,
            Address = ?,
            EmergencyContact = ?
        WHERE PatientID = ?
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
        ps.setInt(9, patient.getPatientID());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}public boolean deletePatient(int patientId) {

    String sql = "DELETE FROM Patients WHERE PatientID = ?";

    try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
    ) {

        ps.setInt(1, patientId);

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}public List<Patient> searchPatients(String keyword) {

    List<Patient> patients = new ArrayList<>();

    String sql = """
        SELECT *
        FROM Patients
        WHERE
            FirstName LIKE ?
            OR LastName LIKE ?
            OR Phone LIKE ?
            OR Email LIKE ?
        """;

    try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
    ) {

        String search = "%" + keyword + "%";

        ps.setString(1, search);
        ps.setString(2, search);
        ps.setString(3, search);
        ps.setString(4, search);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Patient patient = new Patient();

            patient.setPatientID(rs.getInt("PatientID"));
            patient.setFirstName(rs.getString("FirstName"));
            patient.setLastName(rs.getString("LastName"));
            patient.setGender(rs.getString("Gender"));
            Date dob = rs.getDate("DateOfBirth");

if (dob != null) {
    patient.setDateOfBirth(dob.toLocalDate());
}
            patient.setPhone(rs.getString("Phone"));
            patient.setEmail(rs.getString("Email"));
            patient.setAddress(rs.getString("Address"));
            patient.setEmergencyContact(rs.getString("EmergencyContact"));
            patients.add(patient);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return patients;
}


public List<Patient> getAllPatients1() {

    List<Patient> patients = new ArrayList<>();

    String sql = "SELECT * FROM Patients";

    try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
    ) {

        while (rs.next()) {

            Patient patient = new Patient();

            patient.setPatientID(rs.getInt("PatientID"));
            patient.setFirstName(rs.getString("FirstName"));
            patient.setLastName(rs.getString("LastName"));
            patient.setGender(rs.getString("Gender"));
            Date dob = rs.getDate("DateOfBirth");

if (dob != null) {
    patient.setDateOfBirth(dob.toLocalDate());
}
            patient.setPhone(rs.getString("Phone"));
            patient.setEmail(rs.getString("Email"));
            patient.setAddress(rs.getString("Address"));
            patient.setEmergencyContact(rs.getString("EmergencyContact"));

            patients.add(patient);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return patients;
}
}
