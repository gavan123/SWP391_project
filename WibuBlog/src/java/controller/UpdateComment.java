/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import dal.CommentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import model.Comment;
import utility.LocalDateTimeAdapter;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "UpdateComment", urlPatterns = {"/updateComment"})
public class UpdateComment extends HttpServlet {

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
            out.println("<title>Servlet UpdateComment</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateComment at " + request.getContextPath() + "</h1>");
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
        try {
            // Lấy commentId từ request parameter
            int commentId = Integer.parseInt(request.getParameter("commentId"));

            // Gọi đến DAO để lấy thông tin bình luận
            CommentDAO commentDAO = new CommentDAO();
            Comment comment = commentDAO.getCommentById(commentId);

            // Kiểm tra xem bình luận có tồn tại không
            if (comment != null) {
                // Convert comment to JSON sử dụng Gson
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                        .create();
                String commentJson = gson.toJson(comment);

                // Gửi phản hồi về cho client (frontend)
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(commentJson);
            } else {
                // Nếu không tìm thấy bình luận, gửi phản hồi lỗi
                response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404 Not Found
                response.getWriter().write("Comment not found with ID: " + commentId);
            }
        } catch (NumberFormatException e) {
            // Xử lý ngoại lệ nếu commentId không phải là số
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad Request
            response.getWriter().write("Invalid commentId format");
        } catch (Exception e) {
            // Xử lý ngoại lệ khác
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 Internal Server Error
            response.getWriter().write("Internal Server Error: " + e.getMessage());
        }
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
        try {
            // Đọc dữ liệu JSON từ phần thân của yêu cầu
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String jsonRequest = reader.lines().collect(Collectors.joining());

            // Chuyển đổi JSON thành đối tượng Java sử dụng Gson
            Gson gson = new Gson();
            Comment data = gson.fromJson(jsonRequest, Comment.class);

            // Kiểm tra tính hợp lệ của dữ liệu đầu vào
            if (data == null || data.getCommentId() <= 0 || data.getContent() == null) {
                throw new IllegalArgumentException("Invalid data received");
            }

            // Lấy bình luận từ cơ sở dữ liệu
            CommentDAO commentDAO = new CommentDAO();
            Comment cmt = commentDAO.getCommentById(data.getCommentId());

            if (cmt == null) {
                throw new IllegalArgumentException("Comment not found with ID: " + data.getCommentId());
            }

            // Cập nhật nội dung bình luận
            cmt.setContent(data.getContent());
            commentDAO.updateComment(cmt);

            // Gửi phản hồi về cho client (frontend)
            String jsonResponse = "{ \"status\": \"success\" }";
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse);
        } catch (JsonSyntaxException | IOException | IllegalArgumentException e) {
            // Xử lý ngoại lệ và gửi phản hồi lỗi về cho client
            String errorResponse = "{ \"status\": \"error\", \"message\": \"" + e.getMessage() + "\" }";
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(errorResponse);
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
