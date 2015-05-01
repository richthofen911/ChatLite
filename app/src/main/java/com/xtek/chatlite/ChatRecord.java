package com.xtek.chatlite;

/**
 * @author greg
 * @since 6/21/13
 */
public class ChatRecord {

    private String message;
    private String username;

    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private ChatRecord() {
    }

    ChatRecord(String message, String author) {
        this.message = message;
        this.username = author;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }
}
