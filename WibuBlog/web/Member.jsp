<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member profile Page</title>
    </head>
    <body>
         <jsp:include page="Layout.jsp">
            <jsp:param name="accountHeader" value="/content/AccountContent.jsp" />
            <jsp:param name="sideNav" value="SideNav.jsp" />
            <jsp:param name="content" value="/content/MemberContent.jsp" />
        </jsp:include>    
    </body>
</html>
