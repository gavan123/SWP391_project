<%-- 
    Document   : register
    Created on : May 22, 2024, 11:00:22 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <div>
        <form action="signup" method="post" class="form-signup">
                <h1 class="h3 mb-3 font-weight-normal" style="text-align: center"> Register</h1>
                <input name="user" type="text" id="user-name" class="form-control" placeholder="User name" required="" autofocus=""><br>
                <input name="pass" type="password" id="user-pass" class="form-control" placeholder="Password" required autofocus=""><br>
                <input name="repass" type="password" id="user-repeatpass" class="form-control" placeholder="Repeat Password" required autofocus=""><br>
                <input name="email" type="text" id="email" class="form-control" placeholder="Email" required="" autofocus=""><br>
                <input name="fullName" type="text" id="fullName" class="form-control" placeholder="Full Name" required="" autofocus=""><br>

                <button class="btn btn-primary btn-block" type="submit"><i class="fas fa-user-plus"></i> Register</button>
                <a href="#" id="cancel_signup"><i class="fas fa-angle-left"></i> Back</a>
            </form>
    </div>
</html>
