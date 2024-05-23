package dal;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

public class UserDAO extends DBContext {

    public User getUserByEmail(String email) {
        try {
            User user = new User();
            String sql = "select * from [user] where email = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getInt("RoleID"),
                        rs.getInt("Point"),
                        rs.getString("Status"),
                        rs.getString("Email"),
                        rs.getString("Fullname"),
                        rs.getInt("RankID"));
            }
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User getUserById(int userId) {
        try {
            User user = new User();
            String sql = "select * from [user] where UserID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, String.valueOf(userId));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getInt("RoleID"),
                        rs.getInt("Point"),
                        rs.getString("Status"),
                        rs.getString("Email"),
                        rs.getString("Fullname"),
                        rs.getInt("RankID"));
            }

            return user;

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean emailExists(String email) {
        int count = 0;
        try {
            String sql = "SELECT COUNT(UserID) as matching_ids FROM [user] WHERE email = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("matching_ids");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    public void changePassword(String hashedPassword, int userID){
        try {
            String sql = "update [user] set password = ? where UserID = ? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, hashedPassword);
            ps.setInt(2, userID);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}    