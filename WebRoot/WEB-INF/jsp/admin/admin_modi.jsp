<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>企业人事管理系统</title>
        <link type="text/css" rel="stylesheet" media="all" href="/Enterprise-Security/styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="/Enterprise-Security/styles/global_color.css" />
        <script language="javascript" type="text/javascript">
            //保存成功的提示消息
            function showResult() {
                showResultDiv(true);
                window.setTimeout("showResultDiv(false);", 3000);
            }
            function showResultDiv(flag) {
                var divResult = document.getElementById("save_result_info");
                if (flag)
                    divResult.style.display = "block";
                else
                    divResult.style.display = "none";
            }
        </script>
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="/Enterprise-Security/images/logo.png" alt="logo" class="left"/>
            <span>当前用户：<b>${real_name}</b></span>
            <a href="/Enterprise-Security/login/toLogin">[退出]</a>            
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">
            <ul id="menu">
                <li><a href="../../login/toIndex" class="index_off"></a></li>
                <li><a href="../admin_list/1" class="admin_on"></a></li>
                <li><a href="/Enterprise-Security/emp/emp_list/1" class="role_off"></a></li>
                <li><a href="/Enterprise-Security/dept/dept_list/1" class="fee_off"></a></li>
                <li><a href="/Enterprise-Security/admin/tomodifypwd" class="password_off"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <div id="save_result_info" class="save_success">保存成功！</div>
             <!-- commandName指定表单显示的是Model中的哪个对象 -->
            <form:form commandName="adminInfo" action="../${adminInfo.id}" method="put" cssClass="main_form" >
            		 <div class="text_info clearfix"><span>ID：</span></div>
                    <div class="input_info">
                    	<form:input path="id" cssClass="readonly" readonly="true"/>
                    </div>
                    
                    <div class="text_info clearfix"><span>姓名：</span></div>
                    <div class="input_info">
                    	<form:input path="real_name" cssClass="width300" />
                        <span class="required">*20长度以内的汉字、字母、数字的组合</span>
                    </div>
                    <div class="text_info clearfix"><span>管理员账号：</span></div>
                    <div class="input_info">
                    <form:input path="login_name" readonly="true" class="readonly"/>
                     <span class="required">*</span>
                        <div class="validate_msg_medium error_msg">不可修改此项</div>
                    </div>
                     <div class="text_info clearfix"><span>身份证号：</span></div>
                    <div class="input_info">
                    <form:input path="idcard_no" readonly="true" cssClass="readonly"/>
                     <span class="required">*</span>
                        <div class="validate_msg_medium error_msg">不可修改此项</div>
                    </div>
                    <div class="text_info clearfix"><span>电话：</span></div>
                    <div class="input_info">
                    	<form:input path="phone" />
                        <span class="required">*正确的电话号码格式：手机或固话</span>
                    </div>
                    <div class="text_info clearfix"><span>Email：</span></div>
                    <div class="input_info">
                    	<form:input path="email" class="width200" />
                        <span class="required">*50长度以内，正确的 email 格式</span>
                    </div>
                    <div class="text_info clearfix"><span>密码：</span></div>
                     <div class="input_info">
                     	<form:password path="password" />
                     	 <span class="required">*</span>
                        <div class="validate_msg_medium error_msg">字母数字下划线的组合</div>
                    </div>
                       <div class="text_info clearfix"><span>授权日期：</span></div>
                    <div class="input_info">
                    	<form:input path="enrolldate" cssClass="readonly" readonly="true" />
                    	 <span class="required">*</span>
                        <div class="validate_msg_medium error_msg">此项不可修改</div>
                    </div>
                    <div class="button_info clearfix">
                        <input type="submit" value="保存" class="btn_save"  />
                        <input type="button" value="取消" class="btn_save" />
                    </div>
                </form:form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>毕设项目,源自luo_c</p>
            <p>版权所有(C)西安邮电大学 信息对抗技术专业 </p>
        </div>
    </body>
</html>
