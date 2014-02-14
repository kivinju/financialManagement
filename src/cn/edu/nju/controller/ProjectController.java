package cn.edu.nju.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.nju.dao.Item;
import cn.edu.nju.dao.Project;
import cn.edu.nju.dao.User;
import cn.edu.nju.service.ItemService;
import cn.edu.nju.service.ProjectService;
import cn.edu.nju.service.UserService;

@Controller
public class ProjectController {
	@Resource
	ProjectService projectService;
	@Resource
	UserService userService;
	@Resource
	ItemService itemService;
	
	
	@RequestMapping("/projectmanage")
	public String projectManage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		HttpSession session=request.getSession(false);
		if (session==null||session.getAttribute("userid")==null) {
			response.sendRedirect(basePath);
			return null;
		}
		int sessionId=(Integer)session.getAttribute("userid");
		//���ȸ���session��֤��½���û�Ϊ�����ߣ�������ǹ���Ա��ʾ������Ϣ������ת���û�����ҳ
		if (userService.getUserRole(sessionId)!=User.ROLE_MANAGER) {
			request.setAttribute("title","403 Forbidden!");
			request.setAttribute("message", "You don't have the permit to manage projects");
			request.setAttribute("redirect", basePath+"user");
			return "template/message";
		}
		//�Ӻ�̨��ȡ�����û�����Ϣ
		List<Project> list=projectService.getAllProjects();
		request.setAttribute("projects", list);
		return "manager/manageProject";
	}
	@RequestMapping("/manager/project/add")
	public String addProject(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		HttpSession session=request.getSession(false);
		if (session==null||session.getAttribute("userid")==null) {
			response.sendRedirect(basePath);
			return null;
		}
		int sessionId=(Integer)session.getAttribute("userid");
		//���ȸ���session��֤��½���û�Ϊ�����ߣ�������ǹ���Ա��ʾ������Ϣ������ת���û�����ҳ
		if (userService.getUserRole(sessionId)!=User.ROLE_MANAGER) {
			request.setAttribute("title","403 Forbidden!");
			request.setAttribute("message", "You don't have the permit to manage projects");
			request.setAttribute("redirect", basePath+"user");
			return "template/message";
		}
		//���ع�����ӽ���
		List<User> userList=userService.getAllUserByRole(User.ROLE_USER);
		model.addAttribute("userlist", userList);
		List<Item> itemList=itemService.getAllItems();
		model.addAttribute("itemlist", itemList);
		return "manager/addProject";
	}
	@RequestMapping("/manager/project/addProject")
	public String addProject2(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException, ParseException{
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		HttpSession session=request.getSession(false);
		if (session==null||session.getAttribute("userid")==null) {
			response.sendRedirect(basePath);
			return null;
		}
		int sessionId=(Integer)session.getAttribute("userid");
		//���ȸ���session��֤��½���û�Ϊ�����ߣ�������ǹ���Ա��ʾ������Ϣ������ת���û�����ҳ
		if (userService.getUserRole(sessionId)!=User.ROLE_MANAGER) {
			request.setAttribute("title","403 Forbidden!");
			request.setAttribute("message", "You don't have the permit to add project");
			request.setAttribute("redirect", basePath+"user");
			return "template/message";
		}
//		System.out.println("leader:"+request.getParameter("leader"));
//		String[] usersString=request.getParameterValues("members");
//		System.out.println("members:");
//		for (String string : usersString) {
//			System.out.println(string);
//		}
		String leader=request.getParameter("leader");
		String[] members=request.getParameterValues("members");
		String description=request.getParameter("description");
		String amount=request.getParameter("amount");
		String begindate=request.getParameter("beginDate");
		String enddate=request.getParameter("endDate");
		model.addAttribute("description", description);
		model.addAttribute("amount", amount);
		model.addAttribute("begin", begindate);
		model.addAttribute("end", enddate);
		model.addAttribute("leader",leader);
		model.addAttribute("members", members);
		List<User> userList=userService.getAllUserByRole(User.ROLE_USER);
		model.addAttribute("userlist", userList);
		List<Item> itemList=itemService.getAllItems();
		model.addAttribute("itemlist", itemList);
		if (amount==null||amount.equals("")||begindate==null||enddate==null||begindate.equals("")||enddate.equals("")||description==null||description.equals("")||leader==null||leader.equals("")||members==null||members.length==0) {
			request.setAttribute("message", "please complete this page");
			return "manager/addProject";
		}
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		int amountint=Integer.parseInt(amount);
		if (amountint<0) {
			request.setAttribute("message", "please set amount above zero");
			return "manager/addProject";
		}
		Date begin=df.parse(begindate);
		Date end=df.parse(enddate);	
		if (begin.after(end)) {
			request.setAttribute("message", "begin date is after end date");
			return "manager/addProject";
		}
		int leaderID=Integer.parseInt(leader);
		ArrayList<Integer> membersID=new ArrayList<Integer>();
		for (String s : members) {
			int temp=Integer.parseInt(s);
			if (temp==leaderID) {
				request.setAttribute("message", "leader and member should not be the same one");
				return "manager/addProject";
			}
			membersID.add(temp);
		}
		Project project = new Project(amountint,begin,end);
		project.setDescription(description);
		projectService.addProject(project,leaderID,membersID);

		//����û��ɹ���������ת
		request.setAttribute("title","Add project successfully");
		request.setAttribute("message", "Add project successfully");
		request.setAttribute("redirect", "projectmanage");
		return "template/message";
	}
	@RequestMapping("/manager/project/delete")
	public void deleteProject(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		HttpSession session=request.getSession(false);
		int sessionId=(Integer)session.getAttribute("userid");
		if (session==null||session.getAttribute("userid")==null||userService.getUserRole(sessionId)!=User.ROLE_MANAGER) {
			response.sendRedirect(basePath);
			return;
		}
		int userID=Integer.parseInt(request.getParameter("id"));
		projectService.deleteProject(userID);
	}
	@RequestMapping("/manager/project/revise")
	public String reviseProject(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		HttpSession session=request.getSession(false);
		if (session==null||session.getAttribute("userid")==null) {
			response.sendRedirect(basePath);
			return null;
		}
		int sessionId=(Integer)session.getAttribute("userid");
		//���ȸ���session��֤��½���û�Ϊ�����ߣ�������ǹ���Ա��ʾ������Ϣ������ת���û�����ҳ
		if (userService.getUserRole(sessionId)!=User.ROLE_MANAGER) {
			request.setAttribute("title","403 Forbidden!");
			request.setAttribute("message", "You don't have the permit to revise project");
			request.setAttribute("redirect", basePath+"user");
			return "template/message";
		}
		int projectID=Integer.parseInt(request.getParameter("id"));
		request.setAttribute("revise", true);
		Project project=projectService.getProject(projectID);
		model.addAttribute("projectID", project.getPid());
		model.addAttribute("description", project.getDescription());
		model.addAttribute("amount", project.getAmount());
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("begin", df.format(project.getBeginDate()));
		model.addAttribute("end", df.format(project.getEndDate()));
		List<User> userList=userService.getAllUserByRole(User.ROLE_USER);
		model.addAttribute("userlist", userList);
		return "manager/addProject";
	}
	@RequestMapping("/manager/project/reviseProject")
	public String reviseProject2(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException, ParseException{
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		HttpSession session=request.getSession(false);
		if (session==null||session.getAttribute("userid")==null) {
			response.sendRedirect(basePath);
			return null;
		}
		int sessionId=(Integer)session.getAttribute("userid");
		//���ȸ���session��֤��½���û�Ϊ�����ߣ�������ǹ���Ա��ʾ������Ϣ������ת���û�����ҳ
		if (userService.getUserRole(sessionId)!=User.ROLE_MANAGER) {
			request.setAttribute("title","403 Forbidden!");
			request.setAttribute("message", "You don't have the permit to revise project");
			request.setAttribute("redirect", basePath+"user");
			return "template/message";
		}
		int id=Integer.parseInt(request.getParameter("projectID"));
		String description=request.getParameter("description");
		String amount=request.getParameter("amount");
		String begindate=request.getParameter("beginDate");
		String enddate=request.getParameter("endDate");
		model.addAttribute("projectID",id);
		model.addAttribute("description", description);
		model.addAttribute("amount", amount);
		model.addAttribute("begin", begindate);
		model.addAttribute("end", enddate);
		if (amount==null||amount.equals("")||begindate==null||enddate==null||begindate.equals("")||enddate.equals("")||description==null||description.equals("")) {
			request.setAttribute("message", "please complete this page");
			return "manager/addProject";
		}
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		int amountint=Integer.parseInt(amount);
		if (amountint<0) {
			request.setAttribute("message", "please set amount above zero");
			return "manager/addProject";
		}
		Date begin=df.parse(begindate);
		Date end=df.parse(enddate);	
		if (begin.after(end)) {
			request.setAttribute("message", "begin date is after end date");
			return "manager/addProject";
		}
		Project project = new Project(amountint,begin,end);
		project.setDescription(description);
		project.setPid(id);
		projectService.reviseProject(project);
		request.setAttribute("title","revise project successfully");
		request.setAttribute("message", "Revise project successfully");
		request.setAttribute("redirect", "projectmanage");
		return "template/message";
	}
}
