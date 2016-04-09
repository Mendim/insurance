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
    navigaterUtils.refresh(request, "productkadan", "list");
%>


<rapid:override name="page_head">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/admin/public/vendor/datatables/css/datatables.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/admin/public/vendor/datatables/css/dataTables.bootstrap.css">
</rapid:override>


<rapid:override name="page_main">
    <div class="col-md-12">

        <!-- tile -->
        <section class="tile color transparent-black">

            <!-- tile header -->
            <div class="tile-header">
                <h1><strong>卡单</strong>列表</h1>
                <div class="pull-right">
                    <a class="btn btn-primary" href="<%=request.getContextPath()%>/admin/productkadan/addorprofile"><i class="fa fa-plus"></i>添加卡单</a>
                    <a class="btn btn-primary" href="<%=request.getContextPath()%>/admin/supplier/addorprofile"><i class="fa fa-plus"></i>添加提供商</a>
                </div>
            </div>
            <!-- /tile header -->

            <!-- tile body -->
            <div class="tile-body nopadding">

                <table class="table">
                    <thead>
                    <tr>
                        <th width="5%">ID</th>
                        <th>名称</th>
                        <th>种类</th>
                        <th>价格</th>
                        <th>开始时间</th>
                        <th>失效时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator value='#request.ProductKaDanArrayList' var="v">
                        <tr>
                            <td><s:property value='#v.id'/></td>
                            <td><s:property value='#v.product.title'/></td>
                            <td><s:property value='#v.product.card_cate'/></td>
                            <td><s:property value='#v.price'/></td>
                            <td><s:date name='#v.product.start_date' format="yyyy-MM-DD HH:mm:ss"/></td>
                            <td><s:date name='#v.product.end_date' format="yyyy-MM-DD HH:mm:ss"/></td>
                            <td>
                                <span style="line-height: 35px;text-align: center;">
                                    <a class="btn btn-info btn-sm" href="<%=request.getContextPath()%>/admin/productkadan/addorprofile?id=<s:property value='#v.id'/>">编辑</a>
                                    <span>|</span>
                                    <a class="btn btn-danger btn-sm" href="<%=request.getContextPath()%>/admin/productkadan/delete?id=<s:property value='#v.id'/>">删除</a>
                                </span>
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
    <script src="<%=request.getContextPath()%>/static/admin/public/vendor/datatables/datatables.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/admin/public/vendor/datatables/dataTables.bootstrap.js"></script>
</rapid:override>

<rapid:override name="page_script">

</rapid:override>

<jsp:include page="../public/base_template.jsp"></jsp:include>