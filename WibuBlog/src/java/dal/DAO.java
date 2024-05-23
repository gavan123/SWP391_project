/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class DAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public User checkUserExist(String user) throws Exception{
        String query = "select * from User\n"
                + "where [Username] = ?\n";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while(rs.next()){
                return new User(rs.getInt("UserID"),
                                     rs.getString("Username"),
                                     rs.getString("Password"),
                                     rs.getInt("RoleID"),
                                     rs.getInt("Point"),
                                     rs.getString("Status"),
                                     rs.getString("Email"),
                                     rs.getString("Fullname"),
                                     rs.getInt("RankID"));
            }
        }catch (Exception e) {
        }
        return null;
    }
    
    public void register(String user, String pass, String email, String fullName){
        String query = "insert into account\n" 
                + "values(?, ?, 3, 0, active, ?, ?, 0)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setString(3, email);
            ps.setString(4, fullName);
            ps.executeUpdate();
        } catch (Exception e) {
        }
        
    }
}
