package com.group1.keke.model;

public class StudentDetailsResposnse {
    String maric_no;
    String username;

    String email;

    public StudentDetailsResposnse(String maric_no, String username, String email) {
        this.maric_no = maric_no;
        this.username = username;
        this.email = email;
    }

    public String getMaric_no() {
        return maric_no;
    }

    public void setMaric_no(String maric_no) {
        this.maric_no = maric_no;
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
}
