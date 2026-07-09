package com.hms.dao;

import com.hms.database.DatabaseConnection;
import com.hms.models.MedicalRecord;

import java.sql.*;

public class MedicalRecordDAO {
public boolean addMedicalRecord(MedicalRecord record) {

    String sql = """
        INSERT INTO MedicalRecords
        (
            PatientID,
            DoctorID,
            Diagnosis,
            Treatment,
            Allergies,
            ChronicConditions,
            RecordDate
        )
        VALUES
        (?,?,?,?,?,?,?)
        """;

    try (
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)
    ) {

        ps.setInt(1, record.getPatientId());
        ps.setInt(2, record.getDoctorId());
        ps.setString(3, record.getDiagnosis());
        ps.setString(4, record.getTreatment());
        ps.setString(5, record.getAllergies());
        ps.setString(6, record.getChronicConditions());
        ps.setDate(7, Date.valueOf(record.getRecordDate()));

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}
}