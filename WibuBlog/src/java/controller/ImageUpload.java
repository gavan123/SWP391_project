/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import utility.ImageHandler;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author ADMIN
 */
@MultipartConfig
@WebServlet(name = "ImageUpload", urlPatterns = {"/imageUpload"})
public class ImageUpload extends HttpServlet {

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
            out.println("<title>Servlet ImageHandler</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ImageHandler at " + request.getContextPath() + "</h1>");
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

        // Xây dựng đường dẫn tuyệt đối đến thư mục images
        Path baseImagePath = Paths.get(realPath, "images");

        // Cắt bỏ phần "build" nếu có trong đường dẫn
        baseImagePath = ImageHandler.removeBuildFromPath(baseImagePath);

        // Tạo thư mục "game" nếu chưa tồn tại
        Path gameDirectory = baseImagePath.resolve("game");
        Files.createDirectories(gameDirectory);

        // Lưu danh sách các file ảnh và tên file
        List<String> fileNames = new ArrayList<>();
        
        // Lấy các file ảnh từ request và lưu vào thư mục "game"
        for (Part part : request.getParts()) {
            String submittedFileName = part.getSubmittedFileName();
            if (submittedFileName != null && ImageHandler.isImageFile(submittedFileName)) {
                String fileName = Paths.get(submittedFileName).getFileName().toString();
                fileNames.add(fileName);

                try (InputStream input = part.getInputStream()) {
                    BufferedImage image = ImageIO.read(input);
                    ImageHandler.saveImage(image, gameDirectory.toString(), fileName, ImageHandler.getExtension(fileName));
                } catch (IOException e) {
                    response.getWriter().println("Error reading or saving image: " + e.getMessage());
                    return;
                }
            }
        }

        // Tạm dừng 2 giây để đảm bảo hình ảnh được lưu trước khi forward
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // Handle interrupted exception if needed
        }

        // Gửi phản hồi về client
        response.getWriter().println("Upload thành công các ảnh vào thư mục: " + gameDirectory.toString());

        // Sau khi xử lý xong, chuyển hướng về trang Home.jsp hoặc trang khác
        //request.setAttribute("image", fileNames.isEmpty() ? null : fileNames);
        //request.getRequestDispatcher("Home.jsp").forward(request, response);
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
