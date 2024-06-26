<%-- 
    Document   : AnimeDetailContent
    Created on : Jun 25, 2024, 4:10:10 PM
    Author     : minht
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="model.Anime" %>
<%@ page import="dal.AnimeDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.format.FormatStyle" %>
<%@ page import="java.time.LocalDateTime" %>


<c:set var="animeId" value="${param.animeId}" />

<%
    String animeIdParam = request.getParameter("animeId");
    if (animeIdParam != null) {
        try {
            int animeId = Integer.parseInt(animeIdParam);
            AnimeDAO animeDAO = new AnimeDAO();
            Anime anime = animeDAO.getAnimeDetailById(animeId);
            
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Anime Detail - <%= anime.getTitle() %></title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
              integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
              crossorigin="anonymous">
        <style>
            .card {
                
            }
        </style>
    </head>
    <body>

        <div class="container mt-4">
            <div class="row">
                <div class="col-lg-8">
                    <div class="card mb-3">
                        <img src="<%= anime.getImageAnime() %>" class="card-img-top" alt="<%= anime.getTitle() %>">
                        <div class="card-body">
                            <h1 class="card-title"><%= anime.getTitle() %></h1>
                            <h6 class="card-subtitle mb-2 text-muted">
                                <i class="fas fa-calendar-alt"></i>
                                Released: <%= anime.getReleaseDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) %>
                            </h6>
                            <h6 class="card-subtitle mb-2 text-muted">
                                <i class="fas fa-film"></i> Episodes: <%= anime.getEpisodes() %>
                            </h6>
                            <h6 class="card-subtitle mb-2 text-muted">
                                <i class="fas fa-video"></i> Studio: <%= anime.getStudio() %>
                            </h6>
                            <h6 class="card-subtitle mb-2 text-muted">
                                <i class="fas fa-tag"></i> Genre: <%= anime.getGenre() %>
                            </h6>
                            <h6 class="card-subtitle mb-2 text-muted">
                                <i class="fas fa-info-circle"></i> Status: <%= anime.getStatus() %>
                            </h6>
                            <p class="card-text">
                                <%= anime.getSynopsis() %>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>


