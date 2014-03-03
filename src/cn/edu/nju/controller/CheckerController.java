package cn.edu.nju.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.nju.entity.Application;
import cn.edu.nju.entity.Project;
import cn.edu.nju.entity.User;
import cn.edu.nju.service.ApplicationService;
import cn.edu.nju.service.ProjectService;
import cn.edu.nju.service.UserService;

@Controller
public class CheckerController {
	@Resource
	ApplicationService applicationService;
	@Resource
	ProjectService projectService;
	@Resource
	UserService userService;
	
	@RequestMapping("checkerhome")
	public String checkerHome(HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session)
			throws IOException {
		if (session == null || session.getAttribute("userid") == null) {
			response.sendRedirect("");
			return null;
		}	
		int sessionId = (Integer) session.getAttribute("userid");
		// ��ȡ��Ҫ����˵ı�����Ŀ������״̬Ϊ0
		List<Application> applications = applicationService
				.getApplicationsChecking();
		// �ڶ���IntegerΪ�ɱ����ı���
		Map<Application, Short> map = new HashMap<Application, Short>();
		for (Application application : applications) {
			map.put(application, projectService.getRateByPidIid(application
					.getId().getProject(), application.getId().getItem()));
		}
		model.addAttribute("verify", map);
		
		request.setAttribute("users", userService.getAllUserByRole(User.ROLE_USER));
		request.setAttribute("projects", projectService.getAllProjects());
		
		return "checker/checkerHome";
	}
	
	@RequestMapping("userinfo")
	public String userinfo(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model) throws IOException{
		if (session==null||session.getAttribute("userid")==null) {
			response.sendRedirect("");
			return null;
		}
		Short sessionRole = (Short) session.getAttribute("userrole");
		int userId=Integer.parseInt(request.getParameter("userid"));
		User user=userService.getUser(userId);
		model.addAttribute("user", user);
		String role="δ֪";
		Short userrole=user.getRole();
		if(userrole==1){//User.ROLE_USER){
			role="�û�";
		}else if(userrole==0){//User.ROLE_MANAGER){
			role="����Ա";
		}else if(userrole==2){//User.ROLE_CHECKER){
			role="���Ա";
		}else if(userrole==3){//User.ROLE_DIRECTOR){
			role="��������";
		}
		model.addAttribute("userrole", role);
		//�����director������ÿ��ͳ����Ϣ
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM");
		if (sessionRole==3) {
			model.addAttribute("director", true);
			List<Application> applications=applicationService.getApplicationsFromMember(userId);
			//Map<���-�·ݣ����>
			Map<String, Integer> map=new HashMap<String, Integer>();
			for (Application application : applications) {
				Date date=application.getTime();
				String dateString=df.format(date);
				if (map.containsKey(dateString)) {
					int originAmount=map.get(dateString);
					map.put(dateString, originAmount+application.getAmount());
				}else {
					map.put(dateString, application.getAmount());
				}
			}
			model.addAttribute("map",map);
		}
		return "checker/userInfo";
	}
	
	@RequestMapping("projectinfo")
	public String projectinfo(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model) throws IOException{
		if (session==null||session.getAttribute("userid")==null) {
			response.sendRedirect("");
			return null;
		}
		Short sessionRole = (Short) session.getAttribute("userrole");
		int projectId=Integer.parseInt(request.getParameter("projectid"));
		Project project=projectService.getProject(projectId);
		model.addAttribute("project", project);
		//�����director������ÿ��ͳ����Ϣ
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM");
				if (sessionRole==3) {
					model.addAttribute("director", true);
					List<Application> applications=applicationService.getApplicationsFromPid(projectId);
					//Map<���-�·ݣ����>
					Map<String, Integer> map=new HashMap<String, Integer>();
					for (Application application : applications) {
						Date date=application.getTime();
						String dateString=df.format(date);
						if (map.containsKey(dateString)) {
							int originAmount=map.get(dateString);
							map.put(dateString, originAmount+application.getAmount());
						}else {
							map.put(dateString, application.getAmount());
						}
					}
					model.addAttribute("map",map);
				}
		return "checker/projectInfo";
	}
	
	@RequestMapping(value="checker/confirmApplication")
	@ResponseBody
	public String confirmApplication(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		if (session==null||session.getAttribute("userid")==null) {
			response.sendRedirect("");
			return null;
		}
		int projectId=Integer.parseInt(request.getParameter("projectId"));
		int userId=Integer.parseInt(request.getParameter("userId"));
		int itemId=Integer.parseInt(request.getParameter("itemId"));
		applicationService.changeApplicationState(Application.SUCCESS,projectId,userId,itemId);
		return "success";
	}
	
	@RequestMapping(value="checker/rejectApplication")
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
}
