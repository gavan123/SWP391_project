/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author admin
 */
public class Comment {
    private int commentId;
    private int postId;
    private int userId;
    private String image;
    private String content;
    private String status;
    private int upvote;
    private int downvote;
    private int parentId;
    private LocalDateTime postTime;

    public Comment() {
    }

    public Comment(int commentId, int postId, int userId, String image, String content, String status, int upvote, int downvote, int parentId, LocalDateTime postTime) {
        this.commentId = commentId;
        this.postId = postId;
        this.userId = userId;
        this.image = image;
        this.content = content;
        this.status = status;
        this.upvote = upvote;
        this.downvote = downvote;
        this.parentId = parentId;
        this.postTime = postTime;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUpvote() {
        return upvote;
    }

    public void addUpvote(int upvote) {
        this.upvote += upvote;
    }

    public int getDownvote() {
        return downvote;
    }

    public void addDownvote(int downvote) {
        this.downvote += downvote;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public LocalDateTime getPostTime() {
        return postTime;
    }

    public void setPostTime(LocalDateTime postTime) {
        this.postTime = postTime;
    }
    
    
}
