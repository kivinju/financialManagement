<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=request.getAttribute("title") %></title>
</head>
<body>
<%@ include file="../template/header.jsp"%>

<a href="<%=basePath %>usermanage">usermanage</a>
<a href="<%=basePath %>projectmanage">projectmanage</a>

<%@ include file="../template/footer.jsp"%>
</body>
</html>