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
	<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
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
  <form action="user/leadProject/submit">
  	<input type="hidden" name="id" value="${project.pid }">
  	<div>
  		项目信息：<br />
  		${project.description }<br />
  		开始时间：<fmt:formatDate type="date" dateStyle="long" value="${project.beginDate }" /><br />
  		结束时间：<fmt:formatDate type="date" dateStyle="long" value="${project.endDate }" /><br />
  	</div>
  	<hr />
  	<div>
	选择参与成员<br />
	<c:forEach items="${userlist }" var="user" varStatus="userStatus">
		<c:set var="uid">${user.uid}</c:set>
		<input type="checkbox" name="members" value="${user.uid }" <c:if test="${fn:containsIgnoreCase(fn:join(members,'|'),uid) }">checked</c:if> >${user.uname }
	</c:forEach>
	</div>
  	<hr />
	<div>
	支配项目经费<br />
	总额：<span id="sumamount">${project.amount }</span><br />
	可支配：<span id="leftamount">${project.amount }</span><br />
	<input type="hidden" name="left" value="${project.amount }" />
	<table>
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
	<input type="submit" value="submit" />
	<div class="warning"><% String s=(String)request.getAttribute("message");if(s!=null)out.print(s); %></div>
    
   </form>
  </body>
</html>
