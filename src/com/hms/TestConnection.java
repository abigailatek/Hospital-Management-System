package com.hms;

import java.sql.Connection;
import com.hms.database.DatabaseConnection;

public class TestConnection {

    public static void main(String[] args) {

        Connection conn = DatabaseConnection.getConnection();

        if (conn != null) {

            System.out.println("SUCCESS");

        } else {

            System.out.println("FAILED");

        }

    }

}