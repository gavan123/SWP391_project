<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Hello world</h1>

<c:choose>
    <c:when test="${empty user}">
        <form action="login" method="get" class="form-button m-2">
            <button type="submit" class="btn btn-primary">Login</button>
        </form>
    </c:when>
    <c:otherwise>
        <h2>Welcome, <c:out value="${user.username}" />!</h2>
        <p>Full Name: <c:out value="${user.fullName}" /></p>
        <p>Point: <c:out value="${user.point}" /></p>
        <p>Phone: <c:out value="${user.phoneNumber}" /></p>
        <input type="text" class="form-control" placeholder="Basic Input">

        <form action="changePassword" method="get" class="form-button m-2">
            <button type="submit" class="btn btn-primary">Change Password</button>
        </form>
        <form action="logout" method="get" class="form-button m-2">
            <button type="submit" class="btn btn-primary">Logout</button>
        </form>
    </c:otherwise>
</c:choose>