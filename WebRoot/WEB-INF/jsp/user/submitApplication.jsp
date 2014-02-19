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
	<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
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
  
  	项目：${project.description }<br />
  	项目总金额：${project.amount }<br />
  	开始时间：<fmt:formatDate type="date" dateStyle="long" value="${project.beginDate }" /><br />
  	结束时间：<fmt:formatDate type="date" dateStyle="long" value="${project.endDate }" /><br />
  	<hr />
	<form action="user/writeApplication/submit">
	报销项目：
	<c:if test="${empty iplist}" >对不起！没有可报销项！</c:if>
	<c:if test="${!empty iplist}" >
	<table>
	<tr>
		<td>报销项</td>
		<td>可报销比例</td>
		<td>分配金额</td>
		<td>可报销最高额</td>
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
	</table>
	<input type="hidden" name="pid" value="${project.pid }" />
	报销金额：<input id="amount" type="number" name="amount" min="1" max="${max }" value="${amount }"/><br />
    <input type="submit" value="Submit" />
    </c:if>
    </form>
  </body>
</html>
