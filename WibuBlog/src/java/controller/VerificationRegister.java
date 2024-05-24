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

/**
 *
 * @author mindc
 */
@WebServlet(name = "VerificationRegister", urlPatterns = {"/verificationRegister"})
public class VerificationRegister extends HttpServlet {

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
            out.println("<title>Servlet VerificationRegister</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerificationRegister at " + request.getContextPath() + "</h1>");
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
        // Create an instance of UserDAO for database operations
        UserDAO userDao = new UserDAO();

        // Retrieve the verification code and user input from the request
        String expectedVerificationCode = request.getParameter("template");
        String userInputCode = request.getParameter("response");

        // Check if the input verification code matches the expected verification code
        if (userInputCode != null && userInputCode.equals(expectedVerificationCode)) {
            // Get the current session
            HttpSession session = request.getSession();

            // Retrieve the new user from the session
            User newUser = (User) session.getAttribute("newUser");

            if (newUser != null) {
                // Add the new user to the database
                userDao.addUser(newUser);

                // Retrieve the user ID from the database and set it in the session
                int userId = userDao.getUserByEmail(newUser.getEmail()).getUserId();
                session.setAttribute("userid", userId);

                // Remove the temporary attribute from the session
                session.removeAttribute("temporary");

                // Forward the request to home.jsp
                request.getRequestDispatcher("home.jsp").forward(request, response);
            } else {
                // If no new user found in session, redirect to an error page or show an error message
                request.setAttribute("errorMessage", "No user found in session.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else {
            // If verification fails, set the verification code as a request attribute and forward back to verification page
            request.setAttribute("template", expectedVerificationCode);
            request.setAttribute("errorMessage", "Verification code does not match.");
            request.getRequestDispatcher("verificationRegister.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
