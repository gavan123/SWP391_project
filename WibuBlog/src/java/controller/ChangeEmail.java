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
import utility.ContentDelivery;
import utility.KeyGenerator;
import validation.Validator;

/**
 *
 * @author admin
 */
@WebServlet(name = "ChangeEmail", urlPatterns = {"/ChangeEmail"})
public class ChangeEmail extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangeEmail</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeEmail at " + request.getContextPath() + "</h1>");
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
        String oldEmail = user.getEmail();
        String newEmail = request.getParameter("newEmail");
        UserDAO userDAO = new UserDAO();
        
        if (newEmail.equals("")){
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Email Must Not Be Empty');");
            out.println("location='AccountSetting.jsp';");
            out.println("</script>");
        }
        else if (userDAO.getUserByEmail(newEmail) != null) {
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Email Already Existed');");
            out.println("location='AccountSetting.jsp';");
            out.println("</script>");
        } 
        else if (Validator.emailRegex(newEmail) == false){
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Invalid Email');");
            out.println("location='AccountSetting.jsp';");
            out.println("</script>");
        }
        else {
            String verificationCode = KeyGenerator.generateVerificationCode();
            ContentDelivery.sendRegistrationVerification(oldEmail, user.getUsername(), verificationCode);
            session.setAttribute("template", verificationCode);
            session.setAttribute("newEmail", newEmail);
            request.setAttribute("message", "An OTP have been sent to " + oldEmail + " please login to verify to change your email.");
            request.getRequestDispatcher("VerifyChangedEmail.jsp").forward(request, response);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
