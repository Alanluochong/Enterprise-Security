<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>企业人事管理系统</title>
        <link type="text/css" rel="stylesheet" media="all" href="/Enterprise-Security/styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="/Enterprise-Security/styles/global_color.css" />
        <script type="text/javascript" src="/Enterprise-Security/js/jquery-1.11.1.js"></script>
        <style type="text/css" >
        	.red{color:red;font-weight: bold}
        	.green{color:green;font-weight: bold}
        </style>
        <script language="javascript" type="text/javascript">
            //保存成功的提示信息
            function checkDeptno() {
               var deptno = $("#deptno").val();
               if(deptno==""){
               	$("#deptno_error").html("员工所属部门编号不能为空");
               	$("#deptno_error").addClass("red");
               	return;
               }
               $.ajax({
				   type: "get",
				   url: "../emp/checkDeptno/"+deptno,
				   success: function(data){
				     if(data.deptno){
				     	$("#deptno_error").html(data.dname);
				     	$("#deptno_error").addClass("green");
				     }else{
	            		$("#deptno_error").html("对不起,不存在此部门");
	            		$("#deptno_error").removeClass("green");
	            		$("#deptno_error").addClass("red");
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
                <li><a href="../admin/admin_list/1" class="admin_off"></a></li>
                <li><a href="emp_list/1" class="role_on"></a></li>
                <li><a href="/Enterprise-Security/dept/dept_list/1" class="fee_off"></a></li>
                <li><a href="../admin/tomodifypwd" class="password_off"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">       
            <form action="add" method="post" class="main_form">
                <!--必填项-->
                
                <div class="text_info clearfix"><span>姓名：</span></div>
                <div class="input_info">
                    <input type="text" name="ename" id="ename"/>
                    <span class="required">*</span>
                    <div class="validate_msg_long">20长度以内的汉字、字母和数字的组合</div>
                </div>
                 <div class="text_info clearfix"><span>部门编号：</span></div>
                 <div class="input_info">
                    <input type="text" name="deptno" id="deptno" onblur="checkDeptno();"/>
                    <span class="required">*</span>
                    <div class="validate_msg_long" id="deptno_error">填写所属部门的编号</div>
                </div>             
                 <div class="text_info clearfix"><span>年龄：</span></div>
                <div class="input_info">
                    <input type="text" name="age" id="age"/>
                    <span class="required">*</span>
                    <div class="validate_msg_long">20长度以内的汉字、字母和数字的组合</div>
                </div>
                 <div class="text_info clearfix"><span>电话：</span></div>
                <div class="input_info">
                    <input type="text" name="phone" id="phone"/>
                    <span class="required">*</span>
                    <div class="validate_msg_long">正确的电话号码格式</div>
                </div>
                <div class="text_info clearfix"><span>身份证：</span></div>
                <div class="input_info">
                    <input type="text" name="idcard_no" id="idcard_no" />
                    <span class="required">*</span>
                    <div class="validate_msg_long">正确的身份证号码格式</div>
                </div>
                <div class="text_info clearfix"><span>电子邮箱：</span></div>
                <div class="input_info">
                    <input type="text" name="email" id="email"  />
                    <span class="required">*</span>
                    <div class="validate_msg_long">正确的电子邮箱格式</div>
                </div>
                <div class="text_info clearfix"><span>性别：</span></div>
                <div class="input_info">
                    <select name="gender" id="gender">
                    	<option value="男" selected="selected">男</option>
                    	<option value="女" >女</option>
                    </select>
                    <span class="required">*请选择一项</span>
                </div>     
                <div class="text_info clearfix"><span>薪资：</span></div>
                <div class="input_info">
                    <input type="text" class="width200" name="sal" id="sal"/>
                    <span class="required">*</span>
                    <div class="validate_msg_medium">员工的月薪水</div>
                </div>   
               
                <!--操作按钮-->
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
