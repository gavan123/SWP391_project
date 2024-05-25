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

        // Retrieve parameters from the request
        int userID = Integer.parseInt(request.getParameter("userID"));
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String newConfirmedPassword = request.getParameter("newConfirmedPassword");

        // Hash the old password
        String oldPasswordHash = Hash.getHash(oldPassword);

        // Initialize UserDAO to interact with the database
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserById(userID);

        // Check if the old password is correct
        if (oldPasswordHash.equals(user.getPasswordHash())) {
            // Check if the new password matches the confirmed password
            if (newPassword.equals(newConfirmedPassword)) {
                // Hash the new password and update it in the database
                String hashedNewPassword = Hash.getHash(newPassword);
                userDAO.changePassword(hashedNewPassword, userID);

                // Forward to the index page after successful password change
                request.getRequestDispatcher("index.html").forward(request, response);
            } else {
                // Set error message for password mismatch
                String errorMessage = "Confirmed password incorrect, please try again!";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("ChangePass.jsp").forward(request, response);
            }
        } else {
            // Set error message for incorrect old password
            String errorMessage = "Old password is incorrect, please try again!";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("ChangePass.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
