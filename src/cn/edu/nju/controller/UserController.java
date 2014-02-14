package cn.edu.nju.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.org.apache.regexp.internal.RE;

import cn.edu.nju.dao.User;
import cn.edu.nju.dao.UserDAO;
import cn.edu.nju.service.UserService;

@Controller
public class UserController {

	@Resource
	private UserService userService;
	
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String login(HttpServletRequest request,HttpServletResponse response){
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		User user=userService.login(request.getParameter("username"), request.getParameter("password"));
		if (user==null) {
			//没有找到这个用户
			request.setAttribute("message", "Sorry, please check your name or password");
			request.setAttribute("username", request.getParameter("username"));
			return "index";
		}else {
			//创建session
			HttpSession session=request.getSession(true);
			session.setAttribute("username", user.getUname());
			session.setAttribute("userid", user.getUid());
			session.setAttribute("userrole", user.getRole());
			try {
				response.sendRedirect(basePath+"user/");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}
	
	@RequestMapping("/user")
	public String userHomePage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		HttpSession session=request.getSession(false);
		if (session==null||session.getAttribute("userid")==null) {
			response.sendRedirect(basePath);
			return null;
		}
		int sessionId=(Integer)session.getAttribute("userid");
		//修改：决定访问自己主页不用id，直接根据session中的id进行访问
		//如果访问的不是自己的主页，要回到登陆页面。
//		if (sessionId!=Integer.parseInt(userid)) {
//			response.sendRedirect(basePath);
//			return null;
//		}
		request.setAttribute("title","Welcome!"+session.getAttribute("username"));
		//返回管理者界面，其他的用户类型返回相应的界面
		return "manager/managerHome";		
	}
	
	@RequestMapping("/usermanage")
	public String userManage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		HttpSession session=request.getSession(false);
		if (session==null||session.getAttribute("userid")==null) {
			response.sendRedirect(basePath);
			return null;
		}
		int sessionId=(Integer)session.getAttribute("userid");
		//首先根据session验证登陆的用户为管理者，如果不是管理员显示错误信息，并跳转至用户的主页
		if (userService.getUserRole(sessionId)!=User.ROLE_MANAGER) {
			request.setAttribute("title","403 Forbidden!");
			request.setAttribute("message", "You don't have the permit to manage users");
			request.setAttribute("redirect", basePath+"user");
			return "template/message";
		}
		//从后台获取所有用户的信息
		ArrayList<User> list=userService.getAllUsers();
//		for (User user : list) {
//			System.out.println(user.getUpmappings());
//		}
		request.setAttribute("users", list);
		request.setAttribute("title","UserManage");
		return "manager/manageUser";
	}
	
	@RequestMapping("/manager/delete")
	public void deleteUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		HttpSession session=request.getSession(false);
		int sessionId=(Integer)session.getAttribute("userid");
		if (session==null||session.getAttribute("userid")==null||userService.getUserRole(sessionId)!=User.ROLE_MANAGER) {
			response.sendRedirect(basePath);
			return;
		}
		int userID=Integer.parseInt(request.getParameter("id"));
		userService.deleteUser(userID);
	}
	
	@RequestMapping("/manager/add")
	public String addUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		HttpSession session=request.getSession(false);
		if (session==null||session.getAttribute("userid")==null) {
			response.sendRedirect(basePath);
			return null;
		}
		int sessionId=(Integer)session.getAttribute("userid");
		//首先根据session验证登陆的用户为管理者，如果不是管理员显示错误信息，并跳转至用户的主页
		if (userService.getUserRole(sessionId)!=User.ROLE_MANAGER) {
			request.setAttribute("title","403 Forbidden!");
			request.setAttribute("message", "You don't have the permit to add user");
			request.setAttribute("redirect", basePath+"user");
			return "template/message";
		}
		return "manager/addUser";
	}
	
	@RequestMapping("/manager/addUser")
	public String addUser2(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		HttpSession session=request.getSession(false);
		if (session==null||session.getAttribute("userid")==null) {
			response.sendRedirect(basePath);
			return null;
		}
		int sessionId=(Integer)session.getAttribute("userid");
		//首先根据session验证登陆的用户为管理者，如果不是管理员显示错误信息，并跳转至用户的主页
		if (userService.getUserRole(sessionId)!=User.ROLE_MANAGER) {
			request.setAttribute("title","403 Forbidden!");
			request.setAttribute("message", "You don't have the permit to add user");
			request.setAttribute("redirect", basePath+"user");
			return "template/message";
		}
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String confirm=request.getParameter("confirm");
		String cardnum=request.getParameter("cardnum");
		String banknum=request.getParameter("banknum");
		Short role=Short.parseShort(request.getParameter("role"));
		if (name==null||name.equals("")||password==null||password.equals("")||confirm==null||confirm.equals("")) {
			request.setAttribute("message", "please complete this page");
			return "manager/addUser";
		}
		if (!password.equals(confirm)){
			request.setAttribute("message","your password and confirm password are different!");
			return "manager/addUser";
		}
		if (cardnum.length()!=7){
			request.setAttribute("message","Card number must has seven characters");
			return "manager/addUser";
		}
		if (banknum.length()>19){
			request.setAttribute("message","Bank number must has less than 20 characters");
			return "manager/addUser";
		}
		User user=new User(cardnum, banknum, role, name, password);
		userService.addUser(user);
		//添加用户成功，进行跳转
		request.setAttribute("title","Add user successfully");
		request.setAttribute("message", "Add user successfully");
		request.setAttribute("redirect", "usermanage");
		return "template/message";
	}
	@RequestMapping("/manager/revise")
	public String reviseUser(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		HttpSession session=request.getSession(false);
		if (session==null||session.getAttribute("userid")==null) {
			response.sendRedirect(basePath);
			return null;
		}
		int sessionId=(Integer)session.getAttribute("userid");
		//首先根据session验证登陆的用户为管理者，如果不是管理员显示错误信息，并跳转至用户的主页
		if (userService.getUserRole(sessionId)!=User.ROLE_MANAGER) {
			request.setAttribute("title","403 Forbidden!");
			request.setAttribute("message", "You don't have the permit to revise user");
			request.setAttribute("redirect", basePath+"user");
			return "template/message";
		}
		int userID=Integer.parseInt(request.getParameter("id"));
		User user=userService.getUser(userID);
		model.addAttribute("user", user);
		return "manager/reviseUser";
	}
	
	@RequestMapping("/manager/reviseUser")
	public String reviseUser2(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		HttpSession session=request.getSession(false);
		if (session==null||session.getAttribute("userid")==null) {
			response.sendRedirect(basePath);
			return null;
		}
		int sessionId=(Integer)session.getAttribute("userid");
		//首先根据session验证登陆的用户为管理者，如果不是管理员显示错误信息，并跳转至用户的主页
		if (userService.getUserRole(sessionId)!=User.ROLE_MANAGER) {
			request.setAttribute("title","403 Forbidden!");
			request.setAttribute("message", "You don't have the permit to revise user");
			request.setAttribute("redirect", basePath+"user");
			return "template/message";
		}
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String confirm=request.getParameter("confirm");
		String cardnum=request.getParameter("cardnum");
		String banknum=request.getParameter("banknum");
		Short role=Short.parseShort(request.getParameter("role"));
		int uid=Integer.parseInt(request.getParameter("id"));
		User user=userService.getUser(uid);
		model.addAttribute("user", user);
		if (name==null||name.equals("")||password==null||password.equals("")||confirm==null||confirm.equals("")) {
			request.setAttribute("message", "please complete this page");
			return "manager/reviseUser";
		}
		if (!password.equals(confirm)){
			request.setAttribute("message","your password and confirm password are different!");
			return "manager/reviseUser";
		}
		if (cardnum.length()!=7){
			request.setAttribute("message","Card number must has seven characters");
			return "manager/reviseUser";
		}
		if (banknum.length()>19){
			request.setAttribute("message","Bank number must has less than 20 characters");
			return "manager/reviseUser";
		}
		user=new User(cardnum, banknum, role, name, password);
		user.setUid(uid);
		userService.reviseUser(user);
		//添加用户成功，进行跳转
		request.setAttribute("title","revise user successfully");
		request.setAttribute("message", "Revise user successfully");
		request.setAttribute("redirect", "usermanage");
		return "template/message";
	}
}
