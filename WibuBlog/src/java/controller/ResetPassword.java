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
import model.User;
import security.Hash;

/**
 *
 * @author mindc
 */
@WebServlet(name = "ResetPassword", urlPatterns = {"/ResetPassword"})
public class ResetPassword extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ResetPassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetPassword at " + request.getContextPath() + "</h1>");
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
        UserDAO userDAO = new UserDAO();
        int userID = Integer.parseInt(request.getParameter("userID"));
        String newPassword = request.getParameter("newPassword");
        
        if (newPassword.equals(request.getParameter("newConfirmedPassword"))) {
            String hashedPassword = Hash.getHash(newPassword);
            userDAO.changePassword(hashedPassword, userID);
            String message = "Password changed sucessfully please re-login.";
            request.getRequestDispatcher("index.html").forward(request, response);
        } else {
            String errorMessage = "Confirmed password incorrect, please try again!";
             request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("changePass.jsp").forward(request, response);
        }
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
