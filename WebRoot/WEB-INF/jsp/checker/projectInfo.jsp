<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    
    <title>${project.description }</title>
    
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
					<caption><h2>项目信息</h2></caption>
					<tr><th>pid:</th><td>${project.pid }</td></tr>
					<tr><th>可报销金额:</th><td>${project.amount }</td></tr>
					<tr><th>描述:</th><td>${project.description }</td></tr>
					<tr><th>开始时间:</th><td><fmt:formatDate type="date" dateStyle="long" value="${project.beginDate }" /></td></tr>
					<tr><th>结束时间:</th><td><fmt:formatDate type="date" dateStyle="long" value="${project.endDate }" /></td></tr>
				</table>
			</div>
			<div class="col-md-5 col-md-offset-1">
				<!-- 统计信息 -->
				<c:if test="${director }">
					<table class="table table-hover table-striped">
					<caption><h2>报销统计</h2></caption>
					<tr>
						<td>月份</td>
						<td>报销金额</td>
					</tr>
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
		<table class="table table-hover table-striped">
		<caption><h2>成员</h2></caption>
		<thead>
			<tr>
				<th>用户ID</th>
				<th>用户姓名</th>
				<th>卡号</th>
				<th>银行卡号</th>
				<th>职务</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="upmapping" items="${project.upmappings }" >
		<tr>
			<c:set var="user" value="${upmapping.id.user }"></c:set>
			<td>${user.uid }</td>
			<td>${user.uname }</td>
			<td>${user.cardnum }</td>
			<td>${user.banknum }</td>
			<td>
				<c:if test="${!upmapping.uprole }">成员</c:if>
				<c:if test="${upmapping.uprole }">主持人</c:if>
			</td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
		<c:if test="${!empty project.ipmappings }">
			<table class="table table-hover table-striped">
			<caption><h2>报销项目</h2></caption>
				<thead>
					<tr>
						<th>报销项目</th>
						<th>报销比例</th>
						<th>分配金额</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="ipmapping" items="${project.ipmappings }">
						<tr>
							<td>${ipmapping.id.item.name }</td>
							<td>${ipmapping.rate }%</td>
							<td>${ipmapping.amount }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		<c:if test="${!empty project.applications}">
		<table class="table table-hover table-striped">
			<caption><h2>此项目报销申请</h2></caption>
		<thead>
			<tr>
				<td>用户id</td>
				<td>用户姓名</td>
				<td>报销项目</td>
				<td>报销金额</td>
				<td>提交时间</td>
				<td>状态</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="ver" items="${project.applications }">
				<tr>
					<td class="ver_project" >${ver.id.user.uid }</td>
					<td>${ver.id.user.uname }</td>
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
