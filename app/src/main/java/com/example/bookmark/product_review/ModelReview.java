package com.example.bookmark.product_review;

public class ModelReview {

    String bookName,uid,comment,timestamp;
    float rate;

    public ModelReview() {
    }

    public ModelReview(String bookName, String uid, float rate, String comment,String timestamp) {
        this.bookName = bookName;
        this.uid = uid;
        this.rate = rate;
        this.comment = comment;
        this.timestamp=timestamp;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
