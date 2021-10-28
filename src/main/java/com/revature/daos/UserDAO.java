package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {
	List<User> findAllUsers();
	User findByUsername(String username);
	User findById(int id);
	boolean addUser(User user);
	boolean updateUser(User user);
	boolean deleteUser(User user);
}
