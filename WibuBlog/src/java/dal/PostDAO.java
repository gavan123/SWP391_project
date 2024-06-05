/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Anime;
import model.Post;

/**
 *
 * @author ADMIN
 */
public class PostDAO extends DBContext {
    // Lấy tất cả các bài đăng sắp xếp theo thời gian giảm dần

    public List<Post> getAllPosts() {
        List<Post> postList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Post ORDER BY PostTime DESC";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post(
                        rs.getInt("PostID"),
                        rs.getInt("UserID"),
                        rs.getInt("CategoryID"),
                        rs.getString("Content"),
                        rs.getString("Source"),
                        rs.getString("Image"),
                        rs.getString("Status"),
                        rs.getInt("Vote"),
                        rs.getInt("View")
                );
                postList.add(post);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return postList;
    }

    // Lấy bài đăng dựa trên PostID
    public Post getPostById(int postId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Post WHERE PostID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, postId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Post(
                        rs.getInt("PostID"),
                        rs.getInt("UserID"),
                        rs.getInt("CategoryID"),
                        rs.getString("Content"),
                        rs.getString("Source"),
                        rs.getString("Image"),
                        rs.getTimestamp("PostTime").toLocalDateTime(),
                        rs.getString("Status"),
                        rs.getInt("Vote"),
                        rs.getInt("View")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return null; // Trả về null nếu không tìm thấy bài đăng
    }

    // Tìm kiếm bài đăng theo tên
    public List<Post> searchByName(String name) {
        List<Post> postList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Post WHERE Content LIKE ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post(
                        rs.getInt("PostID"),
                        rs.getInt("UserID"),
                        rs.getInt("CategoryID"),
                        rs.getString("Content"),
                        rs.getString("Source"),
                        rs.getString("Image"),
                        rs.getTimestamp("PostTime").toLocalDateTime(),
                        rs.getString("Status"),
                        rs.getInt("Vote"),
                        rs.getInt("View")
                );
                postList.add(post);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return postList;
    }
}
