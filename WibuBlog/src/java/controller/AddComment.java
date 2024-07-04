/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CommentDAO;
import model.Comment;
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
import model.Comment;
import model.Post;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AddComment", urlPatterns = {"/addComment"})
public class AddComment extends HttpServlet {

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
            out.println("<title>Servlet AddComment</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddComment at " + request.getContextPath() + "</h1>");
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
        PostDAO postDAO = new PostDAO();
        CommentDAO commentDAO = new CommentDAO();
        NotificationDAO notificationDAO = new NotificationDAO();

        try {
            // Check if user is logged in
            if (session.getAttribute("user") == null) {
                String errorMessage = "Session expired! Please log in again.";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("Login.jsp").forward(request, response);
                return;
            }

            // Retrieve user information from session
            User userSession = (User) session.getAttribute("user");

            // Retrieve information from request parameters
            String content = request.getParameter("content");
            int postId = Integer.parseInt(request.getParameter("postId")); // Convert postId to int
            int parentId = Integer.parseInt(request.getParameter("parentId"));

            // Create a comment object with the gathered information
            Comment comment = new Comment(postId, userSession.getUserId(), content.trim(), parentId);

            // Add the comment to the database
            commentDAO.addComment(comment);

            // Create a notification for the post owner about the new comment
            int postOwnerId = postDAO.getUserIdOfPostByPostID(postId);
            notificationDAO.createCommentNotification(postId, userSession.getUserId(), postOwnerId);

        } catch (NumberFormatException | ServletException | IOException e) {
            // Handle exceptions
            String errorMessage = "Error adding comment: " + e.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
