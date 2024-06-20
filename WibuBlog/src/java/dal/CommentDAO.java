/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Comment;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class CommentDAO extends DBContext {
    // Lấy một Comment bằng ID

    public Comment getCommentById(int commentId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Comment WHERE CommentID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, commentId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Comment(
                        rs.getInt("CommentID"),
                        rs.getInt("PostID"),
                        rs.getInt("UserID"),
                        rs.getString("Content"),
                        rs.getString("Status"),
                        rs.getInt("Vote"),
                        rs.getInt("ParentId"),
                        rs.getTimestamp("CreateAt").toLocalDateTime()
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return null;
    }

    // Lấy tất cả Comment cho một bài Post
    public List<Comment> getCommentsForPost(int postId) {
        List<Comment> commentList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Comment WHERE PostID = ?  ORDER BY CreateAt DESC";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, postId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment(
                        rs.getInt("CommentID"),
                        rs.getInt("PostID"),
                        rs.getInt("UserID"),
                        rs.getString("Content"),
                        rs.getString("Status"),
                        rs.getInt("Vote"),
                        rs.getInt("ParentId"),
                        rs.getTimestamp("CreateAt").toLocalDateTime()
                );
                commentList.add(comment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return commentList;
    }

    // Thêm một Comment mới
    public void addComment(Comment comment) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO Comment (PostID, UserID, Content, Status, Vote, ParentID, CreateAt) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, comment.getPostId());
            ps.setInt(2, comment.getUserId());
            ps.setString(3, comment.getContent());
            ps.setString(4, "active");
            ps.setInt(5, 0);
            // Set ParentId
            if (comment.getParentId() == null || comment.getParentId() == 0) {
                ps.setNull(6, java.sql.Types.INTEGER);
            } else {
                ps.setInt(6, comment.getParentId());
            }
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"))));
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closePreparedStatement(ps);
        }
    }

    // Cập nhật thông tin Comment
    public void updateComment(Comment comment) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE Comment SET PostID = ?, UserID = ?, Content = ?, Status = ?, Vote = ?, "
                    + "ParentID = ?, CreatedAt = ? WHERE CommentID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, comment.getPostId());
            ps.setInt(2, comment.getUserId());
            ps.setString(3, comment.getContent());
            ps.setString(4, comment.getStatus());
            ps.setInt(5, comment.getVote());
            ps.setInt(6, comment.getParentId());
            ps.setTimestamp(7, Timestamp.valueOf(comment.getCreateAt()));
            ps.setInt(8, comment.getCommentId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closePreparedStatement(ps);
        }
    }

    // Xóa một Comment
    public void deleteComment(int commentId) {
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM Comment WHERE CommentID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, commentId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closePreparedStatement(ps);
        }
    }

    public boolean updateVote(int commentId, int vote) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE Comment SET Vote = ? WHERE CommentID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, vote);
            ps.setInt(2, commentId);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closePreparedStatement(ps);
        }
    }

    public static void main(String[] args) {
        CommentDAO cod = new CommentDAO();
        Comment comment = new Comment(53, 1, "Hello world");
        cod.addComment(comment);
    }
}
