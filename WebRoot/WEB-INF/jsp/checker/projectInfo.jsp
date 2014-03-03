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

  </head>
  
  <body>
<%@ include file="../template/header.jsp"%>
		pid:${project.pid }<br />
		可报销金额:${project.amount }<br />
		描述:${project.description }<br />
		开始时间:<fmt:formatDate type="date" dateStyle="long" value="${project.beginDate }" /><br />
		结束时间:<fmt:formatDate type="date" dateStyle="long" value="${project.endDate }" /><br />
		
		成员：
		<table>
		<thead>
			<tr>
				<td>用户ID</td>
				<td>用户姓名</td>
				<td>卡号</td>
				<td>银行卡号</td>
				<td>职务</td>
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
			报销项目：
			<table>
				<thead>
					<tr>
						<td>报销项目</td>
						<td>报销比例</td>
						<td>分配金额</td>
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
			<p>此项目报销申请：</p>
			<table>
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
