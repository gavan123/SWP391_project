<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="p" scope="request" class="dal.PostDAO" />

<div class="row pb-6">


    <div class="col-lg-12">

        <div class="card mb-1 rounded-2 border-0">
            <div class="card-header">
                <h1 class="card-title text-uppercase text-center">New Post</h1>
            </div>
        </div>

        <c:forEach var="post" items="${allPosts}">
            <div class="card mb-2 rounded-2">
                <div class="card-body">
                    <h2 class="card-title">
                        <a href="postDetail?postId=${post.postId}" />${post.title}</a>
                    </h2>
                    <img class="float-left posts-img img-thumbnail mr-2" src="${pageContext.request.contextPath}/images/game/${post.image}" onerror="this.src='assets/images/others/product-3.jpg'">
                    <p class="card-text">${post.content}</p>
                </div>
            </div>
        </c:forEach>

        <div class="card mb-2 rounded-2">
            <div class="card-body">
                <h2 class="card-title"> 
                    <a href="//vidian.vn/chi-tiet/de-ba-tram-thien">Đế Bá: Trầm Thiên</a>
                </h2>
                <img class="float-left posts-img img-thumbnail mr-2" src="////vidian.vn/img-thumbnail/img-thumbnail-1717903641280.jpg" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'">
                <p class="card-text">Đế Bá: Trầm Thiên&nbsp;là&nbsp;Cứu Cực Tiên&nbsp;và được ca ngợi là&nbsp;Đệ Nhất Tiên Nhân&nbsp;của&nbsp;Thiên Cảnh. Tuy cũng có những tồn tại mạnh hơn như&nbsp;An Nhiên,&nbsp;Bạch Tuộc,&nbsp;Ẩn Tiên&nbsp;...</p>
            </div>
        </div>



        <!-- Pagination -->
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link" href="?page=1">First</a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="?page=${currentPage - 1}">Previous</a>
                    </li>
                </c:if>

                <!-- Current page and surrounding pages -->
                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${currentPage - 2}">${currentPage - 2}</a>
                    </li>
                </c:if>

                <li class="page-item active">
                    <a class="page-link" href="?page=${currentPage}">${currentPage}</a>
                </li>

                <c:if test="${currentPage < totalPages}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${currentPage + 2}">${currentPage + 2}</a>
                    </li>
                </c:if>

                <c:if test="${currentPage < totalPages}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${currentPage + 1}">Next</a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="?page=${totalPages}">Last</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>

</div>
