package com.revature.models;

public class UserLogin {
	
	private String username;
	private int password;
	private String role;
	
	public UserLogin(String username, int password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public int getPassword() {
		return password;
	}

}
