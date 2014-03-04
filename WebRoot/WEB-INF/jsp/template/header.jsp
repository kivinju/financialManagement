<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>


<% 		String role="未知";
		Short userrole=(Short) session.getAttribute("userrole");
		if(userrole==1){//User.ROLE_USER){
			role="用户";
		}else if(userrole==0){//User.ROLE_MANAGER){
			role="管理员";
		}else if(userrole==2){//User.ROLE_CHECKER){
			role="审核员";
		}else if(userrole==3){//User.ROLE_DIRECTOR){
			role="财务主管";
		}
 %>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
<ul id="nav_head">
<li><a href="user">Home</a></li>
<li><a href="logout">Logout</a></li>
<li id="nav_title">Online Reimbursement System</li>
<div id="nav_right">
<li>id:<%=session.getAttribute("userid") %></li>
<li>name:<%=session.getAttribute("username") %></li>
<li>role:<%=role %></li>
</div>
</ul>
</nav>
<div class="container">