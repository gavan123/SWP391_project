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
    private String title;
    private String content;
    private String source;
    private String image;
    private LocalDateTime postTime;
    private String status;
    private int vote;
    private int view;
    private int commentCount;
    private int ReportCount;
    private int VoteCount;

    public Post() {
    }

    public Post(int postId, int userId, int categoryId, String title, String source, String image, LocalDateTime postTime, String status, int vote, int view, int ReportCount) {
        this.postId = postId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.title = title;
        this.source = source;
        this.image = image;
        this.postTime = postTime;
        this.status = status;
        this.vote = vote;
        this.view = view;
        this.ReportCount = ReportCount;
    }

    public Post(int userId, int categoryId, String title, String content, String source, String image, LocalDateTime postTime, String status) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.source = source;
        this.image = image;
        this.postTime = postTime;
        this.status = status;
    }

    public Post(int postId, int userId, int categoryId, String title, String content, String source, String image, String status, int vote, int view) {
        this.postId = postId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.source = source;
        this.image = image;
        this.status = status;
        this.vote = vote;
        this.view = view;
    }

    public Post(int postId, int userId, int categoryId, String title, String content, String source, String image, LocalDateTime postTime, String status, int vote, int view, int commentCount) {
        this.postId = postId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.source = source;
        this.image = image;
        this.postTime = postTime;
        this.status = status;
        this.vote = vote;
        this.view = view;
        this.commentCount = commentCount;
    }

    public Post(int postId, int userId, int categoryId, String title, String content, String source, String image, LocalDateTime postTime, String status, int vote, int view) {
        this.postId = postId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.source = source;
        this.image = image;
        this.postTime = postTime;
        this.status = status;
        this.vote = vote;
        this.view = view;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
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

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReportCount() {
        return ReportCount;
    }

    public void setReportCount(int ReportCount) {
        this.ReportCount = ReportCount;
    }

    public int getVoteCount() {
        return VoteCount;
    }

    public void setVoteCount(int VoteCount) {
        this.VoteCount = VoteCount;
    }
    
    

}
