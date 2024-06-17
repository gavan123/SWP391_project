<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="p" scope="request" class="dal.PostDAO" />
<jsp:useBean id="c" scope="request" class="dal.CategoryDAO" />
<div class="row pb-6">


    <div class="col-lg-12">

        <div class="card mb-1 rounded-2 border-0">
            <div class="card-header">
                <h1 class="card-title text-uppercase text-center">
                    <c:choose>
                        <c:when test="${category != null}">
                            ${category.name}
                        </c:when>
                        <c:otherwise>
                            Category Not Found
                        </c:otherwise>
                    </c:choose>
                </h1>
            </div>
        </div>

        <div class="card mb-2 rounded-2">
            <div class="card-body">
                <h2 class="card-title"> 
                    <a href="//vidian.vn/chi-tiet/bang-xep-hang-nguyet-phieu-qidian-thang-5-nam-2024">Bảng xếp hạng nguyệt phiếu Qidian tháng 5 năm 2024</a>
                </h2>
                <img class="float-left posts-img img-thumbnail mr-2" src="////vidian.vn/img-thumbnail/img-thumbnail-1718008088760.webp" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'">
                <p class="card-text">Bảng xếp hạng nguyệt phiếu tháng 5 xuất hiện nhiều tiểu thuyết đô thị, thể loại cao võ đoạt quán quân nguyệt phiếu.</p>
            </div>
        </div>

        <div class="card mb-2 rounded-2">
            <div class="card-body">
                <h2 class="card-title"> 
                    <a href="//vidian.vn/chi-tiet/de-ba-tram-thien">Đế Bá: Trầm Thiên</a>
                </h2>
                <img class="float-left posts-img img-thumbnail mr-2" src="////vidian.vn/img-thumbnail/img-thumbnail-1717903641280.jpg" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'">
                <p class="card-text">Đế Bá: Trầm Thiên&nbsp;là&nbsp;Cứu Cực Tiên&nbsp;và được ca ngợi là&nbsp;Đệ Nhất Tiên Nhân&nbsp;của&nbsp;Thiên Cảnh. Tuy cũng có những tồn tại mạnh hơn như&nbsp;An Nhiên,&nbsp;Bạch Tuộc,&nbsp;Ẩn Tiên&nbsp;...</p>
            </div>
        </div>



    </div>
</div>
