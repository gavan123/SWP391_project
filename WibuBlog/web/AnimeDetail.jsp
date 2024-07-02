<%-- 
    Document   : AnimeDetail
    Created on : Jun 25, 2024, 4:22:47 PM
    Author     : minht
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="model.Anime" %>
<%@ page import="dal.AnimeDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.format.FormatStyle" %>
<%@ page import="java.time.LocalDateTime" %>

<%
    String animeIdS = (String)request.getParameter("animeId");
    int animeId = -1;
    try {
        animeId = Integer.parseInt(animeIdS);
    } catch (NumberFormatException nfe) {}
    
    
    Anime anime = null; 
    if  (animeId != -1) {
           AnimeDAO animeDAO = new AnimeDAO();
           anime = animeDAO.getAnimeDetailById(animeId);
    }         
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> <%= anime.getTitle() %></title>
    </head>
    <body>
        <jsp:include page="Layout.jsp">
            <jsp:param name="sideNav" value="SideNav.jsp" />
            <jsp:param name="accountHeader" value="/content/AccountContent.jsp" />
            <jsp:param name="content" value="/content/AnimeDetailContent.jsp" />
        </jsp:include>
    </body>
</html>
