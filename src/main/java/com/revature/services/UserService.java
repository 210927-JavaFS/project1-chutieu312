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
		User User = UserDao.findById(id);
		if (User != null) {
			return User;
		}else {
			return new User();
		}
	}
	
	public boolean addUser(User User) {
		return UserDao.addUser(User);
	}
	
	public boolean updateUser(User User) {
		return UserDao.updateUser(User);
	}
	
	public boolean deleteUser(int id) {
		User User = getUser(id);
		return UserDao.deleteUser(User);
	}
	
}
