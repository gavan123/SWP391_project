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

    public ReportDAO() {
    }

    public ReportDAO(Connection connection) {
        this.connection = connection;
    }

    public Report getReportById(int reportId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM [Report] WHERE ReportID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, reportId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Report(
                        rs.getInt("ReportID"),
                        rs.getInt("UserID"),
                        rs.getTimestamp("TimeCreated").toLocalDateTime(),
                        rs.getString("Reason"),
                        rs.getInt("PostID"),
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

    public List<Report> getReportsByUserId(int userId) {
        List<Report> reportList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from [Report] where UserID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Report report = new Report(
                        rs.getInt("ReportID"),
                        rs.getInt("UserID"),
                        rs.getTimestamp("TimeCreated").toLocalDateTime(),
                        rs.getString("Reason"),
                        rs.getInt("PostID"),
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

    public List<Report> getReportsByPostId(int postId) {
        List<Report> reportList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from [Report] where PostID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, postId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Report report = new Report(
                        rs.getInt("ReportID"),
                        rs.getInt("UserID"),
                        rs.getTimestamp("TimeCreated").toLocalDateTime(),
                        rs.getString("Reason"),
                        rs.getInt("PostID"),
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

    public List<Report> getReportsByStatus(String status) {
        List<Report> reportList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from [Report] where Status = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, status);
            rs = ps.executeQuery();
            while (rs.next()) {
                Report report = new Report(
                        rs.getInt("ReportID"),
                        rs.getInt("UserID"),
                        rs.getTimestamp("TimeCreated").toLocalDateTime(),
                        rs.getString("Reason"),
                        rs.getInt("PostID"),
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
    
    public void addReport(Report report) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO [Report] (UserID, TimeCreated, Reason, PostID, Status, Note) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, report.getUserId());
            ps.setTimestamp(2, Timestamp.valueOf(report.getTimeCreated()));
            ps.setString(3, report.getReason());
            ps.setInt(4, report.getPostId());
            ps.setString(5, report.getStatus());
            if (report.getNote() != null) {
                ps.setString(6, report.getNote());
            } else {
                ps.setString(6, "");
            }

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closePreparedStatement(ps);
        }
    }

    public void addReport(int userId, LocalDateTime timeCreated, String reason, int postId) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO [Report] (UserID, TimeCreated, Reason, PostID, Status, Note) "
                    + "VALUES (?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setTimestamp(2, Timestamp.valueOf(timeCreated));
            ps.setString(3, reason);
            ps.setInt(4, postId);
            ps.setString(5, "Pending");
            ps.setString(6, "");

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closePreparedStatement(ps);
        }
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

}
