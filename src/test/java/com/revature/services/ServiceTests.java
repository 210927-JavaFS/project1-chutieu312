package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.Bootstrap;
import com.revature.Encrypt;
import com.revature.models.Reimb;
import com.revature.models.ReimbStatus;
import com.revature.models.ReimbType;
import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.models.UserRole;

public class ServiceTests {

	public static LoginService loginService;
	public static ReimbService reimbService;
	public static UserService userService;
	
	public static Reimb reimb1;
	public static Reimb reimb2;
	public static Reimb reimb3;
	public static UserDTO userDTO1;
	public static UserDTO userDTO2;
	public static UserDTO userDTO3;
	public static User user1;
	public static User user2;
	public static UserRole userRole1;
	public static ReimbType reimbType1 = new ReimbType(1,"Lodging");
	public static ReimbType reimbType3 = new ReimbType(3,"Food");
	
	
	public static Logger log = LoggerFactory.getLogger(ServiceTests.class);	
	
	
//	If you're certain that the tests don't make any changes to those conditions, you can use beforeAll (which will run once).
//
//	If the tests do make changes to those conditions, then you would need to use beforeEach, which will run before every test, 
//	so it can reset the conditions for the next one.
	
	@BeforeAll
	public static void setServiceTest() {
		//log.info("In setAccountServiceTest");
		
		loginService = new LoginService();
		reimbService = new ReimbService();
		userService = new UserService();
		
		userDTO1 = new UserDTO(); userDTO1.username="a";userDTO1.password="a";
		userDTO2 = new UserDTO(); userDTO2.username="a";userDTO2.password="b";
		userDTO3 = new UserDTO();
		
		userRole1 = new UserRole(1,"Employee");
		user1 = new User("a1",Encrypt.encodePassword("a1"),"Henry1","Nguyen1","@a1.com",userRole1);
		user2 = new User("b1",Encrypt.encodePassword("b1"),"Tina1","Quach1","@b1.com",userRole1);
		Bootstrap.run();
		
		ReimbStatus reimbStatusPending = new ReimbStatus(1,"Pending");
		
		reimb1 = new Reimb(100.0,new Date(),"food","food.com",user1,reimbStatusPending,reimbType3);
		reimb2 = new Reimb(100.0,new Date(),"lodging","lodging.com",user2,reimbStatusPending,reimbType1);
		
		
	}	
	
//	@BeforeEach
//	public  void setServiceTest2() 
//	{
//		loginService = new LoginService();
//		reimbService = new ReimbService();
//		userService = new UserService();
//	}
			
	@Test
	public void testAddUser1() {
		log.info("In testAddReimb");
		assertTrue(userService.addUser(user1));
	}
	
	
	@Test
	public void testAddUser2() {
		log.info("In testAddReimb");
		assertTrue(userService.addUser(user2));
	}
	
	@Test
	public void testAddReimb() {
		log.info("In testAddReimb");
		assertTrue(reimbService.addReimb(reimb1));
		assertTrue(reimbService.addReimb(reimb2));
	}
	
	@Test
	public void testAddReimb2() {
		log.info("In testAddReimb2");
		assertTrue(reimbService.addReimb(reimb2));
	}
	
	@Test
	public void testUpdate() {
		log.info("In testUdate");
		reimb1.setResolvedlDate(new Date());
		assertTrue(reimbService.updateReimb(reimb1));
		
	}
	
	@Test
	public void testUpdate2() {
		log.info("In testUdate");
		ReimbStatus approved = new ReimbStatus(2,"Approved");
		reimb1.setReimbStatus(approved);
		assertTrue(reimbService.updateReimb(reimb1));
		
	}
	
	@Test
	public void testGetReimbByAuthor() {
		List<Reimb> list = reimbService.getReimbByAuthor(6);
		assertNotNull(list);
	}
	
	@Test
	public void testGetReimbByStatus() {
		List<Reimb> list = reimbService.getReimbByStatus("Approved");
		assertNotNull(list);
	}
	
	@Test
	public void testLogin() {
		log.info("In testLogin1");
		assertNotNull(loginService.login(userDTO1));
	}
	
	@Test
	public void testLogin2() {
		log.info("In testLogin2");
		assertNull(loginService.login(userDTO2));
	}	

	
//	@AfterEach
//	public  void setServiceTest2() 
//	{
//		loginService = new LoginService();
//		reimbService = new ReimbService();
//		userService = new UserService();
//	}

	
	@AfterAll 
	public static void clearAccountService() {
		loginService = null;
		reimbService = null;
		userService = null;
		log.info("in clearAccountService");
	}
			
}
