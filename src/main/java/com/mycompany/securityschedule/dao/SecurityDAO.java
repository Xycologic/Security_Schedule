package com.mycompany.securityschedule.dao;

import com.mycompany.securityschedule.db.DatabaseConnection;
import com.mycompany.securityschedule.model.Security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SecurityDAO {
    private final String INSERT_SECURITY_SQL = "INSERT INTO security (id, name) VALUES (?, ?)";
    private final String SELECT_ALL_SECURITY_SQL = "SELECT * FROM security";

    public void addSecurity(int id, String name) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SECURITY_SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
        }
    }

    public void deleteSecurity(int securityId) {
        String DELETE_SECURITY_SQL = "DELETE FROM security WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SECURITY_SQL)) {
            preparedStatement.setInt(1, securityId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Security> getAllSecurity() {
        List<Security> securities = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SECURITY_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Security security = new Security(id, name);
                securities.add(security);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return securities;
    }
}
