
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ page import="model.Media" %>
<%@ page import="dal.UserDAO" %>
<%@ page import="dal.MediaDAO" %>
<%@ page import="dal.PostDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.PostDetail" %>
<link rel="stylesheet" href="assets/css/testcss.css">
<link rel="stylesheet" href="assets/css/testcss2.css">
<%                             User user = (User)session.getAttribute("user");
                               UserDAO userDAO = new UserDAO();                            
                               String rankColor = userDAO.getColorByRank(userDAO.getRankByUserId(user.getUserId()));
                               String role = userDAO.getRoleByRoleID(user.getRoleId()); 
                               
%>
<div class="container">
    <div class="card">
        <div class="card-body">
            <div class="row align-items-center">
                <div class="col-md-7">
                    <div class="d-md-flex align-items-center">
                        <div class="text-center text-sm-left ">

                            <div class="test avatar avatar-image" style="width: 150px; height: 100px;border: 2px solid grey;">  
                                <c:choose>
                                    <c:when test="${user.profilePhoto == null}">
                                        <form action="UploadPFP" method="post" enctype="multipart/form-data"  >
                                            <input type="file" class="upload-input" onchange="this.form.submit()" name="pfp" id="someId">                                       
                                            <div class="upload-text">Upload an image</div>
                                        </form>
                                    </c:when>                                     
                                    <c:otherwise>
                                        <form action="UploadPFP" method="post" enctype="multipart/form-data"  >
                                            <input type="file" class="upload-input" onchange="this.form.submit()" name="pfp" id="someId">         
                                            <img src="${pageContext.request.contextPath}/images/game/${user.profilePhoto}" style="width: 150px; height: 100px">
                                            <div class="upload-text">Upload an image</div>
                                        </form>               
                                    </c:otherwise>
                                </c:choose>

                            </div>
                        </div>                                        
                        <div class="text-center text-sm-left m-v-15 p-l-30">
                            <h2 class="m-b-5">${user.username}</h2>
                            <i class="text-opacity font-size-15" style="color:<%=rankColor%>" ><b><%=userDAO.getRankByUserId(user.getUserId())%>(<%=userDAO.getUserByUserId(user.getUserId()).getPoint()%>)</b></i>
                            <p class="text-dark m-b-20"><%=role%></p>
                        </div>
                    </div>
                </div>
                <div class="col-md-5">
                    <div class="row">
                        <div class="d-md-block d-none border-left col-1"></div>
                        <div class="col">
                            <ul class="list-unstyled m-t-10">
                                <li class="row">
                                    <p class="col-sm-4 col-4 font-weight-semibold text-dark m-b-5">
                                        <i class="m-r-10 text-primary anticon anticon-mail"></i>
                                        <span>Email: </span> 
                                    </p>
                                    <p class="col font-weight-semibold">${user.email}</p>
                                </li>
                                <li class="row">
                                    <p class="col-sm-4 col-4 font-weight-semibold text-dark m-b-5">
                                        <i class="m-r-10 text-primary anticon anticon-phone"></i>
                                        <span>Phone:</span> 
                                    </p>                                 
                                    <c:choose>
                                        <c:when test="${user.phoneNumber != null}">
                                            <p class="col font-weight-semibold"> ${user.phoneNumber} </p>
                                            <c:if test="${updateNumberMessage != null}">
                                                <p style="color: green">${updateNumberMessage}</p>
                                            </c:if>                                                                                     
                                        </c:when>                                     
                                        <c:otherwise>
                                            <p class="col font-weight-semibold"> <a href="addPhoneNumber">Add your phone number</a> </p>
                                        </c:otherwise>
                                    </c:choose>

                                </li>

                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-8">
            <div class="card">
                <div class="card-body">
                    <h5>Bio</h5>     
                    <c:choose>
                        <c:when test="${user.bio == null}">
                            <form action="SetBio" method="post" id="UpdateBio">
                                <label for="bio"></label>
                                <textarea id="bio" name="bio" rows="5" cols="90" placeholder="Show your bio!"></textarea>             
                            </form>
                            <input type="submit" value="Lưu" form="UpdateBio"> 
                        </c:when>
                        <c:otherwise>
                            <div class="w3-container w3-pale-blue w3-leftbar w3-border-blue">
                                <p>${user.bio}</p>
                                <hr style="color: aqua">

                            </div>
                            <div>

                                <a href="#" onclick="togglePopup()" style="float:right">Edit</a>
                            </div>
                            <div class="popup" id="popup-1">
                                <div class="overlay" onclick="togglePopup()"></div>
                                <div class="content">
                                    <div class="close-btn" onclick="togglePopup()">&times;</div>
                                    <h1>Change new bio</h1>
                                    <p>Enter new bio</p>
                                    <form action="UpdateBio" method="post">
                                        <textarea name="newBio" rows="4" cols="50" placeholder="Your new bio 😍"></textarea>
                                        <br>
                                        <input type="submit" value="Done">
                                    </form>
                                </div>
                            </div>
                            <script>
                                function togglePopup() {
                                    document.getElementById("popup-1").classList.toggle("active");
                                }
                            </script>
                        </c:otherwise>
                    </c:choose>
                    <%
                        PostDAO postDAO = new PostDAO();
                        ArrayList<PostDetail> userPostList = postDAO.getPostDetailByUserId(user.getUserId());
                    %>
                    <br>
                    <h5>Posts</h5>
                    <div class="m-t-20">
                        <c:choose>
                            <c:when test="${userPostList.isEmpty}">
                                <%if (userDAO.getUserStatusByUserId(user.getUserId()).equals("active")){%>  
                                    <p>You've not posted anything. Get started by <a href="createPost">create a post</a></p>  
                                     <%}%>
                                 <%if (userDAO.getUserStatusByUserId(user.getUserId()).equals("deactive")){%>  
                                    <label>You are currently banned please wait for your ban to expire to post</label>
                                <%}%>
                                    
                            </c:when>
                            <c:otherwise>
                                 <%if (userDAO.getUserStatusByUserId(user.getUserId()).equals("active")){%>  
                                    <p >What are your thoughts. Don't mind sharing by <a href="createPost">creating a post</a></p>  
                                <%}%>

                                 <%if (userDAO.getUserStatusByUserId(user.getUserId()).equals("deactive")){%>  
                                    <label>You are currently banned please wait for your ban to expire to post</label>
                                <%}%>
                               
                                <hr>
                                <h5>Your posts</h5>
                                <c:forEach items="<%=userPostList%>" var="post">
                                    <div class="card mb-2" onclick="redirectToLink('${pageContext.request.contextPath}/postDetail?postId=${post.postID}')">
                                        <div class="card-body">
                                            <header>
                                                <h1 class="card-title" style="font-size: 26px;line-height:34px">${post.title}</h1>
                                            </header>
                                            <h6 class="card-subtitle mb-2 fw-700" style="font-size: small !important;">
                                                <i class="fas fa-user"></i> 
                                                <a href="#" style="color:blue" accesskey="a">${post.username}</a> | 
                                                <i class="fas fa-clock"></i>
                                                <time datetime="${postTime}">${postTime}</time> | 
                                                <i class="fas fa-eye"></i> ${post.view}
                                            </h6>
                                            <h3 class="fs-14 border-bottom-badge-eee">
                                                <span class="badge badge-info mr-1">
                                                    <a class="text-white" href="postListByCategory?name=${post.categoryName}">${post.categoryName}</a>
                                                </span>
                                                <span class="badge badge-primary mr-1">
                                                    <a class="text-white" href="#">${post.genreName}</a>
                                                </span>
                                            </h3>
                                            <div class="card-text fs-content" style="font-size: 18px;">
                                                ${post.content}
                                                <br>
                                                <br>
                                                <br>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="col-lg-4 mb-2 mx-auto">
                                                    <ul class="list-unstyled m-0 d-flex flex-wrap justify-content-center">
                                                        <li class="d-flex align-items-center mr-4 font-weight-bold">
                                                            <div class="vote-section" id="vote-section">
                                                                <i id="vote_up" class="anticon anticon_vote anticon-arrow-up mr-2"
                                                                   onclick="votePost('up', '${votePostStatus}')"></i>
                                                                <i id="vote_down" class="anticon anticon_vote anticon-arrow-down mr-2" 
                                                                   onclick="votePost('down', '${votePostStatus}')"></i>
                                                                <span id="vote_value">${post.vote}</span>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <hr>

                </div>
            </div>

        </div>
        <div class="col-md-4">
            <div class="card">
                <div class="card-body">
                    <h5>Connected</h5>
                    <div class="m-t-30">
                        <div class="d-flex align-items-center">
                            <div class="avatar avatar-image">
                                <img src="assets/images/avatars/thumb-1.jpg" alt="">
                            </div>
                            <div class="m-l-10">
                                <h5 class="m-b-0">
                                    <a href="" class="text-dark">Erin Gonzales</a>
                                </h5>
                                <span class="font-size-13 text-gray">UI/UX Designer</span>
                            </div>
                        </div>
                    </div>
                    <div class="m-t-30">
                        <div class="d-flex align-items-center">
                            <div class="avatar avatar-image">
                                <img src="assets/images/avatars/thumb-2.jpg" alt="">
                            </div>
                            <div class="m-l-10">
                                <h5 class="m-b-0">
                                    <a href="" class="text-dark">Darryl Day</a>
                                </h5>
                                <span class="font-size-13 text-gray">Software Engineer</span>
                            </div>
                        </div>
                    </div>
                    <div class="m-t-30">
                        <div class="d-flex align-items-center">
                            <div class="avatar avatar-image">
                                <img src="assets/images/avatars/thumb-3.jpg" alt="">
                            </div>
                            <div class="m-l-10">
                                <h5 class="m-b-0">
                                    <a href="" class="text-dark">Marshall Nichols</a>
                                </h5>
                                <span class="font-size-13 text-gray">Product Manager</span>
                            </div>
                        </div>
                    </div>
                    <div class="m-t-30">
                        <div class="d-flex align-items-center">
                            <div class="avatar avatar-image">
                                <img src="assets/images/avatars/thumb-6.jpg" alt="">
                            </div>
                            <div class="m-l-10">
                                <h5 class="m-b-0">
                                    <a href="" class="text-dark">Riley Newman</a>
                                </h5>
                                <span class="font-size-13 text-gray">Data Analyst</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-body">
                    <h5>Projects</h5>
                    <div class="m-t-20">
                        <div class="m-b-20 p-b-20 border-bottom">
                            <div class="media align-items-center m-b-15">
                                <div class="avatar avatar-image">
                                    <img src="assets/images/others/coffee-app-thumb.jpg" alt="">
                                </div>
                                <div class="media-body m-l-20">
                                    <h5 class="m-b-0">
                                        <a href="" class="text-dark">Coffee Finder App</a>
                                    </h5>
                                </div>
                            </div>
                            <p>It is a long established fact that a reader will be distracted by the readable content.</p>
                            <div class="d-inline-block">
                                <a class="m-r-5" data-toggle="tooltip" title="Eugene Jordan" href="">
                                    <div class="avatar avatar-image avatar-sm">
                                        <img src="assets/images/avatars/thumb-6.jpg" alt="">
                                    </div>
                                </a>
                                <a class="m-r-5" data-toggle="tooltip" title="Pamela" href="">
                                    <div class="avatar avatar-image avatar-sm">
                                        <img src="assets/images/avatars/thumb-7.jpg" alt="">
                                    </div>
                                </a>
                            </div>
                            <div class="float-right">
                                <span class="badge badge-pill badge-blue font-size-12 p-h-10">In Progress</span>
                            </div>
                        </div>
                        <div class="m-b-20 p-b-20 border-bottom">
                            <div class="media align-items-center m-b-15">
                                <div class="avatar avatar-image">
                                    <img src="assets/images/others/weather-app-thumb.jpg" alt="">
                                </div>
                                <div class="media-body m-l-20">
                                    <h5 class="m-b-0">
                                        <a href="" class="text-dark">Weather App</a>
                                    </h5>
                                </div>
                            </div>
                            <p>It is a long established fact that a reader will be distracted by the readable content.</p>
                            <div class="d-inline-block">
                                <a class="m-r-5" data-toggle="tooltip" title="Lillian Stone" href="">
                                    <div class="avatar avatar-image avatar-sm">
                                        <img src="assets/images/avatars/thumb-8.jpg" alt="">
                                    </div>
                                </a>
                                <a class="m-r-5" data-toggle="tooltip" title="Victor Terry" href="">
                                    <div class="avatar avatar-image avatar-sm">
                                        <img src="assets/images/avatars/thumb-9.jpg" alt="">
                                    </div>
                                </a>
                                <a class="m-r-5" data-toggle="tooltip" title="Wilma Young" href="">
                                    <div class="avatar avatar-image avatar-sm">
                                        <img src="assets/images/avatars/thumb-10.jpg" alt="">
                                    </div>
                                </a>
                            </div>
                            <div class="float-right">
                                <span class="badge badge-pill badge-cyan font-size-12 p-h-10">Completed</span>
                            </div>
                        </div>
                        <div class="m-b-20 p-b-20 border-bottom">
                            <div class="media align-items-center m-b-15">
                                <div class="avatar avatar-image">
                                    <img src="assets/images/others/music-app-thumb.jpg" alt="">
                                </div>
                                <div class="media-body m-l-20">
                                    <h5 class="m-b-0">
                                        <a href="" class="text-dark">Music App</a>
                                    </h5>
                                </div>
                            </div>
                            <p>Protein, iron, and calcium are some of the nutritional benefits associated with cheeseburgers.</p>
                            <div class="d-inline-block">
                                <a class="m-r-5" data-toggle="tooltip" title="Darryl Day" href="">
                                    <div class="avatar avatar-image avatar-sm">
                                        <img src="assets/images/avatars/thumb-2.jpg" alt="">
                                    </div>
                                </a>
                                <a class="m-r-5" data-toggle="tooltip" title="Virgil Gonzales" href="">
                                    <div class="avatar avatar-image avatar-sm">
                                        <img src="assets/images/avatars/thumb-4.jpg" alt="">
                                    </div>
                                </a>
                            </div>
                            <div class="float-right">
                                <span class="badge badge-pill badge-cyan font-size-12 p-h-10">Completed</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    var file = document.getElementById('someId');
    file.onchange = function (e) {
        var ext = this.value.match(/\.([^\.]+)$/)[1];
        switch (ext) {
            case 'jpg':
                this.form.submit();
                break;
            case 'jpeg':
                this.form.submit();
                break;
            case 'webp':
                this.form.submit();
                break;
            case 'bmp':
                this.form.submit();
            case 'png':
                this.form.submit();
                break;
            case 'tif':
                this.form.submit();
                break;
            default:
                alert('Not allowed');
                this.value = '';
        }
    };
    function redirectToLink(url) {
        window.location.href = url;
    }
    function togglePopup() {
        document.getElementById("popup-1").classList.toggle("active");
    }


</script>
