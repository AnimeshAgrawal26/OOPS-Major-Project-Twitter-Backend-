package com.example.socialmediabackend;

public class CreatePostRequest {
    String postBody;
    int userID;

    String getPostBody() {
        return postBody;
    }
    int getUserID() {
        return userID;
    }
    void setPostBody(String postBody) {
        this.postBody = postBody;
    }
    void setUserID(int userID) {
        this.userID = userID;
    }
}