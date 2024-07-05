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
        // Retrieve the username and password from the request parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check if the username or password is empty and set an error message if true
        if (username == null || password == null) {
            request.setAttribute("errorMessage", "Username or password is empty!");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
            return;
        }

        // Hash the input password using a utility class
        String hashedInputPassword = Hash.getHash(password);

        // Create a DAO object to interact with the database
        LoginDAO dao = new LoginDAO();

        // Retrieve the hashed password and user role from the database for the given username
        String hashedPassword = dao.getHashedPassword(username);
        String userRole = dao.getUserRole(username);

        // Check if the hashed password from the database matches the hashed input password
        if (hashedPassword != null && hashedPassword.equals(hashedInputPassword)) {
            // Create a session and set the user attribute if the login is successful
            HttpSession session = request.getSession();
            // Set session timeout to 30 minutes (30 minutes * 60 seconds)
            session.setMaxInactiveInterval(30 * 60);
            UserDAO userdao = new UserDAO();
            User user = userdao.getUserByUsername(username);
            if (user != null) {
                session.setAttribute("user", user);
                // Set attributes for role checks
                session.setAttribute("isAdmin", dao.isAdmin(username));
                session.setAttribute("isMod", dao.isMod(username));
                session.setAttribute("isMember", dao.isMember(username));
                // Redirect the user to the appropriate home page based on their role
                if (userRole != null) {
                    switch (userRole) {
                        case "Admin":
                        case "Mod":
                        case "Member":
                            request.getRequestDispatcher("Home.jsp").forward(request, response);
                            break;
                        default:
                            // Set an error message if the user role is not recognized
                            request.setAttribute("errorMessage", "User role not recognized.");
                            request.getRequestDispatcher("Login.jsp").forward(request, response);
                            break;
                    }
                }
            }
        } else {
            // Set an error message if the login fails due to invalid username or password
            request.setAttribute("errorMessage", "Login failed! Invalid username or password.");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
