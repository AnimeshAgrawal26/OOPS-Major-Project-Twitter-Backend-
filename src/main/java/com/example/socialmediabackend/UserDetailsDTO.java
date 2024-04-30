package com.example.socialmediabackend;

public class UserDetailsDTO {

    private String name;
    private int userID;
    private String email;

    public UserDetailsDTO() {
    }

    public UserDetailsDTO(String name, int userID, String email) {
        this.name = name;
        this.userID = userID;
        this.email = email;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
