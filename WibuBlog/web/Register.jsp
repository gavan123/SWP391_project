<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Register</title>

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
                                        <c:if test="${not empty successMessage}">
                                            <p style="color:greenyellow">${successMessage}</p>
                                        </c:if>
                                        <div class="d-flex align-items-center justify-content-end m-b-30">
                                            <h2 class="m-b-0">Register</h2>
                                        </div>
                                        <form action="register" method="post" class="form-signup">
                                            <div class="form-group">
                                                <label class="font-weight-semibold" for="userName">Username:</label>
                                                <input type="text" name="username" class="form-control" id="userName" placeholder="Username">
                                            </div>
                                            <div class="form-group">
                                                <label class="font-weight-semibold" for="email">Email:</label>
                                                <input type="email" name="email" class="form-control" id="email" placeholder="Email">
                                            </div>
                                            <div class="form-group">
                                                <label class="font-weight-semibold" for="fullName">Full name:</label>
                                                <input type="text" name="fullname"class="form-control" id="fullName" placeholder="Full Name">
                                            </div>
                                            <div class="form-group">
                                                <label class="font-weight-semibold" for="password">Password:</label>
                                                <input type="password" name="password" class="form-control" id="password" placeholder="Password">
                                            </div>
                                            <div class="form-group">
                                                <div class="d-flex align-items-center justify-content-between p-t-15">
                                                    <div class="checkbox">
                                                        <input type="checkbox" class="form-check-input" id="agreed" name="agreed" required>
                                                        <label for="agreed"><span>I have read the <a href="">agreement</a></span></label>
                                                    </div>
                                                    <button class="btn btn-primary" type="submit"><i class="fas fa-user-plus"></i> Sign Up</button>
                                                </div>
                                            </div>
                                        </form>
                                        <a href="login" id="cancel_register"><i class="fas fa-angle-left"></i> Back</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--        <div class="container">
        <c:if test="${not empty errorMessage}">
            <p style="color:red">${errorMessage}</p>
        </c:if>
        <c:if test="${not empty successMessage}">
            <p style="color:greenyellow">${successMessage}</p>
        </c:if>
        <form action="register" method="post" class="form-signup">
            <h1 class="h3 mb-3 font-weight-normal" style="text-align: center">Register</h1>
            <input name="user" type="text" id="username" class="form-control" placeholder="Username" required="" autofocus=""><br>
            <input name="pass" type="password" id="userpass" class="form-control" placeholder="Password" required autofocus=""><br>
            <input name="email" type="email" id="email" class="form-control" placeholder="Email" required="" autofocus=""><br>
            <input name="fullName" type="text" id="fullName" class="form-control" placeholder="Full Name" required="" autofocus=""><br>
            <div class="form-check">
                <input type="checkbox" class="form-check-input" id="agreed" name="agreed" required>
                <label class="form-check-label" for="agreed">
                    I agree to the <a href="terms.jsp" target="_blank">terms and conditions</a>
                </label>
            </div><br>
            <button class="btn btn-primary btn-block" type="submit"><i class="fas fa-user-plus"></i> Register</button>
            <a href="#" id="cancel_register"><i class="fas fa-angle-left"></i> Back</a>
        </form>
    </div>-->


        <!-- Core Vendors JS -->
        <script src="assets/js/vendors.min.js"></script>
        <!-- Core JS -->
        <script src="assets/js/app.min.js"></script>
    </body>
</html>
