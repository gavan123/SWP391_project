/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.BanDAO;
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
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import model.Ban;
import model.User;

/**
 *
 * @author admin
 */
@WebServlet(name = "AdjustStatus", urlPatterns = {"/AdjustStatus"})
public class AdjustStatus extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdjustStatus</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdjustStatus at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UserDAO ud = new UserDAO();
        NotificationDAO nd = new NotificationDAO();
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        BanDAO bd = new BanDAO();
        if (request.getParameter("banReason") != null) {
            String banReason = request.getParameter("banReason");
            int targetUserId = Integer.parseInt(request.getParameter("userId"));
            String banTime = request.getParameter("banTime");
            if (banTime.equals("1hour")) {
                Ban ban = new Ban(targetUserId,
                        currentUser.getUserId(),
                        banReason,
                        LocalDateTime.now(),
                        LocalDateTime.now().plus(1, ChronoUnit.HOURS));
                ud.setUserStatusByUserId(targetUserId, "deactive");
                bd.insertManualBan(ban);
                nd.createBanNotification(currentUser.getUserId(),
                        targetUserId,
                        banReason,
                        LocalDateTime.now().plus(1, ChronoUnit.HOURS).toString());
                response.sendRedirect("AllMember.jsp");
                return;
            }

            if (banTime.equals("3hour")) {
                Ban ban = new Ban(targetUserId,
                        currentUser.getUserId(),
                        banReason,
                        LocalDateTime.now(),
                        LocalDateTime.now().plus(3, ChronoUnit.HOURS));
                ud.setUserStatusByUserId(targetUserId, "deactive");
                bd.insertManualBan(ban);
                nd.createBanNotification(currentUser.getUserId(),
                        targetUserId,
                        banReason,
                        LocalDateTime.now().plus(3, ChronoUnit.HOURS).toString());
                response.sendRedirect("AllMember.jsp");
                return;
            }
            if (banTime.equals("1day")) {
                Ban ban = new Ban(targetUserId,
                        currentUser.getUserId(),
                        banReason,
                        LocalDateTime.now(),
                        LocalDateTime.now().plus(1, ChronoUnit.DAYS));
                ud.setUserStatusByUserId(targetUserId, "deactive");
                bd.insertManualBan(ban);
                nd.createBanNotification(currentUser.getUserId(),
                        targetUserId,
                        banReason,
                        LocalDateTime.now().plus(1, ChronoUnit.DAYS).toString());
                response.sendRedirect("AllMember.jsp");
                return;
            }
            if (banTime.equals("3day")) {
                Ban ban = new Ban(targetUserId,
                        currentUser.getUserId(),
                        banReason,
                        LocalDateTime.now(),
                        LocalDateTime.now().plus(3, ChronoUnit.DAYS));
                ud.setUserStatusByUserId(targetUserId, "deactive");
                bd.insertManualBan(ban);
                nd.createBanNotification(currentUser.getUserId(),
                        targetUserId,
                        banReason,
                        LocalDateTime.now().plus(3, ChronoUnit.DAYS).toString());
                response.sendRedirect("AllMember.jsp");
                return;
            }
            if (banTime.equals("5day")) {
                Ban ban = new Ban(targetUserId,
                        currentUser.getUserId(),
                        banReason,
                        LocalDateTime.now(),
                        LocalDateTime.now().plus(5, ChronoUnit.DAYS));
                ud.setUserStatusByUserId(targetUserId, "deactive");
                bd.insertManualBan(ban);
                nd.createBanNotification(currentUser.getUserId(),
                        targetUserId,
                        banReason,
                        LocalDateTime.now().plus(5, ChronoUnit.DAYS).toString());
                response.sendRedirect("AllMember.jsp");
                return;
            }
            if (banTime.equals("1week")) {
                Ban ban = new Ban(targetUserId,
                        currentUser.getUserId(),
                        banReason,
                        LocalDateTime.now(),
                        LocalDateTime.now().plus(1, ChronoUnit.WEEKS));
                ud.setUserStatusByUserId(targetUserId, "deactive");
                bd.insertManualBan(ban);
                nd.createBanNotification(currentUser.getUserId(),
                        targetUserId,
                        banReason,
                        LocalDateTime.now().plus(1, ChronoUnit.WEEKS).toString());
                response.sendRedirect("AllMember.jsp");
                return;
            }
            if (banTime.equals("2week")) {
                Ban ban = new Ban(targetUserId,
                        currentUser.getUserId(),
                        banReason,
                        LocalDateTime.now(),
                        LocalDateTime.now().plus(2, ChronoUnit.WEEKS));
                ud.setUserStatusByUserId(targetUserId, "deactive");
                bd.insertManualBan(ban);
                nd.createBanNotification(currentUser.getUserId(),
                        targetUserId,
                        banReason,
                        LocalDateTime.now().plus(2, ChronoUnit.WEEKS).toString());
                response.sendRedirect("AllMember.jsp");
                return;
            }
        } else if (request.getParameter("Action") != null){
            int targetUserId = Integer.parseInt(request.getParameter("UserId"));
            ud.setUserStatusByUserId(targetUserId, "active");
            response.sendRedirect("AllMember.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public static void main(String[] args) {
        LocalDateTime a = LocalDateTime.now().plus(1, ChronoUnit.HOURS);
        System.out.println(a);
    }
}
