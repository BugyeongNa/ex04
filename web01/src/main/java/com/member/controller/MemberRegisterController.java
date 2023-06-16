package com.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ex01.dto.TMemberDTO;
import com.ex01.service.SqlMemberService;
import com.member.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/tmember/insert.do")
public class MemberRegisterController extends HttpServlet {

//	서비스 객체를 생성(dao, modelMapper)
	private MemberService memberService = MemberService.INSTANCE;
	String action = null;
	String nextPage = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		입력폼
		action = req.getPathInfo();
		nextPage = "/member2/memberForm.jsp";
		req.getRequestDispatcher(nextPage).forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		insert db처리
		
		TMemberDTO dto = new TMemberDTO();
		dto.setName(req.getParameter("name"));
		dto.setId(req.getParameter("id"));
		dto.setPwd(req.getParameter("pwd"));
		dto.setEmail(req.getParameter("email"));
		
		// 수정작업 서비스 요청(DB)
		memberService.addMember(dto);
		
		nextPage = "/tmember/list.do";
		req.getRequestDispatcher(nextPage).forward(req, resp);
	}

}
