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
public class Notification {
    private int notificationId;
    private int sourcePostId;
    private int sourceUserId;
    private int targetUserId;
    private String content;
    private LocalDateTime postTime;

    public Notification() {
    }

    public Notification(int notificationId, int sourcePostId, int sourceUserId, int targetUserId, String content, LocalDateTime postTime) {
        this.notificationId = notificationId;
        this.sourcePostId = sourcePostId;
        this.sourceUserId = sourceUserId;
        this.targetUserId = targetUserId;
        this.content = content;
        this.postTime = postTime;
    }

    public Notification(int sourcePostId, int sourceUserId, int targetUserId, String content, LocalDateTime postTime) {
        this.sourcePostId = sourcePostId;
        this.sourceUserId = sourceUserId;
        this.targetUserId = targetUserId;
        this.content = content;
        this.postTime = postTime;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getSourcePostId() {
        return sourcePostId;
    }

    public void setSourcePostId(int postId) {
        this.sourcePostId = postId;
    }

    public int getSourceUserId() {
        return sourceUserId;
    }

    public void setSourceUserId(int sourceUserId) {
        this.sourceUserId = sourceUserId;
    }

    public int getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(int targetUserId) {
        this.targetUserId = targetUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPostTime() {
        return postTime;
    }

    public void setPostTime(LocalDateTime postTime) {
        this.postTime = postTime;
    }
    
   
}
