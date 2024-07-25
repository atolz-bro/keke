package com.group1.keke.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "Driver")
public class Driver{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String plate_no;
    String password;
    String username;
    String phone_no;

    String email;



    String acct_bal;

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public String getAcct_bal() {
        return acct_bal;
    }

    public void setAcct_bal(String acct_bal) {
        this.acct_bal = acct_bal;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPlate_no() {
        return plate_no;
    }

    public void setPlate_no(String plate_no) {
        this.plate_no = plate_no;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
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
}
