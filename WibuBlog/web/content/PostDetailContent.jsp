
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<style>

</style>

<div class="col-lg-12 mb-2">
    <c:choose>
        <c:when test="${post.status == 'active'}">
            <div class="card mb-2">
                <div class="card-body">
                    <header>
                        <h1 class="card-title" style="font-size: 26px;line-height:34px">${post.title}</h1>
                    </header>
                    <h6 class="card-subtitle mb-2 fw-700 " style="font-size: small !important;">
                        <i class="fas fa-user"></i> 
                        <a href="#" style="color:blue" accesskey="a">${post.username}</a> | 
                        <i class="fas fa-clock"></i>
                        <time datetime="${postTime}">${postTime}</time> | 
                        <i class="fas fa-eye"></i> ${post.view}
                        <c:if test="${post.username == user.username}">
                            <button type="button" class="btn btn-danger" id="deletePostButton" style="margin-left: 50%">
                                <i class="fas fa-trash"></i> Delete Post
                            </button>
                        </c:if>
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
                    </div>
                    <hr>
                    <c:if test="${post.username != user.username}">
                        <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#reportPostModal">
                            <i class="fas fa-flag"></i> Report Post
                        </button>
                    </c:if>
                    <div class="row">
                        <div class="col-lg-4 mb-2 mx-auto">
                            <ul class="list-unstyled m-0 d-flex flex-wrap justify-content-center">
                                <li class="d-flex align-items-center mr-4 font-weight-bold">
                                    <div class="vote-section" id="vote-section">
                                        <i id="vote_up" class="anticon anticon_vote anticon-arrow-up mr-2"
                                           onclick="votePost('up', '${votePostStatus}')"></i>
                                        <i id="vote_down" class="anticon anticon_vote anticon-arrow-down mr-2" 
                                           onclick="votePost('down', '${votePostStatus}')"></i>
                                        <span id="vote_value">${post.vote}</span>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </c:when>
        <c:when test="${post.status == 'hide'}">
            <p>The post is hide by author.</p>
        </c:when>
        <c:otherwise>
            <p>The post has been delete</p>
        </c:otherwise>
    </c:choose>


    <!--    Post user-->

    <div class="card mb-2">
        <img class="img-thumbnail rounded-circle mx-auto d-block mt-2" 
             alt="${post.username}" title="${post.username}" 
             style="width:120px;height:120px" 
             src="${pageContext.request.contextPath}/${post.image}"
             onerror="this.src='assets/images/others/product-3.jpg'">
        <div class="card-body">
            <p class="card-title text-center fw-700 mb-0">
                <a style="color:blue" href="#">${post.username}</a>
            </p>
            <p class="text-center mb-0">
                <span class="font-status text-capitalize" 
                      style="background:${post.color} ;color: rgba(255, 255, 255, 0.15);font-weight:800; position: relative;-webkit-background-clip: text;">
                    ${post.rank}
                </span>
            </p>
            <hr>
            <p class="card-text text-center font-status">"${post.bio}"</p>
        </div>
    </div>

    <!--     Comment                       -->
    <div class="card mb-2 border-0">
        <div class="card-body">
            <c:if test="${post.status == 'active'}">
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
                                <img alt="${user.username}" title="${user.username}" 
                                     src="${pageContext.request.contextPath}/${user.profilePhoto}"
                                     onerror="this.src='assets/images/others/product-3.jpg'" width="45" height="45">
                            </div>
                            <div class="comment-input-block media-body">
                                <div class="d-flex justify-content-between align-items-center">
                                    <textarea class="form-control" rows="2" id="msg" minlength="30" 
                                              required placeholder="Ta đến nói hai câu..."></textarea>
                                    <button type="button" class="btn btn-success btn-submit-comment" onclick="sendMsg()">
                                        <i class="fas fa-paper-plane fa-2x"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:if>



            <c:forEach var="comment" items="${commentsList}" varStatus="loop">
                <c:set var="commentUser" value="${userComment[loop.index]}" />
                <c:set var="commentUserRank" value="${userRank[loop.index]}" />
                <c:set var="commentDate" value="${commentTime[loop.index]}" />
                <c:if test="${comment.parentId ==null}">
                    <div class="comment-container media">
                        <div class="comment-avatar">
                            <img alt="${commentUser.username}" 
                                 title="${commentUser.username}" 
                                 src="${pageContext.request.contextPath}/${commentUser.profilePhoto}" 
                                 onerror="this.src='assets/images/others/product-3.jpg'">
                        </div>
                        <div class="comment-input-block media-body" id="comment_${loop.index}">
                            <p class="card-text">
                                <span class="card-title" data-rank-color=" ${commentUserRank.color}" >
                                    ${commentUserRank.name}
                                </span>
                                <span class="text-truncate" title="${commentUser.username}" >
                                    ${commentUser.username}
                                </span>
                            </p>
                            <div class="card comment-card" data-rank-color="${commentUserRank.color}">
                                <div class="card-body">
                                    <c:choose>
                                        <c:when test="${comment.status eq 'active'}">
                                            ${comment.content}
                                        </c:when>
                                        <c:otherwise>
                                            This comment has been deleted.
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <input id="commentId" type="hidden" value="${comment.commentId}" >
                            </div>
                            <div class="card-text comment-date">
                                <span class="badge badge-secondary">
                                    ${commentDate}
                                </span>
                                <c:if test="${not empty user && post.status == 'active'}">
                                    <c:if test="${user.userId == commentUser.userId && comment.status eq 'active'}">
                                        <button type="button" class="btn reply-button" data-comment-id="${comment.commentId}" 
                                                data-toggle="modal" data-target="#editCommentModal">
                                            Edit
                                        </button>
                                    </c:if>
                                    <button class="btn reply-button"  data-comment-id="${comment.commentId}" onclick="toggleReply(this)">
                                        Reply
                                    </button>
                                </c:if>
                            </div>
                            <div id="replyComment_${comment.commentId}" class="replyComment justify-content-between align-items-center d-none">
                                <textarea class="form-control" rows="2" id="msgReply_${comment.commentId}" 
                                          minlength="30" required placeholder="Ta đến nói hai câu..."></textarea>
                                <button type="button" class="btn btn-success btn-submit-comment"
                                        data-comment-id="${comment.commentId}" 
                                        data-reply-user="${commentUser.username}" onclick="sendMsgReply(this)">
                                    <i class="fas fa-paper-plane fa-2x"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </c:if>

                <c:forEach var="commentReply" items="${commentsList}" varStatus="loop">
                    <c:set var="commentUser" value="${userComment[loop.index]}" />
                    <c:set var="commentUserRank" value="${userRank[loop.index]}" />
                    <c:set var="commentDate" value="${commentTime[loop.index]}" />
                    <c:if test="${commentReply.parentId !=null && commentReply.parentId == comment.commentId}">
                        <div class="comment-container media" style="margin-left: 7%">
                            <div class="comment-avatar ">
                                <img alt="${commentUser.username}" 
                                     title="${commentUser.username}" 
                                     src="${pageContext.request.contextPath}/${commentUser.profilePhoto}" 
                                     onerror="this.src='assets/images/others/product-3.jpg'">
                            </div>
                            <div class="comment-input-block media-body" id="comment_${loop.index}">
                                <p class="card-text">
                                    <span class="card-title" data-rank-color=" ${commentUserRank.color}" >
                                        ${commentUserRank.name}
                                    </span>
                                    <span class="text-truncate" title="${commentUser.username}" >
                                        ${commentUser.username}
                                    </span>

                                </p>
                                <div class="card comment-card" data-rank-color="${commentUserRank.color}">
                                    <div class="card-body">
                                        <c:choose>
                                            <c:when test="${commentReply.status eq 'active'}">
                                                ${commentReply.content}
                                            </c:when>
                                            <c:otherwise>
                                                This comment has been deleted.
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <input id="commentId" type="hidden" value="${commentReply.commentId}" >
                                </div>
                                <div class="card-text comment-date">
                                    <span class="badge badge-secondary">
                                        ${commentDate}
                                    </span>
                                    <c:if test="${not empty user && post.status == 'active'}">
                                        <c:if test="${user.userId == commentUser.userId && comment.status eq 'active'}">
                                            <button type="button" class="btn reply-button" data-comment-id="${commentReply.commentId}" 
                                                    data-toggle="modal" data-target="#editCommentModal">
                                                Edit
                                            </button>
                                        </c:if>
                                        <button class="btn reply-button"  data-comment-id="${commentReply.commentId}" onclick="toggleReply(this)">
                                            Reply
                                        </button>
                                    </c:if>
                                </div>
                                <div id="replyComment_${commentReply.commentId}" class="replyComment justify-content-between align-items-center d-none">
                                    <textarea class="form-control" rows="2" id="msgReply_${commentReply.commentId}" 
                                              minlength="30" required placeholder="Ta đến nói hai câu..."></textarea>
                                    <button type="button" class="btn btn-success btn-submit-comment"
                                            data-comment-id="${comment.commentId}" 
                                            data-reply-user="${commentUser.username}" onclick="sendMsgReply(this)">
                                        <i class="fas fa-paper-plane fa-2x"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
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


<!-- Edit Comment Modal -->
<div class="modal fade" id="editCommentModal" tabindex="-1" role="dialog" aria-labelledby="editCommentModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editCommentModalLabel">Edit Comment</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <input type="hidden" name="commentId" id="editCommentId" value="">
                <textarea class="form-control" rows="3" id="editCommentTextarea" minlength="30" required placeholder="Enter your edited comment..."></textarea>
            </div>
            <div class="modal-footer">
                <button id="deleteCommentBtn" type="button" class="btn btn-secondary" >Delete</button>
                <button id="saveEditedCommentBtn" type="button" class="btn btn-primary" >Save changes</button>
            </div>
        </div>
    </div>
</div>

<!-- Report Post Modal -->
<div class="modal fade" id="reportPostModal" tabindex="-1" role="dialog" aria-labelledby="reportPostModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="reportPostModalLabel">Report Post</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="reportReasons">Select reasons for reporting:</label>
                    <div id="reportReasons">
                        <div class="checkbox-item">
                            <div class=" checkbox-wrapper-31">
                                <input class="form-check-input " type="checkbox" name="reasons" id="spam" value="Spam">
                                <svg viewBox="0 0 35.6 35.6"> <circle class="background" cx="17.8" cy="17.8" r="17.8"></circle> <circle class="stroke" cx="17.8" cy="17.8" r="14.37"></circle> <polyline class="check" points="11.78 18.12 15.55 22.23 25.17 12.87"></polyline> </svg>
                            </div>
                            <label class="form-check-label" for="spam">spam</label>
                        </div>
                        <div class="checkbox-item">
                            <div class="checkbox-wrapper-31">
                                <input class="form-check-input" type="checkbox" name="reasons" id="harassment" value="Harassment">
                                <svg viewBox="0 0 35.6 35.6"> <circle class="background" cx="17.8" cy="17.8" r="17.8"></circle> <circle class="stroke" cx="17.8" cy="17.8" r="14.37"></circle> <polyline class="check" points="11.78 18.12 15.55 22.23 25.17 12.87"></polyline> </svg>
                            </div>
                            <label class="form-check-label" for="harassment">Harassment</label>
                        </div>
                        <div class="checkbox-item">
                            <div class="checkbox-wrapper-31">
                                <input class="form-check-input" type="checkbox" name="reasons" id="hateSpeech" value="Hate Speech">
                                <svg viewBox="0 0 35.6 35.6"> <circle class="background" cx="17.8" cy="17.8" r="17.8"></circle> <circle class="stroke" cx="17.8" cy="17.8" r="14.37"></circle> <polyline class="check" points="11.78 18.12 15.55 22.23 25.17 12.87"></polyline> </svg>
                            </div>
                            <label class="form-check-label" for="hateSpeech">Hate Speech</label>
                        </div>
                        <div class="checkbox-item">
                            <div class="checkbox-wrapper-31">
                                <input class="form-check-input" type="checkbox" name="reasons" id="misinformation" value="Misinformation">
                                <svg viewBox="0 0 35.6 35.6"> <circle class="background" cx="17.8" cy="17.8" r="17.8"></circle> <circle class="stroke" cx="17.8" cy="17.8" r="14.37"></circle> <polyline class="check" points="11.78 18.12 15.55 22.23 25.17 12.87"></polyline> </svg>
                            </div>
                            <label class="form-check-label" for="misinformation">Misinformation</label>
                        </div>
                        <div class="checkbox-item">
                            <div class="checkbox-wrapper-31">
                                <input class="form-check-input" type="checkbox" name="reasons" id="other" value="Other">
                                <svg viewBox="0 0 35.6 35.6"> <circle class="background" cx="17.8" cy="17.8" r="17.8"></circle> <circle class="stroke" cx="17.8" cy="17.8" r="14.37"></circle> <polyline class="check" points="11.78 18.12 15.55 22.23 25.17 12.87"></polyline> </svg>
                            </div>
                            <label class="form-check-label" for="other">Other</label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button id="cancelReportBtn" type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                <button id="submitReportBtn" type="button" class="btn btn-primary">Submit Report</button>
            </div>
        </div>
    </div>
</div>

<script>
    
    let voteStatus = 'unvote'; // Trạng thái ban đầu
// Hàm xử lý upvote/downvote
    const votePost = (type, voteStatus) => {
        const voteValueElement = document.getElementById('vote_value');
        const postId = getUrlParameter('postId');
        if (!postId) {
            console.error("postId không tồn tại trong URL");
            return; // Thoát nếu không có postId
        }
        
        isLoggedIn(loggedIn => {
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
            // Xử lý upvote và downvote
            if (type === 'up') {
                increment = (voteStatus === 'upvote') ? -1 : (voteStatus === 'downvote') ? 2 : 1;
                voteStatus = (voteStatus === 'upvote') ? 'unvote' : 'upvote';
                toggleVotePostClass(voteStatus === 'upvote', false);
            } else if (type === 'down') {
                increment = (voteStatus === 'downvote') ? 1 : (voteStatus === 'upvote') ? -2 : -1;
                voteStatus = (voteStatus === 'downvote') ? 'unvote' : 'downvote';
                toggleVotePostClass(false, voteStatus === 'downvote');
            }
            const newVoteValue = currentVote + increment;
            // Cập nhật giá trị vote hiển thị
            voteValueElement.innerText = newVoteValue;
            
            // Gửi yêu cầu AJAX để cập nhật vote
            const xhr = new XMLHttpRequest();
            xhr.open("POST", "updateVotePost", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = () => {
                if (xhr.readyState === 4) {
                    console.log(xhr.status === 200 ? "Vote updated successfully" : "Error updating vote");
                }
            };
            xhr.send("postId=" + postId + "&vote_value=" + newVoteValue + "&vote_status=" + voteStatus);
            location.reload();
            setTimeout(() => {
                location.reload();
            }, 200); // Reload sau 0.2 giây 
        });
    };
    
    //Show change upvote downvote
    document.addEventListener('DOMContentLoaded', function () {
        const votePostStatus = `${votePostStatus}`; // Đảm bảo votePostStatus là string
        if (votePostStatus.trim() === "") {
            return; // Nếu votePostStatus rỗng thì không làm gì cả và thoát khỏi hàm
        }
        console.log(votePostStatus);
        if (votePostStatus === "unvote") {
            toggleVotePostClass(false, false);
        } else if (votePostStatus === "upvote") {
            toggleVotePostClass(true, false);
        } else if (votePostStatus === "downvote") {
            toggleVotePostClass(false, true);
        }
        
    });
    
// Thay đổi background của .comment-input-block .card-title
    const commentTitles = document.querySelectorAll('.comment-input-block .card-title');
    commentTitles.forEach(title => {
        const rankColor = title.getAttribute('data-rank-color');
        if (rankColor) {
            title.style.background = rankColor;
            title.style.color = 'rgba(255, 255, 255, 0.15)';
            title.style.fontWeight = '800';
            title.style.position = 'relative';
            title.style.webkitBackgroundClip = 'text';
        }
    });
    
// Thay đổi border color và box-shadow của .comment-card
    const commentCards = document.querySelectorAll('.comment-card');
    commentCards.forEach(card => {
        const rankColor = card.getAttribute('data-rank-color');
        if (rankColor) {
            card.style.borderColor = "" + rankColor;
            card.style.boxShadow = '0 0 15px ' + rankColor;
        }
    });
    
    
// Hàm để lấy giá trị của một tham số từ URL
    const getUrlParameter = param => {
        const urlParams = new URLSearchParams(window.location.search);
        return urlParams.get(param);
    };
    
    // Hàm để xử lý thêm/loại bỏ lớp 'upvoted' và 'downvoted' cửa post
    const toggleVotePostClass = (addUpvoted, addDownvoted) => {
        const voteSection = document.getElementById('vote-section');
        voteSection.classList.toggle('upvoted', addUpvoted);
        voteSection.classList.toggle('downvoted', addDownvoted);
    };
    
    
// Kiểm tra đăng nhập
    const isLoggedIn = callback => {
        const xhr = new XMLHttpRequest();
        xhr.open("GET", "checkLogin", true);
        xhr.setRequestHeader("Content-Type", "text/plain");
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
// Gửi tin nhắn
    function sendMsg() {
        const msg = $("#msg").val();
        const postId = getUrlParameter('postId');
        if (!postId) {
            console.error("postId không tồn tại trong URL");
            return; // Thoát nếu không có postId
        }
        if (msg.length < 30) {
            alert("Enter at least 30 word...");
        } else {
            $.ajax({
                type: 'POST',
                url: 'addComment',
                data: {content: msg, postId: postId, parentId: 0},
                success: response => {
                    alert("Comment added successfully!");
                    $("#msg").val('');
                    location.reload();
                },
                error: error => {
                    alert("Error adding comment: " + error.responseText);
                }
            });
        }
    }
    
// Chuyển đổi trạng thái hiển thị phản hồi
    function toggleReply(button) {
        const commentId = button.getAttribute('data-comment-id');
        console.log(commentId);
        const replyCommentDiv = document.querySelector('#replyComment_' + commentId);
        if (replyCommentDiv) {
            if (replyCommentDiv.classList.contains('d-none')) {
                replyCommentDiv.classList.remove('d-none');
                replyCommentDiv.classList.add('d-flex');
            } else {
                replyCommentDiv.classList.add('d-none');
                replyCommentDiv.classList.remove('d-flex');
            }
        } else {
            console.error('Không tìm thấy phần tử với id #replyComment_' + commentId);
        }
    }
    
// Gửi phản hồi
    function sendMsgReply(button) {
        const parentId = button.getAttribute('data-comment-id');
        const userReply = button.getAttribute('data-reply-user');
        const msg = $("#msgReply_" + parentId).val();
        const postId = getUrlParameter('postId');
        if (!postId) {
            console.error("postId không tồn tại trong URL");
            return; // Thoát nếu không có postId
        }
        if (msg.length < 30) {
            alert("Enter at least 30 word...");
        } else {
            $.ajax({
                type: 'POST',
                url: 'addComment',
                data: {content: "@" + userReply + " " + msg, postId: postId, parentId: parentId},
                success: response => {
                    alert("Comment added successfully!");
                    $("#msgReply").val('');
                    location.reload();
                },
                error: error => {
                    console.log("Error adding comment: " + error.responseText);
                }
            });
        }
    }
    
    $(document).ready(() => {
        //Show edit comment
        $('#editCommentModal').on('show.bs.modal', (event) => {
            const button = $(event.relatedTarget); // Button that triggered the modal
            const commentId = button.data('comment-id'); // Extract info from data-* attributes
            $.ajax({
                url: 'updateComment',
                type: 'GET',
                data: {commentId},
                success: (data) => {
                    // Assuming 'data' is the comment object in JSON format
                    $('#editCommentId').val(data.commentId);
                    $('#editCommentTextarea').val(data.content);
                },
                error: (xhr, status, error) => {
                    console.error(`Failed to fetch comment: ${error}`);
                }
            });
        });
        
        // Save edited comment function
        const saveEditedComment = () => {
            // Get the edited comment ID and content from the modal
            var commentId = $('#editCommentId').val();
            var editedContent = $('#editCommentTextarea').val();
            // Prepare the data to send in the AJAX request
            var data = {
                commentId: commentId,
                content: editedContent
            };
            // Send an AJAX POST request to update comment
            $.ajax({
                type: 'POST',
                url: 'updateComment',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function (response) {
                    console.log('Comment edited successfully');
                    // Optionally close the modal or perform other actions
                    $('#editCommentModal').modal('hide'); // Example: Hide modal after successful save
                    location.reload();
                    alert('Comment edited successfully');
                },
                error: function (xhr, status, error) {
                    // Handle error
                    console.error('Error editing comment:', error);
                }
            });
        };
        // Attach saveEditedComment function to the 'Save changes' button click
        $('#saveEditedCommentBtn').on('click', saveEditedComment);
        
        // Save edited comment function
        const deleteComment = () => {
            var commentId = $('#editCommentId').val();
            var data = {
                commentId: commentId,
            };
            // Send an AJAX POST request to update comment
            $.ajax({
                type: 'POST',
                url: 'deleteComment',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function (response) {
                    console.log('Comment edited successfully');
                    $('#editCommentModal').modal('hide');
                    location.reload();
                    alert('Comment deleted successfully');
                },
                error: function (xhr, status, error) {
                    console.error('Error editing comment:', error);
                }
            });
        };
        // Attach deleteCommentBtn function to the 'Save changes' button click
        $('#deleteCommentBtn').on('click', deleteComment);
        
        $('#deletePostButton').click(function () {
            const postId = getUrlParameter('postId');
            if (confirm('Are you sure you want to delete this post?')) {
                $.ajax({
                    url: 'deletePost',
                    type: 'POST',
                    data: {postId: postId},
                    success: (response) => {
                        alert('Post deleted successfully');
                        window.location.href = 'home';
                    },
                    error: (xhr, status, error) => {
                        console.log(error);
                        alert('Failed to delete post');
                        location.reload();
                    }
                });
            }
        });
    });
</script>