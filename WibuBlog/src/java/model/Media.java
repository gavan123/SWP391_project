/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author mindc
 */
public class Media {
    private int mediaId;
    private int userId;
    private String fileName;
    private String path;
    private String type;
    private LocalDateTime uploaded;

    public Media() {
    }

    public Media(int mediaId, int userId, String fileName, String path, String type, LocalDateTime uploaded) {
        this.mediaId = mediaId;
        this.userId = userId;
        this.fileName = fileName;
        this.path = path;
        this.type = type;
        this.uploaded = uploaded;
    }

    
    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getUploaded() {
        return uploaded;
    }

    public void setUploaded(LocalDateTime uploaded) {
        this.uploaded = uploaded;
    }
    
}
