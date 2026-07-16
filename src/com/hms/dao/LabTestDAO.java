package com.hms.dao;

import com.hms.database.DatabaseConnection;
import com.hms.models.LabTest;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LabTestDAO {

    public boolean addLabTest(LabTest test) {

        String sql = """
            INSERT INTO LabTests
            (
                PatientID,
                DoctorID,
                TestName,
                Result,
                TestDate,
                Status
            )
            VALUES
            (?,?,?,?,?,?)
            """;

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, test.getPatientId());
            ps.setInt(2, test.getDoctorId());
            ps.setString(3, test.getTestName());
            ps.setString(4, test.getResult());
            ps.setDate(5, Date.valueOf(test.getTestDate()));
            ps.setString(6, test.getStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
         public List<LabTest> getAllLabTests() {

    List<LabTest> tests = new ArrayList<>();

    String sql =
            "SELECT * FROM LabTests ORDER BY TestDate DESC";

    try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
    ) {

        while (rs.next()) {

            LabTest test = new LabTest();

            test.setTestId(
                    rs.getInt("TestID"));

            test.setPatientId(
                    rs.getInt("PatientID"));

            test.setDoctorId(
                    rs.getInt("DoctorID"));

            test.setTestName(
                    rs.getString("TestName"));

            test.setResult(
                    rs.getString("Result"));

            test.setTestDate(
                    rs.getDate("TestDate")
                            .toLocalDate());

            test.setStatus(
                    rs.getString("Status"));

            tests.add(test);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return tests;
}
      public boolean deleteLabTest(int id) {

    String sql =
            "DELETE FROM LabTests WHERE TestID=?";

    try (
            Connection conn =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql)
    ) {

        ps.setInt(1, id);

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}
   public List<LabTest> searchLabTests(
        int patientId) {

    List<LabTest> tests =
            new ArrayList<>();

    String sql =
            "SELECT * FROM LabTests WHERE PatientID=?";

    try (
            Connection conn =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql)
    ) {

        ps.setInt(1, patientId);

        ResultSet rs =
                ps.executeQuery();

        while (rs.next()) {

            LabTest test =
                    new LabTest();

            test.setTestId(
                    rs.getInt("TestID"));

            test.setPatientId(
                    rs.getInt("PatientID"));

            test.setDoctorId(
                    rs.getInt("DoctorID"));

            test.setTestName(
                    rs.getString("TestName"));

            test.setResult(
                    rs.getString("Result"));

            test.setTestDate(
                    rs.getDate("TestDate")
                            .toLocalDate());

            test.setStatus(
                    rs.getString("Status"));

            tests.add(test);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return tests;
}
      public int getLabTestCount() {

    String sql =
            "SELECT COUNT(*) FROM LabTests";

    try (
            Connection conn =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery()
    ) {

        if (rs.next()) {
            return rs.getInt(1);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return 0;
}
    public List<LabTest> getPendingTests() {
        return null;

}
}