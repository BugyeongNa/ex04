package com.member.dispachar.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.dispachar.Controller;
import com.member.service.MemberService;

public class MemberRemoveController implements Controller{

	MemberService memberService = MemberService.INSTANCE;
	
	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		
		String id = req.getParameter("id");
		memberService.deleteMember(id);
		
		return "tmemList.do";
	}
}
