package com.example.studentapp.Models;

public class Student {

    private String id, fn, sn, email;


    public Student(String id, String fn, String sn, String email) {
        this.id = id;
        this.fn = fn;
        this.sn = sn;
        this.email = email;

    }

    public Student() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
