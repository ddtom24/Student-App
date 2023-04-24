package com.example.studentapp.Models;

public class Reviews {

    String comment, author, date, bookpk;

    public Reviews(String comment, String author, String date, String bookpk) {

        this.comment = comment;
        this.author = author;
        this.date = date;
        this.bookpk = bookpk;
    }

    public Reviews() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBookpk() {
        return bookpk;
    }

    public void setBookpk(String bookpk) {
        this.bookpk = bookpk;
    }
}
