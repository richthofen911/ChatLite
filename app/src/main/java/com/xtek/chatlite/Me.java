package com.xtek.chatlite;

public class Me {
    private static String userName;
    private static String userEmail;
    private static String mood;
    private static String MY_MAIN_URL;

    public Me(String name){
        userName = name;
    }

    public static void setUserName(String name){
        userName = name;
    }

    public static String getUserName(){
        return userName;
    }

    public static void setUserEmail(String email){
        userEmail = email;
    }

    public static String getUserEmail(){
        return userEmail;
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
