package cn.edu.nju.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.nju.entity.Application;
import cn.edu.nju.entity.Ipmapping;
import cn.edu.nju.entity.Project;
import cn.edu.nju.entity.Upmapping;
import cn.edu.nju.entity.User;
import cn.edu.nju.service.ApplicationService;
import cn.edu.nju.service.ItemService;
import cn.edu.nju.service.ProjectService;
import cn.edu.nju.service.UserService;

@Controller
public class UserController {
	@Resource
	ProjectService projectService;
	@Resource
	ApplicationService applicationService;
	@Resource
	UserService userService;
	@Resource
	ItemService itemService;
	
	@RequestMapping(value="userhome")
	public String userHome(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model) throws IOException{
		if (session==null||session.getAttribute("userid")==null) {
			response.sendRedirect("");
			return null;
		}
		int sessionId=(Integer)session.getAttribute("userid");
		request.setAttribute("title","Welcome!"+session.getAttribute("username"));
		Map<Project,Boolean> projectList=projectService.getProjectsFromUserId(sessionId);
		//第二个Integer是为了计算这个project已使用多少金额。
		Map<Project,Integer> leadProjects=new HashMap<Project, Integer>();
		Map<Project,Integer> partProjects=new HashMap<Project, Integer>();
		Iterator<Entry<Project, Boolean>> iter=projectList.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<Project, Boolean> entry=iter.next();
			if (entry.getValue()==Upmapping.ROLE_LEADER) {
				leadProjects.put(entry.getKey(),projectService.hasUse(entry.getKey().getPid()));
			}else {
				partProjects.put(entry.getKey(),projectService.hasUse(entry.getKey().getPid()));
			}
			//再在model中加入这个project的经费使用情况，算每个项目使用多少比较麻烦，需要联合application，ipmapping，item,project
			
		}
		model.addAttribute("leadProject", leadProjects);
		model.addAttribute("partProject", partProjects);
		//获取需要他审核的报销项目，申请状态为0
		List<Application> applications=applicationService.getApplicationsFromLeader(sessionId);
		//第二个Integer为可报销的比例
		Map<Application, Short> map=new HashMap<Application, Short>();
		for (Application application : applications) {
			map.put(application, projectService.getRateByPidIid(application.getId().getProject(),application.getId().getItem()));
		}
		model.addAttribute("verify", map);
		//获取他的报销项目
		Map<Application, Short> map2=new HashMap<Application, Short>();
		List<Application> applications2=applicationService.getApplicationsFromMember(sessionId);
		for (Application application : applications2) {
			System.out.println("app"+applications2.size());
			map2.put(application, projectService.getRateByPidIid(application.getId().getProject(),application.getId().getItem()));
		}
		model.addAttribute("applications", map2);
		return "user/userHome";
	}
	
	@RequestMapping(value="user/confirmApplication")
	@ResponseBody
	public String confirmApplication(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		if (session==null||session.getAttribute("userid")==null) {
			response.sendRedirect("");
			return null;
		}
		int projectId=Integer.parseInt(request.getParameter("projectId"));
		int userId=Integer.parseInt(request.getParameter("userId"));
		int itemId=Integer.parseInt(request.getParameter("itemId"));
		applicationService.changeApplicationState(Application.WAITCHECKER,projectId,userId,itemId);
		return "success";
	}
	
	@RequestMapping(value="user/rejectApplication")
	@ResponseBody
	public String rejectApplication(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		if (session==null||session.getAttribute("userid")==null) {
			response.sendRedirect("");
			return null;
		}
		int projectId=Integer.parseInt(request.getParameter("projectId"));
		int userId=Integer.parseInt(request.getParameter("userId"));
		int itemId=Integer.parseInt(request.getParameter("itemId"));
		applicationService.changeApplicationState(Application.REWRITE,projectId,userId,itemId);
		return "fail";
	}

	
	@RequestMapping("user/leadProject")
	public String leadProject(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model) throws IOException{
		if (session==null||session.getAttribute("userid")==null) {
			response.sendRedirect("");
			return null;
		}
		int sessionId=(Integer)session.getAttribute("userid");
		int projectId=Integer.parseInt(request.getParameter("id"));
		
		List<Ipmapping> ipmappings=itemService.getIpmapByProjectId(projectId);
		model.addAttribute("iplist", ipmappings);
		model.addAttribute("project", projectService.getProject(projectId));
		
		List<User> userList=userService.getAllUserByRole(User.ROLE_USER);
		//添加成员需要把主持人给移除
		User leader=null;
		for (User user : userList) {
			if (user.getUid()==sessionId) {
				leader=user;
				break;
			}
		}
		userList.remove(leader);
		model.addAttribute("userlist", userList);
		ArrayList<String> temp=new ArrayList<String>();
		ArrayList<Integer> members=projectService.getMembersFromProjectID(projectId);
		if (members!=null&&members.size()!=0) {
			for (Integer integer : members) {
				temp.add(String.valueOf(integer));
			}
			model.addAttribute("members",temp.toArray(new String[temp.size()]));			
		}
		return "user/leadProject";
	}
	
	//处理lead project提交的表单
	@RequestMapping("user/leadProject/submit")
	public String leadProjectSubmit(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model) throws IOException{
		if (session==null||session.getAttribute("userid")==null) {
			response.sendRedirect("");
			return null;
		}
		int sessionId=(Integer)session.getAttribute("userid");
		int projectId=Integer.parseInt(request.getParameter("id"));
		List<Ipmapping> ipmappings=itemService.getIpmapByProjectId(projectId);
		model.addAttribute("iplist", ipmappings);
		model.addAttribute("project", projectService.getProject(projectId));
		List<User> userList=userService.getAllUserByRole(User.ROLE_USER);
		//取掉leader自己
		userList.remove(userService.getUser(sessionId));
		model.addAttribute("userlist", userList);
		int left=Integer.parseInt(request.getParameter("left"));
		if (left<0) {
			request.setAttribute("message", "your left money is below zero.");
			return "user/leadProject";
		}
		//获得网页上选中的项，处理
		String[] members=request.getParameterValues("members");
		model.addAttribute("members", members);
		//iid,amount
		Map<Integer, Integer> map=new HashMap<Integer, Integer>();
		for (Ipmapping ipmapping : ipmappings) {
			int iid=ipmapping.getId().getItem().getIid();
			String value=request.getParameter(String.valueOf(iid));
			if (value==null||value.equals("")) {
				request.setAttribute("message", "please complete this page");
				return "user/leadProject";
			}
			int tempAmount=Integer.parseInt(value);
			map.put(iid, tempAmount);
			model.addAttribute(String.valueOf(iid), tempAmount);
		}
		ArrayList<Integer> membersID=new ArrayList<Integer>();
		for (String s : members) {
			int temp=Integer.parseInt(s);
			membersID.add(temp);
		}
		projectService.reviseProject(projectId, membersID,map);
		
		//表单（修改project）处理成功，则重定向到userhome
		request.setAttribute("title","project completed!");
		request.setAttribute("message", "You have successfully assign the project");
		request.setAttribute("redirect", "userhome");
		return "template/message";
	}
	
	@RequestMapping(value={"user/reviseApplication","user/writeApplication"})
	public String writeApplication(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model) throws IOException{
		if (session==null||session.getAttribute("userid")==null) {
			response.sendRedirect("");
			return null;
		}
		int sessionId=(Integer)session.getAttribute("userid");
		 int itemId=-1;
		 int projectId=Integer.parseInt(request.getParameter("pid"));
		model.addAttribute("project", projectService.getProject(projectId));
		if (request.getParameter("iid")!=null) {
			itemId=Integer.parseInt(request.getParameter("iid"));
			Application application=applicationService.getApplicationFromId(sessionId,projectId,itemId);
			model.addAttribute("amount", application.getAmount());
			model.addAttribute("iid", itemId);
		}
		//获得这个项目的可以报销的项目List<iid>
		List<Ipmapping> itemIdList=itemService.getIpmapByProjectId(projectId);
		if (itemId!=-1) {
			for (Ipmapping ipmapping : itemIdList) {
				if (ipmapping.getId().getItem().getIid()==itemId) {
					model.addAttribute("max",ipmapping.getAmount()/ipmapping.getRate() );
					break;
				}
			}
		}
		model.addAttribute("iplist", itemIdList);
		return "user/submitApplication";
	}
	
	@RequestMapping(value="user/writeApplication/submit")
	public String submitApplication(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model) throws IOException{
		if (session==null||session.getAttribute("userid")==null) {
			response.sendRedirect("");
			return null;
		}
		int sessionId=(Integer)session.getAttribute("userid");
		int pid=Integer.parseInt(request.getParameter("pid"));
		int iid=Integer.parseInt(request.getParameter("item"));
		int amount=Integer.parseInt(request.getParameter("amount"));
		applicationService.submitApplication(sessionId,pid,iid,amount);

		request.setAttribute("title","submit application successfully!");
		request.setAttribute("message", "You have successfully submit an application!");
		request.setAttribute("redirect", "userhome");
		return "template/message";
	}
}
