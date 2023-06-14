package com.ex01.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ex01.domain.TodoVO;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBConnectTest {

	private TodoDAO todoDAO;
	
	@Test
	public void test() {
		int num1 = 10;
		int num2 = 10;
//		assertEquals() 같다고 확신하는 매서드
		Assertions.assertEquals(num1, num2);
	}
	
	@Test
	public void testConnection() throws Exception{
		Class.forName("org.mariadb.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection(
				"jdbc:mariadb://localhost:3306/ex00",
				"root",
				"0202");
		
		Assertions.assertNotNull(conn);
		conn.close();
	}
	
	@Test
	public void testHikariCPconnection() throws Exception{
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("org.mariadb.jdbc.Driver");
		config.setJdbcUrl("jdbc:mariadb://localhost:3306/mydb");
		config.setUsername("myUser");
		config.setPassword("1234");
		
		HikariDataSource ds = new HikariDataSource(config);
		Connection conn = ds.getConnection();
		
		Assertions.assertNotNull(conn);
		conn.close();
	}
	
	@BeforeEach
	public void read() {
		todoDAO = new TodoDAO();
	}
	
	@BeforeEach
	public void read2() {
		todoDAO = new TodoDAO();
	}
	
	@Test
	public void  testTime() throws Exception{
		System.out.println(todoDAO.getTime());
	}

//	db insert test
	@Test
	public void testInsert() throws Exception{
		TodoVO todoVO = TodoVO.builder()
				.tno(1L)
				.title("Sample Title...")
				.dueDate(LocalDate.of(2023, 5, 9))
				.build();
		
//		System.out.println(todoVO.toString());
		
		todoDAO.insert(todoVO);
	}
	
//	db select all test
	@Test
	public void testList() throws Exception{
		List<TodoVO> list = todoDAO.selectAll();
		list.forEach(vo -> System.out.println(vo));
	}
	
//	db select one test
	@Test
	public void testSelectOne() throws Exception{
		Long tno = 10L; 
		TodoVO vo = todoDAO.selectOne(tno);
		
		System.out.println(vo);
	}
	
//	db delete one test
	@Test
	public void testDeleteOne() throws Exception{
		Long tno = 1L;
		todoDAO.deletOne(tno);
		
	}
	
//	db update one test
	@Test
	public void testUpdateOne() throws Exception{
		TodoVO vo = TodoVO.builder()
				.tno(2L)
				.title("Sample Title...")
				.dueDate(LocalDate.of(2023, 5, 9))
				.finished(false)
				.build(); 
		
		todoDAO.updateOne(vo);
	}
}
	

//	DTO => setter/getter, VO => getter
	
