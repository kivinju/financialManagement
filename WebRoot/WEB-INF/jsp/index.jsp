<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Welcome!</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css">
	<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
  </head>
  
  <body>
  <div class="container">
    <form action="<%=basePath %>login" method="post"  class="well">
    	<h2>Online Reimbursement System</h2>
    	<table id="login_table" >
    	<tr>
    	<td>Username:</td>
    	<td><input type="text" name="username"   <% String name=(String)request.getAttribute("username");if(name!=null){ %> value="<%=name %>"  <% } %>/></td>
    	</tr>
    	<tr>
    	<td>Password:</td>
    	<td><input type="password" name="password" /></td>
		</tr>
		<tr>
		<td colspan="2"><input type="submit" value="Submit"  id="login_submit"/></td>
    	</tr>
    	<tr>
    	<td colspan="2">
  <% String message=(String)request.getAttribute("message");if(message!=null){ %>
  	<div class="warning"><%=message %></div>
  <% } %></td>
    	</tr>
    	</table>
    	
    </form>
    <%@ include file="template/footer.jsp"%>
  </body>
</html>
