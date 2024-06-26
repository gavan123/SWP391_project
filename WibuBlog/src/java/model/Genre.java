/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class Genre {

    private int genreId;
    private String name;
    private String description;

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }

    public Genre(int genreId, String name) {
        this.genreId = genreId;
        this.name = name;
    }

    public Genre(int genreId, String name, String description) {
        this.genreId = genreId;
        this.name = name;
        this.description = description;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
