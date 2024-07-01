/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Report;

/**
 *
 * @author minht
 */
public class ReportDAO extends DBContext {

     public Integer getUserIdByUsername(String username) {
        Integer userId = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT UserID FROM [User] WHERE Username = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("UserID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return userId;
    }

    public Integer getPostIdByPostTitle(String postTitle) {
        Integer postId = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT PostID FROM Post WHERE Title = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, postTitle);
            rs = ps.executeQuery();
            if (rs.next()) {
                postId = rs.getInt("PostID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return postId;
    }

    
    // tạo report 
  public boolean createReport(String username, String postTitle, String reason) {
        Integer userId = getUserIdByUsername(username);
        Integer postId = getPostIdByPostTitle(postTitle);

        if (userId == null || postId == null) {
            System.out.println("Failed to create report. User or post not found.");
            return false;
        }

        PreparedStatement ps = null;
        try {
            // Get current LocalDateTime
            LocalDateTime now = LocalDateTime.now();

            // Convert LocalDateTime to Timestamp
            Timestamp timestamp = Timestamp.valueOf(now);

            String sql = "INSERT INTO Report (UserID, TimeCreated, Reason, PostID, [Status]) VALUES (?, ?, ?, ?, 'Pending')";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setTimestamp(2, timestamp);
            ps.setString(3, reason);
            ps.setInt(4, postId);

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closePreparedStatement(ps);
        }
    }
    
    // lấy list Report
    public List<Report> ListAllReport() {
        List<Report> reportList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT rp.ReportID, rp.UserID, rp.PostReport, rp.Reason, rp.PostID, rp.Status, "
                + "p.Title, u.Username, rp.Note "
                + "FROM Report rp "
                + "JOIN Post p ON p.PostID = rp.PostID "
                + "JOIN [User] u ON rp.UserID = u.UserID";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int reportId = rs.getInt("ReportID");
                int userId = rs.getInt("UserID");
                LocalDateTime postReport = rs.getTimestamp("PostReport").toLocalDateTime();
                String reason = rs.getString("Reason");
                int postId = rs.getInt("PostID");
                String status = rs.getString("Status");
                String title = rs.getString("Title");
                String username = rs.getString("Username");
                String note = rs.getString("Note");

                Report report = new Report(reportId, postReport, reason, status, username, title, note);
                reportList.add(report);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    public List<Report> listReportRejected() {
        List<Report> rejectedReports = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT rp.ReportID, rp.TimeCreated, rp.Reason, rp.Status, "
                + "u.Username AS UsernameReport, p.Title AS TitleReport, rp.Note "
                + "FROM Report rp "
                + "JOIN Post p ON p.PostID = rp.PostID "
                + "JOIN [User] u ON u.UserID = rp.UserID "
                + "WHERE rp.Status = 'Rejected'";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int reportId = rs.getInt("ReportID");
                LocalDateTime postReportTime = rs.getTimestamp("TimeCreated").toLocalDateTime();
                String reason = rs.getString("Reason");
                String statusReport = rs.getString("Status");
                String usernameReport = rs.getString("UsernameReport");
                String titleReport = rs.getString("TitleReport");
                String note = rs.getString("Note");

                Report report = new Report(reportId, postReportTime, reason, statusReport, usernameReport, titleReport, note);
                rejectedReports.add(report);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return rejectedReports;
    }

    // List approved reports method
    public List<Report> listReportApproved() {
        List<Report> approvedReports = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT rp.ReportID, rp.TimeCreated, rp.Reason, rp.Status, "
                + "u.Username AS UsernameReport, p.Title AS TitleReport, rp.Note "
                + "FROM Report rp "
                + "JOIN Post p ON p.PostID = rp.PostID "
                + "JOIN [User] u ON u.UserID = rp.UserID "
                + "WHERE rp.Status = 'Approved'";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int reportId = rs.getInt("ReportID");
                LocalDateTime postReportTime = rs.getTimestamp("TimeCreated").toLocalDateTime();
                String reason = rs.getString("Reason");
                String statusReport = rs.getString("Status");
                String usernameReport = rs.getString("UsernameReport");
                String titleReport = rs.getString("TitleReport");
                String note = rs.getString("Note");

                Report report = new Report(reportId, postReportTime, reason, statusReport, usernameReport, titleReport, note);
                approvedReports.add(report);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return approvedReports;
    }
}
