/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.PostDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author admin
 */
@WebServlet(name = "SearchUser", urlPatterns = {"/SearchUser"})
public class SearchUser extends HttpServlet {

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
            out.println("<title>Servlet SearchUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchUser at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String txtSearch = request.getParameter("searchToken");
        PostDAO pd = new PostDAO();
        UserDAO ud = new UserDAO();
        ArrayList<User> list = ud.searchUser(txtSearch);
        if(request.getParameter("flag") == null){
        request.setAttribute("searchList", list);
        request.setAttribute("NumberOfPostLast3Days", pd.getTotalPostLast3Days());
        request.setAttribute("AvgPostPerDay", pd.getAvgPostPerDayLastMonth());
        request.setAttribute("TotalPost", pd.getTotalPost());
        request.setAttribute("TotalMember", pd.getTotalMember());
        request.setAttribute("Top10User", pd.getTop10UserByPoint());
        request.setAttribute("Top6Post", pd.getTop6VotedPost());
        request.getRequestDispatcher("AdminDashboard.jsp").forward(request, response);   
        }
        else{
            request.setAttribute("searchList", list);
            request.getRequestDispatcher("AllMember.jsp").forward(request, response);
        }
    }

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
