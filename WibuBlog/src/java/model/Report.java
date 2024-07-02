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
    private LocalDateTime postReportTime;
    private String reason;
    private int postId;
    private String statusReport;
    private String usernameReport;
    private String titleReport;

    public Report() {
    }

    public Report(int reportId, LocalDateTime postReportTime, String reason, String statusReport, String usernameReport, String titleReport) {
        this.reportId = reportId;
        this.postReportTime = postReportTime;
        this.reason = reason;
        this.statusReport = statusReport;
        this.usernameReport = usernameReport;
        this.titleReport = titleReport;
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

    public LocalDateTime getPostReportTime() {
        return postReportTime;
    }

    public void setPostReportTime(LocalDateTime postReportTime) {
        this.postReportTime = postReportTime;
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

    public String getStatusReport() {
        return statusReport;
    }

    public void setStatusReport(String statusReport) {
        this.statusReport = statusReport;
    }

    public String getUsernameReport() {
        return usernameReport;
    }

    public void setUsernameReport(String usernameReport) {
        this.usernameReport = usernameReport;
    }

    public String getTitleReport() {
        return titleReport;
    }

    public void setTitleReport(String titleReport) {
        this.titleReport = titleReport;
    }

}
