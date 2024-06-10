/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Media;
import model.User;

/**
 *
 * @author mindc
 */
public class MediaDAO extends DBContext {

    public void insertMedia(Media media) {
        try {
            String sql = "insert into media values(?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, media.getUserId());
            ps.setString(2, media.getFileName());
            ps.setString(3, media.getPath());
            ps.setString(4, media.getType());
            ps.setString(5, media.getUploaded().toString());
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getMediaJustInserted(int userID) {

        try {
            String sql = "select top (1) * from Media where [userID] = ? \n"
                    + "order by Uploaded desc";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int mediaId = rs.getInt("MediaID");
            return mediaId;

        } catch (SQLException ex) {
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public String getExtension(String FileName) {
        String extension = "";
        int i = FileName.lastIndexOf('.');
        if (i > 0) {
            extension = FileName.substring(i + 1);
            return extension;
        }
        return null;
    }

    public String encodeMediaName(int userID) {
        return userID + "_" + LocalDate.now().toString() + "_" + LocalTime.now();
    }
    
    public String searchEncodedMedia(String mediaID){
        String sql = "select * from media where userID = ? ";
        
        return null;
    }
}
