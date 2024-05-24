/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.LoginDAO;
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

/**
 *
 * @author minht
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class Login extends HttpServlet {

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
            out.println("<title>Servlet Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null) {
            request.setAttribute("errorMessage", "Username or password is empty!");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }

        String hashedInputPassword = Hash.getHash(password);

        LoginDAO dao = new LoginDAO();
        String hashedPassword = dao.getHashedPassword(username);
        String userRole = dao.getUserRole(username);

        if (hashedPassword != null && hashedPassword.equals(hashedInputPassword)) {
            HttpSession session = request.getSession();
            UserDAO userdao = new UserDAO();
            User user = userdao.getUserByUsername(username);
            session.setAttribute("user", user);

//            if (userRole != null && userRole.equals("Admin")) {
//                // Redirect to Admin
//                response.sendRedirect("Admin.jsp");
//            } else if (userRole != null && userRole.equals("Mod")) {
//                // Redirect to Mod
//                response.sendRedirect("Mod.jsp");
//            } else if (userRole != null && userRole.equals("Member")) {
//                // Redirect to guest Member
//                response.sendRedirect("Member.jsp");
//
//            }
            if (userRole != null) {
                switch (userRole) {
                    case "Admin" -> response.sendRedirect("home.jsp");
                    case "Mod" -> response.sendRedirect("home.jsp");
                    case "Member" -> response.sendRedirect("home.jsp");
                    default -> {
                        request.setAttribute("errorMessage", "User role not recognized.");
                        request.getRequestDispatcher("Login.jsp").forward(request, response);
                    }
                }
            }
        } else {
            request.setAttribute("errorMessage", "Login failed! Invalid username or password.");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
