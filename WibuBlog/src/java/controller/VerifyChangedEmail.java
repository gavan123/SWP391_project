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

/**
 *
 * @author admin
 */
@WebServlet(name = "VerifyChangedEmail", urlPatterns = {"/VerifyChangedEmail"})
public class VerifyChangedEmail extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VerifyChangedEmail</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerifyChangedEmail at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String template = (String) session.getAttribute("template");
        String input = request.getParameter("response");
        String newEmail = (String) session.getAttribute("newEmail");
        if (!input.equals(template)){
            request.setAttribute("errorMessage", "Wrong verificationcode");
            request.getRequestDispatcher("VerifyChangedEmail.jsp").forward(request, response);
        }
        else{
            session.removeAttribute("template");
            user.setEmail(newEmail);
            UserDAO userDAO = new UserDAO();
            userDAO.changeEmail(newEmail, user.getUserId());
            response.sendRedirect("AccountSetting.jsp");
        }
        
        
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
