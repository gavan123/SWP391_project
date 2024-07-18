<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="dal.CategoryDAO" %>
<%@ page import="model.Category" %>
<%@ page import="model.Genre" %>
<%@ page import="dal.GenreDAO" %>
<%@ page import="java.util.List"%>
<link rel="stylesheet" href="assets/css/testcss5.css">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Post</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
      body{background-color:#f8f9fa;color:#333}
.btn-check{display:none}
.btn-check + .btn{cursor:pointer;margin-bottom:10px}
.btn-check:checked + .btn{background-color:#007bff;color:#fff}
.form-group{margin-bottom:20px}
.container{background-color:#fff;padding:20px;border-radius:8px;box-shadow:0 0 10px rgba(0,0,0,0.1)}
.btn-primary{background-color:#007bff;border-color:#007bff}
.btn-primary:hover{background-color:#0056b3;border-color:#004085}
.custom-file-input{cursor:pointer}
.custom-file-label{overflow:hidden;white-space:nowrap;text-overflow:ellipsis}
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
    <c:if test="${message != null}">
        <p>${message}</p>
    </c:if>
    <div class="container mt-4">
        <h2>Edit Post</h2>
        <form id="editPostForm" action="EditPost" method="post" enctype="multipart/form-data" onsubmit="return validateForm();">
            <span id="username" style="font-weight: bold; color: #333;"> UserName: ${user.username} </span>
            <input type="hidden" id="postId" name="postId" value="${post.postID}">
            <div class="form-group">
                <label for="title">Title:</label>
                <input type="text" class="form-control" id="title" name="title" value="${post != null ? post.title : ''}" placeholder="Enter the title" required>
            </div>
            <div class="form-group">
                <label>Categories:</label><br>
                <c:forEach var="category" items="${categories}">
                    <input type="radio" class="btn-check" id="category-${category.categoryId}" name="category" value="${category.categoryId}"
                           ${post != null && post.categoryName == category.name ? 'checked' : ''}>
                    <label class="btn btn-outline-primary" for="category-${category.categoryId}">${category.name}</label>
                </c:forEach>
            </div>
            <div class="form-group">
                <label>Genres:</label><br>
                <c:forEach var="genre" items="${genres}">
                    <input type="radio" class="btn-check" id="genre-${genre.genreId}" name="genre" value="${genre.genreId}" 
                           ${post != null && post.genreName == genre.name ? 'checked' : ''}>
                    <label class="btn btn-outline-primary" for="genre-${genre.genreId}">${genre.name}</label>
                </c:forEach>
            </div>
            <div class="form-group">
                <label for="source">Source:</label>
                <input type="text" class="form-control" id="source" name="source" value="${post != null ? post.source : ''}"  placeholder="Enter the source (optional)">
            </div>
            <div class="form-group">
                <label for="content">Content:</label>
                <textarea class="form-control" id="content" name="content" rows="5" placeholder="Enter the content" required>
                    ${post != null ? post.content : ''}
                </textarea>
            </div>
            <div class="form-group">
                <label for="image">Image:</label>
                <input type="hidden" id="imageDB" name="imageDB" value="${post != null ? post.image : ''}">
                <div class="custom-file">
                    <input type="file" class="custom-file-input" id="image" name="image" 
                           accept="image/png,image/jpeg,image/jpg,image/bmp,image/gif,image/webp" ${post.image == null ? 'required' : ''}>
                    <label class="custom-file-label" for="image">Choose file...</label>
                    <small class="form-text text-muted">Accepted formats: PNG, JPEG, JPG, BMP, GIF, WEBP</small>
                </div>
            </div>
            <button type="submit" id="updatePostButton" class="btn btn-primary mt-4">Update Post</button>
        </form>
    </div>
</body>
<script src="assets/ckeditor/ckeditor.js"></script>

<script>
            CKEDITOR.replace('content', {
                extraPlugins: 'imagebrowser',
                removePlugins: 'image',
                filebrowserBrowseUrl: 'ckeditor/plugins/imagebrowser/browser/browser.html',
                filebrowserUploadUrl: '/imageUpCopy',
                filebrowserUploadMethod: 'form'
            });

</script>
<script type="text/javascript">
    window.onload = function () {
        // Check if the profanityDetected attribute is set
        var profanityDetected = "<%= request.getAttribute("profanityDetected") %>";
        if (profanityDetected === "true") {
            alert("Please RECONSIDER YOUR POST.");
        }
    };

</script>

