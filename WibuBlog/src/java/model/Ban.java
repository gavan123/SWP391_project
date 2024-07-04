/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author admin
 */
public class Ban {
    private int banId;
    private int bannedUserId;
    private int banSourceId;
    private String banReason;
    private LocalDateTime bannedDate;
    private LocalDateTime expireDate;
    private int sourcePostId;

    public Ban() {
    }

    public Ban(int banId, int bannedUserId, int banSourceId, String banReason, LocalDateTime bannedDate, LocalDateTime expireDate,int sourcePostId) {
        this.banId = banId;
        this.bannedUserId = bannedUserId;
        this.banSourceId = banSourceId;
        this.banReason = banReason;
        this.bannedDate = bannedDate;
        this.expireDate = expireDate;
        this.sourcePostId = sourcePostId;
    }
    
    public Ban( int bannedUserId, int banSourceId, String banReason, LocalDateTime bannedDate, LocalDateTime expireDate) {
        this.bannedUserId = bannedUserId;
        this.banSourceId = banSourceId;
        this.banReason = banReason;
        this.bannedDate = bannedDate;
        this.expireDate = expireDate;
    }


    public Ban(int bannedUserId, int banSourceId, String banReason, LocalDateTime bannedDate, LocalDateTime expireDate,int sourcePostId) {
        this.bannedUserId = bannedUserId;
        this.banSourceId = banSourceId;
        this.banReason = banReason;
        this.bannedDate = bannedDate;
        this.expireDate = expireDate;
        this.sourcePostId = sourcePostId;
    }
   
    public int getBanId() {
        return banId;
    }

    public void setBanId(int banId) {
        this.banId = banId;
    }

    public int getBannedUserId() {
        return bannedUserId;
    }

    public void setBannedUserId(int bannedUserId) {
        this.bannedUserId = bannedUserId;
    }

    public int getBanSourceId() {
        return banSourceId;
    }

    public void setBanSourceId(int banSourceId) {
        this.banSourceId = banSourceId;
    }

    public String getBanReason() {
        return banReason;
    }

    public void setBanReason(String banReason) {
        this.banReason = banReason;
    }

    public LocalDateTime getBannedDate() {
        return bannedDate;
    }

    public void setBannedDate(LocalDateTime bannedDate) {
        this.bannedDate = bannedDate;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public int getSourcePostId() {
        return sourcePostId;
    }

    public void setSourcePostId(int sourcePostId) {
        this.sourcePostId = sourcePostId;
    }

   
    
}
