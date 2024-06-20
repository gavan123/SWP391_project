<%-- 
    Document   : Header
    Created on : May 31, 2024, 8:07:48 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header Page</title>
        <!-- Favicon -->
        <link rel="shortcut icon" href="">
        <link href="assets/css/app.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <style>
            .logo {
                width: 100%; /* Hoặc đặt kích thước cố định nếu cần */
                max-width: 300px; /* Kích thước tối đa của div logo, bạn có thể điều chỉnh giá trị này */
                margin: 0 auto; /* Để căn giữa div logo nếu cần */
            }

            .logo img {
                width: 100%;
                height: 70px;
                display: block;
            }
            .app{
                background-color: #fee2dc;
            }
            ::selection {
                color: black;
                background: #fee2dc;
            }
        </style>
    </head>
    <body>
        <div class="app">
            <div class="layout">
                <!-- Header START -->
                <div class="header">
                    <div class="logo logo-dark">
                        <a href="home">
                            <img src="images/logo/logo.png" onerror="this.src='assets/images/others/product-3.jpg'" alt="Logo" class="logo-image">
                            <img class="logo-fold" src="images/logo/logo.png" onerror="this.src='assets/images/others/product-3.jpg'" alt="Logo" class="logo-image">
                        </a>
                    </div>
                    <div class="logo logo-white">
                        <a href="home">
                            <img src="images/logo/logo.png" onerror="this.src='assets/images/others/product-3.jpg'" alt="Logo" class="logo-image">
                            <img class="logo-fold" src="images/logo/logo.png" onerror="this.src='assets/images/others/product-3.jpg'" alt="Logo" class="logo-image">
                        </a>
                    </div>
                    <div class="nav-wrap">
                        <ul class="nav-left">
                            <li class="desktop-toggle">
                                <a href="javascript:void(0);">
                                    <i class="anticon"></i>
                                </a>
                            </li>
                            <li class="mobile-toggle">
                                <a href="javascript:void(0);">
                                    <i class="anticon"></i>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0);" data-toggle="modal" data-target="#search-drawer">
                                    <i class="anticon anticon-search"></i>
                                </a>
                            </li>
                        </ul>
                        <ul class="nav-right">
                            <li class="dropdown dropdown-animated scale-left">
                                <a href="javascript:void(0);" data-toggle="dropdown">
                                    <i class="anticon anticon-bell notification-badge"></i>
                                </a>
                                <div class="dropdown-menu pop-notification">
                                    <div class="p-v-15 p-h-25 border-bottom d-flex justify-content-between align-items-center">
                                        <p class="text-dark font-weight-semibold m-b-0">
                                            <i class="anticon anticon-bell"></i>
                                            <span class="m-l-10">Notification</span>
                                        </p>
                                        <a class="btn-sm btn-default btn" href="javascript:void(0);">
                                            <small>View All</small>
                                        </a>
                                    </div>
                                    <div class="relative">
                                        <div class="overflow-y-auto relative scrollable" style="max-height: 300px">
                                            <a href="javascript:void(0);" class="dropdown-item d-block p-15 border-bottom">
                                                <div class="d-flex">
                                                    <div class="avatar avatar-blue avatar-icon">
                                                        <i class="anticon anticon-mail"></i>
                                                    </div>
                                                    <div class="m-l-15">
                                                        <p class="m-b-0 text-dark">You received a new message</p>
                                                        <p class="m-b-0"><small>8 min ago</small></p>
                                                    </div>
                                                </div>
                                            </a>
                                            <a href="javascript:void(0);" class="dropdown-item d-block p-15 border-bottom">
                                                <div class="d-flex">
                                                    <div class="avatar avatar-cyan avatar-icon">
                                                        <i class="anticon anticon-user-add"></i>
                                                    </div>
                                                    <div class="m-l-15">
                                                        <p class="m-b-0 text-dark">New user registered</p>
                                                        <p class="m-b-0"><small>7 hours ago</small></p>
                                                    </div>
                                                </div>
                                            </a>
                                            <a href="javascript:void(0);" class="dropdown-item d-block p-15 border-bottom">
                                                <div class="d-flex">
                                                    <div class="avatar avatar-red avatar-icon">
                                                        <i class="anticon anticon-user-add"></i>
                                                    </div>
                                                    <div class="m-l-15">
                                                        <p class="m-b-0 text-dark">System Alert</p>
                                                        <p class="m-b-0"><small>8 hours ago</small></p>
                                                    </div>
                                                </div>
                                            </a>
                                            <a href="javascript:void(0);" class="dropdown-item d-block p-15 ">
                                                <div class="d-flex">
                                                    <div class="avatar avatar-gold avatar-icon">
                                                        <i class="anticon anticon-user-add"></i>
                                                    </div>
                                                    <div class="m-l-15">
                                                        <p class="m-b-0 text-dark">You have a new update</p>
                                                        <p class="m-b-0"><small>2 days ago</small></p>
                                                    </div>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <jsp:include page="${param.accountHeader}" />
                            <li>
                                <a href="javascript:void(0);" data-toggle="modal" data-target="#quick-view">
                                    <i class="anticon anticon-appstore"></i>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>    
                <!-- Header END -->

                <!-- Side Nav START -->
                <jsp:include page="${param.sideNav}" />
                <!-- Side Nav END -->

                <!-- Page Container START -->
                <div class="page-container">
                    <div class="main-content">
                        <jsp:include page="${param.content}" />
                    </div>
                </div>
                <!-- Page Container END -->

                <!-- Search Start-->
                <div class="modal modal-left fade search" id="search-drawer">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header justify-content-between align-items-center">
                                <h5 class="modal-title">Search</h5>
                                <button type="button" class="close" data-dismiss="modal">
                                    <i class="anticon anticon-close"></i>
                                </button>
                            </div>
                            <div class="modal-body scrollable">
                                <div class="input-affix">
                                    <i class="prefix-icon anticon anticon-search"></i>
                                    <input type="text" class="form-control" placeholder="Search">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Search End-->

                <!-- Quick View START -->
                <div class="modal modal-right fade quick-view" id="quick-view">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header justify-content-between align-items-center">
                                <h5 class="modal-title">Theme Config</h5>
                            </div>
                            <div class="modal-body scrollable">
                                <div class="m-b-30">
                                    <h5 class="m-b-0">Header Color</h5>
                                    <p>Config header background color</p>
                                    <div class="theme-configurator d-flex m-t-10">
                                        <div class="radio">
                                            <input id="header-default" name="header-theme" type="radio" checked value="default">
                                            <label for="header-default"></label>
                                        </div>
                                        <div class="radio">
                                            <input id="header-primary" name="header-theme" type="radio" value="primary">
                                            <label for="header-primary"></label>
                                        </div>
                                        <div class="radio">
                                            <input id="header-success" name="header-theme" type="radio" value="success">
                                            <label for="header-success"></label>
                                        </div>
                                        <div class="radio">
                                            <input id="header-secondary" name="header-theme" type="radio" value="secondary">
                                            <label for="header-secondary"></label>
                                        </div>
                                        <div class="radio">
                                            <input id="header-danger" name="header-theme" type="radio" value="danger">
                                            <label for="header-danger"></label>
                                        </div>
                                    </div>
                                </div>
                                <hr>
                                <div>
                                    <h5 class="m-b-0">Side Nav Dark</h5>
                                    <p>Change Side Nav to dark</p>
                                    <div class="switch d-inline">
                                        <input type="checkbox" name="side-nav-theme-toogle" id="side-nav-theme-toogle">
                                        <label for="side-nav-theme-toogle"></label>
                                    </div>
                                </div>
                                <hr>
                                <div>
                                    <h5 class="m-b-0">Folded Menu</h5>
                                    <p>Toggle Folded Menu</p>
                                    <div class="switch d-inline">
                                        <input type="checkbox" name="side-nav-fold-toogle" id="side-nav-fold-toogle">
                                        <label for="side-nav-fold-toogle"></label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>            
                </div>
                <!-- Quick View END -->
            </div>
        </div>
        <script src="assets/js/vendors.min.js"></script>

        <script src="assets/js/app.min.js"></script>
    </body>
</html>
