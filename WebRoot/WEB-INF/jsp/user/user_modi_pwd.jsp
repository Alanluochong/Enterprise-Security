<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>企业人事管理系统</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" /> 
        <script type="text/javascript" src="/Enterprise-Security/js/jquery-1.11.1.js">
		</script>
        <script type="text/javascript">
        	function doSubmit(){
        		//提交表单
        		var oldpwd=$("#oldpwd").val();
        		var newpwd=$("#newpwd").val();
        		var newpwd1=$("#newpwd1").val();
        		if(oldpwd==""){
        			$("#error").html("当前密码不能为空");
        			return;
        		}else if(newpwd==""){
        			$("#error").html("密码不能为空");
        			return;
        		}else if(newpwd1==""){
        			$("#error").html("密码不能为空");
        			return;
        		}else if(newpwd!==newpwd1){
        			$("#error").html("两次新密码输入不相同");
        			return;
        		}
        		//TODO更改检查
        		//发送请求
        		$.ajax({
        			url:"/Enterprise-Security/admin/modifypwd",
        			type:"put",
        			beforeSend:function(xhr){
        				//将用户名和密码放入HTTP请求的header部分
        				xhr.setRequestHeader("oldpwd",oldpwd);
        				xhr.setRequestHeader("newpwd",newpwd);
        			},
        			success:function(data){//data为服务端返回的数据
        				var ok=data.success;
        				if(ok){
        					$("#error").html(data.message);
        				}else{
        					$("#error").html(data.error);
        				}
        			}
        		});
        	}
        </script>
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="../images/logo.png" alt="logo" class="left"/>
            <span>当前用户：<b>${real_name}</b></span>
            <a href="/Enterprise-Security/login/toLogin">[退出]</a>            
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">
            <ul id="menu">
                <li><a href="../login/toIndex" class="index_off"></a></li>
                <li><a href="admin_list/1" class="admin_off"></a></li>
                <li><a href="../emp/emp_list/1" class="role_off"></a></li>
                <li><a href="/Enterprise-Security/dept/dept_list/1" class="fee_off"></a></li>
                <li><a href="/Enterprise-Security/admin/tomodifypwd" class="password_on"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <div id="main">      
            <!--保存操作后的提示信息：成功或者失败-->      
            <div id="save_result_info" class="save_success">保存成功！</div><!--保存失败，旧密码错误！-->
            <form action="" method="" class="main_form">
                <div class="text_info clearfix"><span>旧密码：</span></div>
                <div class="input_info">
                    <input type="password" class="width200" id="oldpwd" name="oldpwd" /><span class="required">*</span>
                    <div class="validate_msg_medium">30长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>新密码：</span></div>
                <div class="input_info">
                    <input type="password"  class="width200" id="newpwd" name="newpwd" /><span class="required">*</span>
                    <div class="validate_msg_medium">30长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>重复新密码：</span></div>
                <div class="input_info">
                    <input type="password" class="width200" id="newpwd1" name="newpwd1" /><span class="required">*</span>
                    <div class="validate_msg_medium" >两次新密码必须相同</div>
                    
                </div>
                <div class="validate_msg_medium">
                <span id="error" style="color:red;font-weight:bold">
                </span></div>
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save" onclick="doSubmit();" />
                    <input type="button" value="取消" class="btn_save" />
                </div>
            </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>毕设项目,源自luo_c</p>
            <p>版权所有(C)西安邮电大学 信息对抗技术专业 </p>
        </div>
    </body>
</html>
