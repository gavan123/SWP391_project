<%-- 
    Document   : CreateTicket
    Created on : 3 Jul 2024, 13:44:16
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="dal.CategoryDAO" %>
<%@ page import="model.Category" %>
<%@ page import="model.Genre" %>
<%@ page import="dal.GenreDAO" %>
<%@ page import="java.util.List"%>
<link rel="stylesheet" href="assets/css/testcss5.css">

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
        List<Genre> genres = genreDAO.getAllGenres(); %>




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
        <script>
            function validateForm() {
                var categoryChecked = document.querySelector('input[name="category"]:checked');
                var genreChecked = document.querySelector('input[name="genre"]:checked');

                if (!categoryChecked) {
                    alert("Please select a category.");
                    return false;
                }

                if (!genreChecked) {
                    alert("Please select a genre.");
                    return false;
                }

                return true;
            }
        </script>
    </head>
    <body>
        <div class="container mt-4">
    <h2>Create a support ticket</h2>
    <form action="CreateTicket" method="post" id="uploadForm">
        <span id="username" style="font-weight: bold; color: #333;"> UserName: ${user.username} </span>
        
        <div class="form-group">
            <label for="content">If you have any problem or difficulty using WibuForum, please contact us and our support team will reach out to you as soon as possible.</label>
            <textarea class="form-control" id="content" name="content" rows="5" placeholder="Please describe your problem in detail here. You should also include the cause and any relevant links to your problem." required></textarea>
        </div>
        
        <button type="submit" class="btn btn-primary">Submit ticket</button>
    </form>
</div>
        
        
   

    </body>
</html>
