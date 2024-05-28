/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import utility.ContentDelivery;
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
import utility.KeyGenerator;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ForgotAuthenthicate", urlPatterns = {"/forgotAuthenthicate"})
public class ForgotAuthenthicate extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ForgotAuthenthicate</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgotAuthenthicate at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Authenticate.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Initialize DAO and session
        UserDAO userDao = new UserDAO();
        HttpSession session = request.getSession();

        // Retrieve verification code from session and email from session or request
        String expectedVerificationCode = (String) session.getAttribute("template");
        String email = (String) session.getAttribute("email");

        String userInputCode = request.getParameter("code");
        User user = userDao.getUserByEmail(email);

        if (userInputCode != null && userInputCode.equals(expectedVerificationCode)) {
            // Generate a new password
            String newPassword = KeyGenerator.generateVerificationCode();

            // Send the new password via email
            ContentDelivery.sendNewPassword(email, newPassword);

            // Hash the new password
            String hashedNewPassword = Hash.getHash(newPassword);

            // Update the new password in the database
            userDao.changePassword(hashedNewPassword, user.getUserId());
            // Remove sessions
            session.removeAttribute("template");
            session.removeAttribute("email");
            // Redirect the user to the login page
            response.sendRedirect("Login.jsp");
        } else {
            // If the verification code is incorrect, redirect the user to re-enter the verification code
            response.sendRedirect("Authenticate.jsp");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}