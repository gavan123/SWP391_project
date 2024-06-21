package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Anime;

public class AnimeDAO extends DBContext {

    // Lấy một Anime bằng ID
    public Anime getAnimeById(int animeId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Anime anime = null;
        try {
            String sql = "SELECT * FROM Anime WHERE AnimeID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, animeId);
            rs = ps.executeQuery();
            if (rs.next()) {
                anime = new Anime(
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
            String sql = "INSERT INTO Anime ([Title], [Synopsis], [Genre], [Episodes], [Status], [ReleaseDate], [Studio], [ImageAnime]) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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
            String sql = "SELECT [AnimeId], Title, [Synopsis], [Episodes], [Status], [imageAnime] FROM Anime";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Anime anime = new Anime(
                        rs.getInt("AnimeId"),
                        rs.getString("Title"),
                        rs.getString("Synopsis"),
                        rs.getInt("Episodes"),
                        rs.getString("Status"),
                        rs.getString("imageAnime")
                );
                animeList.add(anime);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }

        return animeList;
    }

    public List<Anime> AnimeDetail() {
        List<Anime> animeList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM Anime";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Anime anime = new Anime(
                        rs.getInt("AnimeId"),
                        rs.getString("Title"),
                        rs.getString("Synopsis"),
                        rs.getString("GenreName"), // lấy tên thể loại từ cột GenreName
                        rs.getInt("Episodes"),
                        rs.getString("Status"),
                        rs.getTimestamp("ReleaseDate").toLocalDateTime(),
                        rs.getString("Studio"),
                        rs.getString("imageAnime")
                );
                animeList.add(anime);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }

        return animeList;
    }


public static void main(String[] args) {
    AnimeDAO postDAO = new AnimeDAO();

    // Kiểm tra phương thức AnimeDetail
    List<Anime> animeList = postDAO.getListAnime();
    if (animeList.isEmpty()) {
        System.out.println("Không có anime nào được tìm thấy.");
    } else {
        for (Anime anime : animeList) {
            System.out.println("AnimeId: " + anime.getAnimeId());
            System.out.println("Title: " + anime.getTitle());
            System.out.println("Synopsis: " + anime.getSynopsis());
            System.out.println("Genre: " + anime.getGenre());
            System.out.println("Episodes: " + anime.getEpisodes());
            System.out.println("Status: " + anime.getStatus());
            System.out.println("Release Date: " + anime.getReleaseDate());
            System.out.println("Studio: " + anime.getStudio());
            System.out.println("Image: " + anime.getImageAnime());
            System.out.println("--------------------------");
        }
    }
}
}
