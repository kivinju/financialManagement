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

import cn.edu.nju.dao.Application;
import cn.edu.nju.dao.Project;
import cn.edu.nju.dao.Upmapping;
import cn.edu.nju.service.ApplicationService;
import cn.edu.nju.service.ProjectService;

@Controller
public class UserController {
	@Resource
	ProjectService projectService;
	@Resource
	ApplicationService applicationService;
	
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
			map.put(application, projectService.getRateByPidIid(application.getId().getProject(),application.getId().getItem()));
		}
		model.addAttribute("applications", map2);
		return "user/userHome";
	}
}
