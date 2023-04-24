package com.example.studentapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class LibraryBook implements Parcelable {  //extends Book
    String bookID, edition, isbn, category, title, author, url;
    ArrayList<String> reviews;
    boolean reserved = false;


    enum Categories{
        BUSINESS,
        COMPUTING,
        ECONOMICS,
        MATHEMATICS;
    }
    public LibraryBook(String bookID, String category, String title, String author, String url, String edition, String isbn, ArrayList<String> reviews, boolean reserved) {
        this.bookID=bookID;
        this.title = title;
        this.author = author;
        this.url = url;
        this.edition = edition;
        this.isbn = isbn;
        this.reviews = reviews;
        this.reserved = reserved;
        this.category = category;
    }

    public LibraryBook() {

    }

    protected LibraryBook(Parcel in) {
        bookID = in.readString();
        edition = in.readString();
        isbn = in.readString();
        category = in.readString();
        title = in.readString();
        author = in.readString();
        url = in.readString();
        reviews = in.createStringArrayList();
        reserved = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bookID);
        dest.writeString(edition);
        dest.writeString(isbn);
        dest.writeString(category);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(url);
        dest.writeStringList(reviews);
        dest.writeByte((byte) (reserved ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LibraryBook> CREATOR = new Creator<LibraryBook>() {
        @Override
        public LibraryBook createFromParcel(Parcel in) {
            return new LibraryBook(in);
        }

        @Override
        public LibraryBook[] newArray(int size) {
            return new LibraryBook[size];
        }
    };

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public ArrayList<String> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<String> reviews) {
        this.reviews = reviews;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}

