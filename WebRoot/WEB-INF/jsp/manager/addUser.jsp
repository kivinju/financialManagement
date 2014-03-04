<%@ page language="java" import="java.util.*,cn.edu.nju.entity.User" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Add User</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css">
	<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
  </head>
  
  <body>
    <%@ include file="../template/header.jsp"%>
    
    <form action="manager/addUser" method="POST">
    	Name:<input type="text" name="name" value="${user.uname}"/><br />
    	Password:<input type="password" name="password" value="${user.password}"/><br />
    	Password Confirm:<input type="password" name="confirm"/><br />
    	Card Number:<input type="text" name="cardnum" value="${user.cardnum}"/><br />
    	Bank Number:<input type="text" name="banknum" value="${user.banknum}"/><br />
    	Role:<select name="role">
			<option value="<%=User.ROLE_MANAGER %>" <c:if test="${user.role==0}">selected="selected"</c:if> >Manager</option>
			<option value="<%=User.ROLE_USER %>" <c:if test="${user.role==1}">selected="selected"</c:if> >User</option>
			<option value="<%=User.ROLE_CHECKER %>" <c:if test="${user.role==2}">selected="selected"</c:if> >Checker</option>
			<option value="<%=User.ROLE_DIRECTOR %>" <c:if test="${user.role==3}">selected="selected"</c:if> >Director</option>
		</select><br />
		<input type="submit" value="submit" />
	</form>
    <div class="warning"><% String s=(String)request.getAttribute("message");if(s!=null)out.print(s); %></div>
    
    <%@ include file="../template/footer.jsp"%>
  </body>
</html>
