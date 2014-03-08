<%@ page language="java" import="java.util.*,java.text.*,cn.edu.nju.entity.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>${user.uname }</title>
    
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
	<div class="row">
		<div class="col-md-5">
		<table class="table table-hover table-striped">
		<caption><h2>用户信息</h2></caption>
			<tr><th>uid:</th><td>${user.uid }</td></tr>
			<tr><th>cardnum:</th><td>${user.cardnum }</td></tr>
			<tr><th>banknum:</th><td>${user.banknum }</td></tr>
			<tr><th>userrole:</th><td>${userrole }</td></tr>
			<tr><th>name:</th><td>${user.uname }</td></tr>
		</table>
		</div>
		<div class="col-md-5 col-md-offset-1">
			<!-- 统计信息 -->
			<c:if test="${director }">
				<table class="table table-hover table-striped">
				<caption><h2>报销统计</h2></caption>
				<thead>
				<tr>
					<th>月份</th>
					<th>报销金额</th>
				</tr>
				</thead>
				<c:forEach var="entry" items="${map }">
					<tr>
						<td>${entry.key }</td>
						<td>${entry.value }</td>
					</tr>
				</c:forEach>
				</table>
			</c:if>
		</div>
	</div>
		<c:if test="${!empty user.upmappings}">
		<table class="table table-hover table-striped">
		<caption><h2>参与项目</h2></caption>
		<thead>
			<tr>
				<th>project id</th>
				<th>amount</th>
				<th>begin date</th>
				<th>end date</th>
				<th>description</th>
				<th>role</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="upmapping" items="${user.upmappings }" >
		<tr>
			<c:set var="project" value="${upmapping.id.project }"></c:set>
			<td>${project.pid }</td>
			<td>${project.amount }</td>
			<td><fmt:formatDate type="date" dateStyle="long" value="${project.beginDate }" /></td>
			<td><fmt:formatDate type="date" dateStyle="long" value="${project.endDate }" /></td>
			<td>${project.description }</td>
			<td>
				<c:if test="${!upmapping.uprole }">成员</c:if>
				<c:if test="${upmapping.uprole }">主持人</c:if>
			</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
		</c:if>
		<c:if test="${!empty user.applications}">
			<p></p>
	<table class="table table-hover table-striped">
		<caption><h2>申请报销</h2></caption>
		<thead>
			<tr>
				<th>项目id</th>
				<th>项目名称</th>
				<th>报销项目</th>
				<th>报销金额</th>
				<th>提交时间</th>
				<th>状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="ver" items="${user.applications }">
				<tr>
					<td class="ver_project" >${ver.id.project.pid }</td>
					<td>${ver.id.project.description }</td>
					<td id="${ver.id.item.iid }" class="ver_item" >${ver.id.item.name }</td>
					<td>${ver.amount }</td>
					<td><fmt:formatDate type="date" dateStyle="long" value="${ver.time }" /></td>
					<td>
					<c:choose>
						<c:when test="${ver.state==0 }">主持人审核中</c:when>
						<c:when test="${ver.state==1 }">财务审核中</c:when>
						<c:when test="${ver.state==2 }">通过</c:when>
						<c:when test="${ver.state==3 }">重写</c:when>
						<c:otherwise>未知状态</c:otherwise>
					</c:choose>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
		</c:if>
<%@ include file="../template/footer.jsp"%>
  </body>
</html>
