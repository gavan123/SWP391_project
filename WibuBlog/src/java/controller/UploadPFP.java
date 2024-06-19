/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.MediaDAO;
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
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import model.Media;
import model.User;

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
        //create user object to insert to database
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        MediaDAO mediaDAO = new MediaDAO();

        //fetch file from jsp
        Part file = request.getPart("pfp");
        String imageFileName = file.getSubmittedFileName();
        String encodedMediaName = mediaDAO.encodeMediaName(user.getUserId()) + "." + mediaDAO.getExtension(imageFileName);

        //set an upload path to store media
        String uploadPath = "C:/Users/admin/Documents/GitHub/SWP391_project/WibuBlog/web/PFP/" + imageFileName; //sua duong dan 
        try {
            FileOutputStream fos = new FileOutputStream(uploadPath);
            InputStream is = file.getInputStream();

            byte[] data = new byte[is.available()];
            is.read(data);

            //upload file to path
            fos.write(data);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        //encode file name in case of duplicate file name but different content
        File source = new File("C:\\Users\\admin\\Documents\\GitHub\\SWP391_project\\WibuBlog\\web\\PFP\\" + imageFileName); //sua duong dan 
        File target = new File("C:\\Users\\admin\\Documents\\GitHub\\SWP391_project\\WibuBlog\\web\\PFP\\" + encodedMediaName); //sua duong dan 
         if (source.renameTo(target)) {                   
            out.println("File is renamed"); 
        } 
        else { 
            out.println("File cannot be renamed"); 
        } 
        Media media = new Media(0,
                user.getUserId(),
                mediaDAO.encodeMediaName(user.getUserId()),
                "http://localhost:9999/WibuBlog/PFP/" + encodedMediaName,
                mediaDAO.getExtension(imageFileName),
                LocalDateTime.now());

        //insert into database
        mediaDAO.insertMedia(media);
        UserDAO userDAO = new UserDAO();
        userDAO.updateProfilePhoto(user.getUserId(), mediaDAO.getMediaJustInserted(user.getUserId()));
        user.setProfilePhoto(mediaDAO.getMediaJustInserted(user.getUserId()));
        //go back to profile
         response.sendRedirect("Profile.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
