
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ page import="dal.UserDAO" %>
<%@ page import="dal.MediaDAO" %>
<link rel="stylesheet" href="assets/css/testcss.css">
<% User user = (User)session.getAttribute("user");
    UserDAO userDAO = new UserDAO();
    String rank = userDAO.getRankByRankID(user.getRankId());
    String rankColor = userDAO.getColorByRank(rank);
    String role = userDAO.getRoleByRoleID(user.getRoleId()); 
    MediaDAO mediaDAO = new MediaDAO();
    
%>
<div class="container">
    <div class="card">
        <div class="card-body">
            <div class="row align-items-center">
                <div class="col-md-7">
                    <div class="d-md-flex align-items-center">
                        <div class="text-center text-sm-left ">
                            
                          <div class="test avatar avatar-image" style="width: 150px; height: 150px">                            
                                   <c:choose>
                                        <c:when test="${user.profilePhoto == 0}">
                                            <form action="UploadPFP" method="post" enctype="multipart/form-data"  >
                                            <input type="file" class="upload-input" onchange="this.form.submit()" name="pfp" id="someId">
                                            <img src="assets/images/avatars/thumb-3.jpg" alt="">
                                            <div class="upload-text">Upload an image</div>
                                            </form>
                                        </c:when>                                     
                                        <c:otherwise>
                                            <p class="col font-weight-semibold"><img src=""></p>
                                        </c:otherwise>
                                      </c:choose>
                                
                            </div>
                        </div>
                        <div class="text-center text-sm-left m-v-15 p-l-30">
                            <h2 class="m-b-5">${user.username}</h2>
                            <i class="text-opacity font-size-15" style="color:<%=rankColor%>" ><b><%=rank%></b></i>
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
                    <p>It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here'.</p>
                    <h5>Experices</h5>
                    <div class="m-t-20">
                        <div class="media m-b-30">
                            <div class="avatar avatar-image">
                                <img src="assets/images/others/adobe-thumb.png" alt="">
                            </div>
                            <div class="media-body m-l-20">
                                <h6 class="m-b-0">UI/UX Designer, Adobe Inc.</h6>
                                <span class="font-size-13 text-gray">Jul 2018</span>
                            </div>
                        </div>
                        <div class="media m-b-30">
                            <div class="avatar avatar-image">
                                <img src="assets/images/others/amazon-thumb.png" alt="">
                            </div>
                            <div class="media-body m-l-20">
                                <h6 class="m-b-0">Product Developer, Amazon.com Inc.</h6>
                                <span class="font-size-13 text-gray">Jul-2017 - Jul 2018</span>
                            </div>
                        </div>
                        <div class="media m-b-30">
                            <div class="avatar avatar-image">
                                <img src="assets/images/others/nvidia-thumb.png" alt="">
                            </div>
                            <div class="media-body m-l-20">
                                <h6 class="m-b-0">Interface Designer, Nvidia Corporation</h6>
                                <span class="font-size-13 text-gray">Jul-2016 - Jul 2017</span>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <h5>Education</h5>
                    <div class="m-t-20">
                        <div class="media m-b-30">
                            <div class="avatar avatar-image">
                                <img src="assets/images/others/cambridge-thumb.png" alt="">
                            </div>
                            <div class="media-body m-l-20">
                                <h6 class="m-b-0">MSt in Social Innovation, Cambridge University</h6>
                                <span class="font-size-13 text-gray">Jul-2012 - Jul 2016</span>
                            </div>
                        </div>
                        <div class="media m-b-30">
                            <div class="avatar avatar-image">
                                <img src="assets/images/others/phillips-academy-thumb.png" alt="">
                            </div>
                            <div class="media-body m-l-20">
                                <h6 class="m-b-0">Phillips Academy</h6>
                                <span class="font-size-13 text-gray">Jul-2005 - Jul 2011</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-body">
                    <h5>Reviews (18)</h5>
                    <div class="m-t-20">
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item p-h-0">
                                <div class="media m-b-15">
                                    <div class="avatar avatar-image">
                                        <img src="assets/images/avatars/thumb-8.jpg" alt="">
                                    </div>
                                    <div class="media-body m-l-20">
                                        <h6 class="m-b-0">
                                            <a href="" class="text-dark">Lillian Stone</a>
                                        </h6>
                                        <span class="font-size-13 text-gray">28th Jul 2018</span>
                                    </div>
                                </div>
                                <span>The palatable sensation we lovingly refer to as The Cheeseburger has a distinguished and illustrious history. It was born from humble roots, only to rise to well-seasoned greatness.</span>
                                <div class="star-rating m-t-15">
                                    <input type="radio" id="star1-5" name="rating-1" value="5" checked disabled/><label for="star1-5" title="5 star"></label>
                                    <input type="radio" id="star1-4" name="rating-1" value="4" disabled/><label for="star1-4" title="4 star"></label>
                                    <input type="radio" id="star1-3" name="rating-1" value="3" disabled/><label for="star1-3" title="3 star"></label>
                                    <input type="radio" id="star1-2" name="rating-1" value="2" disabled/><label for="star1-2" title="2 star"></label>
                                    <input type="radio" id="star1-1" name="rating-1" value="1" disabled/><label for="star1-1" title="1 star"></label>
                                </div>
                            </li>
                            <li class="list-group-item p-h-0">
                                <div class="media m-b-15">
                                    <div class="avatar avatar-image">
                                        <img src="assets/images/avatars/thumb-9.jpg" alt="">
                                    </div>
                                    <div class="media-body m-l-20">
                                        <h6 class="m-b-0">
                                            <a href="" class="text-dark">Victor Terry</a>
                                        </h6>
                                        <span class="font-size-13 text-gray">28th Jul 2018</span>
                                    </div>
                                </div>
                                <span>The palatable sensation we lovingly refer to as The Cheeseburger has a distinguished and illustrious history. It was born from humble roots, only to rise to well-seasoned greatness.</span>
                                <div class="star-rating m-t-15">
                                    <input type="radio" id="star2-5" name="rating-2" value="5" disabled/><label for="star2-5" title="5 star"></label>
                                    <input type="radio" id="star2-4" name="rating-2" value="4" checked disabled/><label for="star2-4" title="4 star"></label>
                                    <input type="radio" id="star2-3" name="rating-2" value="3" disabled/><label for="star2-3" title="3 star"></label>
                                    <input type="radio" id="star2-2" name="rating-2" value="2" disabled/><label for="star2-2" title="2 star"></label>
                                    <input type="radio" id="star2-1" name="rating-2" value="1" disabled/><label for="star2-1" title="1 star"></label>
                                </div>
                            </li>
                            <li class="list-group-item p-h-0">
                                <div class="media m-b-15">
                                    <div class="avatar avatar-image">
                                        <img src="assets/images/avatars/thumb-10.jpg" alt="">
                                    </div>
                                    <div class="media-body m-l-20">
                                        <h6 class="m-b-0">
                                            <a href="" class="text-dark">Wilma Young</a>
                                        </h6>
                                        <span class="font-size-13 text-gray">28th Jul 2018</span>
                                    </div>
                                </div>
                                <span>The palatable sensation we lovingly refer to as The Cheeseburger has a distinguished and illustrious history. It was born from humble roots, only to rise to well-seasoned greatness.</span>
                                <div class="star-rating m-t-15">
                                    <input type="radio" id="star3-5" name="rating-3" value="5" checked disabled/><label for="star3-5" title="5 star"></label>
                                    <input type="radio" id="star3-4" name="rating-3" value="4" disabled/><label for="star3-4" title="4 star"></label>
                                    <input type="radio" id="star3-3" name="rating-3" value="3" disabled/><label for="star3-3" title="3 star"></label>
                                    <input type="radio" id="star3-2" name="rating-3" value="2" disabled/><label for="star3-2" title="2 star"></label>
                                    <input type="radio" id="star3-1" name="rating-3" value="1" disabled/><label for="star3-1" title="1 star"></label>
                                </div>
                            </li>
                        </ul> 
                    </div>  
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
                                    file.onchange = function(e) {
                                      var ext = this.value.match(/\.([^\.]+)$/)[1];                                 
                                      switch (ext) {
                                        case 'jpg':
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
                                </script>
