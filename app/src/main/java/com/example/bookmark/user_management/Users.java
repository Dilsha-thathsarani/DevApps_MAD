package com.example.bookmark.user_management;

public class Users {

    private String Uid,Email,Name,Password;
    private int UserType;

    public int getUserType() {
        return UserType;
    }

    public void setUserType(int UserType) {
        this.UserType = UserType;
    }


    public Users()
    {

    }

    public Users(String uid,String email, String name,int userType) {
        this.Uid=uid;
        this.Email = email;
        this.Name = name;
        // this.Password = password;
        this.UserType=userType;

    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String Uid) {
        this.Uid = Uid;
    }


    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }


}
