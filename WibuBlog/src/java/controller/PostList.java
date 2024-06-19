package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import dal.PostDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Post;

/**
 *
 * @author ADMIN
 */
@WebServlet(urlPatterns = {"/postList"})
public class PostList extends HttpServlet {

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
            out.println("<title>Servlet PostList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PostList at " + request.getContextPath() + "</h1>");
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
        // Số lượng bài viết trên mỗi trang
        int postsPerPage = 10;

        // Lấy tất cả bài viết từ DAO
        PostDAO postDAO = new PostDAO();
        List<Post> allPosts = postDAO.getAllPosts();

        // Lấy tổng số lượng bài viết
        int totalPosts = allPosts.size();

        // Lấy số trang từ request parameter, mặc định là trang đầu tiên
        int page = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                // Xử lý ngoại lệ khi pageParam không phải là số
                e.printStackTrace();
            }
        }

        // Tính offset (vị trí bắt đầu lấy bài viết) dựa trên số trang và số lượng bài viết trên mỗi trang
        int offset = (page - 1) * postsPerPage;

        // Lấy danh sách bài viết cho trang hiện tại
        List<Post> postsForPage = new ArrayList<>();
        for (int i = offset; i < offset + postsPerPage && i < totalPosts; i++) {
            postsForPage.add(allPosts.get(i));
        }

        // Tính tổng số trang
        int totalPages = (int) Math.ceil((double) totalPosts / postsPerPage);

        // Đặt các thuộc tính vào request để sử dụng trong JSP
        request.setAttribute("allPosts", postsForPage);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        // Chuyển hướng đến trang JSP để hiển thị
        request.getRequestDispatcher("PostList.jsp").forward(request, response);
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
