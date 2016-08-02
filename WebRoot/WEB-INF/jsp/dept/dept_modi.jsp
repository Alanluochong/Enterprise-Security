<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>企业人事管理系统</title>
        <link type="text/css" rel="stylesheet" media="all" href="/Enterprise-Security/styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="/Enterprise-Security/styles/global_color.css" />
        <script type="text/javascript" src="/Enterprise-Security/js/jquery-1.11.1.js"></script>
        <script language="javascript" type="text/javascript">
            var name_flag = false;//代表name是否通过检测
            function checkDname(){
            	name_flag = false;
            	//检查资费名是否为空
            	var dname = $("#dname").val();
            	if(dname == ""){
            		$("#dname_error").html("资费名为空");
            		$("#dname_error").addClass("error_msg");
            		return false;//此处将false作为doSubmit函数返回值
            	}
            	//检查部门名是否重复
            	//ajax(/fee/check/name)-->CheckNameController-->CostMapperDao-->json(返回boolean值)
            	$.ajax({
				   type: "get",
				   async: false,
				   url: "../checkDname/"+dname,
				   success: function(ok){
				     if(ok){
				     	$("#dname_error").html("部门名称可用");
				     	name_flag = true;//允许提交
				     }else{
	            		$("#dname_error").html("部门名称被占用,换一个吧");
	            		$("#dname_error").addClass("error_msg");
	            		name_flag = false;
	            		//注意:此处用return false只是退出当前回调函数,
				     	//没有将false作为doSubmit函数的返回值,
				     	//因此不能阻止表单提交
				     }
				   }
				});
            	//通过检查返回true,未通过返回false
            	return name_flag;
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
                <li><a href="../../emp/emp_list/1" class="role_off"></a></li>
                <li><a href="../dept_list/1" class="fee_on"></a></li>
                <li><a href="../../admin/tomodifypwd" class="password_off"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <!-- commandName指定表单显示的是Model中的哪个对象 -->
            <form:form commandName="dept" action="../${dept.deptno}" onsubmit="return checkDname()" method="put" cssClass="main_form" >
                <div class="text_info clearfix"><span>部门编号：</span></div>
                <div class="input_info">
                <form:input path="deptno" cssClass="readonly" readonly="true"/>
                </div>
                <div class="text_info clearfix"><span>部门名称：</span></div>
                <div class="input_info">
                    <form:input path="dname" cssClass="width300" id="dname"/>
                    <span class="required">*</span>
                    <div class="validate_msg_short" id="dname_error">50长度的字母、数字、汉字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>部门所在地：</span></div>
                <div class="input_info">
                    <form:input path="loc" cssClass="width100"/>
                    <span >*城市名称</span>
                </div>
                <div class="text_info clearfix"><span>部门经理：</span></div>
                <div class="input_info">
                    <form:input path="manager" cssClass="width100"/>
                    <span >*部门负责人名字</span>
                </div>   
                <div class="button_info clearfix">
                    <input type="submit" value="保存" class="btn_save" />
                    <input type="button" value="取消" class="btn_save" />
                </div>
           </form:form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <span>毕设项目,源自luo_c</span>
            <br />
            <span>版权所有(C)西安邮电大学 信息对抗技术专业 </span>
        </div>
    </body>
</html>
