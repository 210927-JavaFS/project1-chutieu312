package com.revature.services;

import com.revature.models.UserLogin;

public class UserLoginDAO {
	
	public UserLogin getByUsername(String username) {
		if(username.equals("a")) {
			return new UserLogin("a", "1".hashCode(), "Manager");
		}
		return null;
	}

}
