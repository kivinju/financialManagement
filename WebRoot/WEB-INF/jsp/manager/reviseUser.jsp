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
    
    <title>Revise User</title>
    
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
    
    	<h3 class="col-md-offset-3">Revise User</h3>
    <form action="manager/reviseUser" method="POST" class="form-horizontal" role="form">
    	<div class="form-group">
   	 		<label class="col-md-2 control-label col-md-offset-1">ID</label>
   	 		<div class="col-md-6">
  		  		<p class="form-control-static">${user.uid}</p>
    		</div>
    	</div>
    	<div class="form-group">
   	 		<label class="col-md-2 control-label col-md-offset-1">Name</label>
   	 		<div class="col-md-6">
  		  		<input type="text" name="name" value="${user.uname}"  class="form-control"  placeholder="user name"/>
    		</div>
    	</div>
    	<div class="form-group">
   	 		<label class="col-md-2 control-label col-md-offset-1">Password</label>
   	 		<div class="col-md-6">
  		  		<input type="password" name="password" value="${user.password}"  class="form-control"  placeholder="Ente your password"/>
    		</div>
    	</div>
    	<div class="form-group">
   	 		<label class="col-md-2 control-label col-md-offset-1">Password Confirm</label>
   	 		<div class="col-md-6">
    			<input type="password" name="confirm" class="form-control"  placeholder="Confirm your password"/>
			</div>
    	</div>
    	<div class="form-group">
   	 		<label class="col-md-2 control-label col-md-offset-1">Card Number</label>
   	 		<div class="col-md-6">
    			<input type="text" name="cardnum" value="${user.cardnum}"  class="form-control"  placeholder="Enter your seven-characters card number"/>
			</div>
    	</div>
    	<div class="form-group">
   	 		<label class="col-md-2 control-label col-md-offset-1">Bank Number</label>
   	 		<div class="col-md-6">
    			<input type="text" name="banknum" value="${user.banknum}"  class="form-control"  placeholder="Enter your bank number"/>
			</div>
    	</div>
    	<div class="form-group">
   	 		<label class="col-md-2 control-label col-md-offset-1">Role</label>
   	 		<div class="col-md-6">
		    	<select name="role" class="form-control">
					<option value="<%=User.ROLE_MANAGER %>" <c:if test="${user.role==0}">selected="selected"</c:if> >Manager</option>
					<option value="<%=User.ROLE_USER %>" <c:if test="${user.role==1}">selected="selected"</c:if> >User</option>
					<option value="<%=User.ROLE_CHECKER %>" <c:if test="${user.role==2}">selected="selected"</c:if> >Checker</option>
					<option value="<%=User.ROLE_DIRECTOR %>" <c:if test="${user.role==3}">selected="selected"</c:if> >Director</option>
				</select>
			</div>
    	</div>
		<input type="hidden" name="id" value="${user.uid }">
    	<div class="form-group">
    		<div class="col-md-offset-3">
				<input type="submit" value="submit to revise this user"  class="btn btn-default"/>
			</div>
		</div>
	</form>
    <div class="warning col-md-offset-3"><% String s=(String)request.getAttribute("message");if(s!=null)out.print(s); %></div>
  
    <%@ include file="../template/footer.jsp"%>
  </body>
</html>
