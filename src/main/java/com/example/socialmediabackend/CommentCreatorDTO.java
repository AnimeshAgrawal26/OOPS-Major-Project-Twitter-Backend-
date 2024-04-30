package com.example.socialmediabackend;

public class CommentCreatorDTO {

    private int userID;
    private String name;

    // Constructors, getters, and setters

    public CommentCreatorDTO() {
    }

    public CommentCreatorDTO(int userID, String name) {
        this.userID = userID;
        this.name = name;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
