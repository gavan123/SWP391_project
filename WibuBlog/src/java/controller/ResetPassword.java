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
import validation.Validator;

/**
 *
 * @author mindc
 */
@WebServlet(name = "ResetPassword", urlPatterns = {"/resetPassword"})
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
        request.getRequestDispatcher("ResetPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve parameters from the request
        String email = request.getParameter("email");
        String newPassword = request.getParameter("newPassword");
        String newConfirmedPassword = request.getParameter("newConfirmedPassword");

        // If password is invalid, set error message and forward to ResetPassword.jsp
        if (!Validator.passwordRegex(newPassword) || !Validator.passwordRegex(newConfirmedPassword)) {
            String errorMessage = "Invalid password! Password must contain 8-50 characters, one uppercase, one lowercase, and one special character.";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("ResetPassword.jsp").forward(request, response);
        }

        // Initialize UserDAO to interact with the database
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByEmail(email);

        // Check if the new password matches the confirmed password
        if (newPassword.equals(newConfirmedPassword)) {
            // Hash the new password and update it in the database
            String hashedNewPassword = Hash.getHash(newPassword);
            userDAO.changePassword(hashedNewPassword, user.getUserId());
            // Forward to the index page after successful password change
            request.setAttribute("message", "Password changed successfully, please login again!");
            request.getRequestDispatcher("Login.jsp").forward(request, response);

        } else {
            // Set error message for password mismatch
            String errorMessage = "Confirmed password incorrect, please try again!";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("ChangePass.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
