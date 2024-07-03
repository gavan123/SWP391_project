<%-- 
    Document   : PostList
    Created on : Jun 13, 2024, 4:17:19 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Post list Page</title>
        <style>
            .posts-img{width:200px;height:auto;object-fit:cover;border-radius:5px}
            .card-text{display:block;overflow:hidden;white-space:nowrap;text-overflow:ellipsis}
        </style>
    </head>
    <body>
        <jsp:include page="Layout.jsp">
            <jsp:param name="sideNav" value="SideNav.jsp" />
            <jsp:param name="accountHeader" value="/content/AccountContent.jsp" />
            <jsp:param name="content" value="/content/PostListContent.jsp" />
        </jsp:include>
    </body>
</html>
