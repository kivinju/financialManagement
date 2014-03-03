package cn.edu.nju.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.xml.crypto.Data;

import org.springframework.stereotype.Service;

import com.sun.org.apache.regexp.internal.recompile;

import cn.edu.nju.dao.ApplicationDAO;
import cn.edu.nju.dao.IpmappingDAO;
import cn.edu.nju.dao.ItemDAO;
import cn.edu.nju.dao.ProjectDAO;
import cn.edu.nju.dao.UpmappingDAO;
import cn.edu.nju.dao.UserDAO;
import cn.edu.nju.entity.Application;
import cn.edu.nju.entity.Ipmapping;
import cn.edu.nju.entity.IpmappingId;
import cn.edu.nju.entity.Item;
import cn.edu.nju.entity.Project;
import cn.edu.nju.entity.Upmapping;
import cn.edu.nju.entity.UpmappingId;
import cn.edu.nju.entity.User;

@Service
public class ProjectService {
	@Resource
	UserDAO userDAO;
	@Resource
	ProjectDAO projectDAO;
	@Resource
	UpmappingDAO upmappingDAO;
	@Resource
	IpmappingDAO ipmappingDAO;
	@Resource
	ItemDAO itemDAO;
	@Resource
	ApplicationDAO applicationDAO;

	public List<Project> getAllProjects() {
		System.out.println("get all projects");
		return projectDAO.findAll();
	}

	public void addProject(Project project, int leaderID,
			ArrayList<Integer> membersID, Map<Integer, Short> itemMap) {
		projectDAO.save(project);
		int projectID = projectDAO.getLastProjectID();
		project = projectDAO.findById(projectID);
		upmappingDAO.save(new Upmapping(new UpmappingId(userDAO
				.findById(leaderID), project), Upmapping.ROLE_LEADER));
		for (Integer id : membersID) {
			upmappingDAO.save(new Upmapping(new UpmappingId(userDAO
					.findById(id), project), Upmapping.ROLE_MEMBER));
		}
		Iterator<Entry<Integer, Short>> iter = itemMap.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<Integer, Short> entry=iter.next();
			ipmappingDAO.save(new Ipmapping(new IpmappingId(project, itemDAO.findById(entry.getKey())), entry.getValue(),0));
		}
	}

	public void deleteProject(int projectID) {
		projectDAO.delete(getProject(projectID));
	}

	public Project getProject(int projectID) {
		return projectDAO.findById(projectID);
	}

	public void reviseProject(Project project, int leaderID, ArrayList<Integer> membersID, Map<Integer, Short> itemMap) {
		projectDAO.merge(project);
		int projectID = project.getPid();
		//先删除
		List<Ipmapping> list1=ipmappingDAO.findByProjectID(projectID);
		for (Ipmapping ipmapping : list1) {
			ipmappingDAO.delete(ipmapping);
		}
		List<Upmapping> list2=upmappingDAO.findByProjectID(projectID);
		for (Upmapping upmapping : list2) {
			upmappingDAO.delete(upmapping);
		}
		//同添加
		project = projectDAO.findById(projectID);
		upmappingDAO.save(new Upmapping(new UpmappingId(userDAO
				.findById(leaderID), project), Upmapping.ROLE_LEADER));
		for (Integer id : membersID) {
			upmappingDAO.save(new Upmapping(new UpmappingId(userDAO
					.findById(id), project), Upmapping.ROLE_MEMBER));
		}
		Iterator<Entry<Integer, Short>> iter = itemMap.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<Integer, Short> entry=iter.next();
			ipmappingDAO.save(new Ipmapping(new IpmappingId(project, itemDAO.findById(entry.getKey())), entry.getValue(),0));
		}
	}

	public void reviseProject(int projectId, ArrayList<Integer> membersID,
			Map<Integer, Integer> map) {
		List<Upmapping> list2=upmappingDAO.findByProjectID(projectId);
		for (Upmapping upmapping : list2) {
			if (upmapping.getUprole()==Upmapping.ROLE_MEMBER) {
				upmappingDAO.delete(upmapping);
			}
		}
		Project project = projectDAO.findById(projectId);
		for (Integer id : membersID) {
			upmappingDAO.save(new Upmapping(new UpmappingId(userDAO
					.findById(id), project), Upmapping.ROLE_MEMBER));
		}
		Iterator<Entry<Integer, Integer>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<Integer, Integer> entry=iter.next();
			Ipmapping ipmapping=ipmappingDAO.findById(new IpmappingId(projectDAO.findById(projectId), itemDAO.findById(entry.getKey())));
			ipmapping.setAmount(entry.getValue());
			ipmappingDAO.merge(ipmapping);
		}
	}
	
	public Map<Integer, Short> getItemsFromProjectID(int projectID) {
		List<Ipmapping> list=ipmappingDAO.findByProjectID(projectID);
		Map<Integer, Short> map=new HashMap<Integer, Short>();
		for (Ipmapping ipmapping : list) {
			map.put(ipmapping.getId().getItem().getIid(), ipmapping.getRate());
		}
		return map;
	}

	public int getLeaderFromProjectID(int projectID) {
		List<Upmapping> list=upmappingDAO.findByProjectID(projectID);
		for (Upmapping upmapping : list) {
			if (upmapping.getUprole()==upmapping.ROLE_LEADER) {
				return upmapping.getId().getUser().getUid();
			}
		}
		return -1;
	}

	public ArrayList<Integer> getMembersFromProjectID(int projectID) {
		ArrayList<Integer> temp= new ArrayList<Integer>();
		List<Upmapping> list=upmappingDAO.findByProjectID(projectID);
		for (Upmapping upmapping : list) {
			if (upmapping.getUprole()==upmapping.ROLE_MEMBER) {
				System.out.println(upmapping.getUprole());
				temp.add(upmapping.getId().getUser().getUid());
			}
		}
		return temp;
	}

	public Map<Project, Boolean> getProjectsFromUserId(int sessionId) {
		List<Upmapping> list=upmappingDAO.findByProperty("id.user.uid", sessionId);
		Map<Project,Boolean> projects=new HashMap<Project, Boolean>();
		for (Upmapping upmapping : list) {
			Project temp=projectDAO.findById(upmapping.getId().getProject().getPid());
			if (isProjectActive(temp)) {
				projects.put(temp,upmapping.getUprole());
			}
		}
		return projects;
	}
	
	//计算一个项目已经用了多少钱了
	public int hasUse(int projectId) {
		int amount=0;
		List<Application> applications=applicationDAO.findByProjectId(projectId, Application.SUCCESS);
		for (Application application : applications) {
			amount+=application.getAmount();
		}
		return amount;
	}
	
	private static boolean isBefore(Date date1,Date date2){
		if (date1.getTime()<date2.getTime()) {
			return true;
		}
		return false;
	}
	private boolean isProjectActive(Project project) {
		return isBefore(new Date(),project.getEndDate());
	}

	public Short getRateByPidIid(Project project, Item item) {
		return ipmappingDAO.findById(new IpmappingId(project, item)).getRate();
	}

}
