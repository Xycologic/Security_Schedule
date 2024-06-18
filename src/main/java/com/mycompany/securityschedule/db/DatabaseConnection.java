package com.mycompany.securityschedule.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/security_schedule";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "ventus161203"; // Ganti dengan password MySQL Anda

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
            throw new SQLException("Database connection error: " + e.getMessage());
        }
    }
}
