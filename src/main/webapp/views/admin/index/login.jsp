<%--
  Created by IntelliJ IDEA.
  User: annpeter
  Date: 4/5/16
  Time: 11:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Minimal 1.3 - Login Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8" />

    <link rel="icon" type="image/ico" href="<%=request.getContextPath()%>/static/admin/public/img/favicon.ico"/>
    <!-- Bootstrap -->
    <link href="<%=request.getContextPath()%>/static/admin/public/vendor/bootstrap/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="<%=request.getContextPath()%>/static/admin/public/vendor/font-awesome/css/font-awesome.min-4.1.0.css"
          rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/admin/public/vendor/bootstrap/css/bootstrap-checkbox.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/admin/public/vendor/bootstrap/css/bootstrap-dropdown-multilevel.css">

    <link href="<%=request.getContextPath()%>/static/admin/public/css/minimal.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="/static/admin/public/vendor/html5shiv/html5shiv-3.7.0.js"></script>
    <script src="/static/admin/public/vendor/respond.js/respond-1.3.0.min.js"></script>
    <![endif]-->
</head>
<body class="bg-1">
<!-- Wrap all page content here -->
<div id="wrap">
    <!-- Make page fluid -->
    <div class="row">
        <!-- Page content -->
        <div id="content" class="col-md-12 full-page login">


            <div class="inside-block">
                <img src="../../../static/admin/public/img/temp/logo-big.png" alt class="logo">
                <h1><strong>Welcome</strong> Stranger</h1>
                <h5>Minimal Admin Theme</h5>

                <form id="form-signin" action="<%=request.getContextPath()%>/admin/index/login" class="form-signin" method="post">
                    <section>
                        <div class="input-group">
                            <input type="text" class="form-control" name="account" placeholder="用户昵称或手机号或邮箱">
                            <div class="input-group-addon"><i class="fa fa-user"></i></div>
                        </div>
                        <div class="input-group">
                            <input type="password" class="form-control" name="passwd" placeholder="密码">
                            <div class="input-group-addon"><i class="fa fa-key"></i></div>
                        </div>
                    </section>
                    <section class="controls">
                        <div class="checkbox check-transparent">
                            <input type="checkbox" value="1" id="remember" checked>
                            <label for="remember">Remember me</label>
                        </div>
                        <a href="login.html#">Forget password?</a>
                    </section>
                    <section class="log-in">
                        <button class="btn btn-greensea">Log In</button>
                        <span>or</span>
                        <button class="btn btn-slategray">Create an account</button>
                    </section>
                </form>
            </div>

        </div>
        <!-- /Page content -->
    </div>
</div>
<!-- Wrap all page content end -->
</body>
</html>