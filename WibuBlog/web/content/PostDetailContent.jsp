
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    
    .comment-input-block .card-title {
        background: #FAF41F;
        color: rgba(255, 255, 255, 0.15);
        font-weight: 800;
        position: relative;
        -webkit-animation: shine-data-v-729833f6 1s infinite;
        -webkit-background-clip: text;
        -webkit-background-size: 300px;
    }
    .comment-card {
        border-color: #FAF41F;
        box-shadow: 0 0 15px #FAF41F;
    }

</style>
<div class="col-lg-12 mb-2">
    <div class="card mb-2">
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
                <h4>Source: ${post.source != null ? post.source : 'N/A'}</h4>
            </div>
            <hr>
            <div class="row">
                <div class="col-lg-4 mb-2 mx-auto">
                    <ul class="list-unstyled m-0 d-flex flex-wrap justify-content-center">
                        <li class="d-flex align-items-center mr-4 font-weight-bold">
                            <div class="vote-section" id="vote-section">
                                <i id="vote_up" class="anticon anticon_vote anticon-arrow-up mr-2" onclick="vote('up')" ></i>
                                <i id="vote_down" class="anticon anticon_vote anticon-arrow-down mr-2" onclick="vote('down')"></i>
                                <span id="vote_value">${post.vote}</span>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="card mb-2 border-0">
        <div class="card-body">
            <c:choose>
                <c:when test="${empty user}">
                    <div class="card mb-2 border-0">
                        <div class="card-body">
                            <p class="card-text text-center">
                                You need to <span><a style="color:blue" href="login">Login</a></span> to comment!
                            </p>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="border-0 bg-none mt-2 media align-items-center">
                        <div class="comment-avatar mr-2">
                            <img alt="${user.username}" title="${user.username}" src="${user.profilePhoto}" onerror="this.src='assets/images/others/product-3.jpg'" width="45" height="45">
                        </div>
                        <div class="comment-input-block media-body">
                            <div class="d-flex justify-content-between align-items-center">
                                <textarea class="form-control" style="height: 60px !important; border-radius: 1.25rem; background-color: white;" rows="2" id="msg" minlength="30" required placeholder="Ta đến nói hai câu..."></textarea>
                                <button type="button" class="btn btn-success bg-transparent btn-submit-comment border-0 text-primary d-flex align-items-center justify-content-center shadow-none px-2 ml-auto" onclick="sendMsg()">
                                    <i class="fas fa-paper-plane fa-2x"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>

            <c:forEach var="comment" items="${commentsList}" varStatus="loop">
                <c:set var="commentUser" value="${userComment[loop.index]}" />
                <c:set var="commentDate" value="${commentTime[loop.index]}" />
                <div class="comment-container media">
                    <div class="comment-avatar">
                        <img alt="${commentUser.username}" title="${commentUser.username}" src="${commentUser.profilePhoto}" onerror="this.src='assets/images/others/product-3.jpg'">
                    </div>
                    <div class="comment-input-block media-body" id="comment_${loop.index}">
                        <p class="card-text">
                            <span class="card-title">
                                ${comment.content}
                            </span>
                            <span class="text-truncate" title="${commentUser.username}" >
                                ${commentUser.username}
                            </span>
                        </p>
                        <div class="card comment-card">
                            <div class="card-body">
                                ${comment.content}
                            </div>
                            <input id="input_${loop.index}" type="text" value="${comment.commentId}" hidden="">
                        </div>
                        <p class="card-text comment-date">
                            <span class="badge badge-secondary">
                                ${commentDate}
                            </span>
                            <button class="btn reply-button" onclick="reply(${comment.commentId})">
                                <i class="mdi mdi-reply"></i> Reply
                            </button>
                        </p>
                    </div>
                </div>
            </c:forEach>
            <c:if test="${empty commentsList}">
                <p>No comments found.</p>
            </c:if>

            <div class="border-0 bg-none media align-items-center mt-3" style="border-top:1px solid #a7acad !important;">
                <div class="comment-avatar mr-2">
                    <img alt="Lữ thiên thụ " title="Lữ thiên thụ " src="//vidian.vn/public-img/image-1660494445519.jpg" onerror="this.src='https:////vidian.vn/images/chi-dao-sang-tac.jpg'" width="45" height="45">
                </div>
                <div class="comment-input-block media-body" id="comment_0">
                    <p class="card-text">
                        <span class="card-title text-capitalize" style="background:#FAF41F -webkit-gradient(linear, left top, right top, from(#FAF41F), to(#FAF41F), color-stop(0.5,#ffffff)) 0 0 no-repeat; color: rgba(255, 255, 255, 0.15); font-weight:800; position: relative; -webkit-animation: shine-data-v-729833f6 1s infinite; -webkit-background-clip: text; -webkit-background-size: 300px;">dục thần</span>
                        <span class="text-truncate ml-1 font-weight-bold" style="font-size:14px" title="Lữ thiên thụ ">Lữ thiên thụ </span>
                    </p>
                    <div class="card" style="border-color:#FAF41F">
                        <div class="card-body border-0 rounded fw-500" style="box-shadow: 0 0 15px #FAF41F">
                            Hảo bài hay.................👍
                        </div>
                        <input id="input_0" type="text" value="6307298b6a8aa46faf9bd801" hidden="">
                    </div>
                    <p class="card-text text-right mt-2 mb-0">
                        <span class="badge badge-secondary">2 năm trước</span>
                        <button class="btn border-0 bg-none text-secondary rounded-2" id="button_0" onclick="reply(0)"><i class="mdi mdi-reply text-secondary"></i>Trả lời</button>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

<script>
    // Biến để theo dõi trạng thái upvote/downvote
    let voteStatus = 'none'; // Trạng thái ban đầu

// Hàm để lấy giá trị của một tham số từ URL
    const getUrlParameter = (param) => {
        const urlParams = new URLSearchParams(window.location.search);
        return urlParams.get(param);
    };

    const vote = (type) => {
        const voteValueElement = document.getElementById('vote_value');
        const postId = getUrlParameter('postId');
        if (!postId) {
            console.error("postId không tồn tại trong URL");
            return; // Thoát ra nếu postId không tồn tại
        }

        isLoggedIn((loggedIn) => {
            if (!loggedIn) {
                Swal.fire({
                    icon: 'error',
                    title: 'You need login to vote',
                    showConfirmButton: false,
                    timer: 2000
                });
                return;
            }
            const currentVote = parseInt(voteValueElement.innerText);
            let increment = 0;

            // Hàm để xử lý thêm/loại bỏ lớp 'upvoted' và 'downvoted'
            const toggleVoteClass = (addUpvoted, addDownvoted) => {
                const voteSection = document.getElementById('vote-section');
                voteSection.classList.toggle('upvoted', addUpvoted);
                voteSection.classList.toggle('downvoted', addDownvoted);
            };

            // Xử lý upvote và downvote
            if (type === 'up') {
                increment = (voteStatus === 'upvoted') ? -1 : (voteStatus === 'downvoted') ? 2 : 1;
                voteStatus = (voteStatus === 'upvoted') ? 'none' : 'upvoted';
                toggleVoteClass(voteStatus === 'upvoted', false);
            } else if (type === 'down') {
                increment = (voteStatus === 'downvoted') ? 1 : (voteStatus === 'upvoted') ? -2 : -1;
                voteStatus = (voteStatus === 'downvoted') ? 'none' : 'downvoted';
                toggleVoteClass(false, voteStatus === 'downvoted');
            }

            let newVoteValue = currentVote + increment;
            voteValueElement.innerText = newVoteValue;

            // Gửi yêu cầu AJAX để cập nhật vote
            const xhr = new XMLHttpRequest();
            xhr.open("POST", "updateVote", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = () => {
                if (xhr.readyState === 4) {
                    console.log(xhr.status === 200 ? "Vote updated successfully" : "Error updating vote");
                }
            };
            xhr.send("postId=" + postId + "&vote_value=" + newVoteValue);
        });
    };

    // Check Login
    const isLoggedIn = (callback) => {
        const xhr = new XMLHttpRequest();
        xhr.open("GET", "checkLogin", true);
        xhr.setRequestHeader("Content-Type", "text/plain"); // Sử dụng text/plain
        xhr.onreadystatechange = () => {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    const loggedIn = (xhr.responseText.trim() === 'true');
                    callback(loggedIn);
                } else {
                    console.error("Lỗi khi kiểm tra đăng nhập:", xhr.status);
                    callback(false);
                }
            }
        };
        xhr.send();
    };

    function sendMsg() {
        var msg = $("#msg").val();
        if (msg.length < 30) {
            alert("Tối thiểu 30 ký tự...");
        } else {
            var postId = "6665211975745839de997bef";
            var postTitle = "Đế Bá: Trầm Thiên";
            var posterId = "5fd8e6df5915dc650457f080";
            var postSlug = "de-ba-tram-thien";
            $.post("//vidian.vn/chi-tiet/de-ba-tram-thien/add-comment", {_msg: msg, _postId: postId, _postTitle: postTitle, _posterId: posterId, _postSlug: postSlug}, function (result) {
                if (result == 200) {
                    alert("Thành công! Comment cần chờ tác giả phê duyệt!");
                }
            }, "json");
        }
    }


    function sendMsgReply(id) {
        var msg = $("#msgReply").val();
        var parent_id = $("#input_" + id).val();
        if (msg.length < 30) {
            alert("Tối thiểu 30 ký tự...");
        } else {
            var postId = "6665211975745839de997bef";
            var postTitle = "Đế Bá: Trầm Thiên";
            var posterId = "5fd8e6df5915dc650457f080";
            var postSlug = "de-ba-tram-thien";
            $.post("//vidian.vn/chi-tiet/de-ba-tram-thien/add-comment-reply", {_msg: msg, _postId: postId, _postTitle: postTitle, _posterId: posterId, _postSlug: postSlug, _parent_id: parent_id}, function (result) {
                if (result == 200) {
                    alert("Thành công! Comment cần chờ tác giả phê duyệt!");
                }
            }, "json");
        }
    }

</script>