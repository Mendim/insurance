<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<!DOCTYPE html>
<html>
<head>
    <title>后台管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8"/>

    <link rel="icon" type="image/ico" href="<%=request.getContextPath()%>/static/admin/public/img/favicon.ico"/>
    <!-- Bootstrap -->
    <link href="<%=request.getContextPath()%>/static/admin/public/vendor/bootstrap/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="<%=request.getContextPath()%>/static/admin/public/vendor/font-awesome/css/font-awesome.min-4.1.0.css"
          rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/admin/public/vendor/animate/css/animate.min.css">
    <link type="text/css" rel="stylesheet" media="all"
          href="<%=request.getContextPath()%>/static/admin/public/vendor/mmenu/css/jquery.mmenu.all.css"/>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/static/admin/public/vendor/videobackground/css/jquery.videobackground.css">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/static/admin/public/vendor/bootstrap/css/bootstrap-checkbox.css">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/static/admin/public/vendor/bootstrap/css/bootstrap-dropdown-multilevel.css">

    <rapid:block name="page_head">
    </rapid:block>

    <link href="<%=request.getContextPath()%>/static/admin/public/css/minimal.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="/static/admin/public/vendor/html5shiv/html5shiv-3.7.0.js"></script>
    <script src="/static/admin/public/vendor/respond.js/respond-1.3.0.min.js"></script>
    <![endif]-->

</head>

<body class="solid-bg-3">

<!-- Preloader -->
<div class="mask">
    <div id="loader"></div>
</div>
<!--/Preloader -->

<!-- Wrap all page content here -->
<div id="wrap">


    <!-- Make page fluid -->
    <div class="row">


        <!-- Fixed navbar -->
        <div class="navbar navbar-default navbar-fixed-top navbar-transparent-black mm-fixed-top" role="navigation"
             id="navbar">


            <!-- Branding -->
            <div class="navbar-header col-md-2">
                <a class="navbar-brand" href="index.html">
                    <strong>MIN</strong>IMAL
                </a>
                <div class="sidebar-collapse">
                    <a href="blank-page.html#">
                        <i class="fa fa-bars"></i>
                    </a>
                </div>
            </div>
            <!-- Branding end -->


            <!-- .nav-collapse -->
            <div class="navbar-collapse">

                <!-- Page refresh -->
                <ul class="nav navbar-nav refresh">
                    <li class="divided">
                        <a href="blank-page.html#" class="page-refresh"><i class="fa fa-refresh"></i></a>
                    </li>
                </ul>
                <!-- /Page refresh -->

                <!-- Search -->
                <div class="search" id="main-search">
                    <i class="fa fa-search"></i> <input type="text" placeholder="Search...">
                </div>
                <!-- Search end -->

                <!-- Quick Actions -->
                <ul class="nav navbar-nav quick-actions">

                    <li class="dropdown divided">

                        <a class="dropdown-toggle button" data-toggle="dropdown" href="blank-page.html#">
                            <i class="fa fa-tasks"></i>
                            <span class="label label-transparent-black">2</span>
                        </a>

                        <ul class="dropdown-menu wide arrow nopadding bordered">
                            <li><h1>You have <strong>2</strong> new tasks</h1></li>
                            <li>
                                <a href="blank-page.html#">
                                    <div class="task-info">
                                        <div class="desc">Layout</div>
                                        <div class="percent">80%</div>
                                    </div>
                                    <div class="progress progress-striped progress-thin">
                                        <div class="progress-bar progress-bar-green" role="progressbar"
                                             aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                                             style="width: 80%">
                                            <span class="sr-only">40% Complete (success)</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="blank-page.html#">
                                    <div class="task-info">
                                        <div class="desc">Schemes</div>
                                        <div class="percent">15%</div>
                                    </div>
                                    <div class="progress progress-striped active progress-thin">
                                        <div class="progress-bar progress-bar-cyan" role="progressbar"
                                             aria-valuenow="45" aria-valuemin="0" aria-valuemax="100"
                                             style="width: 15%">
                                            <span class="sr-only">45% Complete</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="blank-page.html#">
                                    <div class="task-info">
                                        <div class="desc">Forms</div>
                                        <div class="percent">5%</div>
                                    </div>
                                    <div class="progress progress-striped progress-thin">
                                        <div class="progress-bar progress-bar-orange" role="progressbar"
                                             aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 5%">
                                            <span class="sr-only">5% Complete (warning)</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="blank-page.html#">
                                    <div class="task-info">
                                        <div class="desc">JavaScript</div>
                                        <div class="percent">30%</div>
                                    </div>
                                    <div class="progress progress-striped progress-thin">
                                        <div class="progress-bar progress-bar-red" role="progressbar" aria-valuenow="45"
                                             aria-valuemin="0" aria-valuemax="100" style="width: 30%">
                                            <span class="sr-only">30% Complete (danger)</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="blank-page.html#">
                                    <div class="task-info">
                                        <div class="desc">Dropdowns</div>
                                        <div class="percent">60%</div>
                                    </div>
                                    <div class="progress progress-striped progress-thin">
                                        <div class="progress-bar progress-bar-amethyst" role="progressbar"
                                             aria-valuenow="45" aria-valuemin="0" aria-valuemax="100"
                                             style="width: 60%">
                                            <span class="sr-only">60% Complete</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li><a href="blank-page.html#">Check all tasks <i class="fa fa-angle-right"></i></a></li>
                        </ul>

                    </li>

                    <li class="dropdown divided">

                        <a class="dropdown-toggle button" data-toggle="dropdown" href="blank-page.html#">
                            <i class="fa fa-envelope"></i>
                            <span class="label label-transparent-black">1</span>
                        </a>

                        <ul class="dropdown-menu wider arrow nopadding messages">
                            <li><h1>You have <strong>1</strong> new message</h1></li>
                            <li>
                                <a class="cyan" href="blank-page.html#">
                                    <div class="profile-photo">
                                        <img src="../../../static/admin/public/img/temp/ici-avatar.jpg" alt/>
                                    </div>
                                    <div class="message-info">
                                        <span class="sender">Ing. Imrich Kamarel</span>
                                        <span class="time">12 mins</span>
                                        <div class="message-content">Duis aute irure dolor in reprehenderit in voluptate
                                            velit esse cillum
                                        </div>
                                    </div>
                                </a>
                            </li>

                            <li>
                                <a class="green" href="blank-page.html#">
                                    <div class="profile-photo">
                                        <img src="../../../static/admin/public/img/temp/arnold-avatar.jpg" alt/>
                                    </div>
                                    <div class="message-info">
                                        <span class="sender">Arnold Karlsberg</span>
                                        <span class="time">1 hour</span>
                                        <div class="message-content">Lorem ipsum dolor sit amet, consectetur adipisicing
                                            elit
                                        </div>
                                    </div>
                                </a>
                            </li>

                            <li>
                                <a href="blank-page.html#">
                                    <div class="profile-photo">
                                        <img src="../../../static/admin/public/img/temp/profile-photo.jpg" alt/>
                                    </div>
                                    <div class="message-info">
                                        <span class="sender">John Douey</span>
                                        <span class="time">3 hours</span>
                                        <div class="message-content">Excepteur sint occaecat cupidatat non proident,
                                            sunt in culpa qui officia
                                        </div>
                                    </div>
                                </a>
                            </li>

                            <li>
                                <a class="red" href="blank-page.html#">
                                    <div class="profile-photo">
                                        <img src="../../../static/admin/public/img/temp/peter-avatar.jpg" alt/>
                                    </div>
                                    <div class="message-info">
                                        <span class="sender">Peter Kay</span>
                                        <span class="time">5 hours</span>
                                        <div class="message-content">Ut enim ad minim veniam, quis nostrud
                                            exercitation
                                        </div>
                                    </div>
                                </a>
                            </li>

                            <li>
                                <a class="orange" href="blank-page.html#">
                                    <div class="profile-photo">
                                        <img src="../../../static/admin/public/img/temp/george-avatar.jpg" alt/>
                                    </div>
                                    <div class="message-info">
                                        <span class="sender">George McCain</span>
                                        <span class="time">6 hours</span>
                                        <div class="message-content">Lorem ipsum dolor sit amet, consectetur adipisicing
                                            elit
                                        </div>
                                    </div>
                                </a>
                            </li>


                            <li class="topborder"><a href="blank-page.html#">Check all messages <i
                                    class="fa fa-angle-right"></i></a></li>
                        </ul>

                    </li>

                    <li class="dropdown divided">

                        <a class="dropdown-toggle button" data-toggle="dropdown" href="blank-page.html#">
                            <i class="fa fa-bell"></i>
                            <span class="label label-transparent-black">3</span>
                        </a>

                        <ul class="dropdown-menu wide arrow nopadding bordered">
                            <li><h1>You have <strong>3</strong> new notifications</h1></li>

                            <li>
                                <a href="blank-page.html#">
                                    <span class="label label-green"><i class="fa fa-user"></i></span>
                                    New user registered.
                                    <span class="small">18 mins</span>
                                </a>
                            </li>

                            <li>
                                <a href="blank-page.html#">
                                    <span class="label label-red"><i class="fa fa-power-off"></i></span>
                                    Server down.
                                    <span class="small">27 mins</span>
                                </a>
                            </li>

                            <li>
                                <a href="blank-page.html#">
                                    <span class="label label-orange"><i class="fa fa-plus"></i></span>
                                    New order.
                                    <span class="small">36 mins</span>
                                </a>
                            </li>

                            <li>
                                <a href="blank-page.html#">
                                    <span class="label label-cyan"><i class="fa fa-power-off"></i></span>
                                    Server restared.
                                    <span class="small">45 mins</span>
                                </a>
                            </li>

                            <li>
                                <a href="blank-page.html#">
                                    <span class="label label-amethyst"><i class="fa fa-power-off"></i></span>
                                    Server started.
                                    <span class="small">50 mins</span>
                                </a>
                            </li>

                            <li><a href="blank-page.html#">Check all notifications <i class="fa fa-angle-right"></i></a>
                            </li>
                        </ul>

                    </li>

                    <li class="dropdown divided user" id="current-user">
                        <div class="profile-photo">
                            <img src="../../../static/admin/public/img/temp/profile-photo.jpg" alt/>
                        </div>
                        <a class="dropdown-toggle options" data-toggle="dropdown" href="blank-page.html#">
                            John Douey <i class="fa fa-caret-down"></i>
                        </a>

                        <ul class="dropdown-menu arrow settings">

                            <li>

                                <h3>Backgrounds:</h3>
                                <ul id="color-schemes">
                                    <li><a href="blank-page.html#" class="bg-1"></a></li>
                                    <li><a href="blank-page.html#" class="bg-2"></a></li>
                                    <li><a href="blank-page.html#" class="bg-3"></a></li>
                                    <li><a href="blank-page.html#" class="bg-4"></a></li>
                                    <li><a href="blank-page.html#" class="bg-5"></a></li>
                                    <li><a href="blank-page.html#" class="bg-6"></a></li>
                                    <li class="title">Solid Backgrounds:</li>
                                    <li><a href="blank-page.html#" class="solid-bg-1"></a></li>
                                    <li><a href="blank-page.html#" class="solid-bg-2"></a></li>
                                    <li><a href="blank-page.html#" class="solid-bg-3"></a></li>
                                    <li><a href="blank-page.html#" class="solid-bg-4"></a></li>
                                    <li><a href="blank-page.html#" class="solid-bg-5"></a></li>
                                    <li><a href="blank-page.html#" class="solid-bg-6"></a></li>
                                </ul>


                            </li>

                            <li class="divider"></li>


                            <li>

                                <div class="form-group videobg-check">
                                    <label class="col-xs-8 control-label">Video BG</label>
                                    <div class="col-xs-4 control-label">
                                        <div class="onoffswitch greensea small">
                                            <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox"
                                                   id="videobg-check">
                                            <label class="onoffswitch-label" for="videobg-check">
                                                <span class="onoffswitch-inner"></span>
                                                <span class="onoffswitch-switch"></span>
                                            </label>
                                        </div>
                                    </div>
                                </div>

                                <ul id="videobackgrounds">
                                    <li class="title">Choose type:</li>
                                    <li><a href="blank-page.html#" class="video-bg-1"></a></li>
                                    <li><a href="blank-page.html#" class="video-bg-2"></a></li>
                                    <li><a href="blank-page.html#" class="video-bg-3"></a></li>
                                    <li><a href="blank-page.html#" class="video-bg-4"></a></li>
                                    <li><a href="blank-page.html#" class="video-bg-5"></a></li>
                                    <li><a href="blank-page.html#" class="video-bg-6"></a></li>
                                    <li><a href="blank-page.html#" class="video-bg-7"></a></li>
                                    <li><a href="blank-page.html#" class="video-bg-8"></a></li>
                                    <li><a href="blank-page.html#" class="video-bg-9"></a></li>
                                    <li><a href="blank-page.html#" class="video-bg-10"></a></li>
                                </ul>

                            </li>


                            <li class="divider"></li>

                            <li>
                                <a href="<%=request.getContextPath()%>/admin/administrator/profile"><i class="fa fa-user"></i> Profile</a>
                            </li>

                            <li>
                                <a href="blank-page.html#"><i class="fa fa-calendar"></i> Calendar</a>
                            </li>

                            <li>
                                <a href="blank-page.html#"><i class="fa fa-envelope"></i> Inbox <span
                                        class="badge badge-red" id="user-inbox">3</span></a>
                            </li>

                            <li class="divider"></li>

                            <li>
                                <a href="<%=request.getContextPath()%>/admin/administrator/logout"><i class="fa fa-power-off"></i> Logout</a>
                            </li>
                        </ul>
                    </li>

                    <li>
                        <a href="blank-page.html#mmenu"><i class="fa fa-comments"></i></a>
                    </li>
                </ul>
                <!-- /Quick Actions -->


                <!-- Sidebar -->
                <ul class="nav navbar-nav side-nav" id="sidebar">

                    <li class="collapsed-content">
                        <ul>
                            <li class="search"><!-- Collapsed search pasting here at 768px --></li>
                        </ul>
                    </li>

                    <li class="navigation" id="navigation">
                        <a href="#" class="sidebar-toggle" data-toggle="#navigation">Navigation <i
                                class="fa fa-angle-up"></i></a>

                        <ul class="menu">

                            <li>
                                <a href="<%=request.getContextPath()%>/admin/index/index">
                                    <i class="fa fa-tachometer"></i> Dashboard
                                    <span class="badge badge-red">1</span>
                                </a>
                            </li>
                            <%--左侧导航栏--%>
                            <s:property value="#session.LeftNavHtml" escape="false"></s:property>
                        </ul>

                    </li>

                    <li class="summary" id="order-summary">
                        <a href="blank-page.html#" class="sidebar-toggle underline" data-toggle="#order-summary">Orders
                            Summary <i class="fa fa-angle-up"></i></a>

                        <div class="media">
                            <a class="pull-right" href="blank-page.html#">
                                <span id="sales-chart"></span>
                            </a>
                            <div class="media-body">
                                This week sales
                                <h3 class="media-heading">26, 149</h3>
                            </div>
                        </div>

                        <div class="media">
                            <a class="pull-right" href="blank-page.html#">
                                <span id="balance-chart"></span>
                            </a>
                            <div class="media-body">
                                This week balance
                                <h3 class="media-heading">318, 651</h3>
                            </div>
                        </div>

                    </li>

                    <li class="settings" id="general-settings">
                        <a href="blank-page.html#" class="sidebar-toggle underline" data-toggle="#general-settings">General
                            Settings <i class="fa fa-angle-up"></i></a>

                        <div class="form-group">
                            <label class="col-xs-8 control-label">Switch ON</label>
                            <div class="col-xs-4 control-label">
                                <div class="onoffswitch greensea">
                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox"
                                           id="switch-on" checked="">
                                    <label class="onoffswitch-label" for="switch-on">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-8 control-label">Switch OFF</label>
                            <div class="col-xs-4 control-label">
                                <div class="onoffswitch greensea">
                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox"
                                           id="switch-off">
                                    <label class="onoffswitch-label" for="switch-off">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>

                    </li>


                </ul>
                <!-- Sidebar end -->


            </div>
            <!--/.nav-collapse -->


        </div>
        <!-- Fixed navbar end -->


        <!-- Page content -->
        <div id="content" class="col-md-12">


            <!-- page header -->
            <div class="pageheader">


                <h2><i class="fa fa-file-o" style="line-height: 48px;padding-left: 2px;"></i> Blank Page <span>// Place subtitle here...</span>
                </h2>


                <div class="breadcrumbs">
                    <ol class="breadcrumb">
                        <li>You are here</li>
                        <li><a href="index.html">Minimal</a></li>
                        <li><a href="blank-page.html#">Example Pages</a></li>
                        <li class="active">Blank Page</li>
                    </ol>
                </div>


            </div>
            <!-- /page header -->


            <!-- content main container -->
            <div class="main">
                <div class="row">
                    <rapid:block name="page_main">
                    </rapid:block>
                </div>
            </div>
            <!-- /content container -->


        </div>
        <!-- Page content end -->



    </div>
    <!-- Make page fluid-->


</div>
<!-- Wrap all page content end -->


<section>
    <div id="alertForm" class="modal fade" tabindex="-1" data-width="400" aria-hidden="true" style="display: none;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title"></h4>
                </div>
                <div class="modal-body">
                    <div class="row form-horizontal">
                        <div class="col-md-12">
                            <div class="modal-context"></div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-cancle" >取消</button>
                    <button type="button" data-dismiss="modal" class="btn red btn-ok">确定</button>
                </div>
            </div>
        </div>
    </div>
</section>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->


<script type="text/javascript"
        src="<%=request.getContextPath()%>/static/admin/public/js/jquery-1.11.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script type="text/javascript"
        src="<%=request.getContextPath()%>/static/admin/public/vendor/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/static/admin/public/vendor/bootstrap/bootstrap-dropdown-multilevel.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/static/admin/public/vendor/run_prettify/run_prettify.js?lang=css&skin=sons-of-obsidian"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/static/admin/public/vendor/mmenu/js/jquery.mmenu.min.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/static/admin/public/vendor/sparkline/jquery.sparkline.min.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/static/admin/public/vendor/nicescroll/jquery.nicescroll.min.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/static/admin/public/vendor/animate-numbers/jquery.animateNumbers.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/static/admin/public/vendor/videobackground/jquery.videobackground.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/static/admin/public/vendor/blockui/jquery.blockUI.js"></script>


<script type="text/javascript"
        src="<%=request.getContextPath()%>/static/admin/public/vendor/jquery-cookie/jquery.cookie.js"></script>


<rapid:block name="page_script_include">

</rapid:block>


<script src="<%=request.getContextPath()%>/static/admin/public/js/minimal.min.js"></script>

<rapid:block name="page_footer">

</rapid:block>


<script type="text/javascript">
    //将当前导航的上级展开并高亮
    $('.active').parents('#navigation li').addClass(' open active ');


    window.alert = function(context, title){
        if(title){
            apalert(title, context);
        }else{
            apalert("提示", context);
        }
    }

    //alertForm提示框,警告框
     function apalert(title,context,funOk, funCancle){
        $("#alertForm .modal-title").html(title);
        $("#alertForm .modal-context").html(context);
        //重新刷新alertFrom的高度
        var alertFromHeight = ($(window).height() - $("#alertForm > .modal-dialog").height())*0.3;
        $("#alertForm").modal('show');

        if(funOk){
            $("#alertForm .btn-ok").on('click', funOk);
        }
        if(funCancle){
            $("#alertForm .btn-cancle").on('click', funCancle);
        }
    }


    //权限检查
    (function () {
        //调整alert框的高度
        var alertFromHeight = ($(window).height() - $("#alertForm > .modal-dialog").height()) * 0.3;

        $("#alertForm > .modal-dialog").css("margin-top", alertFromHeight + 'px');

        $("a").bind('click', function (event) {
            var href = $(this).attr('href');
            $.cookie('tempUrl', href);

            if (href != undefined && href.length > 6) {
                var reg = new RegExp("^/admin/([a-z]*)/([a-z]*)");
                var ret = href.match(reg);
                if(ret==null){
                    return;
                }
                var action = ret[1];
                var method = ret[2];

                function checkAuth() {
                    $.ajax({
                        type: 'POST',
                        url: '<%=request.getContextPath()%>/admin/authgroup/checkauth',
                        data: {'action': action, 'method': method},
                        success: function (data) {
                            if (data.status == 1) {
                                //授权成功
                                var href = $.cookie('tempUrl');
                                window.location.href = href;
                                $.cookie("tempUrl", null);
                            } else {
                                //授权失败
                                apalert("警告", "您没有此访问路径的权限");
                            }
                        },
                        dataType: 'json'
                    });
                }

                if (method.substring(0, 6) == 'delete') {
                    apalert("提示", "确定要删除么?", function () {
                        checkAuth();
                    });
                } else {
                    checkAuth();
                }
                return false;
            } else {
                return true;
            }
        });
    })();

</script>
<rapid:block name="page_script">

</rapid:block>

</body>
</html>
