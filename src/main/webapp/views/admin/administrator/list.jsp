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

    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/admin/public/vendor/datatables/css/datatables.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/admin/public/vendor/datatables/css/dataTables.bootstrap.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/admin/public/css/table-center.css">
</rapid:override>


<rapid:override name="page_main">
    <div class="col-md-12">

        <!-- tile -->
        <section class="tile color transparent-black">

            <!-- tile header -->
            <div class="tile-header">
                <h1><strong>系统管理账户</strong>列表</h1>
                <div class="pull-right">
                    <a class="btn btn-primary" href="<%=request.getContextPath()%>/admin/administrator/add"><i class="fa fa-plus"></i>添加管理员</a>
                    <a class="btn btn-primary" href="<%=request.getContextPath()%>/admin/authgroup/add"><i class="fa fa-plus"></i>添加管理组</a>
                </div>
            </div>
            <!-- /tile header -->

            <!-- tile body -->
            <div class="tile-body nopadding">

                <table class="table" id="adminListTable">
                    <thead>
                    <tr>
                        <th width="5%">ID</th>
                        <th>真实姓名</th>
                        <th>登录昵称</th>
                        <th>手机号</th>
                        <th>邮箱</th>
                        <s:if test="#request.BoolShowGroupName">
                            <th>群组</th>
                        </s:if>
                        <th>操作</th>
                        <td class="none">最后登录时间</td>
                        <td class="none">最后登录IP</td>
                        <td class="none">注册日期</td>
                    </tr>
                    </thead>
                    <tbody>
                        <s:iterator value='#request.AdministratorArrayList' var="v">
                            <tr>
                                <td><span><s:property value='#v.id'/><s:property value="#v.id"/></span></td>
                                <td><span><s:property value='#v.truename'/></span></td>
                                <td><span><s:property value='#v.nickname'/></span></td>
                                <td><span><s:property value='#v.mobile'/></span></td>
                                <td><span><s:property value='#v.email'/></span></td>
                                <s:if test="#request.BoolShowGroupName">
                                    <td>
                                        <span>
                                            <select name="administrator.groupId" class="chosen-select chosen-transparent form-control">
                                                <s:iterator value='#request.AuthGroupArrayList' var="g">
                                                    <option value='<s:property value='#g.id'/>' <s:if test="#g.id == #v.groupId">selected</s:if>>
                                                        <s:property value='#g.name'/>
                                                    </option>
                                                </s:iterator>
                                            </select>
                                        </span>
                                    </td>
                                </s:if>
                                <td>
                                    <span>
                                        <a class="btn btn-danger btn-sm" href="<%=request.getContextPath()%>/admin/administrator/delete?id=<s:property value='#v.id'/>">删除</a>
                                    </span>
                                </td>
                                <td><s:date name='#v.last_login_time' format="yyyy-MM-DD HH:mm:ss"/></td>
                                <td><s:property value='#v.last_login_ip'/></td>
                                <td><s:date name='#v.createtime' format="yyyy-MM-DD HH:mm:ss"/></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>

            </div>
            <!-- /tile body -->

        </section>
        <!-- /tile -->

    </div>

</rapid:override>


<rapid:override name="page_script_include">
    <script src="<%=request.getContextPath()%>/static/admin/public/vendor/chosen/chosen.jquery.min.js"></script>

    <script src="<%=request.getContextPath()%>/static/admin/public/vendor/datatables/datatables.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/admin/public/vendor/datatables/dataTables.bootstrap.js"></script>

    <script src="<%=request.getContextPath()%>/static/admin/public/js/ap-datatables.js"></script>
</rapid:override>

<rapid:override name="page_script">

    <script>
        $(function(){
            //chosen select input
            $(".chosen-select").chosen({disable_search_threshold: 10});


            //dataTable
            apDatatablesInit("adminListTable");

        });
    </script>

</rapid:override>

<jsp:include page="../public/base_template.jsp"></jsp:include>