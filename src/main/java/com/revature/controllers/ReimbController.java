package com.revature.controllers;

import java.util.List;

import com.revature.models.Reimb;
import com.revature.services.ReimbService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ReimbController implements Controller  {

	private ReimbService reimbService = new ReimbService();

	public Handler getAllReimbs = (ctx) -> {
		List<Reimb> list = reimbService.getAllReimbs();

		ctx.json(list);
		ctx.status(200);
	};

	public Handler getReimb = (ctx) -> {
		try {
			String idString = ctx.pathParam("reimb");
			int id = Integer.parseInt(idString);
			Reimb Reimb = reimbService.getReimb(id);
			ctx.json(Reimb);
			ctx.status(200);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			ctx.status(406);
		}
	};

	public Handler addReimb = (ctx) -> {
		Reimb reimb = ctx.bodyAsClass(Reimb.class);
		System.out.println("Tao ne:   "+reimb.toString());
		if (reimbService.addReimb(reimb)) {
			ctx.status(201);
		} else {
			ctx.status(400);
		}
	};

	public Handler updateReimb = (ctx) -> {
		Reimb Reimb = ctx.bodyAsClass(Reimb.class);
		if (reimbService.updateReimb(Reimb)) {
			ctx.status(200);
		} else {
			ctx.status(400);
		}
	};

	public Handler deleteReimb = (ctx) -> {
		String id = ctx.pathParam("reimb");
		try {
			int Reimb = Integer.parseInt(id);
			if (reimbService.deleteReimb(Reimb)) {
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
		app.get("/reimbs", this.getAllReimbs);
		app.get("/reimbs/:reimb", this.getReimb);
		app.post("/reimbs", this.addReimb);
		app.put("/reimbs", this.updateReimb);
		app.delete("/reimbs/:reimb", this.deleteReimb);
	}
}
