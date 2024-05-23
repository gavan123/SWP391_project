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
public class Post {
    private int postId;
    private int userId;
    private int categoryId;
    private String content;
    private String source;
    private String image;
    private LocalDateTime postTime;
    private String status;
    private int upvote;
    private int downvote;

    public Post() {
    }

    public Post(int postId, int userId, int categoryId, String content, String source, String image, LocalDateTime postTime, String status, int upvote, int downvote) {
        this.postId = postId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.content = content;
        this.source = source;
        this.image = image;
        this.postTime = postTime;
        this.status = status;
        this.upvote = upvote;
        this.downvote = downvote;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getPostTime() {
        return postTime;
    }

    public void setPostTime(LocalDateTime postTime) {
        this.postTime = postTime;
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
    
    
}