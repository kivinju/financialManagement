package cn.edu.nju.service;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import javax.annotation.Resource;

import cn.edu.nju.dao.ProjectDAO;
import cn.edu.nju.entity.Project;

public class CheckTimer extends TimerTask {
	@Resource
	ProjectDAO projectDAO;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("check projects!");
		List<Project> list=projectDAO.findAll();
		Date now =new Date();
		for (Project project : list) {
			if(project.getEndDate().getTime()<now.getTime()){
				System.out.println("delete project "+project.getDescription());
				projectDAO.delete(project);
			}
		}
	}

}
