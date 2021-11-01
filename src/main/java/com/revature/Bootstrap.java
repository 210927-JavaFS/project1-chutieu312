package com.revature;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Reimb;
import com.revature.models.ReimbStatus;
import com.revature.models.ReimbType;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.utils.HibernateUtil;

public class Bootstrap {
	
	public static void run() {
		addReimbStatus();
		addReimbType();
		addUserRole();
		addUser();
	}

	
	public static boolean addReimbStatus() {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.save(new ReimbStatus(1,"Pending"));
			session.save(new ReimbStatus(2,"Approved"));
			session.save(new ReimbStatus(3,"Denied"));
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean addReimbType() {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.save(new ReimbType(1,"Lodging"));
			session.save(new ReimbType(2,"Travel"));
			session.save(new ReimbType(3,"Food"));
			session.save(new ReimbType(4,"Other"));
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean addUserRole() {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.save(new UserRole(1,"Employee"));
			session.save(new UserRole(2,"Finance Manager"));
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean addUser() {
		try {
			UserRole userRole1 = new UserRole(1,"Employee");
			UserRole userRole2 = new UserRole(2,"Finance Manager");
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.save(new User(1,"a",Encrypt.encodePassword("a"),"Henry","Nguyen","@a.com",userRole1));
			session.save(new User(2,"b",Encrypt.encodePassword("b"),"Tina","Quach","@b.com",userRole1));
			session.save(new User(3,"c",Encrypt.encodePassword("c"),"Sara","Nguyen","@c.com",userRole1));
			session.save(new User(4,"d",Encrypt.encodePassword("d"),"Young","Nguyen","@d.com",userRole1));
			session.save(new User(5,"e",Encrypt.encodePassword("e"),"Can","Nguyen","@e.com",userRole2));
			tx.commit();
			//HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
	
//	public static boolean addReimb() {
//	try {
//		Session session = HibernateUtil.getSession();
//		Transaction tx = session.beginTransaction();
//		session.save(new Reimb());
//		session.save(new Reimb());
//		session.save(new Reimb());
//		tx.commit();
//		HibernateUtil.closeSession();
//		return true;
//	} catch (HibernateException e) {
//		e.printStackTrace();
//		return false;
//	}
//}
	
}
