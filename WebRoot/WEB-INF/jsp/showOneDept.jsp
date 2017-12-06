<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'welcome.jsp' starting page</title>
  </head>
  
  <body>
    welcome, springmvc0630. <br/>
	控制器消息为：${info}
	<table border="1">
    	<thead>
    		<tr>
    			<td>部门id</td>
    			<td>部门名称</td>
    			<td>所在城市</td>
    		</tr>
    	</thead>
    	<tbody>
    		<tr>
    			<td>${staff.sid}</td>
    			<td>${staff.sname}</td>
    			<td>${staff.smobile}</td>
    		</tr>
    	</tbody>
    </table>
  </body>
</html>
