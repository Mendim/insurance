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
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%
    ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
    NavigaterUtils navigaterUtils = (NavigaterUtils) ctx.getBean("navigaterUtils");
    navigaterUtils.refresh(request, "administrator", "list");
%>


<rapid:override name="page_head">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/static/admin/public/vendor/summernote/css/summernote.css">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/static/admin/public/vendor/summernote/css/summernote-bs3.css">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/admin/public/vendor/chosen/css/chosen.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/admin/public/vendor/chosen/css/chosen-bootstrap.css">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/admin/public/vendor/datepicker/css/bootstrap-datetimepicker.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/admin/public/vendor/colorpicker/css/bootstrap-colorpicker.css">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/admin/public/vendor/colorpalette/bootstrap-colorpalette.css">

</rapid:override>


<rapid:override name="page_main">
    <div class="col-md-8">

        <!-- tile -->
        <section class="tile color transparent-black">

            <!-- tile header -->
            <div class="tile-header">
                <h1><strong>Text</strong> Inputs</h1>
                <div class="controls">
                    <a href="form-elements.html#" class="minimize"><i class="fa fa-chevron-down"></i></a>
                    <a href="form-elements.html#" class="refresh"><i class="fa fa-refresh"></i></a>
                    <a href="form-elements.html#" class="remove"><i class="fa fa-times"></i></a>
                </div>
            </div>
            <!-- /tile header -->

            <!-- tile body -->
            <div class="tile-body">

                <form class="form-horizontal" role="form">

                    <div class="form-group">
                        <label for="input01" class="col-sm-4 control-label">Normal input field</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="input01">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="input02" class="col-sm-4 control-label">Password input field</label>
                        <div class="col-sm-8">
                            <input type="password" class="form-control" id="input02">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="input03" class="col-sm-4 control-label">Input with help text</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="input03">
                            <span class="help-block">A block of help text that breaks onto a new line and may extend beyond one line.</span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="input04" class="col-sm-4 control-label">Input field with placeholder</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="input04" placeholder="This is placeholder...">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="input05" class="col-sm-4 control-label">Normal textarea</label>
                        <div class="col-sm-8">
                            <textarea class="form-control" id="input05" rows="6"></textarea>
                        </div>
                    </div>

                    <div class="form-group transparent-editor">
                        <label class="col-sm-4 control-label">Textarea with editor</label>
                        <div class="col-sm-8">
                            <div class="form-control" id="input06"></div>
                        </div>
                    </div>

                    <div class="form-group form-footer">
                        <div class="col-sm-offset-4 col-sm-8">
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
    <script src="<%=request.getContextPath()%>/static/admin/public/vendor/summernote/summernote.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/admin/public/vendor/chosen/chosen.jquery.min.js"></script>
</rapid:override>

<rapid:override name="page_script">
    <script>
        $(function(){
            //load wysiwyg editor
            $('#input06').summernote({
                toolbar: [
                    //['style', ['style']], // no style button
                    ['style', ['bold', 'italic', 'underline', 'clear']],
                    ['fontsize', ['fontsize']],
                    ['color', ['color']],
                    ['para', ['ul', 'ol', 'paragraph']],
                    ['height', ['height']],
                    //['insert', ['picture', 'link']], // no insert buttons
                    //['table', ['table']], // no table button
                    //['help', ['help']] //no help button
                ],
                height: 137 //set editable area's height
            });
        })

    </script>

</rapid:override>

<jsp:include page="../public/base_template.jsp"></jsp:include>