/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DBContext;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author admin
 */
@WebServlet(name = "CheckUsernameAJax", urlPatterns = {"/CheckUsername"})
public class CheckUsernameAJax extends HttpServlet {
    protected Connection connection;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangeUsername</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeUsername at " + request.getContextPath() + "</h1>");
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
        String newUsername = request.getParameter("newUsername");
        UserDAO userDAO = new UserDAO();
        HttpSession session = request.getSession();
        if(userDAO.getUserByUsername(newUsername)!= null){
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print("Already Exists");
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
