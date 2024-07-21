<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ page import="dal.UserDAO" %>
<%@ page import="dal.PostDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.PostDetail" %>
<link rel="stylesheet" href="assets/css/testcss.css">
<link rel="stylesheet" href="assets/css/testcss2.css">

<div class="container">
    <div class="card">
        <div class="card-body">
            <div class="row align-items-center">
                <div class="col-md-7">
                    <div class="d-md-flex align-items-center">
                        <div class="text-center text-sm-left ">
                            <div class="test avatar avatar-image" style="width: 150px; height: 100px;border: 10px solid ${rank.color};">  
                                <img src="${pageContext.request.contextPath}/images/avatar/${member.profilePhoto}" style="width: 150px; height: 100px">
                            </div>
                        </div>                                        
                        <div class="text-center text-sm-left m-v-15 p-l-30">
                            <h2 class="m-b-5">${member.username}</h2>
                            <i class="text-opacity font-size-15">${rank.name}</b></i>
                            <p class="text-dark m-0">Point: ${member.point}</p>
                            <p class="text-dark ">${role}</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-5">
                    <div class="row">
                        <div class="d-md-block d-none border-left col-1"></div>
                        <div class="col">
                            <ul class="list-unstyled m-t-10">
                                <li class="row">
                                    <p class="col-sm-4 col-4 font-weight-semibold text-dark m-b-5">
                                        <i class="m-r-10 text-primary anticon anticon-mail"></i>
                                        <span>Email: </span> 
                                    </p>
                                    <p class="col font-weight-semibold">${member.email}</p>
                                </li>
                                <li class="row">
                                    <p class="col-sm-4 col-4 font-weight-semibold text-dark m-b-5">
                                        <i class="m-r-10 text-primary anticon anticon-phone"></i>
                                        <span>Phone: </span> 
                                    </p>    
                                    <p class="col font-weight-semibold">${member.phoneNumber}</p>
                                </li>

                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-8">
            <div class="card">
                <div class="card-body">
                    <h5>Bio</h5>     

                    <div class="w3-container w3-pale-blue w3-leftbar w3-border-blue">
                        <p>${member.bio}</p>
                        <hr style="color: aqua">
                    </div>
                    <br>
                    <h5>Posts</h5>
                    <div class="post_container">
                        <hr>
                        <c:forEach items="${userPostList}" var="post">
                            <div class="card mb-2" onclick="redirectToLink('${pageContext.request.contextPath}/postDetail?postId=${post.postID}')">
                                <div class="card-body p-0">
                                    <header>
                                        <h1 class="card-title" style="font-size: 26px;line-height:34px">${post.title}</h1>
                                    </header>
                                    <h6 class="card-subtitle mb-2 fw-700" style="font-size: small !important;">
                                        <i class="fas fa-user"></i> 
                                        <a href="#" style="color:blue" accesskey="a">${post.username}</a> | 
                                        <i class="fas fa-clock"></i>
                                        <time datetime="${post.postTime}">${post.postTime}</time> | 
                                        <i class="fas fa-eye"></i> ${post.view}
                                    </h6>
                                    <h3 class="fs-14 border-bottom-badge-eee">
                                        <span class="badge badge-info mr-1">
                                            <a class="text-white" href="postListByCategory?name=${post.categoryName}">${post.categoryName}</a>
                                        </span>
                                        <span class="badge badge-primary mr-1">
                                            <a class="text-white" href="#">${post.genreName}</a>
                                        </span>
                                    </h3>
                                    <hr>
                                    <div class="row">
                                        <div class="col-lg-4 mb-2 mx-auto">
                                            <ul class="list-unstyled m-0 d-flex flex-wrap justify-content-center">
                                                <li class="d-flex align-items-center mr-4 font-weight-bold">
                                                    <div class="vote-section" id="vote-section">
                                                        <span id="vote_value">Vote: ${post.vote}</span>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <hr>

                </div>
            </div>

        </div>
    </div>
</div>

<script>
    function redirectToLink(url) {
        window.location.href = url;
    }
</script>


