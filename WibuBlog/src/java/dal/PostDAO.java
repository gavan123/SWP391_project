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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Post;
import model.PostDetail;
import model.User;

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

    public void deleteGenre(int postId) {
        try {
            String sql = "delete from [postgenre] where Postid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, postId);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public String getUserProfilePhotoByUsername(String username) {
        try {
            String sql = "select * from [user] where username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("ProfilePhoto");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public int getPostIDJustInserted(int userID) {
        try {
            String sql = "select top (1) * from post where [userID] = ? \n"
                    + "order by PostId desc";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int mediaId = rs.getInt("PostID");
            return mediaId;
        } catch (SQLException ex) {
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
                    + "r.[Name] AS [Rank], r.Color ,u.[Bio]"
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
                        rs.getString("Color"),
                        rs.getString("Bio")
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

    public ArrayList<PostDetail> getPostDetailByUserId(int userID) {
        PostDetail postDetail = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT p.PostID, u.Username, c.[Name] AS CategoryName, g.[Name] AS GenreName, \n"
                    + "                    p.Title, p.Content, p.Source, p.[Image], p.PostTime, p.[Status], p.Vote, p.[View], \n"
                    + "                    r.[Name] AS [Rank], r.Color \n"
                    + "                    FROM Post AS p \n"
                    + "                    JOIN [User] AS u ON p.UserID = u.UserID \n"
                    + "                    JOIN [Category] AS c ON p.CategoryID = c.CategoryID \n"
                    + "                    JOIN [PostGenre] AS pg ON p.PostID = pg.PostID \n"
                    + "                    JOIN [Genre] AS g ON g.GenreID = pg.GenreID \n"
                    + "                    JOIN [Rank] AS r ON u.RankID = r.RankID \n"
                    + "					where u.userid = ? order by p.posttime desc";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            ArrayList<PostDetail> list = new ArrayList();
            while (rs.next()) {
                list.add(new PostDetail(
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
                        rs.getString("Color")));
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return null;
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

    public boolean hasUserVoted(int userId, int postId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT COUNT(*) FROM VoteUserPost WHERE UserID = ? AND PostID = ? ";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, postId);

            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
    }

    public String checkVoteStatus(int userId, int postId) {
        String sql = "SELECT Status FROM VoteUserPost WHERE UserID = ? AND PostID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, postId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Status").trim(); // Return the status
                }
                return null; // No vote found for this user and post
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, "Error checking vote status", ex);
            return null; // Handle SQL exception
        }
    }

    public void addUserVote(int userId, int postId, String status) {
        PreparedStatement ps = null;

        try {
            String insertSql = "INSERT INTO VoteUserPost (UserID, PostID, Status) VALUES (?, ?, ?)";
            ps = connection.prepareStatement(insertSql);
            ps.setInt(1, userId);
            ps.setInt(2, postId);
            ps.setString(3, status);

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Vote added successfully."); // Optional: Print success message
            } else {
                System.out.println("Failed to add vote."); // Optional: Print failure message
            }

        } catch (SQLException ex) {
        } finally {
            closePreparedStatement(ps);
        }
    }

    public void updateUserVote(int userId, int postId, String status) {
        PreparedStatement ps = null;

        try {
            String updateSql = "UPDATE VoteUserPost SET Status = ? WHERE UserID = ? AND PostID = ?";
            ps = connection.prepareStatement(updateSql);
            ps.setString(1, status);
            ps.setInt(2, userId);
            ps.setInt(3, postId);

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Vote updated successfully."); // Optional: Print success message
            } else {
                System.out.println("Failed to update vote."); // Optional: Print failure message
            }
        } catch (SQLException ex) {
        } finally {
            closePreparedStatement(ps);
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
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = rs = ps.executeQuery();
            ps.setInt(1, userID);
            ArrayList<Post> userPostList = new ArrayList<>();
            while (rs.next()) {
                userPostList.add(new Post(rs.getInt("PostID"),
                        rs.getInt("UserID"),
                        rs.getInt("CategoryID"),
                        rs.getString("Title"),
                        rs.getString("Content"),
                        rs.getString("Source"),
                        rs.getString("Image"),
                        rs.getTimestamp("DateOfBirth").toLocalDateTime(),
                        rs.getString("Status"),
                        rs.getInt("Vote"),
                        rs.getInt("view")));

            }
            return userPostList;

        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    public boolean deletePost(int postId) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE Post SET Status = 'deactive' WHERE PostID = ?";
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

    public String encodeImageName(int userID) {
        LocalDateTime datetime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_hhmmss");
        String formattedDateTime = datetime.format(formatter);
        return userID + "_" + formattedDateTime;
    }


    public int getUserIdOfPostByPostID(int postId){
        int userId = 0;
        try {
            String sql = "select * from [post] where postid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("UserID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userId;
    }    


    public static void main(String[] args) {
        PostDAO postDAO = new PostDAO();
        List<Post> post = postDAO.getLimitedPosts(10);
        for (Post post1 : post) {
            System.out.println(post1.getPostId());
        }

    }

    public int getTotalPostLast3Days() {
        try {
            int count = 0;
            String sql = "SELECT * FROM [dbo].[Post] WHERE [PostTime] >= DATEADD(day, -3, GETDATE());";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ++count;
            }
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public float getAvgPostPerDayLastMonth() {
        float avgPost = 0;
        try {
            String sql = "WITH PostCounts AS (\n"
                    + "    SELECT \n"
                    + "        CAST([PostTime] AS DATE) AS PostDate,\n"
                    + "        COUNT(*) AS PostCount\n"
                    + "    FROM \n"
                    + "        [dbo].[Post]\n"
                    + "    WHERE \n"
                    + "        [PostTime] >= DATEADD(day, -30, GETDATE()) -- Adjust the interval as needed\n"
                    + "    GROUP BY \n"
                    + "        CAST([PostTime] AS DATE)\n"
                    + ")\n"
                    + "SELECT \n"
                    + "    CAST(AVG(CAST(PostCount AS FLOAT)) AS DECIMAL(10, 1)) AS AvgPostPerDay\n"
                    + "FROM \n"
                    + "    PostCounts;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                avgPost = Float.parseFloat(rs.getString("AvgPostPerDay"));
                return avgPost;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return avgPost;
    }

    public int getTotalPost() {
        try {
            int totalPost = 0;
            String sql = "Select count(*) as TotalPosts from [post]";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalPost = rs.getInt("TotalPosts");
                return totalPost;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getTotalMember() {
        try {
            int totalUser = 0;
            String sql = "select count(*) as TotalMembers from [user] where RoleId != 1";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalUser = rs.getInt("TotalMembers");
                return totalUser;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<User> getTop10UserByPoint() {
        try {
            String sql = "select top(10) * from [user] order by point desc";
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
    
     public ArrayList<User> getAllUser() {
        try {
            String sql = "select * from [user]";
            ArrayList<User> userList = new ArrayList();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userList.add(new User(rs.getInt("UserId"),
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
            return userList;
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
       
    public ArrayList<User> AllDeactivatedUser() {
        try {
            String sql = "select * from [user] where [status] = 'Deactive' ";
            ArrayList<User> deactivatedUserList = new ArrayList();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                deactivatedUserList.add(new User(rs.getInt("UserId"),
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
            return deactivatedUserList;
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Post> getTop6VotedPost(){
        try {
            String sql = "SELECT TOP 6 * FROM [dbo].[Post] WHERE [PostTime] >= DATEADD(day, -14, GETDATE())\n" +
                    " ORDER BY [Vote] DESC;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<Post> list = new ArrayList();
            while(rs.next()){
                list.add(new Post(rs.getInt("PostId"),
                                rs.getInt("UserId"),
                rs.getInt("CategoryID"),
                rs.getString("Title"),
                rs.getString("Content"),
                rs.getString("Source"),
                rs.getString("Image"),
                rs.getTimestamp("PostTime").toLocalDateTime(),
                rs.getString("Status"),
                rs.getInt("Vote"),
                rs.getInt("view")));
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String trimPostTitle(String title){
        return title.substring(0,12) + "...";
    }
   
}
