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
import utility.ProfanityFilter;

/**
 *
 * @author admin
 */
@WebServlet(name = "UpdateBio", urlPatterns = {"/UpdateBio"})
public class UpdateBio extends HttpServlet {

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
            out.println("<title>Servlet UpdateBio</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateBio at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String newBio = (String)request.getParameter("newBio");
        if (ProfanityFilter.checkProfanity(newBio)) {          
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Please reconsider your profile');");
            out.println("location='Profile.jsp';");
            out.println("</script>");
        }
        else{
            UserDAO userDAO = new UserDAO();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            userDAO.updateBio(newBio, user.getUserId());
            user.setBio(newBio);
            response.sendRedirect("Profile.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    public static void main(String[] args)  {
        String newBio = "bucky";
        System.out.println(ProfanityFilter.checkProfanity(newBio));
    }
}
