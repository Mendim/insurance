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
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%
    ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
    NavigaterUtils navigaterUtils = (NavigaterUtils)ctx.getBean("navigaterUtils");
    navigaterUtils.refresh(request, "member", "list");
%>

<rapid:override name="page_main">

    <div class="col-md-12">

        <!-- tile -->
        <section class="tile color transparent-black">

            <!-- tile header -->
            <div class="tile-header">
                <h1><strong>保险业务员</strong><s:property value='#request.Member.realname'/>的信息</h1>
            </div>
            <!-- /tile header -->

            <!-- tile body -->
            <div class="tile-body">

                <form class="form-horizontal" role="form" method="post"
                    action='<%=request.getContextPath()%>/admin/member/profile'>

                    <input type="hidden" name="member.id" class="form-control"
                           value="<s:property value='#request.Member.id'/>">

                    <div class="row">
                        <div class="col-md-8">

                            <div class="form-group">
                                <label class="col-md-4 control-label">用户Nickname</label>
                                <div class="col-md-8">
                                    <input type="text" name="member.username" class="form-control"
                                           value="<s:property value='#request.Member.username'/>"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">头像(200*200)</label>
                                <div class="col-md-8">
                                    <div class="plupload_container" style="position: relative;">
                                        <div id="MemberHeadImg" class="btn btn-info" style="position: relative; z-index: 1;"><i class="fa fa-plus"></i> 上传文件 <span class="info"></span></div>
                                    </div>
                                    <img src="<s:property value='#request.Member.headimg'/>" style="display:none;"  alt=""  height="100">
                                    <input type="hidden" name="member.headimg" value="<s:property value='#request.Member.headimg'/>">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">身份证号码</label>
                                <div class="col-md-8">
                                    <input type="text" name="member.sn" class="form-control"
                                           value="<s:property value='#request.Member.sn'/>"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">服务城市</label>
                                <div class="col-md-8">
                                    <input type="text" name="member.city" class="form-control"
                                           value="<s:property value='#request.Member.city'/>"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">手机</label>
                                <div class="col-md-8">
                                    <input type="text" name="member.mobile" class="form-control"
                                           value="<s:property value='#request.Member.mobile'/>"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">电话</label>
                                <div class="col-md-8">
                                    <input type="text" name="member.tel_num" class="form-control"
                                           value="<s:property value='#request.Member.tel_num'/>"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">QQ</label>
                                <div class="col-md-8">
                                    <input type="text" name="member.qq" class="form-control"
                                           value="<s:property value='#request.Member.qq'/>"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">Email</label>
                                <div class="col-md-8">
                                    <input type="text" name="member.email" class="form-control"
                                           value="<s:property value='#request.Member.email'/>"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">传真</label>
                                <div class="col-md-8">
                                    <input type="text" name="member.fax" class="form-control"
                                           value="<s:property value='#request.Member.fax'/>"/>
                                </div>
                            </div>

                        </div>

                        <div class="col-md-4">
                            <section class="tile color transparent-black textured">
                                <!-- tile header -->
                                <div class="tile-header">
                                    <h1><strong>详细信息</strong></h1>
                                </div>
                                <!-- /tile header -->

                                <!-- tile body -->
                                <div class="tile-body">
                                    <div class="row">
                                        <div class="col-md-4">真实姓名:</div>
                                        <div class="col-md-8"><s:property value='#request.Member.realname'/></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">星级:</div>
                                        <div class="col-md-8"><s:property value='#request.Member.star'/></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">登陆次数:</div>
                                        <div class="col-md-8"><s:property value='#request.Member.logintimes'/>次</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">注册IP:</div>
                                        <div class="col-md-8"><s:property value='#request.Member.reg_ip'/></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">最后登陆IP:</div>
                                        <div class="col-md-8"><s:property value='#request.Member.last_login_ip'/></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">注册时间:</div>
                                        <div class="col-md-8"><s:date name='#request.Member.reg_time' format="yyyy-MM-DD HH:mm:ss"/></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">最后登陆时间:</div>
                                        <div class="col-md-8"><s:date name='#request.Member.last_login_time' format="yyyy-MM-DD HH:mm:ss"/></div>
                                    </div>
                                </div>
                                <!-- /tile body -->

                            </section>
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
        apSingleFileuploadInit('MemberHeadImg', '<%=request.getContextPath()%>');
    </script>

</rapid:override>

<jsp:include page="../public/base_template.jsp"></jsp:include>