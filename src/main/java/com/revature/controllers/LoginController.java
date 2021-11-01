package com.revature.controllers;

import com.revature.models.User;

import javax.servlet.http.HttpSession;

import com.revature.models.UserDTO;
import com.revature.services.LoginService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class LoginController implements Controller {

	private LoginService loginService = new LoginService();
	//HttpSession httpSession;

	private Handler loginAttempt = (ctx) -> {
		UserDTO userDto = ctx.bodyAsClass(UserDTO.class);
		
		System.out.println(userDto.toString());
		
		User user = loginService.login(userDto);
		
		if(user!=null) {
			//If someone logs in I will create a session
			//httpSession = ctx.req.getSession(); //This will create a session for us to track the client that logged in. 
			//HttpServletRequest -> 
			ctx.req.getSession().setAttribute("User", user);
			System.out.println("httpSession.getAtribute: "+ ctx.req.getSession().getAttribute("User"));
			
			ctx.json(user);
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
