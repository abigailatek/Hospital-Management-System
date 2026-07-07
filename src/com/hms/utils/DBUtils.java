package com.hms.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {

    private DBUtils() {
    }

    public static void close(Connection conn,
                             PreparedStatement ps,
                             ResultSet rs) {

        try {

            if (rs != null)
                rs.close();

            if (ps != null)
                ps.close();

            if (conn != null)
                conn.close();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }
}