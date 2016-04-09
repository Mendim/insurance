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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/admin/public/vendor/chosen/css/chosen.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/admin/public/vendor/chosen/css/chosen-bootstrap.css">

    <link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/static/admin/public/vendor/bootstrap/css/datetimepicker.css">

    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/static/admin/public/vendor/summernote/css/summernote.css">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/static/admin/public/vendor/summernote/css/summernote-bs3.css">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/admin/public/vendor/ztree/css/metroStyle.css" type="text/css">
</rapid:override>


<rapid:override name="page_main">
    <div class="col-md-12">

        <!-- tile -->
        <section class="tile color transparent-black">

            <!-- tile header -->
            <div class="tile-header">
                <h1><strong>添加</strong>卡单</h1>

            </div>
            <!-- /tile header -->

            <!-- tile body -->
            <div class="tile-body">

                <form class="form-horizontal" role="form" action="<%=request.getContextPath()%>/admin/productkadan/addorprofile" method="post">
                    <s:if test="#request.ProductKaDan != null">
                        <input type="hidden" name="productKaDan.id" value="<s:property value='#request.ProductKaDan.id'/>"/>
                    </s:if>

                    <div class="form-group">
                        <label class="control-label col-md-2">大简介图(750*250)</label>
                        <div class="col-md-5">

                            <!--single-->
                            <div class="plupload_container" style="position: relative;">
                                <div id="KaDanBigImg" class="btn btn-info" style="position: relative; z-index: 1;">  <i class="fa fa-plus"></i> 上传文件 <span class="info"></span></div>
                            </div>
                            <img src="<s:property value='#request.ProductKaDan.img_big'/>" style="display:none;"  alt="" id="cardImgBig" height="100">
                            <input type="hidden" id="cardImgBigUrl" name="productKaDan.img_big" value="<s:property value='#request.ProductKaDan.img_big'/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-2">小简介图(200*200)</label>
                        <div class="col-md-5">

                            <!--single-->
                            <div class="plupload_container" style="position: relative;">
                                <div id="KaDanBigSmall" class="btn btn-info" style="position: relative; z-index: 1;">  <i class="fa fa-plus"></i> 上传文件 <span class="info"></span></div>
                            </div>
                            <img src="<s:property value='#request.ProductKaDan.img_small'/>" style="display:none;"  alt="" id="cardImgSmall" height="100">
                            <input type="hidden" id="cardImgSmallUrl" name="productKaDan.img_small" value="<s:property value='#request.ProductKaDan.img_small'/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-2">产品名称</label>
                        <div class="col-md-5">
                            <input name="productKaDan.product.title" value="<s:property value='#request.ProductKaDan.product.title'/>" type="text" class="form-control">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-md-2">产品描述summary</label>
                        <div class="col-md-5">
                            <input name="productKaDan.product.summary" value="<s:property value='#request.ProductKaDan.product.summary'/>" type="text" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-2">卡单描述description</label>
                        <div class="col-md-5">
                            <input name="productKaDan.description" value="<s:property value='#request.ProductKaDan.description'/>" type="text" class="form-control">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-md-2">价格</label>
                        <div class="col-md-5">
                            <input name="productKaDan.price" value="<s:property value='#request.ProductKaDan.price'/>" type="text" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-2 control-label">提供商</label>
                        <div class="col-md-5">
                            <select name="productKaDan.product.supplier_id" class="chosen-select chosen-transparent form-control">

                                <s:iterator value='#request.SupplierArrayList' var="v">
                                    <option value='<s:property value='#v.id'/>' <s:if test='#v.id == #request.ProductKaDan.product.supplier_id'>selected</s:if> >
                                        <s:property value='#v.title'/>
                                    </option>
                                </s:iterator>

                            </select>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-md-2">产品分类</label>
                        <div class="col-md-5">
                            <input name="productKaDan.product.cate_id" id="ProductCateId" value="<s:property value='#request.ProductKaDan.product.cate_id'/>" type="hidden" class="form-control">
                            <ul id="ProductCateTree" class="ztree tile color dutch"></ul>
                        </div>
                    </div>



                    <div class="form-group">
                        <label class="control-label col-md-2">投保要求</label>
                        <div class="col-md-5">
                            <input name="productKaDan.conditions" value="<s:property value='#request.ProductKaDan.conditions'/>" type="text" class="form-control">
                        </div>
                    </div>

                    <div class="form-group transparent-editor">
                        <label class="col-md-2 control-label">Html信息</label>
                        <div class="col-md-5">
                            <input  name="productKaDan.html_info" class="form-control" id="HtmlInfo" value="<s:property value='#request.ProductKaDan.html_info'/>"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-2">开始时间</label>
                        <div class="col-md-5">
                            <div class="input-group date form_datetime">
                                <input type="text" size="16" name="productKaDan.product.start_date" readonly="" class="form-control"
                                       placeholder="开始时间" value="<s:property value='#request.ProductKaDan.product.start_date'/>">
                                <span class="input-group-btn">
                                    <button class="btn default date-set" type="button"><i class="fa fa-calendar"></i></button>
                                </span>
                            </div>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-md-2">结束时间</label>
                        <div class="col-md-5">
                            <div class="input-group date form_datetime">
                                <input type="text" size="16" name="productKaDan.product.end_date" readonly="" class="form-control"
                                       placeholder="结束时间"  value="<s:property value='#request.ProductKaDan.product.end_date'/>">
                                <span class="input-group-btn">
                                    <button class="btn default date-set" type="button"><i class="fa fa-calendar"></i></button>
                                </span>
                            </div>
                        </div>
                    </div>


                    <div class="form-group form-footer">
                        <div class="col-md-offset-2 col-md-10">
                            <button type="submit" class="btn btn-primary" >Submit</button>
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

    <script type="text/javascript" src="<%=request.getContextPath()%>/static/admin/public/js/plupload.full.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/admin/public/js/ap-fileupload.js"></script>

    <script type="text/javascript" src="<%=request.getContextPath()%>/static/admin/public/vendor/bootstrap/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/admin/public/vendor/bootstrap/bootstrap-datetimepicker.zh-CN.js"></script>

    <script type="text/javascript" src="<%=request.getContextPath()%>/static/admin/public/vendor/ztree/jquery.ztree.core-3.5.min.js"></script>

    <script src="<%=request.getContextPath()%>/static/admin/public/vendor/summernote/summernote.min.js"></script>

    <script type="text/javascript" src="<%=request.getContextPath()%>/static/admin/public/js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/admin/productkadan/js/formvalidation.js"></script>

    <script src="<%=request.getContextPath()%>/static/admin/public/js/ap-editor.js"></script>
    <script src="<%=request.getContextPath()%>/static/admin/public/js/ap-ztree.js"></script>
</rapid:override>

<rapid:override name="page_script">
    <script type="text/javascript">
        //图片上传组件初始化
        apSingleFileuploadInit('KaDanBigImg', '<%=request.getContextPath()%>');
        apSingleFileuploadInit('KaDanBigSmall', '<%=request.getContextPath()%>');

        //下拉框组件初始化
        $(".chosen-select").chosen({disable_search_threshold: 10});

        //日期组件初始化
        $(".form_datetime").datetimepicker({
            language : 'zh-CN',
            autoclose : true,
            format : "yyyy-mm-dd",
            pickerPosition : "top-left",
            todayBtn : 1,
            todayHighlight : true,
            minuteStep : 1
        });


        //初始化富文本编辑器
        apEditorInit('HtmlInfo');

        //初始化产品种类树
        var nodesJson = <s:property value='#request.jsonTree' escapeHtml='false'/>;
        var selectNodeId =  $('#ProductCateId').val();
        apSingleSelectTreeInit(nodesJson, selectNodeId);


    </script>
</rapid:override>

<rapid:override name="page_footer">

</rapid:override>

<jsp:include page="../public/base_template.jsp"></jsp:include>