<!-- 此页面为每个页面的头 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,cn.edu.nju.dao.User"%>

id:<%=session.getAttribute("userid") %>
<br />name:<%=session.getAttribute("username") %>
<% String role="";
		if ((Short)session.getAttribute("userrole") == User.ROLE_MANAGER) {
			role="manager";
		} else if ((Short)session.getAttribute("userrole") == User.ROLE_USER) {
			role="user";
		} else if ((Short)session.getAttribute("userrole") == User.ROLE_DIRECTOR) {
			role="director";
		} else if ((Short)session.getAttribute("userrole") == User.ROLE_CHECKER) {
			role="checkor";
		}
 %>
<br />role:<%=role %>
<hr />