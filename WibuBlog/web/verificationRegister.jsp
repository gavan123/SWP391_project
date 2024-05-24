<%-- 
    Document   : verifyemail
    Created on : 3 Mar 2024, 13:42:45
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify your email</title>
        <link rel="stylesheet" href="css/stylesheet.css">
    </head>
    <body>
        <p>A 6-digit verification code has been sent to your email. Please enter the code below to complete the verification process: </p>
        
        <form action="verificationRegister" method="post">
            <input type="text" id="usernameField" name="username" required="required" hidden="hidden" >
            <input type="text" name="template" hidden="hidden" required="required" value="<%= request.getAttribute("template") %>">
            <label for="responseField"><input type="text" name="response" id="responseField" required="required"></label>
            <p style="color : red">${requestScope.error}</p>
            <button type="submit">Submit</button>
        </form>
    </body>
</html>
