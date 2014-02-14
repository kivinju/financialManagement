<%@ page language="java" import="java.util.*,cn.edu.nju.dao.User" pageEncoding="UTF-8"%>
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
	<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".delete").click(function(){
				var outer=$(this).parent().parent().attr("id");
				var id=parseInt(outer);
			var sure=confirm("Are you sure to delete id="+id+" user?");
			if(sure){
				$.ajax({  
                   type: "GET",  
                   url: "manager/delete",  
                   data: {id:id},  
                   success:function(data){  
                   	   $("#"+id).fadeOut("slow");
                       alert("You have successfully delete id="+id+" user");  
                   }  
            	})
			}
			})
		})
	</script>

  </head>
  
  <body>
    <%@ include file="../template/header.jsp"%>
    <a href="manager/add">add user</a>
	<table>
		<thead>
			<tr>
				<td>user id</td>
				<td>user name</td>
				<td>password</td>
				<td>card number</td>
				<td>bank number</td>
				<td>role</td>
				<td>revise</td>
			</tr>
		</thead>
		<tbody>
	<% List<User> list=(List)request.getAttribute("users");for(User user:list){ %>
		<tr id="<%=user.getUid() %>">
			<td><%=user.getUid() %></td>
			<td><%=user.getUname() %></td>
			<td><%=user.getPassword() %></td>
			<td><%=user.getCardnum() %></td>
			<td><%=user.getBanknum() %></td>
	<% String userrole="";
	switch (user.getRole()){
		case User.ROLE_MANAGER:
			userrole="manager";
			break;
		case User.ROLE_USER:
			userrole="user";
			break;
		case User.ROLE_DIRECTOR:
			userrole="director";
			break;
		case User.ROLE_CHECKER:
			userrole="checkor";
			break;
	}
 %>
			<td><%=userrole %></td>
			<td><a href="manager/revise?id=<%=user.getUid() %>">revise</a></td>
			<td><button class="delete">delete</button></td>
		</tr>
	<% } %>
		</tbody>
	</table>
	
	<%@ include file="../template/footer.jsp"%>
  </body>
</html>
