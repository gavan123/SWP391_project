
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Notification"%>
<%@ page import="dal.NotificationDAO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.User"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header Page</title>
        <!-- Favicon -->
        <link rel="shortcut icon" href="">
        <link href="assets/css/app.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <style>
            .logo{
                width:100%;
                max-width:300px;
                margin:0 auto
            }
            .logo img{
                width:100%;
                height:70px;
                display:block
            }
            .app{
                background-color:#fee2dc
            }
            ::selection{
                color:#000;
                background:#fee2dc
            }
        </style>
    </head>
    <body>

        <div class="app">
            <div class="layout">
                <!-- Header START -->
                <div class="header">
                    <div class="logo logo-dark">
                        <a href="home">
                            <img src="images/logo/logo.png" onerror="this.src='assets/images/others/product-3.jpg'" alt="Logo" class="logo-image">
                            <img class="logo-fold" src="images/logo/logo.png" onerror="this.src='assets/images/others/product-3.jpg'" alt="Logo" class="logo-image">
                        </a>
                    </div>
                    <div class="logo logo-white">
                        <a href="home">
                            <img src="images/logo/logo.png" onerror="this.src='assets/images/others/product-3.jpg'" alt="Logo" class="logo-image">
                            <img class="logo-fold" src="images/logo/logo.png" onerror="this.src='assets/images/others/product-3.jpg'" alt="Logo" class="logo-image">
                        </a>
                    </div>
                    <div class="nav-wrap">
                        <ul class="nav-left">
                            <li class="desktop-toggle">
                                <a href="javascript:void(0);">
                                    <i class="anticon"></i>
                                </a>
                            </li>
                            <li class="mobile-toggle">
                                <a href="javascript:void(0);">
                                    <i class="anticon"></i>
                                </a>
                            </li>
                            <li>
                                <a>
                                    <form action="SearchContent" method="get">
                                        <i class="anticon anticon-search"></i> <input type="text" name="searchToken" style="width: 250px;height: 25px;font-size:15px">
                                        <input type="submit" style="visibility: hidden;" />
                                    </form>
                                </a>
                            </li>
                        </ul>
                        <ul class="nav-right">
                            <c:if test="${user != null}">
                                <li class="dropdown dropdown-animated scale-left">
                                    <a href="javascript:void(0);" data-toggle="dropdown">
                                        <i class="anticon anticon-bell notification-badge"></i>
                                    </a>

                                    <div class="dropdown-menu pop-notification">
                                        <div class="p-v-15 p-h-25 border-bottom d-flex justify-content-between align-items-center">
                                            <p class="text-dark font-weight-semibold m-b-0">
                                                <i class="anticon anticon-bell"></i>
                                                <span class="m-l-10">Notification</span>
                                            </p>
                                            <a class="btn-sm btn-default btn" href="javascript:void(0);">
                                                <small>View All</small>
                                            </a>
                                        </div>
                                        <div class="relative">
                                            <div class="overflow-y-auto relative scrollable" style="max-height: 300px">
                                                <c:if test="${user != null}">
                                                    <%      
                                                        NotificationDAO nd = new NotificationDAO();
                                                        User user = (User)session.getAttribute("user");
                                                        ArrayList<Notification> notificationList = nd.getTop10Notification(user.getUserId());
                                                        pageContext.setAttribute("notificationList", notificationList);
                                                    %>
                                                </c:if>
                                                <c:forEach items="${notificationList}" var="noti">     
                                                    <c:if test="${noti.sourceUserId != noti.targetUserId}">

                                                        <c:choose>
                                                            <c:when test="${noti.sourcePostId != 0}">
                                                                <a href="postDetail?postId=${noti.sourcePostId}" class="dropdown-item d-block p-15 border-bottom">                                            
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <p class="link" style="float:right;margin-right: 30px" onclick="deleteNotification(${noti.notificationId})">x</p>
                                                                    <a href="Profile.jsp" class="dropdown-item d-block p-15 border-bottom">
                                                                    </c:otherwise>
                                                                </c:choose>

                                                                <div class="d-flex">
                                                                    <div class="avatar avatar-blue avatar-icon">
                                                                        <c:choose>
                                                                            <c:when test="${noti.sourcePostId != null}">
                                                                                <i class="anticon anticon-project">*</i>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <i class="anticon anticon-profile"></i>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </div>
                                                                    <div class="m-l-15" style="max-width: 100%; word-wrap: break-word; white-space: normal;">
                                                                        <p class="m-b-0 text-dark">${noti.content}</p> 
                                                                        <p class="m-b-0"><small>${noti.postTime}</small></p>  
                                                                    </div>
                                                                    <p class="link" style="margin: 0px 0px 8px 12px"  onclick="deleteNotification(${noti.notificationId})">x</p>
                                                                </div>
                                                            </a>
                                                        </c:if>
                                                    </c:forEach> 
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </c:if>
                            <jsp:include page="${param.accountHeader}" />
                            <li>
                                <a href="javascript:void(0);" data-toggle="modal" data-target="#quick-view">
                                    <i class="anticon anticon-appstore"></i>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>    
                <!-- Header END -->

                <!-- Side Nav START -->
                <jsp:include page="${param.sideNav}" />
                <!-- Side Nav END -->

                <!-- Page Container START -->
                <div class="page-container">
                    <div class="main-content">
                        <jsp:include page="${param.content}" />
                    </div>
                </div>
                <!-- Page Container END -->

                <!-- Search Start-->
                <div class="modal modal-left fade search" id="search-drawer">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header justify-content-between align-items-center">
                                <h5 class="modal-title">Search</h5>
                                <button type="button" class="close" data-dismiss="modal">
                                    <i class="anticon anticon-close"></i>
                                </button>
                            </div>
                            <div class="modal-body scrollable">
                                <div class="input-affix">
                                    <i class="prefix-icon anticon anticon-search"></i>
                                    <input type="text" class="form-control" placeholder="Search">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Search End-->

                <!-- Quick View START -->
                <div class="modal modal-right fade quick-view" id="quick-view">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header justify-content-between align-items-center">
                                <h5 class="modal-title">Theme Config</h5>
                            </div>
                            <div class="modal-body scrollable">
                                <div class="m-b-30">
                                    <h5 class="m-b-0">Header Color</h5>
                                    <p>Config header background color</p>
                                    <div class="theme-configurator d-flex m-t-10">
                                        <div class="radio">
                                            <input id="header-default" name="header-theme" type="radio" checked value="default">
                                            <label for="header-default"></label>
                                        </div>
                                        <div class="radio">
                                            <input id="header-primary" name="header-theme" type="radio" value="primary">
                                            <label for="header-primary"></label>
                                        </div>
                                        <div class="radio">
                                            <input id="header-success" name="header-theme" type="radio" value="success">
                                            <label for="header-success"></label>
                                        </div>
                                        <div class="radio">
                                            <input id="header-secondary" name="header-theme" type="radio" value="secondary">
                                            <label for="header-secondary"></label>
                                        </div>
                                        <div class="radio">
                                            <input id="header-danger" name="header-theme" type="radio" value="danger">
                                            <label for="header-danger"></label>
                                        </div>
                                    </div>
                                </div>
                                <hr>
                                <div>
                                    <h5 class="m-b-0">Side Nav Dark</h5>
                                    <p>Change Side Nav to dark</p>
                                    <div class="switch d-inline">
                                        <input type="checkbox" name="side-nav-theme-toogle" id="side-nav-theme-toogle">
                                        <label for="side-nav-theme-toogle"></label>
                                    </div>
                                </div>
                                <hr>
                                <div>
                                    <h5 class="m-b-0">Folded Menu</h5>
                                    <p>Toggle Folded Menu</p>
                                    <div class="switch d-inline">
                                        <input type="checkbox" name="side-nav-fold-toogle" id="side-nav-fold-toogle">
                                        <label for="side-nav-fold-toogle"></label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>            
                </div>
                <!-- Quick View END -->
            </div>
        </div>
        <script src="assets/js/vendors.min.js"></script>

        <script src="assets/js/app.min.js"></script>
        <style>
            .link {
                color: red;
                text-decoration: underline;
                cursor: pointer;
            }
        </style>

        <script>
                                                                        function deleteNotification(notificationId) {
                                                                            var xhr = new XMLHttpRequest();
                                                                            xhr.open('GET', 'DeleteNotification?NotificationId=' + notificationId, true);
                                                                            xhr.onreadystatechange = function () {
                                                                                if (xhr.readyState == 4 && xhr.status == 200) {
                                                                                    // Handle the response from the server
                                                                                    console.log('Notification deleted successfully');
                                                                                    // Refresh the page
                                                                                    window.location.reload();
                                                                                }
                                                                            };
                                                                            xhr.send();
                                                                        }
        </script>
    </body>

</html>
