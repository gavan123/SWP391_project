<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="p" scope="request" class="dal.PostDAO" />
<jsp:useBean id="c" scope="request" class="dal.CategoryDAO" />

<div class="row pb-6">
    <div class="col-lg-12">
        <div class="card mb-1 rounded-2 border-0">
            <div class="card-header">
                <h1 class="card-title text-uppercase text-center">
                    <c:choose>
                        <c:when test="${category != null}">
                            ${category.name}
                        </c:when>
                        <c:otherwise>
                            Category Not Found
                        </c:otherwise>
                    </c:choose>
                </h1>
            </div>
        </div>
        <!-- Start forEach loop to iterate through posts -->
        <c:forEach var="post" items="${p.getAllPostsByCategory(category.categoryId)}">
            <c:if test="${post.status=='active'}">
                <div class="card mb-2 rounded-2">
                    <div class="card-body">
                        <h2 class="card-title">
                            <a href="postDetail?postId=${post.postId}" />${post.title}</a>
                        </h2>
                        <img class="float-left posts-img img-thumbnail mr-2" 
                             src="${pageContext.request.contextPath}/images/post/${post.image}" onerror="this.src='assets/images/others/product-3.jpg'">
                        <p class="card-text">${post.content}</p>
                    </div>
                </div>
            </c:if>
        </c:forEach>
        <!-- End forEach loop -->


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
