<%-- 
    Document   : ListAnimeContent
    Created on : Jun 25, 2024, 3:20:55 PM
    Author     : minht
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="dal.AnimeDAO" %>
<%@ page import="model.Anime" %>
<%@ page import="java.util.List"%>

<% AnimeDAO animeDAO = new AnimeDAO(); %>
<% List<Anime> animeList = animeDAO.getListAnime(); %>

<div class="row pb-6">
    <div class="col-lg-12">
        <div class="card mb-1 rounded-2 border-0">
            <div class="card-header">
                <h1 class="card-title text-uppercase text-center">List of Anime</h1>
            </div>
        </div>

        <c:forEach var="anime" items="<%=animeList%>">
            <div class="card mb-2 rounded-2">
                <div class="card-body">
                    <h2 class="card-title">
                        <a href="AnimeDetail?animeId=${anime.animeId}">${anime.title}</a>
                    </h2>
                    <img class="float-left posts-img img-thumbnail mr-2" src="${anime.imageAnime}" onerror="this.src='assets/images/others/product-3.jpg'">
                    <p class="card-text">${anime.synopsis}</p>
                    <p class="card-text"><strong>Thể loại:</strong> ${anime.genre}</p>
                    <p class="card-text"><strong>Số tập:</strong> ${anime.episodes}</p>
                    <p class="card-text"><strong>Trạng thái:</strong> ${anime.status}</p>
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