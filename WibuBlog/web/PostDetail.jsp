
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Post Detail Page</title>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
        <style>
            .badge{border-radius:20px!important}
.anticon_vote{display:inline-block;padding:20px;width:24px;line-height:24px;text-align:center;border-radius:50%;background-color:#f0f0f0;cursor:pointer}
.anticon-arrow-up{color:green}
.anticon-arrow-down{color:red}
#vote_value{font-size:18px;margin-left:8px}
.vote-section.upvoted{background-color:#90ee90}
.vote-section.downvoted{background-color:#fa8072}
.vote-section{display:flex;justify-content:center;align-items:center;width:100%;border-radius:20px;border:1px solid #ccc;padding:10px;margin:10px;transition:background-color .3s ease}
.comment-container{border-top:1px solid #a7acad!important;border:0;background:none;margin-top:1rem;display:flex;align-items:center}
.comment-container img{margin-top:50px}
.comment-avatar{margin-right:.5rem}
.card-text span{flex-direction:row}
.comment-avatar img{width:55px;height:55px;border-radius:50%}
.comment-input-block .text-truncate{margin-left:.25rem;font-weight:700;font-size:14px}
.comment-date{display:flex;justify-content:flex-end;align-items:center;margin-top:.5rem;margin-bottom:0;width:fit-content;margin-left:auto}
.reply-button{border:0;background:none;color:#6c757d;border-radius:.25rem;display:inline-flex;align-items:center}
.comment-input-block .form-control{height:80px;border-radius:1.25rem;background-color:#fff}
.btn-submit-comment{background-color:transparent;border:0;color:#007bff;display:flex;align-items:center;justify-content:center;box-shadow:none;padding-left:.5rem;padding-right:.5rem;margin-left:auto}
        .checkbox-wrapper-31:hover .check{stroke-dashoffset:0}.checkbox-wrapper-31{position:relative;display:inline-block;width:25px;height:25px}.checkbox-wrapper-31 .background{fill:#ccc;transition:ease all .6s;-webkit-transition:ease all .6s}.checkbox-wrapper-31 .stroke{fill:none;stroke:#fff;stroke-miterlimit:10;stroke-width:2px;stroke-dashoffset:100;stroke-dasharray:100;transition:ease all .6s;-webkit-transition:ease all .6s}.checkbox-wrapper-31 .check{fill:none;stroke:#fff;stroke-linecap:round;stroke-linejoin:round;stroke-width:2px;stroke-dashoffset:22;stroke-dasharray:22;transition:ease all .6s;-webkit-transition:ease all .6s}.checkbox-wrapper-31 input[type=checkbox]{position:absolute;width:100%;height:100%;left:0;top:0;margin:0;opacity:0;-appearance:none;-webkit-appearance:none}.checkbox-wrapper-31 input[type=checkbox]:hover{cursor:pointer}.checkbox-wrapper-31 input[type=checkbox]:checked + svg .background{fill:#6cbe45}.checkbox-wrapper-31 input[type=checkbox]:checked + svg .stroke{stroke-dashoffset:0}.checkbox-wrapper-31 input[type=checkbox]:checked + svg .check{stroke-dashoffset:0}.checkbox-item{margin:5px}
        </style>
    </head>
    <body>
        <jsp:include page="Layout.jsp">
            <jsp:param name="sideNav" value="SideNav.jsp" />
            <jsp:param name="accountHeader" value="/content/AccountContent.jsp" />
            <jsp:param name="content" value="/content/PostDetailContent.jsp" />
        </jsp:include>

    </body>
</html>
