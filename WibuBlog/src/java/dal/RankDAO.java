/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import model.Rank;

/**
 *
 * @author ADMIN
 */
public class RankDAO extends DBContext {

    public Rank getRankById(int rankId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM [rank] WHERE RankID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, rankId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Rank(
                        rs.getInt("RankID"),
                        rs.getString("Name"),
                        rs.getInt("PointsRequired"),
                        rs.getString("Color")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return null;
    }
}
