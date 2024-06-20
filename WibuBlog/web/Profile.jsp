<%-- 
    Document   : Profile
    Created on : May 27, 2024, 4:02:33 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
        <!-- Favicon -->
        <link rel="shortcut icon" href="">
        <!-- page css -->
        <!-- Core css -->
    </head>
    <body>
        <jsp:include page="Layout.jsp">
            <jsp:param name="accountHeader" value="/content/AccountContent.jsp" />
            <jsp:param name="sideNav" value="SideNav.jsp" />
            <jsp:param name="content" value="/content/ProfileContent.jsp" />
        </jsp:include>            
    </body>
</html>
