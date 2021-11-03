package com.revature.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.models.ReimbStatus;
import com.revature.utils.HibernateUtil;

public class ReimbStatusDAO {
	
	public ReimbStatus findByStatus(String status) {
		
		Session session = HibernateUtil.getSession();
		String hql = "FROM ReimbStatus rt WHERE rt.status = :status";
		Query<ReimbStatus> query = session.createQuery(hql);
		query.setParameter("status",status);
		List<ReimbStatus> results = query.list();
		return results.get(0);
	}

}
