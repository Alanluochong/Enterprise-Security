<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>企业人事管理系统</title>
        <link type="text/css" rel="stylesheet" media="all" href="/Enterprise-Security/styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="/Enterprise-Security/styles/global_color.css" />
        <script type="text/javascript" src="/Enterprise-Security/js/jquery-1.11.1.js">
		</script>
        <script language="javascript" type="text/javascript">
            //排序按钮的点击事件
            function sort(btnObj) {
                if (btnObj.className == "sort_desc")
                    btnObj.className = "sort_asc";
                else
                    btnObj.className = "sort_desc";
            }

		   //删除
            function deleteDept(id) {
                var r = window.confirm("确定要删除此部门信息？");
                if(r){//确定删除
                //window.location="/netctoss/fee/"+id;//属于GET请求
                	$.ajax({
                		url:"/Enterprise-Security/dept/"+id,
                		type:"delete",
                		success:function(ok){
                			if(ok){
                			   window.location="/Enterprise-Security/dept/dept_list/"+${page.page};
                			}
                		}
                	});
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
                <li><a href="../../admin/admin_list/1" class="admin_off"></a></li>
                <li><a href="/Enterprise-Security/emp/emp_list/1" class="role_off"></a></li>
                <li><a href="1" class="fee_on"></a></li>
                <li><a href="../../admin/tomodifypwd" class="password_off"></a></li>
            </ul>            
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <form action="" method="">
                <!--排序-->
                <div class="search_add">
                    <input type="button" value="增加" class="btn_add" onclick="location.href='../toAdd';" />
                </div> 
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th>部门编号</th>
                            <th class="width100">部门名称</th>
                            <th>部门地点</th>
                            <th>部门经理</th>
                            <th class="width200"></th>
                        </tr>
                      <c:forEach items="${depts}" var="d">                      
                        <tr>
                            <td>${d.deptno }</td>
                            <td>${d.dname }</td>
                            <td>${d.loc }</td>
                            <td>${d.manager} </td>
                            <td>  
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='../${d.deptno}/toedit';" />
                                <input type="button" value="删除" class="btn_delete" onclick="deleteDept(${d.deptno});"/>
                            </td>
                        </tr>
                      </c:forEach>
                        
                    </table>
                    <p>部门说明：<br />
                    	部门可能不在同一个地方.<br />其中部门名称和部门经理为加密字段;<br />
                    	部门分散在全国各地,每个部门应有一名部门经理;
                    </p>
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
           <span>毕设项目,源自luo_c</span>
            <br />
            <span>版权所有(C)西安邮电大学 信息对抗技术专业 </span>
        </div>
    </body>
</html>
