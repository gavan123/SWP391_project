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
import model.User;

import utility.KeyGenerator;
import validation.Validator;

/**
 *
 * @author user
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {
    
    private String verificationCode = "";
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");      
        UserDAO ud = new UserDAO();
        if (ud.emailExists(email)) {
            String errorMessage = "This email existed!";
            request.setAttribute(errorMessage, "errorMessage");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        }
        else if (ud.getUserByUsername(username) != null){
            String errorMessage = "This username existed!";
            request.setAttribute(errorMessage, "errorMessage");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        }
        else if (!Validator.usernameRegex(username)){
            String errorMessage = "Invalid username! "
                    + "please enter at least 4-20 characters,"
                    + " alphabetic number and characters";
            request.setAttribute(errorMessage, "errorMessage");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        }
        else if (!Validator.passwordRegex(password)){
            String errorMessage = "Invalid password! "
                    + "password must contain 8-50 characters,"
                    + " one uppercase,"
                    + " one lowercase and"
                    + " one special character";
            request.setAttribute(errorMessage, "errorMessage");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        }
        else {        
            verificationCode = KeyGenerator.generateVerificationCode();
            ContentDelivery.sendVerificationCode("test", email, verificationCode);
            User user = new User(0,
                                  username, 
                        password,
                             3,
                             0,
                             "active",
                                   email, 
                                   fullName,
                              1);
            request.setAttribute("newUser",user );
            request.getRequestDispatcher("authenticateRegister.jsp").forward(request, response);
        }      
    }
}
