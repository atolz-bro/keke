package com.group1.keke.model;

public class DriverDetailsResponse {

    String plate_no;
    String username;

    public DriverDetailsResponse(String plate_no, String username) {
        this.plate_no = plate_no;
        this.username = username;
    }

    public String getPlate_no() {
        return plate_no;
    }

    public void setPlate_no(String plate_no) {
        this.plate_no = plate_no;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
