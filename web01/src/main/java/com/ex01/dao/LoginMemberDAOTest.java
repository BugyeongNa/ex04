package com.ex01.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ex01.domain.LoginMemberVO;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoginMemberDAOTest {
	
	private LoginMemberDAO dao;
	
	@BeforeEach
	public void read() {
		dao = new LoginMemberDAO();
	}
	
	@Test
	public void testMemberFind() throws Exception{
		String name = "테스트0";
		String phone = "01000000000";
		
		LoginMemberVO vo = dao.findId(name, phone);
		
		System.out.println(vo);
	}
}
