package com.example.studentapp.Models;

public class Comments {

    String comment, author, date, category;

    public Comments(String comment, String author, String date, String category) {

        this.comment = comment;
        this.author = author;
        this.date = date;
        this.category = category;
    }

    public Comments() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
}
