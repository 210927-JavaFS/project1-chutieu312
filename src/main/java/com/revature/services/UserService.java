package com.revature.services;

import java.util.List;

import com.revature.models.User;
import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;

public class UserService {

	private UserDAO userDao = new UserDAOImpl();

	public List<User> getAllUsers() {
		return userDao.findAllUsers();
	}

	public User getUser(int id) {
		User user = userDao.findById(id);
		if (user != null) {
			return user;
		}else {
			return new User();
		}
	}
	
	public boolean addUser(User user) {
		return userDao.addUser(user);
	}
	
	public boolean updateUser(User user) {
		return userDao.updateUser(user);
	}
	
	public boolean deleteUser(int id) {
		User user = getUser(id);
		return userDao.deleteUser(user);
	}
	
}
