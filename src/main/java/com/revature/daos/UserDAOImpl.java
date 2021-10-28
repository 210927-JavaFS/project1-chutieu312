package com.revature.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.User;
import com.revature.utils.HibernateUtil;

public class UserDAOImpl implements UserDAO {

	@Override
	public List<User> findAllUsers() {
		Session session = HibernateUtil.getSession();
		return session.createQuery("FROM User").list();
	}

	@Override
	public User findByUsername(String username) {
		Session session = HibernateUtil.getSession();
		return session.get(User.class, username);
	}
	
	@Override
	public User findById(int id) {
		Session session = HibernateUtil.getSession();
		return session.get(User.class, id);
	}

	@Override
	public boolean addUser(User User) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.save(User);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateUser(User User) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.merge(User);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteUser(User User) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.delete(User);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}



}
