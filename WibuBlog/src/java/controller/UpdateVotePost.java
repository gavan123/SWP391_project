/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "UpdateVote", urlPatterns = {"/updateVotePost"})
public class UpdateVotePost extends HttpServlet {

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
            out.println("<title>Servlet UpdateVote</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateVote at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String postIdStr = request.getParameter("postId");
        String voteValueStr = request.getParameter("vote_value");
        String voteStatusStr = request.getParameter("vote_status");
        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute("user");
        NotificationDAO nd = new NotificationDAO();
        

        PostDAO postDAO = new PostDAO();
        // Kiểm tra các tham số từ request
        if (userSession == null || postIdStr == null || postIdStr.isEmpty()
                || voteValueStr == null || voteValueStr.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            // Chuyển đổi các tham số thành số nguyên
            int postId = Integer.parseInt(postIdStr);
            int voteValue = Integer.parseInt(voteValueStr);
            int userId = userSession.getUserId();

            // Kiểm tra nếu người dùng đã bầu chọn cho bài đăng này
            boolean isPostVoted = postDAO.hasUserVoted(userId, postId);
            if (!isPostVoted) {
                // Nếu chưa bầu chọn, thêm phiếu bầu mới
                postDAO.addUserVote(userId, postId, voteStatusStr);
            } else {
                // Nếu đã bầu chọn, kiểm tra trạng thái bầu chọn và cập nhật lại
                postDAO.updateUserVote(userId, postId, voteStatusStr);          
            }
           
            // Cập nhật giá trị phiếu bầu của bài đăng
            boolean updateSuccess = postDAO.updateVote(postId, voteValue);
            
            if(updateSuccess){
            response.setStatus(HttpServletResponse.SC_OK);
            }
            else{
               response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (NumberFormatException ex) {
            // Xử lý ngoại lệ nếu không thể chuyển đổi thành số nguyên và thiết lập trạng thái phản hồi là Bad Request
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
