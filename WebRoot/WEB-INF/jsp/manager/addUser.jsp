<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

  </head>
  
  <body>
    <%@ include file="../template/header.jsp"%>
    
    <form action="manager/addUser" method="POST">
    	Name:<input type="text" name="name"/><br />
    	Password:<input type="password" name="password"/><br />
    	Password Confirm:<input type="password" name="confirm"/><br />
    	Card Number:<input type="text" name="cardnum"/><br />
    	Bank Number:<input type="text" name="banknum"/><br />
    	Role:<select name="role">
			<option value="<%=User.ROLE_MANAGER %>">Manager</option>
			<option value="<%=User.ROLE_USER %>">User</option>
			<option value="<%=User.ROLE_CHECKER %>">Checker</option>
			<option value="<%=User.ROLE_DIRECTOR %>">Director</option>
		</select><br />
		<input type="submit" value="submit" />
	</form>
    <div class="warning"><% String s=(String)request.getAttribute("message");if(s!=null)out.print(s); %></div>
    
    <%@ include file="../template/footer.jsp"%>
  </body>
</html>
