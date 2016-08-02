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

            var deptno_flag = false;//代表deptno是否通过检测
            function checkDeptno(){
            	deptno_flag = false;
            	//每次点击按钮后先将提示信息还原
            	$("#deptno_error").html("*数字编号");
            	$("#deptno_error").removeClass("required");
            	$("#dname_error").html("*不能包含符号,只能包含汉子和字母");
            	$("#dname_error").removeClass("required");
            	//检查部门编号和部门名是否为空
            	var v_deptno = $("#deptno").val();
            	var v_dname = $("#dname").val();
            	if(v_deptno == ""){
            		$("#deptno_error").html("部门编号不能为空");
            		$("#deptno_error").addClass("required");
            		return false;//此处将false作为doSubmit函数返回值
            	}else if(v_dname == ""){
            		$("#dname_error").html("部门名称不能为空");
            		$("#dname_error").addClass("required");
            		return false;
            	}
            	//检查部门编号是否重复
            	//ajax(/fee/check/name)-->CheckNameController-->CostMapperDao-->json(返回boolean值)
            	$.ajax({
				   type: "get",
				   async: false,
				   url: "checkDeptno/"+v_deptno,
				   success: function(ok){
				     if(ok){
				     	$("#deptno_error").html("部门编号可用");
				     	$("#deptno_error").removeClass("required");
				     	deptno_flag = true;//允许提交
				     }else{
	            		$("#deptno_error").html("部门编号被占用");
	            		$("#deptno_error").addClass("required");
	            		deptno_flag = false;
	            		//注意:此处用return false只是退出当前回调函数,
				     	//没有将false作为doSubmit函数的返回值,
				     	//因此不能阻止表单提交
				     }
				   }
				});
            	//通过检查返回true,未通过返回false
            	return deptno_flag;
            	
            }
       
            function doSubmit(){
            	var flag =  checkDeptno();
            	return flag;//检测资费名
            	
            	//return false;//阻止提交
            	//return true;//允许提交
            }
        </script>
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="../images/logo.png" alt="logo" class="left"/>
            <span>当前账号：<b>${real_name}</b></span>
            <a href="/Enterprise-Security/login/toLogin">[退出]</a>             
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">
            <ul id="menu">
                <li><a href="../login/toIndex" class="index_off"></a></li>
                <li><a href="../admin/admin_list/1" class="admin_off"></a></li>
                <li><a href="../emp/emp_list/1" class="role_off"></a></li>
                <li><a href="dept_list/1" class="fee_on"></a></li>
                <li><a href="../admin/tomodifypwd" class="password_off"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <!-- 相对路径 -->
            <form action="add" onsubmit="return doSubmit()" method="post" class="main_form">
                <div class="text_info clearfix"><span>部门编号：</span></div>
                <div class="input_info">
                    <input type="text"  name="deptno" id="deptno" class="width100"/>
                    <span  id="deptno_error" >*数字编号</span>
                </div>
                <div class="text_info clearfix"><span>部门名称：</span></div>
                <div class="input_info ">
                    <input type="text" name="dname"  id="dname"  />
                    <span  id="dname_error">*不能包含符号,只能包含汉子和字母</span>
                </div>
                <div class="text_info clearfix"><span>部门地点：</span></div>
                <div class="input_info">
                    <input type="text" name="loc"  id="loc" />
                    <span  id="loc_error">*城市名称</span>
                </div>
                <div class="text_info clearfix"><span>部门经理：</span></div>
                <div class="input_info">
                    <input type="text" name="manager"  id="manager" />
                    <span  id="manager_error">*部门经理名字</span>
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
