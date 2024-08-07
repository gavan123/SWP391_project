<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="c" scope="request" class="dal.CategoryDAO" />
<style>
    .side-nav{
        background-color: #5ce0e6;
    }
</style>
<div class="side-nav">
    <div class="side-nav-inner">
        <ul class="side-nav-menu scrollable">
            <c:if test="${user != null && user.roleId == 1}">
                <li class="nav-item dropdown">
                    <a class="dropdown-toggle" href="DashBoard">
                        <span class="icon-holder">
                            <i class="anticon anticon-dashboard"></i>
                        </span>
                        <span class="title">Dashboard</span>
                    </a>
                </li>
                <li class="nav-item dropdown">
                    <a class="dropdown-toggle" href="ListReport">
                        <span class="icon-holder">
                            <i class="anticon anticon-alert"></i>
                        </span>
                        <span class="title">Report Management</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${user != null && user.roleId == 2}">
                <li class="nav-item dropdown">
                    <a class="dropdown-toggle" href="ListReport">
                        <span class="icon-holder">
                            <i class="anticon anticon-dashboard"></i>
                        </span>
                        <span class="title">Report Management</span>
                    </a>
                </li>
            </c:if>
            <li class="nav-item dropdown">
                <a class="dropdown-toggle" href="javascript:void(0);">
                    <span class="icon-holder">
                        <i class="anticon anticon-appstore"></i>
                    </span>
                    <span class="title">Catalog</span>
                    <span class="arrow">
                        <i class="arrow-icon"></i>
                    </span>
                </a>
                <ul class="dropdown-menu">
                    <c:forEach var="category" items="${c.allCategories}">
                        <li>
                            <c:choose>
                                <c:when test="${category.categoryId == 10}">
                                    <a href="question">${category.name}</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="postListByCategory?categoryId=${category.categoryId}">${category.name}</a>
                                </c:otherwise>
                            </c:choose>
                        </li>
                    </c:forEach>
                </ul>
            </li>
            
<!--            <li class="nav-item dropdown">
                <a class="dropdown-toggle" href="ListAnime">
                    <span class="icon-holder">
                       <i class="anticon anticon-book"></i>
                    </span>
                    <span class="title">List Anime</span>
                   
                </a>
            </li>
            -->
            
            <c:if test="${user != null && user.roleId ==2}">
                <li class="nav-item dropdown">
                    <a class="dropdown-toggle" href="javascript:void(0);">
                        <span class="icon-holder">
                            <i class="anticon anticon-build"></i>
                        </span>
                        <span class="title">Mod</span>
                        <span class="arrow">
                            <i class="arrow-icon"></i>
                        </span>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="checkReportedPosts">Report Post</a>
                        </li>
                    </ul>
                </li>
            </c:if>


            <!--            <li class="nav-item dropdown">
                            <a class="dropdown-toggle" href="javascript:void(0);">
                                <span class="icon-holder">
                                    <i class="anticon anticon-lock"></i>
                                </span>
                                <span class="title">Authentication</span>
                                <span class="arrow">
                                    <i class="arrow-icon"></i>
                                </span>
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="">Login 1</a>
                                </li>
                                <li>
                                    <a href="">Login 2</a>
                                </li>
                            </ul>
                        </li>-->
        </ul>
    </div>
</div>
