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
  </head>
  
  <body>
  
  <% String message=(String)request.getAttribute("message");if(message!=null){ %>
  	<div><%=message %></div>
  <% } %>
    <form action="<%=basePath %>login" method="post">
    	Username:
    	<input type="text" name="username"   <% String name=(String)request.getAttribute("username");if(name!=null){ %> value="<%=name %>"  <% } %>/>
    	<br />
    	Password:
    	<input type="password" name="password" />
    	<br />
		<input type="submit" value="Submit" />
    </form>
  </body>
</html>
