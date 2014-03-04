<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=request.getAttribute("title") %></title>

	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css">
	<link rel="stylesheet" type="text/css" href="css/manager/home.css">
	<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="../template/header.jsp"%>
<table>
<tr>
<td>
<a href="<%=basePath %>usermanage"  class="btn btn-primary btn-lg" role="button">usermanage</a>
<!-- </td>
<td>manager can add user, revise user or delete user!</td>
 -->
</tr>
<tr>
<td>
<a href="<%=basePath %>projectmanage"  class="btn btn-primary btn-lg" role="button">projectmanage</a>
</td>
<!--<td>
manager can add project, revise project or delete project!
<td>-->
</tr>
</table>
<%@ include file="../template/footer.jsp"%>
</body>
</html>