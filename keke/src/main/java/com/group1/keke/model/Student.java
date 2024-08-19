package com.group1.keke.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


@Entity(name = "Student")
public class Student {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;



    @Column
    String matric_no;
    String password;

    @Column
    String username;

    @Column
    String email;


    //@JsonIgnore
    String account_bal;

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMatric_no() {
        return matric_no;
    }

    public void setMatric_no(String matric_no) {
        this.matric_no = matric_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @JsonIgnore
    public void setAccountBal(String accountBal) {
        this.account_bal = accountBal;
    }


    public String getAccount_bal() {
        return account_bal;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", matric_no='" + matric_no + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", account_bal='" + account_bal + '\'' +
                '}';
    }
}

