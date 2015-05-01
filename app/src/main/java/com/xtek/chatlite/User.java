package com.xtek.chatlite;

public class User {
    private static String userName;
    private static String mood;
    private static String MY_MAIN_URL;

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

    public static void setMY_MAIN_URL(String url){
        MY_MAIN_URL = url;
    }

    public static String getMY_MAIN_URL(){
        return MY_MAIN_URL;
    }
}
