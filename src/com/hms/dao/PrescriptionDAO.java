package com.hms.dao;

import com.hms.database.DatabaseConnection;
import com.hms.models.Prescription;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<Prescription> getAllPrescriptions() {

        List<Prescription> prescriptions = new ArrayList<>();

        String sql = "SELECT * FROM Prescriptions";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Prescription p = new Prescription();

                p.setPrescriptionId(rs.getInt("PrescriptionID"));
                p.setRecordId(rs.getInt("RecordID"));
                p.setMedicineName(rs.getString("MedicineName"));
                p.setDosage(rs.getString("Dosage"));
                p.setDuration(rs.getString("Duration"));
                p.setNotes(rs.getString("Notes"));

                prescriptions.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prescriptions;
    }

    public boolean deletePrescription(int prescriptionId) {

        String sql =
                "DELETE FROM Prescriptions WHERE PrescriptionID=?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, prescriptionId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public List<Prescription> searchPrescriptions(
        int recordId) {

    List<Prescription> prescriptions =
            new ArrayList<>();

    String sql =
            "SELECT * FROM Prescriptions WHERE RecordID=?";

    try (
            Connection conn =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql)
    ) {

        ps.setInt(1, recordId);

        ResultSet rs =
                ps.executeQuery();

        while (rs.next()) {

            Prescription p =
                    new Prescription();

            p.setPrescriptionId(
                    rs.getInt("PrescriptionID"));

            p.setRecordId(
                    rs.getInt("RecordID"));

            p.setMedicineName(
                    rs.getString("MedicineName"));

            p.setDosage(
                    rs.getString("Dosage"));

            p.setDuration(
                    rs.getString("Duration"));

            p.setNotes(
                    rs.getString("Notes"));

            prescriptions.add(p);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return prescriptions;
}
    
}