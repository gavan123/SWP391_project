<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="p" scope="request" class="dal.PostDAO" />

<div class="row pb-6">
    <div class="col-lg-12">

        <div class="card mb-1 rounded-2 border-0">
            <div class="card-header">
                <h1 class="card-title text-uppercase text-center">MỤC Q&amp;A</h1>
            </div>
        </div>

        <!-- Start forEach loop to iterate through posts -->
        <c:forEach var="post" items="${p.getAllPostsByCategory(10)}">
            <div class="card mb-2 rounded-2" style="border-color:aqua;border-style:double;border-width:medium">
                <div class="card-body">
                    <ul class="list-group">
                        <li class="list-group-item d-flex justify-content-between align-items-center" style="background:none;border:none">
                            <div class="col-10 text-truncate font-weight-bold">
                                <a href="${post.source}">${post.content}</a>
                            </div>
                            <!-- Display the comment count -->
                            <span class="badge badge-primary badge-pill">
                                <i class="far fa-comment-dots fa-lg"></i> ${post.commentCount}
                            </span>
                        </li>
                    </ul>
                </div>
            </div>
        </c:forEach>
        <!-- End forEach loop -->

    </div>

    <!-- pagination -->
    <div class="mx-auto">

        <nav>
            <ul class="pagination" style="margin-top: 2rem;">

                <!-- FIRST ITEM -->

                <li class="page-item disabled">
                    <a class="page-link" href="/n/qa/1">First</a>
                </li>


                <!-- ITEMS  -->





                <li class="page-item active">
                    <a class="page-link" href="/n/qa/1/">
                        1
                    </a>
                </li>
                <!-- LAST ITEM -->
                <li class="page-item disabled">
                    <a class="page-link" href="#">
                        Last
                    </a>
                </li>

            </ul>
        </nav>

    </div>

</div>
