package com.member.dispachar.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ex01.dto.TMemberDTO;
import com.member.dispachar.Controller;
import com.member.service.MemberService;

public class MemberUpdateController implements Controller{

	MemberService memberService = MemberService.INSTANCE;
	
	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) {

		TMemberDTO dto = new TMemberDTO();
		
		dto.setName(req.getParameter("name"));
		dto.setId(req.getParameter("id"));
		dto.setPwd(req.getParameter("pwd"));
		dto.setEmail(req.getParameter("email"));
		
		memberService.modifyMember(dto);
		
		
		return "tmemList.do";
	}
}
