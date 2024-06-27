/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class TopViewedGenre {
    private String genre;
    private int totalPosts;
    private int totalView;

    public TopViewedGenre() {
    }

    public TopViewedGenre(String genre, int totalPosts, int totalView) {
        this.genre = genre;
        this.totalPosts = totalPosts;
        this.totalView = totalView;
    }
    
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getTotalPosts() {
        return totalPosts;
    }

    public void setTotalPosts(int totalPosts) {
        this.totalPosts = totalPosts;
    }

    public int getTotalView() {
        return totalView;
    }

    public void setTotalView(int totalView) {
        this.totalView = totalView;
    }
    
}
