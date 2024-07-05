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
import model.Ticket;

public class TicketDAO extends DBContext {

    public TicketDAO() {
    }

    public TicketDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Ticket> getAllTickets() {
        List<Ticket> ticketList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from [Ticket]";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ticket report = new Ticket(
                        rs.getInt("TicketID"),
                        rs.getInt("UserID"),
                        rs.getTimestamp("TimeCreated").toLocalDateTime(),
                        rs.getString("Content"),
                        rs.getString("Status"),
                        rs.getString("Note"));

                ticketList.add(report);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return ticketList;
    }

    public Ticket getTicketById(int ticketId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM [Ticket] WHERE TicketID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, ticketId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Ticket(
                        rs.getInt("TicketID"),
                        rs.getInt("UserID"),
                        rs.getTimestamp("TimeCreated").toLocalDateTime(),
                        rs.getString("Content"),
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

    public List<Ticket> getTicketsByUserId(int userId) {
        List<Ticket> ticketList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from [Ticket] where UserID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ticket ticket = new Ticket(
                        rs.getInt("TicketID"),
                        rs.getInt("UserID"),
                        rs.getTimestamp("TimeCreated").toLocalDateTime(),
                        rs.getString("Content"),
                        rs.getString("Status"),
                        rs.getString("Note"));

                ticketList.add(ticket);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return ticketList;
    }

    public List<Ticket> getTicketsByStatus(String status) {
        List<Ticket> ticketList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from [Ticket] where Status = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, status);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ticket report = new Ticket(
                        rs.getInt("TicketID"),
                        rs.getInt("UserID"),
                        rs.getTimestamp("TimeCreated").toLocalDateTime(),
                        rs.getString("Content"),
                        rs.getString("Status"),
                        rs.getString("Note"));

                ticketList.add(report);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return ticketList;
    }

    public List<Ticket> getPendingTickets() {
        List<Ticket> ticketList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from [Ticket] where Status = N'Pending' order by [TimeCreated] desc";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ticket report = new Ticket(
                        rs.getInt("TicketID"),
                        rs.getInt("UserID"),
                        rs.getTimestamp("TimeCreated").toLocalDateTime(),
                        rs.getString("Content"),
                        rs.getString("Status"),
                        rs.getString("Note"));

                ticketList.add(report);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return ticketList;
    }
    
    public List<Ticket> getUserPendingTickets(int userId) {
        List<Ticket> ticketList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from [Ticket] where Status = N'Pending' and userId = ? order by [TimeCreated] desc";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ticket report = new Ticket(
                        rs.getInt("TicketID"),
                        rs.getInt("UserID"),
                        rs.getTimestamp("TimeCreated").toLocalDateTime(),
                        rs.getString("Content"),
                        rs.getString("Status"),
                        rs.getString("Note"));

                ticketList.add(report);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return ticketList;
    }

    
    public List<Ticket> getUserResolvedTickets(int userId) {
        List<Ticket> ticketList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from [Ticket] where (Status = N'Approved' OR Status = N'Rejected') and userid = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ticket report = new Ticket(
                        rs.getInt("TicketID"),
                        rs.getInt("UserID"),
                        rs.getTimestamp("TimeCreated").toLocalDateTime(),
                        rs.getString("Content"),
                        rs.getString("Status"),
                        rs.getString("Note"));

                ticketList.add(report);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return ticketList;
    }
    
     public List<Ticket> getResolvedTickets() {
        List<Ticket> ticketList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from [Ticket] where Status = N'Approved' OR Status = N'Rejected'";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ticket report = new Ticket(
                        rs.getInt("TicketID"),
                        rs.getInt("UserID"),
                        rs.getTimestamp("TimeCreated").toLocalDateTime(),
                        rs.getString("Content"),
                        rs.getString("Status"),
                        rs.getString("Note"));

                ticketList.add(report);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return ticketList;
    }

    public void addTicket(Ticket ticket) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO [Ticket] (UserID, TimeCreated, Content, Status, Note) "
                    + "VALUES (?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, ticket.getUserId());
            ps.setTimestamp(2, Timestamp.valueOf(ticket.getTimeCreated()));
            ps.setString(3, ticket.getContent());
            ps.setString(4, ticket.getStatus());
            ps.setString(5, ticket.getNote());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closePreparedStatement(ps);
        }
    }

    public void addTicket(int userId, LocalDateTime timeCreated, String content) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO [Report] (UserID, TimeCreated, Content, Status, Note) "
                    + "VALUES (?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setTimestamp(2, Timestamp.valueOf(timeCreated));
            ps.setString(3, content);
            ps.setString(4, "Pending");
            ps.setString(5, "");

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closePreparedStatement(ps);
        }
    }

    public boolean setStatusPending(int ticketId) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE [Ticket] SET Status = 'Pending' WHERE TicketID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, ticketId);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closePreparedStatement(ps);
        }
    }

    public boolean setStatusApproved(int ticketId) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE [Ticket] SET Status = 'Approved' WHERE TicketID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, ticketId);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closePreparedStatement(ps);
        }
    }

    public boolean setStatusRejected(int ticketId) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE [Ticket] SET Status = 'Rejected' WHERE TicketID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, ticketId);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closePreparedStatement(ps);
        }
    }
    
    public boolean setNote(int ticketId,String note) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE [Ticket] SET Note = ? WHERE TicketID = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, note);
            ps.setInt(2, ticketId);           
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closePreparedStatement(ps);
        }
    }
    public boolean deleteTicket(int ticketId) {
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM [Ticket] WHERE TicketID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, ticketId);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closePreparedStatement(ps);
        }
    }

    public int getAllTicket(boolean flag) {
        try {
            String sql;
            if (flag == true) {
                sql = "select count(*) as PendingTicket from ticket where status == 'Pending'";
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt("PendingTicket");
                } else {
                    return 0;
                }
            } else {
                sql = "select count(*) as ResolvedTicket from ticket where status != 'Pending'";
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt("ResolvedTicket");
                } else {
                    return 0;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    public void updateTicketContent(String newContent, int ticketId){
        try {
            String sql = "update [ticket] set content = ? where ticketid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newContent);
            ps.setInt(2, ticketId);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
