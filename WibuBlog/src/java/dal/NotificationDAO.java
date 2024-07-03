/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Notification;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.ZoneId;

/**
 *
 * @author admin
 */
public class NotificationDAO extends DBContext {

    public void createUpvoteNotification(int sourcePostId, int sourceUserId, int targetUserId) {
        try {
            PostDAO pd = new PostDAO();
            UserDAO ud = new UserDAO();
            String sql = "insert into [Notification] values ( ? , ? , ?, ?, ?) ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, sourcePostId);
            ps.setInt(2, sourceUserId);
            ps.setInt(3, targetUserId);
            ps.setString(4, ud.getUserById(sourceUserId).getUsername() + " has given an upvote on " + pd.getPostById(sourcePostId).getTitle());
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"))));;
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createDownvoteNotification(int sourcePostId, int sourceUserId, int targetUserId) {
        try {
            PostDAO pd = new PostDAO();
            UserDAO ud = new UserDAO();
            String sql = "insert into [Notification] values ( ? , ? , ?, ?, ?) ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, sourcePostId);
            ps.setInt(2, sourceUserId);
            ps.setInt(3, targetUserId);
            ps.setString(4, ud.getUserById(sourceUserId).getUsername() + " has given an downvote on " + pd.getPostById(sourcePostId).getTitle());
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"))));;
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createCommentNotification(int sourcePostId, int sourceUserId, int targetUserId) {
        try {
            PostDAO pd = new PostDAO();
            UserDAO ud = new UserDAO();
            String sql = "insert into [Notification] values ( ? , ? , ?, ?, ?) ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, sourcePostId);
            ps.setInt(2, sourceUserId);
            ps.setInt(3, targetUserId);
            ps.setString(4, ud.getUserById(sourceUserId).getUsername() + " has commented on " + pd.getPostById(sourcePostId).getTitle());
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"))));;
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createUploadedPostNotification(int sourcePostId, int targetUserId) {
        try {
            String sql = "insert into [notification] (sourcePostId,targetUserId,content,timeCreated) "
                    + "values ( ? , ? , ? ,?) ";
            PostDAO pd = new PostDAO();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, sourcePostId);
            ps.setInt(2, targetUserId);
            ps.setString(3, "Your post " + pd.getPostById(sourcePostId).getTitle() + " has been uploaded");
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"))));
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createRankUpNotification(int targetUserId, int rankid) {
        try {
            RankDAO rd = new RankDAO();
            String sql = "insert into [Notification] ( targetUserid,content,timecreated) values ( ? , ? , ?, ?, ?) ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, targetUserId);
            ps.setString(2, "Your rank has been updated to " + rd.getRankById(rankid));
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"))));
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Notification> getTop10Notification(int targetUserId) {
        try {
            String sql = "Select top 10  * from [Notification] where targetUserId = ? order by timecreated desc";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, targetUserId);
            ArrayList<Notification> list = new ArrayList();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Notification(rs.getInt("NotificationId"),
                        rs.getInt("sourcePostId") != 0 ? rs.getInt("sourcePostId") : 0,
                        rs.getInt("sourceUserId") != 0 ? rs.getInt("sourceUserId") : 0,
                        rs.getInt("targetUserId"),
                        rs.getString("Content"),
                        rs.getTimestamp("timeCreated").toLocalDateTime()));
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void deleteNotificationByNotificationId(int notificationId){
        try {
            String sql = "delete from [Notification] where NotificationId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, notificationId);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteNotificationBySourcePostId(int sourcePostId){
        try {
            String sql = "delete from [Notification] where SourcePostId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, sourcePostId);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createBanNotification(int sourceUserId, int targetUserId,String content, String expireTime){
         try {
            String sql = "insert into [notification] (SourceUserId,TargetUserId,Content,TimeCreated) values (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, sourceUserId);
            ps.setInt(2, targetUserId);
            ps.setString(3, "You have been banned from Wibu Forum for: " + content + " until " + expireTime);
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"))));
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    public void createPromoteNotification(int targetUserId){
        try {
            String sql = "insert into [notification] (TargetUserId,Content,TimeCreated) values (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, targetUserId);
            ps.setString(2, "You have been promoted to Mod");
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"))));
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createDemoteNotification(int targetUserId){
        try {
            String sql = "insert into [notification] (TargetUserId,Content,TimeCreated) values (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, targetUserId);
            ps.setString(2, "You have been demoted from Mod");
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"))));
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        NotificationDAO nd = new NotificationDAO();
        ArrayList<Notification> list = nd.getTop10Notification(6);
        for(Notification x : list){
            System.out.println(x.getContent() + " " + x.getPostTime());
        }
    }
    
    
}
