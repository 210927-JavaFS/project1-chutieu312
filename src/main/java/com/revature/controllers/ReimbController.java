package com.revature.controllers;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	public static Logger log = LoggerFactory.getLogger(ReimbController.class);

	public Handler getAllReimbs = (ctx) -> {
		log.info("In Handler getAllReimbs");
		List<Reimb> list = reimbService.getAllReimbs();
		ctx.json(list);
		ctx.status(200);
		log.info("getAllReimbs successfully.\n");
		for(Reimb r:list) {
			log.info(r.toString()+"\n");
		}
	};

	public Handler getReimb = (ctx) -> {
		log.info("In Handler getReimb");
		try {
			String idString = ctx.pathParam("reimb");
			int id = Integer.parseInt(idString);
			Reimb reimb = reimbService.getReimb(id);
			ctx.json(reimb);
			ctx.status(200);
			log.info("getReimb successfully: "+reimb);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			ctx.status(406);
			log.info("getReimb unsuccessfully.");
		}
	};
	
	public Handler getReimbByAuthor = (ctx) -> {
		log.info("In getReimbByAuthor");
		try {
			String idString = ctx.pathParam("authorId");
			int authorId = Integer.parseInt(idString);
			List<Reimb> list = reimbService.getReimbByAuthor(authorId);
			System.out.println("list in getReimbByAuthor: "+list);
			ctx.json(list);
			ctx.status(200);
			log.info("getReimbByAuthor successfully.\n");
			for(Reimb r:list) {
				log.info(r.toString()+"\n");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			ctx.status(406);
			log.info("getReimbByAuthor unsuccessfully.");
		}
	};
	
	public Handler getReimbByStatus = (ctx) -> {
		log.info("In getReimbByStatus");
		try {
			String status = ctx.pathParam("status");
			List<Reimb> list = reimbService.getReimbByStatus(status);
			System.out.println("list in getReimbByStatus: "+list);
			ctx.json(list);
			ctx.status(200);
			log.info("getReimbByStatus successfully.\n");
			for(Reimb r:list) {
				log.info(r.toString()+"\n");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			ctx.status(406);
			log.info("getReimbByStatus unsuccessfully.");
		}
	};


	public Handler addReimb = (ctx) -> {
		log.info("In addReimb");
		Reimb reimb = ctx.bodyAsClass(Reimb.class);
		reimb.setSubmittedlDate(new Date());
		reimb.setReimbStatus(reimbStatusPending);
		reimb.setAuthor((User) ctx.req.getSession().getAttribute("User"));
		//System.out.println("Tao ne:   "+reimb.toString());
		if (reimbService.addReimb(reimb)) {
			ctx.status(201);
			log.info("addReimb successfully: \n"+reimb);
		} else {
			ctx.status(400);
			log.info("addReimb unsuccessfully.");
		}
	};

	public Handler updateReimb = (ctx) -> {
		log.info("In updateReimb");
		ReimbAprroveDeny reimbAprroveDeny = ctx.bodyAsClass(ReimbAprroveDeny.class);
		int reimbId = Integer.parseInt(reimbAprroveDeny.reimbId);
		Reimb reimb = reimbService.getReimb(reimbId);
		reimb.setResolvedlDate(new Date());
		ReimbStatus reimbStatus = reimbStatusDAO.findByStatus(reimbAprroveDeny.reimbStatus);
		reimb.setReimbStatus(reimbStatus);
		reimb.setResolver((User) ctx.req.getSession().getAttribute("User"));
		
		if (reimbService.updateReimb(reimb)) {
			ctx.status(200);
			log.info("updateReimb successfully: \n"+reimb);
		} else {
			ctx.status(400);
			log.info("updateReimb unsuccessfully.");
		}
	};

	public Handler deleteReimb = (ctx) -> {
		log.info("In deleteReimb");
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
