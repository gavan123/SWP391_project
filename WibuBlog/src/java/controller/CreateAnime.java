package controller;

import dal.AnimeDAO;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Anime;

@WebServlet("/createAnime")
public class CreateAnime extends HttpServlet {
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin từ form
        String title = request.getParameter("title");
        String synopsis = request.getParameter("synopsis");
        String genre = request.getParameter("genre");
        int episodes = Integer.parseInt(request.getParameter("episodes"));
        String status = request.getParameter("status");
        String releaseDateStr = request.getParameter("releaseDate");

        // Chuyển đổi releaseDateStr thành LocalDateTime
        LocalDateTime releaseDate = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            releaseDate = LocalDateTime.parse(releaseDateStr, formatter);
        } catch (Exception e) {
            Logger.getLogger(CreateAnime.class.getName()).log(Level.SEVERE, "Invalid release date format", e);
            response.sendRedirect("error.jsp"); // Chuyển hướng tới trang lỗi nếu có lỗi xảy ra
            return;
        }

        String studio = request.getParameter("studio");
        String imageAnime = request.getParameter("imageAnime");

        // Tạo đối tượng Anime từ dữ liệu form
        Anime anime = new Anime();
        anime.setTitle(title);
        anime.setSynopsis(synopsis);
        anime.setGenre(genre);
        anime.setEpisodes(episodes);
        anime.setStatus(status);
        anime.setReleaseDate(releaseDate);
        anime.setStudio(studio);
        anime.setImageAnime(imageAnime);

        AnimeDAO animeDAO = new AnimeDAO();
        try {
            animeDAO.addAnime(anime);
            response.sendRedirect("success.jsp");
        } catch (IOException ex) {
            Logger.getLogger(CreateAnime.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("Error.jsp");
        }
    }
}
