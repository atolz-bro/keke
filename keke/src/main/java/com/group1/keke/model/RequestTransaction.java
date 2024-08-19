package com.group1.keke.model;

public class RequestTransaction {

    String type;
    String request_amount;
    String newBal;

    public RequestTransaction(String type, String request_amount, String newBal) {
        this.type = type;
        this.request_amount = request_amount;
        this.newBal = newBal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRequest_amount() {
        return request_amount;
    }

    public void setRequest_amount(String request_amount) {
        this.request_amount = request_amount;
    }


    public String getNewBal() {
        return newBal;
    }

    public void setNewBal(String newBal) {
        this.newBal = newBal;
    }
}
