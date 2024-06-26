/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CommentDAO;
import dal.PostDAO;
import dal.RankDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.Comment;
import model.Rank;
import model.User;

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
        // Lấy giá trị postId từ request parameter
        String postIdStr = request.getParameter("postId");

        // Lấy session hiện tại từ request để lấy thông tin người dùng
        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute("user");

        // Kiểm tra xem postId có tồn tại và không rỗng
        if (postIdStr != null && !postIdStr.isEmpty()) {
            try {
                // Chuyển đổi postId từ String sang int
                int postId = Integer.parseInt(postIdStr);

                // Khởi tạo các DAO cần thiết để truy xuất dữ liệu
                PostDAO postDAO = new PostDAO();
                CommentDAO commentDAO = new CommentDAO();
                UserDAO userDAO = new UserDAO();
                RankDAO rankDAO = new RankDAO();

                // Lấy chi tiết bài viết theo postId từ database
                model.PostDetail post = postDAO.getPostDetailById(postId);

                String votePostStatus = ""; // Khởi tạo biến status ban đầu là rỗng

                // Kiểm tra người dùng đã đăng nhập và đã vote cho bài viết này chưa
                if (userSession != null) {
                    boolean isPostVoted = postDAO.hasUserVoted(userSession.getUserId(), postId);
                    if (isPostVoted) {
                        votePostStatus = postDAO.checkVoteStatus(userSession.getUserId(), postId);
                    }
                }

                // Lấy danh sách comment của bài viết từ database
                List<Comment> comments = commentDAO.getCommentsForPost(postId);

                // Chuẩn bị danh sách user và rank tương ứng với mỗi comment
                List<User> users = new ArrayList<>();
                List<Rank> ranks = new ArrayList<>();
                List<String> commentDate = new ArrayList<>();

                // Parent Comment
                for (Comment comment : comments) {
                    User commentUser = userDAO.getUserById(comment.getUserId());
                    Rank commentRank = rankDAO.getRankByUserId(comment.getUserId());
                    if (commentUser != null) {
                        users.add(commentUser);
                    }
                    if (commentRank != null) {
                        ranks.add(commentRank);
                    }
                    String formattedDate = formatDate(comment.getCreateAt());
                    commentDate.add(formattedDate);
                }

                // Kiểm tra và cập nhật số lượt xem bài viết nếu chưa từng được xem
                if (post != null && !isPostViewed(request, postId)) {
                    postDAO.updateView(postId); // Cập nhật số lượt xem của bài viết
                    setPostViewedCookie(response, postId); // Tạo cookie cho việc đã xem bài viết
                }

                // Định dạng lại thời gian của bài viết
                String postDate = formatDate(post.getPostTime());

                // Set các attribute cần thiết cho JSP rendering
                request.setAttribute("user", userSession); // Thông tin người dùng hiện tại
                request.setAttribute("post", post); // Chi tiết bài viết
                request.setAttribute("postTime", postDate); // Thời gian đăng bài viết
                request.setAttribute("userRank", ranks); // Danh sách rank của các user comment
                request.setAttribute("userComment", users); // Danh sách user của các comment
                request.setAttribute("commentsList", comments); // Danh sách comment của bài viết
                request.setAttribute("commentTime", commentDate); // Thời gian của từng comment
                request.setAttribute("votePostStatus", votePostStatus); // Trạng thái phiếu bầu của người dùng
                request.setAttribute("postUser", post.getUsername()); 
                PrintWriter out = response.getWriter();
                // Forward request tới trang PostDetail.jsp để hiển thị chi tiết bài viết
                request.getRequestDispatcher("PostDetail.jsp").forward(request, response);
                return; // Thoát khỏi phương thức sau khi forward thành công
            } catch (NumberFormatException ex) {

            }
        }

        // Nếu không thỏa mãn điều kiện kiểm tra ban đầu hoặc userSession là null, redirect người dùng tới trang Error.jsp
        response.sendRedirect("Error.jsp");
    }

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
        viewedCookie.setPath("/"); // Ensure the cookie is valid for the entire application
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
