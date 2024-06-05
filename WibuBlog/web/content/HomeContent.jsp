<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">

<h1>Hello world</h1>

<c:choose>
    <c:when test="${empty user}">
        <form action="login" method="get" class="form-button m-2">
            <button type="submit" class="btn btn-primary">Login</button>
        </form>
    </c:when>
    <c:otherwise>
        <h2>Welcome, <c:out value="${user.username}" />!</h2>
        <p>Id: <c:out value="${user.userId}" /></p>
        <p>Full Name: <c:out value="${user.fullName}" /></p>
        <p>Point: <c:out value="${user.point}" /></p>
        <p>Phone: <c:out value="${user.phoneNumber}" /></p>
        <form action="profile" method="post" class="form-button m-2">
            <input type="hidden" name="userId" value="<c:out value='${user.userId}' />">
            <button type="submit" class="btn btn-primary">My Profile</button>
        </form>
        <form action="changePassword" method="get" class="form-button m-2">
            <button type="submit" class="btn btn-primary">Change Password</button>
        </form>
        <form id="deleteAccountForm" action="deleteAccount" method="post" class="form-button m-2">
            <button type="button" class="btn btn-primary" onclick="confirmDelete()">Delete Account</button>
        </form>
        <form action="logout" method="get" class="form-button m-2">
            <button type="submit" class="btn btn-primary">Logout</button>
        </form>
    </c:otherwise>
</c:choose>

<script>
    function confirmDelete() {
        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.isConfirmed) {
                document.getElementById('deleteAccountForm').submit();
            }
        })
    }
</script>