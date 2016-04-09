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
    navigaterUtils.refresh(request, "producttuanyi", "list");
%>


<rapid:override name="page_head">
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
                <h1><strong>团体意外险</strong>列表</h1>
                <div class="pull-right">
                    <a class="btn btn-primary" href="<%=request.getContextPath()%>/admin/producttuanyi/addorprofile"><i class="fa fa-plus"></i>添加团体意外险</a>
                    <a class="btn btn-primary" href="<%=request.getContextPath()%>/admin/supplier/addorprofile"><i class="fa fa-plus"></i>添加提供商</a>
                </div>
            </div>
            <!-- /tile header -->

            <!-- tile body -->
            <div class="tile-body nopadding">

                <table class="table" id="productTuanYiTable">
                    <thead>
                    <tr>
                        <th width="5%">ID</th>
                        <th>提供商</th>
                        <th>主险名称</th>
                        <th>附加险个数</th>
                        <th>操作</th>
                        <th class="none" style="display: none;"><h4>附加险</h4></th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator value='#request.ProductTuanYiArrayList' var="v">
                        <tr>
                            <div style="display: none"><s:property value='#v.productId'/></div>
                            <td><s:property value='#v.supplierId'/></td>
                            <td><s:property value='#v.supplierTitle'/></td>
                            <td><s:property value='#v.productTitle'/></td>
                            <td><s:property value='#v.attachCount'/></td>
                            <td>
                                <span style="line-height: 35px;text-align: center;">
                                    <a class="btn btn-info btn-sm" href="<%=request.getContextPath()%>/admin/producttuanyi/addorprofile?id=<s:property value='#v.id'/>">编辑主险</a>
                                    <span>|</span>
                                    <a class="btn btn-danger btn-sm" href="<%=request.getContextPath()%>/admin/producttuanyi/addattach?id=<s:property value='#v.id'/>">添加附加险</a>
                                    <span>|</span>
                                    <a class="btn btn-danger btn-sm" href="<%=request.getContextPath()%>/admin/producttuanyi/delete?id=<s:property value='#v.id'/>">删除</a>
                                </span>
                            </td>
                            <td>
                                <div class="row">
                                    <div class="col-md-4">
                                        <section class="tile color transparent-black">
                                            <!-- tile header -->
                                            <div class="tile-header">
                                                <h3>附加险1</h3>
                                            </div>
                                            <!-- /tile header -->

                                            <!-- tile widget -->
                                            <!-- /tile widget -->

                                            <!-- tile body -->
                                            <div class="tile-body">
                                                <div class="row">
                                                    <div class="col-md-2">Info:</div>
                                                    <div class="col-md-10">这里是信息的简介啥都看附件思考的九分裤</div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-2">Info:</div>
                                                    <div class="col-md-10">这里是信息的简介啥都看附件思考的九分裤</div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-2">Info:</div>
                                                    <div class="col-md-10">这里是信息的简介啥都看附件思考的九分裤</div>
                                                </div>
                                            </div>
                                            <!-- /tile body -->

                                            <!-- tile footer -->
                                            <!-- /tile footer -->
                                        </section>
                                    </div>


                                </div>
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

    <script src="<%=request.getContextPath()%>/static/admin/public/js/ap-datatables.js"></script>
</rapid:override>

<rapid:override name="page_script">
    <script>
        $(function(){

            //dataTable
            apDatatablesInit("productTuanYiTable");

        });
    </script>
</rapid:override>

<jsp:include page="../public/base_template.jsp"></jsp:include>