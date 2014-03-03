package cn.edu.nju.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.nju.entity.User;
import cn.edu.nju.service.ProjectService;
import cn.edu.nju.service.UserService;

@Controller
public class DirectorController {
	@Resource
	UserService userService;
	@Resource
	ProjectService projectService;
	
	
	@RequestMapping("/directorhome")
	public String directorHome(HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session)
			throws IOException {
		if (session == null || session.getAttribute("userid") == null) {
			response.sendRedirect("");
			return null;
		}	
		int sessionId = (Integer) session.getAttribute("userid");
		
		request.setAttribute("users", userService.getAllUserByRole(User.ROLE_USER));
		request.setAttribute("projects", projectService.getAllProjects());
		
		return "director/directorHome";
	}
}
