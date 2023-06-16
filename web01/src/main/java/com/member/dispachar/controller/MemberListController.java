package com.member.dispachar.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ex01.dto.SqlMemberDTO;
import com.ex01.dto.TMemberDTO;
import com.ex01.service.SqlMemberService;
import com.member.dispachar.Controller;
import com.member.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MemberListController implements Controller{

	private MemberService memberService = MemberService.INSTANCE;
	
	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		
		log.info("======= 요청한 서비스 호출");
		
		List<TMemberDTO> memberList = memberService.listMember();
		req.setAttribute("memberList", memberList);
		
		log.info("======= testIndex로 이동");
		return "member/listMember";
	}
}
