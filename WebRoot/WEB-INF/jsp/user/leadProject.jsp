<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>lead project</title>
    
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
			var sumamount=$("#sumamount").text();
			function calPrice(){
				var temp=sumamount;
				var inputs=$(".price");
				for(var i=0;i<inputs.length;i++){
					temp=temp-inputs[i].value;
				}
				if(temp<0){
					$("#leftamount").css("color","red");
				}else{
					$("#leftamount").css("color","black");
				}
				$("#leftamount").text(temp);
				$("input[name='left']").val(temp);
			}
			calPrice();
			$(".price").keyup(function(){
				calPrice();
			})
		})
	</script>
  </head>
  
  <body>
<%@ include file="../template/header.jsp"%>
  <form action="user/leadProject/submit">
  	<input type="hidden" name="id" value="${project.pid }">
  	<div class="row">
  		<h2 class="col-md-2">项目信息</h2>
  		<div class="col-md-3  col-sm-10">
  			<table class="table table-bordered">
	  		<tr>
	  			<th>项目名称</th><td>${project.description }</td>
	  		</tr>
	  		<tr>
	  			<th>开始时间</th><td><fmt:formatDate type="date" dateStyle="long" value="${project.beginDate }" /></td>
	  		</tr>
	  		<th>结束时间</th><td><fmt:formatDate type="date" dateStyle="long" value="${project.endDate }" /></td>
  			</table>
  		</div>
  	</div>
  	<hr />
  	<div class="row">
		<h2 class="col-md-2">选择成员</h2>
		<div class="col-md-8 col-sm-10">
			<c:forEach items="${userlist }" var="user" varStatus="userStatus">
				<c:set var="uid">${user.uid}</c:set>
				<input type="checkbox" name="members" value="${user.uid }" <c:if test="${fn:containsIgnoreCase(fn:join(members,'|'),uid) }">checked</c:if> >${user.uname }
			</c:forEach>
		</div>
	</div>
  	<hr />
	<div class="row">
		<h2 class="col-md-2">项目经费</h2>
		<div class="col-md-4  col-sm-10">
			总额：<span id="sumamount">${project.amount }</span><br />
			可支配：<span id="leftamount">${project.amount }</span><br />
			<input type="hidden" name="left" value="${project.amount }" />
			<table class="table table-hover">
				<thead>
					<tr>
						<td>报销项目名</td>
						<td>报销比例</td>
						<td>可报销额</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="ip" items="${iplist }">
						<tr>
							<td>${ip.id.item.name }</td>
							<td>${ip.rate }%</td>
							<td><input class="price" type="number" name="${ip.id.item.iid }" value="${ip.amount }"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<input type="submit" value="submit"  class="btn btn-default"/>
	<div class="warning"><% String s=(String)request.getAttribute("message");if(s!=null)out.print(s); %></div>
    
   </form>
<%@ include file="../template/footer.jsp"%>
  </body>
</html>
