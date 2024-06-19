<%-- 
    Document   : PostDetail
    Created on : Jun 18, 2024, 10:10:02 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Post Detail Page</title>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

    </head>
    <body>
        <jsp:include page="Layout.jsp">
            <jsp:param name="sideNav" value="SideNav.jsp" />
            <jsp:param name="accountHeader" value="/content/AccountContent.jsp" />
            <jsp:param name="content" value="/content/PostDetailContent.jsp" />
        </jsp:include>
    </body>
</html>
