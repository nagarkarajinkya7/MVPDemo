package com.appmaker.mvpdemo.Model;

public class User {

    private String fName = null;
    private String eMail = null;

    public User() {
    }

    public User(String fName, String eMail) {
        this.fName = fName;
        this.eMail = eMail;
    }

    public String getFullName() {
        return fName;
    }

    public void setFullName(String fName) {
        this.fName = fName;
    }

    public String getMailId() {
        return eMail;
    }

    public void setEmailId(String eMail) {
        this.eMail = eMail;
    }


    @Override
    public String toString() {
        return "Email Address : " + eMail + "\nFull Name : " + fName;
    }
}
