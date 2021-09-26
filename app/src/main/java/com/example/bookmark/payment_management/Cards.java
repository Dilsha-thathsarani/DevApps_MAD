package com.example.bookmark.payment_management;

public class Cards {
    String cardname;
    String number;
    String expdate;
    String cv;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    String uid;

    public Cards(){

    }

    public Cards(String cusname, String number, String expdate, String cv) {
        this.cardname = cusname;
        this.number = number;
        this.expdate = expdate;
        this.cv = cv;
    }

    public String getCardname() {
        return cardname;
    }

    public String getNumber() {
        return number;
    }

    public String getExpdate() {
        return expdate;
    }

    public String getCv() {
        return cv;
    }

    public String getDecNumber() {
        String decrypted = "";
        try {
            decrypted = AESUtils.decrypt(number);
            System.out.println("TEST"+ "decrypted:" + decrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decrypted;
    }
}
