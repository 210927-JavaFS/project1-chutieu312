package com.revature.services;

import com.revature.Encrypt;
import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;
import com.revature.models.User;
import com.revature.models.UserDTO;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginService {

	private UserDAO userDao = new UserDAOImpl();
	public static Logger log = LoggerFactory.getLogger(LoginService.class);
	
	public User login(UserDTO userDto) {
		User user = userDao.findByUsername(userDto.username);
		
		if(user.equals(null)) {
			log.warn("Invalid username.");
			System.out.println("Invalid username.");
			return null;
		}
		else if(!Encrypt.encodePassword(userDto.password).equals(user.getPassWord())) {
				log.warn("Invalid password.");
				System.out.println("Invalid password.");
				return null;
			}
		else {
			System.out.println("Valid login "+new Date());
			
			
			return user;
		}
	}
}
