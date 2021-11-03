package com.revature.controllers;

//import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class LogoutController implements Controller {
	
	//HttpSession httpSession;
	public static Logger log = LoggerFactory.getLogger(LogoutController.class);

	private Handler logoutAttempt = (ctx) -> {
		System.out.println("Before invalidate");
		System.out.println("httpSession.getAtribute: "+ ctx.req.getSession().getAttribute("User"));	
		ctx.req.getSession().invalidate();// invalidates any open session tracking the client.
		ctx.status(200);
		log.info("User logout successfully.");
	};
	
	

	@Override
	public void addRoutes(Javalin app) {
		app.post("/logout", this.logoutAttempt);

	}

}
