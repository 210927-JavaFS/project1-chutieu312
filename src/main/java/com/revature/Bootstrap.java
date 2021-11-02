package com.revature;

import java.util.Date;
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
	
	private static ReimbStatus reimbStatus1 = new ReimbStatus(1,"Pending");
	private static ReimbStatus reimbStatus2 = new ReimbStatus(2,"Approved");
	private static ReimbStatus reimbStatus3 = new ReimbStatus(3,"Denied");
	
	private static ReimbType reimbType1 = new ReimbType(1,"Lodging");
	private static ReimbType reimbType2 = new ReimbType(2,"Travel");
	private static ReimbType reimbType3 = new ReimbType(3,"Food");
	private static ReimbType reimbType4 = new ReimbType(4,"Other");
	
	private static UserRole userRole1 =new UserRole(1,"Employee");
	private static UserRole userRole2 = new UserRole(2,"Finance Manager");
	
	private static User user1 = new User(1,"a",Encrypt.encodePassword("a"),"Henry","Nguyen","@a.com",userRole1);
	private static User user2 = new User(2,"b",Encrypt.encodePassword("b"),"Tina","Quach","@b.com",userRole1);
	private static User user3 = new User(3,"c",Encrypt.encodePassword("c"),"Sara","Nguyen","@c.com",userRole1);
	private static User user4 = new User(4,"d",Encrypt.encodePassword("d"),"Young","Nguyen","@d.com",userRole1);
	private static User user5 = new User(5,"e",Encrypt.encodePassword("e"),"Can","Nguyen","@e.com",userRole2);
	
	private static Reimb reimb1 = new Reimb(1,100,new Date(),"hotel","victory.com",user1,reimbStatus1,reimbType1);
	private static Reimb reimb2 = new Reimb(2,200,new Date(),"bus","bus.com",user2,reimbStatus1,reimbType2);
	private static Reimb reimb3 = new Reimb(3,300,new Date(),"pizza","domino",user4,reimbStatus1,reimbType3);
	private static Reimb reimb4 = new Reimb(4,400,new Date(),"ticket","zoo.com",user3,reimbStatus1,reimbType4);
	private static Reimb reimb5 = new Reimb(5,500,new Date(),"noodle","vegan.com",user2,reimbStatus1,reimbType3);
	private static Reimb reimb6 = new Reimb(6,600,new Date(),"flight","spirit",user1,reimbStatus1,reimbType2);
	
	public static void run() {
		addReimbStatus();
		addReimbType();
		addUserRole();
		addUser();
		addReimb();
	}

	public static boolean addReimbStatus() {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.save(reimbStatus1);
			session.save(reimbStatus2);
			session.save(reimbStatus3);
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
			session.save(reimbType1);
			session.save(reimbType2);
			session.save(reimbType3);
			session.save(reimbType4);
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
			session.save(userRole1);
			session.save(userRole2);
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
			session.save(user1);
			session.save(user2);
			session.save(user3);
			session.save(user4);
			session.save(user5);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean addReimb() {
	try {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.save(reimb1);
		session.save(reimb2);
		session.save(reimb3);
		session.save(reimb4);
		session.save(reimb5);
		session.save(reimb6);
		tx.commit();
		HibernateUtil.closeSession();
		return true;
	} catch (HibernateException e) {
		e.printStackTrace();
		return false;
	}
}
	
}
