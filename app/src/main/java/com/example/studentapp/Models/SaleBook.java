package com.example.studentapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class SaleBook implements Parcelable {

    String price, description, contact, title, author, url;


    public SaleBook(String title, String author, String description, String price, String url, String contact) {
      this.title = title;
      this.author = author;
      this.url = url;
        this.price = price;
        this.description = description;
        this.contact = contact;
    }

    public SaleBook() {
    }

    protected SaleBook(Parcel in) {
        price = in.readString();
        description = in.readString();
        contact = in.readString();
        title = in.readString();
        author = in.readString();
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(price);
        dest.writeString(description);
        dest.writeString(contact);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SaleBook> CREATOR = new Creator<SaleBook>() {
        @Override
        public SaleBook createFromParcel(Parcel in) {
            return new SaleBook(in);
        }

        @Override
        public SaleBook[] newArray(int size) {
            return new SaleBook[size];
        }
    };

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
