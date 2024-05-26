<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Wibi Burotsugu - Verify Email</title>

        <!-- Favicon -->
        <link rel="shortcut icon" href="assets/images/logo/favicon.png">

        <!-- Core CSS -->
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
                                        <div class="d-flex align-items-center justify-content-end m-b-30">
                                            <h2 class="m-b-0">Verify Email</h2>
                                        </div>
                                        <form action="verificationRegister" method="post">
                                            <input type="hidden" id="usernameField" name="username" required="required" >
                                            <input type="hidden" name="template" required="required" value="${template}">
                                            <div class="form-group">
                                                <label class="font-weight-semibold" for="responseField">Enter verification code:</label>
                                               
                                                    <c:if test="${email != null}">
                                                <p style="color: blue">a verification code has been sent to ${email}, please login to your email and prompt the code</p>
                                            </c:if>
                                                      
                                                <input type="text" class="form-control" id="responseField" name="response" oninput="this.value = this.value.replace(/[^0-9]/g, '');" maxlength="6" required>
                                            </div>
                                            <p style="color : red">${requestScope.error}</p>
                                             <c:if test="${errorMessage != null}" >
                                                    <p style="color: red">${errorMessage}</p>
                                                </c:if>
                                            <button class="btn btn-primary" type="submit">Submit</button>
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

        <!-- Core JS -->
        <script src="assets/js/app.min.js"></script>
    </body>

</html>
