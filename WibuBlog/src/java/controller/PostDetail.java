/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.PostDAO;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "PostDetail", urlPatterns = {"/postDetail"})
public class PostDetail extends HttpServlet {

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
            out.println("<title>Servlet PostDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PostDetail at " + request.getContextPath() + "</h1>");
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
        String postIdStr = request.getParameter("postId");
        if (postIdStr != null && !postIdStr.isEmpty()) {
            try {
                int postId = Integer.parseInt(postIdStr);
                PostDAO postDAO = new PostDAO();
                model.PostDetail post = postDAO.getPostDetailById(postId);

                if (post != null) {
                    // Kiểm tra xem bài viết đã được xem chưa
                    if (!isPostViewed(request, postId)) {
                        // Cập nhật số lượt xem và tạo cookie
                        postDAO.updateView(postId);
                        setPostViewedCookie(response, postId);
                    }

                    // Định dạng lại thời gian thành yyyy-MM-dd
                    String formattedDate = formatDate(post.getPostTime());
                    
                    request.setAttribute("post", post);
                    request.setAttribute("postTime", formattedDate);
                    request.getRequestDispatcher("PostDetail.jsp").forward(request, response);
                    return; // Thoát khỏi phương thức sau khi forward
                }
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }

        response.sendRedirect("Error.jsp"); // Xử lý khi không thành công

    }
// Phương thức kiểm tra xem bài viết đã được xem chưa

    private boolean isPostViewed(HttpServletRequest request, int postId) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("viewedPost_" + postId)) {
                    return true;
                }
            }
        }
        return false;
    }

// Phương thức để thiết lập cookie cho bài viết đã được xem
    private void setPostViewedCookie(HttpServletResponse response, int postId) {
        Cookie viewedCookie = new Cookie("viewedPost_" + postId, "true");
        viewedCookie.setMaxAge(24 * 60 * 60); // Số giây trong 1 ngày
        response.addCookie(viewedCookie);
    }

// Phương thức để định dạng ngày thành chuỗi yyyy-MM-dd
    private String formatDate(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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
        processRequest(request, response);
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
