package com.example.studentapp.Models;

import android.net.Uri;

import java.util.Date;

public class Activity {

    String name, description, room, date;



    public Activity(String name, String room, String description, String date) {
        this.name = name;
        this.description = description;
        this.room = room;
        this.date = date;

    }

    public Activity() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
