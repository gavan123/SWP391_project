<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="p" scope="request" class="dal.PostDAO" />

<div class="row pb-6">
    <div class="col-lg-12">
        <div class="card mb-1 rounded-2 border-0">
            <div class="card-header">
                <h1 class="card-title text-uppercase text-center">Mod Report post</h1>
            </div>
        </div>


        <c:forEach var="post" items="${p.allPostHaveReport}">
            <c:if test="${post.status eq 'active' && post.userId != user.userId}">
                <div class="card mb-2 rounded-2" style="border-color:aqua;border-style:double;border-width:medium">
                    <div class="card-body">
                        <img class="float-left posts-img img-thumbnail mr-2 w-20"
                             src="${pageContext.request.contextPath}/images/post/${post.image}" 
                             onerror="this.src='assets/images/others/product-3.jpg'">
                        <ul class="list-group">
                            <li class="list-group-item d-flex justify-content-between align-items-center" style="background:none;border:none">
                                <div class="col-10 text-truncate font-weight-bold ">
                                    <a href="postDetail?postId=${post.postId}">${post.title}</a>
                                </div>
                                <div class="col-2 d-flex justify-content-end">
                                    <button type="button" class="btn btn-warning btn-sm mx-1 view-report-btn" data-id="${post.postId}" 
                                            data-toggle="tooltip" 
                                            title="View report" data-toggle="modal" data-target="#reportModal">
                                        <i class="anticon anticon-exclamation-circle"></i>
                                    </button>
                                    <button type="button" class="btn btn-success btn-sm mx-1" data-toggle="tooltip" title="Accept report to delete post">
                                        <i class="anticon anticon-check-circle"></i>
                                    </button>
                                    <button type="button" class="btn btn-danger btn-sm mx-1" data-toggle="tooltip" title="Decline report to delete post">
                                        <i class="anticon anticon-close-circle"></i>
                                    </button>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </c:if>
        </c:forEach>


    </div>
</div>

<!--Table Report Modal -->
<div class="modal fade" id="reportModal" tabindex="-1" aria-labelledby="reportModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="reportModalLabel">Report Details</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">User</th>
                            <th scope="col">Reason</th>
                            <th scope="col">Note</th>
                            <th scope="col">Date</th>
                        </tr>
                    </thead>
                    <tbody class="table" id="report-details">

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        $('[data-toggle="tooltip"]').tooltip();

        // Ensure that the modal is triggered by the button
        $('.view-report-btn').on('click', function () {
            var postId = $(this).data('id');
            $.ajax({
                url: 'viewReportTable', // URL to fetch report details
                type: 'GET',
                data: {postId: postId},
                success: (data) => {
                    $('#report-details').html(data);
                    $('#reportModal').modal('show');
                },
                error: (xhr, status, error) => {
                    var errorMessage = xhr.status + ': ' + xhr.statusText;
                    if (xhr.responseJSON && xhr.responseJSON.message) {
                        errorMessage += '\n' + xhr.responseJSON.message;
                    }
                    alert('Failed to fetch report details.\nError: ' + errorMessage);
                }
            });
        });
    });

</script>