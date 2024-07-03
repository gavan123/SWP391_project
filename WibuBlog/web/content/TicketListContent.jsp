<%-- 
    Document   : TicketListContent
    Created on : 3 Jul 2024, 20:10:19
    Author     : admin
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
<link rel="stylesheet" href="assets/css/testcss6.css">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
<%@ page import="model.User" %>
<%@ page import="model.Post" %>
<%@ page import="dal.UserDAO" %>
<%@ page import="dal.GenreDAO" %>
<%@ page import="model.TopViewedGenre" %>
<%@ page import="dal.PostDAO" %>
<jsp:useBean id="t" scope="request" class="dal.TicketDAO" />
<%@page import="dal.TicketDAO"%>
<%@page import="dal.UserDAO"%>
<%@page import="model.Ticket"%>
<%@page import="java.util.ArrayList"%>

<%
    UserDAO ud = new UserDAO();
%>

<div class="card mb-2 rounded-5 border-0">
    <div class="card-header">
        <h3 class="card-header-h3 fs-16">
            Newest pending tickets
            <span class="float-right text-uppercase">
                <a href="question">More</a>
            </span>
        </h3>
    </div>
    <div class="card-body">
        <ul class="list-group list-group-flush">
            <c:set var="userId1" value="0" />
            <c:forEach var="ticket" items="${t.getPendingTickets()}">
                <c:set var="userId1" value="${ticket.userId}" />
                <%
                    Integer userId1 = (Integer) pageContext.getAttribute("userId1");
                    User user1 = ud.getUserById(userId1);
                %>
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <div class="col-10 text-truncate font-weight-bold">
                        <a href="TicketDetail?ticketId=${ticket.ticketId}"><%= user1.getUsername() %>: ${ticket.content}</a>
                    </div>
                    <span class="badge badge-primary badge-pill">
                        <i class="far fa-comment-dots fa-lg"></i>
                        <span style="font-size: larger;">${ticket.status}</span> 
                    </span>
                </li>
            </c:forEach>
        </ul>
    </div>

    <div class="card-header">
        <h3 class="card-header-h3 fs-16">
            Resolved tickets
            <span class="float-right text-uppercase">
                <a href="question">More</a>
            </span>
        </h3>
    </div>
    <div class="card-body">
        <ul class="list-group list-group-flush">
            <c:set var="userId2" value="0" />
            <c:forEach var="ticket" items="${t.getResolvedTickets()}">
                <c:set var="userId2" value="${ticket.userId}" />
                <%
                    Integer userId2 = (Integer) pageContext.getAttribute("userId2");
                    User user2 = ud.getUserById(userId2);
                %>
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <div class="col-10 text-truncate font-weight-bold">
                        <a href="TicketDetail?ticketId=${ticket.ticketId}"><%= user2.getUsername() %>: ${ticket.content}</a>
                    </div>
                    <span class="badge badge-primary badge-pill">
                        <i class="far fa-comment-dots fa-lg"></i>
                        <span style="font-size: larger;">${ticket.status}</span> 
                    </span>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>