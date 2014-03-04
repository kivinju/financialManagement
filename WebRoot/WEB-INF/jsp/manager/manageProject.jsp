<%@page import="cn.edu.nju.entity.Project"%>
<%@ page language="java" import="java.util.*,java.text.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Manage projects</title>
    
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
	<script type="text/javascript">
		$(document).ready(function(){
			$(".delete").click(function(){
				var outer=$(this).parent().parent().attr("id");
				var id=parseInt(outer);
			var sure=confirm("Are you sure to delete id="+id+" project?");
			if(sure){
				$.ajax({  
                   type: "GET",  
                   url: "manager/project/delete",  
                   data: {id:id},  
                   success:function(data){  
                   	   $("#"+id).fadeOut("slow");
                       alert("You have successfully delete id="+id+" project");  
                   }  
            	})
			}
			})
		})
	</script>
	
  </head>
  
  <body>
    <%@ include file="../template/header.jsp"%>
    <a href="manager/project/add">add project</a>
	<table>
		<thead>
			<tr>
				<td>project id</td>
				<td>amount</td>
				<td>begin date</td>
				<td>end date</td>
				<td>description</td>
				<td>revise</td>
			</tr>
		</thead>
		<tbody>
		<% SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd"); %>
	<% List<Project> list=(List)request.getAttribute("projects");for(Project project:list){ %>
		<tr id="<%=project.getPid() %>">
			<td><%=project.getPid() %></td>
			<td><%=project.getAmount() %></td>
			<td><%=df.format(project.getBeginDate()) %></td>
			<td><%=df.format(project.getEndDate()) %></td>
			<td><%=project.getDescription() %></td>
			<td><a href="manager/project/revise?id=<%=project.getPid() %>">revise</a></td>
			<td><button class="delete">delete</button></td>
		</tr>
	<% } %>
		</tbody>
	</table>
	
	<%@ include file="../template/footer.jsp"%>
    
    
  </body>
</html>
