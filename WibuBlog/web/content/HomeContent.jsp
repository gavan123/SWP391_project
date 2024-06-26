<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
<jsp:useBean id="p" scope="request" class="dal.PostDAO" />


<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">      
            <label for="image">What are you thinking ?</label>
            <a href="createPost">Create a post</a>
            <!--            <form name="imageUpCopy" action="imageUpCopy" method="POST" enctype="multipart/form-data" class="border p-4">
                            <div class="form-group">
                                <label for="image">Choose Images:</label>
                                <input type="file" class="form-control-file" name="image" id="image" accept="image/png,image/jpeg,image/jpg,image/bmp,image/gif" multiple>
                            </div>
                            <button type="submit" class="btn btn-primary">Upload Images</button>
                        </form>-->
        </div>
    </div>
</div>
<c:forEach var="fileName" items="${image}">
    <img src="${pageContext.request.contextPath}/images/game/${fileName}" alt="Uploaded Image">
</c:forEach>

<div class="card mb-2 rounded-5 border-0">
    <div class="card-header">
        <h3 class="card-header-h3 fs-16">
            Q &amp; A
            <span class="float-right text-uppercase">
                <a href="question">More</a>
            </span>
        </h3>
    </div>
    <div class="card-body">
        <ul class="list-group list-group-flush">
            <c:forEach var="post" items="${p.getLimitedPostsByCategory(5,10)}">
                <c:if test="${post.status=='active'}">
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        <div class="col-10 text-truncate font-weight-bold">
                            <a href="postDetail?postId=${post.postId}">${post.title}</a>
                        </div>
                        <span class="badge badge-primary badge-pill">
                            <i class="far fa-comment-dots fa-lg"></i>
                            <span style="font-size: larger;">${post.view}</span> 
                        </span>
                    </li> 
                </c:if>
            </c:forEach>
        </ul>
    </div>
</div>

<div class="card mb-2 rounded-3 border-0">
    <div class="card-header" id="top"><h3 class="card-header-h3 fs-16">New Post
            <span class="float-right text-uppercase">
                <a href="postList">More</a>
            </span></h3></div>
    <div class="card-body">
        <!-- Swiper slider -->
        <div class="swiper-container swiper-container-initialized swiper-container-horizontal" style="min-height:220px">
            <div class="swiper-wrapper" id="swiper-wrapper" aria-live="polite" style="transform: translate3d(-603.333px, 0px, 0px); transition-duration: 0ms;">
                <c:forEach var="post" items="${p.getLimitedPosts(10)}">
                    <c:if test="${post.status=='active'}"></c:if>
                        <div class="swiper-slide g_thumb" role="group" aria-label="1 / 12" style="width: 135.833px;  margin-right: 15px;">
                            <span class="badge badge-info posts-badge">${post.view} <i class="anticon anticon-eye"></i></span>
                        <a href="postDetail?postId=${post.postId}" id="new_post0">
                            <img class="swiper-img mx-auto d-block rounded-1" loading="lazy" src="${pageContext.request.contextPath}/images/game/${post.image}" 
                                 onerror="this.src='assets/images/others/product-3.jpg'" alt="${post.title}">
                            <span class="menu-text-sm text-center mt-2 text-truncate-2">${post.title}</span>
                        </a>
                    </div>
                </c:forEach>
            </div>
            <!-- Add Pagination -->
            <div class="swiper-pagination swiper-pagination-clickable swiper-pagination-bullets">
            </div>
            <span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span>
            <!-- End Swiper slider -->
        </div>
    </div>
</div>



<div class="card mb-2 rounded-3 border-0">
    <div class="card-header" id="top"><h3 class="card-header-h3 fs-16">Anime Reviews
            <span class="float-right text-uppercase">
                <a href="postListByCategory?categoryId=1">More</a>
            </span></h3></div>
    <div class="card-body">
        <!-- Swiper slider -->
        <div class="swiper-container swiper-container-initialized swiper-container-horizontal" style="min-height:220px">
            <div class="swiper-wrapper" id="swiper-wrapper" aria-live="polite" style="transform: translate3d(-603.333px, 0px, 0px); transition-duration: 0ms;">
                <c:forEach var="post" items="${p.getLimitedPostsByCategory(10, 1)}">
                    <c:if test="${post.status=='active'}">
                        <div class="swiper-slide g_thumb" role="group" aria-label="1 / 12" style="width: 135.833px;  margin-right: 15px;">
                            <span class="badge badge-info posts-badge">${post.view} <i class="anticon anticon-eye"></i></span>
                            <a href="postDetail?postId=${post.postId}" >
                                <img class="swiper-img mx-auto d-block rounded-1" loading="lazy" src="${pageContext.request.contextPath}/images/game/${post.image}" onerror="this.src='assets/images/others/product-3.jpg'" alt="${post.title}">
                                <span class="menu-text-sm text-center mt-2 text-truncate-2">${post.title}</span>
                            </a>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
            <!-- Add Pagination -->
            <div class="swiper-pagination swiper-pagination-clickable swiper-pagination-bullets">
            </div>
            <span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span>
            <!-- End Swiper slider -->
        </div>
    </div>
</div>

<div class="card mb-2 rounded-3 border-0">
    <div class="card-header" id="top"><h3 class="card-header-h3 fs-16">Character Analysis
            <span class="float-right text-uppercase">
                <a href="postListByCategory?categoryId=2">More</a>
            </span></h3></div>
    <div class="card-body">
        <!-- Swiper slider -->
        <div class="swiper-container swiper-container-initialized swiper-container-horizontal" style="min-height:220px">
            <div class="swiper-wrapper" id="swiper-wrapper" aria-live="polite" style="transform: translate3d(-603.333px, 0px, 0px); transition-duration: 0ms;">
                <c:forEach var="post" items="${p.getLimitedPostsByCategory(10, 2)}">
                    <c:if test="${post.status=='active'}">
                        <div class="swiper-slide g_thumb" role="group" aria-label="1 / 12" style="width: 135.833px;  margin-right: 15px;">
                            <span class="badge badge-info posts-badge">${post.view} <i class="anticon anticon-eye"></i></span>
                            <a href="postDetail?postId=${post.postId}" id="new_post0">
                                <img class="swiper-img mx-auto d-block rounded-1" loading="lazy" src="${pageContext.request.contextPath}/images/game/${post.image}" onerror="this.src='assets/images/others/product-3.jpg'" alt="${post.title}">
                                <span class="menu-text-sm text-center mt-2 text-truncate-2">${post.title}</span>
                            </a>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
            <!-- Add Pagination -->
            <div class="swiper-pagination swiper-pagination-clickable swiper-pagination-bullets">
            </div>
            <span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span>
            <!-- End Swiper slider -->
        </div>
    </div>
</div>

<div class="card mb-2 rounded-3 border-0">
    <div class="card-header" id="top"><h3 class="card-header-h3 fs-16">Plot Analysis 
            <span class="float-right text-uppercase">
                <a href="postListByCategory?categoryId=3">More</a>
            </span></h3></div>
    <div class="card-body">
        <!-- Swiper slider -->
        <div class="swiper-container swiper-container-initialized swiper-container-horizontal" style="min-height:220px">
            <div class="swiper-wrapper" id="swiper-wrapper" aria-live="polite" style="transform: translate3d(-603.333px, 0px, 0px); transition-duration: 0ms;">
                <c:forEach var="post" items="${p.getLimitedPostsByCategory(10, 3)}">
                    <c:if test="${post.status=='active'}">
                        <div class="swiper-slide g_thumb" role="group" aria-label="1 / 12" style="width: 135.833px;  margin-right: 15px;">
                            <span class="badge badge-info posts-badge">${post.view} <i class="anticon anticon-eye"></i></span>
    <!--                        <span class="badge badge-danger posts-badge-2 text-truncate">${post.status}</span>-->
                            <a href="postDetail?postId=${post.postId}">
                                <img class="swiper-img mx-auto d-block rounded-1" loading="lazy" src="${pageContext.request.contextPath}/images/game/${post.image}" onerror="this.src='assets/images/others/product-3.jpg'" alt="${post.title}">
                                <span class="menu-text-sm text-center mt-2 text-truncate-2">${post.title}</span>
                            </a>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
            <!-- Add Pagination -->
            <div class="swiper-pagination swiper-pagination-clickable swiper-pagination-bullets">
            </div>
            <span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span>
            <!-- End Swiper slider -->
        </div>
    </div>
</div>

<div class="card mb-2 rounded-3 border-0">
    <div class="card-header" id="top"><h3 class="card-header-h3 fs-16">Interviews
            <span class="float-right text-uppercase">
                <a href="postListByCategory?categoryId=8">More</a>
            </span></h3></div>
    <div class="card-body">
        <!-- Swiper slider -->
        <div class="swiper-container swiper-container-initialized swiper-container-horizontal" style="min-height:220px">
            <div class="swiper-wrapper" id="swiper-wrapper" aria-live="polite" style="transform: translate3d(-603.333px, 0px, 0px); transition-duration: 0ms;">
                <c:forEach var="post" items="${p.getLimitedPostsByCategory(10, 8)}">
                    <c:if test="${post.status=='active'}">
                        <div class="swiper-slide g_thumb" role="group" aria-label="1 / 12" style="width: 135.833px;  margin-right: 15px;">
                            <span class="badge badge-info posts-badge">${post.view} <i class="anticon anticon-eye"></i></span>
                            <!--<span class="badge badge-danger posts-badge-2 text-truncate">${post.status}</span>-->
                            <a href="postDetail?postId=${post.postId}">
                                <img class="swiper-img mx-auto d-block rounded-1" loading="lazy" src="${pageContext.request.contextPath}/images/game/${post.image}" onerror="this.src='assets/images/others/product-3.jpg'" alt="${post.title}">
                                <span class="menu-text-sm text-center mt-2 text-truncate-2">${post.title}</span>
                            </a>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
            <!-- Add Pagination -->
            <div class="swiper-pagination swiper-pagination-clickable swiper-pagination-bullets">
            </div>
            <span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span>
            <!-- End Swiper slider -->
        </div>
    </div>
</div>
