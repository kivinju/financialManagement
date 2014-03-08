<%@ page language="java" import="java.util.*,cn.edu.nju.entity.*,java.text.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Checkor's Homepage</title>
    
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
			$(".ver_confirm").click(function(){
				var projectid=$(this).parent().parent().find(".ver_project").text();
				var userid=$(this).parent().parent().find(".ver_user").text();
				var itemid=$(this).parent().parent().find(".ver_item").attr("id");
				var sure=confirm("Are you sure to confirm this application?");
			if(sure){
				$(this).parent().parent().addClass("choose");
				$.ajax({  
                   type: "GET",  
                   url: "checker/confirmApplication",  
                   data: {projectId:projectid,userId:userid,itemId:itemid},  
                    contentType: 'application/json',
                   success:function(data){
                       alert("You have successfully confirm this application!");
                   	   $(".choose").fadeOut("slow");  
                   }  
            	})
			}
			})
			$(".ver_reject").click(function(){
				var projectid=$(this).parent().parent().find(".ver_project").text();
				var userid=$(this).parent().parent().find(".ver_user").text();
				var itemid=$(this).parent().parent().find(".ver_item").attr("id");
				var sure=confirm("Are you sure to reject this application?");
			if(sure){
				$(this).parent().parent().addClass("choose");
				$.ajax({  
                   type: "GET",  
                   url: "checker/rejectApplication",  
                   data: {projectId:projectid,userId:userid,itemId:itemid},  
                    contentType: 'application/json',
                   success:function(data){
                       alert("You have successfully reject this application!");
                   	   $(".choose").fadeOut("slow");  
                   }  
            	})
			}
			})
		})
	</script>
  </head>
  
  <body>
  <%@ include file="../template/header.jsp"%>
<div>
	<h2>审核：</h2>
	<table class="table table-hover table-striped">
		<thead>
			<tr>
				<th>项目id</th>
				<th>项目名称</th>
				<th>提交成员id</th>
				<th>成员名称</th>
				<th>报销项目</th>
				<th>报销金额</th>
				<th>可报销比例</th>
				<th>到手金额</th>
				<th>提交时间</th>
				<th>是否批准</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="map2" items="${verify }">
				<c:set var="ver" value="${map2.key }" ></c:set>
				<tr>
					<td class="ver_project" >${ver.id.project.pid }</td>
					<td>${ver.id.project.description }</td>
					<td class="ver_user" >${ver.id.user.uid }</td>
					<td>${ver.id.user.uname }</td>
					<td id="${ver.id.item.iid }" class="ver_item" >${ver.id.item.name }</td>
					<td>${ver.amount }</td>
					<td>${map2.value }%</td>
					<td><fmt:parseNumber value="${(ver.amount*map2.value/100)}" integerOnly="true"/></td>
					<td><fmt:formatDate type="date" dateStyle="long" value="${ver.time }" /></td>
					<td><button class="ver_confirm">批准</button></td>
					<td><button class="ver_reject">不批准</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<hr />
  <%@ include file="../template/finfo.jsp"%>
  <%@ include file="../template/footer.jsp"%>
  </body>
</html>
