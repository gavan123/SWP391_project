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
<%@page import="model.User"%>
<%@page import="java.util.ArrayList"%>
<%
    UserDAO ud = new UserDAO();
    User user = (User)session.getAttribute("user");
    TicketDAO td = new TicketDAO();
%>
<div class="card mb-2 rounded-5 border-0">
    <div class="card-header">
        <h3 class="card-header-h3 fs-16">
            Pending tickets
        <span class="float-right text-uppercase">
            <a href="CreateTicket">Create ticket</a>
        </span>
        </h3>
    </div>
    <div class="card-body">
        <ul class="list-group list-group-flush">
            <%for(Ticket x : td.getUserPendingTickets(user.getUserId())){%>
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <div class="col-10 text-truncate font-weight-bold">
                        <a href="javascript:void(0);" onclick="openPopup('${user.username}', '<%=x.getContent()%>', '<%=x.getTicketId()%>', '', 'pending')">
                            ${user.username}: <%=x.getContent()%> 
                        </a>
                    </div>
                    <span class="badge badge-primary badge-pill">
                        <i class="far fa-comment-dots fa-lg"></i>
                        <span style="font-size: larger;"><%=x.getStatus()%></span>          
                    </span>
                        <a style="float: right;color: red" href="javascript:void(0);" onclick="confirmDelete('<%=x.getTicketId()%>')"">X</a>
                </li>
           <%}%>
        </ul>
    </div>
</div>

<div class="card-header">
    <h3 class="card-header-h3 fs-16">
        Resolved tickets
    </h3>
</div>
<div class="card-body">
    <ul class="list-group list-group-flush">
        <%for(Ticket x : td.getUserResolvedTickets(user.getUserId())){%>
           <li class="list-group-item d-flex justify-content-between align-items-center">
                    <div class="col-10 text-truncate font-weight-bold">
                        <a href="javascript:void(0);" onclick="openPopup('${user.username}', '<%=x.getContent()%>', '<%=x.getTicketId()%>', '<%=x.getNote()%>', 'resolved')">
                            ${user.username}: <%=x.getContent()%>
                        </a>
                    </div>
                    <span class="badge badge-primary badge-pill">
                        <i class="far fa-comment-dots fa-lg"></i>
                        <span style="font-size: larger;"><%=x.getStatus()%></span> 
                    </span>
                </li>
        <%}%>
    </ul>
</div>
<div id="popup-overlay" class="popup-overlay"></div>

<div id="popup" class="popup">
    <a href="#" onclick="closePopup()" style="float:right">Close</a>
    <div class="popup-header" id="popup-header">Username</div>
    <div class="popup-content" id="popup-content">Ticket Content</div>
    <div id="popup-note" class="popup-note" style="display: none;">Admin Note</div>
    <form id="edit-ticket-form" action="EditTicket" method="POST" style="display: none;">
        <input type="hidden" id="ticket-id-input" name="ticketId">
        <textarea id="ticket-content-input" name="content" rows="4" class="form-control mb-2"></textarea>
        <div class="popup-buttons">
            <button type="submit" class="btn btn-primary">Update Ticket</button> 
        </div>
    </form>
  
</div>

<script>
    function openPopup(username, content, ticketId, adminNote, status) {
        console.log("openPopup called with username: " + username + ", content: " + content + ", ticketId: " + ticketId + ", adminNote: " + adminNote + ", status: " + status);
        document.getElementById('popup-header').innerText = username;
        document.getElementById('popup-content').innerText = content;

        if (status === 'resolved') {
            document.getElementById('popup-note').style.display = 'block';
            document.getElementById('popup-note').innerText = "Admin Note: " + adminNote;
            document.getElementById('edit-ticket-form').style.display = 'none';
        } else {
            document.getElementById('popup-note').style.display = 'none';
            document.getElementById('edit-ticket-form').style.display = 'block';
            document.getElementById('ticket-id-input').value = ticketId;
            document.getElementById('ticket-content-input').value = content;
        }

        document.getElementById('popup-overlay').style.display = 'block';
        document.getElementById('popup').style.display = 'block';
    }

    function closePopup() {
        console.log("closePopup called");
        document.getElementById('popup-overlay').style.display = 'none';
        document.getElementById('popup').style.display = 'none';
    }

    function confirmDelete(ticketId) {
        Swal.fire({
            title: 'Are you sure?',
            text: "Do you really want to delete this ticket?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, cancel!',
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = 'DeleteTicket?ticketId=' + ticketId;
            }
        });
    }
</script>

<style>
    .popup {
        display: none;
        position: fixed;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
        border: 1px solid #ccc;
        padding: 30px;
        background: white;
        z-index: 1000;
        width: 500px;
        box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
    }
    .popup-header {
        font-size: 24px;
        margin-bottom: 20px;
    }
    .popup-content {
        margin-bottom: 20px;
    }
    .popup-note {
        margin-bottom: 20px;
    }
    .popup-buttons {
        display: flex;
        justify-content: space-between;
    }
    .popup-buttons p {
        margin: 0;
    }
    .popup-buttons button {
        padding: 10px 20px;
        cursor: pointer;
    }
    .popup-overlay {
        display: none;
        position: fixed;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        background: rgba(0, 0, 0, 0.5);
        z-index: 999;
    }
</style>
