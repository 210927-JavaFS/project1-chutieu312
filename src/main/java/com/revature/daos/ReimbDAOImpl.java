package com.revature.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.models.Reimb;
import com.revature.services.ReimbStatusDAO;
import com.revature.utils.HibernateUtil;

public class ReimbDAOImpl implements ReimbDAO {
	
	private UserDAO userDAO = new UserDAOImpl();
	
	private ReimbStatusDAO reimbStatusDAO = new ReimbStatusDAO();

	@Override
	public List<Reimb> findAllReimbs() {
		Session session = HibernateUtil.getSession();
		return session.createQuery("FROM Reimb").list();
	}

	@Override
	public Reimb findByReimbId(int reimbId) {
		Session session = HibernateUtil.getSession();
		return session.get(Reimb.class, reimbId);
	}

	@Override
	public boolean addReimb(Reimb reimb) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.save(reimb);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateReimb(Reimb reimb) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.merge(reimb);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteReimb(Reimb reimb) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.delete(reimb);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public List<Reimb> findReimByAuthorId(int authorId) {
		
		Session session = HibernateUtil.getSession();
		String hql = ("FROM Reimb r WHERE r.author = :author");
		Query<Reimb> query = session.createQuery(hql);
		query.setParameter("author",userDAO.findById(authorId));
		List<Reimb> list = query.list();
		return list;
	}

	@Override
	public List<Reimb> findReimByStatus(String status) {
		Session session = HibernateUtil.getSession();
		String hql = ("FROM Reimb r WHERE r.reimbStatus = :reimbStatus");
		Query<Reimb> query = session.createQuery(hql);
		query.setParameter("reimbStatus",reimbStatusDAO.findByStatus(status));
		List<Reimb> list = query.list();
		return list;
	}

}












