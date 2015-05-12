package com.xtek.chatlite;

/**
 * Created by Tuotuo on 11/05/2015.
 */
public class User {

    private String userName;
    private String mood;
    private String userEmail;

    public User(String name, String email){
        userName = name;
        userEmail = email;
    }

    public void setUserName(String name){
        userName = name;
    }

    public String getUserName(){
        return userName;
    }

    public void setMood(String mMood){
        mood = mMood;
    }

    public String getMood(){
        return mood;
    }

    public void setUserEmail(String email){
        userEmail = email;
    }

    public String getUserEmail(){
        return userEmail;
    }
}
