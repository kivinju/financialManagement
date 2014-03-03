package cn.edu.nju.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.nju.dao.IpmappingDAO;
import cn.edu.nju.dao.ItemDAO;
import cn.edu.nju.entity.Ipmapping;
import cn.edu.nju.entity.Item;

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
