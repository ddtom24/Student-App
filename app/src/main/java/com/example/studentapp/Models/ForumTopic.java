package com.example.studentapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ForumTopic implements Parcelable {
    String category, post, author;


    public ForumTopic(String category, String post, String author) {
        this.category = category;
        this.post = post;
        this.author = author;

    }

    public ForumTopic() {
    }

    protected ForumTopic(Parcel in) {
        category = in.readString();
        post = in.readString();
        author = in.readString();

    }

    public static final Creator<ForumTopic> CREATOR = new Creator<ForumTopic>() {
        @Override
        public ForumTopic createFromParcel(Parcel in) {
            return new ForumTopic(in);
        }

        @Override
        public ForumTopic[] newArray(int size) {
            return new ForumTopic[size];
        }
    };

    public String getCategory() {
        return category;
    }

    public void setCategory(String title) {
        this.category = title;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(category);
        dest.writeString(post);
        dest.writeString(author);
    }
}
