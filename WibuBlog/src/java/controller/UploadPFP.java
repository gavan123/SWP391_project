/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.MediaDAO;
import dal.PostDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

import model.Media;
import model.Post;
import model.User;
import security.SignatureVerification;
import utility.ImageHandler;

@MultipartConfig
@WebServlet(name = "UploadPFP", urlPatterns = {"/UploadPFP"})
public class UploadPFP extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UploadPFP</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UploadPFP at " + request.getContextPath() + "</h1>");
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
        String realPath = request.getServletContext().getRealPath("/");
        MediaDAO mediaDAO = new MediaDAO();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        // Xây dựng đường dẫn tuyệt đối đến thư mục images và cắt bỏ phần "build" nếu có
        Path gameDirectory = ImageHandler
                .removeBuildFromPath(Paths.get(realPath, "images"))
                .resolve("game");
        Files.createDirectories(gameDirectory);

// Lưu danh sách các file ảnh và tên file
        List<String> fileNames = new ArrayList<>();

// Lấy các file ảnh từ request và lưu vào thư mục "game"
        for (Part part : request.getParts()) {
            String submittedFileName = part.getSubmittedFileName();
            if (submittedFileName != null && ImageHandler.isImageFile(submittedFileName)) {
                fileNames.add(submittedFileName);
                // Encode media name and set image to the post
                String encodedMediaName = mediaDAO.encodeMediaName(user.getUserId()) + "." + ImageHandler.getExtension(submittedFileName);           
                UserDAO userDAO = new UserDAO();
                userDAO.updateProfilePhoto(user.getUserId(), encodedMediaName);
                user.setProfilePhoto(encodedMediaName);

                // Đọc và lưu ảnh vào thư mục "game"
                try (InputStream input = part.getInputStream()) {
                    BufferedImage image = ImageIO.read(input);
                    ImageHandler.saveImage(image, gameDirectory.toString(),
                            encodedMediaName, ImageHandler.getExtension(encodedMediaName));
                    response.getWriter().println("Upload thành công các ảnh vào thư mục: " + gameDirectory.toString());
                } catch (IOException e) {
                    response.getWriter().println("Error reading or saving image: " + e.getMessage());
                    return;
                }
            } else {
                response.getWriter().println("File " + submittedFileName + " is not an image.");
            }
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        response.sendRedirect("Profile.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
