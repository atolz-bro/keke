package com.group1.keke.model;

public class DriverLoginResponse {
    String token;
    String username;
    String email;

    String plate_no;

    String account_balance;

    public DriverLoginResponse(String token, String username, String email, String plate_no, String account_balance) {
        this.token = token;
        this.username = username;
        this.email = email;
        this.plate_no = plate_no;
        this.account_balance = account_balance;
    }

    public String getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(String account_balance) {
        this.account_balance = account_balance;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlate_no() {
        return plate_no;
    }

    public void setPlate_no(String plate_no) {
        this.plate_no = plate_no;
    }
}
