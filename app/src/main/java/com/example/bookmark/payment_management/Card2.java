package com.example.bookmark.payment_management;

public class Card2 {

    private String uid;
    private String name;
    private String number;
    private String expdate;
    private int cv;

    Card2(){

    }

    public String getCusname() {
        return name;
    }

    public void setCusname(String cusname) {
        name = cusname;
    }

    public String getCrdNo() {
        return number;
    }

    public void setCrdNo(String crdNo) {
        number = crdNo;
    }

    public String getExp() {
        return expdate;
    }

    public void setExp(String exp) {
        expdate = exp;
    }

    public int getCvv() {
        return cv;
    }

    public void setCvv(int cvv) {
        cv = cvv;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }





}
