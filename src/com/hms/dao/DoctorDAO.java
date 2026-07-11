package com.hms.dao;

import com.hms.database.DatabaseConnection;
import com.hms.models.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    public boolean addDoctor(Doctor doctor) {

        String sql = """
                INSERT INTO Doctors
                (
                    UserID,
                    FirstName,
                    LastName,
                    Specialization,
                    Phone,
                    Email
                )
                VALUES
                (?,?,?,?,?,?)
                """;

        try (
                Connection conn =
                        DatabaseConnection.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setInt(1, doctor.getUserID());
            ps.setString(2, doctor.getFirstName());
            ps.setString(3, doctor.getLastName());
            ps.setString(4, doctor.getSpecialization());
            ps.setString(5, doctor.getPhone());
            ps.setString(6, doctor.getEmail());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Doctor> getAllDoctors() {

        List<Doctor> doctors =
                new ArrayList<>();

        String sql =
                "SELECT * FROM Doctors ORDER BY DoctorID";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql);
                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                Doctor doctor =
                        new Doctor();

                doctor.setDoctorID(
                        rs.getInt("DoctorID"));

                doctor.setUserID(
                        rs.getInt("UserID"));

                doctor.setFirstName(
                        rs.getString("FirstName"));

                doctor.setLastName(
                        rs.getString("LastName"));

                doctor.setSpecialization(
                        rs.getString("Specialization"));

                doctor.setPhone(
                        rs.getString("Phone"));

                doctor.setEmail(
                        rs.getString("Email"));

                doctors.add(doctor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctors;
    }

    public boolean deleteDoctor(int doctorID) {

        String sql =
                "DELETE FROM Doctors WHERE DoctorID=?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setInt(1, doctorID);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean updateDoctor(Doctor doctor) {

    String sql = """
            UPDATE Doctors
            SET
                UserID = ?,
                FirstName = ?,
                LastName = ?,
                Specialization = ?,
                Phone = ?,
                Email = ?
            WHERE DoctorID = ?
            """;

    try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
    ) {

        ps.setInt(1, doctor.getUserID());
        ps.setString(2, doctor.getFirstName());
        ps.setString(3, doctor.getLastName());
        ps.setString(4, doctor.getSpecialization());
        ps.setString(5, doctor.getPhone());
        ps.setString(6, doctor.getEmail());
        ps.setInt(7, doctor.getDoctorID());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}
public List<Doctor> searchDoctors(String keyword) {

    List<Doctor> doctors = new ArrayList<>();

    String sql = """
            SELECT *
            FROM Doctors
            WHERE
                FirstName LIKE ?
                OR LastName LIKE ?
                OR Specialization LIKE ?
                OR Phone LIKE ?
                OR Email LIKE ?
            ORDER BY DoctorID
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
        ps.setString(5, search);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Doctor doctor = new Doctor();

            doctor.setDoctorID(rs.getInt("DoctorID"));
            doctor.setUserID(rs.getInt("UserID"));
            doctor.setFirstName(rs.getString("FirstName"));
            doctor.setLastName(rs.getString("LastName"));
            doctor.setSpecialization(rs.getString("Specialization"));
            doctor.setPhone(rs.getString("Phone"));
            doctor.setEmail(rs.getString("Email"));

            doctors.add(doctor);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return doctors;
}
}