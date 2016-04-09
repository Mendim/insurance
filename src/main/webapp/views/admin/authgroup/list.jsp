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
    navigaterUtils.refresh(request, "authgroup", "list");
%>


<rapid:override name="page_head">

</rapid:override>


<rapid:override name="page_main">
    <div class="col-md-12">

        <!-- tile -->
        <section class="tile color transparent-black">

            <!-- tile header -->
            <div class="tile-header">
                <h1><strong>管理组</strong>列表</h1>
                <div class="pull-right">
                    <a class="btn btn-primary" href="<%=request.getContextPath()%>/admin/authgroup/add"><i class="fa fa-plus"></i>添加管理组</a>
                </div>
            </div>
            <!-- /tile header -->

            <!-- tile body -->
            <div class="tile-body nopadding">

                <table class="table">
                    <thead>
                    <tr>
                        <th width="5%">ID</th>
                        <th>用户组</th>
                        <th>描述</th>
                        <th>授权</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                        <s:iterator value='#request.AuthGroupArrayList' var="v">
                            <tr>
                                <td><s:property value='#v.id'/><s:property value="#v.id"/></td>
                                <td><s:property value='#v.name'/></td>
                                <td><s:property value='#v.description'/></td>
                                <td>
                                    <a class="btn btn-info btn-sm" href="<%=request.getContextPath()%>/admin/authgroup/addorprofile?id=<s:property value='#v.id'/>">分组编辑</a>
                                    <span>|</span>
                                    <a class="btn btn-info btn-sm" href="<%=request.getContextPath()%>/admin/authgroup/ingrouplist?id=<s:property value='#v.id'/>">成员列表</a>
                                </td>
                                <td>
                                    <a class="btn btn-danger btn-sm" href="<%=request.getContextPath()%>/admin/authgroup/delete?id=<s:property value='#v.id'/>">删除分组</a>
                                </td>
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

</rapid:override>

<rapid:override name="page_script">

</rapid:override>

<jsp:include page="../public/base_template.jsp"></jsp:include>