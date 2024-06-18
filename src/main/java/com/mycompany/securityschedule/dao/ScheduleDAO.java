package com.mycompany.securityschedule.dao;

import com.mycompany.securityschedule.db.DatabaseConnection;
import com.mycompany.securityschedule.model.Schedule;
import com.mycompany.securityschedule.model.Security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDAO {
    private final String INSERT_SCHEDULE_SQL = "INSERT INTO schedule (security_id, date, shift) VALUES (?, ?, ?)";
    private final String SELECT_ALL_SCHEDULES_SQL = "SELECT * FROM schedule";
    private final String SELECT_ALL_SECURITY_SQL = "SELECT * FROM security";
    private final String SELECT_SECURITY_NAME_SQL = "SELECT name FROM security WHERE id = ?";

    public void addSchedule(int securityId, String date, String shift) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SCHEDULE_SQL)) {
            preparedStatement.setInt(1, securityId);
            preparedStatement.setString(2, date);
            preparedStatement.setString(3, shift);
            preparedStatement.executeUpdate();
        }
    }

    public List<Schedule> getAllSchedules() {
        List<Schedule> schedules = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SCHEDULES_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int securityId = resultSet.getInt("security_id");
                String date = resultSet.getString("date");
                String shift = resultSet.getString("shift");
                Schedule schedule = new Schedule(id, securityId, date, shift);
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
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

    public void deleteSchedule(int scheduleId) {
        String DELETE_SCHEDULE_SQL = "DELETE FROM schedule WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SCHEDULE_SQL)) {
            preparedStatement.setInt(1, scheduleId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public String getSecurityName(int securityId) throws SQLException {
        String securityName = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SECURITY_NAME_SQL)) {
            preparedStatement.setInt(1, securityId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    securityName = resultSet.getString("name");
                }
            }
        }
        return securityName;
    }
}
