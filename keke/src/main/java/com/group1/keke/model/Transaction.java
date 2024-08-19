package com.group1.keke.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "Transaction")
public class Transaction {

    @JsonIgnore
    @Id() @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String type;

    @JsonIgnore
    String student_id;

    String amount;
    String date;

    String beneficiary;


    public Transaction(String beneficiary, String type, String student_id, String amount, String date) {
        this.beneficiary = beneficiary;
        this.type = type;
        this.student_id = student_id;
        this.amount = amount;
        this.date = date;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
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
        return "Transaction{" +
                "type='" + type + '\'' +
                ", student_id='" + student_id + '\'' +
                ", amount='" + amount + '\'' +
                ", date='" + date + '\'' +
                ", beneficiary='" + beneficiary + '\'' +
                '}';
    }
}
