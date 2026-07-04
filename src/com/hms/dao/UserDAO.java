package com.hms.dao;

import com.hms.database.DatabaseConnection;
import com.hms.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public User authenticate(String username, String password) {

        String sql = """
                SELECT u.UserID,
                       u.Username,
                       u.PasswordHash,
                       u.RoleID,
                       r.RoleName,
                       u.CreatedAt
                FROM Users u
                INNER JOIN Roles r
                ON u.RoleID = r.RoleID
                WHERE u.Username = ?
                AND u.PasswordHash = ?
                """;

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                User user = new User();

                user.setUserID(rs.getInt("UserID"));
                user.setUsername(rs.getString("Username"));
                user.setPasswordHash(rs.getString("PasswordHash"));
                user.setRoleID(rs.getInt("RoleID"));
                user.setRoleName(rs.getString("RoleName"));

                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}