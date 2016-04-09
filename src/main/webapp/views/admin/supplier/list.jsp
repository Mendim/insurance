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
    navigaterUtils.refresh(request, "supplier", "list");
%>


<rapid:override name="page_head">
</rapid:override>


<rapid:override name="page_main">
    <div class="col-md-12">

        <!-- tile -->
        <section class="tile color transparent-black">

            <!-- tile header -->
            <div class="tile-header">
                <h1><strong>系统管理账户</strong>列表</h1>
                <div class="pull-right">
                    <a class="btn btn-primary" href="<%=request.getContextPath()%>/admin/supplier/addorprofile"><i class="fa fa-plus"></i>添加提供商</a>
                </div>
            </div>
            <!-- /tile header -->

            <!-- tile body -->
            <div class="tile-body nopadding">

                <table class="table" id="adminListTable">
                    <thead>
                    <tr>
                        <th width="5%">ID</th>
                        <th>名称</th>
                        <th>代码</th>
                        <th>电话</th>
                        <th>激活地址</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                        <s:iterator value='#request.SupplierArrayList' var="v">
                            <tr>
                                <td><span><s:property value='#v.id'/></span></td>
                                <td><span><s:property value='#v.title'/></span></td>
                                <td><span><s:property value='#v.etitle'/></span></td>
                                <td><span><s:property value='#v.tel'/></span></td>
                                <td><span><s:property value='#v.activeurl'/></span></td>
                                <td>
                                    <span>
                                        <a class="btn btn-info btn-sm" href="<%=request.getContextPath()%>/admin/supplier/addorprofile?id=<s:property value='#v.id'/>">编辑</a>
                                        <span>|</span>
                                        <a class="btn btn-danger btn-sm" href="<%=request.getContextPath()%>/admin/supplier/delete?id=<s:property value='#v.id'/>">删除</a>
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
</rapid:override>

<rapid:override name="page_script">


</rapid:override>

<jsp:include page="../public/base_template.jsp"></jsp:include>