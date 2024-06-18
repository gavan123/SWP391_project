/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author minht
 */
public class PostDetail {

    private int postID;
    private String username;
    private String categoryName;
    private String genreName;
    private String title;
    private String content;
    private String source;
    private String image;
    private LocalDateTime postTime;
    private String status;
    private int vote;
    private int view;
    private String rank;
    private String color;

    public PostDetail() {
    }

    public PostDetail(String title, String content, String image) {
        this.title = title;
        this.content = content;
        this.image = image;
    }

    public PostDetail(int vote, int view, int aInt2, String username, String categoryName, String genreName, String title, LocalDateTime postTime, String content, int aInt3, int aInt4, String source, String image, String status, String rank, String color) {
        this.username = username;
        this.categoryName = categoryName;
        this.genreName = genreName;
        this.title = title;
        this.content = content;
        this.source = source;
        this.image = image;
        this.postTime = postTime;
        this.status = status;
        this.vote = vote;
        this.view = view;
        this.rank = rank;
        this.color = color;
    }
    
    

    public PostDetail(int postID, String username, String categoryName, String genreName, String title, String content, String source, String image, LocalDateTime postTime, String status, int vote, int view, String rank, String color) {
        this.postID = postID;
        this.username = username;
        this.categoryName = categoryName;
        this.genreName = genreName;
        this.title = title;
        this.content = content;
        this.source = source;
        this.image = image;
        this.postTime = postTime;
        this.status = status;
        this.vote = vote;
        this.view = view;
        this.rank = rank;
        this.color = color;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "PostDetail{" + "postID=" + postID + ", username=" + username + ", categoryName=" + categoryName + ", genreName=" + genreName + ", title=" + title + ", content=" + content + ", source=" + source + ", image=" + image + ", postTime=" + postTime + ", status=" + status + ", vote=" + vote + ", view=" + view + ", rank=" + rank + ", color=" + color + '}';
    }

}
