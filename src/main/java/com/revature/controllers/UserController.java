package com.revature.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.revature.models.User;

import com.revature.services.UserService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class UserController implements Controller {

	private UserService userService = new UserService();
	public static Logger log= LoggerFactory.getLogger(UserController.class);

	public Handler getAllUsers = (ctx) -> {
		log.info("In Handler getAllUsers");
		List<User> list = userService.getAllUsers();
		ctx.json(list);
		ctx.status(200);
		log.info("getAllUsers successfully.\n");
		for(User u:list) {
			log.info(u.toString()+"\n");
		}
	};

	public Handler getUser = (ctx)->{
		log.info("In Handler getUser");
		try {
			String idString = ctx.pathParam("user");
			int id = Integer.parseInt(idString);
			User user = userService.getUser(id);
			ctx.json(user);
			ctx.status(200);
			log.info("getUser successfully: \n"+user);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			ctx.status(406);
			log.info("getUser unsuccessfully.");
		}
	};

	public Handler addUser = (ctx)->{
		log.info("In Handler addUser");
		User user = ctx.bodyAsClass(User.class);
		if(userService.addUser(user)) {
			ctx.status(201);
			log.info("addUser successfully.\n"+user);
		}else {
			ctx.status(400);
			log.info("addUser unsuccessfully.");
		}
	};

	public Handler updateUser = (ctx)->{
		log.info("In updateUser");
		User user = ctx.bodyAsClass(User.class);
		if(userService.updateUser(user)) {
			ctx.status(200);
			log.info("updateUser successfully.\n"+user);
		}else {
			ctx.status(400);
			log.info("updateUser unsuccessfully.");
		}
	};

	public Handler deleteUser = (ctx)->{
		String id = ctx.pathParam("user");
		try {
			int user = Integer.parseInt(id);
			if (userService.deleteUser(user)) {
				ctx.status(200);
			} else {
				ctx.status(400);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			ctx.status(406);
		}
	};

	@Override
	public void addRoutes(Javalin app) {
		app.get("/users", this.getAllUsers);
		app.get("/users/:user", this.getUser);
		app.post("/users", this.addUser);
		app.put("/users", this.updateUser);
		app.delete("/users/:user", this.deleteUser);
	}

}
