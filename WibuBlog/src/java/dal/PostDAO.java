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

    // Lấy một số lượng giới hạn các bài đăng
    public List<Post> getLimitedPosts(int limit) {
        List<Post> postList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT TOP ? * FROM Post ORDER BY PostTime DESC";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, limit);
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

    // Lấy một số lượng giới hạn các bài đăng của một danh mục cụ thể
    public List<Post> getLimitedPostsByCategory(int limit, int categoryId) {
        List<Post> postList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT TOP " + limit + " p.*, (SELECT COUNT(*) FROM Comment c WHERE c.PostID = p.PostID) AS CommentCount\n"
                    + "FROM Post p\n"
                    + "WHERE p.CategoryID = ?\n"
                    + "ORDER BY p.PostTime DESC;";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, categoryId); // Thiết lập tham số categoryId
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
                        rs.getInt("View"),
                        rs.getInt("CommentCount") // lấy số lượng bình luận
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

    // Lấy tất cả các bài đăng của một danh mục cụ thể
    public List<Post> getAllPostsByCategory(int categoryId) {
        List<Post> postList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT p.*, (SELECT COUNT(*) FROM Comment c WHERE c.PostID = p.PostID) AS CommentCount\n"
                    + "FROM Post p\n"
                    + "WHERE p.CategoryID = ?\n"
                    + "ORDER BY p.PostTime DESC;";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, categoryId); // Thiết lập tham số categoryId
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
                        rs.getInt("View"),
                        rs.getInt("CommentCount") // lấy số lượng bình luận
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

    public static void main(String[] args) {
        // Tạo một đối tượng PostDAO
        PostDAO postDAO = new PostDAO();

        // Gọi phương thức getAllPosts để lấy tất cả bài đăng
        List<Post> posts = postDAO.getLimitedPostsByCategory(5, 10);

        // Hiển thị thông tin của từng bài đăng
        for (Post post : posts) {
            System.out.println("Post ID: " + post.getPostId());
            System.out.println("User ID: " + post.getUserId());
            System.out.println("Category ID: " + post.getCategoryId());
            System.out.println("Content: " + post.getContent());
            System.out.println("Source: " + post.getSource());
            System.out.println("Image: " + post.getImage());
            System.out.println("Post Time: " + post.getPostTime());
            System.out.println("Status: " + post.getStatus());
            System.out.println("Vote: " + post.getVote());
            System.out.println("View: " + post.getView());
            System.out.println();
        }
    }

}