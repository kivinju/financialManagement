<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

id:<%=session.getAttribute("userid") %>
<br />name:<%=session.getAttribute("username") %>
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
<br />role:<%=role %>
<br /><a href="logout">logout</a>
<br /><a href="user">home</a>
<hr />