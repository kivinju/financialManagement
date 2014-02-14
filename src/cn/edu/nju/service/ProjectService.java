package cn.edu.nju.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.nju.dao.Project;
import cn.edu.nju.dao.ProjectDAO;
import cn.edu.nju.dao.Upmapping;
import cn.edu.nju.dao.UpmappingDAO;
import cn.edu.nju.dao.UpmappingId;
import cn.edu.nju.dao.User;
import cn.edu.nju.dao.UserDAO;

@Service
public class ProjectService {
	@Resource
	UserDAO userDAO;
	@Resource
	ProjectDAO projectDAO;
	@Resource
	UpmappingDAO upmappingDAO;
	
	public List<Project> getAllProjects() {
		System.out.println("get all projects");
		return projectDAO.findAll();
	}

	public void addProject(Project project, int leaderID, ArrayList<Integer> membersID) {
		projectDAO.save(project);
		int projectID=projectDAO.getLastProjectID();
		project=projectDAO.findById(projectID);
		upmappingDAO.save(new Upmapping(new UpmappingId(userDAO.findById(leaderID),project),Upmapping.ROLE_LEADER));
		for (Integer id : membersID) {
			upmappingDAO.save(new Upmapping(new UpmappingId(userDAO.findById(id), project),Upmapping.ROLE_MEMBER));
		}
	}

	public void deleteProject(int projectID) {
		projectDAO.delete(getProject(projectID));		
	}

	public Project getProject(int projectID) {
		return projectDAO.findById(projectID);
	}

	public void reviseProject(Project project) {
		projectDAO.merge(project);		
	}
	
	
}
