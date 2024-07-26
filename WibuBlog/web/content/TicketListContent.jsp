<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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
            Newest pending tickets
        </h3>
           <span class="float-right text-uppercase">
                <a href="CreateTicket">Create ticket</a>
            </span>
    </div>
    <div class="card-body">
        <ul class="list-group list-group-flush">
            <c:set var="userId1" value="0" />
            <c:forEach var="ticket" items="${t.getPendingTickets()}">
                <c:set var="userId" value="${ticket.userId}" />
                <%
                    Integer userId = (Integer) pageContext.getAttribute("userId");
                    User user = ud.getUserById(userId);
                %>
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <div class="col-10 text-truncate font-weight-bold">
                        <a href="javascript:void(0);" onclick="openPopup('<%= user.getUsername()%>', '${ticket.content}', '${ticket.ticketId}')">
                           (${ticket.timeCreated}) <%= user.getUsername()%>: ${ticket.content}
                        </a>
                    </div>
                    <span class="badge badge-primary badge-pill">
                        <i class="far fa-comment-dots fa-lg"></i>
                        <span style="font-size: larger;">${ticket.status}</span> 
                    </span>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div id="popup-overlay" class="popup-overlay"></div>

    <div id="popup" class="popup">
        <div class="popup-header" id="popup-header">Username</div>
        <div class="popup-content" id="popup-content">Ticket Content</div>
        <div class="popup-note">
            <textarea id="popup-note" rows="6" style="width: 100%;" placeholder="Enter your note here..."></textarea>
        </div>
        <div class="popup-buttons">
            <button onclick="confirmAction('reject')" class="btn btn-danger">Reject</button>
            <button onclick="confirmAction('approve')" class="btn btn-success">Approve</button>
        </div>
        <form id="popup-form" method="post">
            <input type="hidden" id="popup-ticket-id" name="ticketId">
            <input type="hidden" id="popup-note-input" name="note">
            <input type="hidden" id="popup-username" name="username">
        </form>
    </div>

    <div class="card-header">
        <h3 class="card-header-h3 fs-16">
            Resolved tickets
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
                        <a href="#">(${ticket.timeCreated}) <%= user2.getUsername()%>: ${ticket.content}</a>
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

<script>
    document.addEventListener("DOMContentLoaded", function() {
        document.getElementById('popup-overlay').addEventListener('click', closePopup);
    });

    function openPopup(username, content, ticketId) {
        console.log("openPopup called with username: " + username + ", content: " + content + ", ticketId: " + ticketId);
        document.getElementById('popup-header').innerText = username;
        document.getElementById('popup-content').innerText = content;
        document.getElementById('popup-ticket-id').value = ticketId; // Set the ticket ID
        document.getElementById('popup-overlay').style.display = 'block';
        document.getElementById('popup-username').value = username;
        document.getElementById('popup').style.display = 'block';
    }

    function closePopup() {
        console.log("closePopup called");
        document.getElementById('popup-overlay').style.display = 'none';
        document.getElementById('popup').style.display = 'none';
    }

    function confirmAction(action) {
        const note = document.getElementById('popup-note').value;
        document.getElementById('popup-note-input').value = note;

        Swal.fire({
            title: `Are you sure you want to ` + action + ` this ticket?`,
            text: `Note:` + note,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: `Yes, ` + action + ` it!`,
            cancelButtonText: 'No, cancel!',
        }).then((result) => {
            if (result.isConfirmed) {
                const form = document.getElementById('popup-form');
                form.action = action === 'reject' ? 'RejectTicket' : 'ApproveTicket';
                form.method = 'POST';
                form.submit();
            }
        });
    }
</script>
