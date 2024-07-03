<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.User" %>
<%@ page import="model.Post" %>
<%@ page import="dal.UserDAO" %>
<%@ page import="dal.GenreDAO" %>
<%@ page import="model.TopViewedGenre"%>
<%@ page import="dal.PostDAO" %>
<%@ page import="java.util.ArrayList"%>
<div class="m-t-30">
    <div class="table-responsive">
        <form action="SearchUser" method="get" style="float: right">
            <i class="anticon anticon-search"></i> <input type="text" name="searchToken" style="width: 250px;height: 25px;font-size:15px" placeholder="Search a member">
            <input type="submit" style="visibility: hidden;" />
            <input type="hidden" value="a" name="flag">
        </form>
        <table class="table table-hover" style="border: solid 1px black">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Member</th>
                    <th>Points</th>
                    <th>Rank</th>
                    <th>Role</th>
                    <th style="max-width: 70px">Email</th>
                    <th colspan="2">Action</th>
                </tr>
            </thead>
            <tbody>
                <%int rank = 0;%>                                    
                <% UserDAO ud = new UserDAO();
                   PostDAO pd = new PostDAO();
                %>
                <c:choose>
                    <c:when test="${searchList == null}">
                        <% for(User x : pd.getAllUser()){%>    
                        <% String popupId = "popup-" + x.getUserId(); %>
                        <% String unbanPopupId = "unbanPopup-" + x.getUserId(); %>
                        <tr>

                            <td>#<%=++rank%></td>

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
                                        <%=x.getEmail()%>
                                    </div>
                                </div>
                            </td>
                            <%if (x.getRoleId() == 3){%>
                            <td> <a href="AdjustRole?Action=Promote&UserId=<%=x.getUserId()%>&Flag=a"><button class="btn btn-success">Promote</button></a></td>
                            <%}%>

                            <%if (x.getRoleId() == 2){%>
                            <td> <a href="AdjustRole?Action=Demote&UserId=<%=x.getUserId()%>&Flag=a"><button class="btn btn-danger">Demote</button></a></td>
                            <%}%>

                            <%if (x.getRoleId() == 1){%>
                            <td>N/A</td>
                            <%}%>

                            <% if(x.getStatus().equals("active") && x.getRoleId() != 1){
                            %>

                            <td><button class="btn btn-danger" onclick="togglePopup('<%=popupId%>')">Ban</button> </td>                 
                            <div class="popup" id="<%=popupId%>">
                                <div class="overlay"></div>
                                <div class="content">
                                    <div class="close-btn" onclick="togglePopup('<%=popupId%>')">&times;</div>
                                    <h1>Ban Reason</h1>
                                    <p>for: <%=x.getUsername()%></p>
                                    <form id="myForm" action="AdjustStatus" method="post">
                                        <input type="text" name="banReason" id="newUsername" placeholder="ban reason" required> 
                                        <br><br>
                                        <label for="time_interval">Choose a time interval:</label>
                                        <br>
                                        <select name="banTime" required>
                                            <option value="1hour">1 Hour</option>
                                            <option value="3hour">3 Hour</option>
                                            <option value="1day">1 Day</option>
                                            <option value="3day">3 Days</option>
                                            <option value="5day">5 Days</option>
                                            <option value="1week">1 Week</option>
                                            <option value="2week">2 Week</option>
                                        </select>
                                        <input type="hidden" name="userId" value="<%=x.getUserId()%>">                               
                                        <br>
                                        <hr>
                                        <input type="submit" value="Done">
                                    </form>
                                </div>
                            </div>
                            <%}%>
                            <% if(x.getStatus().equals("active") && x.getRoleId() == 1){
                            %>
                            <td>N/A</td>
                            <%}%>

                            <% if(x.getStatus().equals("deactive")){
                            %>
                            <td> 
                                <button class="btn btn-info" onclick="togglePopup('<%=unbanPopupId%>')">Unban</button>
                                <div class="popup2" id="<%=unbanPopupId%>">
                                    <div class="overlay"></div>
                                    <div class="content">
                                        <div class="close-btn" onclick="togglePopup('<%=unbanPopupId%>')">&times;</div>
                                        <h1>Unban</h1>
                                        <p>Unbanning: <%=x.getUsername()%></p>
                                        <form action="AdjustStatus" method="post">
                                            <p>Are you sure about unbanning <%=x.getUsername()%></p>                            
                                            <input type="hidden" name="UserId" value="<%=x.getUserId()%>">
                                            <input type="text" hidden name="Action" value="Unban">
                                            <br>
                                            <hr>
                                            <input type="submit" value="Yes">
                                        </form>
                                    </div>
                                </div>
                            </td>
                            <%}%>
                        </tr>
                        <%}%>
                    </c:when>
                    <c:otherwise>
                        <% ArrayList<User> searchList = (ArrayList<User>)request.getAttribute("searchList");
                         for(User x : searchList){%>    
                        <% String popupId = "popup-" + x.getUserId(); %>
                        <% String unbanPopupId = "unbanPopup-" + x.getUserId(); %>
                        <tr>
                            <td>#<%=++rank%></td>

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
                            <%if (x.getRoleId() == 3){%>
                            <td> <a href="AdjustRole?Action=Promote&UserId=<%=x.getUserId()%>&Flag=a"><button class="btn btn-success">Promote</button></a></td>
                            <%}%>

                            <%if (x.getRoleId() == 2){%>
                            <td> <a href="AdjustRole?Action=Demote&UserId=<%=x.getUserId()%>&Flag=a"><button class="btn btn-danger">Demote</button></a></td>
                            <%}%>

                            <%if (x.getRoleId() == 1){%>
                            <td>N/A</td>
                            <%}%>

                            <% if(x.getStatus().equals("active") && x.getRoleId() != 1){
                            %>

                            <td><button class="btn btn-danger" onclick="togglePopup('<%=popupId%>')">Ban</button> </td>                 
                            <div class="popup" id="<%=popupId%>">
                                <div class="overlay"></div>
                                <div class="content">
                                    <div class="close-btn" onclick="togglePopup('<%=popupId%>')">&times;</div>
                                    <h1>Ban Reason</h1>
                                    <p>for: <%=x.getUsername()%></p>
                                    <form action="AdjustStatus" method="post">
                                        <input type="text" name="banReason" id="newUsername" placeholder="ban reason" required> 
                                        <br><br>
                                        <label for="time_interval">Choose a time interval:</label>
                                        <br>
                                        <select name="banTime" required>
                                            <option value="1hour">1 Hour</option>
                                            <option value="3hour">3 Hour</option>
                                            <option value="1day">1 Day</option>
                                            <option value="3day">3 Days</option>
                                            <option value="5day">5 Days</option>
                                            <option value="1week">1 Week</option>
                                            <option value="2week">2 Week</option>
                                        </select>
                                        <input type="hidden" name="userId" value="<%=x.getUserId()%>">
                                        <br>
                                        <hr>
                                        <input type="submit" value="Done">
                                    </form>
                                </div>
                            </div>
                            <%}%>
                            <% if(x.getStatus().equals("active") && x.getRoleId() == 1){
                            %>
                            <td>N/A</td>
                            <%}%>

                            <% if(x.getStatus().equals("deactive")){
                            %>
                            <td> 
                                <button class="btn btn-info" onclick="togglePopup('<%=unbanPopupId%>')">Unban</button>
                                <div class="popup2" id="<%=unbanPopupId%>">
                                    <div class="overlay"></div>
                                    <div class="content">
                                        <div class="close-btn" onclick="togglePopup('<%=unbanPopupId%>')">&times;</div>
                                        <h1>Unban</h1>
                                        <p>Unbanning: <%=x.getUsername()%></p>
                                        <form action="AdjustStatus" method="post">
                                            <p>Are you sure about unbanning <%=x.getUsername()%></p>                            
                                            <input type="hidden" name="UserId" value="<%=x.getUserId()%>">
                                            <input type="text" hidden name="Action" value="Unban">
                                            <br>
                                            <hr>
                                            <input type="submit" value="Yes">
                                        </form>
                                    </div>
                                </div>
                            </td>
                            <%}%>
                        </tr>
                        <%}%>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div> 
    <script>
        function togglePopup(id) {
            document.getElementById(id).classList.toggle("active");
        }
    </script>
</div>

