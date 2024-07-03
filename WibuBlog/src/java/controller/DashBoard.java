/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
 * @author admin
 */
@WebServlet(name = "DashBoard", urlPatterns = {"/DashBoard"})
public class DashBoard extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DashBoard</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DashBoard at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(user.getRoleId() == 1){
            PostDAO pd = new PostDAO();
            request.setAttribute("NumberOfPostLast3Days", pd.getTotalPostLast3Days());
            request.setAttribute("AvgPostPerDay", pd.getAvgPostPerDayLastMonth());
            request.setAttribute("TotalPost", pd.getTotalPost());     
            request.setAttribute("TotalMember", pd.getTotalMember());
            request.setAttribute("Top10User", pd.getTop10UserByPoint());
            request.setAttribute("Top6Post", pd.getTop6VotedPost());
            request.getRequestDispatcher("AdminDashboard.jsp").forward(request, response);    
            return;
        }
        else if (user.getRoleId() == 2){
            response.sendRedirect("ModDashboard.jsp");
            return;
        }
        else {
            response.sendRedirect("MemberDashboard.jsp");
        }
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
