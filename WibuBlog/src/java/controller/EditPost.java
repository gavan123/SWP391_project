/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CategoryDAO;
import dal.GenreDAO;
import dal.NotificationDAO;
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
import java.util.List;
import javax.imageio.ImageIO;
import model.Category;
import model.Genre;
import model.Post;
import model.User;
import utility.ImageHandler;
import utility.ProfanityFilter;

/**
 *
 * @author ADMIN
 */
@MultipartConfig
@WebServlet(name = "EditPost", urlPatterns = {"/EditPost"})
public class EditPost extends HttpServlet {

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
            out.println("<title>Servlet EditPost</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditPost at " + request.getContextPath() + "</h1>");
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
        // Get the session from the request
        HttpSession session = request.getSession();

        // Check if the user is logged in by checking the session
        if (session.getAttribute("user") == null) {
            String errorMessage = "Session expired!";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("Login.jsp").forward(request, response);
            return;
        }

        CategoryDAO categoryDAO = new CategoryDAO();
        GenreDAO genreDAO = new GenreDAO();
        PostDAO postDAO = new PostDAO();

        List<Category> categories = categoryDAO.getCategoryNames();
        List<Genre> genres = genreDAO.getAllGenres();

        // Lấy giá trị postId từ request parameter
        String postIdStr = request.getParameter("postId");

        // Initialize post to null
        model.PostDetail postDetail = null;

        // Kiểm tra xem postId có tồn tại và không rỗng
        if (postIdStr != null && !postIdStr.isEmpty()) {
            try {
                int postId = Integer.parseInt(postIdStr);
                postDetail = postDAO.getPostDetailById(postId);
            } catch (NumberFormatException e) {
            }
        }

        request.setAttribute("post", postDetail);
        request.setAttribute("categories", categories);
        request.setAttribute("genres", genres);

        request.getRequestDispatcher("EditPost.jsp").forward(request, response);
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
        String postIdStr = request.getParameter("postId");
        String title = request.getParameter("title");
        String categoryIdStr = request.getParameter("category");
        String genreIdStr = request.getParameter("genre");
        String source = request.getParameter("source");
        String content = request.getParameter("content");
        String imageDB = request.getParameter("imageDB");

        Part part = request.getPart("image");
        String submittedFileName = (part != null && part.getSize() > 0) ? part.getSubmittedFileName() : null;
        int postId;
        int categoryId;
        int genreId;

        try {
            postId = Integer.parseInt(postIdStr);
            categoryId = Integer.parseInt(categoryIdStr);
            genreId = Integer.parseInt(genreIdStr);
        } catch (NumberFormatException e) {
            response.getWriter().println("Invalid input format: " + e.getMessage());
            return;
        }

        // Kiểm tra nếu nguồn không được cung cấp, mặc định là "Anime Forum"
        if (ProfanityFilter.checkProfanity(source) || ProfanityFilter.checkProfanity(content) || ProfanityFilter.checkProfanity(title)) {
            CategoryDAO categoryDAO = new CategoryDAO();
            GenreDAO genreDAO = new GenreDAO();
            List<Category> categories = categoryDAO.getCategoryNames();
            List<Genre> genres = genreDAO.getAllGenres();
            request.setAttribute("categories", categories);
            request.setAttribute("genres", genres);
            request.setAttribute("profanityDetected", true);
            request.getRequestDispatcher("EditPost?postId=" + postId).forward(request, response);
            return;
        }
        if (source == null || source.isEmpty()) {
            source = "Anime Forum";
        }

        // Kiểm tra nếu không phải là file ảnh hoặc không có file ảnh mới được tải lên
        if (submittedFileName != null && !ImageHandler.isImageFile(submittedFileName)) {
            response.getWriter().println("File " + submittedFileName + " is not an image.");
            return;
        }

        PostDAO postDAO = new PostDAO();
        // Tạo tên file ảnh mới bằng cách mã hóa và kết hợp với tên gốc
        String imageFinal = (submittedFileName != null) ? ImageHandler.encodeMediaName(user.getUserId()) + "." + ImageHandler.getExtension(submittedFileName) : imageDB;

        // Tạo đối tượng Post
        Post post = new Post();
        post.setPostId(postId);
        post.setTitle(title);
        post.setCategoryId(categoryId);
        post.setSource(source);
        post.setContent(content);
        post.setImage(imageFinal);

        // Thực hiện lưu bài post vào cơ sở dữ liệu
        boolean isPostEdited = postDAO.updatePost(post);

        // Kiểm tra kết quả và điều hướng người dùng
        if (isPostEdited) {
            // Lưu thể loại của post
            postDAO.updatePostGenre(postId, genreId);

            // Lưu file ảnh vào thư mục "post"
            if (submittedFileName != null) {
                // Lấy đường dẫn thực tế đến thư mục gốc của ứng dụng web
                String realPath = request.getServletContext().getRealPath("/");
                // Xây dựng đường dẫn tuyệt đối đến thư mục images và cắt bỏ phần "build" nếu có
                Path gameDirectory = ImageHandler
                        .removeBuildFromPath(Paths.get(realPath, "images"))
                        .resolve("post");
                Files.createDirectories(gameDirectory);

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
            }
            NotificationDAO nd = new NotificationDAO();
            nd.createUploadedPostNotification(postId, user.getUserId());
            // Tạo thông báo alert
            String alertMessage = "Post updated successfully!";

            // Thêm script JavaScript vào response để hiển thị thông báo
            String script = "<script>alert('" + alertMessage + "');</script>";
            response.getWriter().println(script);

            // Chuyển hướng sau khi alert được hiển thị
            response.setHeader("Refresh", "0; URL=postDetail?postId=" + postId);
        } else {
            response.sendRedirect("Error.jsp"); // Điều hướng tới trang lỗi
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
