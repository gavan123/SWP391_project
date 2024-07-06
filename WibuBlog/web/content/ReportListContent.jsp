<%-- 
    Document   : ReportListContent
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
<%@ page import="dal.ReportDAO" %>
<jsp:useBean id="r" scope="request" class="dal.ReportDAO" />
<%@page import="dal.TicketDAO"%>
<%@page import="dal.ReportDAO"%>
<%@page import="dal.UserDAO"%>
<%@page import="model.Report"%>
<%@page import="java.util.ArrayList"%>

<style>
.popup{display:none;position:fixed;left:50%;top:50%;transform:translate(-50%,-50%);border:1px solid #ccc;padding:30px;background:#fff;z-index:1000;width:500px;box-shadow:0 0 15px rgba(0,0,0,0.2)}
.popup-header{font-size:24px;margin-bottom:20px}
.popup-content{margin-bottom:20px}
.popup-note{margin-bottom:20px}
.popup-buttons{display:flex;justify-content:space-between}
.popup-buttons button{padding:10px 20px;cursor:pointer}
.popup-overlay{display:none;position:fixed;left:0;top:0;width:100%;height:100%;background:rgba(0,0,0,0.5);z-index:999}
</style>

<%
    UserDAO ud = new UserDAO();
%>

<div class="card mb-2 rounded-5 border-0">
    <div class="card-header">
        <h3 class="card-header-h3 fs-16">
            Newest pending reports           
        </h3>
    </div>
    <div class="card-body">
        <ul class="list-group list-group-flush">
            <c:set var="userId1" value="0" />
            <c:forEach var="report" items="${r.getPendingReports()}">
                <c:set var="userId1" value="${report.userId}" />
                <%
                    Integer userId1 = (Integer) pageContext.getAttribute("userId1");
                    User user1 = ud.getUserById(userId1);
                %>
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <div class="col-10 text-truncate font-weight-bold">
                        <a href="javascript:void(0);" onclick="openPendingPopup('<%= user1.getUsername()%>', '${report.reason}', '${report.reportId}','${report.postId}')">
                            (${report.timeCreated}) ${report.reason}: <%= user1.getUsername()%>
                        </a>
                    </div>
                    <span class="badge badge-primary badge-pill">
                        <i class="far fa-comment-dots fa-lg"></i>
                        <span style="font-size: larger;">${report.status}</span> 
                    </span>
                </li>
            </c:forEach>
        </ul>
    </div>
            
    <div class="card-header">
        <h3 class="card-header-h3 fs-16">
            Resolved reports
        </h3>
    </div>
    <div class="card-body">
        <ul class="list-group list-group-flush">
            <c:set var="userId2" value="0" />
            <c:forEach var="report" items="${r.getResolvedReports()}">
                <c:set var="userId2" value="${report.userId}" />
                <%
                    Integer userId2 = (Integer) pageContext.getAttribute("userId2");
                    User user2 = ud.getUserById(userId2);
                %>
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <div class="col-10 text-truncate font-weight-bold">
                        <a href="javascript:void(0);" onclick="openResolvedPopup('<%= user2.getUsername()%>', '${report.reason}','${report.postId}', '${report.note}')">
                            (${report.timeCreated}) ${report.reason}: <%= user2.getUsername()%>
                        </a>
                    </div>
                    <span class="badge badge-primary badge-pill">
                        <i class="far fa-comment-dots fa-lg"></i>
                        <span style="font-size: larger;">${report.status}</span> 
                    </span>
                </li>
            </c:forEach>
        </ul>
    </div>
            
    <div id="popup-overlay" class="popup-overlay"></div>
<% ReportDAO rd = new ReportDAO();%>
<div id="popup" class="popup">
    <form id="popup-form" action="ValidateReport" method="post">
        <a href="#" id="postDetailLink" style="float: right">go to post</a>
        <div class="popup-header" id="popup-header">Username</div>
        <div class="popup-content" id="popup-content">Report Reason</div>
        <input type="hidden" id="popup-username" name="username">
        <input type="hidden" id="popup-reason" name="reason">
        <input type="hidden" id="popup-note-hidden" name="note">
        <input type="hidden" id="popup-decision" name="choice">
        <input type="hidden" id="popup-reportId" name="reportId"> <!-- Hidden input for reportId -->
        <div class="popup-note">
            <textarea id="popup-note" rows="6" style="width: 100%;" placeholder="Enter your note here..."></textarea>
        </div>
        <div class="popup-buttons">
            <button type="button" onclick="reject()" class="btn btn-danger">Reject</button>
            <button type="button" onclick="approve()" class="btn btn-success">Approve</button>
        </div>
    </form>
</div>

<div id="popup-resolved" class="popup">
     <a href="#" id="postDetailLink2" style="float: right">go to post</a>
    <div class="popup-header" id="popup-header-resolved">Resolved Report Details</div>
    <div class="popup-content" id="popup-content-resolved">
        <p id="popup-resolved-content"></p>
        <p id="popup-resolved-note"></p>
    </div>
    <div class="popup-buttons">
        <button type="button" onclick="closeResolvedPopup()" class="btn btn-secondary">Close</button>
    </div>
</div>

</div>


<script>
  function openPendingPopup(username, content, reportId, postId) {
    document.getElementById('popup-header').innerText = username;
    document.getElementById('popup-content').innerText = content;
    document.getElementById('popup-username').value = username;
    document.getElementById('popup-reason').value = content;
    document.getElementById('popup-reportId').value = reportId; // Set reportId
    document.getElementById('popup-overlay').style.display = 'block';
    document.getElementById('popup').style.display = 'block';
    var postDetailLink = document.getElementById('postDetailLink');
    postDetailLink.href = 'postDetail?postId=' + postId;
}

function openResolvedPopup(username, content,postId ,note) {
    document.getElementById('popup-header-resolved').innerText = username;
    document.getElementById('popup-resolved-content').innerText = content;
    document.getElementById('popup-resolved-note').innerText = "Note: " + note;
    document.getElementById('popup-overlay').style.display = 'block';
    document.getElementById('popup-resolved').style.display = 'block';
    var postDetailLink2 = document.getElementById('postDetailLink2');
    postDetailLink2.href = 'postDetail?postId=' + postId;
}

function closePopup() {
    document.getElementById('popup-overlay').style.display = 'none';
    document.getElementById('popup').style.display = 'none';
    document.getElementById('popup-resolved').style.display = 'none';
}

function closeResolvedPopup() {
    document.getElementById('popup-overlay').style.display = 'none';
    document.getElementById('popup-resolved').style.display = 'none';
}

function reject() {
    const note = document.getElementById('popup-note').value;
    document.getElementById('popup-note-hidden').value = note;
    document.getElementById('popup-decision').value = false;
    document.getElementById('popup-form').submit();
}

function approve() {
    const note = document.getElementById('popup-note').value;
    document.getElementById('popup-note-hidden').value = note;
    document.getElementById('popup-decision').value = true;
    document.getElementById('popup-form').submit();
}

document.getElementById('popup-overlay').addEventListener('click', closePopup);
</script>