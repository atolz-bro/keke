package com.group1.keke.model;

public class DriverLoginRequest {
    String driver_plate_no;
    String password;

    public DriverLoginRequest(String driver_plate_no, String password) {
        this.driver_plate_no = driver_plate_no;
        this.password = password;
    }

    public String getDriver_plate_no() {
        return driver_plate_no;
    }

    public void setDriver_plate_no(String driver_plate_no) {
        this.driver_plate_no = driver_plate_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
