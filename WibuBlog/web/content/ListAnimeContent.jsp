<%-- 
    Document   : ListAnimeContent
    Created on : Jun 25, 2024, 3:20:55 PM
    Author     : minht
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="dal.AnimeDAO" %>
<%@ page import="model.Anime" %>
<%@ page import="java.util.List" %>

<% AnimeDAO animeDAO = new AnimeDAO(); %>
<% List<Anime> animeList = animeDAO.getListAnime(); %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List of Anime</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <style>
        .card {
            margin-bottom: 1rem;
            border-radius: 8px;
        }
        .card-body {
            display: flex;
            align-items: center;
        }
        .card-body img {
            max-width: 100px;
            margin-right: 1rem;
        }
        .card-body h2 {
            font-size: 1.25rem;
        }
        .card-body p {
            margin: 0.5rem 0;
        }
        .card-title a {
            text-decoration: none;
            color: #007bff;
        }
        .card-title a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="container mt-4">
    <div class="row">
        <div class="col-lg-12">
            <div class="card mb-1 rounded-2 border-0">
                <div class="card-header">
                    <h1 class="card-title text-uppercase text-center">List of Anime</h1>
                    <%-- Chỉ hiển thị nút Create Anime cho Admin và Mod --%>
                    <c:if test="${sessionScope.isAdmin or sessionScope.isMod}">
                        <div class="text-right mb-2">
                            <a href="CreateAnime.jsp" class="btn btn-success">Create Anime</a>
                        </div>
                    </c:if>
                </div>
            </div>

            <c:forEach var="anime" items="${animeList}">
                <div class="card">
                    <div class="card-body">
                        <img class="img-thumbnail" 
                             src="${pageContext.request.contextPath}/images/anime/${anime.imageAnime}" 
                             onerror="this.src='assets/images/others/product-3.jpg'">
                        <div>
                            <h2 class="card-title">
                                <a href="AnimeDetail?animeId=${anime.animeId}">${anime.title}</a>
                            </h2>
                            <p class="card-text">${anime.synopsis}</p>
                            <p class="card-text"><strong>Thể loại:</strong> ${anime.genre}</p>
                            <p class="card-text"><strong>Số tập:</strong> ${anime.episodes}</p>
                            <p class="card-text"><strong>Trạng thái:</strong> ${anime.status}</p>
                        </div>
                        <div class="ml-auto">
                            <%-- Chỉ hiển thị nút Update và Delete cho Admin và Mod --%>
                            <c:if test="${sessionScope.isAdmin or sessionScope.isMod}">
                                <!-- Nút Update Anime -->
                                <a href="UpdateAnime?animeId=${anime.animeId}" class="btn btn-primary mr-2">Update</a>
                                <!-- Nút Delete Anime -->
                                <a href="DeleteAnime?animeId=${anime.animeId}" class="btn btn-danger">Delete</a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <!-- Pagination -->
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <c:if test="${currentPage > 1}">
                        <li class="page-item">
                            <a class="page-link" href="?page=1">First</a>
                        </li>
                        <li class="page-item">
                            <a class="page-link" href="?page=${currentPage - 1}">Previous</a>
                        </li>
                    </c:if>

                    <!-- Current page and surrounding pages -->
                    <c:if test="${currentPage > 1}">
                        <li class="page-item">
                            <a class="page-link" href="?page=${currentPage - 2}">${currentPage - 2}</a>
                        </li>
                    </c:if>

                    <li class="page-item active">
                        <a class="page-link" href="?page=${currentPage}">${currentPage}</a>
                    </li>

                    <c:if test="${currentPage < totalPages}">
                        <li class="page-item">
                            <a class="page-link" href="?page=${currentPage + 2}">${currentPage + 2}</a>
                        </li>
                    </c:if>

                    <c:if test="${currentPage < totalPages}">
                        <li class="page-item">
                            <a class="page-link" href="?page=${currentPage + 1}">Next</a>
                        </li>
                        <li class="page-item">
                            <a class="page-link" href="?page=${totalPages}">Last</a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </div>
</div>

</body>
</html>
