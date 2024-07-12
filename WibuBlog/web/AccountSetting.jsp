<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="assets/css/testcss2.css">
<link rel="stylesheet" href="assets/css/testcss3.css">
<link rel="stylesheet" href="assets/css/testcss4.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Account Setting</title>
        <!-- Favicon -->
        <link rel="shortcut icon" href="">
        <!-- page css -->
        <!-- Core css -->
        <link href="assets/css/app.min.css" rel="stylesheet">
    </head>
    <body >
        
        <div class="app">
            <div class="container-fluid p-h-0 p-v-20 bg full-height d-flex" style="background-image: url('PFP/6_20240613_100454.jpeg')">
                <div class="d-flex flex-column justify-content-between w-100">
                    <div class="container d-flex h-100">
                        <div class="row align-items-center w-100">
                            <div class="col-md-7 col-lg-5 m-h-auto">
                                <div class="card shadow-lg">
                                    <div class="card-body">
                                        <div class="d-flex align-items-center justify-content-end m-b-30">
                                            <h2 class="m-b-0">Account Profile</h2>
                                        </div>
                                            <div class="form-group">
                                                <p class="font-weight-semibold">Username: ${user.username} <span><a href="#" onclick="togglePopup()" style="float:right">Change</a></span> </p>
                                            </div>
                                            
                                            <div class="popup" id="popup-1">
                                                <div class="overlay" onclick="togglePopup()"></div>
                                                <div class="content">
                                                    <div class="close-btn" onclick="togglePopup()">&times;</div>
                                                    <h1>Change username</h1>
                                                    <p>Old username: ${user.username}</p>
                                                    <form action="ChangeUsername" method="post">
                                                        <input type="text" name="newUsername" id="newUsername" placeholder="new username" oninput="checkUsername()"> 
                                                        <br>
                                                        <span id="result" name="result" style="color: red"></span>
                                                        <hr>
                                                        <input type="submit" value="Done">
                                                    </form>
                                                </div>
                                            </div>
                                           
                                            <div class="form-group">
                                                <p class="font-weight-semibold">Email: ${user.email} <span><a href="#" style="float: right" onclick="togglePopup2()">Change</a></span> </p></p>
                                            </div>
                                            
                                             <div class="popup2" id="popup-2">
                                                <div class="overlay" onclick="togglePopup2()"></div>
                                                <div class="content">
                                                    <div class="close-btn" onclick="togglePopup2()">&times;</div>
                                                    <h1>Change email</h1>
                                                    <p>Email: ${user.email}</p>
                                                    <form action="ChangeEmail" method="post">
                                                        <input type="text" name="newEmail" id="newEmail" placeholder="new email" oninput="checkEmail()"> 
                                                        <br>
                                                        <span id="result2" name="result" style="color: red"></span>
                                                        <hr>
                                                        <input type="submit" value="Done">
                                                    </form>
                                                </div>
                                            </div>
                                                    
                                            <div class="form-group">
                                                <p class="font-weight-semibold">Full name: ${user.fullName} <span><a href="#" style="float: right" onclick="togglePopup3()">Change</a></span> </p></p>
                                            </div>
                                            
                                               <div class="popup3" id="popup-3">
                                                <div class="overlay" onclick="togglePopup3()"></div>
                                                <div class="content">
                                                    <div class="close-btn" onclick="togglePopup3()">&times;</div>
                                                    <h1>Change name</h1>
                                                    <p>Name: ${user.fullName}</p>
                                                    <form action="ChangeName" method="post">
                                                        <input type="text" name="newName" id="newName" placeholder="new name" oninput="checkName()"> 
                                                        <br>
                                                        <span id="result3" name="result3" style="color: red"></span>
                                                        <hr>
                                                        <input type="submit" value="Done">
                                                    </form>
                                                </div>
                                            </div>
                                                    
                                            <div class="form-group">
                                                <p class="font-weight-semibold" p>Password: ********  <span><a href="ChangePassword" style="float: right">Change</a></span> </p></p>
                                            </div>
                                                    
                                            
                                                    
                                              <div class="form-group">
                                                <p class="font-weight-semibold" p>Phone Number: ${user.phoneNumber}  <span><a href="#" style="float: right" onclick="togglePopup4()">Change</a></span> </p></p>
                                            </div>
                                            <div class="popup" id="popup-4">
                                                <div class="overlay" onclick="togglePopup4()"></div>
                                                <div class="content">
                                                    <div class="close-btn" onclick="togglePopup4()">&times;</div>
                                                    <h1>Change Phone Number</h1>    
                                                    <p>Old number: ${user.phoneNumber}</p>
                                                    <form action="ChangePhoneNumber" method="post">
                                                        <input type="number" name="newPhoneNumber" id="newPhoneNumber" placeholder="new phone number" oninput="checkPhoneNumber()" required oninput="this.value = this.value.replace(/[^0-9]/g, '');" maxlength="10"> 
                                                        <br>
                                                        <span id="result4" name="result4" style="color: red"></span>
                                                        <hr>
                                                        <input type="submit" value="Done">
                                                    </form>
                                                </div>
                                            </div>
                                            <hr >
                                            <div class="form-group">
                                                <div class="d-flex align-items-center justify-content-between p-t-15">
                                                    <button class="btn btn-primary" type="submit" style="margin: auto">Done</button>
                                                </div>
                                            </div>
                                        <a href="Profile.jsp" id="cancel_register"><i class="fas fa-angle-left"></i>Back</a>
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
        
        
         <script>
             function togglePopup() {
                document.getElementById("popup-1").classList.toggle("active");
            }
              function togglePopup2() {
                document.getElementById("popup-2").classList.toggle("active");
            }
              function togglePopup3() {
                document.getElementById("popup-3").classList.toggle("active");
            }
             function togglePopup4() {
                document.getElementById("popup-4").classList.toggle("active");
            }
        </script>
             
        
       
            
             <script>
             function checkUsername(){
                 jQuery.ajax({
                 url:"CheckUsername",
                 data:'newUsername='+$("#newUsername").val(),
                 type:"POST",
                 success:function(data){
                     $("#result").html(data);
                   },
                 error:function(){}
              });
             }
             
              function checkEmail(){
                 jQuery.ajax({
                 url:"CheckEmail",
                 data:'newEmail='+$("#newEmail").val(),
                 type:"POST",
                 success:function(data){
                     $("#result2").html(data);
                   },
                 error:function(){}
              });
             }
             
                function checkName(){
                 jQuery.ajax({
                 url:"CheckName",
                 data:'newName='+$("#newName").val(),
                 type:"POST",
                 success:function(data){
                     $("#result3").html(data);
                   },
                 error:function(){}
              });
             }
             
              function checkPhoneNumber(){
                 jQuery.ajax({
                 url:"CheckPhoneNumber",
                 data:'newPhoneNumber='+$("#newPhoneNumber").val(),
                 type:"POST",
                 success:function(data){
                     $("#result4").html(data);
                   },
                 error:function(){}
              });
             }
             </script>
    </body>
</html>
