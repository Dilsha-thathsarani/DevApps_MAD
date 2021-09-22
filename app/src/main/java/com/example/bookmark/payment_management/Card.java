package com.example.bookmark.payment_management;

public class Card {

    private String uid;
    private String Cusname;
    private int CrdNo;
    private String Exp;
    private int Cvv;

    Card(){

    }

    public String getCusname() {
        return Cusname;
    }

    public void setCusname(String cusname) {
        Cusname = cusname;
    }

    public int getCrdNo() {
        return CrdNo;
    }

    public void setCrdNo(int crdNo) {
        CrdNo = crdNo;
    }

    public String getExp() {
        return Exp;
    }

    public void setExp(String exp) {
        Exp = exp;
    }

    public int getCvv() {
        return Cvv;
    }

    public void setCvv(int cvv) {
        Cvv = cvv;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }





}
