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
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
    NavigaterUtils navigaterUtils = (NavigaterUtils) ctx.getBean("navigaterUtils");
    navigaterUtils.refresh(request, "productcate", "list");
%>


<rapid:override name="page_head">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/admin/public/vendor/ztree/css/metroStyle.css" type="text/css">
</rapid:override>


<rapid:override name="page_main">
    <div class="col-md-12">

        <!-- tile -->
        <section class="tile color transparent-black">

            <!-- tile header -->
            <div class="tile-header">
                <h1><strong>产品类别</strong>树</h1>
            </div>
            <!-- /tile header -->

            <!-- tile body -->
            <div class="tile-body">
                <ul id="ProductCateTree" class="ztree tile color dutch"></ul>
            </div>
            <!-- /tile body -->

        </section>

        <!-- /tile -->

    </div>

</rapid:override>


<rapid:override name="page_script_include">
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/admin/public/vendor/ztree/jquery.ztree.core-3.5.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/admin/public/vendor/ztree/jquery.ztree.exedit-3.5.min.js"></script>
</rapid:override>

<rapid:override name="page_script">
    <script type="text/javascript">
        var setting = {
            view: {
                addHoverDom: zTreeAddHoverDom,
                removeHoverDom: zTreeRemoveHoverDom,
                selectedMulti: false
            },
            edit: {
                enable: true,
                showRemoveBtn:zTreeShowRemoveBtn,
                showRenameBtn: zTreeShowRenameBtn,
                editNameSelectAll: true,
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                beforeDrag: zTreeBeforeDrag,
                beforeEditName: zTreeBeforeEditName,
                beforeRemove: zTreeBeforeRemove,
                beforeRename: zTreeBeforeRename,
                onRemove: zTreeOnRemove,
                onRename: zTreeOnRename,
                beforeClick: zTreeBeforeClick
            }
        };

        var zNodes = <s:property value='#request.jsonTree' escapeHtml='false'/>;

        function zTreeShowRemoveBtn(treeId, treeNode) {
            return !(treeNode.parentTId == null);
        }
        function zTreeShowRenameBtn(treeId, treeNode) {
            return !(treeNode.parentTId == null);
        }



        var log, className = "dark";
        function zTreeBeforeDrag(treeId, treeNodes) {
            return false;
        }
        function zTreeBeforeEditName(treeId, treeNode) {
            className = (className === "dark" ? "":"dark");
            zTreeShowLog("[ "+zTreeGetTime()+" beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
            var zTree = $.fn.zTree.getZTreeObj("ProductCateTree");
            zTree.selectNode(treeNode);

            return confirm("进入节点 -- " + treeNode.name + " 的编辑状态吗？");
        }
        function zTreeBeforeRemove(treeId, treeNode) {
            className = (className === "dark" ? "":"dark");
            zTreeShowLog("[ "+zTreeGetTime()+" beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
            var zTree = $.fn.zTree.getZTreeObj("ProductCateTree");
            zTree.selectNode(treeNode);
            return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
        }
        function zTreeOnRemove(e, treeId, treeNode) {
            $.ajax({
                type:"post",
                url:"<%=request.getContextPath()%>/admin/productcate/delete?id="+treeNode.id,
                success:function(date){
                    alert(date.message);
                },
                dataType: 'json'
            });
        }
        function zTreeBeforeRename(treeId, treeNode, newName, isCancel) {
            className = (className === "dark" ? "":"dark");
            zTreeShowLog((isCancel ? "<span style='color:red'>":"") + "[ "+zTreeGetTime()+" beforeRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
            if (newName.length == 0) {
                alert("节点名称不能为空.");
                var zTree = $.fn.zTree.getZTreeObj("ProductCateTree");
                setTimeout(function(){zTree.editName(treeNode)}, 10);
                return false;
            }
            return true;
        }
        function zTreeOnRename(e, treeId, treeNode, isCancel) {
            var parent = treeNode.getParentNode();
            treeNode.pId = parent.id;
            if(!isCancel){
                $.ajax({
                    type:"post",
                    url:"<%=request.getContextPath()%>/admin/productcate/saveorupdate?id="+treeNode.id+"&pId="+treeNode.pId+"&name="+treeNode.name,
                    success:function(date){
                        alert(date.message);
                    },
                    dataType: 'json'
                });
            }
        }
        function zTreeShowLog(str) {
            if (!log) log = $("#log");
            log.append("<li class='"+className+"'>"+str+"</li>");
            if(log.children("li").length > 8) {
                log.get(0).removeChild(log.children("li")[0]);
            }
        }
        function zTreeGetTime() {
            var now= new Date(),
                    h=now.getHours(),
                    m=now.getMinutes(),
                    s=now.getSeconds(),
                    ms=now.getMilliseconds();
            return (h+":"+m+":"+s+ " " +ms);
        }

        function zTreeBeforeClick(treeId, treeNode, clickFlag){
            if(treeNode.isParent){
                var zTree = $.fn.zTree.getZTreeObj("ProductCateTree");
                zTree.expandNode(treeNode);
                return false;
            }
        }

        var newCount = 1;
        function zTreeAddHoverDom(treeId, treeNode) {
            var sObj = $("#" + treeNode.tId + "_span");
            if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
            var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                    + "' title='add node' onfocus='this.blur();'></span>";
            sObj.after(addStr);
            var btn = $("#addBtn_"+treeNode.tId);
            if (btn) btn.bind("click", function(){
                var zTree = $.fn.zTree.getZTreeObj("ProductCateTree");
                zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
                return false;
            });
        };
        function zTreeRemoveHoverDom(treeId, treeNode) {
            $("#addBtn_"+treeNode.tId).unbind().remove();
        };

        $(document).ready(function(){
            for(var i=0;i<zNodes.length;i++){
                if(!zNodes[i].pId){
                    zNodes[i].open = true;
                    break;
                }
            }
            $.fn.zTree.init($("#ProductCateTree"), setting, zNodes);
        });
    </script>
</rapid:override>

<jsp:include page="../public/base_template.jsp"></jsp:include>