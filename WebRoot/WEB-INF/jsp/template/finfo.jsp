<%@ page language="java" import="java.util.*,cn.edu.nju.entity.*,java.text.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h2>用户信息：</h2>
	<table class="table table-hover table-striped">
		<thead>
			<tr>
				<th>user id</th>
				<th>user name</th>
				<th>password</th>
				<th>card number</th>
				<th>bank number</th>
				<th>role</th>
				<th>detailed info</th>
			</tr>
		</thead>
		<tbody>
	<% List<User> list=(List)request.getAttribute("users");for(User user:list){ %>
		<tr id="<%=user.getUid() %>">
			<td><%=user.getUid() %></td>
			<td><%=user.getUname() %></td>
			<td><%=user.getPassword() %></td>
			<td><%=user.getCardnum() %></td>
			<td><%=user.getBanknum() %></td>
	<% String roleh="未知";
		Short userroleh=user.getRole();
		//System.out.println(userroleh);
		if(userroleh==1){//User.ROLE_USER){
			roleh="用户";
		}else if(userroleh==0){//User.ROLE_MANAGER){
			roleh="管理员";
		}else if(userroleh==2){//User.ROLE_CHECKER){
			roleh="审核员";
		}else if(userroleh==3){//User.ROLE_DIRECTOR){
			roleh="财务主管";
		}
 %>
			<td><%=roleh %></td>
			<td><a href="userinfo?userid=<%=user.getUid() %>">info</a></td>
		</tr>
	<% } %>
		</tbody>
	</table>
<h2>项目信息:</h2>
<table class="table table-hover table-striped">
		<thead>
			<tr>
				<th>project id</th>
				<th>amount</th>
				<th>begin date</th>
				<th>end date</th>
				<th>description</th>
				<th>detailed info</th>
			</tr>
		</thead>
		<tbody>
		<% SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd"); %>
	<% List<Project> plist=(List)request.getAttribute("projects");for(Project project:plist){ %>
		<tr id="<%=project.getPid() %>">
			<td><%=project.getPid() %></td>
			<td><%=project.getAmount() %></td>
			<td><%=df.format(project.getBeginDate()) %></td>
			<td><%=df.format(project.getEndDate()) %></td>
			<td><%=project.getDescription() %></td>
			<td><a href="projectinfo?projectid=<%=project.getPid() %>">info</a></td>
		</tr>
	<% } %>
		</tbody>
	</table>
