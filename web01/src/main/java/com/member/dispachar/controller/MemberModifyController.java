package com.member.dispachar.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ex01.dto.TMemberDTO;
import com.member.dispachar.Controller;
import com.member.service.MemberService;

public class MemberModifyController implements Controller{

	MemberService memberService = MemberService.INSTANCE;
	
	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		String id = req.getParameter("id");
		
		TMemberDTO dto = memberService.findMember(id);
		req.setAttribute("member", dto);
		
		return "member/modifyMember";
	}
	
	
}
