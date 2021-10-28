package com.revature.services;

import com.revature.models.UserDTO;
import com.revature.models.UserLogin;


public class LoginService {

	private UserLoginDAO userLoginDAO = new UserLoginDAO();
	
	public boolean login(UserDTO userDto) {
		UserLogin userLogin = userLoginDAO.getByUsername(userDto.username);
		
		if(userLogin!=null && (userDto.password.hashCode()==userLogin.getPassword())) {
			return true;
		}
		
		return false;
	}
}
