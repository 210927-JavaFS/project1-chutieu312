package com.revature.controllers;

import com.revature.models.UserDTO;
import com.revature.services.LoginService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class LoginController implements Controller {

	private LoginService loginService = new LoginService();

	private Handler loginAttempt = (ctx) -> {
		UserDTO userDto = ctx.bodyAsClass(UserDTO.class);
		
		if(loginService.login(userDto)) {
			//If someone logs in I will create a session
			ctx.req.getSession(); //This will create a session for us to track the client that logged in. 
			
			ctx.status(200);
		}else {
			ctx.req.getSession().invalidate();// invalidates any open session tracking the client.
			ctx.status(401);
		}
	};
	
	@Override
	public void addRoutes(Javalin app) {
		app.post("/login", this.loginAttempt);
	}
	
	
}
