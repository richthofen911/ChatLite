package com.xtek.chatlite;

/**
 * Created by admin on 29/04/15.
 */
public class User {
    private static String userName;
    private static String mood;

    public User(String name){
        userName = name;
    }

    public static void setUserName(String name){
        userName = name;
    }

    public static String getUserName(){
        return userName;
    }

    public static void setMood(String mMood){
        mood = mMood;
    }

    public static String getMood(){
        return mood;
    }
}
