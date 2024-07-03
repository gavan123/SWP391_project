/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author minht
 */
public class LoginDAO extends DBContext {

    public LoginDAO() {
        this.connection = connection;
    }

    public String getHashedPassword(String username) {
        String query = "SELECT password FROM [User] WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("password");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getUserRole(String username) {
        String query = "SELECT RoleName FROM [User] "
                + "JOIN Role ON [User].RoleID = Role.RoleID "
                + "WHERE [User].Username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("RoleName");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean isAdmin(String username) {
        return "Admin".equals(getUserRole(username));
    }

    public boolean isMod(String username) {
        return "Mod".equals(getUserRole(username));
    }

    public boolean isMember(String username) {
        return "Member".equals(getUserRole(username));
    }
}
