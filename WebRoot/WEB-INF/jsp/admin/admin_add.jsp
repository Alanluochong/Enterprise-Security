<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>企业人事管理系统</title>
        <link type="text/css" rel="stylesheet" media="all" href="/Enterprise-Security/styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="/Enterprise-Security/styles/global_color.css" />
        <script type="text/javascript" src="/Enterprise-Security/js/jquery-1.11.1.js"></script>
        <script language="javascript" type="text/javascript">
            //保存成功的提示消息
            function checkPassword() {
               var password = $("#password").val();
               var password1 = $("#password1").val();
               if(password!=password1){
            		$("#password_error").html("两次输入密码不一致");
            		$("#password_error").addClass("required");
               }else{
               		$("#password_error").html("输入正确");
               		$("#password_error").removeClass("required");
               }
            }
            function  doSubmit(){
            	var login_name = $("#login_name").val();
            	if(login_name==""){
            		$("#login_name_error").html("用户名不能为空");
            		$("#login_name_error").addClass("required");
            		return;
            	}
            	$.ajax({
				   type: "get",
				   url: "checkLogin_name/"+login_name,
				   success: function(ok){
				     if(ok){
				     	$("#login_name_error").html("用户名可用");
				     	$("#login_name_error").removeClass("required");
				     }else{
	            		$("#login_name_error").html("用户名已存在");
	            		$("#login_name_error").addClass("required");
				     }
				   }
				});
            	
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
                <li><a href="../login/toIndex" class="index_off"></a></li>
                <li><a href="admin_list/1" class="admin_on"></a></li>
                <li><a href="../emp/emp_list/1" class="role_off"></a></li>
                <li><a href="/Enterprise-Security/dept/dept_list/1" class="fee_off"></a></li>
                <li><a href="/Enterprise-Security/admin/tomodifypwd" class="password_off"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <form action="add" method="post"  class="main_form">
                    <div class="text_info clearfix"><span>姓名：</span></div>
                    <div class="input_info">
                        <input type="text" name="real_name"/>
                        <span class="required">*</span>
                        <div class="validate_msg_long">20长度以内的汉字、字母、数字的组合</div>
                    </div>
                    <div class="text_info clearfix"><span>管理员账号：</span></div>
                    <div class="input_info">
                        <input type="text" name="login_name" id="login_name" onblur="doSubmit();"/>
                        <span  id="login_name_error">*30长度以内的字母、数字和下划线的组合,不可修改</span>
                    </div>
                    <div class="text_info clearfix"><span>密码：</span></div>
                    <div class="input_info">
                        <input type="password" name="password" id="password"/>
                        <span class="required">*</span>
                        <div class="validate_msg_long">30长度以内的字母、数字和下划线的组合</div>
                    </div>
                    <div class="text_info clearfix"><span>重复密码：</span></div>
                    <div class="input_info">
                        <input type="password"  id="password1" onblur="checkPassword();"/>
                        <span  id="password_error">*两次密码必须相同</span>
                    </div>      
                    <div class="text_info clearfix"><span>电话：</span></div>
                    <div class="input_info">
                    <input type="text" class="width200" name="phone"/>
                       <span class="required">*</span>
                    </div>
                    <div class="text_info clearfix"><span>Email：</span></div>
                    <div class="input_info">
                        <input type="text" class="width200" name="email"/>
                        <span class="required">*</span>
                    </div>
                     <div class="text_info clearfix"><span>身份证号：</span></div>
                    <div class="input_info">
                        <input type="text" class="width200" name="idcard_no"/>
                        <span class="required">*</span>
                    </div>
                     <div class="text_info clearfix"><span>性别：</span></div>
                    <div class="input_info">
                       	<label for="man">男</label>
                       	<input type="radio"  name="gender" id="man" value="0" checked="checked"/>
                        <label for="female">女</label>
                        <input type="radio"  name="gender" id="female" value="1"/>
                    </div>
                    <div class="button_info clearfix">
                        <input type="submit" value="保存" class="btn_save"  />
                        <input type="button" value="取消" class="btn_save" />
                    </div>
                </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <span>毕设项目,源自luo_c</span>
            <br />
            <span>版权所有(C)西安邮电大学 信息对抗技术专业 </span>
        </div>
    </body>
</html>
