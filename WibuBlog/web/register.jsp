<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
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
        </div>
    </body>
</html>
