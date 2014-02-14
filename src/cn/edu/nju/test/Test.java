package cn.edu.nju.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import cn.edu.nju.dao.User;
import cn.edu.nju.dao.UserDAO;

public class Test {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new FileSystemXmlApplicationContext("/src/applicationContext.xml");
		UserDAO userDAO=UserDAO.getFromApplicationContext(ac);
		userDAO.save(new User("ada","123123123", (short) 0, "admin", "123456"));
	}
}
