<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Question list Page</title>
        <style>
            .card{border-radius:15px}
        </style>
    </head>
    <body>
        <jsp:include page="Layout.jsp">
            <jsp:param name="sideNav" value="SideNav.jsp" />
            <jsp:param name="accountHeader" value="/content/AccountContent.jsp" />
            <jsp:param name="content" value="/content/QuestionContent.jsp" />
        </jsp:include>
    </body>
</html>
