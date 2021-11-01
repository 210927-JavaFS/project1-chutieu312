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
		Reimb reimb = ReimbDao.findByReimbId(reimbId);
		if (reimb != null) {
			return reimb;
		}else {
			return new Reimb();
		}
	}
	
	public boolean addReimb(Reimb reimb) {
		return ReimbDao.addReimb(reimb);
	}
	
	public boolean updateReimb(Reimb reimb) {
		return ReimbDao.updateReimb(reimb);
	}
	
	public boolean deleteReimb(int reimbId) {
		Reimb reimb = getReimb(reimbId);
		return ReimbDao.deleteReimb(reimb);
	}

	public List<Reimb> getReimbByAuthor(int authorId) {
		return ReimbDao.findReimByAuthorId(authorId);
		
	}

	public List<Reimb> getReimbByStatus(String status) {
		return ReimbDao.findReimByStatus(status); 
	}
	
}
