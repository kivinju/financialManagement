<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 除了提交报销申请只用，还用作修改申请和退回重写 -->
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Submit Application</title>
    
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
			$("input[name='item']").click(function(){
				 var itemid=$(this).val();
				 var max=$("#"+itemid).text();
				 $("#amount").attr("max",max);
			})
		})
	</script>
  </head>
  
  <body>
  	<%@ include file="../template/header.jsp"%>
  	<div class="row">
  		<h2 class="col-md-2">项目信息</h2>
  		<div class="col-md-5">
		  	<table class="table table-bordered">
			  	<tr>
			  		<th>项目</th><td>${project.description }</td>
			  	</tr>
			  	<tr>	  	
			  		<th>项目总金额</th><td>${project.amount }</td>
			  	</tr>
			  	<tr>
			  		<th>开始时间</th><td><fmt:formatDate type="date" dateStyle="long" value="${project.beginDate }" /></td>
			  	</tr>
			  	<tr>
			  		<th>结束时间</th><td><fmt:formatDate type="date" dateStyle="long" value="${project.endDate }" /></td>
			  	</tr>
		  	</table>
	  	</div>
  	</div>
  	<hr />
	<form action="user/writeApplication/submit">
	<h2>报销项目：</h2>
	<c:if test="${empty iplist}" >对不起！没有可报销项！</c:if>
	<c:if test="${!empty iplist}" >
	<div class="row">
		<div class="col-md-7">
			<table class="table table-hover">
				<tr>
					<th>报销项</th>
					<th>可报销比例</th>
					<th>分配金额</th>
					<th>可报销最高额</th>
				</tr>
				<c:forEach items="${iplist }" var="ip">
					<tr>
						<c:set var="itemId" value="${ip.id.item.iid }"></c:set>
						<td>
						<input type="radio" name="item" value="${itemId }" <c:if test="${itemId==iid }">checked="checked"</c:if> />${ip.id.item.name }
						</td>
						<td>${ip.rate }%</td>
						<td>${ip.amount }</td>
						<td id="${itemId }"><fmt:parseNumber value="${(ip.amount*100/ip.rate)}" integerOnly="true"/></td>
					</tr>
				</c:forEach>
				<tr>
					<th>报销金额</th>
					<td colspan="3"><input id="amount" type="number" name="amount" min="1" max="${max }" value="${amount }"/></td>
				</tr>
			</table>
		</div>
	</div>
	<input type="hidden" name="pid" value="${project.pid }" />
    <input type="submit" value="Submit"   class="btn btn-default"/>
    </c:if>
    </form>
  </body>
</html>
