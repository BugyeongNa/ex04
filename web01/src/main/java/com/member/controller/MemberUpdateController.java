package com.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ex01.dto.TMemberDTO;
import com.member.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/reg/update.do")
public class MemberUpdateController extends HttpServlet{
	
	private MemberService memberService;
	private String nextPage = "";
	
	public MemberUpdateController() {
		memberService = MemberService.INSTANCE;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		
		TMemberDTO dto = memberService.findMember(id);
		req.setAttribute("member", dto);
		
		nextPage = "/member2/modifyMember.jsp";
		req.getRequestDispatcher(nextPage).forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		TMemberDTO dto = new TMemberDTO();
		
		dto.setName(req.getParameter("name"));
		dto.setId(req.getParameter("id"));
		dto.setPwd(req.getParameter("pwd"));
		dto.setEmail(req.getParameter("email"));
		
		int result = memberService.modifyMember(dto);
		
		nextPage ="/member/list.do";
		resp.sendRedirect(req.getContextPath()+ nextPage);
		
	}
	
}
