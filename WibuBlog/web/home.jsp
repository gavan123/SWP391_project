
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link rel="shortcut icon" href="">


        <!--        <link href="assets/css/app.min.css" rel="stylesheet">-->

    </head>
    <body>
        <jsp:include page="Layout.jsp">
            <jsp:param name="content" value="HomeContent.jsp" />
        </jsp:include>

<!--        <script src="assets/js/vendors.min.js"></script>

        <script src="assets/js/app.min.js"></script>-->
    </body>
</html>
