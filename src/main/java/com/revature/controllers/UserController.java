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
	public static Logger log = LoggerFactory.getLogger(UserController.class);

	public Handler getAllUsers = (ctx) -> {
		log.info("In Handler getAllUsers");
		List<User> list = userService.getAllUsers();

		ctx.json(list);
		ctx.status(200);
	};

	public Handler getUser = (ctx)->{
		try {
			log.info("In Handler getUse");
			String idString = ctx.pathParam("user");
			int id = Integer.parseInt(idString);
			User user = userService.getUser(id);
			ctx.json(user);
			ctx.status(200);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			ctx.status(406);
		}
	};

	public Handler addUser = (ctx)->{
		log.info("In Handler addUser");
		User user = ctx.bodyAsClass(User.class);
		if(userService.addUser(user)) {
			log.info("add User successfully");
			ctx.status(201);
		}else {
			log.warn("add User unsuccessfully");
			ctx.status(400);
		}
	};

	public Handler updateUser = (ctx)->{
		log.info("In Handler updateUser");
		User user = ctx.bodyAsClass(User.class);
		if(userService.updateUser(user)) {
			log.info("update User successfully");
			ctx.status(200);
		}else {
			log.warn("update User Unsuccessfully");
			ctx.status(400);
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
