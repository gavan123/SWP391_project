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
    public ArrayList<User> getUserEmail (String email){
        try {
            String sql = "select * from [user] where email = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            ArrayList <User> list = new ArrayList();
            while(rs.next()){
                User user = new User(rs.getInt("UserID"),
                                     rs.getString("Username"),
                                     rs.getString("Password"),
                                     rs.getInt("RoleID"),
                                     rs.getInt("Point"),
                                     rs.getString("Status"),
                                     rs.getString("Email"),
                                     rs.getString("Fullname"),
                                     rs.getInt("RankID"));
                list.add(user);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            return null;
    }
}
