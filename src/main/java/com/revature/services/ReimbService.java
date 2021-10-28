package com.revature.services;

import java.util.List;

import com.revature.models.Reimb;
import com.revature.daos.ReimbDAO;
import com.revature.daos.ReimbDAOImpl;

public class ReimbService {

	private ReimbDAO ReimbDao = new ReimbDAOImpl();

	public List<Reimb> getAllReimbs() {
		return ReimbDao.findAllReimbs();
	}

	public Reimb getReimb(int reimbId) {
		Reimb Reimb = ReimbDao.findByReimbId(reimbId);
		if (Reimb != null) {
			return Reimb;
		}else {
			return new Reimb();
		}
	}
	
	public boolean addReimb(Reimb Reimb) {
		return ReimbDao.addReimb(Reimb);
	}
	
	public boolean updateReimb(Reimb Reimb) {
		return ReimbDao.updateReimb(Reimb);
	}
	
	public boolean deleteReimb(int reimbId) {
		Reimb Reimb = getReimb(reimbId);
		return ReimbDao.deleteReimb(Reimb);
	}
	
}
