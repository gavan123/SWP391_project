/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Ban;

/**
 *
 * @author admin
 */
public class BanDAO extends DBContext{
    public void insertManualBan(Ban ban){
        try {
            String sql = "insert into [ban] values (?,?,?,?,?,null)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,ban.getBannedUserId());
            ps.setInt(2,ban.getBanSourceId());
            ps.setString(3,ban.getBanReason());
            ps.setTimestamp(4, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            ps.setTimestamp(5, java.sql.Timestamp.valueOf(ban.getExpireDate()));
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(BanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertAutomaticBan(Ban ban){
           try {
            String sql = "insert into [ban] values (?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,ban.getBannedUserId());
            ps.setInt(2,ban.getBanSourceId());
            ps.setString(3,ban.getBanReason());
            ps.setTimestamp(4, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            ps.setTimestamp(5, java.sql.Timestamp.valueOf(ban.getExpireDate()));
            ps.setInt(6, ban.getSourcePostId());
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(BanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
