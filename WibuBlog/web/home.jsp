
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link rel="shortcut icon" href="">

    </head>
    <body>
        <jsp:include page="Layout.jsp">
            <jsp:param name="accountHeader" value="/content/AccountContent.jsp" />
            <jsp:param name="content" value="/content/HomeContent.jsp" />
        </jsp:include>
    </body>
</html>
