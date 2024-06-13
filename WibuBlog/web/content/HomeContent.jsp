<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
<jsp:useBean id="p" scope="request" class="dal.PostDAO" />

<c:choose>
    <c:when test="${empty user}">
        <form action="login" method="get" class="form-button m-2">
            <button type="submit" class="btn btn-primary">Login</button>
        </form>
    </c:when>
    <c:otherwise>
        <h2>Welcome, <c:out value="${user.username}" />!</h2>
        <p>Id: <c:out value="${user.userId}" /></p>
        <p>Full Name: <c:out value="${user.fullName}" /></p>
        <p>Point: <c:out value="${user.point}" /></p>
        <p>Phone: <c:out value="${user.phoneNumber}" /></p>
        <form action="profile" method="post" class="form-button m-2">
            <input type="hidden" name="userId" value="<c:out value='${user.userId}' />">
            <button type="submit" class="btn btn-primary">My Profile</button>
        </form>
        <form action="changePassword" method="get" class="form-button m-2">
            <button type="submit" class="btn btn-primary">Change Password</button>
        </form>
        <form id="deleteAccountForm" action="deleteAccount" method="post" class="form-button m-2">
            <button type="button" class="btn btn-primary" onclick="confirmDelete()">Delete Account</button>
        </form>
        <form action="logout" method="get" class="form-button m-2">
            <button type="submit" class="btn btn-primary">Logout</button>
        </form>
    </c:otherwise>
</c:choose>

<div class="card mb-2 rounded-5 border-0">
    <div class="card-header">
        <h3 class="card-header-h3 fs-16">
            Q &amp; A
            <span class="float-right text-uppercase">
                <a href="question">Xem thêm</a>
            </span>
        </h3>
    </div>
    <div class="card-body">
        <ul class="list-group list-group-flush">
            <c:forEach var="post" items="${p.getLimitedPostsByCategory(5,10)}">
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <div class="col-10 text-truncate font-weight-bold">
                        <a href="${post.source}">${post.content}</a>
                    </div>
                    <span class="badge badge-primary badge-pill">
                        <i class="far fa-comment-dots fa-lg"></i>
                        <span style="font-size: larger;">${post.view}</span> 
                    </span>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

<div class="card mb-2 rounded-3 border-0">
    <div class="card-header" id="top"><h3 class="card-header-h3 fs-16">New Post
            <span class="float-right text-uppercase">
                <a href="postList">Xem thêm</a>
            </span></h3></div>
    <div class="card-body">
        <!-- Swiper slider -->
        <div class="swiper-container swiper-container-initialized swiper-container-horizontal" style="min-height:220px">
            <div class="swiper-wrapper" id="swiper-wrapper" aria-live="polite" style="transform: translate3d(-603.333px, 0px, 0px); transition-duration: 0ms;">
                <c:forEach var="post" items="${p.getLimitedPosts(10)}">
                    <div class="swiper-slide g_thumb" role="group" aria-label="1 / 12" style="width: 135.833px;  margin-right: 15px;">
                        <span class="badge badge-info posts-badge">${post.view} <i class="anticon anticon-eye"></i></span>
                        <span class="badge badge-danger posts-badge-2 text-truncate">${post.status}</span>
                        <a href="${post.source}" id="new_post0">
                            <img class="swiper-img mx-auto d-block rounded-1" loading="lazy" src="${post.image}" onerror="this.src='assets/images/others/product-3.jpg'" alt="${post.content}">
                            <span class="menu-text-sm text-center mt-2 text-truncate-2">${post.content}</span>
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
<script>
    function confirmDelete() {
        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.isConfirmed) {
                document.getElementById('deleteAccountForm').submit();
            }
        })
    }
</script>