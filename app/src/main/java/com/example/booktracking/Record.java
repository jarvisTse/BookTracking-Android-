package com.example.booktracking;

public class Record {
    private int book_id;
    private int user_id;
    private int staff_id;
    private String title;
    private String borrow_at;
    private String deadline_at;
    private String return_at;
    private int renewal_num;
    private String type;

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBorrow_at() {
        return borrow_at;
    }

    public void setBorrow_at(String borrow_at) {
        this.borrow_at = borrow_at;
    }

    public String getDeadline_at() {
        return deadline_at;
    }

    public void setDeadline_at(String deadline_at) {
        this.deadline_at = deadline_at;
    }

    public String getReturn_at() {
        return return_at;
    }

    public void setReturn_at(String return_at) {
        this.return_at = return_at;
    }

    public int getRenewal_num() {
        return renewal_num;
    }

    public void setRenewal_num(int renewal_num) {
        this.renewal_num = renewal_num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
