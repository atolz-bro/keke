package com.group1.keke.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "DriverTransaction")
public class DriverTransaction {

    @JsonIgnore
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @JsonIgnore
    String driver_id;
    String type;
    String amount;
    String date;
    
    String student_matric_no;

    public DriverTransaction( String driver_id, String type, String amount, String date, String student_matric_no) {
        this.driver_id = driver_id;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.student_matric_no = student_matric_no;
    }

    public DriverTransaction() {

    }

    public Long getId() {
        return id;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public String getStudent_matric_no() {
        return student_matric_no;
    }

    public void setStudent_matric_no(String student_matric_no) {
        this.student_matric_no = student_matric_no;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DriverTransaction{" +
                "id=" + id +
                ", driver_id='" + driver_id + '\'' +
                ", type='" + type + '\'' +
                ", amount='" + amount + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
