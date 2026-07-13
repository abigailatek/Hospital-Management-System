package com.hms.dao;

import com.hms.database.DatabaseConnection;
import com.hms.models.MedicalRecord;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
  public List<MedicalRecord> getAllMedicalRecords() {

    List<MedicalRecord> records =
            new ArrayList<>();

    String sql =
            "SELECT * FROM MedicalRecords ORDER BY RecordDate DESC";

    try (
            Connection conn =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery()
    ) {

        while (rs.next()) {

            MedicalRecord record =
                    new MedicalRecord();

            record.setRecordId(
                    rs.getInt("RecordID"));

            record.setPatientId(
                    rs.getInt("PatientID"));

            record.setDoctorId(
                    rs.getInt("DoctorID"));

            record.setDiagnosis(
                    rs.getString("Diagnosis"));

            record.setTreatment(
                    rs.getString("Treatment"));

            record.setAllergies(
                    rs.getString("Allergies"));

            record.setChronicConditions(
                    rs.getString(
                            "ChronicConditions"));

            record.setRecordDate(
                    rs.getDate(
                            "RecordDate")
                            .toLocalDate());

            records.add(record);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return records;
}
     public boolean updateMedicalRecord(
        MedicalRecord record) {

    String sql = """
            UPDATE MedicalRecords
            SET
            PatientID=?,
            DoctorID=?,
            Diagnosis=?,
            Treatment=?,
            Allergies=?,
            ChronicConditions=?,
            RecordDate=?
            WHERE RecordID=?
            """;

    try (
            Connection conn =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql)
    ) {

        ps.setInt(1,
                record.getPatientId());

        ps.setInt(2,
                record.getDoctorId());

        ps.setString(3,
                record.getDiagnosis());

        ps.setString(4,
                record.getTreatment());

        ps.setString(5,
                record.getAllergies());

        ps.setString(6,
                record.getChronicConditions());

        ps.setDate(7,
                Date.valueOf(
                        record.getRecordDate()));

        ps.setInt(8,
                record.getRecordId());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}
 public boolean deleteMedicalRecord(
        int recordId) {

    String sql =
            "DELETE FROM MedicalRecords WHERE RecordID=?";

    try (
            Connection conn =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql)
    ) {

        ps.setInt(1,
                recordId);

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}
     public List<MedicalRecord>
searchMedicalRecords(int patientId) {

    List<MedicalRecord> records =
            new ArrayList<>();

    String sql =
            "SELECT * FROM MedicalRecords WHERE PatientID=?";

    try (
            Connection conn =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql)
    ) {

        ps.setInt(1,
                patientId);

        ResultSet rs =
                ps.executeQuery();

        while (rs.next()) {

            MedicalRecord record =
                    new MedicalRecord();

            record.setRecordId(
                    rs.getInt("RecordID"));

            record.setPatientId(
                    rs.getInt("PatientID"));

            record.setDoctorId(
                    rs.getInt("DoctorID"));

            record.setDiagnosis(
                    rs.getString("Diagnosis"));

            record.setTreatment(
                    rs.getString("Treatment"));

            record.setAllergies(
                    rs.getString("Allergies"));

            record.setChronicConditions(
                    rs.getString(
                            "ChronicConditions"));

            record.setRecordDate(
                    rs.getDate(
                            "RecordDate")
                            .toLocalDate());

            records.add(record);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return records;
}
}