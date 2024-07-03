package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Anime;

public class AnimeDAO extends DBContext {

    // Lấy một Anime bằng ID
    public Anime getAnimeDetailById(int animeId) {
        Anime anime = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT a.[AnimeID], a.Title, a.[Synopsis], g.[Name] AS GenreName,"
                    + " a.Episodes, a.[Status], a.imageAnime, a.ReleaseDate, a.Studio "
                    + "FROM Anime a "
                    + "JOIN Genre g ON a.GenreId = g.GenreID "
                    + "WHERE a.AnimeID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, animeId);
            rs = ps.executeQuery();

            if (rs.next()) {
                anime = new Anime(
                        rs.getInt("AnimeID"),
                        rs.getString("Title"),
                        rs.getString("Synopsis"),
                        rs.getString("GenreName"),
                        rs.getInt("Episodes"),
                        rs.getString("Status"),
                        rs.getTimestamp("ReleaseDate").toLocalDateTime(),
                        rs.getString("Studio"),
                        rs.getString("imageAnime")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnimeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }

        return anime;
    }

    // Lấy tất cả Anime
    public List<Anime> getAllAnime() {
        List<Anime> animeList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Anime";
            ps = connection.prepareStatement(sql);
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
                        rs.getString("ImageAnime") // Lấy giá trị từ cột ImageAnime
                );
                animeList.add(anime);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnimeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return animeList;
    }

    // Thêm một Anime mới
    public void addAnime(Anime anime) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO Anime (Title, Synopsis, Genre, Episodes, Status, ReleaseDate, Studio, ImageAnime) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, anime.getTitle());
            ps.setString(2, anime.getSynopsis());
            ps.setString(3, anime.getGenre());
            ps.setInt(4, anime.getEpisodes());
            ps.setString(5, anime.getStatus());
            ps.setTimestamp(6, Timestamp.valueOf(anime.getReleaseDate()));
            ps.setString(7, anime.getStudio());
            ps.setString(8, anime.getImageAnime());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AnimeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closePreparedStatement(ps);
        }
    }

    // Cập nhật thông tin Anime
    public void updateAnime(Anime anime) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE Anime SET Title = ?, Synopsis = ?, Genre = ?, Episodes = ?, Status = ?, ReleaseDate = ?, Studio = ?, ImageAnime = ? WHERE AnimeID = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, anime.getTitle());
            ps.setString(2, anime.getSynopsis());
            ps.setString(3, anime.getGenre());
            ps.setInt(4, anime.getEpisodes());
            ps.setString(5, anime.getStatus());
            ps.setTimestamp(6, Timestamp.valueOf(anime.getReleaseDate()));
            ps.setString(7, anime.getStudio());
            ps.setString(8, anime.getImageAnime());
            ps.setInt(9, anime.getAnimeId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AnimeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closePreparedStatement(ps);
        }
    }

    // Xóa một Anime
    public void deleteAnime(int animeId) {
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM Anime WHERE AnimeID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, animeId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AnimeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closePreparedStatement(ps);
        }
    }

    public List<Anime> getListAnime() {
        List<Anime> animeList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT a.[AnimeID], a.Title, a.[Synopsis], a.[Episodes], a.[Status], a.[imageAnime], g.[Name] AS genreName "
                    + "FROM Anime a "
                    + "JOIN Genre g ON a.GenreId = g.GenreID";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Anime anime = new Anime(
                        rs.getInt("AnimeID"),
                        rs.getString("Title"),
                        rs.getString("Synopsis"),
                        rs.getString("genreName"), // Lấy giá trị từ cột genreName
                        rs.getInt("Episodes"),
                        rs.getString("Status"),
                        rs.getString("imageAnime")
                );
                animeList.add(anime);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnimeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }

        return animeList;
    }

    public static void main(String[] args) {
        AnimeDAO animeDAO = new AnimeDAO();

        int animeId = 2;

        Anime anime = animeDAO.getAnimeDetailById(animeId);

        if (anime != null) {
            System.out.println("Anime ID: " + anime.getAnimeId());
            System.out.println("Title: " + anime.getTitle());
            System.out.println("Synopsis: " + anime.getSynopsis());
            System.out.println("Genre: " + anime.getGenre());
            System.out.println("Episodes: " + anime.getEpisodes());
            System.out.println("Status: " + anime.getStatus());
            System.out.println("Release Date: " + anime.getReleaseDate());
            System.out.println("Studio: " + anime.getStudio());
            System.out.println("Image: " + anime.getImageAnime());
        } else {
            System.out.println("Anime not found for ID: " + animeId);
        }
    }
}
