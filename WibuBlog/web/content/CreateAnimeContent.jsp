<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="dal.AnimeDAO" %>
<%@ page import="model.Anime" %>
<%@ page import="model.Genre" %>
<%@ page import="dal.GenreDAO" %>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create Anime</title>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

        <%        GenreDAO genreDAO = new GenreDAO(); %>
        <%        List<Genre> genres = genreDAO.getAllGenres(); %>
        <style>
            .form-group {
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>
        <div class="container mt-4">
            <h2>Create a New Anime</h2>
            <form action="createAnime" method="post">
                <div class="form-group">
                    <label for="title">Title:</label>
                    <input type="text" class="form-control" id="title" name="title" maxlength="150" required>
                </div>
                <div class="form-group">
                    <label for="synopsis">Synopsis:</label>
                    <textarea class="form-control" id="synopsis" name="synopsis" rows="5" required></textarea>
                </div>
                <div class="form-group">
                    <label for="genre">Genre:</label><br>
                    <c:forEach var="genre" items="<%=genres%>">
                        <input type="radio" class="btn-check" id="genre-${genre.genreId}" name="genre" value="${genre.genreId}">
                        <label class="btn btn-outline-primary" for="genre-${genre.genreId}">${genre.name}</label>
                    </c:forEach>
                </div>
                <div class="form-group">
                    <label for="episodes">Episodes:</label>
                    <input type="number" class="form-control" id="episodes" name="episodes" min="1" required>
                </div>
                <div class="form-group">
                    <label for="status">Status:</label>
                    <select class="form-control" id="status" name="status" required>
                        <option value="Ongoing">Ongoing</option>
                        <option value="Released">Released</option>
                        <option value="Completed">Completed</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="releaseDate">Release Date:</label>
                    <input type="date" class="form-control" id="releaseDate" name="releaseDate" required>
                </div>
                <div class="form-group">
                    <label for="studio">Studio:</label>
                    <input type="text" class="form-control" id="studio" name="studio" required>
                </div>
                <div class="form-group">
                    <label for="image">Image URL</label>
                    <div class="custom-file">
                        <input type="file" class="custom-file-input" id="image" name="image" 
                               accept="image/png,image/jpeg,image/jpg,image/bmp,image/gif,image/webp" required>
                        <label class="custom-file-label" for="image">Choose file...</label>
                        <small class="form-text text-muted">Accepted formats: PNG, JPEG, JPG, BMP, GIF, WEBP</small>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Create Anime</button>
            </form>
        </div>
    </body>
</html>
