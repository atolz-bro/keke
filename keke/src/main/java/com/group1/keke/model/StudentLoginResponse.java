package com.group1.keke.model;

public class StudentLoginResponse {
    String token;
    String username;
    String email;
    String matric_no;

    String account_balance;

    public StudentLoginResponse(String token, String username, String email, String matric_no, String account_balance) {
        this.token = token;
        this.username = username;
        this.email = email;
        this.matric_no = matric_no;
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

    public String getMatric_no() {
        return matric_no;
    }

    public void setMatric_no(String matric_no) {
        this.matric_no = matric_no;
    }
}
