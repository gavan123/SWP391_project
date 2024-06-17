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

        <div class="card mb-2 rounded-2">
            <div class="card-body">
                <h2 class="card-title"> 
                    <a href="//vidian.vn/chi-tiet/tieu-diem-nhan-vat-tong-thu-hang">Tiêu điểm nhân vật - Tống Thư Hàng</a>
                </h2>
                <img class="float-left posts-img img-thumbnail mr-2" src="https:////vidian.vn/img-thumbnail/default-book.png" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'">
                <p class="card-text">Tống Thư Hàng nhân vật chính</p>
            </div>
        </div>

        <div class="card mb-2 rounded-2">
            <div class="card-body">
                <h2 class="card-title"> 
                    <a href="//vidian.vn/chi-tiet/review-tieu-thuyet-nua-doi-sau-cua-ta-suon-xam-thom-diu-trong-bong-co-kim">Review tiểu thuyết Nửa Đời Sau Của Ta: sườn xám thơm dịu, trong bông có kim</a>
                </h2>
                <img class="float-left posts-img img-thumbnail mr-2" src="////vidian.vn/img-thumbnail/img-thumbnail-1717771378712.png" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'">
                <p class="card-text">Bài văn chương này mang đến cho ta một cảm giác, giống như là trong quán trà đắt tiền có mỹ nhân mặc sườn xám thơm ngát rót cho ngươi một chén trà, sau đó...</p>
            </div>
        </div>

        <div class="card mb-2 rounded-2">
            <div class="card-body">
                <h2 class="card-title"> 
                    <a href="//vidian.vn/chi-tiet/lay-gia-thien-lam-vi-du-phan-tich-trong-hoat-hinh-tu-chan-cai-gi-moi-la-quan-trong-nhat">Lấy Già Thiên làm ví dụ, phân tích trong hoạt hình tu chân, cái gì mới là quan trọng nhất?</a>
                </h2>
                <img class="float-left posts-img img-thumbnail mr-2" src="////vidian.vn/img-thumbnail/img-thumbnail-1717773066371.jpg" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'">
                <p class="card-text">Trong những hoạt động tu chân, thực lực thường được coi là tiêu chuẩn quan trọng của địa vị và tôn nghiêm. Gần đây, một tin đồn có quan hệ tới Tử Nguyệt...</p>
            </div>
        </div>

        <div class="card mb-2 rounded-2">
            <div class="card-body">
                <h2 class="card-title"> 
                    <a href="//vidian.vn/chi-tiet/review-tieu-thuyet-truong-sinh-tu-bang-mon-tu-si-bat-dau-con-duong-tu-tien-bien-kho-roi">Review tiểu thuyết Trường Sinh, Từ Bàng Môn Tu Sĩ Bắt Đầu: con đường tu tiên biến khó rồi!</a>
                </h2>
                <img class="float-left posts-img img-thumbnail mr-2" src="////vidian.vn/img-thumbnail/img-thumbnail-1717517353711.jpg" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'">
                <p class="card-text">Trong một thời đại mà việc cập nhật văn học mạng liên tục tăng tốc, các nhà phê bình nguyên sinh đặt mình vào hiện trường văn học mạng là một sức mạnh tươi...</p>
            </div>
        </div>

        <div class="card mb-2 rounded-2">
            <div class="card-body">
                <h2 class="card-title"> 
                    <a href="//vidian.vn/chi-tiet/bo-phim-ngan-thieu-nien-ca-hanh-mua-thu-nhat-lap-ho-so-dao-dien-dong-huy-ba-mua-co-the-quay-xong-tat-ca-noi-dung-cot-truyen-khong">Bộ phim ngắn Thiếu Niên Ca Hành mùa thứ nhất lập hồ sơ, đạo diễn Đồng Huy, ba mùa có thể quay xong tất cả nội dung cốt truyện không?</a>
                </h2>
                <img class="float-left posts-img img-thumbnail mr-2" src="////vidian.vn/img-thumbnail/img-thumbnail-1717515593644.jpg" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'">
                <p class="card-text">Thiếu Niên Ca Hành sắp quay phim ngắn rồi! Trong thông tin quy hoạch lập hồ sơ thông qua chương trình kịch ngắn mạng trọng điểm toàn quốc tháng 4 năm 2024.</p>
            </div>
        </div>

        <div class="card mb-2 rounded-2">
            <div class="card-body">
                <h2 class="card-title"> 
                    <a href="//vidian.vn/chi-tiet/sach-moi-tien-cong-khai-vat-cua-co-chan-nhan-dat-truoc-nam-van-sau-xep-hang-dat-truoc-thu-bay-toan-tram-qidian">Sách mới Tiên Công Khai Vật của Cổ Chân Nhân đặt trước năm vạn sáu, xếp hạng đặt trước thứ bảy toàn trạm Qidian</a>
                </h2>
                <img class="float-left posts-img img-thumbnail mr-2" src="////vidian.vn/img-thumbnail/img-thumbnail-1717513857367.webp" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'">
                <p class="card-text">Thành tích của sách mới  Tiên Công Khai Vật  đã có, ta vẫn đi chụp màn hình.</p>
            </div>
        </div>

        <div class="card mb-2 rounded-2">
            <div class="card-body">
                <h2 class="card-title"> 
                    <a href="//vidian.vn/chi-tiet/tien-nghich-no-luc-cua-vuong-lam-vi-tan-cap-nguyen-anh-cung-ly-mo-uyen-la-that-tam-hay-la-loi-dung">Tiên Nghịch: Nỗ lực của Vương Lâm vì tấn cấp Nguyên Anh, cùng Lý Mộ Uyển là thật tâm hay là lợi dụng?</a>
                </h2>
                <img class="float-left posts-img img-thumbnail mr-2" src="////vidian.vn/img-thumbnail/img-thumbnail-1717432669404.jpg" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'">
                <p class="card-text">Trong Tiên Nghịch, Vương Lâm tinh thông cấm chế thượng cổ mà trở thành tiêu điểm chú ý của mọi người. Nhưng mà, con đường tu luyện của hắn cũng không phải thuận</p>
            </div>
        </div>

        <div class="card mb-2 rounded-2">
            <div class="card-body">
                <h2 class="card-title"> 
                    <a href="//vidian.vn/chi-tiet/dau-pha-thuong-khung-tieu-viem-ngam-minh-trong-huyet-dam-manh-co-nao-thu-tu-luu-ly-hoa-lien-don-cung-ran-cuong-hoa-doc">Đấu Phá Thương Khung: Tiêu Viêm ngâm mình trong huyết đàm mạnh cỡ nào? Thủ Tú Lưu Ly Hỏa Liên Độn cứng rắn, cương hỏa độc!</a>
                </h2>
                <img class="float-left posts-img img-thumbnail mr-2" src="////vidian.vn/img-thumbnail/img-thumbnail-1717431018713.jpg" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'">
                <p class="card-text">Ở Trung Châu, cuộc chiến tranh đoạt danh ngạch ngâm mình trong năm đại thiên kiêu đang tiến hành trong lửa nóng. Thiên Sơn huyết đầm, một chỗ tràn ngập thần bí </p>
            </div>
        </div>

        <div class="card mb-2 rounded-2">
            <div class="card-body">
                <h2 class="card-title"> 
                    <a href="//vidian.vn/chi-tiet/qidian-lai-tang-them-ba-quyen-sach-moi-dat-duoc-van-mua-thoi-dai-hoang-kim-cua-thuy-giac-hoi-bien-bach-dat-hang-dau-tien-gan-hai-muoi-tam-nghin">Qidian lại tăng thêm ba quyển sách mới đạt được vạn mua, Thời Đại Hoàng Kim của Thuỵ Giác Hội Biến Bạch đặt hàng đầu tiên gần hai mươi tám nghìn</a>
                </h2>
                <img class="float-left posts-img img-thumbnail mr-2" src="////vidian.vn/img-thumbnail/img-thumbnail-1717428463003.webp" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'">
                <p class="card-text">Hôm nay bài thứ nhất tới nói trước sách mới vạn mua.</p>
            </div>
        </div>

        <div class="card mb-2 rounded-2">
            <div class="card-body">
                <h2 class="card-title"> 
                    <a href="//vidian.vn/chi-tiet/review-tieu-thuyet-chao-buoi-sang-nguoi-lam-cong-tam-quoc">Review tiểu thuyết Chào Buổi Sáng, Người Làm Công Tam Quốc!</a>
                </h2>
                <img class="float-left posts-img img-thumbnail mr-2" src="////vidian.vn/img-thumbnail/img-thumbnail-1717421278323.jpg" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'">
                <p class="card-text">Trong nước xôn xao, dân sinh dày vò.... Kỳ thật Lục Huyền Ngư không hiểu lắm hàm nghĩa của tám chữ này.</p>
            </div>
        </div>

        <div class="card mb-2 rounded-2">
            <div class="card-body">
                <h2 class="card-title"> 
                    <a href="//vidian.vn/chi-tiet/tien-tu-xin-nghe-ta-giai-thich-dan-mang-danh-gia-the-nao">Tiên Tử, Xin Nghe Ta Giải Thích: Dân mạng đánh giá thế nào?</a>
                </h2>
                <img class="float-left posts-img img-thumbnail mr-2" src="////vidian.vn/img-thumbnail/img-thumbnail-1717482901014.jpg" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'">
                <p class="card-text">Tiên Tử, Xin Nghe Ta Giải Thích, được sáng tác bởi Di Thiên Đại Hạ, thể loại Tiên Hiệp, nhân vật chính vô sỉ., Trước mắt&nbsp;Tiên Tử, Xin Nghe Ta Giải Thích đã hơn </p>
            </div>
        </div>

        <div class="card mb-2 rounded-2">
            <div class="card-body">
                <h2 class="card-title"> 
                    <a href="//vidian.vn/chi-tiet/de-ba-bo-de-lao-to-lao-thu-yeu">Đế Bá: Bồ Đề Lão Tổ | Lão Thụ Yêu</a>
                </h2>
                <img class="float-left posts-img img-thumbnail mr-2" src="////vidian.vn/img-thumbnail/img-thumbnail-1717241665798.jpg" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'">
                <p class="card-text">Đế Bá: Lão Thụ Yêu có ngoại hiệu Bồ Đề Lão Tổ,&nbsp;&nbsp;là 1 vị&nbsp;Thủy Tổ&nbsp;sống bí ẩn ở&nbsp;Thánh Sơn. Khi xưa, hắn từng thua trước&nbsp;Viễn Hoang Thánh Sơn</p>
            </div>
        </div>

        <div class="card mb-2 rounded-2">
            <div class="card-body">
                <h2 class="card-title"> 
                    <a href="//vidian.vn/chi-tiet/tien-cong-khai-quat-cua-co-chan-nhan-truoc-khi-len-ke-dat-duoc-500-fan-minh-chu-cat-ngu-quyen-tu-luyen-bat-dau-tu-don-gian-hoa-cong-phap-doi-ten">Tiên Công Khai Quật của Cổ Chân Nhân trước khi lên kệ đạt được 500 fan Minh chủ, Cật Ngư quyển Tu Luyện Bắt Đầu Từ Đơn Giản Hóa Công Pháp đổi tên</a>
                </h2>
                <img class="float-left posts-img img-thumbnail mr-2" src="////vidian.vn/img-thumbnail/img-thumbnail-1717044673511.webp" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'">
                <p class="card-text">Hôm nay vẫn là một bài, nói đơn giản một chút tin tức nhìn thấy.</p>
            </div>
        </div>

        <div class="card mb-2 rounded-2">
            <div class="card-body">
                <h2 class="card-title"> 
                    <a href="//vidian.vn/chi-tiet/de-ba-trieu-dai-chuy">Đế Bá: Triệu Đại Chùy</a>
                </h2>
                <img class="float-left posts-img img-thumbnail mr-2" src="////vidian.vn/img-thumbnail/img-thumbnail-1717056744572.jpg" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'">
                <p class="card-text">Đế Bá: Triệu Đại Chùy&nbsp;xuất thân từ&nbsp;Hồng Trần Kim Tiên Giới, có danh xưng là&nbsp;Tử Dương Kim Tiên, lãnh đạo của&nbsp;Liên Minh Thôn Phệ.</p>
            </div>
        </div>

        <div class="card mb-2 rounded-2">
            <div class="card-body">
                <h2 class="card-title"> 
                    <a href="//vidian.vn/chi-tiet/de-cu-vai-quyen-tieu-thuyet-ky-huyen-ma-phap-do-thi-di-nang-khoa-huyen-tuong-lai-da-hoan-thanh">Đề cử vài quyển tiểu thuyết kỳ huyễn ma pháp, đô thị dị năng, khoa huyễn tương lai đã hoàn thành</a>
                </h2>
                <img class="float-left posts-img img-thumbnail mr-2" src="////vidian.vn/img-thumbnail/img-thumbnail-1716886092053.jpg" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'">
                <p class="card-text">Hôm nay tiểu biên tiếp tục đề cử vài quyển tiểu thuyết, có kỳ huyễn ma pháp, có đô thị dị năng và khoa huyễn tương lai, mọi người nhìn đánh giá tham khảo</p>
            </div>
        </div>

        <div class="card mb-2 rounded-2">
            <div class="card-body">
                <h2 class="card-title"> 
                    <a href="//vidian.vn/chi-tiet/hoang-hon-phan-gioi-dan-mang-danh-gia-the-nao">Hoàng Hôn Phân GIới: Dân mạng đánh giá thế nào?</a>
                </h2>
                <img class="float-left posts-img img-thumbnail mr-2" src="////vidian.vn/img-thumbnail/img-thumbnail-1716622071626.webp" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'">
                <p class="card-text">Hoàng Hôn Phân Giới, tiểu thuyết đô thị dị thuật siêu năng kết hợp vô địch văn, được sáng tác bởi tác giả Hắc Sơn Lão Quỷ, trước mắt đã viết được 540 chương.</p>
            </div>
        </div>

        <div class="card mb-2 rounded-2">
            <div class="card-body">
                <h2 class="card-title"> 
                    <a href="//vidian.vn/chi-tiet/yeu-than-ky-nhiep-vu">Yêu Thần Ký: Nhiếp Vũ</a>
                </h2>
                <img class="float-left posts-img img-thumbnail mr-2" src="////vidian.vn/img-thumbnail/img-thumbnail-1716465830042.jpg" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'">
                <p class="card-text">Nhiếp Vũ&nbsp;(聂雨 – Nie Yu) là một nhân vật nữ trong tiểu thuyết huyền huyễn Yêu Thần Ký, do tác giả Phát Tiêu Đích Oa Ngưu sáng tác.</p>
            </div>
        </div>

        <div class="card mb-2 rounded-2">
            <div class="card-body">
                <h2 class="card-title"> 
                    <a href="//vidian.vn/chi-tiet/de-cu-tieu-thuyet-huyen-nghi-tro-choi-lich-su-da-hoan-thanh">Đề cử tiểu thuyết huyền nghi, trò chơi, lịch sử đã hoàn thành</a>
                </h2>
                <img class="float-left posts-img img-thumbnail mr-2" src="////vidian.vn/img-thumbnail/img-thumbnail-1716362814689.jpg" onerror="this.src='https:////vidian.vn/img-thumbnail/default-book.png'">
                <p class="card-text">Hôm nay đề cử 3 quyển tiểu thuyết, 1 quyển thuộc về&nbsp;Cthulhu, ác linh, huyền nghi, 1 quyển thuộc về trò chơi du hí và quyển còn lại thuộc về lịch sử vô địch</p>
            </div>
        </div>
        
    </div>
</div>
