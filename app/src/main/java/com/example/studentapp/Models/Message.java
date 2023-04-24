package com.example.studentapp.Models;

public class Message {

    private String senderID, receiverID, subject, message, date_time;

    public Message(String senderID, String receiverID, String subject, String message, String date_time) {
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.subject = subject;
        this.message = message;
        this.date_time = date_time;
    }

    public Message() {
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }
}
