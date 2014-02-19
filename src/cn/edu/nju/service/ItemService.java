package cn.edu.nju.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.nju.dao.Ipmapping;
import cn.edu.nju.dao.IpmappingDAO;
import cn.edu.nju.dao.Item;
import cn.edu.nju.dao.ItemDAO;

@Service
public class ItemService {
	@Resource
	ItemDAO itemDAO;
	@Resource
	IpmappingDAO ipmappingDAO;
	
	public List<Item> getAllItems() {
		return itemDAO.findAll();
	}

	public List<Ipmapping> getIpmapByProjectId(int projectId) {
		return ipmappingDAO.findByProjectID(projectId);
	}
	
	
}
