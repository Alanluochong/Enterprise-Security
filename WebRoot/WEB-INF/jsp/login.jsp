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
        		var name=$("#admin_code").val();
        		var password=$("#password").val();
        		var code=$("#code").val();
        		if(code==""){
        			$("#validateError").html("请输入验证码!");
        			return;
        		}
        		if(name==""){
        			$("#error").html("用户名不能为空");
        			return;
        		}else if(password==""){
        			$("#error").html("密码不能为空");
        		}
        		//TODO登陆检查
        		//发送请求
        		$.ajax({
        			url:"/Enterprise-Security/login/login/"+code,
        			type:"get",
        			beforeSend:function(xhr){
        				//将用户名和密码放入HTTP请求的header部分
        				xhr.setRequestHeader("name",name);
        				xhr.setRequestHeader("password",password);
        			},
        			success:function(data){//data为服务端返回的数据
        				var ok=data.login;
        				if(ok){
        					window.location="/Enterprise-Security/login/toIndex";
        				}else{
        					$("#validateError").html(data.validateError);
        					$("#error").html(data.error);
        					//刷新验证码信息
        					$("#num").attr("src","/Enterprise-Security/login/getCode/"+new Date().getTime());
        				}
        			}
        		});
        	}
        </script>
    </head>
    <body class="index">
    	<form id="loginForm">
        <div class="login_box">
            <table>
                <tr>
                    <td class="login_info">账号：</td>
                    <td colspan="2"><input name="admin_code" id="admin_code" type="text" class="width150" /></td>
                    <td class="login_error_info"><span class="required">30长度的字母、数字和下划线</span></td>
                </tr>
                <tr>
                    <td class="login_info">密码：</td>
                    <td colspan="2"><input name="password" id="password" type="password" class="width150" /></td>
                    <td><span class="required">30长度的字母、数字和下划线</span></td>
                </tr>
                <tr>
                    <td class="login_info">验证码：</td>
                    <td class="width70"><input name="validate" id="code" type="text" class="width70" /></td>
                    <td>
                    <a href="javascript:;" onclick="document.getElementById('num').src ='getCode/'+(new Date()).getTime();">
                    <img id="num" src="getCode/1" alt="验证码" title="点击更换"/></a></td>  
                    <td><span class="required" id="validateError"></span></td>              
                </tr>            
                <tr>
                    <td></td>
                    <td class="login_button" colspan="2">
                        <a href="javaScript:;" onclick="doSubmit();"><img src="../images/login_btn.png" /></a>
                    </td>    
                    <td><span class="required" id="error"></span></td>                
                </tr>
            </table>
        </div>
        </form>
    </body>
</html>
