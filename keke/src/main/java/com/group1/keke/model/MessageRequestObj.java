package com.group1.keke.model;

public class MessageRequestObj {
    String message;
    String matric_no;
    String plate_no;

    public MessageRequestObj(String message, String matric_no, String plate_no) {
        this.message = message;
        this.matric_no = matric_no;
        this.plate_no = plate_no;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMatric_no() {
        return matric_no;
    }

    public void setMatric_no(String matric_no) {
        this.matric_no = matric_no;
    }

    public String getPlate_no() {
        return plate_no;
    }

    public void setPlate_no(String plate_no) {
        this.plate_no = plate_no;
    }

    @Override
    public String toString() {
        return "MessageRequestObj{" +
                "nessage='" + message + '\'' +
                ", matric_no='" + matric_no + '\'' +
                ", plate_no='" + plate_no + '\'' +
                '}';
    }
}
