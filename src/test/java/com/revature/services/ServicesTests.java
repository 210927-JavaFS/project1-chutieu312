package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
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

public class ServicesTests {
	
	public static Logger log = LoggerFactory.getLogger(ServicesTests.class);
	
	public static LoginService loginService;
	public static ReimbService reimbService;
	public static UserService userService;
	
	public static Reimb reimb1;
	public static Reimb reimb2;
	
	public static UserDTO userDTO1;
	public static UserDTO userDTO2;
	
	public static User user1;
	public static User user2;
	
	private static UserRole userRole1;
	private static ReimbStatus reimbStatus1;
	private static ReimbStatus reimbStatus2;
	private static ReimbType reimbType2;
	
	@BeforeAll
	public static void setServices() {
		loginService = new LoginService();
		reimbService = new ReimbService();
		userService = new UserService();
		userDTO1 = new UserDTO();
		userDTO2 = new UserDTO();
		Bootstrap.run();
	}
	
	@BeforeEach
	public void setVar() {
		userDTO1.username="a";
		userDTO1.password="a";
		userDTO2.username="a";
		userDTO2.password="b";
		
		userRole1 =new UserRole(1,"Employee");
		user1 = new User(6,"f",Encrypt.encodePassword("f"),
				"Young1","Nguyen1","@f.com",userRole1);
		
		reimbStatus1 = new ReimbStatus(1,"Pending");
		reimbStatus2 = new ReimbStatus(2,"Approved");
		reimbType2 = new ReimbType(2,"Travel");
		reimb1 = new Reimb(7,700,new Date(),"flight1","spirit1",
				user1,reimbStatus1,reimbType2);
	}
	//==========================loginService=================================
	@Test
	public void testLoginPass() {
		assertNotNull(loginService.login(userDTO1));
	}
	@Test
	public void testLoginFail() {
		assertNull(loginService.login(userDTO2));
	}
	//===========================userService=================================
	// Test User Service
	@Test
	public void testAddUser() {
		assertTrue(userService.addUser(user1));
	}
	
	@Test
	public void testGetUserByID() {
		assertEquals(user1,userService.getUser(6));
	}
	
	@Test
	public void testGetAllUsers() {
		List<User> list = userService.getAllUsers();
		assertNotNull(list);
		assertEquals(6,list.size());
	}
	
	//============================reimbService================================
	// Test Reimb Service
	@Test
	public void testAddReimb() {
		assertTrue(reimbService.addReimb(reimb1));
	}
	
	@Test
	public void testGetAllReimb(){
		List<Reimb> list = reimbService.getAllReimbs();
		assertNotNull(list);
		assertEquals(6,list.size());
	}
	
	@Test
	public void testGetReimbById() {
		reimb2 = reimbService.getReimb(6);
		assertNotNull(reimb2);
		assertEquals(6,reimb2.getReimbId());
	}
	
	@Test
	public void testGetReimbByStatus() {
		List<Reimb> list = reimbService.getReimbByStatus("Pending");
		assertNotNull(list);
	}
//	
	@Test
	public void testGetReimbByAuthor() {
		List<Reimb> list = reimbService.getReimbByAuthor(1);
		assertNotNull(list);
		assertEquals(2,list.size());
	}
//	
	@Test
	public void testUpdateReimb1() {
		reimb1.setReimbStatus(reimbStatus2);
		assertTrue(reimbService.updateReimb(reimb1));
	}
	
	@Test
	public void testUpdateReimb2() {
		reimb1.setResolvedlDate(new Date());
		assertTrue(reimbService.updateReimb(reimb1));
	}
		
	//============================================================
	
//	@AfterEach
//	public void clearVar() {
//		userDTO1 = null;
//		userDTO2 = null;
//		user1 = null;
//		userRole1 = null;
//	}
	
	//============================================================
	
	@AfterAll
	public static void clearServices() {
		loginService = null;
		reimbService = null;
		userService = null;
		
		userDTO1 = null;
		userDTO2 = null;
		user1 = null;
		userRole1 = null;
		reimbStatus1 = null;
		reimbStatus2 = null;
		reimbType2 = null;
		reimb1 = null;

	}

}







































