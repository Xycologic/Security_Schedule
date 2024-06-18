package com.mycompany.securityschedule.controller;

import com.mycompany.securityschedule.dao.SecurityDAO;
import com.mycompany.securityschedule.model.Security;

import java.sql.SQLException;
import java.util.List;

public class SecurityController {
    private SecurityDAO securityDAO;

    public SecurityController() {
        this.securityDAO = new SecurityDAO();
    }

    public void addSecurity(int id, String name) throws SQLException {
        securityDAO.addSecurity(id, name);
    }

    public void deleteSecurity(int securityId) {
        securityDAO.deleteSecurity(securityId);
    }
    
    public List<Security> getAllSecurity() {
        return securityDAO.getAllSecurity();
    }
}
