/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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

@WebServlet("/updateAnime")
public class UpdateAnime extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin ID của anime cần cập nhật từ parameter
        int animeId = Integer.parseInt(request.getParameter("animeId"));

        AnimeDAO animeDAO = new AnimeDAO();
        Anime anime = animeDAO.getAnimeDetailById(animeId);

        request.setAttribute("anime", anime);

        // Chuyển hướng tới trang JSP để hiển thị form cập nhật
        request.getRequestDispatcher("updateAnime.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin từ form cập nhật
        int animeId = Integer.parseInt(request.getParameter("animeId"));
        String title = request.getParameter("title");
        String synopsis = request.getParameter("synopsis");
        String genre = request.getParameter("genre");
        int episodes = Integer.parseInt(request.getParameter("episodes"));
        String status = request.getParameter("status");
        LocalDateTime releaseDate = LocalDateTime.parse(request.getParameter("releaseDate"));
        String studio = request.getParameter("studio");
        String imageAnime = request.getParameter("imageAnime");

        Anime anime = new Anime(animeId, title, synopsis, genre, episodes, status, releaseDate, studio, imageAnime);

        AnimeDAO animeDAO = new AnimeDAO();
        try {
            animeDAO.updateAnime(anime);
            response.sendRedirect("ListAnime.jsp");
        } catch (Exception ex) {
            Logger.getLogger(UpdateAnime.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", "Failed to update anime. Please try again later.");
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }
    }

}
