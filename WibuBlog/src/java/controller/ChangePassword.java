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
import security.Hash;
import utility.ContentDelivery;
import validation.Validator;

/**
 *
 * @author mindc
 */
@WebServlet(name = "ChangePassword", urlPatterns = {"/ChangePassword"})
public class ChangePassword extends HttpServlet {

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
        request.getRequestDispatcher("ChangePass.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the session from the request
        HttpSession session = request.getSession();

        // Check if the user is logged in by checking the session
        if (session.getAttribute("user") == null) {
            String errorMessage = "Session expired!";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("Login.jsp").forward(request, response);
            return;
        }

        // User is logged in, continue with the password change process
        User userSession = (User) session.getAttribute("user");

        // Get information from the request
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String newConfirmedPassword = request.getParameter("newConfirmedPassword");

        // Check if the old password is correct
        if (!userSession.getPasswordHash().equals(Hash.getHash(oldPassword))) {
            // If the old password is incorrect, send error message and return to the change password page
            String errorMessage = "Old password is incorrect, please try again!";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("ChangePass.jsp").forward(request, response);
            return;
        }

        // If password is invalid, set error message and forward to ResetPassword.jsp
        if (!Validator.passwordRegex(newPassword) || !Validator.passwordRegex(newConfirmedPassword)) {
            String errorMessage = "Invalid password! Password must contain 8-50 characters, one uppercase, one lowercase, and one special character.";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("ChangePass.jsp").forward(request, response);
            return;
        }

        // Check if the new password and confirmed password match
        if (!newPassword.equals(newConfirmedPassword)) {
            // If the new password does not match, send error message and return to the change password page
            String errorMessage = "Confirmed password incorrect, please try again!";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("ChangePass.jsp").forward(request, response);
            return;
        }

        // Old password is correct and new password matches, proceed with updating the new password in the database
        String hashedNewPassword = Hash.getHash(newPassword);
        UserDAO userDAO = new UserDAO();
        userDAO.changePassword(hashedNewPassword, userSession.getUserId());
        ContentDelivery.sendSecurityAlert(userSession.getEmail(), userSession.getUsername());

        request.setAttribute("message", "Password changed successfully, please login again!");
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
