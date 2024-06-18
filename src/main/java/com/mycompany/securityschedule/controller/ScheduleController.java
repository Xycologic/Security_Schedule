package com.mycompany.securityschedule.controller;

import com.mycompany.securityschedule.dao.ScheduleDAO;
import com.mycompany.securityschedule.model.Schedule;
import com.mycompany.securityschedule.model.Security;

import java.sql.SQLException;
import java.util.List;

public class ScheduleController {
    private ScheduleDAO scheduleDAO;

    public ScheduleController() {
        this.scheduleDAO = new ScheduleDAO();
    }

    public void addSchedule(int securityId, String date, String shift) throws SQLException {
        try {
            scheduleDAO.addSchedule(securityId, date, shift);
        } catch (SQLException e) {
            // Handle or rethrow the exception as needed
            throw e;
        }
    }

    public List<Schedule> getAllSchedules() {
        return scheduleDAO.getAllSchedules();
    }
       
    public void deleteSchedule(int scheduleId) {
        scheduleDAO.deleteSchedule(scheduleId);
    }
    
    public List<Security> getAllSecurity() {
        return scheduleDAO.getAllSecurity();
    }

    public String getSecurityName(int securityId) {
        try {
            return scheduleDAO.getSecurityName(securityId);
        } catch (SQLException e) {
            // Handle or log the exception appropriately
            e.printStackTrace();
            return "Security Name Not Found";
        }
    }
}
