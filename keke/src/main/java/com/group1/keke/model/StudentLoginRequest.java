package com.group1.keke.model;

public class StudentLoginRequest {
    String matric_no;
    String password;

    public StudentLoginRequest(String matric_no, String password) {
        this.matric_no = matric_no;
        this.password = password;
    }

    public String getMatric_no() {
        return matric_no;
    }

    public void setMatric_no(String matric_no) {
        this.matric_no = matric_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
