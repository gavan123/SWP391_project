/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CategoryDAO;
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
import model.Category;
import model.Post;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "PostListByCategory", urlPatterns = {"/postListByCategory"})
public class PostListByCategory extends HttpServlet {

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
            out.println("<title>Servlet PostListByCategory</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PostListByCategory at " + request.getContextPath() + "</h1>");
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
        String categoryIdParam = request.getParameter("categoryId");
        String categoryNameParam = request.getParameter("name");

        int postsPerPage = 10;

        CategoryDAO categoryDAO = new CategoryDAO();
        PostDAO postDAO = new PostDAO();

        Category category = null;
        List<Post> postsForPage = new ArrayList<>();
        int totalPages = 0;
        int page = 1;

        try {
            if (categoryIdParam != null && !categoryIdParam.isEmpty()) {
                int categoryId = Integer.parseInt(categoryIdParam);
                category = categoryDAO.getCategoryById(categoryId);
            } else if (categoryNameParam != null && !categoryNameParam.isEmpty()) {
                category = categoryDAO.getCategoryByName(categoryNameParam);
            }

            if (category != null) {
                List<Post> allPosts = postDAO.getAllPostsByCategory(category.getCategoryId());
                int totalPosts = allPosts.size();
                // Lấy số trang từ request parameter, mặc định là trang đầu tiên
                String pageParam = request.getParameter("page");
                page = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;
                // Tính offset (vị trí bắt đầu lấy bài viết) dựa trên số trang và số lượng bài viết trên mỗi trang
                int offset = (page - 1) * postsPerPage;
                // Lấy danh sách bài viết cho trang hiện tại
                for (int i = offset; i < Math.min(offset + postsPerPage, totalPosts); i++) {
                    postsForPage.add(allPosts.get(i));
                }

                // Tính tổng số trang
                totalPages = (int) Math.ceil((double) totalPosts / postsPerPage);
            }
        } catch (NumberFormatException e) {
        }

        if (category != null) {
            // Đặt các thuộc tính vào request để sử dụng trong JSP
            request.setAttribute("allPosts", postsForPage);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("category", category);
            request.getRequestDispatcher("PostListCate.jsp").forward(request, response);
        } else {
            response.sendRedirect("Error.jsp");
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
