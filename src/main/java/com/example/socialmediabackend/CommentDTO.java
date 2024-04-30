package com.example.socialmediabackend;

public class CommentDTO {

    private int commentID;
    private String commentBody;
    private CommentCreatorDTO commentCreator;

    // Constructors, getters, and setters

    public CommentDTO() {
    }

    public CommentDTO(int commentID, String commentBody, CommentCreatorDTO commentCreator) {
        this.commentID = commentID;
        this.commentBody = commentBody;
        this.commentCreator = commentCreator;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public CommentCreatorDTO getCommentCreator() {
        return commentCreator;
    }

    public void setCommentCreator(CommentCreatorDTO commentCreator) {
        this.commentCreator = commentCreator;
    }
}
