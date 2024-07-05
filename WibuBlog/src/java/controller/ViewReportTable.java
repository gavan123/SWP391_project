/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ReportDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import model.Report;
import utility.DateConverter;
import dal.UserDAO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ViewReportTable", urlPatterns = {"/viewReportTable"})
public class ViewReportTable extends HttpServlet {

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
            out.println("<title>Servlet ViewReportTable</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewReportTable at " + request.getContextPath() + "</h1>");
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
        UserDAO ud = new UserDAO();
        int postId;
        try {
            postId = Integer.parseInt(request.getParameter("postId"));
        } catch (NumberFormatException e) {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("<tr><td colspan='5'>Invalid post ID provided.</td></tr>");
            return;
        }

        ReportDAO reportDAO = new ReportDAO();
        List<Report> reports = reportDAO.getReportsByPostId(postId);

        // Sort reports by usernameReport
        reports.sort(Comparator.comparing(Report::getUserId));

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        StringBuilder htmlResponse = new StringBuilder();

        if (!reports.isEmpty()) {
            for (int i = 0; i < reports.size(); i++) {
                Report report = reports.get(i);
                String formattedDate = dateFormat.format(DateConverter.convertToDateViaSqlTimestamp(report.getTimeCreated()));
                htmlResponse.append("<tr>")
                        .append("<td>").append(i + 1).append("</td>")
                        .append("<td>").append(ud.getUserByUserId(report.getUserId()).getUsername()).append("</td>")
                        .append("<td>").append(report.getReason()).append("</td>")
                        .append("<td>").append(report.getNote()).append("</td>")
                        .append("<td>").append(formattedDate).append("</td>")
                        .append("</tr>");
            }
        } else {
            htmlResponse.append("<tr><td colspan='5'>No reports found for this post.</td></tr>");
        }

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(htmlResponse.toString());
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
        processRequest(request, response);
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
