package com.hms.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:sqlserver://localhost\\SQLEXPRESS;"
            + "databaseName=HMS;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    private static final String USER = "sa";
    private static final String PASSWORD = "12345678";
    
    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println(" Database Connected Successfully!");

            return connection;

        } catch (ClassNotFoundException e) {
            System.out.println("SQL Server JDBC Driver not found!");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println(" Database Connection Failed!");
            e.printStackTrace();
        }

        return null;
    }
}