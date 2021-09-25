package com.example.bookmark.product_review;

import java.util.Comparator;

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

    public static Comparator<ModelReview> newest=new Comparator<ModelReview>() {
        @Override
        public int compare(ModelReview o1, ModelReview o2) {
            return o1.getTimestamp().compareTo(o2.getTimestamp());
        }
    };

    public static Comparator<ModelReview> oldest=new Comparator<ModelReview>() {
        @Override
        public int compare(ModelReview o1, ModelReview o2) {
            return o2.getTimestamp().compareTo(o1.getTimestamp());
        }
    };


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
