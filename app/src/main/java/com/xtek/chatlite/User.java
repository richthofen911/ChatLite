package com.xtek.chatlite;

/**
 * Created by admin on 29/04/15.
 */
public class User {
    private String userName;
    private String mood = "happy";

    public User(String name){
        userName = name;
    }

    public void setUserName(String name){
        userName = name;
    }

    public String getUserName(){
        return userName;
    }

    public void setMood(String mood){
        this.mood = mood;
    }

    public String getMood(){
        return mood;
    }
}
