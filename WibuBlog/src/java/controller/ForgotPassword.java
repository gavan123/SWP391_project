/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import utility.KeyGenerator;
import dal.UserDAO;
import utility.ContentDelivery;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "ForgotPassword", urlPatterns = {"/ForgotPassword"})
public class ForgotPassword extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ForgotPassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgotPassword at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve the email parameter from the request
        String email = request.getParameter("email");
        UserDAO userDao = new UserDAO();

        // Check if the email exists in the database
        if (userDao.emailExists(email)) {
            // Generate a verification code
            String verificationCode = KeyGenerator.generateVerificationCode();

            // Send the verification code to the user's email
            ContentDelivery.sendVerificationCode(email, verificationCode);

            // Set email and verification code as request attributes
            request.setAttribute("email", email);
            HttpSession session = request.getSession();
            session.setAttribute("template", verificationCode);
            request.setAttribute("template", verificationCode);

            // Forward the request to Authenticate.jsp
            request.getRequestDispatcher("Authenticate.jsp").forward(request, response);
        } else {
            // If the email does not exist, set an error message
            String errorMessage = "This email does not exist!";
            request.setAttribute("errorMessage", errorMessage);
            request.setAttribute("email", email);

            // Forward the request to ForgotPassword.jsp
            request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
