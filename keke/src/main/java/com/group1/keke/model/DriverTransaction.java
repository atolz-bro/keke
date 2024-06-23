package com.group1.keke.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "DriverTransaction")
public class DriverTransaction {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String driver_id;
    String type;
    String amount;
    String date;

    public DriverTransaction(String driver_id, String type, String amount, String date) {
        this.driver_id = driver_id;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public DriverTransaction() {

    }

    public Long getId() {
        return id;
    }

    public String getDriver_id() {
        return driver_id;
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
