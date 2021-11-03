package com.revature.daos;

import java.util.List;

import com.revature.models.Reimb;

public interface ReimbDAO {

	List<Reimb> findAllReimbs();
	Reimb findByReimbId(int reimbId);
	boolean addReimb(Reimb reimb);
	boolean updateReimb(Reimb reimb);
	boolean deleteReimb(Reimb reimb);
	List<Reimb> findReimByAuthorId(int authorId);
	List<Reimb> findReimByStatus(String status);
}
