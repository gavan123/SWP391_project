package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

public class UserDAO extends DBContext {

    public UserDAO() {
    }

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public User getUserByEmail(String email) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM [user] WHERE email = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getInt("RoleID"),
                        rs.getInt("Point"),
                        rs.getString("Status"),
                        rs.getString("Email"),
                        rs.getString("Fullname"),
                        rs.getInt("RankID"),
                        rs.getString("ProfilePhoto") != null ? rs.getString("ProfilePhoto") : null,
                        rs.getString("PhoneNumber"),
                        rs.getTimestamp("DateOfBirth") != null ? rs.getTimestamp("DateOfBirth").toLocalDateTime() : null,
                        rs.getTimestamp("CreationDate") != null ? rs.getTimestamp("CreationDate").toLocalDateTime() : null);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return null;
    }
    public void setUserStatusByUserId(int userId, String userStatus){
        try {
            String sql = "update [user] set Status = ? where userid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userStatus);
            ps.setInt(2, userId);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<User> getTop10User() {
        try {
            String sql = "select top(10) * from [user]";
            ArrayList<User> top10UserList = new ArrayList();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                top10UserList.add(new User(rs.getInt("UserId"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getInt("RoleId"),
                        rs.getInt("Point"),
                        rs.getString("Status"),
                        rs.getString("Email"),
                        rs.getString("Fullname"),
                        rs.getInt("RankID"),
                        rs.getString("ProfilePhoto") != null ? rs.getString("ProfilePhoto") : null,
                        rs.getString("PhoneNumber") != null ? rs.getString("PhoneNumber") : null,
                        rs.getTimestamp("DateOfBirth") != null ? rs.getTimestamp("DateOfBirth").toLocalDateTime() : null,
                        rs.getTimestamp("CreationDate") != null ? rs.getTimestamp("CreationDate").toLocalDateTime() : null,
                        rs.getString("Bio") != null ? rs.getString("Bio") : null));
            }
            return top10UserList;
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public int getUserTotalPostLast3Days(int userId) {
        try {
            int totalUserPost = 0;
            String sql = "SELECT \n"
                    + "    [UserID],\n"
                    + "    COUNT(*) AS TotalPosts\n"
                    + "FROM \n"
                    + "    [dbo].[Post]\n"
                    + "WHERE \n"
                    + "    [PostTime] >= DATEADD(day, -3, GETDATE()) and userid = ? \n"
                    + "GROUP BY \n"
                    + "    [UserID];";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalUserPost = rs.getInt("TotalPosts");
                return totalUserPost;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<User> searchUser(String username) {
        try {
            String sql = "select * from [user] where username like '%" + username + "%'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<User> list = new ArrayList();
            while(rs.next()){
                list.add(new User(rs.getInt("UserId"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getInt("RoleId"),
                        rs.getInt("Point"),
                        rs.getString("Status"),
                        rs.getString("Email"),
                        rs.getString("Fullname"),
                        rs.getInt("RankID"),
                        rs.getString("ProfilePhoto") != null ? rs.getString("ProfilePhoto") : null,
                        rs.getString("PhoneNumber") != null ? rs.getString("PhoneNumber") : null,
                        rs.getTimestamp("DateOfBirth") != null ? rs.getTimestamp("DateOfBirth").toLocalDateTime() : null,
                        rs.getTimestamp("CreationDate") != null ? rs.getTimestamp("CreationDate").toLocalDateTime() : null,
                        rs.getString("Bio") != null ? rs.getString("Bio") : null));
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void setUserRoleIdByUserId(int userId,int roleId){
        try {
            String sql = "Update [user] set roleid = ? where userid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, roleId);
            ps.setInt(2, userId);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public User getUserById(int userId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM [user] WHERE UserID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getInt("RoleID"),
                        rs.getInt("Point"),
                        rs.getString("Status"),
                        rs.getString("Email"),
                        rs.getString("Fullname"),
                        rs.getInt("RankID"),
                        rs.getString("ProfilePhoto") != null ? rs.getString("ProfilePhoto") : null,
                        rs.getString("PhoneNumber"),
                        rs.getTimestamp("DateOfBirth") != null ? rs.getTimestamp("DateOfBirth").toLocalDateTime() : null,
                        rs.getTimestamp("CreationDate") != null ? rs.getTimestamp("CreationDate").toLocalDateTime() : null);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return null;
    }

    public void changePhoneNumber(String phonenumber, int userID) {
        PreparedStatement ps = null;
        try {
            String sql = "update [user] set phonenumber = ? where UserID = ? ";
            ps = connection.prepareStatement(sql);
            ps.setString(1, phonenumber);
            ps.setInt(2, userID);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closePreparedStatement(ps);
        }
    }

    public boolean emailExists(String email) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT COUNT(UserID) as matching_ids FROM [user] WHERE email = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("matching_ids") == 1;
            }
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return false;
    }

    public void changePassword(String hashedPassword, int userID) {
        PreparedStatement ps = null;
        try {
            String sql = "update [user] set password = ? where UserID = ? ";
            ps = connection.prepareStatement(sql);
            ps.setString(1, hashedPassword);
            ps.setInt(2, userID);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closePreparedStatement(ps);
        }
    }

    public void changeName(String newName, int userID) {
        PreparedStatement ps = null;
        try {
            String sql = "update [user] set Fullname = ? where UserID = ? ";
            ps = connection.prepareStatement(sql);
            ps.setString(1, newName);
            ps.setInt(2, userID);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closePreparedStatement(ps);
        }
    }

    public void changeEmail(String newEmail, int userID) {
        PreparedStatement ps = null;
        try {
            String sql = "update [user] set email = ? where UserID = ? ";
            ps = connection.prepareStatement(sql);
            ps.setString(1, newEmail);
            ps.setInt(2, userID);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closePreparedStatement(ps);
        }
    }

    public User getUserByUsername(String username) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from [user] where username = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getInt("RoleID"),
                        rs.getInt("Point"),
                        rs.getString("Status"),
                        rs.getString("Email"),
                        rs.getString("Fullname"),
                        rs.getInt("RankID"),
                        rs.getString("ProfilePhoto") != null ? rs.getString("ProfilePhoto") : null,
                        rs.getString("PhoneNumber") != null ? rs.getString("PhoneNumber") : null,
                        rs.getTimestamp("DateOfBirth") != null ? rs.getTimestamp("DateOfBirth").toLocalDateTime() : null,
                        rs.getTimestamp("CreationDate") != null ? rs.getTimestamp("CreationDate").toLocalDateTime() : null,
                        rs.getString("Bio") != null ? rs.getString("Bio") : null);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return null;
    }

    public void addUser(User user) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO [user] (Username, Password, RoleID, Point, Status, Email, Fullname, RankID) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPasswordHash());
            ps.setInt(3, user.getRoleId());
            ps.setInt(4, user.getPoint());
            ps.setString(5, user.getStatus());
            ps.setString(6, user.getEmail());
            ps.setString(7, user.getFullName());
            ps.setInt(8, user.getRankId());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closePreparedStatement(ps);
        }
    }

    public boolean updateProfile(int userId, String username, String fullName, String phoneNumber, LocalDateTime dateOfBirth) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE [user] SET Username = ?, "
                    + "Fullname = ?, PhoneNumber = ?, DateOfBirth = ? WHERE UserID = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, fullName);
            ps.setString(3, phoneNumber);
            ps.setTimestamp(4, java.sql.Timestamp.valueOf(dateOfBirth));
            ps.setInt(5, userId);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closePreparedStatement(ps);
        }
    }

    public boolean updatePhoneNumber(int userId, String phoneNumber) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE [user] SET PhoneNumber = ? WHERE UserID = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, phoneNumber);
            ps.setInt(2, userId);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closePreparedStatement(ps);
        }
    }

    public boolean deactiveAccount(int userId) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE [user] SET Status = 'deactive' WHERE UserID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closePreparedStatement(ps);
        }
    }

    public void updateProfilePhoto(int userId, String profilePhotoID) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE [user] SET ProfilePhoto = ? WHERE UserID = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, profilePhotoID);
            ps.setInt(2, userId);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    @Override
//    public void run() {
//        PreparedStatement ps = null;
//        try {
//            String sql = "UPDATE [user] SET "
//                    + "Username = null, "
//                    + "PasswordHash = null, "
//                    + "RoleId = null, "
//                    + "Point = null, "
//                    + "Status = null, "
//                    + "Email = null, "
//                    + "Fullname = null, "
//                    + "RankId = null, "
//                    + "ProfilePhoto = null, "
//                    + "PhoneNumber = null "
//                    + "WHERE Status = 'deactive' AND deactivation_date <= DATEADD(day, -7, CURRENT_TIMESTAMP)";
//            ps = connection.prepareStatement(sql);
//            int rowsUpdated = ps.executeUpdate();
//            if (rowsUpdated > 0) {
//                Logger.getLogger(UserDAO.class.getName()).log(Level.INFO, "Updated {0} accounts", rowsUpdated);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            closePreparedStatement(ps);
//        }
//    }
    public String getRankByRankID(int rankID) {
        try {
            String sql = "select * from [rank] where RankID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, rankID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String rank = rs.getString("name");
            return rank;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getRoleByRoleID(int roleID) {
        try {
            String sql = "select * from [Role] where RoleID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, roleID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String role = rs.getString("RoleName");
            return role;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getColorByRank(String rank) {
        try {
            String sql = "select * from [Rank] where name = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, rank);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String color = rs.getString("Color");
            return color;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void setPhoneNumber(String phoneNumber, int userID) {
        try {
            String sql = "update [user] set PhoneNumber = ? where userid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, phoneNumber);
            ps.setInt(2, userID);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkPhoneNumber(String phoneNumber) {
        try {
            String sql = "select * from user where PhoneNumber = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, phoneNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void updateBio(String content, int userID) {
        try {
            String sql = "update [user] set bio = ? where userID = ? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, content);
            ps.setInt(2, userID);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changeUsername(String newUsername, int userID) {
        try {
            String sql = "update [user] set username = ? where userID = ? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newUsername);
            ps.setInt(2, userID);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


}
