package com.example.eaterydemo.model;

public class Message {
    private String Notification;
    private int Status;

    public Message(int status, String notification) {
        Status = status;
        Notification = notification;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getNotification() {
        return Notification;
    }

    public void setNotification(String notification) {
        Notification = notification;
    }
}
