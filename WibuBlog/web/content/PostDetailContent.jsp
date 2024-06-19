
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .badge {
        border-radius: 20px !important;
    }
    .anticon_vote {
        display: inline-block;
        width: 24px;
        height: 24px;
        line-height: 24px;
        text-align: center;
        border-radius: 50%;
        background-color: #f0f0f0;
        cursor: pointer;
    }
    .anticon-arrow-up {
        color: green;
    }
    .anticon-arrow-down {
        color: red;
    }
    #vote_value {
        font-size: 18px;
        margin-left: 8px;
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
            </div>
            <hr>
            <div class="row">
                <div class="col-lg-4 mb-2 mx-auto">
                    <ul class="list-unstyled m-0 d-flex flex-wrap justify-content-center">
                        <li class="d-flex align-items-center mr-4 font-weight-bold">
                            <i id="vote_up" class="anticon anticon_vote anticon-arrow-up mr-2" onclick="vote('up')" ></i>
                            <i id="vote_down" class="anticon anticon_vote anticon-arrow-down mr-2" onclick="vote('down')"></i>
                            <span id="vote_value">${post.vote}</span>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="card mb-2 border-0">
        <div class="card-body">


            <p class="card-text text-center">Thư hữu cần <span><a style="color:blue" href="////vidian.vn/dang-nhap">đăng nhập</a></span> để bình luận!</p>

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

            <hr>
        </div>
    </div>
</div>

<script>
   // Biến để theo dõi trạng thái upvote/downvote
    let voteStatus = 'none'; // Trạng thái ban đầu

    function vote(type) {
        let voteValueElement = document.getElementById('vote_value');
        let currentVote = parseInt(voteValueElement.innerText);
        let increment = 0;

        if (type === 'up') {
            increment = (voteStatus === 'upvoted') ? -1 : (voteStatus === 'downvoted') ? 2 : 1;
            voteStatus = (voteStatus === 'upvoted') ? 'none' : 'upvoted';
        } else if (type === 'down') {
            increment = (voteStatus === 'downvoted') ? 1 : (voteStatus === 'upvoted') ? -2 : -1;
            voteStatus = (voteStatus === 'downvoted') ? 'none' : 'downvoted';
        }

        voteValueElement.innerText = currentVote + increment;
    }
</script>