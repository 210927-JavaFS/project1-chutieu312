package com.revature.controllers;

import java.util.Date;
import java.util.List;

import com.revature.models.Reimb;
import com.revature.models.ReimbAprroveDeny;
import com.revature.models.ReimbStatus;
import com.revature.models.User;
import com.revature.services.ReimbService;
import com.revature.services.ReimbStatusDAO;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ReimbController implements Controller  {

	private ReimbService reimbService = new ReimbService();
	private ReimbStatusDAO reimbStatusDAO = new ReimbStatusDAO();
	private ReimbStatus reimbStatusPending = new ReimbStatus(1,"Pending");

	public Handler getAllReimbs = (ctx) -> {
		List<Reimb> list = reimbService.getAllReimbs();

		ctx.json(list);
		ctx.status(200);
	};

	public Handler getReimb = (ctx) -> {
		try {
			String idString = ctx.pathParam("reimb");
			int id = Integer.parseInt(idString);
			Reimb reimb = reimbService.getReimb(id);
			ctx.json(reimb);
			ctx.status(200);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			ctx.status(406);
		}
	};
	
	public Handler getReimbByAuthor = (ctx) -> {
		try {
			String idString = ctx.pathParam("authorId");
			int authorId = Integer.parseInt(idString);
			List<Reimb> list = reimbService.getReimbByAuthor(authorId);
			System.out.println("list in getReimbByAuthor: "+list);
			ctx.json(list);
			ctx.status(200);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			ctx.status(406);
		}
	};
	
	public Handler getReimbByStatus = (ctx) -> {
		try {
			String status = ctx.pathParam("status");
			List<Reimb> list = reimbService.getReimbByStatus(status);
			System.out.println("list in getReimbByStatus: "+list);
			ctx.json(list);
			ctx.status(200);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			ctx.status(406);
		}
	};


	public Handler addReimb = (ctx) -> {
		Reimb reimb = ctx.bodyAsClass(Reimb.class);
		reimb.setSubmittedlDate(new Date());
		reimb.setReimbStatus(reimbStatusPending);
		reimb.setAuthor((User) ctx.req.getSession().getAttribute("User"));
		//System.out.println("Tao ne:   "+reimb.toString());
		if (reimbService.addReimb(reimb)) {
			ctx.status(201);
		} else {
			ctx.status(400);
		}
	};

	public Handler updateReimb = (ctx) -> {
		ReimbAprroveDeny reimbAprroveDeny = ctx.bodyAsClass(ReimbAprroveDeny.class);
		int reimbId = Integer.parseInt(reimbAprroveDeny.reimbId);
		Reimb reimb = reimbService.getReimb(reimbId);
		reimb.setResolvedlDate(new Date());
		ReimbStatus reimbStatus = reimbStatusDAO.findByStatus(reimbAprroveDeny.reimbStatus);
		reimb.setReimbStatus(reimbStatus);
		reimb.setResolver((User) ctx.req.getSession().getAttribute("User"));
		
		if (reimbService.updateReimb(reimb)) {
			ctx.status(200);
		} else {
			ctx.status(400);
		}
	};

	public Handler deleteReimb = (ctx) -> {
		String id = ctx.pathParam("reimb");
		try {
			int reimbId = Integer.parseInt(id);
			if (reimbService.deleteReimb(reimbId)) {
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
		app.get("/reimbs/authors/:authorId", this.getReimbByAuthor);
		app.get("/reimbs/status/:status", this.getReimbByStatus);
		app.post("/reimbs", this.addReimb);
		app.put("/reimbs", this.updateReimb);
		app.delete("/reimbs/:reimb", this.deleteReimb);
	}
}
