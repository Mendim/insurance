<%--
  Created by IntelliJ IDEA.
  User: annpeter
  Date: 4/4/16
  Time: 11:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="cn.annpeter.insurance.utils.NavigaterUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%
    ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
    NavigaterUtils navigaterUtils = (NavigaterUtils) ctx.getBean("navigaterUtils");
    navigaterUtils.refresh(request, "administrator", "list");
%>


<rapid:override name="page_head">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/admin/public/vendor/chosen/css/chosen.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/admin/public/vendor/chosen/css/chosen-bootstrap.css">
</rapid:override>


<rapid:override name="page_main">

    <div class="col-md-12">

        <!-- tile -->
        <section class="tile color transparent-black">

            <!-- tile header -->
            <div class="tile-header">
                <s:if test="#request.Administrator != null">
                    <h1><strong>管理员</strong><s:property value='#request.Administrator.truename'/>的信息</h1>
                </s:if>
                <s:else>
                    <h1><strong>添加</strong>管理员</h1>
                </s:else>
            </div>
            <!-- /tile header -->

            <!-- tile body -->
            <div class="tile-body">

                <form class="form-horizontal" role="form" action="<%=request.getContextPath()%>/admin/administrator/add" method="post">

                    <s:if test="#request.Administrator != null">
                        <input type="hidden" name="administrator.id" class="form-control"
                               value="<s:property value='#request.Administrator.id'/>"/>
                    </s:if>

                    <div class="row">
                        <div class="col-md-8">
                            <s:if test="#request.Administrator == null">
                                <div class="form-group">
                                    <label class="col-md-4 control-label">管理员真实姓名</label>
                                    <div class="col-md-8">
                                        <input type="text" name="administrator.truename" class="form-control"
                                               value="<s:property value='#request.Administrator.truename'/>"/>
                                    </div>
                                </div>
                            </s:if>

                            <div class="form-group">
                                <label class="col-md-4 control-label">管理员登陆姓名</label>
                                <div class="col-md-8">
                                    <input type="text" name="administrator.nickname" class="form-control"
                                           value="<s:property value='#request.Administrator.nickname'/>"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">所属管理组</label>
                                <div class="col-md-8">
                                    <select name="administrator.groupId" class="chosen-select chosen-transparent form-control">
                                        <s:iterator value='#request.AuthGroupArrayList' var="v">
                                            <option value='<s:property value='#v.id'/>'>
                                                <s:property value='#v.name'/>
                                            </option>
                                        </s:iterator>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">密码</label>
                                <div class="col-md-8">
                                    <input type="password" id="administratorpassword" name="administrator.adminpwd" class="form-control"
                                           value=""/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">确认密码</label>
                                <div class="col-md-8">
                                    <input type="password" name="administrator.adminpwdconfirm" class="form-control" value=""/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">手机</label>
                                <div class="col-md-8">
                                    <input type="text" name="administrator.mobile" class="form-control"
                                           value="<s:property value='#request.Administrator.mobile'/>"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">邮箱</label>
                                <div class="col-md-8">
                                    <input type="text" name="administrator.email" class="form-control"
                                           value="<s:property value='#request.Administrator.email'/>"/>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <s:if test="#request.Administrator != null">
                                <!-- tile -->
                                <section class="tile color transparent-black textured">
                                    <!-- tile header -->
                                    <div class="tile-header">
                                        <h1><strong>详细信息</strong></h1>
                                    </div>
                                    <!-- /tile header -->

                                    <!-- tile body -->
                                    <div class="tile-body">
                                        <div class="row">
                                            <div class="col-md-3">真实姓名:</div>
                                            <div class="col-md-9"><s:property value='#request.Administrator.truename'/></div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-3">最后登录IP:</div>
                                            <div class="col-md-9"><s:property value='#request.Administrator.last_login_ip'/></div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-3">最后登录时间:</div>
                                            <div class="col-md-9"><s:date name='#request.Administrator.last_login_time' format="yyyy-MM-DD HH:mm:ss"/></div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-3">创建时间:</div>
                                            <div class="col-md-9"><s:date name='#request.Administrator.createtime' format="yyyy-MM-DD HH:mm:ss"/></div>
                                        </div>
                                    </div>
                                    <!-- /tile body -->

                                </section>
                                <!-- /tile -->
                            </s:if>
                        </div>

                    </div>

                    <div class="form-group form-footer">
                        <div class="col-md-offset-3 col-md-9">
                            <button type="submit" class="btn btn-primary">Submit</button>
                            <button type="reset" class="btn btn-default">Reset</button>
                        </div>
                    </div>

                </form>

            </div>
            <!-- /tile body -->



        </section>
        <!-- /tile -->




    </div>

</rapid:override>


<rapid:override name="page_script_include">
    <script src="<%=request.getContextPath()%>/static/admin/public/vendor/chosen/chosen.jquery.min.js"></script>

    <script type="text/javascript" src="<%=request.getContextPath()%>/static/admin/public/js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/admin/administrator/js/formvalidation.js"></script>
</rapid:override>

<rapid:override name="page_script">

    <script>
        $(function(){
            //下拉框组件初始化
            $(".chosen-select").chosen({disable_search_threshold: 10});
        });
    </script>

</rapid:override>

<jsp:include page="../public/base_template.jsp"></jsp:include>