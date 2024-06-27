<%@ page import="model.User" %>
<%@ page import="model.Post" %>
<%@ page import="dal.UserDAO" %>
<%@ page import="dal.GenreDAO" %>
<%@ page import="model.TopViewedGenre" %>
<%@ page import="dal.PostDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-container">              
    <!-- Content Wrapper START -->
    <div class="main-content">
        <div class="row">
            <div class="col-md-6 col-lg-3">
                <div class="card">
                    <div class="card-body">
                        <div class="media align-items-center">
                            <div class="avatar avatar-icon avatar-lg avatar-blue">
                                <i class="anticon anticon-file-ppt"></i>
                            </div>
                            <div class="m-l-15">
                                <h2 class="m-b-0">${NumberOfPostLast3Days}</h2>
                                <p class="m-b-0 text-muted">Post Last 3 Days</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-6 col-lg-3">
                <div class="card">
                    <div class="card-body">
                        <div class="media align-items-center">
                            <div class="avatar avatar-icon avatar-lg avatar-cyan">
                                <i class="anticon anticon-line-chart"></i>
                            </div>
                            <div class="m-l-15">
                                <h2 class="m-b-0">${AvgPostPerDay}</h2>
                                <p class="m-b-0 text-muted">Post/Day</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-6 col-lg-3">
                <div class="card">
                    <div class="card-body">
                        <div class="media align-items-center">
                            <div class="avatar avatar-icon avatar-lg avatar-gold">
                                <i class="anticon anticon-profile"></i>
                            </div>
                            <div class="m-l-15">
                                <h2 class="m-b-0">${TotalPost}</h2>
                                <p class="m-b-0 text-muted">Total Post</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-6 col-lg-3">
                <div class="card">
                    <div class="card-body">
                        <div class="media align-items-center">
                            <div class="avatar avatar-icon avatar-lg avatar-purple">
                                <i class="anticon anticon-user"></i>
                            </div>
                            <div class="m-l-15">
                                <h2 class="m-b-0">${TotalMember}</h2>
                                <p class="m-b-0 text-muted">Total Members</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12 col-lg-12 col-sm-12 ">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-center">
                            <h5>Top Members</h5>
                            <div>
                                <a href="javascript:void(0);" class="btn btn-sm btn-default">View All</a>
                            </div>
                        </div>
                        <div class="m-t-30">
                            <div class="table-responsive">
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>Member</th>
                                            <th>Points</th>
                                            <th>Rank</th>
                                            <th>Role</th>
                                            <th style="max-width: 70px">Posts Last 3 Days</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% UserDAO ud = new UserDAO();
                                           PostDAO pd = new PostDAO();
                                           for(User x : pd.getTop10UserByPoint()){%>                                
                                        <tr>
                                            <td>
                                                <div class="media align-items-center">
                                                    <div class="avatar avatar-image rounded">
                                                        <img src="${pageContext.request.contextPath}/images/game/<%=x.getProfilePhoto()%>"  onerror="this.src='assets/images/others/product-3.jpg'" alt="${topUser.username}">
                                                    </div>
                                                    <div class="m-l-10">
                                                        <span><%=x.getUsername()%></span>
                                                    </div>
                                                </div>
                                            </td>
                                            <td><%=x.getPoint()%></td>

                                            <td style="color:<%=ud.getColorByRank(ud.getRankByRankID(x.getRankId()))%>"><%=ud.getRankByRankID(x.getRankId())%></td>
                                            <td><%=ud.getRoleByRoleID(x.getRoleId())%></td>
                                            <td>
                                                <div class="d-flex align-items-center">

                                                    <div class="m-l-10">
                                                        <%=ud.getUserTotalPostLast3Days(x.getUserId())%>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        <%}%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
                                    
                                    
        <div class="row">
            <div class="col-md-12 col-lg-4">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-center">
                            <h5 class="m-b-0">Hot Posts</h5>
                        </div>
                        <div class="m-t-30">
                            <% for(Post x: pd.getTop6VotedPost()){%>
                            <div class="m-b-25" onclick="redirectToLink('${pageContext.request.contextPath}/postDetail?postId=<%=x.getPostId()%>')">
                                <div class="d-flex align-items-center justify-content-between">
                                    <!--start-->
                                    <div class="avatar avatar-image rounded" onclick="redirectToLink('${pageContext.request.contextPath}/postDetail?postId=<%=x.getPostId()%>')">
                                        <img src="${pageContext.request.contextPath}/images/game/<%=x.getImage()%>"  onerror="this.src='assets/images/others/product-3.jpg'" alt="${topUser.username}">
                                    </div>
                                    <div class="m-l-10" >
                                        <% if(x.getTitle().length() < 12){ %>
                                        <span><%=x.getTitle()%></span>
                                        <%}%>
                                        <% if(x.getTitle().length() >= 12){%>
                                         <span><%=pd.trimPostTitle(x.getTitle())%></span>
                                        <%}%>
                                    </div>                                
                                    <div class="scale-left">
                                    
                                        <div>
                                                <i class="anticon anticon-eye"></i>
                                                <span class="m-l-10">View(<%=x.getView()%>)</span>        
                                                <i class="anticon anticon-arrow-up"></i>
                                                <span class="m-l-10">Vote(<%=x.getVote()%>)</span>
                                        </div>
                                    </div>
                                </div>  
                                        <br><hr>   
                            </div>
                            <%}%>
                            <!--end-->

                        </div>
                    </div>
                </div>
            </div>
                            
            <% GenreDAO gd = new GenreDAO();%>  
            <div class="col-md-12 col-lg-4">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-center">
                            <h5 class="m-b-0">Popular Category</h5>
                        </div>
                        <div class="m-t-30">
                            <% for(TopViewedGenre x: gd.getTop6ViewedGenre()){%>
                            <div class="m-b-25" >
                                <div class="d-flex align-items-center justify-content-between">
                                    <!--start-->
                                    <div>
                                        <% if(x.getGenre().length() < 12){ %>
                                        <p><%=x.getGenre()%></p>
                                        <%}%>
                                         <% if(x.getGenre().length() >= 12){%>
                                         <p><%=pd.trimPostTitle(x.getGenre())%></p>
                                        <%}%>
                                    </div>
                                    <div class="m-l-10" >
                                     <i class="anticon anticon-eye"></i>
                                                <span class="m-l-10">View(<%=x.getTotalView()%>)</span>   
                                        
                                    </div>                                
                                    <div class="scale-left">
                                    
                                        <div>
                                                <i class="anticon anticon-arrow-up"></i>
                                                <span class="m-l-10">Total Posts(<%=x.getTotalPosts()%>)</span>
                                        </div>
                                    </div>
                                </div>  
                                        <br><hr>   
                            </div>
                            <%}%>
                        </div>
                    </div>
                </div>
            </div>
                            
                            
            <div class="col-md-12 col-lg-4">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-center">
                            <h5 class="m-b-0">Members</h5>
                            <div>
                                <a href="javascript:void(0);" class="btn btn-sm btn-default">View All</a>
                            </div>
                        </div>
                        <div class="m-t-30">
                            <div class="m-b-25">
                                <div class="d-flex align-items-center justify-content-between">
                                    <div class="media align-items-center">
                                        <div class="font-size-35">
                                            <i class="anticon anticon-file-word text-primary"></i>
                                        </div>
                                        <div class="m-l-15">
                                            <h6 class="m-b-0">
                                                <a class="text-dark" href="javascript:void(0);">Documentation.doc</a>
                                            </h6>
                                            <p class="text-muted m-b-0">1.2MB</p>
                                        </div>
                                    </div>
                                    <div class="dropdown dropdown-animated scale-left">
                                        <a class="text-gray font-size-18" href="javascript:void(0);" data-toggle="dropdown">
                                            <i class="anticon anticon-ellipsis"></i>
                                        </a>
                                        <div class="dropdown-menu">
                                            <button class="dropdown-item" type="button">
                                                <i class="anticon anticon-eye"></i>
                                                <span class="m-l-10">View</span>
                                            </button>
                                            <button class="dropdown-item" type="button">
                                                <i class="anticon anticon-download"></i>
                                                <span class="m-l-10">Download</span>
                                            </button>
                                            <button class="dropdown-item" type="button">
                                                <i class="anticon anticon-delete"></i>
                                                <span class="m-l-10">Remove</span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="m-b-25">
                                <div class="d-flex align-items-center justify-content-between">
                                    <div class="media align-items-center">
                                        <div class="font-size-35">
                                            <i class="anticon anticon-file-excel text-success"></i>
                                        </div>
                                        <div class="m-l-15">
                                            <h6 class="m-b-0">
                                                <a class="text-dark" href="javascript:void(0);">Expensess.xls</a>
                                            </h6>
                                            <p class="text-muted m-b-0">518KB</p>
                                        </div>
                                    </div>
                                    <div class="dropdown dropdown-animated scale-left">
                                        <a class="text-gray font-size-18" href="javascript:void(0);" data-toggle="dropdown">
                                            <i class="anticon anticon-ellipsis"></i>
                                        </a>
                                        <div class="dropdown-menu">
                                            <button class="dropdown-item" type="button">
                                                <i class="anticon anticon-eye"></i>
                                                <span class="m-l-10">View</span>
                                            </button>
                                            <button class="dropdown-item" type="button">
                                                <i class="anticon anticon-download"></i>
                                                <span class="m-l-10">Download</span>
                                            </button>
                                            <button class="dropdown-item" type="button">
                                                <i class="anticon anticon-delete"></i>
                                                <span class="m-l-10">Remove</span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="m-b-25">
                                <div class="d-flex align-items-center justify-content-between">
                                    <div class="media align-items-center">
                                        <div class="font-size-35">
                                            <i class="anticon anticon-file-text text-secondary"></i>
                                        </div>
                                        <div class="m-l-15">
                                            <h6 class="m-b-0">
                                                <a class="text-dark" href="javascript:void(0);">Receipt.txt</a>
                                            </h6>
                                            <p class="text-muted m-b-0">355KB</p>
                                        </div>
                                    </div>
                                    <div class="dropdown dropdown-animated scale-left">
                                        <a class="text-gray font-size-18" href="javascript:void(0);" data-toggle="dropdown">
                                            <i class="anticon anticon-ellipsis"></i>
                                        </a>
                                        <div class="dropdown-menu">
                                            <button class="dropdown-item" type="button">
                                                <i class="anticon anticon-eye"></i>
                                                <span class="m-l-10">View</span>
                                            </button>
                                            <button class="dropdown-item" type="button">
                                                <i class="anticon anticon-download"></i>
                                                <span class="m-l-10">Download</span>
                                            </button>
                                            <button class="dropdown-item" type="button">
                                                <i class="anticon anticon-delete"></i>
                                                <span class="m-l-10">Remove</span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="m-b-25">
                                <div class="d-flex align-items-center justify-content-between">
                                    <div class="media align-items-center">
                                        <div class="font-size-35">
                                            <i class="anticon anticon-file-word text-primary"></i>
                                        </div>
                                        <div class="m-l-15">
                                            <h6 class="m-b-0">
                                                <a class="text-dark" href="javascript:void(0);">Project Requirement.doc</a>
                                            </h6>
                                            <p class="text-muted m-b-0">1.6MB</p>
                                        </div>
                                    </div>
                                    <div class="dropdown dropdown-animated scale-left">
                                        <a class="text-gray font-size-18" href="javascript:void(0);" data-toggle="dropdown">
                                            <i class="anticon anticon-ellipsis"></i>
                                        </a>
                                        <div class="dropdown-menu">
                                            <button class="dropdown-item" type="button">
                                                <i class="anticon anticon-eye"></i>
                                                <span class="m-l-10">View</span>
                                            </button>
                                            <button class="dropdown-item" type="button">
                                                <i class="anticon anticon-download"></i>
                                                <span class="m-l-10">Download</span>
                                            </button>
                                            <button class="dropdown-item" type="button">
                                                <i class="anticon anticon-delete"></i>
                                                <span class="m-l-10">Remove</span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="m-b-25">
                                <div class="d-flex align-items-center justify-content-between">
                                    <div class="media align-items-center">
                                        <div class="font-size-35">
                                            <i class="anticon anticon-file-pdf text-danger"></i>
                                        </div>
                                        <div class="m-l-15">
                                            <h6 class="m-b-0">
                                                <a class="text-dark" href="javascript:void(0);">App Flow.pdf</a>
                                            </h6>
                                            <p class="text-muted m-b-0">19.8MB</p>
                                        </div>
                                    </div>
                                    <div class="dropdown dropdown-animated scale-left">
                                        <a class="text-gray font-size-18" href="javascript:void(0);" data-toggle="dropdown">
                                            <i class="anticon anticon-ellipsis"></i>
                                        </a>
                                        <div class="dropdown-menu">
                                            <button class="dropdown-item" type="button">
                                                <i class="anticon anticon-eye"></i>
                                                <span class="m-l-10">View</span>
                                            </button>
                                            <button class="dropdown-item" type="button">
                                                <i class="anticon anticon-download"></i>
                                                <span class="m-l-10">Download</span>
                                            </button>
                                            <button class="dropdown-item" type="button">
                                                <i class="anticon anticon-delete"></i>
                                                <span class="m-l-10">Remove</span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div class="d-flex align-items-center justify-content-between">
                                    <div class="media align-items-center">
                                        <div class="font-size-35">
                                            <i class="anticon anticon-file-ppt text-warning"></i>
                                        </div>
                                        <div class="m-l-15">
                                            <h6 class="m-b-0">
                                                <a class="text-dark" href="javascript:void(0);">Presentation.ppt</a>
                                            </h6>
                                            <p class="text-muted m-b-0">2.7MB</p>
                                        </div>
                                    </div>
                                    <div class="dropdown dropdown-animated scale-left">
                                        <a class="text-gray font-size-18" href="javascript:void(0);" data-toggle="dropdown">
                                            <i class="anticon anticon-ellipsis"></i>
                                        </a>
                                        <div class="dropdown-menu">
                                            <button class="dropdown-item" type="button">
                                                <i class="anticon anticon-eye"></i>
                                                <span class="m-l-10">View</span>
                                            </button>
                                            <button class="dropdown-item" type="button">
                                                <i class="anticon anticon-download"></i>
                                                <span class="m-l-10">Download</span>
                                            </button>
                                            <button class="dropdown-item" type="button">
                                                <i class="anticon anticon-delete"></i>
                                                <span class="m-l-10">Remove</span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
                            
                            
    </div>
    <!-- Content Wrapper END -->

    <!-- Footer START -->
    <footer class="footer">
        <div class="footer-content">
            <p class="m-b-0">Copyright © 2019 Theme_Nate. All rights reserved.</p>
            <span>
                <a href="" class="text-gray m-r-15">Term &amp; Conditions</a>
                <a href="" class="text-gray">Privacy &amp; Policy</a>
            </span>
        </div>
    </footer>
    <!-- Footer END -->
    <script>
        function redirectToLink(url) {
        window.location.href = url;
    }
    </script>
</div>
<!-- Page Container END -->


<!-- Core JS -->
