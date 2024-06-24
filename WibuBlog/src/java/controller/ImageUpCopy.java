/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utility.ImageHandler;

/**
 *
 * @author ADMIN
 */
@MultipartConfig
@WebServlet(name = "ImageUpCopy", urlPatterns = {"/imageUpCopy"})
public class ImageUpCopy extends HttpServlet {

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
            out.println("<title>Servlet ImageUpCopy</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ImageUpCopy at " + request.getContextPath() + "</h1>");
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
        // Lấy đường dẫn thực tế đến thư mục gốc của ứng dụng web
        String realPath = request.getServletContext().getRealPath("/");

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

                // Đọc và lưu ảnh vào thư mục "game"
                try (InputStream input = part.getInputStream()) {
                    BufferedImage image = ImageIO.read(input);
                    ImageHandler.saveImage(image, gameDirectory.toString(), submittedFileName, ImageHandler.getExtension(submittedFileName));
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
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

// Chuyển hướng về trang Home.jsp hoặc trang khác sau khi xử lý xong
        request.setAttribute("image", fileNames.isEmpty() ? null : fileNames);
        request.getRequestDispatcher("Home.jsp").forward(request, response);
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
