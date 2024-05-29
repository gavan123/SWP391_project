
<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Reset password</title>

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
                                    <div class="d-flex align-items-center justify-content-between m-b-30">
                                        <img class="img-fluid" alt="" src="assets/images/logo/logo.png">
                                        <h2 class="m-b-0">Reset Password</h2>
                                    </div>
                                    <form action="ResetPassword" method="post">
                                         <c:if test="${errorMessage != null}">
                                                <p style="color: red">${errorMessage}</p>
                                            </c:if>
                                                <input type="hidden" value="${email}" name="email">
                                        <div class="form-group">
                                            <label class="font-weight-semibold" for="userName">New password:</label>
                                            <div class="input-affix">
                                                 <i class="prefix-icon anticon anticon-lock"></i>
                                                 <input type="password" class="form-control" id="userName" placeholder="New password" name="newPassword">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="font-weight-semibold" for="password">New confirmed Password:</label>
                                            <div class="input-affix m-b-10">
                                                <i class="prefix-icon anticon anticon-lock"></i>
                                                <input type="password" class="form-control" id="password" placeholder="New confirmed password" name="newConfirmedPassword">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="d-flex align-items-center justify-content-between">
                                                <span class="font-size-13 text-muted">                                                 
                                                    <a class="small" href="login"> Return to login</a>
                                                </span>
                                                <button class="btn btn-primary">Next</button>
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