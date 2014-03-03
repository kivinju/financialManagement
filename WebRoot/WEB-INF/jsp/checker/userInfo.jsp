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

  </head>
  
  <body>
<%@ include file="../template/header.jsp"%>
		uid:${user.uid }<br />
		cardnum:${user.cardnum }<br />
		banknum:${user.banknum }<br />
		userrole:${userrole }<br />
		name:${user.uname }<br />
		<c:if test="${!empty user.upmappings}">
			<p>参与项目：</p>
		<table>
		<thead>
			<tr>
				<td>project id</td>
				<td>amount</td>
				<td>begin date</td>
				<td>end date</td>
				<td>description</td>
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
			<p>申请：</p>
	<table>
		<thead>
			<tr>
				<td>项目id</td>
				<td>项目名称</td>
				<td>报销项目</td>
				<td>报销金额</td>
				<td>提交时间</td>
				<td>状态</td>
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
		<!-- 统计信息 -->
		<c:if test="${director }">
			<hr />
			报销统计：
			<table>
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
<%@ include file="../template/footer.jsp"%>
  </body>
</html>
