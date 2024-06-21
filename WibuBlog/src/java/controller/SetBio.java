/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import utility.ProfanityFilter;

/**
 *
 * @author admin
 */
@WebServlet(name = "SetBio", urlPatterns = {"/SetBio"})
public class SetBio extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateBio</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateBio at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bio = request.getParameter("bio");
        if (ProfanityFilter.checkProfanity(bio)) {
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Please RECONSIDER YOUR PROFILE');");
            out.println("location='Profile.jsp';");
            out.println("</script>");
            response.sendRedirect("Profile.jsp");
        } else {
            HttpSession session = request.getSession();
            UserDAO userDAO = new UserDAO();
            User user = (User) session.getAttribute("user");
            userDAO.updateBio(bio, user.getUserId());
            user.setBio(bio);
            response.sendRedirect("Profile.jsp");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
