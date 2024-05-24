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
import model.User;
import com.ContentDelivery;

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
        
        String email = request.getParameter("email");
        UserDAO ud = new UserDAO();

        if (ud.emailExists(email)) {
            String verificationCode = KeyGenerator.generateVerificationCode();
            ContentDelivery.sendVerificationCode(email, verificationCode);
            request.setAttribute("email", email);
            request.setAttribute("template", verificationCode);
            request.getRequestDispatcher("Authenticate.jsp").forward(request, response);
        } else {
            String errorMessage = "This email does not exist!";
            request.setAttribute(errorMessage, "errorMessage");
            request.setAttribute(email, "email");
            request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String verificationCode = request.getParameter("template");
        String input = request.getParameter("response");
        
        if (input.equals(verificationCode)) {
            request.setAttribute(email, "email");
            request.getRequestDispatcher("ResetPassword.jsp").forward(request, response);
        } else {
            String errorMessage = "Incorrect verification code.";
            request.setAttribute(errorMessage, "errorMessage");
            request.setAttribute(email, "email");
            request.setAttribute("template", verificationCode);
            request.getRequestDispatcher("Authenticate.jsp").forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
