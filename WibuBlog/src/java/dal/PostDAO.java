/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Anime;
import model.Post;
import model.PostDetail;

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
            String sql = "SELECT * FROM Post WHERE CategoryId != 10 ORDER BY PostTime DESC";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post(
                        rs.getInt("PostID"),
                        rs.getInt("UserID"),
                        rs.getInt("CategoryID"),
                        rs.getString("Title"),
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

    public void updatePostImage(String url, int postID) {
        try {
            String sql = "update [post] set image = ? where postID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, url);
            ps.setInt(2, postID);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getPostIDJustInserted(int userID) {

        try {
            String sql = "select top (1) * from post where [userID] = ? \n"
                    + "order by PostTime desc";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int mediaId = rs.getInt("PostID");
            return mediaId;
        } catch (SQLException ex) {
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    // Lấy một số lượng giới hạn các bài đăng
    public List<Post> getLimitedPosts(int limit) {
        List<Post> postList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // Xây dựng câu truy vấn động
            String sql = "SELECT TOP " + limit + " * FROM Post WHERE CategoryId != 10 ORDER BY PostTime DESC";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post(
                        rs.getInt("PostID"),
                        rs.getInt("UserID"),
                        rs.getInt("CategoryID"),
                        rs.getString("Title"),
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
                        rs.getString("Title"),
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

    public void insertPostGenre(int postID, int genreID) {
        try {
            String sql = "insert into PostGenre (PostID,GenreID) values(?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, postID);
            ps.setInt(2, genreID);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                        rs.getString("Title"),
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
                        rs.getString("Title"),
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
            String sql = "SELECT * FROM Post WHERE Title LIKE ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post(
                        rs.getInt("PostID"),
                        rs.getInt("UserID"),
                        rs.getInt("CategoryID"),
                        rs.getString("Title"),
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

    public PostDetail getPostDetailById(int postID) {
        PostDetail postDetail = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT p.PostID, u.Username, c.[Name] AS CategoryName, g.[Name] AS GenreName, "
                    + "p.Title, p.Content, p.Source, p.[Image], p.PostTime, p.[Status], p.Vote, p.[View], "
                    + "r.[Name] AS [Rank], r.Color "
                    + "FROM Post AS p "
                    + "JOIN [User] AS u ON p.UserID = u.UserID "
                    + "JOIN [Category] AS c ON p.CategoryID = c.CategoryID "
                    + "JOIN [PostGenre] AS pg ON p.PostID = pg.PostID "
                    + "JOIN [Genre] AS g ON g.GenreID = pg.GenreID "
                    + "JOIN [Rank] AS r ON u.RankID = r.RankID "
                    + "WHERE p.PostID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, postID);
            rs = ps.executeQuery();
            if (rs.next()) {
                postDetail = new PostDetail(
                        rs.getInt("PostID"),
                        rs.getString("Username"),
                        rs.getString("CategoryName"),
                        rs.getString("GenreName"),
                        rs.getString("Title"),
                        rs.getString("Content"),
                        rs.getString("Source"),
                        rs.getString("Image"),
                        rs.getTimestamp("PostTime").toLocalDateTime(),
                        rs.getString("Status"),
                        rs.getInt("Vote"),
                        rs.getInt("View"),
                        rs.getString("Rank"),
                        rs.getString("Color")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return postDetail;
    }

    public boolean updateVote(int postId, int vote) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE Post SET Vote = ? WHERE PostID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, vote);
            ps.setInt(2, postId);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closePreparedStatement(ps);
        }
    }

    public boolean hasUserVoted(Post votepost) {
        String checkSql = "SELECT COUNT(*) FROM VoteUserPost WHERE UserID = ? AND PostID = ?";

        try (PreparedStatement checkPs = connection.prepareStatement(checkSql)) {
            checkPs.setInt(1, votepost.getUserId());
            checkPs.setInt(2, votepost.getPostId());

            try (ResultSet rs = checkPs.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public void addVote(Post votepost) {
        String insertSql = "INSERT INTO VoteUserPost (UserID, PostID, Status) VALUES (?, ?, 'vote')";

        try (PreparedStatement insertPs = connection.prepareStatement(insertSql)) {
            insertPs.setInt(1, votepost.getUserId());
            insertPs.setInt(2, votepost.getPostId());

            int rowsInserted = insertPs.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean updateView(int postId) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE Post SET [View] = [View] + 1 WHERE PostID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, postId);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closePreparedStatement(ps);
        }
    }

    public Integer getUserIdByUsername(String username) {
        Integer userId = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "UserID FROM [User] WHERE username = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("UserID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return userId;
    }

    public Integer getCategoryIdByName(String categoryName) {
        Integer categoryId = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = " SELECT CategoryID FROM Category WHERE Name =  ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, categoryName);
            rs = ps.executeQuery();
            if (rs.next()) {
                categoryId = rs.getInt("CategoryID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return categoryId;
    }

    public boolean createPost(Post post) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO Post ([UserID], [CategoryID], [Title], "
                    + "[Content], [Source], [Image], "
                    + "[PostTime], [Status], [Vote], [View]) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, 'active', 0, 0)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, post.getUserId());
            ps.setInt(2, post.getCategoryId());
            ps.setString(3, post.getTitle());
            ps.setString(4, post.getContent());
            ps.setString(5, post.getSource());
            ps.setString(6, post.getImage());

            // Set the current timestamp as PostTime
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closePreparedStatement(ps);
        }
    }

    public ArrayList<Post> getUserPost(int userID) {
        try {
            String sql = "select * from [post] where userid = ?";
            PreparedStatement ps = null;
            ps = connection.prepareStatement(sql);
            ResultSet rs = rs = ps.executeQuery();
            ArrayList<Post> userPostList = new ArrayList<>();
            while (rs.next()) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        PostDAO postDAO = new PostDAO();

        Post post = new Post();
        post.setUserId(1);
        post.setCategoryId(1);
        post.setTitle("Sample Post Title");
        post.setContent("This is a sample content for the post.");
        post.setSource("Sample Source");
        post.setImage("sample_image.jpg");
        post.setPostTime(LocalDateTime.now());
        post.setStatus("active");
        post.setVote(0);
        post.setView(0);
        PostDetail post1 = postDAO.getPostDetailById(54);
        System.out.println(post1.getTitle());

        boolean isPostCreated = postDAO.createPost(post);
        if (isPostCreated) {
            System.out.println("Post has been created successfully.");
        } else {
            System.out.println("Failed to create the post.");
        }

    }

}
