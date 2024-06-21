/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.MediaDAO;
import dal.PostDAO;
import utility.ImageHandler;
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
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import model.Post;
import model.User;

/**
 *
 * @author ADMIN
 */
@MultipartConfig
@WebServlet(name = "ImageUpload", urlPatterns = {"/imageUpload"})
public class ImageUpload extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy đường dẫn thực tế đến thư mục gốc của ứng dụng web
        String realPath = request.getServletContext().getRealPath("/");
        MediaDAO mediaDAO = new MediaDAO();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
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
                fileNames.add(submittedFileName);
                String encodedMediaName = mediaDAO.encodeMediaName(user.getUserId()) + "." + ImageHandler.getExtension(submittedFileName);
                PostDAO postDAO = new PostDAO();
                Post post = (Post)session.getAttribute("newPost");
                post.setImage("http://localhost:9999/WibuBlog/images/game/" + encodedMediaName);
                int postID = postDAO.getPostIDJustInserted(user.getUserId());
                postDAO.setPostImage("http://localhost:9999/WibuBlog/images/game/" + encodedMediaName, postID);
                session.removeAttribute("newPost");
                // Đọc và lưu ảnh vào thư mục "game"
                try (InputStream input = part.getInputStream()) {
                    BufferedImage image = ImageIO.read(input);
                    ImageHandler.saveImage(image, gameDirectory.toString(),
                            encodedMediaName, ImageHandler.getExtension(encodedMediaName));
                } catch (IOException e) {
                    response.getWriter().println("Error reading or saving image: " + e.getMessage());
                    return;
                }
            }
        }
        
        response.sendRedirect("Home.jsp");
        
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {

        // Gửi phản hồi về client
        response.getWriter().println("Upload thành công các ảnh vào thư mục: " + gameDirectory.toString()+"/"+fileNames.get(0));

        // Sau khi xử lý xong, chuyển hướng về trang Home.jsp hoặc trang khác
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
