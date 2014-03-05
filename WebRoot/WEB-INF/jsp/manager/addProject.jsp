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
	
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css">
	<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
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
    <h3 class="col-md-offset-3">Revise Project</h3>
    <form action="manager/project/reviseProject" method="POST"  class="form-horizontal" role="form">
    <div class="form-group">
   	 		<label class="col-md-2 control-label col-md-offset-1">ID</label>
   	 		<div class="col-md-6">
    			<p class="form-control-static">${projectID }</p>
			</div>
    	</div>
    <input type="hidden" name="projectID" value="${projectID }" />
    <% }else{ %>
    <h3 class="col-md-offset-3">Add Project</h3>
    <form action="manager/project/addProject" method="POST"  class="form-horizontal" role="form">
    <% } %>
    	<div class="form-group">
   	 		<label class="col-md-2 control-label col-md-offset-1">Description</label>
   	 		<div class="col-md-6">
    			<input type="text" name="description" value="${description }"   class="form-control"  placeholder="Enter your project description"/>
			</div>
    	</div>
    	<div class="form-group">
   	 		<label class="col-md-2 control-label col-md-offset-1">Amount</label>
   	 		<div class="col-md-6">
    			<input type="number" name="amount" value="${amount }"  class="form-control"  placeholder="Enter this project amount"/>
			</div>
    	</div>
    	<div class="form-group">
   	 		<label class="col-md-2 control-label col-md-offset-1">Begin Date</label>
   	 		<div class="col-md-6">
    			<input type="date" name="beginDate" value="${begin }" class="form-control" />
			</div>
    	</div>
    	<div class="form-group">
   	 		<label class="col-md-2 control-label col-md-offset-1">End Date</label>
   	 		<div class="col-md-6">
    			<input type="date" name="endDate" value="${end }" class="form-control" />
			</div>
    	</div>
		<hr />
    	<div class="form-group">
   	 		<label class="col-md-2 control-label col-md-offset-1">Leader</label>
   	 		<div class="col-md-6">
				<c:forEach items="${userlist }" var="user" varStatus="userStatus">
					<label class="radio-inline"><input type="radio" name="leader" value="${user.uid }" <c:if test="${user.uid==leader }">checked</c:if> >${user.uname }</label>	
				</c:forEach>
			</div>
    	</div>
		<hr />
<!-- 	Members:<c:forEach items="${userlist }" var="user" varStatus="userStatus">
		<c:set var="uid">${user.uid}</c:set>
		<input type="checkbox" name="members" value="${user.uid }" <c:if test="${fn:containsIgnoreCase(fn:join(members,'|'),uid) }">checked</c:if> >${user.uname }
		</c:forEach>
 -->
		
    	<div class="form-group">
   	 		<label class="col-md-2 control-label col-md-offset-1">报销</label>
   	 		<div class="col-md-6">	 	
				<c:forEach items="${itemlist }" var="item" varStatus="itemStatus">
					<c:set var="itemid">${item.iid}</c:set>
					<c:choose>
						<c:when test="${fn:containsIgnoreCase(fn:join(items,'|'),itemid) }">
							<input type="checkbox" name="item" value="${item.iid }" checked  />${item.name }
							<input type="number" name="${item.iid }" max="100" min="1" value="${map[item.iid] }" />
							<br />
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="item" value="${item.iid }"/>${item.name }
							<input type="number" name="${item.iid }" max="100" min="1" disabled="disabled" value="0" />
							<br />
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
    	</div>
    	<div class="form-group">
    		<div class="col-md-offset-3">
				<input type="submit" value="submit"  class="btn btn-default"/>
			</div>
		</div>
	</form>
    <div class="warning col-md-offset-3"><% String s=(String)request.getAttribute("message");if(s!=null)out.print(s); %></div>
    
    <%@ include file="../template/footer.jsp"%>
  </body>
</html>
