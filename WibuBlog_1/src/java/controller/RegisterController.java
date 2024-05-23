/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dal.UserDAO;
import dal.UserDAOImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author user
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("user");
        String password = request.getParameter("pass");
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        String agreed = request.getParameter("agreed");
        boolean status = false;
        String successMessage = "";
        String errorMessage = "";


        if (agreed != null) {
            User newUser = new User(0, agreed, agreed, 0, 0);
            newUser.setUsername(username);
            newUser.setPasswordHash(password);
            newUser.setEmail(email);
            newUser.setFullName(fullName);
            UserDAO userDAO = new UserDAOImpl();
            status = userDAO.insertUser(username, password, email, fullName);
            if (status) {
                successMessage = "Register successfully !";
            } else {
                errorMessage = "Something went wrong! Please try again!";
            }
        } else {
            errorMessage = "Please agree with terms and conditions";
        }
        request.setAttribute("successMessage", successMessage);
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}
