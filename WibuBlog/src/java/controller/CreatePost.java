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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import model.Category;
import model.Genre;
import model.Post;
import model.User;

/**
 *
 * @author admin
 */
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
        String title = request.getParameter("title");
        int categoryId = Integer.parseInt(request.getParameter("category"));
        int genreId = Integer.parseInt(request.getParameter("genre"));
        String content = request.getParameter("content");
        Post post = new Post();
        post.setUserId(user.getUserId());
        post.setCategoryId(categoryId);
        post.setTitle(title);
        post.setContent(content);
        post.setImage("ehe");
        post.setPostTime(LocalDateTime.now());
        post.setStatus("active");
        // Lưu bài viết vào cơ sở dữ liệu
        PostDAO postDAO = new PostDAO();
        boolean isPostCreated = postDAO.createPost(post);
        // Kiểm tra kết quả và điều hướng người dùng
        if (isPostCreated) {
            postDAO.insertPostGenre(postDAO.getPostIDJustInserted(user.getUserId()), genreId);
            NotificationDAO nd = new NotificationDAO();
            nd.createUploadedPostNotification(postDAO.getPostIDJustInserted(user.getUserId()), user.getUserId());
            session.setAttribute("postID",postDAO.getPostIDJustInserted(user.getUserId()));
            session.setAttribute("newPost", post);
            response.sendRedirect("ChoosePostPic.jsp"); // Điều hướng tới trang thành công
        } else {
            response.sendRedirect("error.jsp"); // Điều hướng tới trang lỗi
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
