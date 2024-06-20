<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="p" scope="request" class="dal.PostDAO" />

<div class="row pb-6">
    <div class="col-lg-12">

        <div class="card mb-1 rounded-2 border-0">
            <div class="card-header">
                <h1 class="card-title text-uppercase text-center">MỤC Q&amp;A</h1>
            </div>
        </div>

        <!-- Start forEach loop to iterate through posts -->
        <c:forEach var="post" items="${p.getAllPostsByCategory(10)}">
            <div class="card mb-2 rounded-2" style="border-color:aqua;border-style:double;border-width:medium">
                <div class="card-body">
                    <ul class="list-group">
                        <li class="list-group-item d-flex justify-content-between align-items-center" style="background:none;border:none">
                            <div class="col-10 text-truncate font-weight-bold">
                                <a href="postDetail?postId=${post.postId}">${post.title}</a>
                            </div>
                            <!-- Display the comment count -->
                            <span class="badge badge-primary badge-pill">
                                <i class="far fa-comment-dots fa-lg"></i> ${post.commentCount}
                            </span>
                        </li>
                    </ul>
                </div>
            </div>
        </c:forEach>
        <!-- End forEach loop -->

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
