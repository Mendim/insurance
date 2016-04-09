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
                <s:if test="#request.Administrator != null">
                    <h1><strong>提供商</strong><s:property value='#request.Administrator.truename'/>的信息</h1>
                </s:if>
                <s:else>
                    <h1><strong>添加</strong>提供商</h1>
                </s:else>
            </div>
            <!-- /tile header -->

            <!-- tile body -->
            <div class="tile-body">

                <form class="form-horizontal" role="form" method="post"
                      action='<%=request.getContextPath()%>/admin/supplier/addorprofile'>

                    <s:if test="#request.Supplier != null">
                        <input type="hidden" name="supplier.id" class="form-control"
                               value="<s:property value='#request.Supplier.id'/>"/>
                    </s:if>

                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-4 control-label">提供商名称</label>
                                <div class="col-md-8">
                                    <input type="text" name="supplier.title" class="form-control"
                                           value="<s:property value='#request.Supplier.title'/>"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">LOGO(200*200)</label>
                                <div class="col-md-8">
                                    <div class="plupload_container" style="position: relative;">
                                        <div id="SupplierLOGO" class="btn btn-info" style="position: relative; z-index: 1;"><i class="fa fa-plus"></i> 上传文件 <span class="info"></span></div>
                                    </div>
                                    <img src="<s:property value='#request.Supplier.logo'/>" style="display:none;"  alt=""  height="100">
                                    <input type="hidden" name="supplier.logo" value="<s:property value='#request.Supplier.logo'/>">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">代码</label>
                                <div class="col-md-8">
                                    <input type="text" name="supplier.etitle" class="form-control"
                                           value="<s:property value='#request.Supplier.etitle'/>"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">激活网址</label>
                                <div class="col-md-8">
                                    <input type="text" name="supplier.activeurl" class="form-control"
                                           value="<s:property value='#request.Supplier.activeurl'/>"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">联系电话</label>
                                <div class="col-md-8">
                                    <input type="text" name="supplier.tel" class="form-control"
                                           value="<s:property value='#request.Supplier.tel'/>"/>
                                </div>
                            </div>
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
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/admin/public/js/plupload.full.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/admin/public/js/ap-fileupload.js"></script>
</rapid:override>

<rapid:override name="page_script">

    <script>
        //图片上传组件初始化
        apSingleFileuploadInit('SupplierLOGO', '<%=request.getContextPath()%>');

    </script>

</rapid:override>

<jsp:include page="../public/base_template.jsp"></jsp:include>