

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Anime" %>
<%@ page import="model.Post" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Kết quả tìm kiếm</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">

        <form action="SearchServlet" method="GET">
            <div class="form-group">
                <label for="keyword">Keyword:</label>
                <input type="text" class="form-control" id="keyword" name="keyword" placeholder="Enter keyword">
            </div>
            <div class="form-group">
                <label for="searchType">Search Type:</label>
                <select class="form-control" id="searchType" name="searchType">
                    <option value="anime">Anime</option>
                    <option value="post">Post</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Search</button>
        </form>

        <h2>Kết quả tìm kiếm</h2>

        <%-- Hiển thị kết quả Anime --%>
        <h3>Anime</h3>
        <ul>
            <% 
            List<Anime> animeList = (List<Anime>) request.getAttribute("animeList");
            if (animeList != null) {
                for (Anime anime : animeList) { %>
                    <li>Title: <%= anime.getTitle() %></li>
                    <li>Genre: <%= anime.getGenre() %></li>
                    <li>Episodes: <%= anime.getEpisodes() %></li>
                    <li>Release Date: <%= anime.getReleaseDate() %></li>
                    <li>Studio: <%= anime.getStudio() %></li>
                    <li><img src="<%= anime.getImageAnime() %>" alt="<%= anime.getTitle() %>"></li>
                    <hr>
                <% }
            } %>
        </ul>

        <%-- Hiển thị kết quả Post --%>
        <h3>Post</h3>
        <ul>
            <% 
            List<Post> postList = (List<Post>) request.getAttribute("postList");
            if (postList != null) {
                for (Post post : postList) { %>
                    <li>Title: <%= post.getTitle() %></li>
                    <li>Content: <%= post.getContent() %></li>
                    <li>Source: <%= post.getSource() %></li>
                    <li><img src="<%= post.getImageUrl() %>" alt="<%= post.getTitle() %>"></li>
                    <hr>
                <% }
            } %>
        </ul>
    </div>
</body>
</html>
