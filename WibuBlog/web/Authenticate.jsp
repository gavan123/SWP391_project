<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Wibi Burotsugu</title>

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
                                            <h2 class="m-b-0">Authenticate</h2>
                                        </div>
                                        <form action="forgotAuthenticate" method="post">
                                            <div class="form-group">
                                                <label class="font-weight-semibold" for="code">Enter verification code</label>
                                                <div class="input-affix">
                                                    <i class="prefix-icon anticon anticon-user"></i>
                                                    <input type="text" class="form-control" name="code" id="code" placeholder="Enter code here" oninput="this.value = this.value.replace(/[^0-9]/g, '');" maxlength="6" required="">
                                                    <input type="hidden" value="${email}" name="email">
                                                </div>
                                            </div>                                     
                                            <div class="form-group">
                                                <div class="d-flex align-items-center justify-content-between">
                                                    <span class="font-size-13 text-muted">                                                    
                                                        <a class="small" href="login">Return to login</a>
                                                    </span>
                                                    <button type="submit" class="btn btn-primary">Next</button>
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