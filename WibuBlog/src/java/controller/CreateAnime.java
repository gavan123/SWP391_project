package controller;

import dal.AnimeDAO;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            response.sendRedirect("Error.jsp"); // Chuyển hướng tới trang lỗi nếu có lỗi xảy ra
            return;
        }

        String studio = request.getParameter("studio");
        String imageAnime = request.getParameter("imageAnime");

        // Tạo đối tượng Anime từ dữ liệu form
        Anime anime = new Anime(title, synopsis, genre, episodes, status, releaseDate, studio, imageAnime);

        AnimeDAO animeDAO = new AnimeDAO();
        try {
            animeDAO.addAnime(anime);
            response.sendRedirect("ListAnime.jsp");
        } catch (Exception ex) {
            request.setAttribute("errorMessage", "Failed to add anime. Please try again later.");
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }
    }
}
