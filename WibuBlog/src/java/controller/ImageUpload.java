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
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private static final Path BASE_IMAGE_PATH = FileSystems.getDefault().getPath("web", "images").toAbsolutePath();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy đường dẫn thực tế đến thư mục gốc của ứng dụng web
        String realPath = request.getServletContext().getRealPath("/");

        // Xây dựng đường dẫn tuyệt đối đến thư mục images
        Path baseImagePath = Paths.get(realPath, "images");

        // Retrieve the image Part from the request
        Part filePart = request.getPart("image");

        // Extract filename from content-disposition header of part
        String fileName = filePart.getSubmittedFileName();

        // Determine format based on filename extension
        String format = ImageHandler.getExtension(fileName);

        try (InputStream input = filePart.getInputStream()) {
            // Read input stream into BufferedImage
            BufferedImage image = ImageIO.read(input);

            // Save the image using ImageHandler
            String directory = "game"; // Đổi tên thư mục nếu cần
            ImageHandler.saveImage(image, baseImagePath.resolve(directory).toString(), fileName, format);

            // Construct the full path where the image is saved
            Path savedImagePath = baseImagePath.resolve(directory).resolve(fileName);

            // Optionally, respond with the path savedImagePath
//            String responseMessage = "Image uploaded and saved successfully. Path: " + savedImagePath.toString();
//            response.getWriter().println(responseMessage);
            
            request.setAttribute("image", fileName);
            request.getRequestDispatcher("Home.jsp").forward(request, response);
        } catch (IOException e) {
            // Handle exception (e.g., log error, respond with error message)
            response.getWriter().println("Error uploading image: " + e.getMessage());
        }
    }

// Helper method to extract filename from content-disposition header
    private String getSubmittedFileName(Part part) {
        String contentDispositionHeader = part.getHeader("content-disposition");
        for (String cd : contentDispositionHeader.split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1)
                        .substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
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
