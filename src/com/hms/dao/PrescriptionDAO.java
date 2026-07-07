package com.hms.dao;

import com.hms.database.DatabaseConnection;
import com.hms.models.Prescription;

import java.sql.*;

public class PrescriptionDAO {
public boolean addPrescription(Prescription prescription) {

    String sql = """
        INSERT INTO Prescriptions
        (
            RecordID,
            MedicineName,
            Dosage,
            Duration,
            Notes
        )
        VALUES
        (?,?,?,?,?)
        """;

    try (
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)
    ) {

        ps.setInt(1, prescription.getRecordId());
        ps.setString(2, prescription.getMedicineName());
        ps.setString(3, prescription.getDosage());
        ps.setString(4, prescription.getDuration());
        ps.setString(5, prescription.getNotes());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}
}