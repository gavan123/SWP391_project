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
import model.Genre;
import model.TopViewedGenre;

/**
 *
 * @author ADMIN
 */
public class GenreDAO extends DBContext {

    // Lấy Genre bằng ID
    public Genre getGenreById(int genreId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Genre WHERE GenreID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, genreId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Genre(
                        rs.getInt("GenreID"),
                        rs.getString("Name"),
                        rs.getString("Description")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(GenreDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return null;
    }

    public List<Genre> getAllGenresName() {
        List<Genre> genreList = new ArrayList<>();
        String sql = "SELECT GenreID, [Name] FROM Genre";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int genreID = rs.getInt("GenreID");
                String name = rs.getString("Name");
                Genre genre = new Genre(genreID, name);
                genreList.add(genre);
            }

        } catch (SQLException ex) {
            Logger.getLogger(GenreDAO.class.getName()).log(Level.SEVERE, "Error fetching genres", ex);
        }

        return genreList;
    }

    // Lấy tất cả Genre
    public List<Genre> getAllGenres() {
        List<Genre> genreList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Genre";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Genre genre = new Genre(
                        rs.getInt("GenreID"),
                        rs.getString("Name"),
                        rs.getString("Description")
                );
                genreList.add(genre);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GenreDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return genreList;
    }

    // Thêm một Genre mới
    public void addGenre(Genre genre) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO Genre (Name, Description) VALUES (?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, genre.getName());
            ps.setString(2, genre.getDescription());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GenreDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closePreparedStatement(ps);
        }
    }

    // Cập nhật thông tin Genre
    public void updateGenre(Genre genre) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE Genre SET Name = ?, Description = ? WHERE GenreID = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, genre.getName());
            ps.setString(2, genre.getDescription());
            ps.setInt(3, genre.getGenreId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GenreDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closePreparedStatement(ps);
        }
    }

    // Xóa một Genre
    public void deleteGenre(int genreId) {
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM Genre WHERE GenreID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, genreId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GenreDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closePreparedStatement(ps);
        }
    }

    public ArrayList<TopViewedGenre> getTop6ViewedGenre() {
        try {
            String sql = "SELECT top 6 \n"
                    + "    g.Name AS Genre,\n"
                    + "    COUNT(DISTINCT p.PostID) AS TotalPosts,\n"
                    + "    SUM(p.[View]) AS TotalView\n"
                    + "FROM \n"
                    + "    Genre g\n"
                    + "JOIN \n"
                    + "    PostGenre pg ON g.GenreID = pg.GenreID\n"
                    + "JOIN \n"
                    + "    (\n"
                    + "        SELECT \n"
                    + "            p.PostID,\n"
                    + "            p.[View]\n"
                    + "        FROM \n"
                    + "            Post p\n"
                    + "        WHERE \n"
                    + "            p.Status = 'Active' -- Assuming there's a status column in Post table\n"
                    + "    ) p ON pg.PostID = p.PostID\n"
                    + "GROUP BY \n"
                    + "    g.Name\n"
                    + "ORDER BY \n"
                    + "    TotalView DESC;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<TopViewedGenre> list = new ArrayList();
            while (rs.next()) {
                list.add(new TopViewedGenre(rs.getString("Genre"),
                        rs.getInt("TotalPosts"),
                        rs.getInt("TotalView")));
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(GenreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static void main(String[] args) {
       
    }

}
