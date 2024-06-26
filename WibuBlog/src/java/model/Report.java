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
public class Report {
    private int reportId;
    private int userId;
    private LocalDateTime timeCreated;
    private String reason;
    private int postId;
    private String status;

    public Report() {
    }
    
    public Report(int userId, LocalDateTime timeCreated, String reason, int postId) {
        this.reportId = 0;
        this.userId = userId;
        this.timeCreated = timeCreated;
        this.reason = reason;
        this.postId = postId;
        this.status = "Pending";
    }

    public Report(int reportId, int userId, LocalDateTime timeCreated, String reason, int postId) {
        this.reportId = reportId;
        this.userId = userId;
        this.timeCreated = timeCreated;
        this.reason = reason;
        this.postId = postId;
        this.status = "Pending";
    }

    public Report(int reportId, int userId, LocalDateTime timeCreated, String reason, int postId, String status) {
        this.reportId = reportId;
        this.userId = userId;
        this.timeCreated = timeCreated;
        this.reason = reason;
        this.postId = postId;
        this.status = status;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
