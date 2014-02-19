package cn.edu.nju.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.nju.dao.Application;
import cn.edu.nju.dao.ApplicationDAO;
import cn.edu.nju.dao.ApplicationId;
import cn.edu.nju.dao.ItemDAO;
import cn.edu.nju.dao.Project;
import cn.edu.nju.dao.ProjectDAO;
import cn.edu.nju.dao.Upmapping;
import cn.edu.nju.dao.UpmappingDAO;
import cn.edu.nju.dao.User;
import cn.edu.nju.dao.UserDAO;

@Service
public class ApplicationService {
	@Resource
	ApplicationDAO applicationDAO;
	@Resource
	UpmappingDAO upmappingDAO;
	@Resource
	ProjectDAO projectDAO;
	@Resource
	UserDAO userDAO;
	@Resource
	ItemDAO itemDAO;

	public List<Application> getApplicationsFromLeader(int sessionId) {
		List<Upmapping> list=upmappingDAO.findByUserID(sessionId, Upmapping.ROLE_LEADER);
		List<Application> applications=new ArrayList<Application>();
		for (Upmapping upmapping : list) {
			applications.addAll(applicationDAO.findByProjectId(upmapping.getId().getProject().getPid(),Application.WAITLEADER));
		}
		return applications;
	}

	public List<Application> getApplicationsFromMember(int sessionId) {
		List<Application> applications=applicationDAO.findByProperty("id.user.uid", sessionId);
		return applications;
	}

	public void changeApplicationState(short state, int projectId,
			int userId, int itemId) {
		// TODO Auto-generated method stub
		ApplicationId id=new ApplicationId(userDAO.findById(userId), projectDAO.findById(projectId), itemDAO.findById(itemId));
		Application application=applicationDAO.findById(id);
		application.setState(state);
		applicationDAO.merge(application);
	}

	public Application getApplicationFromId(int sessionId, int projectId,
			int itemId) {
		return applicationDAO.findById(new ApplicationId(userDAO.findById(sessionId), projectDAO.findById(projectId), itemDAO.findById(itemId)));
	}

	public void submitApplication(int sessionId, int pid, int iid, int amount) {
		ApplicationId id=new ApplicationId(userDAO.findById(sessionId), projectDAO.findById(pid), itemDAO.findById(iid));
		Application application=new Application(id, amount, Application.WAITLEADER, new Date());
		applicationDAO.merge(application);
	}
	
	
	
}
