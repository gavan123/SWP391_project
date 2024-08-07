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
    private String note;

    public Report() {
    }

    public Report(int reportId, int userId, LocalDateTime timeCreated, String reason, int postId, String status, String note) {
        this.reportId = reportId;
        this.userId = userId;
        this.timeCreated = timeCreated;
        this.reason = reason;
        this.postId = postId;
        this.status = status;
        this.note = note.length() > 0 ? note : "";
    }

    public Report(int userId, LocalDateTime timeCreated, String reason, int postId, String status, String note) {
        this.userId = userId;
        this.timeCreated = timeCreated;
        this.reason = reason;
        this.postId = postId;
        this.status = status;
        this.note = note.length() > 0 ? note : "";
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

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    
}
