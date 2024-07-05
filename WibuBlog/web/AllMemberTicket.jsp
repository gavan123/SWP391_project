<%-- 
    Document   : AllTicket
    Created on : Jun 29, 2024, 11:49:21 PM
    Author     : admin
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="p" scope="request" class="dal.TicketDAO" />
<%@page import="dal.TicketDAO"%>
<%@page import="dal.UserDAO"%>
<%@page import="model.Ticket"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ticket list</title>
        <link rel="shortcut icon" href="">
        <link href="https://unpkg.com/swiper/swiper-bundle.min.css" rel="stylesheet">
        <style>
            .card{border:5px solid #ddd;border-radius:15px;box-shadow:0 2px 4px rgba(0,0,0,0.1)}
            .card-header-h3{font-size:18px;font-weight:700}
            .swiper-slide{width:200px;margin-right:15px}
            .posts-badge{position:absolute;top:10px;left:10px;z-index:1}
            .posts-badge-2{position:absolute;top:10px;right:10px;z-index:1}
            .swiper-img{max-width:100%;max-height:150px;object-fit:cover}
            .text-truncate-2{display:-webkit-box;-webkit-line-clamp:2;-webkit-box-orient:vertical;overflow:hidden;text-overflow:ellipsis}
        </style>
    </head>
    <body>
        <jsp:include page="Layout.jsp">
            <jsp:param name="sideNav" value="SideNav.jsp" />
            <jsp:param name="accountHeader" value="/content/AccountContent.jsp" />
            <jsp:param name="content" value="/content/MemberTicketListContent.jsp" />
        </jsp:include>
    </body>

    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
    <script>
        var swiper = new Swiper('.swiper-container', {
            slidesPerView: 'auto',
            spaceBetween: 15,
            pagination: {
                el: '.swiper-pagination',
                clickable: true
            }
        });
    </script>
</html>
