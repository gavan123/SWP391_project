package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

public class UserDAO extends DBContext {

    public User getUserByEmail(String email) {
        try {
            User user = null;
            String sql = "SELECT * FROM [user] WHERE email = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getInt("RoleID"),
                        rs.getInt("Point"),
                        rs.getString("Status"),
                        rs.getString("Email"),
                        rs.getString("Fullname"),
                        rs.getInt("RankID"),
                        rs.getString("ProfilePhoto"),
                        rs.getString("PhoneNumber"),
                        rs.getTimestamp("DateOfBirth").toLocalDateTime(),
                        rs.getTimestamp("CreationDate").toLocalDateTime());
            }
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User getUserById(int userId) {
        try {
            User user = null;
            String sql = "SELECT * FROM [user] WHERE UserID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getInt("RoleID"),
                        rs.getInt("Point"),
                        rs.getString("Status"),
                        rs.getString("Email"),
                        rs.getString("Fullname"),
                        rs.getInt("RankID"),
                        rs.getString("ProfilePhoto"),
                        rs.getString("PhoneNumber"),
                        rs.getTimestamp("DateOfBirth").toLocalDateTime(),
                        rs.getTimestamp("CreationDate").toLocalDateTime());
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

    public void changePassword(String hashedPassword, int userID) {
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

    public User getUserByUsername(String username) {
        try {
            String sql = "select *from [user] where username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getInt("RoleID"),
                        rs.getInt("Point"),
                        rs.getString("Status"),
                        rs.getString("Email"),
                        rs.getString("Fullname"),
                        rs.getInt("RankID"),
                        rs.getString("ProfilePhoto"),
                        rs.getString("PhoneNumber"),
                        rs.getTimestamp("DateOfBirth").toLocalDateTime(),
                        rs.getTimestamp("CreationDate").toLocalDateTime());
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public boolean updateProfile(int userId, String username, String fullName, LocalDateTime dateOfBirth) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE [user] SET Username = ?, Fullname = ?, DateOfBirth = ? WHERE UserID = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, fullName);
            ps.setTimestamp(3, java.sql.Timestamp.valueOf(dateOfBirth));
            ps.setInt(4, userId);
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

    public boolean updateProfilePhoto(int userId, String profilePhoto) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE [user] SET ProfilePictureURL = ? WHERE UserID = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, profilePhoto);
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

}
