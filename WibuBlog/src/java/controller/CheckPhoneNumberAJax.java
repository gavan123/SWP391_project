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
import validation.Validator;

/**
 *
 * @author admin
 */
@WebServlet(name = "CheckPhoneNumberAJax", urlPatterns = {"/CheckPhoneNumber"})
public class CheckPhoneNumberAJax extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CheckPhoneNumberAJax</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckPhoneNumberAJax at " + request.getContextPath() + "</h1>");
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
        String newPhoneNumber = request.getParameter("newPhoneNumber");
        UserDAO userDAO = new UserDAO();
        if(newPhoneNumber.equals("")){
             response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print("Phone Number Must Not Be Empty");
        }

        else{
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print("");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
