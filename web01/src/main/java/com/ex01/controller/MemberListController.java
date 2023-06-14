package com.ex01.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ex01.dto.TMemberDTO;
import com.member.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/memList/*")
public class MemberListController extends HttpServlet{
	// 서비스 객체를 생성(dao, modelMapper)
	private MemberService memberService = MemberService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandler(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandler(req, resp);
	}
	
	protected void doHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getPathInfo();
		String nextPage = null;
		
		log.info("/member/*");
		log.info("getPathInfo(): "+action);
		
		if (action == null || action.equals("/listMember.do")) {
			try {
				List<TMemberDTO> memberList = memberService.memberList();
				req.setAttribute("memberList", memberList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			nextPage ="/member2/list.jsp";
			req.getRequestDispatcher(nextPage).forward(req, resp);
			
		} 
	}
}
