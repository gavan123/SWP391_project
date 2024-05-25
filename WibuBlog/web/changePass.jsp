<%-- 
    Document   : resetPass
    Created on : May 23, 2024, 8:52:09 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
    </head>
    <body>
        <h1>Change Password</h1>
        <c:if test="${not empty errorMessage}">
            <p style="color:red;">${errorMessage}</p>
        </c:if>
        <form action="ResetPassword" method="post">
            <div>
                <label for="username">UserID:</label>
                <input type="text" id="username" name="userID" required>
            </div>
             <div>
                <label for="newPassword">Old Password:</label>
                <input type="password" id="oldPassword" name="oldPassword" required>
            </div>
            <div>
                <label for="newPassword">New Password:</label>
                <input type="password" id="newPassword" name="newPassword" required>
            </div>
            <div>
                <label for="confirmPassword">Confirm New Password:</label>
                <input type="password" id="confirmPassword" name="newConfirmedPassword" required>
            </div>
            <div>
                <button type="submit">Change Password</button>
            </div>
        </form>
    </body>
</html>
