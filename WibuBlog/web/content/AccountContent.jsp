<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="model.User" %>
<%@ page import="model.Media" %>
<%@ page import="dal.UserDAO" %>
<%@ page import="dal.MediaDAO" %>


<c:choose>
    <c:when test="${empty user}">
        <form action="login" method="get" class="form-button m-2">
            <button type="submit" class="dropdown-item d-block p-h-15 p-v-10">
                <div class="d-flex align-items-center justify-content-between">
                    <div>
                        <i class="anticon opacity-04 font-size-16 anticon-logout"></i>
                        <span class="m-l-10">Login</span>
                    </div>
                </div>
            </button>
        </form>
    </c:when>
    <c:otherwise>
        <% User user = (User)session.getAttribute("user");
           UserDAO userDAO = new UserDAO();
           String rank = userDAO.getRankByRankID(user.getRankId());
           String rankColor = userDAO.getColorByRank(rank);
           String role = userDAO.getRoleByRoleID(user.getRoleId()); 
           MediaDAO mediaDAO = new MediaDAO();
           Media media = mediaDAO.getMedia(user.getProfilePhoto());
        %>
        <li class="dropdown dropdown-animated scale-left">
            <div class="pointer" data-toggle="dropdown">
                <div class="avatar avatar-image m-h-10 m-r-15">
                    <c:choose>
                        <c:when test="<%=media != null%>">
                            <img src="<%=media.getPath()%>" alt="">
                        </c:when>
                        <c:otherwise>
                            <img src="" alt="">
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="p-b-15 p-t-20 dropdown-menu pop-profile">
                <div class="p-h-20 p-b-15 m-b-10 border-bottom">
                    <div class="d-flex m-r-50">
                        <div class="avatar avatar-lg avatar-image">
                            <c:choose>
                                <c:when test="<%=media != null%>">
                                    <img src="<%=media.getPath()%>" alt="">
                                </c:when>
                                <c:otherwise>
                                    <img src="" alt="">
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="m-l-10">
                            <p class="m-b-0 text-dark font-weight-semibold">${user.username}</p>
                            <p class="m-b-0 opacity-07" style="color:<%=rankColor%>"><%=rank%></p>
                        </div>
                    </div>
                </div>
                <a href="#" class="dropdown-item d-block p-h-15 p-v-10" onclick="document.getElementById('profileForm').submit();">
                    <div class="d-flex align-items-center justify-content-between">
                        <div>
                            <i class="anticon opacity-04 font-size-16 anticon-user"></i>
                            <span class="m-l-10">My Profile</span>
                        </div>
                        <i class="anticon font-size-10 anticon-right"></i>
                    </div>
                </a>
                <form id="profileForm" action="profile" method="post" class="d-none">
                    <input type="hidden" name="userId" value="<c:out value='${user.userId}' />">
                </form>
                <a href="logout" class="dropdown-item d-block p-h-15 p-v-10">
                    <div class="d-flex align-items-center justify-content-between">
                        <div>
                            <i class="anticon opacity-04 font-size-16 anticon-logout"></i>
                            <span class="m-l-10">Logout</span>
                        </div>
                        <i class="anticon font-size-10 anticon-right"></i>
                    </div>
                </a>
                <a href="javascript:void(0);" class="dropdown-item d-block p-h-15 p-v-10">
                    <div class="d-flex align-items-center justify-content-between">
                        <div>
                            <i class="anticon opacity-04 font-size-16 anticon-user"></i>
                            <span class="m-l-10">Edit Profile</span>
                        </div>
                        <i class="anticon font-size-10 anticon-right"></i>
                    </div>
                </a>
                <a href="javascript:void(0);" class="dropdown-item d-block p-h-15 p-v-10">
                    <div class="d-flex align-items-center justify-content-between">
                        <div>
                            <i class="anticon opacity-04 font-size-16 anticon-lock"></i>
                            <span class="m-l-10">Account Setting</span>
                        </div>
                        <i class="anticon font-size-10 anticon-right"></i>
                    </div>
                </a>
                <a href="javascript:void(0);" class="dropdown-item d-block p-h-15 p-v-10">
                    <div class="d-flex align-items-center justify-content-between">
                        <div>
                            <i class="anticon opacity-04 font-size-16 anticon-project"></i>
                            <span class="m-l-10">Projects</span>
                        </div>
                        <i class="anticon font-size-10 anticon-right"></i>
                    </div>
                </a>
            </div>
        </li>
    </c:otherwise>
</c:choose>
