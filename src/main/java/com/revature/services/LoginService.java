package com.revature.services;

import com.revature.Encrypt;
import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;
import com.revature.models.User;
import com.revature.models.UserDTO;
import java.util.Date;

public class LoginService {

	private UserDAO userDao = new UserDAOImpl();
	
	public User login(UserDTO userDto) {
		User user = userDao.findByUsername(userDto.username);
		
		if(user.equals(null)) {
			System.out.println("Invalid username.");
			return null;
		}
		else if(!Encrypt.encodePassword(userDto.password).equals(user.getPassWord())) {
				System.out.println("Invalid password.");
				return null;
			}
		else {
			System.out.println("Valid login "+new Date());
			
			
			return user;
		}
	}
}
