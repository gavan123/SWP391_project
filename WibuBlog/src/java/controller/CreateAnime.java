package controller;

import dal.AnimeDAO;
import dal.MediaDAO;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import model.Anime;
import model.User;
import utility.ImageHandler;


@MultipartConfig
@WebServlet("/createAnime")
public class CreateAnime extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Lấy thông tin từ form
        String title = request.getParameter("title");
        String synopsis = request.getParameter("synopsis");
        String genre = request.getParameter("genre");
        int episodes = Integer.parseInt(request.getParameter("episodes"));
        String status = request.getParameter("status");
        String releaseDateStr = request.getParameter("releaseDate");
        String studio = request.getParameter("studio");
        Part part = request.getPart("image");
        String submittedFileName = part.getSubmittedFileName();

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

        // Kiểm tra nếu không phải là file ảnh
        if (submittedFileName == null || !ImageHandler.isImageFile(submittedFileName)) {
            response.getWriter().println("File " + submittedFileName + " is not an image.");
            return;
        }

        // Lấy đường dẫn thực tế đến thư mục gốc của ứng dụng web
        String realPath = request.getServletContext().getRealPath("/");
        // Xây dựng đường dẫn tuyệt đối đến thư mục images và cắt bỏ phần "build" nếu có
        Path gameDirectory = ImageHandler.removeBuildFromPath(Paths.get(realPath, "images")).resolve("anime");
        Files.createDirectories(gameDirectory);

        MediaDAO mediaDAO = new MediaDAO();
        String imageFinal = mediaDAO.encodeMediaName(user.getUserId()) + "." + ImageHandler.getExtension(submittedFileName);

        // Tạo đối tượng Anime từ dữ liệu form
        Anime anime = new Anime(title, synopsis, genre, episodes, status, releaseDate, studio, imageFinal);

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
