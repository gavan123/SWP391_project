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
import java.time.LocalDateTime;
import model.Report;
import model.User;

@WebServlet(name = "CreateReport", urlPatterns = {"/CreateReport"})
public class CreateReport extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateReport</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateReport at " + request.getContextPath() + "</h1>");
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
        ReportDAO rd = new ReportDAO(); // Assuming ReportDAO requires a connection parameter in its constructor
        PostDAO pd = new PostDAO(); // Assuming PostDAO requires a connection parameter in its constructor

        User user = (User) session.getAttribute("user");
        String reason = request.getParameter("reportReasons");
        int postId = Integer.parseInt(request.getParameter("postid"));

        if (user != null && reason != null && reason.length() > 0 && postId > 0) {
            String username = user.getUsername();
            String postTitle = pd.getPostById(postId).getTitle(); // Assuming PostDAO has a method to get post by ID

            boolean reportCreated = rd.createReport(username, postTitle, reason);

            if (reportCreated) {
                request.getRequestDispatcher("postDetail?postId=" + postId).forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Failed to create report.");
                request.getRequestDispatcher("Error.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Invalid input parameters.");
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
