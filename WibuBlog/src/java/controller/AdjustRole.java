/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.NotificationDAO;
import dal.PostDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@WebServlet(name = "AdjustRole", urlPatterns = {"/AdjustRole"})
public class AdjustRole extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdjustRole</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdjustRole at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("Action");
        int userId = Integer.parseInt(request.getParameter("UserId"));
        PostDAO pd = new PostDAO();
        UserDAO ud = new UserDAO();
        NotificationDAO nd = new NotificationDAO();
        ud.setUserRoleIdByUserId(userId, 2);
        nd.createPromoteNotification(userId);
        request.setAttribute("NumberOfPostLast3Days", pd.getTotalPostLast3Days());
        request.setAttribute("AvgPostPerDay", pd.getAvgPostPerDayLastMonth());
        request.setAttribute("TotalPost", pd.getTotalPost());
        request.setAttribute("TotalMember", pd.getTotalMember());
        request.setAttribute("Top10User", pd.getTop10UserByPoint());
        if (action.equals("Promote")) {
            if (request.getParameter("Flag") == null) {
                request.getRequestDispatcher("AdminDashboard.jsp").forward(request, response);
                return;
            } else {
                response.sendRedirect("AllMember.jsp");
                return;
            }
        } else {
            ud.setUserRoleIdByUserId(userId, 3);
            nd.createDemoteNotification(userId);
            request.setAttribute("NumberOfPostLast3Days", pd.getTotalPostLast3Days());
            request.setAttribute("AvgPostPerDay", pd.getAvgPostPerDayLastMonth());
            request.setAttribute("TotalPost", pd.getTotalPost());
            request.setAttribute("TotalMember", pd.getTotalMember());
            request.setAttribute("Top10User", pd.getTop10UserByPoint());
            if (request.getParameter("Flag") == null) {
                request.getRequestDispatcher("AdminDashboard.jsp").forward(request, response);
                return;
            } else {
                response.sendRedirect("AllMember.jsp");
                return;
            }
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
    }// </editor-fold>

}
