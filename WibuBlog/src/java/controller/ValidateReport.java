/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.PostDAO;
import dal.ReportDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
@WebServlet(name = "ValidateReport", urlPatterns = {"/ValidateReport"})
public class ValidateReport extends HttpServlet {

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
            out.println("<title>Servlet ValidateReport</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ValidateReport at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        HttpSession session = request.getSession();
        ReportDAO rd = new ReportDAO();
        PostDAO pd = new PostDAO();
        String note = request.getParameter("note");
        int reportId = Integer.parseInt(request.getParameter("reportId"));
        boolean approved = Boolean.parseBoolean((String) request.getParameter("choice"));

        if (reportId <= 0) {
            request.setAttribute("errorMessage", "Failed to retrieve report ID.");
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }

        if (approved) {
            rd.setStatusApproved(reportId);
            rd.setNote(note,reportId);
            rd.DeleteAllReportWithTheSameId(rd.getPostIdByReportId(reportId), "Approved");
            pd.deletePost(rd.getReportById(reportId).getPostId());
            PrintWriter out = response.getWriter();
            out.print(rd.getPostIdByReportId(reportId));
        } else {
            rd.setNote(note,reportId);
            rd.DeleteAllReportWithTheSameId(rd.getPostIdByReportId(reportId), "Rejected");
            rd.setStatusRejected(reportId);
        }
        
       // request.getRequestDispatcher("ReportList.jsp").forward(request, response);

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
