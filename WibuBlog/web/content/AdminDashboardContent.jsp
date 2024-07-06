<%@ page import="model.User" %>
<%@ page import="model.Post" %>
<%@ page import="dal.UserDAO" %>
<%@ page import="dal.GenreDAO" %>
<%@ page import="model.TopViewedGenre"%>
<%@ page import="dal.PostDAO" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dal.TicketDAO" %>
<%@ page import="model.Ticket" %>
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
                                <form action="SearchUser" method="get">
                                    <i class="anticon anticon-search"></i> <input type="text" name="searchToken" style="width: 250px;height: 25px;font-size:15px" placeholder="Search a member">
                                    <input type="submit" style="visibility: hidden;" />
                                </form>
                            </div>
                        </div>
                        <div class="m-t-30">
                            <div class="table-responsive">
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>Rank</th>
                                            <th>Member</th>
                                            <th>Points</th>
                                            <th>Rank</th>
                                            <th>Role</th>
                                            <th style="max-width: 70px">Posts Last 3 Days</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%int rank = 0;%>                                    
                                        <% UserDAO ud = new UserDAO();
                                           PostDAO pd = new PostDAO();
                                        %>
                                        <c:choose>
                                            <c:when test="${searchList == null}">
                                                <% for(User x : pd.getTop10UserByPoint()){%>    

                                                <tr>

                                                    <td>#<%=++rank%></td>

                                                    <td>
                                                        <div class="media align-items-center">
                                                            <div class="avatar avatar-image rounded">
                                                                <img src="${pageContext.request.contextPath}/images/avatar/<%=x.getProfilePhoto()%>"  onerror="this.src='assets/images/others/product-3.jpg'" alt="${topUser.username}">
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
                                                    <%if (x.getRoleId() == 3){%>
                                                    <td> <a href="AdjustRole?Action=Promote&UserId=<%=x.getUserId()%>"><button class="btn btn-success">Promote</button></a></td>
                                                    <%}%>
                                                    <%if (x.getRoleId() == 2){%>
                                                    <td> <a href="AdjustRole?Action=Demote&UserId=<%=x.getUserId()%>"><button class="btn btn-danger">Demote</button></a></td>
                                                    <%}%>
                                                    <%if (x.getRoleId() == 1){%>
                                                    <td>N/A</td>
                                                    <%}%>
                                                </tr>
                                                <%}%>
                                            </c:when>
                                            <c:otherwise>
                                                <% ArrayList<User> searchList = (ArrayList<User>)request.getAttribute("searchList");
                                                 for(User x : searchList){%>      
                                                <tr>
                                                    <td>#</td>
                                                    <td>
                                                        <div class="media align-items-center">
                                                            <div class="avatar avatar-image rounded">
                                                                <img src="${pageContext.request.contextPath}/images/avatar/<%=x.getProfilePhoto()%>"  onerror="this.src='assets/images/others/product-3.jpg'" alt="${topUser.username}">
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
                                                    <%if (x.getRoleId() == 3){%>
                                                    <td> <td> <a href="AdjustRole?Action=Promote&UserId=<%=x.getUserId()%>"><button class="btn btn-success">Promote</button></a></td></td>
                                                    <%}%>
                                                    <%if (x.getRoleId() == 2){%>
                                                    <td> <td> <a href="AdjustRole?Action=Demote&UserId=<%=x.getUserId()%>"><button class="btn btn-success">Promote</button></a></td></td>
                                                    <%}%>
                                                    <%if (x.getRoleId() == 1){%>
                                                    <td>N/A</td>
                                                    <%}%>
                                                </tr>
                                                <%}%>
                                            </c:otherwise>
                                        </c:choose>
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
                            <h5 class="m-b-0">Ticket</h5>
                            <div>
                                <a href="Ticket?Dashboard=a" class="btn btn-sm btn-default">View All
                                </a>
                            </div>
                        </div>
                        <div class="m-t-30">
                            <%  TicketDAO td = new TicketDAO();
                                %>
                            <div class="m-b-25">
                                <div class="d-flex align-items-center justify-content-between">
                                    <!--start-->
                                    <div class="m-l-10" >
                                        
                                        <span>Pending ticket: <%=td.getAllPendingTicket()%></span>
                                        <hr>
                                        <br>
                                        <span>Resolved Ticket:  <%=td.getAllResolvedTicket()%></span>
                                       
                                    </div>                                
                                    <div class="scale-left">

                              
                                    </div>
                                </div>  
                                <br><hr>   
                            </div>                         
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
                            <h5 class="m-b-0">Popular Genre</h5>
                           <!-- <div>
                                <a href="ViewAll?Data=CategoryStat" class="btn btn-sm btn-default">View All</a>
                            </div>-->
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
                                <a href="ViewAll?Data=Member" class="btn btn-sm btn-default">View All</a>
                            </div>
                        </div>
                        <div class="m-t-30">
                              <% for(User x: ud.getTop10User()){%>
                            <div class="m-b-25">
                                 <div class="d-flex align-items-center justify-content-between">
                                    <!--start-->
                                    <div class="avatar avatar-image rounded">
                                        <img src="${pageContext.request.contextPath}/images/avatar/<%=x.getProfilePhoto()%>"  onerror="this.src='assets/images/others/product-3.jpg'" alt="${topUser.username}">
                                    </div>
                                    <div>
                                        <% if(x.getUsername().length() < 12){ %>
                                        <p><%=x.getUsername()%></p>
                                        <%}%>
                                        <% if(x.getUsername().length() >= 12){%>
                                        <p><%=pd.trimPostTitle(x.getUsername())%></p>
                                        <%}%>
                                    </div>
                                    <div class="m-l-10" >
                                        <i class="anticon anticon-profile"></i>
                                        <span class="m-l-10">Point(<%=x.getPoint()%>)</span>   

                                    </div>                                
                                   
                                </div>  
                            </div>
                                        <%}%>
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
