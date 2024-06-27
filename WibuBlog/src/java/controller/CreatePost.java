/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CategoryDAO;
import dal.GenreDAO;
import dal.PostDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import javax.imageio.ImageIO;
import model.Category;
import model.Genre;
import model.Post;
import model.User;
import utility.ImageHandler;

/**
 *
 * @author admin
 */
@MultipartConfig
@WebServlet(name = "createPost2", urlPatterns = {"/createPost"})
public class CreatePost extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet createPost2</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet createPost2 at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryDAO categoryDAO = new CategoryDAO();
        GenreDAO genreDAO = new GenreDAO();

        List<Category> categories = categoryDAO.getCategoryNames();
        List<Genre> genres = genreDAO.getAllGenres();

        request.setAttribute("categories", categories);
        request.setAttribute("genres", genres);

        request.getRequestDispatcher("CreatePost.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Lấy thông tin từ form
        String title = request.getParameter("title");
        int categoryId = Integer.parseInt(request.getParameter("category"));
        int genreId = Integer.parseInt(request.getParameter("genre"));
        String source = request.getParameter("source");
        String content = request.getParameter("content");
        Part part = request.getPart("image");
        String submittedFileName = part.getSubmittedFileName();

        // Kiểm tra nếu nguồn không được cung cấp, mặc định là "Anime Forum"
        if (source == null || source.isEmpty()) {
            source = "Anime Forum";
        }

        // Kiểm tra nếu không phải là file ảnh
        if (submittedFileName == null || !ImageHandler.isImageFile(submittedFileName)) {
            response.getWriter().println("File " + submittedFileName + " is not an image.");
            return;
        }

        // Lấy đường dẫn thực tế đến thư mục gốc của ứng dụng web
        String realPath = request.getServletContext().getRealPath("/");
        // Xây dựng đường dẫn tuyệt đối đến thư mục images và cắt bỏ phần "build" nếu có
        Path gameDirectory = ImageHandler
                .removeBuildFromPath(Paths.get(realPath, "images"))
                .resolve("post");
        Files.createDirectories(gameDirectory);
        
        PostDAO postDAO = new PostDAO();
        // Tạo tên file ảnh mới bằng cách mã hóa và kết hợp với tên gốc
        String encodedImageName = postDAO.encodeImageName(user.getUserId());
        String imageFinal = encodedImageName + submittedFileName;

        // Tạo đối tượng Post
        Post post = new Post(user.getUserId(), categoryId, title, content,
                source, "post/" + imageFinal, LocalDateTime.MIN, "active");

        // Thực hiện lưu bài post vào cơ sở dữ liệu
        boolean isPostCreated = postDAO.createPost(post);

        // Kiểm tra kết quả và điều hướng người dùng
        if (isPostCreated) {
            // Lưu thể loại của post
            postDAO.insertPostGenre(postDAO.getPostIDJustInserted(user.getUserId()), genreId);

            // Lưu file ảnh vào thư mục "post"
            try (InputStream input = part.getInputStream()) {
                BufferedImage image = ImageIO.read(input);
                ImageHandler.saveImage(image, gameDirectory.toString(), imageFinal, ImageHandler.getExtension(submittedFileName));
                response.getWriter().println("Upload thành công ảnh vào thư mục: " + gameDirectory.toString());
            } catch (IOException e) {
                response.getWriter().println("Error reading or saving image: " + e.getMessage());
                return;
            }
            try {
                Thread.sleep(3000); // Giả lập thời gian chờ
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            response.sendRedirect("Home.jsp"); // Điều hướng tới trang thành công
        } else {
            response.sendRedirect("Error.jsp"); // Điều hướng tới trang lỗi
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
