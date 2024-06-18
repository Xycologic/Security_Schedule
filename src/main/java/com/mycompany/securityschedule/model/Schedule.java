/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.securityschedule.model;

public class Schedule {
    private int id;
    private int securityId;
    private String date;
    private String shift;

    public Schedule(int id, int securityId, String date, String shift) {
        this.id = id;
        this.securityId = securityId;
        this.date = date;
        this.shift = shift;
    }

    public int getId() {
        return id;
    }

    public int getSecurityId() {
        return securityId;
    }
    
    public String getDate() {
        return date;
    }

    public String getShift() {
        return shift;
    }
}
