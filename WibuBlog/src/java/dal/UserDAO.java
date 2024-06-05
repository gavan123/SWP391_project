package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

public class UserDAO extends DBContext implements Runnable {

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
                        rs.getString("ProfilePhoto"),
                        rs.getString("PhoneNumber"),
                        rs.getTimestamp("DateOfBirth").toLocalDateTime(),
                        rs.getTimestamp("CreationDate").toLocalDateTime());
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return null;
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
                        rs.getString("ProfilePhoto"),
                        rs.getString("PhoneNumber"),
                        rs.getTimestamp("DateOfBirth").toLocalDateTime(),
                        rs.getTimestamp("CreationDate").toLocalDateTime());
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return null;
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
                        rs.getString("ProfilePhoto"),
                        rs.getString("PhoneNumber"),
                        rs.getTimestamp("DateOfBirth").toLocalDateTime(),
                        rs.getTimestamp("CreationDate").toLocalDateTime());
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

    @Override
    public void run() {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE [user] SET "
                    + "Username = null, "
                    + "PasswordHash = null, "
                    + "RoleId = null, "
                    + "Point = null, "
                    + "Status = null, "
                    + "Email = null, "
                    + "Fullname = null, "
                    + "RankId = null, "
                    + "ProfilePhoto = null, "
                    + "PhoneNumber = null "
                    + "WHERE Status = 'deactive' AND deactivation_date <= DATEADD(day, -7, CURRENT_TIMESTAMP)";
            ps = connection.prepareStatement(sql);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.INFO, "Updated {0} accounts", rowsUpdated);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closePreparedStatement(ps);
        }
    }
}
