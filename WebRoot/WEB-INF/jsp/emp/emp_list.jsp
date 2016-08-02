<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>企业人事管理系统</title>
        <link type="text/css" rel="stylesheet" media="all" href="/Enterprise-Security/styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="/Enterprise-Security/styles/global_color.css" /> 
        <script type="text/javascript" src="/Enterprise-Security/js/jquery-1.11.1.js"></script>
        <script language="javascript" type="text/javascript">
            //显示角色详细信息
            function showDetail(flag, a) {
                var detailDiv = a.parentNode.getElementsByTagName("div")[0];
                if (flag) {
                    detailDiv.style.display = "block";
                }
                else
                    detailDiv.style.display = "none";
            }
            //重置密码
            function resetPwd() {
                alert("请至少选择一条数据！");
                //document.getElementById("operate_result_info").style.display = "block";
            }
            //删除
            function deleteEmp(id) {
               var r = window.confirm("确定要删除此员工吗？");
                if(r){//确定删除
                //window.location="/netctoss/fee/"+id;//属于GET请求
                	$.ajax({
                		url:"/Enterprise-Security/emp/"+id,
                		type:"delete",
                		success:function(ok){
                			if(ok){
                			   window.location="/Enterprise-Security/emp/emp_list/"+${page.page};
                			}
                		}
                	});
                }
            }
            //全选
            function selectAdmins(inputObj) {
                var inputArray = document.getElementById("datalist").getElementsByTagName("input");
                for (var i = 1; i < inputArray.length; i++) {
                    if (inputArray[i].type == "checkbox") {
                        inputArray[i].checked = inputObj.checked;
                    }
                }
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
                <li><a href="/Enterprise-Security/admin/admin_list/1" class="admin_off"></a></li>
                <li><a href="1" class="role_on"></a></li>
                <li><a href="/Enterprise-Security/dept/dept_list/1" class="fee_off"></a></li>
                <li><a href="/Enterprise-Security/admin/tomodifypwd" class="password_off"></a></li>
            </ul>            
        </div>
      
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <form action="" method="">
                 <input type="button" align="right" value="增加" class="btn_add" onclick="location.href='../toAdd';" />
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th class="th_select_all">
                                <input type="checkbox" onclick="selectAdmins(this);" />
                                <span>全选</span>
                            </th>
                            <th>ID</th>
                            <th>姓名</th>
                            <th>薪资</th>
                            <th>年龄</th>
                            <th>性别</th>
                            <th>电话</th>
                            <th>电子邮件</th>
                            <th>身份证号</th>
                            <th>入职日期</th>
                            <th>所属部门</th>
                            <th></th>
                        </tr>   
                       <c:forEach items="${emps}" var="e">                  
                        <tr>
                            <td><input type="checkbox" /></td>
                            <td>${e.id }</td>
                            <td>${e.ename}</td>
                            <td>${e.sal }</td>
                            <td>${e.age}</td>
                            <td>${e.gender }</td>
                            <td>${e.phone }</td>
                            <td>${e.email}</td>
                            <td>${e.idcard_no}</td>
                            <td>${e.entrydate}</td>
                            <td>${e.dname}</td>
                            <td class="td_modi">
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='../${e.id}/toedit';" />
                                <input type="button" value="删除" class="btn_delete" onclick="deleteEmp(${e.id});" />
                            </td>
                        </tr>
                      </c:forEach>
                    </table>
                </div>
                <!--分页-->
               <div id="pages">
        	        <c:choose>
        	        	<c:when test="${page.page>1}">
        	        	<a href="${page.page-1 }">上一页</a>
        	        	</c:when>
        	        	<c:otherwise>
        	        	<a>上一页</a>
        	        	</c:otherwise>
        	        </c:choose>
        	        <!-- 循环显示页面序号 -->
        	        <c:forEach var="i" begin="1" end="${page.totalPage}">
        	        	<c:choose>
        	        		<c:when test="${i==page.page}">
        	        		<a href="${i}" class="current_page">${i}</a>
        	        		</c:when>
        	        		<c:otherwise>
        	        		<a href="${i}" >${i}</a>
        	        		</c:otherwise>
        	        	</c:choose>
        	        	
        	        </c:forEach>
                    <c:choose>
                    	<c:when test="${page.page<page.totalPage}">
                    	 <a href="${page.page+1 }">下一页</a>
                    	</c:when>
                    	<c:otherwise>
                    	<a>下一页</a>
                    	</c:otherwise>
                    </c:choose>
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
