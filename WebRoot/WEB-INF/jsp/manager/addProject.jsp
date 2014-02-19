<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>add project</title>
    
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
				 if($(this).prop("checked")==true){
				 	$(this).next().removeAttr("disabled");
				 }else{
				 	$(this).next().attr("disabled","disabled");
				 }
			})
		})
	</script>
  </head>
  
  <body>
    <%@ include file="../template/header.jsp"%>
    
    <% if(request.getAttribute("revise")!=null){ %>
    <form action="manager/project/reviseProject" method="POST">
    ID:${projectID }
    <input type="hidden" name="projectID" value="${projectID }" /><br />
    <% }else{ %>
    <form action="manager/project/addProject" method="POST">
    <% } %>
		Description:<input type="text" name="description" value="${description }"/><br />
		Amount:<input type="number" name="amount" value="${amount }"/><br />
		Begin Date:<input type="date" name="beginDate" value="${begin }"><br />
		End Date:<input type="date" name="endDate" value="${end }"><br />
		<hr />
		Leader:<c:forEach items="${userlist }" var="user" varStatus="userStatus">
		<input type="radio" name="leader" value="${user.uid }" <c:if test="${user.uid==leader }">checked</c:if> >${user.uname }		
		</c:forEach>
		<hr />
<!-- 	Members:<c:forEach items="${userlist }" var="user" varStatus="userStatus">
		<c:set var="uid">${user.uid}</c:set>
		<input type="checkbox" name="members" value="${user.uid }" <c:if test="${fn:containsIgnoreCase(fn:join(members,'|'),uid) }">checked</c:if> >${user.uname }
		</c:forEach>
 -->
 		<hr />
		报销：(比例为1到100的整数)
		<c:forEach items="${itemlist }" var="item" varStatus="itemStatus">
			<c:set var="itemid">${item.iid}</c:set>
			<c:choose>
				<c:when test="${fn:containsIgnoreCase(fn:join(items,'|'),itemid) }">
					<br /><input type="checkbox" name="item" value="${item.iid }" checked  />${item.name }
					<input type="number" name="${item.iid }" max="100" min="1" value="${map[item.iid] }" />
				</c:when>
				<c:otherwise>
					<br /><input type="checkbox" name="item" value="${item.iid }"/>${item.name }
					<input type="number" name="${item.iid }" max="100" min="1" disabled="disabled" value="0" />
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<br /><input type="submit" value="submit" />
	</form>
    <div class="warning"><% String s=(String)request.getAttribute("message");if(s!=null)out.print(s); %></div>
    
    <%@ include file="../template/footer.jsp"%>
  </body>
</html>
