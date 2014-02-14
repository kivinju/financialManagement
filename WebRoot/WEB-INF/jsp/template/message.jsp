<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title><%=request.getAttribute("title") %></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	var secs=3;
	var URL;
	function Load(url){
		URL=url;
		for(var i=secs;i>=0;i--){
			window.setTimeout('doUpdate('+i+')', (secs-i)*1000);
		}
	}
	function doUpdate(num){
		document.getElementById('ShowDiv').innerHTML='This page will redirect to <a href="<%=request.getAttribute("redirect") %>" ><%=request.getAttribute("redirect") %></a> in '+num+'s';
		if(num==0){
			window.location=URL;
		}
	}
	Load("<%=request.getAttribute("redirect") %>");
</script>
  </head>
  
  <body>
    <%@ include file="../template/header.jsp"%>
    
    <div><%=request.getAttribute("message") %></div>
    
    <div id="ShowDiv"></div>
    <%@ include file="../template/footer.jsp"%>
  </body>
</html>
