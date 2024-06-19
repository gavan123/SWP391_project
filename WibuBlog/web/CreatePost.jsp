<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="dal.CategoryDAO" %>
<%@ page import="model.Category" %>
<%@ page import="model.Genre" %>
<%@ page import="dal.GenreDAO" %>
<%@page import="java.util.List"%>
        
        
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create Post</title>
       
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

        <%CategoryDAO categoryDAO = new CategoryDAO();
        GenreDAO genreDAO = new GenreDAO(); %>
        
        <% List<Category> categories = categoryDAO.getCategoryNames();
        List<Genre> genres = genreDAO.getAllGenres();%>
        
        


        <style>
            .btn-check {
                display: none;
            }
            .btn-check + .btn {
                cursor: pointer;
            }
            .btn-check:checked + .btn {
                background-color: #007bff;
                color: white;
            }
        </style>
    </head>
    <body>
        <div class="container mt-4">
            <h2>Create a New Post</h2>
            <form action="createPost" method="post">
                <div class="form-group">
                    <label for="title">Title:</label>
                    <input type="text" class="form-control" id="title" name="title" maxlength="150" required>
                </div>
                <div class="form-group">
                    <label>Categories:</label><br>
                    <c:forEach var="category" items="${categories}">
                        <input type="checkbox" class="btn-check" id="category-${category.categoryId}" name="category" value="${category.categoryId}">
                        <label class="btn btn-outline-primary" for="category-${category.categoryId}">${category.name}</label>
                    </c:forEach>
                </div>
                <div class="form-group">
                    <label>Genres:</label><br>
                    <c:forEach var="genre" items="${genres}">
                        <input type="checkbox" class="btn-check" id="genre-${genre.genreID}" name="genre" value="${genre.genreID}">
                        <label class="btn btn-outline-primary" for="genre-${genre.genreID}">${genre.name}</label>
                    </c:forEach>
                </div>
                <div class="form-group">
                    <label for="content">Content:</label>
                    <textarea class="form-control" id="content" name="content" rows="5" required></textarea>
                </div>
                <div class="form-group">
                    <label for="source">Source:</label>
                    <input type="text" class="form-control" id="source" name="source">
                </div>
                <div class="form-group">
                    <label for="image">Image URL:</label>
                    <input type="text" class="form-control" id="image" name="image">
                </div>
                <button type="submit" class="btn btn-primary">Create Post</button>
            </form>
        </div>
    </body>
</html>
