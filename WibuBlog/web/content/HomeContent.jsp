<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
<jsp:useBean id="p" scope="request" class="dal.PostDAO" />

<c:choose>
    <c:when test="${empty user}">
        <form action="login" method="get" class="form-button m-2">
            <button type="submit" class="btn btn-primary">Login</button>
        </form>
    </c:when>
    <c:otherwise>
        <h2>Welcome, <c:out value="${user.username}" />!</h2>
        <p>Id: <c:out value="${user.userId}" /></p>
        <p>Full Name: <c:out value="${user.fullName}" /></p>
        <p>Point: <c:out value="${user.point}" /></p>
        <p>Phone: <c:out value="${user.phoneNumber}" /></p>
        <form action="profile" method="post" class="form-button m-2">
            <input type="hidden" name="userId" value="<c:out value='${user.userId}' />">
            <button type="submit" class="btn btn-primary">My Profile</button>
        </form>
        <form action="changePassword" method="get" class="form-button m-2">
            <button type="submit" class="btn btn-primary">Change Password</button>
        </form>
        <form id="deleteAccountForm" action="deleteAccount" method="post" class="form-button m-2">
            <button type="button" class="btn btn-primary" onclick="confirmDelete()">Delete Account</button>
        </form>
        <form action="logout" method="get" class="form-button m-2">
            <button type="submit" class="btn btn-primary">Logout</button>
        </form>
    </c:otherwise>
</c:choose>

<div class="card mb-2 rounded-5 border-0">
    <div class="card-header">
        <h3 class="card-header-h3 fs-16">
            Q &amp; A
            <span class="float-right text-uppercase">
                <a href="question">Xem thêm</a>
            </span>
        </h3>
    </div>
    <div class="card-body">
        <ul class="list-group list-group-flush">
            <c:forEach var="post" items="${p.getLimitedPostsByCategory(5,10)}">
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <div class="col-10 text-truncate font-weight-bold">
                        <a href="${post.source}">${post.content}</a>
                    </div>
                    <span class="badge badge-primary badge-pill">
                        <i class="far fa-comment-dots fa-lg"></i>
                        <span style="font-size: larger;">${post.view}</span> 
                    </span>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

<div class="card mb-2 rounded-3 border-0">
    <div class="card-header" id="top"><h3 class="card-header-h3 fs-16">BÀI MỚI<span class="float-right text-uppercase"><a href="//vidian.vn/n/bai-moi/1">Xem thêm</a></span></h3></div>
    <div class="card-body">

        <!-- Swiper slider -->

        <div class="swiper-container swiper-container-initialized swiper-container-horizontal" style="min-height:220px">
            <div class="swiper-wrapper" id="swiper-wrapper-c19fcdab835924ac" aria-live="polite" style="transform: translate3d(-603.333px, 0px, 0px); transition-duration: 0ms;">



                <div class="swiper-slide g_thumb" role="group" aria-label="1 / 12" style="width: 135.833px; margin-right: 15px;">
                    <span class="badge badge-info posts-badge">35 <i class="mdi mdi-eye"></i></span>

                    <span class="badge badge-danger posts-badge-2 text-truncate">Nhiệt huyết</span>

                    <a href="//vidian.vn/chi-tiet/review-tieu-thuyet-truong-sinh-tu-bang-mon-tu-si-bat-dau-con-duong-tu-tien-bien-kho-roi" id="new_post0">

                        <img class="swiper-img mx-auto d-block rounded-1" loading="lazy" src="//vidian.vn/img-thumbnail/img-thumbnail-1717517353711.jpg" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'" alt="Review tiểu thuyết Trường Sinh, Từ Bàng Môn Tu Sĩ Bắt Đầu: con đường tu tiên biến khó rồi!">

                        <span class="menu-text-sm text-center mt-2 text-truncate-2">Review tiểu thuyết Trường Sinh, Từ Bàng Môn Tu Sĩ Bắt Đầu: con đường tu tiên biến khó rồi!</span>
                    </a>
                </div>


                <div class="swiper-slide g_thumb" role="group" aria-label="2 / 12" style="width: 135.833px; margin-right: 15px;">
                    <span class="badge badge-info posts-badge">42 <i class="mdi mdi-eye"></i></span>

                    <a href="//vidian.vn/chi-tiet/bo-phim-ngan-thieu-nien-ca-hanh-mua-thu-nhat-lap-ho-so-dao-dien-dong-huy-ba-mua-co-the-quay-xong-tat-ca-noi-dung-cot-truyen-khong" id="new_post1">

                        <img class="swiper-img mx-auto d-block rounded-1" loading="lazy" src="//vidian.vn/img-thumbnail/img-thumbnail-1717515593644.jpg" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'" alt="Bộ phim ngắn Thiếu Niên Ca Hành mùa thứ nhất lập hồ sơ, đạo diễn Đồng Huy, ba mùa có thể quay xong tất cả nội dung cốt truyện không?">

                        <span class="menu-text-sm text-center mt-2 text-truncate-2">Bộ phim ngắn Thiếu Niên Ca Hành mùa thứ nhất lập hồ sơ, đạo diễn Đồng Huy, ba mùa có thể quay xong tất cả nội dung cốt truyện không?</span>
                    </a>
                </div>


                <div class="swiper-slide g_thumb" role="group" aria-label="3 / 12" style="width: 135.833px; margin-right: 15px;">
                    <span class="badge badge-info posts-badge">26 <i class="mdi mdi-eye"></i></span>

                    <a href="//vidian.vn/chi-tiet/sach-moi-tien-cong-khai-vat-cua-co-chan-nhan-dat-truoc-nam-van-sau-xep-hang-dat-truoc-thu-bay-toan-tram-qidian" id="new_post2">

                        <img class="swiper-img mx-auto d-block rounded-1" loading="lazy" src="//vidian.vn/img-thumbnail/img-thumbnail-1717513857367.webp" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'" alt="Sách mới Tiên Công Khai Vật của Cổ Chân Nhân đặt trước năm vạn sáu, xếp hạng đặt trước thứ bảy toàn trạm Qidian">

                        <span class="menu-text-sm text-center mt-2 text-truncate-2">Sách mới Tiên Công Khai Vật của Cổ Chân Nhân đặt trước năm vạn sáu, xếp hạng đặt trước thứ bảy toàn trạm Qidian</span>
                    </a>
                </div>


                <div class="swiper-slide g_thumb swiper-slide-prev" role="group" aria-label="4 / 12" style="width: 135.833px; margin-right: 15px;">
                    <span class="badge badge-info posts-badge">1201 <i class="mdi mdi-eye"></i></span>

                    <a href="//vidian.vn/chi-tiet/tien-nghich-no-luc-cua-vuong-lam-vi-tan-cap-nguyen-anh-cung-ly-mo-uyen-la-that-tam-hay-la-loi-dung" id="new_post3">

                        <img class="swiper-img mx-auto d-block rounded-1" loading="lazy" src="//vidian.vn/img-thumbnail/img-thumbnail-1717432669404.jpg" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'" alt="Tiên Nghịch: Nỗ lực của Vương Lâm vì tấn cấp Nguyên Anh, cùng Lý Mộ Uyển là thật tâm hay là lợi dụng?">

                        <span class="menu-text-sm text-center mt-2 text-truncate-2">Tiên Nghịch: Nỗ lực của Vương Lâm vì tấn cấp Nguyên Anh, cùng Lý Mộ Uyển là thật tâm hay là lợi dụng?</span>
                    </a>
                </div>


                <div class="swiper-slide g_thumb swiper-slide-active" role="group" aria-label="5 / 12" style="width: 135.833px; margin-right: 15px;">
                    <span class="badge badge-info posts-badge">584 <i class="mdi mdi-eye"></i></span>

                    <span class="badge badge-danger posts-badge-2 text-truncate">Nhiệt huyết</span>

                    <a href="//vidian.vn/chi-tiet/dau-pha-thuong-khung-tieu-viem-ngam-minh-trong-huyet-dam-manh-co-nao-thu-tu-luu-ly-hoa-lien-don-cung-ran-cuong-hoa-doc" id="new_post4">

                        <img class="swiper-img mx-auto d-block rounded-1" loading="lazy" src="//vidian.vn/img-thumbnail/img-thumbnail-1717431018713.jpg" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'" alt="Đấu Phá Thương Khung: Tiêu Viêm ngâm mình trong huyết đàm mạnh cỡ nào? Thủ Tú Lưu Ly Hỏa Liên Độn cứng rắn, cương hỏa độc!">

                        <span class="menu-text-sm text-center mt-2 text-truncate-2">Đấu Phá Thương Khung: Tiêu Viêm ngâm mình trong huyết đàm mạnh cỡ nào? Thủ Tú Lưu Ly Hỏa Liên Độn cứng rắn, cương hỏa độc!</span>
                    </a>
                </div>


                <div class="swiper-slide g_thumb swiper-slide-next" role="group" aria-label="6 / 12" style="width: 135.833px; margin-right: 15px;">
                    <span class="badge badge-info posts-badge">351 <i class="mdi mdi-eye"></i></span>

                    <span class="badge badge-danger posts-badge-2 text-truncate">Nhiệt huyết</span>

                    <a href="//vidian.vn/chi-tiet/qidian-lai-tang-them-ba-quyen-sach-moi-dat-duoc-van-mua-thoi-dai-hoang-kim-cua-thuy-giac-hoi-bien-bach-dat-hang-dau-tien-gan-hai-muoi-tam-nghin" id="new_post5">

                        <img class="swiper-img mx-auto d-block rounded-1" loading="lazy" src="//vidian.vn/img-thumbnail/img-thumbnail-1717428463003.webp" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'" alt="Qidian lại tăng thêm ba quyển sách mới đạt được vạn mua, Thời Đại Hoàng Kim của Thuỵ Giác Hội Biến Bạch đặt hàng đầu tiên gần hai mươi tám nghìn">

                        <h2 class="menu-text-sm text-center mt-2 text-truncate-2">Qidian lại tăng thêm ba quyển sách mới đạt được vạn mua, Thời Đại Hoàng Kim của Thuỵ Giác Hội Biến Bạch đặt hàng đầu tiên gần hai mươi tám nghìn</h2>
                    </a>
                </div>


                <div class="swiper-slide g_thumb" role="group" aria-label="7 / 12" style="width: 135.833px; margin-right: 15px;">
                    <span class="badge badge-info posts-badge">227 <i class="mdi mdi-eye"></i></span>

                    <span class="badge badge-danger posts-badge-2 text-truncate">Nhiệt huyết</span>

                    <a href="//vidian.vn/chi-tiet/review-tieu-thuyet-chao-buoi-sang-nguoi-lam-cong-tam-quoc" id="new_post6">

                        <img class="swiper-img mx-auto d-block rounded-1" loading="lazy" src="//vidian.vn/img-thumbnail/img-thumbnail-1717421278323.jpg" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'" alt="Review tiểu thuyết Chào Buổi Sáng, Người Làm Công Tam Quốc!">

                        <h2 class="menu-text-sm text-center mt-2 text-truncate-2">Review tiểu thuyết Chào Buổi Sáng, Người Làm Công Tam Quốc!</h2>
                    </a>
                </div>


                <div class="swiper-slide g_thumb" role="group" aria-label="8 / 12" style="width: 135.833px; margin-right: 15px;">
                    <span class="badge badge-info posts-badge">199 <i class="mdi mdi-eye"></i></span>

                    <span class="badge badge-danger posts-badge-2 text-truncate">Sảng văn</span>

                    <a href="//vidian.vn/chi-tiet/tien-tu-xin-nghe-ta-giai-thich-dan-mang-danh-gia-the-nao" id="new_post7">

                        <img class="swiper-img mx-auto d-block rounded-1" loading="lazy" src="//vidian.vn/img-thumbnail/img-thumbnail-1717482901014.jpg" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'" alt="Tiên Tử, Xin Nghe Ta Giải Thích: Dân mạng đánh giá thế nào?">

                        <h2 class="menu-text-sm text-center mt-2 text-truncate-2">Tiên Tử, Xin Nghe Ta Giải Thích: Dân mạng đánh giá thế nào?</h2>
                    </a>
                </div>


                <div class="swiper-slide g_thumb" role="group" aria-label="9 / 12" style="width: 135.833px; margin-right: 15px;">
                    <span class="badge badge-info posts-badge">3336 <i class="mdi mdi-eye"></i></span>

                    <span class="badge badge-danger posts-badge-2 text-truncate">Nhiệt huyết</span>

                    <a href="//vidian.vn/chi-tiet/de-ba-bo-de-lao-to-lao-thu-yeu" id="new_post8">

                        <img class="swiper-img mx-auto d-block rounded-1" loading="lazy" src="//vidian.vn/img-thumbnail/img-thumbnail-1717241665798.jpg" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'" alt="Đế Bá: Bồ Đề Lão Tổ | Lão Thụ Yêu">

                        <h2 class="menu-text-sm text-center mt-2 text-truncate-2">Đế Bá: Bồ Đề Lão Tổ | Lão Thụ Yêu</h2>
                    </a>
                </div>


                <div class="swiper-slide g_thumb" role="group" aria-label="10 / 12" style="width: 135.833px; margin-right: 15px;">
                    <span class="badge badge-info posts-badge">3532 <i class="mdi mdi-eye"></i></span>

                    <span class="badge badge-danger posts-badge-2 text-truncate">Nhiệt huyết</span>

                    <a href="//vidian.vn/chi-tiet/tien-cong-khai-quat-cua-co-chan-nhan-truoc-khi-len-ke-dat-duoc-500-fan-minh-chu-cat-ngu-quyen-tu-luyen-bat-dau-tu-don-gian-hoa-cong-phap-doi-ten" id="new_post9">

                        <img class="swiper-img mx-auto d-block rounded-1" loading="lazy" src="//vidian.vn/img-thumbnail/img-thumbnail-1717044673511.webp" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'" alt="Tiên Công Khai Quật của Cổ Chân Nhân trước khi lên kệ đạt được 500 fan Minh chủ, Cật Ngư quyển Tu Luyện Bắt Đầu Từ Đơn Giản Hóa Công Pháp đổi tên">

                        <h2 class="menu-text-sm text-center mt-2 text-truncate-2">Tiên Công Khai Quật của Cổ Chân Nhân trước khi lên kệ đạt được 500 fan Minh chủ, Cật Ngư quyển Tu Luyện Bắt Đầu Từ Đơn Giản Hóa Công Pháp đổi tên</h2>
                    </a>
                </div>


                <div class="swiper-slide g_thumb" role="group" aria-label="11 / 12" style="width: 135.833px; margin-right: 15px;">
                    <span class="badge badge-info posts-badge">2251 <i class="mdi mdi-eye"></i></span>

                    <span class="badge badge-danger posts-badge-2 text-truncate">Nhiệt huyết</span>

                    <a href="//vidian.vn/chi-tiet/de-ba-trieu-dai-chuy" id="new_post10">

                        <img class="swiper-img mx-auto d-block rounded-1" loading="lazy" src="//vidian.vn/img-thumbnail/img-thumbnail-1717056744572.jpg" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'" alt="Đế Bá: Triệu Đại Chùy">

                        <h2 class="menu-text-sm text-center mt-2 text-truncate-2">Đế Bá: Triệu Đại Chùy</h2>
                    </a>
                </div>


                <div class="swiper-slide g_thumb" role="group" aria-label="12 / 12" style="width: 135.833px; margin-right: 15px;">
                    <span class="badge badge-info posts-badge">3728 <i class="mdi mdi-eye"></i></span>

                    <span class="badge badge-danger posts-badge-2 text-truncate">Nhiệt huyết</span>

                    <a href="//vidian.vn/chi-tiet/de-cu-vai-quyen-tieu-thuyet-ky-huyen-ma-phap-do-thi-di-nang-khoa-huyen-tuong-lai-da-hoan-thanh" id="new_post11">

                        <img class="swiper-img mx-auto d-block rounded-1" loading="lazy" src="//vidian.vn/img-thumbnail/img-thumbnail-1716886092053.jpg" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'" alt="Đề cử vài quyển tiểu thuyết kỳ huyễn ma pháp, đô thị dị năng, khoa huyễn tương lai đã hoàn thành">

                        <h2 class="menu-text-sm text-center mt-2 text-truncate-2">Đề cử vài quyển tiểu thuyết kỳ huyễn ma pháp, đô thị dị năng, khoa huyễn tương lai đã hoàn thành</h2>
                    </a>
                </div>

            </div>

            <!-- Add Pagination -->
            <div class="swiper-pagination swiper-pagination-clickable swiper-pagination-bullets">

            </div>
            <span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span>

            <!-- End Swiper slider -->

        </div>
    </div>
</div>
<script>
    function confirmDelete() {
        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.isConfirmed) {
                document.getElementById('deleteAccountForm').submit();
            }
        })
    }
</script>