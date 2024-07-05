package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Report;

public class ReportDAO extends DBContext {

    public List<Report> listReportByID(int reportId) {
        List<Report> reportList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT rp.ReportID, rp.UserID, rp.TimeCreated, rp.Reason, rp.PostID, rp.Status, "
                + "p.Title AS PostTitle, u.Username AS Username, rp.Note "
                + "FROM Report rp "
                + "JOIN Post p ON p.PostID = rp.PostID "
                + "JOIN [User] u ON u.UserID = rp.UserID "
                + "WHERE rp.ReportID = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, reportId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Report report = new Report(
                        rs.getInt("ReportID"),
                        rs.getInt("UserID"),
                        rs.getTimestamp("TimeCreated").toLocalDateTime(),
                        rs.getString("Reason"),
                        rs.getInt("PostId"),
                        rs.getString("Status"),
                        rs.getString("Note"));
                reportList.add(report);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return reportList;
    }
    
    public List<Report> getAllReports() {
        List<Report> reportList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * from [Report]";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Report report = new Report(
                        rs.getInt("ReportID"),
                        rs.getInt("UserID"),
                        rs.getTimestamp("TimeCreated").toLocalDateTime(),
                        rs.getString("Reason"),
                        rs.getInt("PostId"),
                        rs.getString("Status"),
                        rs.getString("Note"));
                reportList.add(report);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return reportList;
    }

    // Method to retrieve a report by reportId
    public Report getReportById(int reportId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * from [Report] WHERE ReportID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, reportId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Report(
                        rs.getInt("ReportID"),
                        rs.getInt("UserID"),
                        rs.getTimestamp("TimeCreated").toLocalDateTime(),
                        rs.getString("Reason"),
                        rs.getInt("PostId"),
                        rs.getString("Status"),
                        rs.getString("Note"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return null;
    }

    // Method to retrieve a list of reports by userId
    public List<Report> getReportsByUserId(int userId) {
        List<Report> reportList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * from [Report] WHERE UserID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Report report = new Report(
                        rs.getInt("ReportID"),
                        rs.getInt("UserID"),
                        rs.getTimestamp("TimeCreated").toLocalDateTime(),
                        rs.getString("Reason"),
                        rs.getInt("PostId"),
                        rs.getString("Status"),
                        rs.getString("Note"));
                reportList.add(report);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return reportList;
    }

    // Method to retrieve a list of reports by postId
    public List<Report> getReportsByPostId(int postId) {
        List<Report> reportList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * from [Report] WHERE PostID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, postId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Report report = new Report(
                        rs.getInt("ReportID"),
                        rs.getInt("UserID"),
                        rs.getTimestamp("TimeCreated").toLocalDateTime(),
                        rs.getString("Reason"),
                        rs.getInt("PostId"),
                        rs.getString("Status"),
                        rs.getString("Note"));
                reportList.add(report);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return reportList;
    }

    // Method to retrieve a list of reports by status
    public List<Report> getReportsByStatus(String status) {
        List<Report> reportList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Report WHERE [Status] = N'?'";
            ps = connection.prepareStatement(sql);
            ps.setString(1, status);
            rs = ps.executeQuery();
            while (rs.next()) {
                Report report = new Report(
                        rs.getInt("ReportID"),
                        rs.getInt("UserID"),
                        rs.getTimestamp("TimeCreated").toLocalDateTime(),
                        rs.getString("Reason"),
                        rs.getInt("PostId"),
                        rs.getString("Status"),
                        rs.getString("Note"));

                reportList.add(report);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return reportList;
    }

    public List<Report> getPendingReports() {
        List<Report> reportList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Report WHERE [Status] = N'Pending'";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Report report = new Report(
                        rs.getInt("ReportID"),
                        rs.getInt("UserID"),
                        rs.getTimestamp("TimeCreated").toLocalDateTime(),
                        rs.getString("Reason"),
                        rs.getInt("PostId"),
                        rs.getString("Status"),
                        rs.getString("Note"));

                reportList.add(report);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return reportList;
    }

    public List<Report> getResolvedReports() {
        List<Report> reportList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Report WHERE [Status] = N'Approved' OR Status = N'Rejected'";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Report report = new Report(
                        rs.getInt("ReportID"),
                        rs.getInt("UserID"),
                        rs.getTimestamp("TimeCreated").toLocalDateTime(),
                        rs.getString("Reason"),
                        rs.getInt("PostId"),
                        rs.getString("Status"),
                        rs.getString("Note"));

                reportList.add(report);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return reportList;
    }

    public boolean setStatusPending(int reportId) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE [Report] SET Status = 'Pending' WHERE ReportID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, reportId);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closePreparedStatement(ps);
        }
    }

    public boolean setStatusApproved(int reportId) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE [Report] SET Status = 'Approved' WHERE ReportID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, reportId);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closePreparedStatement(ps);
        }
    }

    public boolean setStatusRejected(int reportId) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE [Report] SET Status = 'Rejected' WHERE ReportID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, reportId);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closePreparedStatement(ps);
        }
    }

    public boolean deleteReport(int reportId) {
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM [Report] WHERE ReportID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, reportId);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closePreparedStatement(ps);
        }
    }

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
            LocalDateTime now = LocalDateTime.now();

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

    public static void main(String[] args) {
        ReportDAO rd = new ReportDAO(); 
        List<Report> reportList = rd.getPendingReports();
        reportList.forEach(report -> {System.out.println(report.getStatus());});
        System.out.println(reportList.get(0).getReason());
    }
}
