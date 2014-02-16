package cn.edu.nju.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.nju.dao.Application;
import cn.edu.nju.dao.ApplicationDAO;
import cn.edu.nju.dao.Project;
import cn.edu.nju.dao.ProjectDAO;
import cn.edu.nju.dao.Upmapping;
import cn.edu.nju.dao.UpmappingDAO;
import cn.edu.nju.dao.User;

@Service
public class ApplicationService {
	@Resource
	ApplicationDAO applicationDAO;
	@Resource
	UpmappingDAO upmappingDAO;

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
	
	
	
}
