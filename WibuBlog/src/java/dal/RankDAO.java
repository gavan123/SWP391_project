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

    public Rank getRankByUserId(int userId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT r.RankID, r.Name AS RankName, r.PointsRequired, r.Color\n"
                    + "FROM [dbo].[Rank] r\n"
                    + "JOIN [dbo].[User] u ON u.RankID = r.RankID\n"
                    + "WHERE u.UserID = ?;";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Rank(
                        rs.getInt("RankID"),
                        rs.getString("RankName"),
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

    public static void main(String[] args) {
        RankDAO rankdao = new RankDAO();
        Rank rank = rankdao.getRankByUserId(1);
        if (rank != null) {
            System.out.println("Rank Information:");
            System.out.println("RankID: " + rank.getRankId());
            System.out.println("Name: " + rank.getName());
            System.out.println("Points Required: " + rank.getPointsRequired());
            System.out.println("Color: " + rank.getColor());
        } else {
            System.out.println("Rank not found for UserID = 1");
        }
    }
}
