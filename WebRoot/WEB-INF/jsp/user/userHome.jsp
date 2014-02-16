<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    
    <title><%=request.getAttribute("title") %></title>
    
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

<div>
我的项目：<br />
	主持:<br />
	<table>
		<thead>
			<tr>
				<td>项目编号</td>
				<td>项目描述</td>
				<td>开始日期</td>
				<td>结束日期</td>
				<td>项目金额</td>
				<td>已使用</td>
				<td>调配人员与金额</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${leadProject }" var="map">
				<c:set var="project" value="${map.key }" ></c:set>
				<tr>
					<td>${project.pid }</td>
					<td>${project.description }</td>
					<td><fmt:formatDate type="date" dateStyle="long" value="${project.beginDate }" /></td>
					<td><fmt:formatDate type="date" dateStyle="long" value="${project.endDate }" /></td>
					<td>${project.amount }</td>
					<td>${map.value }</td>
					<td><a href='<c:url value="" />'>编辑</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	参与：<br />
	<table>
		<thead>
			<tr>
				<td>项目编号</td>
				<td>项目描述</td>
				<td>开始日期</td>
				<td>结束日期</td>
				<td>项目金额</td>
				<td>已使用</td>
				<td>报销</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${partProject }" var="map">
				<c:set var="project" value="${map.key }" ></c:set>
				<tr>
					<td>${project.pid }</td>
					<td>${project.description }</td>
					<td><fmt:formatDate type="date" dateStyle="long" value="${project.beginDate }" /></td>
					<td><fmt:formatDate type="date" dateStyle="long" value="${project.endDate }" /></td>
					<td>${project.amount }</td>
					<td>${map.value }</td>
					<td><a href='<c:url value="" />'>报销</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>	
</div>
<div>
	审核：
	<table>
		<thead>
			<tr>
				<td>项目id</td>
				<td>项目名称</td>
				<td>提交成员id</td>
				<td>成员名称</td>
				<td>报销项目</td>
				<td>报销金额</td>
				<td>可报销比例</td>
				<td>到手金额</td>
				<td>提交时间</td>
				<td>是否批准</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="map2" items="${verify }">
				<c:set var="ver" value="${map2.key }" ></c:set>
				<tr>
					<td>${ver.id.project.pid }</td>
					<td>${ver.id.project.description }</td>
					<td>${ver.id.user.uid }</td>
					<td>${ver.id.user.uname }</td>
					<td>${ver.id.item.name }</td>
					<td>${ver.amount }</td>
					<td>${map2.value }%</td>
					<td><fmt:parseNumber value="${(ver.amount*map2.value/100)}" integerOnly="true"/></td>
					<td><fmt:formatDate type="date" dateStyle="long" value="${ver.time }" /></td>
					<td><a href='<c:url value="" />'>批准</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div>
	报销项目：
	<table>
		<thead>
			<tr>
				<td>项目id</td>
				<td>项目名称</td>
				<td>报销项目</td>
				<td>报销金额</td>
				<td>可报销比例</td>
				<td>到手金额</td>
				<td>提交时间</td>
				<td>状态</td>
			</tr>
		</thead>
		<tbody>
			${applications }
			<c:forEach var="map3" items="${applications }">
				<c:set var="ver" value="${map3.key }" ></c:set>
				<tr>
					<td>${ver.id.project.pid }</td>
					<td>${ver.id.project.description }</td>
					<td>${ver.id.item.name }</td>
					<td>${ver.amount }</td>
					<td>${map3.value }%</td>
					<td><fmt:parseNumber value="${(ver.amount*map3.value/100)}" integerOnly="true"/></td>
					<td><fmt:formatDate type="date" dateStyle="long" value="${ver.time }" /></td>
					<td>
					<c:choose>
						<c:when test="${ver.state==0 }">主持人审核中</c:when>
						<c:when test="${ver.state==1 }">财务审核中</c:when>
						<c:when test="${ver.state==2 }">通过</c:when>
						<c:when test="${ver.state==3 }">重写</c:when>
					</c:choose>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<%@ include file="../template/footer.jsp"%>
  </body>
</html>
