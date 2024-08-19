package com.group1.keke.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "Message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String message;
    String student_id;
    String  driver_id;

    String sender;

    String date;

    public Message(String message, String student_id, String driver_id, String sender, String date) {
        this.message = message;
        this.student_id = student_id;
        this.driver_id = driver_id;
        this.sender = sender;
        this.date = date;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Message() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getStudent_id() {
        return student_id;
    }


    @JsonIgnore
    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }


    public String getDriver_id() {
        return driver_id;
    }

    @JsonIgnore
    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
