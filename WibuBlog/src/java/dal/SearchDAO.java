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
 * @author minht
 */
public class SearchDAO extends DBContext {

    public List<Anime> searchAnimeByTitle(String keyword) {
        List<Anime> animeList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Anime WHERE Title LIKE ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Anime anime = new Anime(
                        rs.getInt("AnimeID"),
                        rs.getString("Title"),
                        rs.getString("Synopsis"),
                        rs.getString("Genre"),
                        rs.getInt("Episodes"),
                        rs.getString("Status"),
                        rs.getTimestamp("ReleaseDate").toLocalDateTime(),
                        rs.getString("Studio"),
                        rs.getString("ImageAnime")
                );
                animeList.add(anime);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SearchDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return animeList;
    }
    
    public List<Post> searchPostsByTitle(String keyword) {
        List<Post> postList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            String sql = "SELECT * FROM Post WHERE Title LIKE ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Post post = new Post(
                        rs.getInt("PostID"),
                        rs.getInt("UserID"),
                        rs.getInt("CategoryID"),
                        rs.getString("Title"),
                        rs.getString("Content"),
                        rs.getString("Source"),
                        rs.getString("ImageUrl"),
                        rs.getTimestamp("PostTime").toLocalDateTime(),
                        rs.getString("Status"),
                        rs.getInt("Vote"),
                        rs.getInt("View"),
                        rs.getInt("CommentCount")
                );
                postList.add(post);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SearchDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        
        return postList;
    }
    
}
