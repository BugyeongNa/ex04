package com.member.dispachar.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.dispachar.Controller;

public class MemberRegisterController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		
		return "member/memberForm";
	}
}
