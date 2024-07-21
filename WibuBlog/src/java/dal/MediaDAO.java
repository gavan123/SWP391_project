/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author mindc
 */
public class MediaDAO extends DBContext {

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
        LocalDateTime datetime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_hhmmss");
        String formattedDateTime = datetime.format(formatter);
        return userID + "_" + formattedDateTime;
    }

}
