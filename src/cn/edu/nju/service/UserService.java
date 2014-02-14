package cn.edu.nju.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.nju.dao.User;
import cn.edu.nju.dao.UserDAO;

@Service
public class UserService {
	
	@Resource
	private UserDAO userDAO;
	
	public User login(String userName,String password) {
		System.out.println(userName+","+password);
		List<User> list=userDAO.findByUname(userName);
		for (User user : list) {
			if (user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}
	
	public int getUserRole(int userId) {
		System.out.println("find role:"+userId);
		User user=userDAO.findById(userId);
		if (user!=null) {
			return user.getRole();
		}
		return -1;
	}
	
	public ArrayList<User> getAllUsers() {
		System.out.println("get all users");
		return (ArrayList<User>)userDAO.findAll();
	}

	public ArrayList<User> getAllUserByRole(Short role) {
		return (ArrayList<User>)userDAO.findByRole(role);
	}
	
	public void deleteUser(int userID) {
		System.out.println("delete user");
		userDAO.delete(userDAO.findById(userID));
	}

	public void addUser(User user) {
		System.out.println("add user");
		userDAO.save(user);
	}
	
	public User getUser(int userID){
		return userDAO.findById(userID);
	}

	public void reviseUser(User user) {
		// TODO Auto-generated method stub
		userDAO.merge(user);
	}
}
