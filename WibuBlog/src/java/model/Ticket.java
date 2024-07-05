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
public class Ticket {
    private int ticketId;
    private int userId;
    private LocalDateTime timeCreated;
    private String content;
    private String status;
    private String note;
    
    public Ticket() {}

    public Ticket(int userId, LocalDateTime timeCreated, String content) {
        this.userId = userId;
        this.timeCreated = timeCreated;
        this.content = content;
        this.status = "Pending";
        this.note = "";
    }
    

    public Ticket(int userId, LocalDateTime timeCreated, String content, String status, String note) {
        this.userId = userId;
        this.timeCreated = timeCreated;
        this.content = content;
        this.status = status;
        this.note = note.length() > 0 ? note : "";
    }

    public Ticket(int ticketId, int userId, LocalDateTime timeCreated, String content, String status, String note) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.timeCreated = timeCreated;
        this.content = content;
        this.status = status;
        this.note = note.length() > 0 ? note : "";
    }

    public int getTicketId() {
        return ticketId;
    }

    public int getUserId() {
        return userId;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public String getContent() {
        return content;
    }

    public String getStatus() {
        return status;
    }

    public String getNote() {
        return note;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setNote(String note) {
        this.note = note;
    }
    

}
