package com.example.booktracking;

import android.os.Parcelable;

import java.io.Serializable;

public class Book implements Serializable {

    private int id;
    private String title;
    private String type;
    private String author;
    private String publisher;
    private int publicationYear = -1;
    private String language;
    private String ISBN;
    private String description;
    private int pageNumber = -1;
    private String status;
    private String image;
    private String created_at;
    private String updated_at;

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        if (author == null)
            return "N/A";
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        if (publisher == null)
            return "N/A";
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getLanguage() {
        if (language == null)
            return "N/A";
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getISBN() {
        if (ISBN == null)
            return "N/A";
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getDescription() {
        if (description == null)
            return "N/A";
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getStatus() {
        if (status == null)
            return "N/A";
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getStringYear() {
        if (publicationYear == -1) {
            return "N/A";
        } else {
            return String.valueOf(publicationYear);
        }
    }

    public String getStringPage() {
        if (pageNumber == -1) {
            return "N/A";
        } else {
            return String.valueOf(pageNumber);
        }
    }
}
