package com.mycompany.securityschedule.model;

public class Security {
    private int id;
    private String name;

    public Security(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return name; // Mengembalikan hanya nama sebagai representasi String
    }
}
