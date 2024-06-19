<%-- 
    Document   : resetPass
    Created on : May 23, 2024, 8:52:09 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Change Password</title>
        <!-- Favicon -->
        <link rel="shortcut icon" href="">
        <!-- page css -->
        <!-- Core css -->
        <link href="assets/css/app.min.css" rel="stylesheet">
    </head>

    <body>
        <div class="app">
            <div class="container-fluid p-h-0 p-v-20 bg full-height d-flex" style="background-image: url('assets/images/others/login-3.png')">
                <div class="d-flex flex-column justify-content-between w-100">
                    <div class="container d-flex h-100">
                        <div class="row align-items-center w-100">
                            <div class="col-md-7 col-lg-5 m-h-auto">
                                <div class="card shadow-lg">
                                    <div class="card-body">
                                        <c:if test="${not empty errorMessage}">
                                            <p style="color:red">${errorMessage}</p>
                                        </c:if>
                                        <div class="d-flex align-items-center justify-content-end m-b-30">
                                            <h2 class="m-b-0">Change Password</h2>
                                        </div>
                                        <form action="changePassword" method="post">
                                            <div class="form-group">
                                                <label class="font-weight-semibold" for="oldPassword">Old Password:</label>
                                                <div class="input-affix">
                                                    <i class="prefix-icon anticon anticon-lock"></i>
                                                    <input type="password" name="oldPassword" class="form-control" id="oldPassword" placeholder="Old Password" required>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="font-weight-semibold" for="newPassword">New Password:</label>
                                                <div class="input-affix">
                                                    <i class="prefix-icon anticon anticon-lock"></i>
                                                    <input type="password" name="newPassword" class="form-control" id="newPassword" placeholder="New Password" required>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="font-weight-semibold" for="confirmPassword">Confirm New Password:</label>
                                                <div class="input-affix">
                                                    <i class="prefix-icon anticon anticon-lock"></i>
                                                    <input type="password" name="newConfirmedPassword" class="form-control" id="confirmPassword" placeholder="Confirm New Password" required>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="d-flex align-items-center justify-content-between">
                                                    <button class="btn btn-primary">Change Password</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Core Vendors JS -->
        <script src="assets/js/vendors.min.js"></script>

        <!-- page js -->

        <!-- Core JS -->
        <script src="assets/js/app.min.js"></script>
    </body>
</html>
