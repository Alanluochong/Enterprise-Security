<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
            //保存成功的提示消息
            function checkDeptno() {
            	var deptno = $("#deptno").val();
            	$("#deptno_error").removeClass("green");
                if(deptno==""){
               	  $("#deptno_error").html("*员工所属部门编号不能为空");
               	  $("#deptno_error").addClass("red");
               	  return;
                }
                 $.ajax({
				   type: "get",
				   url: "../checkDeptno/"+deptno,
				   success: function(data){
				     if(data.deptno){
				     	$("#deptno_error").html("*"+data.dname);
				     	$("#deptno_error").addClass("green");
				     }else{
	            		$("#deptno_error").html("*对不起,不存在此部门");
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
                <li><a href="../../login/toIndex" class="index_off"></a></li>
                <li><a href="../../admin/admin_list/1" class="admin_off"></a></li>
                <li><a href="../emp_list/1" class="role_on"></a></li>
                <li><a href="/Enterprise-Security/dept/dept_list/1" class="fee_off"></a></li>
                <li><a href="/Enterprise-Security/admin/tomodifypwd" class="password_off"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <div id="save_result_info" class="save_success">保存成功！</div>
             <!-- commandName指定表单显示的是Model中的哪个对象 -->
            <form:form commandName="emp" action="../${emp.id}" method="put" cssClass="main_form" >
            		 <div class="text_info clearfix"><span>ID：</span></div>
                    <div class="input_info">
                    	<form:input path="id" cssClass="readonly" readonly="true"/>
                    	 <span class="required">*</span>
                        <div class="validate_msg_medium error_msg">不可修改项</div>
                    </div>
                    
                    <div class="text_info clearfix"><span>姓名：</span></div>
                    <div class="input_info">
                    	<form:input path="ename"  />
                        <span >*20长度以内的汉字、字母、数字的组合</span>
                    </div>
                       <div class="text_info clearfix"><span>所属部门：</span></div>
                    <div class="input_info">
                    	<form:input path="deptno" id="deptno" onblur="checkDeptno();"/>
                        <span  id="deptno_error">*填写部门所属编号</span>
                    </div>
                    <div class="text_info clearfix"><span>电话号码：</span></div>
                    <div class="input_info">
                    <form:input path="phone"   />
                     <span >*11位以内数字组合</span>
                    </div>
                     <div class="text_info clearfix"><span>身份证号：</span></div>
                    <div class="input_info">
                    <form:input path="idcard_no" readonly="true" cssClass="readonly" class="width300" />
                     <span class="required">*</span>
                        <div class="validate_msg_medium error_msg">不可修改此项</div>
                    </div>
                    <div class="text_info clearfix"><span>薪资：</span></div>
                    <div class="input_info">
                    	<form:input path="sal" />
                        <span >*员工的月薪资</span>
                    </div>
                    <div class="text_info clearfix"><span>Email：</span></div>
                    <div class="input_info">
                    	<form:input path="email" class="width200" />
                        <span >*50长度以内，正确的 email 格式</span>
                    </div>
                    <div class="text_info clearfix"><span>性别：</span></div>
                     <div class="input_info">
                     	<form:input path="gender" readonly="true" cssClass="readonly" />
                     	 <span class="required">*</span>
                        <div class="validate_msg_medium error_msg">不可修改项</div>
                    </div>
                       <div class="text_info clearfix"><span>授权日期：</span></div>
                    <div class="input_info">
                    	<form:input path="entrydate" cssClass="readonly" readonly="true" />
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
            <span>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</span>
            <br />
            <span>版权所有(C)加拿大达内IT培训集团公司 </span>
        </div>
    </body>
</html>
