/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author admin
 */
public class Anime {

    private int animeId;
    private String title;
    private String synopsis;
    private String genre;
    private int episodes;
    private String status;
    private LocalDateTime releaseDate;
    private String studio;
    private String imageAnime;

    public Anime() {
    }

    public Anime(int animeId, String title, String synopsis, int episodes, String status, String imageAnime) {
        this.animeId = animeId;
        this.title = title;
        this.synopsis = synopsis;
        this.episodes = episodes;
        this.status = status;
        this.imageAnime = imageAnime;
    }
    

    public Anime(int animeId, String title, String synopsis, String genre, int episodes, String status, LocalDateTime releaseDate, String studio, String imageAnime) {
        this.animeId = animeId;
        this.title = title;
        this.synopsis = synopsis;
        this.genre = genre;
        this.episodes = episodes;
        this.status = status;
        this.releaseDate = releaseDate;
        this.studio = studio;
        this.imageAnime = imageAnime;
    }

    public int getAnimeId() {
        return animeId;
    }

    public void setAnimeId(int animeId) {
        this.animeId = animeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getImageAnime() {
        return imageAnime;
    }

    public void setImageAnime(String imageAnime) {
        this.imageAnime = imageAnime;
    }


    
}

