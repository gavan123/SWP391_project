<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
<jsp:useBean id="p" scope="request" class="dal.PostDAO" />
<link rel="stylesheet" href="assets/css/testcss6.css">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
<%@ page import="model.User" %>
<%@ page import="model.Post" %>
<%@ page import="dal.UserDAO" %>
<%@ page import="dal.GenreDAO" %>
<%@ page import="model.TopViewedGenre" %>
<%@ page import="dal.PostDAO" %>

<%                             User user = (User)session.getAttribute("user");
                               UserDAO ud = new UserDAO();   
                               PostDAO pd = new PostDAO();
%>
 <%if (user != null && ud.getUserStatusByUserId(user.getUserId()).equals("active")){%>  
<div class="thinking-card">    
    <label for="image">What's on your mind?</label>
    <a href="createPost">Create a post</a>
</div>
    <%}%>
 <%if (user != null && ud.getUserStatusByUserId(user.getUserId()).equals("deactive")){%>  
<div class="thinking-card">   
    <label for="image">You are currently banned please wait for your ban to expire to post</label>
</div>
    <%}%>   

<div class="m-t-30">
    <div class="table-responsive">
        <table class="table table-hover" style="border: 1px solid black">
            <thead>
                <tr>
                    <th>Rank</th>
                    <th>Member</th>
                    <th>Points</th>
                    <th>Rank</th>
                    <th>Role</th>
                    <th style="max-width: 70px">Posts Last 3 Days</th>
                </tr>
            </thead>
            <tbody>
               <%
                   int rank = 0;
                   for(User x : pd.getTop10UserByPoint()){%>                                
                <tr>
                    <td>#<%=++rank%></td>
                    <td>
                        <div class="media align-items-center">
                            <div class="avatar avatar-image rounded">
                                <img src="${pageContext.request.contextPath}/images/avatar/<%=x.getProfilePhoto()%>"  onerror="this.src='assets/images/others/product-3.jpg'" alt="${topUser.username}">
                            </div>
                            <div class="m-l-10">
                                <span><%=x.getUsername()%></span>
                            </div>
                        </div>
                    </td>
                    <td><%=x.getPoint()%></td>

                    <td style="color:<%=ud.getColorByRank(ud.getRankByRankID(x.getRankId()))%>"><%=ud.getRankByRankID(x.getRankId())%></td>
                    <td><%=ud.getRoleByRoleID(x.getRoleId())%></td>
                    <td>
                        <div class="d-flex align-items-center">

                            <div class="m-l-10">
                                <%=ud.getUserTotalPostLast3Days(x.getUserId())%>
                            </div>
                        </div>
                    </td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </div>
</div>
            
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
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <div class="col-10 text-truncate font-weight-bold">
                        <a href="postDetail?postId=${post.postId}">${post.title}</a>
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
                <a href="postList">More</a>
            </span></h3></div>
    <div class="card-body">
        <!-- Swiper slider -->
        <div class="swiper-container swiper-container-initialized swiper-container-horizontal" style="min-height:220px">
            <div class="swiper-wrapper" id="swiper-wrapper" aria-live="polite" style="transform: translate3d(-603.333px, 0px, 0px); transition-duration: 0ms;">
                <c:forEach var="post" items="${p.getLimitedPosts(10)}">
                    <div class="swiper-slide g_thumb" role="group" aria-label="1 / 12" style="width: 135.833px;  margin-right: 15px;">
                        <span class="badge badge-info posts-badge">${post.view} <i class="anticon anticon-eye"></i></span>
                        <a href="postDetail?postId=${post.postId}" id="new_post0">
                            <img class="swiper-img mx-auto d-block rounded-1" loading="lazy" 
                                 src="${pageContext.request.contextPath}/images/post/${post.image}" 
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
    <div class="card-header" id="top"><h3 class="card-header-h3 fs-16">Hot Post
            <span class="float-right text-uppercase">
                <a href="postList">More</a>
            </span></h3></div>
    <div class="card-body">
        <!-- Swiper slider -->
        <div class="swiper-container swiper-container-initialized swiper-container-horizontal" style="min-height:220px">
            <div class="swiper-wrapper" id="swiper-wrapper" aria-live="polite" style="transform: translate3d(-603.333px, 0px, 0px); transition-duration: 0ms;">
              <% for(Post x: pd.getTop6VotedPost()){%>
                    <div class="swiper-slide g_thumb" role="group" aria-label="1 / 12" style="width: 135.833px;  margin-right: 15px;">
                        <span class="badge badge-info posts-badge"><%=x.getView()%> <i class="anticon anticon-eye"></i></span>
                        <a href="postDetail?postId=<%=x.getPostId()%>" id="new_post0">
                            <img class="swiper-img mx-auto d-block rounded-1" loading="lazy" 
                                 src="${pageContext.request.contextPath}/images/post/<%=x.getImage()%>" 
                                 onerror="this.src='assets/images/others/product-3.jpg'" alt="<%=x.getTitle()%>">
                            <span class="menu-text-sm text-center mt-2 text-truncate-2"><%=x.getTitle()%></span>
                        </a>
                    </div>
                <%}%>
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
                    <div class="swiper-slide g_thumb" role="group" aria-label="1 / 12" style="width: 135.833px;  margin-right: 15px;">
                        <span class="badge badge-info posts-badge">${post.view} <i class="anticon anticon-eye"></i></span>
                        <a href="postDetail?postId=${post.postId}" >
                            <img class="swiper-img mx-auto d-block rounded-1" loading="lazy" src="${pageContext.request.contextPath}/images/post/${post.image}" onerror="this.src='assets/images/others/product-3.jpg'" alt="${post.title}">
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
    <div class="card-header" id="top"><h3 class="card-header-h3 fs-16">Character Analysis
            <span class="float-right text-uppercase">
                <a href="postListByCategory?categoryId=2">More</a>
            </span></h3></div>
    <div class="card-body">
        <!-- Swiper slider -->
        <div class="swiper-container swiper-container-initialized swiper-container-horizontal" style="min-height:220px">
            <div class="swiper-wrapper" id="swiper-wrapper" aria-live="polite" style="transform: translate3d(-603.333px, 0px, 0px); transition-duration: 0ms;">
                <c:forEach var="post" items="${p.getLimitedPostsByCategory(10, 2)}">
                    <div class="swiper-slide g_thumb" role="group" aria-label="1 / 12" style="width: 135.833px;  margin-right: 15px;">
                        <span class="badge badge-info posts-badge">${post.view} <i class="anticon anticon-eye"></i></span>
                        <a href="postDetail?postId=${post.postId}" id="new_post0">
                            <img class="swiper-img mx-auto d-block rounded-1" loading="lazy" src="${pageContext.request.contextPath}/images/post/${post.image}" onerror="this.src='assets/images/others/product-3.jpg'" alt="${post.title}">
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
    <div class="card-header" id="top"><h3 class="card-header-h3 fs-16">Plot Analysis 
            <span class="float-right text-uppercase">
                <a href="postListByCategory?categoryId=3">More</a>
            </span></h3></div>
    <div class="card-body">
        <!-- Swiper slider -->
        <div class="swiper-container swiper-container-initialized swiper-container-horizontal" style="min-height:220px">
            <div class="swiper-wrapper" id="swiper-wrapper" aria-live="polite" style="transform: translate3d(-603.333px, 0px, 0px); transition-duration: 0ms;">
                <c:forEach var="post" items="${p.getLimitedPostsByCategory(10, 3)}">
                    <div class="swiper-slide g_thumb" role="group" aria-label="1 / 12" style="width: 135.833px;  margin-right: 15px;">
                        <span class="badge badge-info posts-badge">${post.view} <i class="anticon anticon-eye"></i></span>
<!--                        <span class="badge badge-danger posts-badge-2 text-truncate">${post.status}</span>-->
                        <a href="postDetail?postId=${post.postId}">
                            <img class="swiper-img mx-auto d-block rounded-1" loading="lazy" src="${pageContext.request.contextPath}/images/post/${post.image}" onerror="this.src='assets/images/others/product-3.jpg'" alt="${post.title}">
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
    <div class="card-header" id="top"><h3 class="card-header-h3 fs-16">Interviews
            <span class="float-right text-uppercase">
                <a href="postListByCategory?categoryId=8">More</a>
            </span></h3></div>
    <div class="card-body">
        <!-- Swiper slider -->
        <div class="swiper-container swiper-container-initialized swiper-container-horizontal" style="min-height:220px">
            <div class="swiper-wrapper" id="swiper-wrapper" aria-live="polite" style="transform: translate3d(-603.333px, 0px, 0px); transition-duration: 0ms;">
                <c:forEach var="post" items="${p.getLimitedPostsByCategory(10, 8)}">
                    <div class="swiper-slide g_thumb" role="group" aria-label="1 / 12" style="width: 135.833px;  margin-right: 15px;">
                        <span class="badge badge-info posts-badge">${post.view} <i class="anticon anticon-eye"></i></span>
                        <!--<span class="badge badge-danger posts-badge-2 text-truncate">${post.status}</span>-->
                        <a href="postDetail?postId=${post.postId}">
                            <img class="swiper-img mx-auto d-block rounded-1" loading="lazy" 
                                 src="${pageContext.request.contextPath}/images/post/${post.image}" 
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


