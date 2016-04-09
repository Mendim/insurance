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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/admin/public/vendor/ztree/css/metroStyle.css" type="text/css">
    <style type="text/css">
        .ztree * {
            font-size: 15px !important;
            color: #FFFFFF;
        }
    </style>
</rapid:override>


<rapid:override name="page_main">
    <div class="col-md-12">

        <!-- tile -->
        <section class="tile color transparent-black">

            <!-- tile header -->
            <div class="tile-header">
                <h1><strong>添加</strong>管理组</h1>

            </div>
            <!-- /tile header -->

            <!-- tile body -->
            <div class="tile-body">

                <form class="form-horizontal" role="form" action="<%=request.getContextPath()%>/admin/authgroup/addorprofile" method="post">

                    <s:if test="#request.AuthGroup != null">
                        <input type="hidden" name="authGroup.id" value="<s:property value='#request.AuthGroup.id'/>"/>
                    </s:if>

                    <div class="form-group">
                        <label class="col-md-2 control-label">组名</label>
                        <div class="col-md-5">
                            <input type="text" name="authGroup.name" class="form-control"
                                   value="<s:property value='#request.AuthGroup.name'/>"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-2 control-label">组描述</label>
                        <div class="col-md-5">
                            <input type="text" name="authGroup.description" class="form-control"
                                   value="<s:property value='#request.AuthGroup.description'/>"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-2 control-label">组内权限</label>
                        <div class="col-md-5">
                            <input type="hidden" name="authGroup.authRule" id="authRule" value=""/>
                            <ul id="AuthTree" class="ztree tile color transparent-black"></ul>
                        </div>
                    </div>

                    <div class="form-group form-footer">
                        <div class="col-md-offset-2 col-md-10">
                            <button type="submit" class="btn btn-primary" onclick="javascript:getCheckNodes();">Submit</button>
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
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/admin/public/vendor/ztree/jquery.ztree.core-3.5.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/admin/public/vendor/ztree/jquery.ztree.excheck-3.5.min.js"></script>

    <script type="text/javascript" src="<%=request.getContextPath()%>/static/admin/public/js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/admin/authgroup/js/formvalidation.js"></script>
</rapid:override>

<rapid:override name="page_script">
    <script type="text/javascript">

        function getCheckNodes(){
            var zTreeObj = $.fn.zTree.getZTreeObj("AuthTree");
            //获取所有点击过的节点,将节点id拼接成字符串,以逗号隔开.
            var nodes = zTreeObj.getCheckedNodes();
            var allNodes = '';
            for(var i=0;i<nodes.length;i++){
                if(!nodes[i].children)
                    allNodes += (nodes[i].id + ',');
            }
            $('#authRule').val(allNodes);
        }

        $(function(){
            var setting = {
                check: {
                    enable: true
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                view: {
                    showIcon: false
                }
            };

            var zNodes = <s:property value='#request.jsonTree' escapeHtml='false'/>;

            $.fn.zTree.init($("#AuthTree"), setting, zNodes);
        });
    </script>
</rapid:override>

<jsp:include page="../public/base_template.jsp"></jsp:include>