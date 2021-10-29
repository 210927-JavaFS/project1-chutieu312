package com.revature.services;

import java.util.List;

import com.revature.models.User;
import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;

public class UserService {

	private UserDAO UserDao = new UserDAOImpl();

	public List<User> getAllUsers() {
		return UserDao.findAllUsers();
	}

	public User getUser(int id) {
		User user = UserDao.findById(id);
		if (user != null) {
			return user;
		}else {
			return new User();
		}
	}
	
	public boolean addUser(User user) {
		return UserDao.addUser(user);
	}
	
	public boolean updateUser(User user) {
		return UserDao.updateUser(user);
	}
	
	public boolean deleteUser(int id) {
		User user = getUser(id);
		return UserDao.deleteUser(user);
	}
	
}
