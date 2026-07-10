package com.hms.dao;

import com.hms.database.DatabaseConnection;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReportDAO {

    public DefaultTableModel getPatientReport() {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Gender");
        model.addColumn("Phone");
        model.addColumn("Email");

        String sql = """
                SELECT PatientID,
                       FirstName,
                       LastName,
                       Gender,
                       Phone,
                       Email
                FROM Patients
                ORDER BY PatientID
                """;

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                model.addRow(new Object[]{
                        rs.getInt("PatientID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Gender"),
                        rs.getString("Phone"),
                        rs.getString("Email")
                });

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }

}