/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.PostDAO;
import dal.RankDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Rank;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ViewMember", urlPatterns = {"/ViewMember"})
public class ViewMember extends HttpServlet {

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
            out.println("<title>Servlet ViewMember</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewMember at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();

// Check if the user is logged in by checking the session
        if (session.getAttribute("user") == null) {
            String errorMessage = "You cannot view another member. Must login!";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("Login.jsp").forward(request, response);
            return;
        }

        String memberName = request.getParameter("member");
        UserDAO userDAO = new UserDAO();
        RankDAO rankDAO = new RankDAO();
        PostDAO postDAO = new PostDAO();

        User member = userDAO.getUserByUsername(memberName);

        if (member != null) {
            Rank rank = rankDAO.getRankById(member.getRankId());
            String role = userDAO.getRoleByRoleID(member.getRoleId());

            ArrayList<model.PostDetail> userPostList = postDAO.getPostDetailByUserId(member.getUserId());

            request.setAttribute("member", member);
            request.setAttribute("rank", rank);
            request.setAttribute("role", role);
            request.setAttribute("userPostList", userPostList);
            request.getRequestDispatcher("Member.jsp").forward(request, response);
        } else {
            String errorMessage = "Member not found!";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
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
