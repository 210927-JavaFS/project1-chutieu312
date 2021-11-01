package com.revature.controllers;

import javax.servlet.http.HttpSession;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class LogoutController implements Controller {
	
	HttpSession httpSession;

	private Handler logoutAttempt = (ctx) -> {
		System.out.println("Before invalidate");
		System.out.println("httpSession.getAtribute: "+ ctx.req.getSession().getAttribute("User"));	
		ctx.req.getSession().invalidate();// invalidates any open session tracking the client.
		ctx.status(200);
	};
	
	

	@Override
	public void addRoutes(Javalin app) {
		app.post("/logout", this.logoutAttempt);

	}

}
