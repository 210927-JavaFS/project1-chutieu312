package com.revature.controllers;

import java.util.List;

import com.revature.models.User;

import com.revature.services.UserService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class UserController implements Controller {

	private UserService userService = new UserService();

	public Handler getAllUsers = (ctx) -> {
		List<User> list = userService.getAllUsers();

		ctx.json(list);
		ctx.status(200);
	};

	public Handler getUser = (ctx)->{
		try {
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
		User User = ctx.bodyAsClass(User.class);
		if(userService.addUser(User)) {
			ctx.status(201);
		}else {
			ctx.status(400);
		}
	};

	public Handler updateUser = (ctx)->{
		User User = ctx.bodyAsClass(User.class);
		if(userService.updateUser(User)) {
			ctx.status(200);
		}else {
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
