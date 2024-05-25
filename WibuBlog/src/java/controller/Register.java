/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.ContentDelivery;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dal.UserDAO;
import jakarta.servlet.http.HttpSession;
import model.User;

import utility.KeyGenerator;
import validation.Validator;

/**
 *
 * @author user
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class Register extends HttpServlet {

    private String verificationCode = "";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve parameters from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullname");

        // Create an instance of UserDAO for database operations
        UserDAO ud = new UserDAO();

        // Check if the email already exists in the database
        if (ud.emailExists(email)) {
            // If email exists, set error message and forward to Register.jsp
            String errorMessage = "This email existed!";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        } // Check if the username already exists in the database
        else if (ud.getUserByUsername(username) != null) {
            // If username exists, set error message and forward to Register.jsp
            String errorMessage = "This username existed!";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        } // Validate username using regex
        else if (!Validator.usernameRegex(username)) {
            // If username is invalid, set error message and forward to Register.jsp
            String errorMessage = "Invalid username! Please enter at least 4-20 characters, alphabetic numbers, and characters.";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        } // Validate password using regex
        else if (!Validator.passwordRegex(password)) {
            // If password is invalid, set error message and forward to Register.jsp
            String errorMessage = "Invalid password! Password must contain 8-50 characters, one uppercase, one lowercase, and one special character.";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        } // If all validations pass
        else {
            // Generate a verification code
            verificationCode = KeyGenerator.generateVerificationCode();
            // Send verification code to the user's email
            ContentDelivery.sendVerificationCode("AnimeBlogBro", email, verificationCode);

            // Create a new User object
            User user = new User(username, password, 3, 0, "active", email, fullName, 1);

            // Set the new user as a request attribute
            //request.setAttribute("newUser", user);

            // Get the current session and set user attributes
            HttpSession session = request.getSession();
            session.setAttribute("newUser", user);
            session.setAttribute("temporary", verificationCode);

            // Forward the request to authenticateRegister.jsp
            request.getRequestDispatcher("VerificationRegister.jsp").forward(request, response);
        }
    }
}
