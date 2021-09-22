package com.example.bookmark.payment_management;

public class Card {

    private String uid;
   // private String Cusname;
    //private String CrdNo;
   // private String Exp;
   // private int Cvv;
    private String cardname;
    private String number;
    private String cv;
    private String expdate;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getExpdate() {
        return expdate;
    }

    public void setExpdate(String expdate) {
        this.expdate = expdate;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    Card(){

    }
/*
    public String getCusname() {
        return Cusname;
    }

    public void setCusname(String cardname) {
        Cusname = cardname;
    }

    public String getCrdNo() {
        return CrdNo;
    }

    public void setCrdNo(String crdNo) {
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
*/
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }





}
