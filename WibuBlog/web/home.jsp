<%-- 
    Document   : home
    Created on : May 23, 2024, 4:51:02 PM
    Author     : user
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link rel="shortcut icon" href="assets/images/logo/favicon.png">

        <!-- page css -->

        <!-- Core css -->
        <link href="assets/css/app.min.css" rel="stylesheet">
    </head>
    <body>
        <h1>Hello world</h1>
        <c:choose>
            <c:when test="${empty user}">
                <form action="login" method="get">
                    <button type="submit" class="btn btn-primary">Login</button>
                </form>
            </c:when>
            <c:otherwise>
                <h2>Welcome, <c:out value="${user.username}" />!</h2>
                <p>Full Name: <c:out value="${user.fullName}" /></p>
                <form action="logout" method="get">
                    <button type="submit" class="btn btn-primary">Logout</button>
                </form>
            </c:otherwise>
        </c:choose>
    </body>
    <!-- Core Vendors JS -->
    <script src="assets/js/vendors.min.js"></script>

    <!-- page js -->

    <!-- Core JS -->
    <script src="assets/js/app.min.js"></script>
</html>
