package com.example.socialmediabackend;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "PostTable")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postID;

    @Column(nullable = false)
    private String postBody;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private int userID;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;


    @JsonProperty("postID")
    public int getPostId() {
        return postID;
    }

    public void setPostId(int postID) {
        this.postID = postID;
    }

    @JsonProperty("userID") // Map JSON key "userId" to field "userID"
    public int getUserId() {
        return userID;
    }

    public void setUserId(int userId) {
        this.userID = userId;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
