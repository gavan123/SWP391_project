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
@WebServlet(name = "AddPhoneNumber", urlPatterns = {"/addPhoneNumber"})
public class AddPhoneNumber extends HttpServlet {

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
            out.println("<title>Servlet AddPhoneNumber</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddPhoneNumber at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("AddPhoneNumber.jsp");
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String phoneNumber = request.getParameter("phoneNumber");
        UserDAO userDAO = new UserDAO();
        if(userDAO.checkPhoneNumber(phoneNumber) == true){
            request.setAttribute("errorMessage", "This number has already existed");
            request.getRequestDispatcher("AddPhoneNumber.jsp").forward(request, response);
        }
        else{
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        user.setPhoneNumber(phoneNumber);
        userDAO.setPhoneNumber(phoneNumber, user.getUserId());
        request.setAttribute("updateNumberMessage", "Update successfully");
        request.getRequestDispatcher("Profile.jsp").forward(request, response);
        }
       
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
