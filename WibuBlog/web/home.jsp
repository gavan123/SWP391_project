
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link rel="shortcut icon" href="">

        <!-- Core CSS -->
        <link href="assets/css/app.min.css" rel="stylesheet">

    </head>
    <body>
        <h1>Hello world</h1>

        <c:choose>
            <c:when test="${empty user}">
                <form action="login" method="get" class="form-button m-2">
                    <button type="submit" class="btn btn-primary ">Login</button>
                </form>
            </c:when>
            <c:otherwise>
                <h2>Welcome, <c:out value="${user.username}" />!</h2>
                <p>Full Name: <c:out value="${user.fullName}" /></p>
                <form action="ChangePassword" method="get" class="form-button m-2">
                    <button type="submit" class="btn btn-primary ">Change Password</button>
                </form>
                <form action="logout" method="get" class="form-button m-2" >
                    <button type="submit" class="btn btn-primary ">Logout</button>
                </form>
            </c:otherwise>
        </c:choose>

        <!-- Core Vendors JS -->
        <script src="assets/js/vendors.min.js"></script>

        <!-- Core JS -->
        <script src="assets/js/app.min.js"></script>
    </body>
</html>
