package com.example.bookmark.product_review;

public class Review {

    private String Uid,BookName,Comment,Timestamp;
    private float Rate;

    public Review()
    {

    }
    public Review(String bookname,String uid,float rate,String comment,String timestamp) {
        this.Uid=uid;
        this.BookName = bookname;
        this.Rate=rate;
        this.Comment=comment;
        this.Timestamp = timestamp;

    }


    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookname) {
        BookName = bookname;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public float getRate() {
        return Rate;
    }

    public void setRate(float rate) {
        Rate = rate;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String timestamp) {
        Timestamp = timestamp;
    }
}
